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

import com.vimeo.networking.Search.FilterType;
import com.vimeo.networking.callbacks.AuthCallback;
import com.vimeo.networking.callbacks.IgnoreResponseVimeoCallback;
import com.vimeo.networking.callbacks.VimeoCallback;
import com.vimeo.networking.callers.GetRequestCaller;
import com.vimeo.networking.interceptors.LanguageHeaderInterceptor;
import com.vimeo.networking.logging.ClientLogger;
import com.vimeo.networking.model.Comment;
import com.vimeo.networking.model.Document;
import com.vimeo.networking.model.PictureCollection;
import com.vimeo.networking.model.PictureResource;
import com.vimeo.networking.model.PinCodeInfo;
import com.vimeo.networking.model.Privacy;
import com.vimeo.networking.model.TextTrackList;
import com.vimeo.networking.model.User;
import com.vimeo.networking.model.Video;
import com.vimeo.networking.model.VimeoAccount;
import com.vimeo.networking.model.error.ErrorCode;
import com.vimeo.networking.model.error.LocalErrorCode;
import com.vimeo.networking.model.error.VimeoError;
import com.vimeo.networking.model.iap.Product;
import com.vimeo.networking.model.iap.Products;
import com.vimeo.networking.model.notifications.SubscriptionCollection;
import com.vimeo.networking.model.search.SearchResponse;
import com.vimeo.networking.model.search.SuggestionResponse;
import com.vimeo.networking.utils.BaseUrlInterceptor;
import com.vimeo.networking.utils.PrivacySettingsParams;
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
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Client class used for making networking calls to Vimeo API.
 * <p>
 * Created by alfredhanssen on 4/12/15.
 *
 * @see <a href="https://developer.vimeo.com/api">The Vimeo API Docs</a>
 */
@SuppressWarnings("unused")
public class VimeoClient {

    private static volatile boolean sContinuePinCodeAuthorizationRefreshCycle;

    @NotNull
    private Configuration mConfiguration;
    @NotNull
    private VimeoService mVimeoService;
    @Nullable
    private Cache mCache;
    @Nullable
    private String mCurrentCodeGrantState;

    @NotNull
    private Retrofit mRetrofit;

    @NotNull
    private String mUserAgent;

    @Nullable
    private Timer mPinCodeAuthorizationTimer;

    @NotNull
    private final BaseUrlInterceptor mBaseUrlInterceptor = new BaseUrlInterceptor();

    @NotNull
    private final LanguageHeaderInterceptor mLanguageHeaderInterceptor;

    /**
     * Currently authenticated account
     */
    @Nullable
    private VimeoAccount mVimeoAccount;

    public interface Caller<DataType_T> {

        @NotNull
        Call<DataType_T> call(@NotNull String authHeader,
                              @NotNull String uri,
                              @NotNull Map<String, String> queryMap,
                              @NotNull String cacheHeader,
                              @NotNull VimeoService vimeoService);
    }

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
        mLanguageHeaderInterceptor = new LanguageHeaderInterceptor(mConfiguration.mLocales);
        mConfiguration.mInterceptors.add(mBaseUrlInterceptor);
        mConfiguration.mInterceptors.add(mLanguageHeaderInterceptor);
        mCache = mConfiguration.getCache();
        final RetrofitSetup retrofitSetup = new RetrofitSetup(mConfiguration, mCache);
        mRetrofit = retrofitSetup.createRetrofit();
        mUserAgent = retrofitSetup.createUserAgent();
        mVimeoService = mRetrofit.create(VimeoService.class);
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
        // Will look like the following: https://api.vimeo.com/oauth/authorize?<UTF8 encoded params>
        final HttpUrl baseUrl = HttpUrl.parse(mConfiguration.getBaseUrl());
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

    /**
     * This method is used to create an account on Vimeo with the credentials {@code email} and
     * {@code password}. It is used to join with an email and password.
     *
     * @param displayName    The display name for the account.
     * @param email          Account's email.
     * @param password       Account's password.
     * @param marketingOptIn Flag to opt in or out of email marketing emails.
     * @param callback       Callback to inform you of the result of login.
     * @return A Call object.
     */
    @Nullable
    public Call<VimeoAccount> join(@Nullable final String displayName,
                                   @Nullable final String email,
                                   @Nullable final String password,
                                   final boolean marketingOptIn,
                                   @Nullable final AuthCallback callback) {
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
        parameters.put(Vimeo.PARAMETER_MARKETING_OPT_IN, Boolean.toString(marketingOptIn));

        final Call<VimeoAccount> call = mVimeoService.join(getBasicAuthHeader(), parameters);
        call.enqueue(new AccountCallback(this, email, callback));
        return call;
    }

    /**
     * This method is used to create an account on Vimeo with Facebook.
     *
     * @param facebookToken  Facebook token.
     * @param email          Account's email.
     * @param marketingOptIn Flag to opt in or out of email marketing emails.
     * @param callback       Callback to inform you of the result of login.
     * @return A Call object.
     */
    @Nullable
    public Call<VimeoAccount> joinWithFacebookToken(@NotNull final String facebookToken,
                                                    @NotNull final String email,
                                                    final boolean marketingOptIn,
                                                    @NotNull final AuthCallback callback) {
        if (facebookToken.isEmpty()) {
            final VimeoError error = new VimeoError("Facebook authentication error.");
            error.addInvalidParameter(Vimeo.FIELD_TOKEN, ErrorCode.UNABLE_TO_LOGIN_NO_TOKEN,
                    "An empty or null Facebook access token was provided.");
            callback.failure(error);
            return null;
        }

        final HashMap<String, String> parameters = new HashMap<>();
        parameters.put(Vimeo.PARAMETER_TOKEN, facebookToken);
        parameters.put(Vimeo.PARAMETER_SCOPE, mConfiguration.mScope);
        parameters.put(Vimeo.PARAMETER_MARKETING_OPT_IN, Boolean.toString(marketingOptIn));

        final Call<VimeoAccount> call = mVimeoService.join(getBasicAuthHeader(), parameters);
        call.enqueue(new AccountCallback(this, email, callback));
        return call;
    }

    /**
     * Register the user using a Google authentication token.
     *
     * @param googleToken    {@code id_token} value received by Google after authenticating.
     * @param email          User email address.
     * @param marketingOptIn Flag to opt in or out of marketing emails.
     * @param callback       This callback will be executed after the request succeeds or fails.
     * @return a retrofit {@link Call} object, which <b>has already been enqueued</b>.
     */
    @Nullable
    public Call<VimeoAccount> joinWithGoogleToken(@NotNull final String googleToken,
                                                  @NotNull final String email,
                                                  final boolean marketingOptIn,
                                                  @NotNull final AuthCallback callback) {
        if (googleToken.isEmpty()) {
            final VimeoError error = new VimeoError("Google authentication error.");
            error.addInvalidParameter(Vimeo.FIELD_TOKEN, ErrorCode.UNABLE_TO_LOGIN_NO_TOKEN,
                    "An empty or null Google access token was provided.");
            callback.failure(error);
            return null;
        }

        final HashMap<String, String> parameters = new HashMap<>();
        parameters.put(Vimeo.PARAMETER_ID_TOKEN, googleToken);
        parameters.put(Vimeo.PARAMETER_SCOPE, mConfiguration.mScope);
        parameters.put(Vimeo.PARAMETER_MARKETING_OPT_IN, Boolean.toString(marketingOptIn));

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
    public Call<VimeoAccount> loginWithFacebookToken(@NotNull final String facebookToken,
                                                     @NotNull final String email,
                                                     @NotNull final AuthCallback callback) {
        if (facebookToken.isEmpty()) {
            final VimeoError error = new VimeoError("Facebook authentication error.");
            error.addInvalidParameter(Vimeo.FIELD_TOKEN,
                    ErrorCode.UNABLE_TO_LOGIN_NO_TOKEN,
                    "An empty or null Facebook access token was provided.");
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
     * Allow a user to login to their account using a Google authentication token.
     *
     * @param googleToken {@code id_token} value received by Google after authenticating.
     * @param email       User email address.
     * @param callback    This callback will be executed after the request succeeds or fails.
     * @return a retrofit {@link Call} object, which <b>has already been enqueued</b>.
     */
    @Nullable
    public Call<VimeoAccount> loginWithGoogleToken(@NotNull final String googleToken,
                                                   @NotNull final String email,
                                                   @NotNull final AuthCallback callback) {
        if (googleToken.isEmpty()) {
            final VimeoError error = new VimeoError("Google authentication error.");
            error.addInvalidParameter(Vimeo.FIELD_TOKEN,
                    ErrorCode.UNABLE_TO_LOGIN_NO_TOKEN,
                    "An empty or null Google access token was provided.");
            callback.failure(error);
            return null;
        }

        final Call<VimeoAccount> call = mVimeoService.logInWithGoogle(getBasicAuthHeader(),
                Vimeo.GOOGLE_GRANT_TYPE,
                googleToken,
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

        PinCodeAccountCallback(@NotNull VimeoClient client, @NotNull AuthCallback callback, @NotNull Timer timer) {
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

        PinCodePollingTimerTask(@NotNull PinCodeInfo pinCodeInfo,
                                @NotNull Timer timer,
                                int expiresInSeconds,
                                @NotNull String scope,
                                @Nullable VimeoClient client,
                                @NotNull AuthCallback authCallback) {
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
            if (!VimeoClient.sContinuePinCodeAuthorizationRefreshCycle
                || now >= mExpiresInNano
                || authCallback == null
                || vimeoClient == null) {
                if (VimeoClient.sContinuePinCodeAuthorizationRefreshCycle) {
                    //noinspection AssignmentToStaticFieldFromInstanceMethod
                    VimeoClient.sContinuePinCodeAuthorizationRefreshCycle = false;
                    mTimer.cancel();
                    if (authCallback != null && now >= mExpiresInNano) {
                        final VimeoError error = new VimeoError("Pin code expired.");
                        error.setLocalErrorCode(LocalErrorCode.UNABLE_TO_LOGIN_PINCODE_EXPIRED);
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
     * @param pinCodeCallback {@link VimeoCallback} that will receive {@link PinCodeInfo} to display.
     *                        This is held as weak reference.
     * @param authCallback    {@link AuthCallback} that will be notified when Authorization is complete.
     *                        This is held as a weak reference. It may be called back on a different thread.
     * @return a call object for the pin code request
     */
    public Call<PinCodeInfo> logInWithPinCode(@NotNull final VimeoCallback<PinCodeInfo> pinCodeCallback,
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

        call.enqueue(new VimeoCallback<PinCodeInfo>() {
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
                        SCOPE, sSharedInstance, authCallback
                );
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
    public Call<Video> editVideo(@Nullable String uri,
                                 @Nullable String title,
                                 @Nullable String description,
                                 @Nullable String password,
                                 @Nullable PrivacySettingsParams privacySettingsParams,
                                 @Nullable HashMap<String, Object> parameters,
                                 @NotNull VimeoCallback<Video> callback) {
        if (uri == null || uri.isEmpty()) {
            callback.failure(new VimeoError("uri cannot be empty!"));

            return null;
        }
        if (title == null && description == null && (privacySettingsParams == null || privacySettingsParams.getParams().isEmpty())) {
            // The fields above can be null individually, but if they're all null there is no point
            // in making the request 1/26/16 [KV]
            callback.failure(new VimeoError("title, description, and privacy settings cannot be empty!"));

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

        if (privacySettingsParams != null && !privacySettingsParams.getParams().isEmpty()) {
            final Map<String, Object> privacyMap = privacySettingsParams.getParams();
            final Privacy.ViewValue viewPrivacyValue = (Privacy.ViewValue) privacyMap.get(Vimeo.PARAMETER_VIDEO_VIEW);

            if (viewPrivacyValue == Privacy.ViewValue.PASSWORD) {
                if (password == null) {
                    callback.failure(new VimeoError("Password cannot be null password privacy type"));
                    return null;
                }
                parameters.put(Vimeo.PARAMETER_VIDEO_PASSWORD, password);
            }
            parameters.put(Vimeo.PARAMETER_VIDEO_PRIVACY, privacyMap);
        }

        final Call<Video> call = mVimeoService.editVideo(getAuthHeader(), uri, parameters);
        call.enqueue(callback);

        return call;
    }

    @Nullable
    public Call<User> editUser(String uri,
                               @Nullable String name,
                               @Nullable String location,
                               @Nullable String bio,
                               VimeoCallback<User> callback) {
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

        final Call<User> call = mVimeoService.editUser(getAuthHeader(), uri, parameters);
        call.enqueue(callback);
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
                                                          @NotNull VimeoCallback<SubscriptionCollection> callback) {

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
     * @param callback the {@link VimeoCallback} to be invoked when the request finishes
     * @return a {@link Call} with the {@link Document} type. This can be used for request
     * cancellation.
     */
    @NotNull
    public Call<Document> getTermsOfService(@NotNull VimeoCallback<Document> callback) {
        return getDocument(Vimeo.ENDPOINT_TERMS_OF_SERVICE, callback);
    }

    /**
     * An asynchronous request to get a {@link Document} representing the Vimeo Privacy Policy
     *
     * @param callback the {@link VimeoCallback} to be invoked when the request finishes
     * @return a {@link Call} with the {@link Document} type. This can be used for request
     * cancellation.
     */
    @NotNull
    public Call<Document> getPrivacyPolicy(@NotNull VimeoCallback<Document> callback) {
        return getDocument(Vimeo.ENDPOINT_PRIVACY_POLICY, callback);
    }

    /**
     * An asynchronous request to get a {@link Document} representing the Vimeo Payment Addendum
     *
     * @param callback the {@link VimeoCallback} to be invoked when the request finishes
     * @return a {@link Call} with the {@link Document} type. This can be used for request
     * cancellation.
     */
    @NotNull
    public Call<Document> getPaymentAddendum(@NotNull VimeoCallback<Document> callback) {
        return getDocument(Vimeo.ENDPOINT_PAYMENT_ADDENDUM, callback);
    }

    /**
     * Gets a {@link Document} at the provided uri. When finished, the callback will be invoked.
     *
     * @param uri      the uri of the Document
     * @param callback the {@link VimeoCallback} to be invoked when the request finishes
     * @return a {@link Call} with the {@link Document} type. This can be used for request
     * cancellation.
     */
    @NotNull
    public Call<Document> getDocument(@NotNull String uri, @NotNull VimeoCallback<Document> callback) {
        final Call<Document> call = mVimeoService.getDocument(getAuthHeader(), uri);
        call.enqueue(callback);

        return call;
    }

    /**
     * Gets a {@link TextTrackList} at the provided uri. When finished, the callback will be invoked.
     *
     * @param uri      the uri of the text track.
     * @param callback the {@link VimeoCallback} to be invoked when the request finishes
     * @return a {@link Call} with the {@link TextTrackList} type. This can be used for request
     * cancellation.
     */
    @NotNull
    public Call<TextTrackList> getTextTrackList(@NotNull String uri,
                                                @NotNull VimeoCallback<TextTrackList> callback) {
        Call<TextTrackList> call = mVimeoService.getTextTrackList(getAuthHeader(), uri);
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
     * @param callback The VimeoCallback containing PictureResource data
     */
    @Nullable
    public Call<PictureResource> createPictureResource(String uri, VimeoCallback<PictureResource> callback) {
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
     *            {@link #createPictureResource(String, VimeoCallback)}
     */
    @Nullable
    public Call<PictureCollection> activatePictureResource(String uri, VimeoCallback<PictureCollection> callback) {
        if (uri == null || uri.trim().isEmpty()) {
            callback.failure(new VimeoError("uri cannot be empty!"));
            return null;
        }
        final HashMap<String, Object> parameters = new HashMap<>();
        parameters.put(Vimeo.PARAMETER_ACTIVE, true);
        final Call<PictureCollection> call = mVimeoService.editPictureCollection(getAuthHeader(), uri, parameters);
        call.enqueue(callback);
        return call;
    }
    // </editor-fold>

    // -----------------------------------------------------------------------------------------------------
    // Video actions (Like, Watch Later, Commenting)
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Video actions (Like, Watch Later, Commenting)">
    public Call updateFollow(boolean follow, String uri, IgnoreResponseVimeoCallback callback) {
        if (follow) {
            return follow(uri, callback);
        } else {
            return unfollow(uri, callback);
        }
    }

    private Call follow(String uri, IgnoreResponseVimeoCallback callback) {
        return putContent(uri, null, callback);
    }

    private Call unfollow(@Nullable String uri, @NotNull IgnoreResponseVimeoCallback callback) {
        return deleteContent(uri, null, callback);
    }

    public Call updateLikeVideo(boolean like,
                                @Nullable String uri,
                                @Nullable String password,
                                @NotNull IgnoreResponseVimeoCallback callback) {
        if (like) {
            return likeVideo(uri, password, callback);
        } else {
            return unlikeVideo(uri, password, callback);
        }
    }

    private Call likeVideo(@Nullable String uri,
                           @Nullable String password,
                           @NotNull IgnoreResponseVimeoCallback callback) {
        final Map<String, String> options = new HashMap<>();
        if (password != null) {
            options.put(Vimeo.PARAMETER_PASSWORD, password);
        }
        return putContent(uri, options, callback);
    }

    private Call unlikeVideo(@Nullable String uri,
                             @Nullable String password,
                             @NotNull IgnoreResponseVimeoCallback callback) {
        final Map<String, String> options = new HashMap<>();
        if (password != null) {
            options.put(Vimeo.PARAMETER_PASSWORD, password);
        }
        return deleteContent(uri, options, callback);
    }

    public Call updateWatchLaterVideo(boolean watchLater,
                                      @Nullable String uri,
                                      @Nullable String password,
                                      @NotNull IgnoreResponseVimeoCallback callback) {
        if (watchLater) {
            return watchLaterVideo(uri, password, callback);
        } else {
            return unwatchLaterVideo(uri, password, callback);
        }
    }

    private Call watchLaterVideo(@Nullable String uri,
                                 @Nullable String password,
                                 @NotNull IgnoreResponseVimeoCallback callback) {
        final Map<String, String> options = new HashMap<>();
        if (password != null) {
            options.put(Vimeo.PARAMETER_PASSWORD, password);
        }
        return putContent(uri, options, callback);
    }

    private Call unwatchLaterVideo(@Nullable String uri,
                                   @Nullable String password,
                                   @NotNull IgnoreResponseVimeoCallback callback) {
        final Map<String, String> options = new HashMap<>();
        if (password != null) {
            options.put(Vimeo.PARAMETER_PASSWORD, password);
        }
        return deleteContent(uri, options, callback);
    }

    @Nullable
    public Call<Comment> comment(String uri,
                                 String comment,
                                 @Nullable String password,
                                 VimeoCallback<Comment> callback) {
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
     * {@link #getVideoSync(String, CacheControl, String)}
     *
     * @param uri         the uri of the video to fetch
     * The <a href="https://developer.vimeo.com/api/common-formats#json-filter">JSON Filter</a> to optimize 
     *                      the request query. May be null.
     * @return a Retrofit response object with the Video as the body
     */
    @Nullable
    public retrofit2.Response<Video> getVideoSync(String uri, @Nullable String fieldFilter) {
        return getVideoSync(uri, CacheControl.FORCE_NETWORK, fieldFilter);
    }

    /**
     * This will fetch a video synchronously
     *
     * @param uri          the uri of the video to fetch
     * @param cacheControl the cache control
     * @param fieldFilter   The <a href="https://developer.vimeo.com/api/common-formats#json-filter">JSON Filter</a> to optimize 
     *                      the request query. May be null.
     * @return a Retrofit response object with the Video as the body
     */
    @Nullable
    public retrofit2.Response<Video> getVideoSync(String uri,
                                                  CacheControl cacheControl,
                                                  @Nullable String fieldFilter) {
        final String cacheHeaderValue = createCacheControlString(cacheControl);
        try {
            final Map<String, String> queryMap = createQueryMap(null, null, fieldFilter);
            return mVimeoService.getVideo(getAuthHeader(), uri, queryMap, cacheHeaderValue).execute();
        } catch (final IOException e) {
            return null;
        }
    }
    // </editor-fold>

    // -----------------------------------------------------------------------------------------------------
    // Gets, posts, puts, deletes
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Gets, posts, puts, deletes">

    /**
     * A package private search method: use
     * {@link Search#search(String, FilterType, String, Map, List, String, VimeoCallback)}
     * which relies on this method.
     *
     * @param queryMap the query parameters
     * @param callback the callback to be invoked when the call finishes
     * @return the Call object provided by the Retrofit service
     */
    @NotNull
    Call<SearchResponse> search(@NotNull Map<String, String> queryMap,
                                @NotNull VimeoCallback<SearchResponse> callback) {

        final Call<SearchResponse> call = mVimeoService.search(getAuthHeader(), queryMap);
        call.enqueue(callback);
        return call;
    }

    /**
     * A package private search suggestions method. See {@link Search#suggest(String, int, int, VimeoCallback)} for
     * public usage of the suggestion API.
     *
     * @param queryMap the query parameters
     * @param callback the callback to be invoked when the call finishes
     * @return the {@link Call} object provided by Retrofit
     */
    @NotNull
    Call<SuggestionResponse> suggest(@NotNull Map<String, String> queryMap,
                                     @NotNull VimeoCallback<SuggestionResponse> callback) {
        final Call<SuggestionResponse> call = mVimeoService.suggest(getAuthHeader(), queryMap);
        call.enqueue(callback);

        return call;
    }

    /**
     * Gets a list of {@link Products} that can be purchased, such as Vimeo subscriptions.
     *
     * @param callback the {@link VimeoCallback} to be invoked when the request finishes
     * @return a {@link Call} with the {@link Products}. This can be used for request cancellation.
     */
    @NotNull
    public Call<Products> getProducts(@NotNull VimeoCallback<Products> callback) {
        Call<Products> call = mVimeoService.getProducts(getAuthHeader());
        call.enqueue(callback);
        return call;
    }

    /**
     * Get a single {@link Product} item from a given product URI.
     *
     * @param uri      URI for a product. Can be obtained from {@link Product#getUri()}.
     * @param callback Callback that will be executed once a {@link Product} has been loaded.
     * @return {@link Call} that can be used to cancel network requests.
     */
    public Call<Product> getProduct(String uri, VimeoCallback<Product> callback) {
        return getContent(uri, CacheControl.FORCE_NETWORK, GetRequestCaller.PRODUCT, null, null, null, callback);
    }

    /**
     * Fetches the currently authenticated user from the API
     *
     * @param callback the callback to be invoked when the request finishes
     */
    public void getCurrentUser(@NotNull VimeoCallback<User> callback) {
        getCurrentUser(null, callback);
    }

    /**
     * Fetches the currently authenticated user from the API
     *
     * @param filter   the field filter to apply to the request
     * @param callback the callback to be invoked when the request finishes
     */
    @SuppressWarnings("WeakerAccess")
    public void getCurrentUser(@Nullable String filter, @NotNull VimeoCallback<User> callback) {
        getContent(Vimeo.ENDPOINT_ME,
                CacheControl.FORCE_NETWORK,
                GetRequestCaller.USER,
                null,
                null,
                filter,
                callback);
    }

    /**
     * A generic GET call that takes in the URI of the specific resource.
     *
     * @param uri           URI of the resource to GET
     * @param cacheControl  Cache control type (includes max age and other cache policy information)
     * @param caller        The {@link GetRequestCaller} for the expected response type
     * @param query         Query string for hitting the search endpoint
     * @param refinementMap Used to refine lists (generally for search) with sorts and filters
     *                      {@link RequestRefinementBuilder}
     * @param fieldFilter   The <a href="https://developer.vimeo.com/api/common-formats#json-filter">JSON Filter</a> to optimize 
     *                      the request query. May be null. (highly recommended!)
     * @param callback      The callback for the specific model type of the resource
     * @see <a href="https://developer.vimeo.com/api/common-formats#json-filter">Vimeo API Field Filter Docs</a>
     */
    @Nullable
    public <DataType_T> Call<DataType_T> getContent(@NotNull String uri,
                                                    @NotNull CacheControl cacheControl,
                                                    @NotNull Caller<DataType_T> caller,
                                                    @Nullable String query,
                                                    @Nullable Map<String, String> refinementMap,
                                                    @Nullable String fieldFilter,
                                                    @NotNull VimeoCallback<DataType_T> callback) {
        if (uri.isEmpty()) {
            callback.failure(new VimeoError("Uri cannot be empty!"));
            return null;
        }

        final String cacheHeaderValue = createCacheControlString(cacheControl);
        final Map<String, String> queryMap = createQueryMap(query, refinementMap, fieldFilter);
        final Call<DataType_T> call = caller.call(getAuthHeader(),
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
     *                      {@link RequestRefinementBuilder}
     * @param fieldFilter   The <a href="https://developer.vimeo.com/api/common-formats#json-filter">JSON Filter</a> to optimize 
     *                      the request query. May be null. (highly recommended!)
     * @param caller        The {@link GetRequestCaller} for the expected response type
     * @see <a href="https://developer.vimeo.com/api/spec#common-parameters">Vimeo API Field Filter Docs</a>
     */
    @Nullable
    public <DataType_T> retrofit2.Response<DataType_T> getContentSync(
            @NotNull String uri,
            @NotNull CacheControl cacheControl,
            @Nullable String query,
            @Nullable Map<String, String> refinementMap,
            @Nullable String fieldFilter,
            @NotNull Caller<DataType_T> caller) {

        if (uri.isEmpty()) {
            throw new AssertionError("uri cannot be null or empty");
        }

        final String cacheHeaderValue = createCacheControlString(cacheControl);
        final Map<String, String> queryMap = createQueryMap(query, refinementMap, fieldFilter);
        final Call<DataType_T> call = caller.call(getAuthHeader(),
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

    /**
     * A generic POST call that takes in the URI of the specific resource.
     *
     * @param uri          URI of the resource to POST
     * @param cacheControl Cache control type
     * @param postBody     The body of the POST request
     * @param callback     The callback for the specific model type of the resource
     */
    public Call postContent(@NotNull String uri,
                            @Nullable CacheControl cacheControl,
                            @Nullable ArrayList<Object> postBody,
                            @NotNull IgnoreResponseVimeoCallback callback) {

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
    public Call<Void> emptyResponsePost(@Nullable String uri,
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
     * A synchronous version of {@link #emptyResponsePost(String, HashMap, VimeoCallback)}
     *
     * @return A {@link VimeoError} if there has been a network error or null if the call has been successful.
     * @see #emptyResponsePost
     */
    @Nullable
    public VimeoError emptyResponsePostSync(@Nullable String uri, @Nullable HashMap<String, String> postBody) {

        VimeoError vimeoError = null;
        if (uri == null) {
            return new VimeoError("uri cannot be empty!");
        }

        if (postBody == null) {
            postBody = new HashMap<>();
        }

        final Call<Void> call = mVimeoService.emptyResponsePost(getAuthHeader(), uri, postBody);
        try {
            final Response<Void> response = call.execute();
            if (!isSuccessfulResponse(response)) {
                vimeoError = VimeoNetworkUtil.getErrorFromResponse(response);
                if (vimeoError == null) {
                    vimeoError = new VimeoError();
                }
            }
        } catch (final Exception e) {
            vimeoError = new VimeoError();
            vimeoError.setException(e);
        }

        return vimeoError;
    }

    /**
     * Determines if a network response has been successful.
     *
     * @param response A network response
     * @return true if the response is successful and false otherwise
     */
    private boolean isSuccessfulResponse(@Nullable retrofit2.Response response) {
        return response != null && response.isSuccessful();
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

    /**
     * Certain endpoints will return the current user in the response when you perform a PUT on them. Use this method to
     * interact with them.
     *
     * @param uri          the URI to connect to.
     * @param cacheControl allows the consumer to force a request from cache or network.
     * @param options      the options that can be sent with the request.
     * @param body         The body of the PUT request
     * @param callback     the callback that will be invoked.
     * @return a call with the request, or null if the parameters passed are invalid.
     */
    @Nullable
    public Call<User> putContentWithUserResponse(@Nullable final String uri,
                                                 @Nullable final CacheControl cacheControl,
                                                 @Nullable final Map<String, String> options,
                                                 @Nullable final Object body,
                                                 @NotNull final VimeoCallback<User> callback) {
        if (uri == null || uri.isEmpty()) {
            callback.failure(new VimeoError("uri cannot be empty!"));
            return null;
        }

        final String cacheHeaderValue = cacheControl != null ? cacheControl.toString() : null;

        final Map<String, String> optionsMap = options == null ? new HashMap<String, String>() : options;

        final Call<User> call = body != null
                ? mVimeoService.putContentWithUserResponse(getAuthHeader(), uri, cacheHeaderValue, optionsMap, body)
                : mVimeoService.putContentWithUserResponse(getAuthHeader(), uri, optionsMap);
        call.enqueue(callback);
        return call;
    }

    /**
     * A generic PUT call that takes in the URI of the specific resource.
     *
     * @param uri          URI of the resource to PUT
     * @param cacheControl Cache control type
     * @param body         The body of the PUT request
     * @param callback     The callback for the specific model type of the resource
     */
    @Nullable
    public Call putContent(@Nullable final String uri,
                           @Nullable final CacheControl cacheControl,
                           @Nullable final Map<String, String> options,
                           @Nullable final Object body,
                           @NotNull final IgnoreResponseVimeoCallback callback) {

        if (uri == null || uri.isEmpty()) {
            callback.failure(new VimeoError("uri cannot be empty!"));
            return null;
        }

        final String cacheHeaderValue = cacheControl != null ? cacheControl.toString() : null;

        final Map<String, String> optionsMap = options == null ? new HashMap<String, String>() : options;

        final Call<Object> call;
        if (body != null) {
            call = mVimeoService.PUT(getAuthHeader(), uri, cacheHeaderValue, optionsMap, body);
        } else {
            call = mVimeoService.PUT(getAuthHeader(), uri, optionsMap);
        }
        call.enqueue(callback);
        return call;
    }

    @Nullable
    public Call putContent(@Nullable String uri,
                           @Nullable Map<String, String> options,
                           @NotNull IgnoreResponseVimeoCallback callback) {
        return putContent(uri, null, options, null, callback);
    }

    @Nullable
    public Call deleteContent(@Nullable String uri,
                              @Nullable Map<String, String> options,
                              @NotNull IgnoreResponseVimeoCallback callback) {
        if (uri == null || uri.isEmpty()) {
            callback.failure(new VimeoError("uri cannot be empty!"));
            return null;
        }
        if (options == null) {
            options = new HashMap<>();
        }
        final Call<Object> call = mVimeoService.DELETE(getAuthHeader(), uri, options);
        call.enqueue(callback);
        return call;
    }

    // </editor-fold>

    // -----------------------------------------------------------------------------------------------------
    // Header values
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Header values">
    @NotNull
    public String getUserAgent() {
        return mUserAgent;
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

    @SuppressWarnings("WeakerAccess")
    public String getBasicAuthHeader() {
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

    @SuppressWarnings("WeakerAccess")
    @NotNull
    public static Map<String, String> createQueryMap(@Nullable String query,
                                                     @Nullable Map<String, String> refinementMap,
                                                     @Nullable String fieldFilter) {
        final Map<String, String> queryMap;
        if (refinementMap != null && !refinementMap.isEmpty()) {
            queryMap = new HashMap<>(refinementMap);
        } else {
            queryMap = new HashMap<>();
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
