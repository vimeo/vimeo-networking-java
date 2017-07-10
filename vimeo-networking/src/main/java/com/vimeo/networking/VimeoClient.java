/*
 * Copyright (c) 2015 Vimeo (https://vimeo.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.vimeo.networking;

import com.google.gson.Gson;
import com.vimeo.networking.Search.FilterType;
import com.vimeo.networking.callbacks.AuthCallback;
import com.vimeo.networking.callbacks.ModelCallback;
import com.vimeo.networking.callbacks.VimeoCallback;
import com.vimeo.networking.callers.GetRequestType;
import com.vimeo.networking.logging.ClientLogger;
import com.vimeo.networking.logging.LoggingInterceptor;
import com.vimeo.networking.model.Comment;
import com.vimeo.networking.model.Document;
import com.vimeo.networking.model.PictureResource;
import com.vimeo.networking.model.PinCodeInfo;
import com.vimeo.networking.model.Privacy;
import com.vimeo.networking.model.User;
import com.vimeo.networking.model.Video;
import com.vimeo.networking.model.VimeoAccount;
import com.vimeo.networking.model.error.ErrorCode;
import com.vimeo.networking.model.error.VimeoError;
import com.vimeo.networking.model.notifications.SubscriptionCollection;
import com.vimeo.networking.model.search.SearchResponse;
import com.vimeo.networking.model.search.SuggestionResponse;
import com.vimeo.networking.utils.BaseUrlInterceptor;
import com.vimeo.networking.utils.VimeoNetworkUtil;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Credentials;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Client class used for making networking calls to Vimeo API.
 * <p>
 * Created by alfredhanssen on 4/12/15.
 *
 * @see <a href="https://developer.vimeo.com/api">The Vimeo API Docs</a>
 */
@SuppressWarnings("unused")
final public class VimeoClient {

    private static volatile boolean sContinuePinCodeAuthorizationRefreshCycle;

    @NotNull
    private final Configuration mConfiguration;
    @NotNull
    private final VimeoService mVimeoService;
    @Nullable
    private final Cache mCache;
    @Nullable
    private String mCurrentCodeGrantState;

    @NotNull
    private final Retrofit mRetrofit;

    @NotNull
    private final Gson mGson;
    @Nullable
    private Timer mPinCodeAuthorizationTimer;

    @NotNull
    private final BaseUrlInterceptor mBaseUrlInterceptor = new BaseUrlInterceptor();

    @NotNull
    private final String mLibraryUserAgentComponent;

    /**
     * Currently authenticated account
     */
    @Nullable
    private VimeoAccount mVimeoAccount;

    // -----------------------------------------------------------------------------------------------------
    // Configuration
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Configuration">
    @Nullable
    private static VimeoClient sSharedInstance;

    @NotNull
    public static VimeoClient getInstance() {
        if (sSharedInstance == null) {
            throw new AssertionError("Instance must be configured before use");
        }

        return sSharedInstance;
    }

    public static void initialize(@NotNull Configuration configuration) {
        sSharedInstance = new VimeoClient(configuration);
    }

    private VimeoClient(@NotNull Configuration configuration) {
        mConfiguration = configuration;
        mGson = VimeoNetworkUtil.getGson();
        mCache = mConfiguration.getCache();
        mRetrofit = createRetrofit();
        mVimeoService = mRetrofit.create(VimeoService.class);
        mLibraryUserAgentComponent = "VimeoNetworking/" + BuildConfig.VERSION + " (Java)";
        ClientLogger.setLogProvider(mConfiguration.mLogProvider);
        ClientLogger.setLogLevel(mConfiguration.mLogLevel);

        final VimeoAccount vimeoAccount = mConfiguration.loadAccount();
        setVimeoAccount(vimeoAccount);
    }

    /**
     * Sets a new base URL to be used for requests by the
     * VimeoClient for specific paths. Only the included
     * paths will have there base URL changed. If you
     * have called {@link #excludePathsForBaseUrl(HttpUrl, String...)}
     * before this, you must also call {@link #resetBaseUrl()}
     * before calling this method. Any call to this method will
     * override the previously used base URL.
     *
     * @param baseUrl    the base URL.
     * @param inclusions the paths to include. If this is empty,
     *                   then no paths will be changed.
     */
    @SuppressWarnings("WeakerAccess")
    public void includePathsForBaseUrl(@NotNull HttpUrl baseUrl, @NotNull String... inclusions) {
        mBaseUrlInterceptor.includePathsForBaseUrl(baseUrl, inclusions);
    }

    /**
     * Sets a new base URL to be used for all requests
     * by VimeoClient except the excluded paths. Excluded
     * paths will be the only ones to not have their base
     * changed. If you have called {@link #includePathsForBaseUrl(HttpUrl, String...)}
     * before this, you must also call {@link #resetBaseUrl()}
     * before calling this method. Any call to this method will
     * override the previously used base URL.
     *
     * @param baseUrl    the base URL.
     * @param exclusions the paths to exclude. If this is empty,
     *                   then all paths will be changed.
     */
    @SuppressWarnings("WeakerAccess")
    public void excludePathsForBaseUrl(@NotNull HttpUrl baseUrl, @NotNull String... exclusions) {
        mBaseUrlInterceptor.excludePathsForBaseUrl(baseUrl, exclusions);
    }

    /**
     * Resets the base URL used by the VimeoClient.
     */
    @SuppressWarnings("WeakerAccess")
    public void resetBaseUrl() {
        mBaseUrlInterceptor.resetBaseUrl();
    }

    @NotNull
    private String createUserAgent() {
        final String userProvidedAgent = mConfiguration.getUserAgentString();

        if (userProvidedAgent != null && !userProvidedAgent.isEmpty()) {
            return userProvidedAgent + ' ' + mLibraryUserAgentComponent;
        } else {
            return mLibraryUserAgentComponent;
        }
    }

    @NotNull
    private Retrofit createRetrofit() {
        return new Retrofit.Builder().baseUrl(mConfiguration.mBaseURLString)
                .client(createOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(mGson))
                .build();
    }

    @NotNull
    private OkHttpClient createOkHttpClient() {
        final RetrofitClientBuilder retrofitClientBuilder = new RetrofitClientBuilder();
        retrofitClientBuilder.setCache(mCache)
                .addNetworkInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        // Rewrite the server's cache-control header because are server sets all Cache-Control
                        // headers to no-store [AH] 4/24/2015
                        return chain.proceed(chain.request())
                                .newBuilder()
                                .header(Vimeo.HEADER_CACHE_CONTROL, Vimeo.HEADER_CACHE_PUBLIC)
                                .build();
                    }
                })
                .setReadTimeout(mConfiguration.mTimeout, TimeUnit.SECONDS)
                .setConnectionTimeout(mConfiguration.mTimeout, TimeUnit.SECONDS)
                .addInterceptor(new LoggingInterceptor())
                .addInterceptor(mBaseUrlInterceptor)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        final Request original = chain.request();

                        // Customize the request to add the user agent and accept header to all of them
                        final Request request = original.newBuilder()
                                .header(Vimeo.HEADER_USER_AGENT, createUserAgent())
                                .header(Vimeo.HEADER_ACCEPT, getAcceptHeader())
                                .method(original.method(), original.body())
                                .build();

                        // Customize or return the response
                        return chain.proceed(request);
                    }
                })
                .addNetworkInterceptors(mConfiguration.mNetworkInterceptors)
                .addInterceptors(mConfiguration.mInterceptors);

        if (mConfiguration.mCertPinningEnabled) {
            // Try and pin certificates to prevent man-in-the-middle attacks (if pinning is enabled)
            try {
                retrofitClientBuilder.pinCertificates();
            } catch (final Exception e) {
                ClientLogger.e("Exception when pinning certificate: " + e.getMessage(), e);
            }
        }

        return retrofitClientBuilder.build();
    }

    public void clearRequestCache() {
        try {
            if (mCache != null) {
                mCache.evictAll();
            } else {
                ClientLogger.e("Attempt to clear null cache");
            }
        } catch (final IOException e) {
            ClientLogger.e("Cache clearing error: " + e.getMessage(), e);
        }
    }

    @NotNull
    public Retrofit getRetrofit() {
        return mRetrofit;
    }

    public VimeoAccount getVimeoAccount() {
        if (mVimeoAccount == null) {
            throw new AssertionError("Account should never be null");
        }

        return mVimeoAccount;
    }

    public void setVimeoAccount(@Nullable VimeoAccount vimeoAccount) {
        if (vimeoAccount == null) {
            // If the provided account was null but we have an access token, persist the vimeo account with
            // just a token in it. Otherwise we'll want to leave the persisted account as null.
            vimeoAccount = new VimeoAccount(mConfiguration.mAccessToken);
            //noinspection VariableNotUsedInsideIf
            if (mConfiguration.mAccessToken != null) {
                mConfiguration.saveAccount(vimeoAccount, null);
            }
        }

        mVimeoAccount = vimeoAccount;
    }

    /**
     * @deprecated use {@link #saveAccount(VimeoAccount, String)} instead
     * <p>
     * We find no use in storing the password when you can persist the {@link VimeoAccount} across
     * application sessions.
     */
    @Deprecated
    public void saveAccount(@Nullable VimeoAccount vimeoAccount, String email, String password) {
        saveAccount(vimeoAccount, email);
    }

    /**
     * Sets the {@link #mVimeoAccount} field as well as triggering the saveAccount event for the
     * account store
     */
    @SuppressWarnings("WeakerAccess")
    public void saveAccount(@Nullable VimeoAccount vimeoAccount, String email) {
        setVimeoAccount(vimeoAccount);
        mConfiguration.saveAccount(vimeoAccount, email);
    }

    @NotNull
    public Configuration getConfiguration() {
        return mConfiguration;
    }
    // </editor-fold>

    // -----------------------------------------------------------------------------------------------------
    // Authentication
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Authentication">

    /**
     * Provides a URI that can be opened in a web view that will prompt for login and permissions
     * or used currently logged in users credentials.
     * <p>
     * If the user accepts your app, they are redirected to your redirect_uri along with two parameters:
     * {@link Vimeo#CODE_GRANT_RESPONSE_TYPE} or {@link Vimeo#CODE_GRANT_STATE}
     *
     * @return The URI that should be opened in a web view
     * @see <a href="https://developer.vimeo.com/api/authentication#generate-redirect">Vimeo API Docs</a>
     */
    @SuppressWarnings("WeakerAccess")
    public String getCodeGrantAuthorizationURI() {
        mCurrentCodeGrantState = UUID.randomUUID().toString();

        // TODO: TEST
        // Will look like the following: https://api.vimeo.com/oauth/authorize?<UTF8 encoded params>
        final HttpUrl baseUrl = HttpUrl.parse(mConfiguration.mBaseURLString);
        final HttpUrl uri = new HttpUrl.Builder().scheme(baseUrl.scheme())
                .host(baseUrl.host())
                .encodedPath(Vimeo.CODE_GRANT_PATH)
                .addQueryParameter(Vimeo.PARAMETER_REDIRECT_URI, mConfiguration.mCodeGrantRedirectURI)
                .addQueryParameter(Vimeo.PARAMETER_RESPONSE_TYPE, Vimeo.CODE_GRANT_RESPONSE_TYPE)
                .addQueryParameter(Vimeo.PARAMETER_STATE, mCurrentCodeGrantState)
                .addQueryParameter(Vimeo.PARAMETER_SCOPE, mConfiguration.mScope)
                .addQueryParameter(Vimeo.PARAMETER_CLIENT_ID, mConfiguration.mClientID)
                .build();
        return uri.toString();
    }


    /**
     * Authenticates the user from the {@link #getCodeGrantAuthorizationURI()}.
     * <p>
     * Exchanges the code for the access token.
     *
     * @param uri      URI from {@link #getCodeGrantAuthorizationURI()}
     * @param callback Callback pertaining to authentication
     * @see <a href="https://developer.vimeo.com/api/authentication#generate-redirect">Vimeo API Docs</a>
     */
    @Nullable
    public Call<VimeoAccount> authenticateWithCodeGrant(String uri, AuthCallback callback) {
        if (callback == null) {
            throw new AssertionError("Callback cannot be null");
        }

        if (uri == null || uri.isEmpty()) {
            callback.failure(new VimeoError("uri must not be null"));

            return null;
        }

        // TODO: TEST
        final Map<String, String> queryMap = VimeoNetworkUtil.getSimpleQueryMap(uri);
        final String code = queryMap.get(Vimeo.CODE_GRANT_RESPONSE_TYPE);
        final String state = queryMap.get(Vimeo.CODE_GRANT_STATE);

        if (code == null || code.isEmpty() || state == null || state.isEmpty() ||
            !state.equals(mCurrentCodeGrantState)) {
            mCurrentCodeGrantState = null;

            callback.failure(new VimeoError("Code grant code/state is null or state has changed"));

            return null;
        }

        mCurrentCodeGrantState = null;

        final String redirectURI = mConfiguration.mCodeGrantRedirectURI;

        final Call<VimeoAccount> call =
                mVimeoService.authenticateWithCodeGrant(getBasicAuthHeader(), redirectURI, code,
                                                        Vimeo.CODE_GRANT_TYPE);
        call.enqueue(new AccountCallback(this, callback));
        return call;
    }

    /**
     * Authorizes users of the app who are not signed in. This call requires a client id and client secret
     * to be set on the initial Configuration.
     * <p>
     * Leaves User as null in {@link VimeoAccount} model and populates the rest.
     *
     * @param callback Callback pertaining to authentication
     */
    public Call<VimeoAccount> authorizeWithClientCredentialsGrant(AuthCallback callback) {
        if (callback == null) {
            throw new AssertionError("Callback cannot be null");
        }

        final Call<VimeoAccount> call =
                mVimeoService.authorizeWithClientCredentialsGrant(getBasicAuthHeader(),
                                                                  Vimeo.CLIENT_CREDENTIALS_GRANT_TYPE,
                                                                  mConfiguration.mScope);
        call.enqueue(new AccountCallback(this, callback));
        return call;
    }

    /**
     * Authorizes users of the app who are not signed in. This call requires a client id and client secret
     * to be set on the initial Configuration.
     * <p>
     * Leaves User as null in {@link VimeoAccount} model and populates the rest.
     * <p>
     * WARNING: This contains a synchronous network call. Use {@link #authorizeWithClientCredentialsGrant(AuthCallback)}
     * for asynchronous use.
     */
    @Nullable
    public VimeoAccount authorizeWithClientCredentialsGrantSync() {

        final Call<VimeoAccount> call =
                mVimeoService.authorizeWithClientCredentialsGrant(getBasicAuthHeader(),
                                                                  Vimeo.CLIENT_CREDENTIALS_GRANT_TYPE,
                                                                  mConfiguration.mScope);

        VimeoAccount vimeoAccount = null;
        try {
            final retrofit2.Response<VimeoAccount> response = call.execute();
            if (response.isSuccessful()) {
                vimeoAccount = response.body();
                saveAccount(vimeoAccount, null);
            }
        } catch (final IOException e) {
            ClientLogger.e("Exception during authorizeWithClientCredentialsGrantSync: " + e.getMessage(), e);
        }

        return vimeoAccount;
    }

    /**
     * Exchange OAuth1 token/secret combination for a new OAuth2 token
     *
     * @param callback    Callback pertaining to authentication
     * @param token       An OAuth1 token
     * @param tokenSecret An OAuth1 token secret
     * @return The Account
     */
    public Call<VimeoAccount> exchangeOAuthOneToken(String token, String tokenSecret, AuthCallback callback) {
        if (callback == null) {
            throw new AssertionError("Callback cannot be null");
        }

        final Call<VimeoAccount> call = mVimeoService.exchangeOAuthOneToken(getBasicAuthHeader(),
                                                                            Vimeo.OAUTH_ONE_GRANT_TYPE,
                                                                            token,
                                                                            tokenSecret,
                                                                            mConfiguration.mScope);
        call.enqueue(new AccountCallback(this, callback));
        return call;
    }

    /**
     * This method is used to exchange a user access token from app A for a token for app B.
     * <p>
     * An app can use this to exchange an authenticated user's access token from app A for a new access
     * token for app B, using the mScope associated with app B. The caller of this method will be app B.
     * <p>
     * <b>This method is application restricted, and not meant to be consumed by the general public.</b>
     *
     * @param token    the authenticated user access token from application A. This <b>cannot</b> be a client
     *                 credentials access token.
     * @param callback Callback invoked upon success/fail of the service
     * @return A Call object
     */
    public Call<VimeoAccount> singleSignOnTokenExchange(@NotNull String token, AuthCallback callback) {
        if (callback == null) {
            throw new AssertionError("Callback cannot be null");
        }

        final Call<VimeoAccount> call =
                mVimeoService.ssoTokenExchange(getBasicAuthHeader(), token, mConfiguration.mScope);
        call.enqueue(new AccountCallback(this, callback));
        return call;
    }

    @Nullable
    public Call<VimeoAccount> join(String displayName, String email, String password, AuthCallback callback) {
        if (callback == null) {
            throw new AssertionError("Callback cannot be null");
        }

        if (displayName == null || displayName.isEmpty() || email == null || email.isEmpty() ||
            password == null || password.isEmpty()) {

            final VimeoError error = new VimeoError("Name, email, and password must be set.");

            if (displayName == null || displayName.isEmpty()) {
                error.addInvalidParameter(Vimeo.FIELD_NAME, ErrorCode.INVALID_INPUT_NO_NAME,
                                          "An empty or null name was provided.");
            }
            if (email == null || email.isEmpty()) {
                error.addInvalidParameter(Vimeo.FIELD_EMAIL, ErrorCode.INVALID_INPUT_NO_EMAIL,
                                          "An empty or null email was provided.");
            }
            if (password == null || password.isEmpty()) {
                error.addInvalidParameter(Vimeo.FIELD_PASSWORD, ErrorCode.INVALID_INPUT_NO_PASSWORD,
                                          "An empty or null password was provided.");
            }
            callback.failure(error);

            return null;
        }

        final HashMap<String, String> parameters = new HashMap<>();
        parameters.put(Vimeo.PARAMETER_USERS_NAME, displayName);
        parameters.put(Vimeo.PARAMETER_EMAIL, email);
        parameters.put(Vimeo.PARAMETER_PASSWORD, password);
        parameters.put(Vimeo.PARAMETER_SCOPE, mConfiguration.mScope);

        final Call<VimeoAccount> call = mVimeoService.join(getBasicAuthHeader(), parameters);
        call.enqueue(new AccountCallback(this, email, callback));
        return call;
    }

    @Nullable
    public Call<VimeoAccount> joinWithFacebookToken(String facebookToken, String email,
                                                    AuthCallback callback) {
        if (callback == null) {
            throw new AssertionError("Callback cannot be null");
        }

        if (facebookToken == null || facebookToken.isEmpty()) {
            final VimeoError error = new VimeoError("Facebook authentication error.");

            if (facebookToken == null || facebookToken.isEmpty()) {
                error.addInvalidParameter(Vimeo.FIELD_TOKEN, ErrorCode.UNABLE_TO_LOGIN_NO_TOKEN,
                                          "An empty or null Facebook access token was provided.");
            }
            callback.failure(error);
            return null;
        }

        final HashMap<String, String> parameters = new HashMap<>();
        parameters.put(Vimeo.PARAMETER_TOKEN, facebookToken);
        parameters.put(Vimeo.PARAMETER_SCOPE, mConfiguration.mScope);

        final Call<VimeoAccount> call = mVimeoService.join(getBasicAuthHeader(), parameters);
        call.enqueue(new AccountCallback(this, email, callback));
        return call;
    }

    @Nullable
    public Call<VimeoAccount> logIn(String email, String password, AuthCallback callback) {
        if (callback == null) {
            throw new AssertionError("Callback cannot be null");
        }

        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            final VimeoError error = new VimeoError("Email and password must be set.");

            if (email == null || email.isEmpty()) {
                error.addInvalidParameter(Vimeo.FIELD_USERNAME,
                                          ErrorCode.INVALID_INPUT_NO_EMAIL,
                                          "An empty or null email was provided.");
            }
            if (password == null || password.isEmpty()) {
                error.addInvalidParameter(Vimeo.FIELD_PASSWORD,
                                          ErrorCode.INVALID_INPUT_NO_PASSWORD,
                                          "An empty or null password was provided.");
            }
            callback.failure(error);

            return null;
        }

        final Call<VimeoAccount> call = mVimeoService.logIn(getBasicAuthHeader(),
                                                            email,
                                                            password,
                                                            Vimeo.PASSWORD_GRANT_TYPE,
                                                            mConfiguration.mScope);
        call.enqueue(new AccountCallback(this, email, callback));
        return call;
    }

    /**
     * Synchronous version of login call
     * <p>
     * Useful when dealing with Android AccountAuthenticator [AH]
     *
     * @param email    user's email address
     * @param password user's password
     * @return the account object since it is synchronous
     */
    public VimeoAccount logIn(String email, String password) {
        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            return null;
        }

        final Call<VimeoAccount> call =
                mVimeoService.logIn(getBasicAuthHeader(),
                                    email,
                                    password,
                                    Vimeo.PASSWORD_GRANT_TYPE,
                                    mConfiguration.mScope);

        VimeoAccount vimeoAccount = null;
        try {
            final retrofit2.Response<VimeoAccount> response = call.execute();
            if (response.isSuccessful()) {
                vimeoAccount = response.body();
            }
        } catch (final IOException e) {
            ClientLogger.e("Exception during logIn: " + e.getMessage(), e);
        }

        saveAccount(vimeoAccount, email);

        return vimeoAccount;
    }

    @Nullable
    public Call<VimeoAccount> loginWithFacebookToken(String facebookToken, String email, AuthCallback callback) {
        if (callback == null) {
            throw new AssertionError("Callback cannot be null");
        }

        if (facebookToken == null || facebookToken.isEmpty()) {
            final VimeoError error = new VimeoError("Facebook authentication error.");

            if (facebookToken == null || facebookToken.isEmpty()) {
                error.addInvalidParameter(Vimeo.FIELD_TOKEN,
                                          ErrorCode.UNABLE_TO_LOGIN_NO_TOKEN,
                                          "An empty or null Facebook access token was provided.");
            }
            callback.failure(error);
            return null;
        }

        final Call<VimeoAccount> call = mVimeoService.logInWithFacebook(getBasicAuthHeader(),
                                                                        Vimeo.FACEBOOK_GRANT_TYPE,
                                                                        facebookToken,
                                                                        mConfiguration.mScope);
        call.enqueue(new AccountCallback(this, email, callback));
        return call;
    }


    /**
     * Must be called when user logs out to ensure that the tokens have been invalidated
     * <p>
     *
     * @param callback Callback for handling logout
     */
    public Call<Object> logOut(@Nullable final VimeoCallback<Object> callback) {
        // If you've provided an access token to the configuration builder, we're assuming that you wouldn't
        // want to be able to log out of it, because this would invalidate the constant you've provided us.
        if (mConfiguration.mAccessToken != null && mVimeoAccount != null &&
            mConfiguration.mAccessToken.equals(mVimeoAccount.getAccessToken())) {
            if (callback != null) {
                callback.failure(new VimeoError(
                        "Don't log out of the account provided through the configuration builder. Need to ensure " +
                        "that the access token generated in the dev console isn't accidentally invalidated."));
            }
            return null;
        }
        final Call<Object> call = mVimeoService.logOut(getAuthHeader());
        call.enqueue(new VimeoCallback<Object>() {
            @Override
            public void success(Object o) {
                if (callback != null) {
                    callback.success(o);
                }
            }

            @Override
            public void failure(VimeoError error) {
                if (callback != null) {
                    callback.failure(error);
                }
            }
        });

        // Remove account immediately, but only after the auth header has been set (working properly?) [AH] 5/4/15
        mConfiguration.deleteAccount(mVimeoAccount);
        setVimeoAccount(null);
        return call;
    }

    /**
     * Class responsible for setting the account on successful authorization.
     * <p>
     * Sets the account on the {@link VimeoClient} as well as the {@link AccountStore}
     */
    private static class AccountCallback extends VimeoCallback<VimeoAccount> {

        private final VimeoClient mClient;
        private String mEmail;
        private final AuthCallback mCallback;

        AccountCallback(VimeoClient client, AuthCallback callback) {
            if (client == null || callback == null) {
                throw new AssertionError("Client and Callback must not be null");
            }

            mClient = client;
            mCallback = callback;
        }

        AccountCallback(VimeoClient client, String email, AuthCallback callback) {
            if (client == null || callback == null) {
                throw new AssertionError("Client and Callback must not be null");
            }

            mClient = client;
            mEmail = email;
            mCallback = callback;
        }

        /**
         * @deprecated use {@link #AccountCallback(VimeoClient, String, AuthCallback)} instead
         * <p>
         * We find no use in storing the password when you can persist the {@link VimeoAccount} across
         * application sessions.
         */
        @Deprecated
        public AccountCallback(VimeoClient client, String email, String password, AuthCallback callback) {
            this(client, email, callback);
        }

        @Override
        public void success(VimeoAccount vimeoAccount) {
            if (vimeoAccount.getUser() != null && (mEmail == null || mEmail.isEmpty())) {
                // We must always have a `name` field, which is used by the Android Account Manager for
                // display in the device Settings -> Accounts [KZ] 12/17/15
                final String userName = vimeoAccount.getUser().mName;
                final String name = userName != null ? userName : vimeoAccount.getUser().mUri;
                mClient.saveAccount(vimeoAccount, name);
            } else {
                mClient.saveAccount(vimeoAccount, mEmail);
            }
            mCallback.success();
        }

        @Override
        public void failure(VimeoError error) {
            mCallback.failure(error);
        }
    }

    /**
     * {@link AccountCallback} class responsible for setting the account on successful pin code authorization.
     * <p>
     * Sets the account on the {@link VimeoClient} as well as the {@link AccountStore}
     */
    private static class PinCodeAccountCallback extends AccountCallback {

        @NotNull
        private final Timer mTimer;

        PinCodeAccountCallback(@NotNull VimeoClient client, @NotNull AuthCallback callback,
                               @NotNull Timer timer) {
            super(client, callback);
            mTimer = timer;
        }

        private void cancelPolling() {
            //noinspection AssignmentToStaticFieldFromInstanceMethod
            VimeoClient.sContinuePinCodeAuthorizationRefreshCycle = false;
            mTimer.cancel();
        }

        @Override
        public void success(VimeoAccount vimeoAccount) {
            if (VimeoClient.sContinuePinCodeAuthorizationRefreshCycle) {
                cancelPolling();
                super.success(vimeoAccount);
            }
        }

        public void failure(VimeoError error) {
            if (VimeoClient.sContinuePinCodeAuthorizationRefreshCycle) {
                if (error.getHttpStatusCode() == HttpURLConnection.HTTP_BAD_REQUEST) {
                    // 400: Bad Request implies the code hasn't been activated yet, so try again
                    return;
                }
                cancelPolling();
                super.failure(error);
            }
        }

    }

    /**
     * This method cancels any previous active {@link VimeoClient#logInWithPinCode} attempts.
     */
    public void cancelPinCodeLogin() {
        //noinspection AssignmentToStaticFieldFromInstanceMethod
        VimeoClient.sContinuePinCodeAuthorizationRefreshCycle = false;
        if (mPinCodeAuthorizationTimer != null) {
            mPinCodeAuthorizationTimer.cancel();
        }
    }

    /**
     * {@link TimerTask} used for polling the server with a pin code at a scheduled interval
     */
    private static class PinCodePollingTimerTask extends TimerTask {

        private final PinCodeInfo mPinCodeInfo;
        private final Timer mTimer;
        private final long mExpiresInNano;
        private final WeakReference<AuthCallback> mAuthCallbackWeakReference;
        private final WeakReference<VimeoClient> mVimeoClient;
        private final String mScope;

        PinCodePollingTimerTask(@NotNull PinCodeInfo pinCodeInfo, @NotNull Timer timer, int expiresInSeconds,
                                @NotNull AuthCallback authCallback, @Nullable VimeoClient client,
                                @NotNull String scope) {
            mTimer = timer;
            mPinCodeInfo = pinCodeInfo;

            mExpiresInNano = System.nanoTime() + TimeUnit.SECONDS.toNanos(expiresInSeconds);
            mAuthCallbackWeakReference = new WeakReference<>(authCallback);
            mVimeoClient = new WeakReference<>(client);
            mScope = scope;
        }

        @Override
        public void run() {
            final AuthCallback authCallback = mAuthCallbackWeakReference.get();
            final VimeoClient vimeoClient = mVimeoClient.get();
            final long now = System.nanoTime();
            if (!VimeoClient.sContinuePinCodeAuthorizationRefreshCycle || now >= mExpiresInNano ||
                authCallback == null || vimeoClient == null) {
                if (VimeoClient.sContinuePinCodeAuthorizationRefreshCycle) {
                    //noinspection AssignmentToStaticFieldFromInstanceMethod
                    VimeoClient.sContinuePinCodeAuthorizationRefreshCycle = false;
                    mTimer.cancel();
                    if (authCallback != null && now >= mExpiresInNano) {
                        final VimeoError error = new VimeoError("Pin code expired.");
                        error.setErrorCode(ErrorCode.UNABLE_TO_LOGIN_PINCODE_EXPIRED);
                        authCallback.failure(error);
                    }
                }

            } else {
                final Call<VimeoAccount> call =
                        vimeoClient.mVimeoService.logInWithPinCode(vimeoClient.getBasicAuthHeader(),
                                                                   Vimeo.DEVICE_GRANT_TYPE,
                                                                   mPinCodeInfo.getUserCode(),
                                                                   mPinCodeInfo.getDeviceCode(),
                                                                   mScope);
                call.enqueue(new PinCodeAccountCallback(vimeoClient, authCallback, mTimer));
            }
        }
    }

    /**
     * (PRIVATE: Vimeo Use Only, will not work for third-party applications)
     * This is a method for Vimeo's internal use in association with pin code based authentication for
     * connected but keyboardless devices such as Android TV. The method will return immediately but will run
     * for an extended period of time asynchronously until a mTimeout expires, authorization has been
     * successful, or a failure occurs. The expected application flow is that the user is initially presented
     * a pin code (returned in pinCodeCallback), which they then enter into a special page (from
     * a link returned in pinCodeCallback) on Vimeo.com  on a different device.  Back on the original device,
     * the app is polling the api to check whether the pin code has been authenticated.  The `pinCodeCallback`
     * will be called after an initial request to retrieve the pin code and activate link.
     * This method will handle polling the api to check if the code has been activated, and it will ultimately
     * call the completion handler (authCallback) when that happens.  If the pin code expires while we're
     * waiting, completion handler (authCallback) will be called with an error
     *
     * @param pinCodeCallback {@link ModelCallback} that will receive {@link PinCodeInfo} to display.
     *                        This is held as weak reference.
     * @param authCallback    {@link AuthCallback} that will be notified when Authorization is complete.
     *                        This is held as a weak reference. It may be called back on a different thread.
     * @return a call object for the pin code request
     */
    public Call<PinCodeInfo> logInWithPinCode(@NotNull final ModelCallback<PinCodeInfo> pinCodeCallback,
                                              @NotNull final AuthCallback authCallback) {
        //noinspection AssignmentToStaticFieldFromInstanceMethod
        VimeoClient.sContinuePinCodeAuthorizationRefreshCycle = false;
        if (mPinCodeAuthorizationTimer != null) {
            mPinCodeAuthorizationTimer.cancel();
        }

        final String SCOPE = mConfiguration.mScope;
        final Call<PinCodeInfo> call = mVimeoService.getPinCodeInfo(getBasicAuthHeader(),
                                                                    Vimeo.DEVICE_GRANT_TYPE,
                                                                    SCOPE);

        call.enqueue(new ModelCallback<PinCodeInfo>(PinCodeInfo.class) {
            @Override
            public void success(PinCodeInfo pinCodeInfo) {
                if (pinCodeInfo.getUserCode() == null ||
                    pinCodeInfo.getDeviceCode() == null ||
                    pinCodeInfo.getActivateLink() == null ||
                    pinCodeInfo.getExpiresIn() <= 0 ||
                    pinCodeInfo.getInterval() <= 0) {
                    pinCodeCallback.failure(new VimeoError("Invalid data returned from server for pin code"));
                    return;
                }
                pinCodeCallback.success(pinCodeInfo);
                final int SECONDS_TO_MILLISECONDS = 1000;
                mPinCodeAuthorizationTimer = new Timer();
                //noinspection AssignmentToStaticFieldFromInstanceMethod
                VimeoClient.sContinuePinCodeAuthorizationRefreshCycle = true;
                final TimerTask task = new PinCodePollingTimerTask(pinCodeInfo,
                                                                   mPinCodeAuthorizationTimer,
                                                                   pinCodeInfo.getExpiresIn(),
                                                                   authCallback,
                                                                   sSharedInstance,
                                                                   SCOPE);
                final long period = SECONDS_TO_MILLISECONDS * pinCodeInfo.getInterval();
                mPinCodeAuthorizationTimer.scheduleAtFixedRate(task, 0, period);
            }

            @Override
            public void failure(VimeoError error) {
                pinCodeCallback.failure(error);
            }
        });
        return call;
    }
    // </editor-fold>

    // -----------------------------------------------------------------------------------------------------
    // Editing (Video, User)
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Editing (Video, User)">
    @Nullable
    public Call<Object> editVideo(String uri, String title, String description, @Nullable String password,
                                  @Nullable Privacy.PrivacyValue privacyValue,
                                  @Nullable HashMap<String, Object> parameters,
                                  ModelCallback<Object> callback) {
        if (callback == null) {
            throw new AssertionError("Callback cannot be null");
        }

        if (uri == null || uri.isEmpty()) {
            callback.failure(new VimeoError("uri cannot be empty!"));

            return null;
        }

        if (title == null && description == null && privacyValue == null) {
            // The fields above can be null individually, but if they're all null there is no point
            // in making the request 1/26/16 [KV]
            callback.failure(new VimeoError("title, description, and privacyValue cannot be empty!"));

            return null;
        }

        if (parameters == null) {
            parameters = new HashMap<>();
        }

        if (title != null) {
            parameters.put(Vimeo.PARAMETER_VIDEO_NAME, title);
        }

        if (description != null) {
            parameters.put(Vimeo.PARAMETER_VIDEO_DESCRIPTION, description);
        }

        if (privacyValue != null) {
            if (privacyValue == Privacy.PrivacyValue.PASSWORD) {
                if (password == null) {
                    callback.failure(new VimeoError("Password cannot be null password privacy type"));

                    return null;
                }

                parameters.put(Vimeo.PARAMETER_VIDEO_PASSWORD, password);
            }

            final String privacyString = privacyValue.getText();
            final HashMap<String, String> privacyMap = new HashMap<>();
            privacyMap.put(Vimeo.PARAMETER_VIDEO_VIEW, privacyString);
            parameters.put(Vimeo.PARAMETER_VIDEO_PRIVACY, privacyMap);
        }

        final Call<Object> call = mVimeoService.edit(getAuthHeader(), uri, parameters);
        call.enqueue(getRetrofitCallback(callback));

        return call;
    }

    @Nullable
    public Call<Object> editUser(String uri,
                                 @Nullable String name,
                                 @Nullable String location,
                                 @Nullable String bio,
                                 ModelCallback<Object> callback) {
        if (callback == null) {
            throw new AssertionError("Callback cannot be null");
        }

        if (uri == null || uri.isEmpty()) {
            callback.failure(new VimeoError("uri cannot be empty!"));

            return null;
        }

        if (name == null && location == null && bio == null) // No point in editing user
        {
            callback.failure(new VimeoError("name, location, and bio cannot all be empty!"));

            return null;
        }

        final HashMap<String, Object> parameters = new HashMap<>();

        if (name != null) {
            parameters.put(Vimeo.PARAMETER_USERS_NAME, name);
        }

        if (location != null) {
            parameters.put(Vimeo.PARAMETER_USERS_LOCATION, location);
        }

        if (bio != null) {
            parameters.put(Vimeo.PARAMETER_USERS_BIO, bio);
        }

        final Call<Object> call = mVimeoService.edit(getAuthHeader(), uri, parameters);
        call.enqueue(getRetrofitCallback(callback));
        return call;
    }

    /**
     * Used to edit a users push notification subscriptions
     *
     * @param subscriptionMap a map of the subscriptions you wish to change
     * @param callback        callback to be invoked when the request finishes
     * @return A Call so that the request can be cancelled if need be
     */
    @Nullable
    public Call<SubscriptionCollection> editSubscriptions(@NotNull Map<String, Boolean> subscriptionMap,
                                                          @NotNull
                                                                  ModelCallback<SubscriptionCollection> callback) {

        final Call<SubscriptionCollection> call = mVimeoService.editSubscriptions(getAuthHeader(), subscriptionMap);
        call.enqueue(callback);

        return call;
    }

    // </editor-fold>

    // -----------------------------------------------------------------------------------------------------
    // Documents
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Documents">

    /**
     * An asynchronous request to get a {@link Document} representing the Vimeo Terms of Service
     *
     * @param callback the {@link ModelCallback} to be invoked when the request finishes
     * @return a {@link Call} with the {@link Document} type. This can be used for request
     * cancellation.
     */
    @NotNull
    public Call<Document> getTermsOfService(@NotNull ModelCallback<Document> callback) {
        return getDocument(Vimeo.ENDPOINT_TERMS_OF_SERVICE, callback);
    }

    /**
     * An asynchronous request to get a {@link Document} representing the Vimeo Privacy Policy
     *
     * @param callback the {@link ModelCallback} to be invoked when the request finishes
     * @return a {@link Call} with the {@link Document} type. This can be used for request
     * cancellation.
     */
    @NotNull
    public Call<Document> getPrivacyPolicy(@NotNull ModelCallback<Document> callback) {
        return getDocument(Vimeo.ENDPOINT_PRIVACY_POLICY, callback);
    }

    /**
     * An asynchronous request to get a {@link Document} representing the Vimeo Payment Addendum
     *
     * @param callback the {@link ModelCallback} to be invoked when the request finishes
     * @return a {@link Call} with the {@link Document} type. This can be used for request
     * cancellation.
     */
    @NotNull
    public Call<Document> getPaymentAddendum(@NotNull ModelCallback<Document> callback) {
        return getDocument(Vimeo.ENDPOINT_PAYMENT_ADDENDUM, callback);
    }

    /**
     * Gets a {@link Document} at the provided uri. When finished, the callback will be invoked.
     *
     * @param uri      the uri of the Document
     * @param callback the {@link ModelCallback} to be invoked when the request finishes
     * @return a {@link Call} with the {@link Document} type. This can be used for request
     * cancellation.
     */
    @NotNull
    public Call<Document> getDocument(@NotNull String uri, @NotNull ModelCallback<Document> callback) {
        final Call<Document> call = mVimeoService.getDocument(getAuthHeader(), uri);
        call.enqueue(callback);

        return call;
    }
    // </editor-fold>

    // -----------------------------------------------------------------------------------------------------
    // Pictures
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Pictures">

    /**
     * Create a picture resource using the given uri
     * <p>
     * For more in depth documentation on this flow, see https://developer.vimeo.com/api/upload/pictures
     *
     * @param uri      should be in the format /videos/{video_id}/pictures or /user/{user_id}/pictures
     *                 The Uri should be obtained from metadata.connections.pictures.uri
     * @param callback The ModelCallback containing PictureResource data
     */
    @Nullable
    public Call<PictureResource> createPictureResource(String uri, ModelCallback<PictureResource> callback) {
        if (uri == null || uri.trim().isEmpty()) {
            callback.failure(new VimeoError("uri cannot be empty!"));
            return null;
        }

        final Call<PictureResource> call = mVimeoService.createPictureResource(getAuthHeader(), uri);
        call.enqueue(callback);
        return call;
    }

    /**
     * Activate a picture resource
     *
     * @param uri The Uri that is found in the PictureResource returned from
     *            {@link #createPictureResource(String, ModelCallback)}
     */
    @Nullable
    public Call<Object> activatePictureResource(String uri, ModelCallback callback) {
        if (uri == null || uri.trim().isEmpty()) {
            callback.failure(new VimeoError("uri cannot be empty!"));
            return null;
        }
        final HashMap<String, Object> parameters = new HashMap<>();
        parameters.put(Vimeo.PARAMETER_ACTIVE, true);
        final Call<Object> call = mVimeoService.edit(getAuthHeader(), uri, parameters);
        call.enqueue(getRetrofitCallback(callback));
        return call;
    }

    /**
     * Delete a picture resource
     * This is helpful if your activation or upload step fails - you can delete the resource allocated
     *
     * @param uri The Uri that is found in the PictureResource returned from
     *            {@link #createPictureResource(String, ModelCallback)}
     */
    public void deletePictureResource(String uri, ModelCallback callback) {
        if (uri == null || uri.trim().isEmpty()) {
            callback.failure(new VimeoError("uri cannot be empty!"));
            return;
        }

        deleteContent(uri, callback);
    }

    // </editor-fold>

    // -----------------------------------------------------------------------------------------------------
    // Video actions (Like, Watch Later, Commenting)
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Video actions (Like, Watch Later, Commenting)">
    public Call<Object> updateFollow(boolean follow, String uri, ModelCallback callback) {
        return updateFollow(follow, uri, callback, true);
    }

    public Call<Object> updateFollow(boolean follow, String uri, ModelCallback callback, boolean enqueue) {
        if (follow) {
            return follow(uri, callback, enqueue);
        } else {
            return unfollow(uri, callback, enqueue);
        }
    }

    public Call<Object> follow(String uri, ModelCallback callback, boolean enqueue) {
        return putContent(uri, null, callback, enqueue);
    }

    public Call<Object> unfollow(String uri, ModelCallback callback, boolean enqueue) {
        return deleteContent(uri, callback, enqueue);
    }

    public Call<Object> updateLikeVideo(boolean like, String uri, @Nullable String password,
                                        ModelCallback callback) {
        if (like) {
            return likeVideo(uri, password, callback);
        } else {
            return unlikeVideo(uri, password, callback);
        }
    }

    @SuppressWarnings("WeakerAccess")
    public Call<Object> likeVideo(String uri, @Nullable String password, ModelCallback callback) {
        final Map<String, String> options = new HashMap<>();
        if (password != null) {
            options.put(Vimeo.PARAMETER_PASSWORD, password);
        }
        return putContent(uri, options, callback, true);
    }

    @SuppressWarnings("WeakerAccess")
    public Call<Object> unlikeVideo(String uri, @Nullable String password, ModelCallback callback) {
        final Map<String, String> options = new HashMap<>();
        if (password != null) {
            options.put(Vimeo.PARAMETER_PASSWORD, password);
        }
        return deleteContent(uri, options, callback, true);
    }

    public Call<Object> updateWatchLaterVideo(boolean watchLater, String uri, @Nullable String password,
                                              ModelCallback callback) {
        if (watchLater) {
            return watchLaterVideo(uri, password, callback);
        } else {
            return unwatchLaterVideo(uri, password, callback);
        }
    }

    @SuppressWarnings("WeakerAccess")
    public Call<Object> watchLaterVideo(String uri, @Nullable String password, ModelCallback callback) {
        final Map<String, String> options = new HashMap<>();
        if (password != null) {
            options.put(Vimeo.PARAMETER_PASSWORD, password);
        }
        return putContent(uri, options, callback, true);
    }

    @SuppressWarnings("WeakerAccess")
    public Call<Object> unwatchLaterVideo(String uri, @Nullable String password, ModelCallback callback) {
        final Map<String, String> options = new HashMap<>();
        if (password != null) {
            options.put(Vimeo.PARAMETER_PASSWORD, password);
        }
        return deleteContent(uri, options, callback, true);
    }


    @Nullable
    public Call<Comment> comment(String uri,
                                 String comment,
                                 @Nullable String password,
                                 ModelCallback<Comment> callback) {
        if (callback == null) {
            throw new AssertionError("Callback cannot be null");
        }

        if (uri == null || uri.isEmpty() || comment == null || comment.isEmpty()) {
            callback.failure(new VimeoError("uri cannot be empty!"));

            return null;
        }

        final Map<String, String> options = new HashMap<>();
        if (password != null) {
            options.put(Vimeo.PARAMETER_PASSWORD, password);
        }

        final HashMap<String, String> postBody = new HashMap<>();
        postBody.put(Vimeo.PARAMETER_COMMENT_TEXT_BODY, comment);

        final Call<Comment> call = mVimeoService.comment(getAuthHeader(), uri, options, postBody);
        call.enqueue(callback);
        return call;
    }

    /**
     * This will fetch a video synchronously by-passing the cache. To fetch from cache, use
     * {@link #fetchVideoSync(String, CacheControl, String)}
     *
     * @param uri         the uri of the video to fetch
     * @param fieldFilter any field filters to apply for the video response, may be null
     * @return a Retrofit response object with the Video as the body
     */
    @Nullable
    public retrofit2.Response<Video> fetchVideoSync(String uri, @Nullable String fieldFilter) {
        return fetchVideoSync(uri, CacheControl.FORCE_NETWORK, fieldFilter);
    }

    /**
     * This will fetch a video synchronously
     *
     * @param uri          the uri of the video to fetch
     * @param cacheControl the cache control
     * @param fieldFilter  any field filters to apply for the video response, may be null
     * @return a Retrofit response object with the Video as the body
     */
    @Nullable
    public retrofit2.Response<Video> fetchVideoSync(String uri,
                                                    CacheControl cacheControl,
                                                    @Nullable String fieldFilter) {
        final String cacheHeaderValue = createCacheControlString(cacheControl);
        try {
            return mVimeoService.getVideo(getAuthHeader(),
                                          cacheHeaderValue,
                                          uri,
                                          createQueryMap(null, null, fieldFilter)).execute();
        } catch (final IOException e) {
            return null;
        }
    }
    // </editor-fold>

    // -----------------------------------------------------------------------------------------------------
    // Gets, posts, puts, deletes
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Gets, posts, puts, deletes">
    public void deleteVideo(String uri, Map<String, String> options, ModelCallback callback) {
        deleteContent(uri, options, callback, true);
    }

    private VimeoCallback<Object> getRetrofitCallback(final ModelCallback<Object> callback) {
        return new VimeoCallback<Object>() {
            @Override
            public void success(Object o) {
                // Handle the gson parsing using a mGsonDeserializer object
                mConfiguration.mGsonDeserializer.deserialize(mGson, o, callback);
            }

            @Override
            public void failure(VimeoError error) {
                callback.failure(error);
            }
        };
    }

    @Nullable
    public Call<Object> search(String uri, String query, ModelCallback callback,
                               @Nullable Map<String, String> searchRefinement, @Nullable String fieldFilter) {
        if (query == null || query.isEmpty()) {
            callback.failure(new VimeoError("Query cannot be empty!"));
            return null;
        }

        // If no sort refinement specified, default to relevance
        if (searchRefinement == null) {
            searchRefinement = new SearchRefinementBuilder(Vimeo.RefineSort.RELEVANCE).build();
        } else if (!searchRefinement.containsKey(Vimeo.PARAMETER_GET_SORT)) {
            searchRefinement.put(Vimeo.PARAMETER_GET_SORT, Vimeo.RefineSort.RELEVANCE.getText());
        }

        // Search always defaults to using the network
        return fetchContent(uri, CacheControl.FORCE_NETWORK, callback, query, searchRefinement, fieldFilter);
    }

    /**
     * A package private search method: use
     * {@link Search#search(String, FilterType, ModelCallback, Map, List, String, String)}
     * which relies on this method.
     *
     * @param queryMap the query parameters
     * @param callback the callback to be invoked when the call finishes
     * @return the Call object provided by the Retrofit service
     */
    @NotNull
    Call<SearchResponse> search(@NotNull Map<String, String> queryMap,
                                @NotNull ModelCallback<SearchResponse> callback) {

        final Call<SearchResponse> call = mVimeoService.search(getAuthHeader(), queryMap);
        call.enqueue(callback);
        return call;
    }

    /**
     * A package private search suggestions method. See {@link Search#suggest(String, int, int, ModelCallback)} for
     * public usage of the suggestion API.
     *
     * @param queryMap the query parameters
     * @param callback the callback to be invokedn when the call finishes
     * @return the {@link Call} object provided by Retrofit
     */
    @NotNull
    Call<SuggestionResponse> suggest(@NotNull Map<String, String> queryMap,
                                     @NotNull ModelCallback<SuggestionResponse> callback) {
        final Call<SuggestionResponse> call = mVimeoService.suggest(getAuthHeader(), queryMap);
        call.enqueue(callback);

        return call;
    }


    /**
     * Fetches the currently authenticated user from the API
     *
     * @param callback the callback to be invoked when the request finishes
     */
    public void fetchCurrentUser(@NotNull ModelCallback<User> callback) {
        fetchCurrentUser(callback, null);
    }

    /**
     * Fetches the currently authenticated user from the API
     *
     * @param callback    the callback to be invoked when the request finishes
     * @param fieldFilter the field filter to apply to the request
     */
    public void fetchCurrentUser(@NotNull ModelCallback<User> callback, @Nullable String fieldFilter) {
        fetchContent(Vimeo.ENDPOINT_ME, CacheControl.FORCE_NETWORK, callback, fieldFilter);
    }

    /**
     * A generic GET call that takes in the URI of the specific resource.
     *
     * @param uri           URI of the resource to GET
     * @param cacheControl  Cache control type (includes max age and other cache policy information)
     * @param callback      The callback for the specific model type of the resource
     * @param query         Query string for hitting the search endpoint
     * @param refinementMap Used to refine lists (generally for search) with sorts and filters
     * @param fieldFilter   The string of fields to include in the response (highly recommended!)
     *                      {@link SearchRefinementBuilder}
     * @see <a href="https://developer.vimeo.com/api/spec#common-parameters">Vimeo API Field Filter Docs</a>
     */
    @Nullable
    public Call<Object> fetchContent(@NotNull String uri, CacheControl cacheControl,
                                     @NotNull ModelCallback callback, @Nullable String query,
                                     @Nullable Map<String, String> refinementMap,
                                     @Nullable String fieldFilter) {
        if (uri.isEmpty()) {
            callback.failure(new VimeoError("Uri cannot be empty!"));
            return null;
        }

        final String cacheHeaderValue = createCacheControlString(cacheControl);

        final Map<String, String> queryMap = createQueryMap(query, refinementMap, fieldFilter);

        final Call<Object> call = mVimeoService.GET(getAuthHeader(), uri, queryMap, cacheHeaderValue);
        call.enqueue(getRetrofitCallback(callback));
        return call;
    }

    /**
     * A generic GET call that takes in the URI of the specific resource.
     *
     * @param uri           URI of the resource to GET
     * @param cacheControl  Cache control type (includes max age and other cache policy information)
     * @param callback      The callback for the specific model type of the resource
     * @param query         Query string for hitting the search endpoint
     * @param refinementMap Used to refine lists (generally for search) with sorts and filters
     * @param fieldFilter   The string of fields to include in the response (highly recommended!)
     *                      {@link SearchRefinementBuilder}
     * @param caller        The {@link GetRequestType.Caller} for the expected response type
     * @see <a href="https://developer.vimeo.com/api/spec#common-parameters">Vimeo API Field Filter Docs</a>
     */
    @Nullable
    public <DataType_T> retrofit2.Call<DataType_T> getContent(@NotNull String uri,
                                                              @NotNull CacheControl cacheControl,
                                                              @NotNull VimeoCallback<DataType_T> callback,
                                                              @Nullable String query,
                                                              @Nullable Map<String, String> refinementMap,
                                                              @Nullable String fieldFilter,
                                                              @NotNull GetRequestType.Caller<DataType_T> caller) {
        if (uri.isEmpty()) {
            callback.failure(new VimeoError("Uri cannot be empty!"));
            return null;
        }

        final String cacheHeaderValue = createCacheControlString(cacheControl);
        final Map<String, String> queryMap = createQueryMap(query, refinementMap, fieldFilter);
        final retrofit2.Call<DataType_T> call = caller.call(getAuthHeader(),
                                                            uri,
                                                            queryMap,
                                                            cacheHeaderValue,
                                                            mVimeoService);
        call.enqueue(callback);
        return call;
    }

    /**
     * A generic GET call that takes in the URI of the specific resource and fetches content synchronously.
     *
     * @param uri           URI of the resource to GET
     * @param cacheControl  Cache control type (includes max age and other cache policy information)
     * @param query         Query string for hitting the search endpoint
     * @param refinementMap Used to refine lists (generally for search) with sorts and filters
     * @param fieldFilter   The string of fields to include in the response (highly recommended!)
     *                      {@link SearchRefinementBuilder}
     * @param caller        The {@link GetRequestType.Caller} for the expected response type
     * @see <a href="https://developer.vimeo.com/api/spec#common-parameters">Vimeo API Field Filter Docs</a>
     */
    @Nullable
    public <DataType_T> retrofit2.Response<DataType_T> getContentSync(
            @NotNull String uri,
            @NotNull CacheControl cacheControl,
            @Nullable String query,
            @Nullable Map<String, String> refinementMap,
            @Nullable String fieldFilter,
            @NotNull GetRequestType.Caller<DataType_T> caller) {

        if (uri.isEmpty()) {
            throw new AssertionError("uri cannot be null or empty");
        }

        final String cacheHeaderValue = createCacheControlString(cacheControl);
        final Map<String, String> queryMap = createQueryMap(query, refinementMap, fieldFilter);
        final retrofit2.Call<DataType_T> call = caller.call(getAuthHeader(),
                                                            uri,
                                                            queryMap,
                                                            cacheHeaderValue,
                                                            mVimeoService);
        try {
            return call.execute();
        } catch (final IOException ioe) {
            return null;
        }
    }

    @Nullable
    public Call<Object> fetchContent(String uri, CacheControl cacheControl, ModelCallback callback) {
        return fetchContent(uri, cacheControl, callback, null, null, null);
    }

    @Nullable
    public Call<Object> fetchContent(String uri,
                                     CacheControl cacheControl,
                                     ModelCallback callback,
                                     String fieldFilter) {
        return fetchContent(uri, cacheControl, callback, null, null, fieldFilter);
    }

    @Nullable
    public Call<Object> fetchNetworkSortedContent(String uri, ModelCallback callback, String fieldFilter) {
        return fetchContent(uri, CacheControl.FORCE_NETWORK, callback, null,
                            new SearchRefinementBuilder(Vimeo.RefineSort.DEFAULT).build(), fieldFilter);
    }

    @Nullable
    public Call<Object> fetchCachedSortedContent(String uri, ModelCallback callback, String fieldFilter) {
        return fetchContent(uri, CacheControl.FORCE_CACHE, callback, null,
                            new SearchRefinementBuilder(Vimeo.RefineSort.DEFAULT).build(), fieldFilter);
    }

    @Nullable
    public Call<Object> fetchCachedContent(String uri, ModelCallback callback) {
        return fetchContent(uri, CacheControl.FORCE_CACHE, callback);
    }

    @Nullable
    public Call<Object> fetchNetworkContent(String uri, ModelCallback callback) {
        return fetchContent(uri, CacheControl.FORCE_NETWORK, callback);
    }

    /**
     * A generic POST call that takes in the URI of the specific resource.
     *
     * @param uri          URI of the resource to POST
     * @param cacheControl Cache control type
     * @param postBody     The body of the POST request
     * @param callback     The callback for the specific model type of the resource
     */
    @Nullable
    public Call<Object> postContent(@NotNull String uri, CacheControl cacheControl,
                                    HashMap<String, String> postBody, @NotNull VimeoCallback callback) {

        if (postBody == null) {
            postBody = new HashMap<>();
        }

        String cacheHeaderValue = null;
        if (cacheControl != null) {
            cacheHeaderValue = cacheControl.toString();
        }

        // Don't use the deserialization callback because we don't get objects returned with post
        return POST(getAuthHeader(), uri, cacheHeaderValue, postBody, callback);
    }

    /**
     * A generic POST call that takes in the URI of the specific resource.
     *
     * @param uri          URI of the resource to POST
     * @param cacheControl Cache control type
     * @param postBody     The body of the POST request
     * @param callback     The callback for the specific model type of the resource
     */
    @Nullable
    public Call<Object> postContent(@NotNull String uri, CacheControl cacheControl,
                                    ArrayList<Object> postBody, @NotNull VimeoCallback<Object> callback) {

        if (postBody == null) {
            postBody = new ArrayList<>();
        }

        String cacheHeaderValue = null;
        if (cacheControl != null) {
            cacheHeaderValue = cacheControl.toString();
        }

        final Call<Object> call = mVimeoService.POST(getAuthHeader(), uri, cacheHeaderValue, postBody);
        call.enqueue(callback);
        return call;
    }

    /**
     * A POST call where the API doesn't return any response body. This is only handled by Retrofit
     * if you specify a Void object response type.
     * Example: Forgot password call
     *
     * @param uri      URI of the resource to POST
     * @param postBody The body of the POST request
     * @param callback The callback for the specific model type of the resource
     */
    @Nullable
    public Call<Void> emptyResponsePost(String uri,
                                        @Nullable HashMap<String, String> postBody,
                                        VimeoCallback<Void> callback) {
        if (callback == null) {
            throw new AssertionError("Callback cannot be null");
        }

        if (uri == null) {
            callback.failure(new VimeoError("uri cannot be empty!"));

            return null;
        }

        if (postBody == null) {
            postBody = new HashMap<>();
        }

        final Call<Void> call = mVimeoService.emptyResponsePost(getAuthHeader(), uri, postBody);
        call.enqueue(callback);
        return call;
    }

    /**
     * A PATCH call where the API doesn't return any response body. This is only handled by Retrofit
     * if you specify a Void object response type.
     *
     * @param uri         URI of the resource to PATCH to
     * @param queryParams any query parameters of the PATCH
     * @param patchBody   the body of the PATCH, it is likely a List or a Map
     * @param callback    the callback to be invoked upon request completion
     */
    @NotNull
    public Call<Void> emptyResponsePatch(@NotNull String uri,
                                         @Nullable Map<String, String> queryParams,
                                         @NotNull Object patchBody,
                                         @NotNull VimeoCallback<Void> callback) {
        if (queryParams == null) {
            queryParams = new HashMap<>();
        }

        final Call<Void> call = mVimeoService.emptyResponsePatch(getAuthHeader(), uri, queryParams, patchBody);
        call.enqueue(callback);

        return call;
    }

    @Nullable
    public Call<Object> putContent(String uri,
                                   @Nullable Map<String, String> options,
                                   ModelCallback callback,
                                   boolean enqueue) {
        if (callback == null) {
            throw new AssertionError("Callback cannot be null");
        }


        if (uri == null || uri.isEmpty()) {
            callback.failure(new VimeoError("uri cannot be empty!"));
            return null;
        }

        if (options == null) {
            options = new HashMap<>();
        }

        return PUT(getAuthHeader(), uri, options, getRetrofitCallback(callback), enqueue);
    }

    @Nullable
    public Call<Object> deleteContent(String uri,
                                      @Nullable Map<String, String> options,
                                      ModelCallback callback,
                                      boolean enqueue) {
        if (callback == null) {
            throw new AssertionError("Callback cannot be null");
        }

        if (uri == null || uri.isEmpty()) {
            callback.failure(new VimeoError("uri cannot be empty!"));

            return null;
        }
        if (options == null) {
            options = new HashMap<>();
        }

        return DELETE(getAuthHeader(), uri, options, getRetrofitCallback(callback), enqueue);
    }

    @SuppressWarnings("WeakerAccess")
    public Call<Object> deleteContent(String uri, ModelCallback callback, boolean enqueue) {
        return deleteContent(uri, null, callback, enqueue);
    }

    public Call<Object> deleteContent(String uri, ModelCallback callback) {
        return deleteContent(uri, callback, true);
    }

    private Call<Object> PUT(String authHeader, String uri, Map<String, String> options,
                             VimeoCallback<Object> callback, boolean enqueue) {
        final Call<Object> call = mVimeoService.PUT(authHeader, uri, options);
        if (enqueue) {
            call.enqueue(callback);
        }
        return call;
    }

    private Call<Object> DELETE(String authHeader, String uri, Map<String, String> options,
                                VimeoCallback<Object> callback, boolean enqueue) {
        final Call<Object> call = mVimeoService.DELETE(authHeader, uri, options);
        if (enqueue) {
            call.enqueue(callback);
        }
        return call;
    }

    private Call<Object> POST(String authHeader, String uri, String cacheHeaderValue,
                              HashMap<String, String> parameters, VimeoCallback<Object> callback) {
        final Call<Object> call = mVimeoService.POST(authHeader, uri, cacheHeaderValue, parameters);
        call.enqueue(callback);
        return call;
    }

    // </editor-fold>

    // -----------------------------------------------------------------------------------------------------
    // Header values
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Header values">
    public String getUserAgent() {
        return createUserAgent();
    }

    @SuppressWarnings("WeakerAccess")
    public String getAcceptHeader() {
        return "application/vnd.vimeo.*+json; version=" + mConfiguration.mApiVersionString;
    }

    public String getAuthHeader() {
        final String credential;

        if (mVimeoAccount != null && mVimeoAccount.isAuthenticated()) {
            credential = "Bearer " + mVimeoAccount.getAccessToken();
        } else {
            credential = getBasicAuthHeader();
        }

        return credential;
    }

    private String getBasicAuthHeader() {
        return Credentials.basic(mConfiguration.mClientID, mConfiguration.mClientSecret);
    }

    @NotNull
    private String createCacheControlString(@Nullable CacheControl cacheControl) {
        if (cacheControl != null) {
            if (cacheControl.onlyIfCached()) {
                final CacheControl.Builder builder = VimeoNetworkUtil.getCacheControlBuilder(cacheControl);
                // If no max age specified on CacheControl then set it to our default [KV]
                if (cacheControl.maxAgeSeconds() == -1) {
                    builder.maxAge(mConfiguration.mCacheMaxAge, TimeUnit.SECONDS);
                }
                // CacheControl.FORCE_CACHE defaults stale to Integer.MAX so we need to overwrite it
                // so that a max age can actually do it's job [KV]
                builder.maxStale(0, TimeUnit.SECONDS);
                cacheControl = builder.build();
            }
        } else {
            cacheControl =
                    new CacheControl.Builder().maxAge(mConfiguration.mCacheMaxAge, TimeUnit.SECONDS).build();
        }
        return cacheControl.toString();
    }

    @NotNull
    static Map<String, String> createQueryMap(@Nullable String query,
                                              @Nullable Map<String, String> refinementMap,
                                              @Nullable String fieldFilter) {
        Map<String, String> queryMap = new HashMap<>();
        if (refinementMap != null && !refinementMap.isEmpty()) {
            queryMap = refinementMap;
        }
        if (query != null && !query.isEmpty()) {
            queryMap.put(Vimeo.PARAMETER_GET_QUERY, query);
        }
        if (fieldFilter != null && !fieldFilter.isEmpty()) {
            queryMap.put(Vimeo.PARAMETER_GET_FIELD_FILTER, fieldFilter);
        }
        return queryMap;
    }
    // </editor-fold>
}
