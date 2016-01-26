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

import com.vimeo.networking.callbacks.AuthCallback;
import com.vimeo.networking.callbacks.ModelCallback;
import com.vimeo.networking.callbacks.VimeoCallback;
import com.vimeo.networking.logging.LoggingInterceptor;
import com.vimeo.networking.model.Account;
import com.vimeo.networking.model.Comment;
import com.vimeo.networking.model.PictureResource;
import com.vimeo.networking.model.Privacy;
import com.vimeo.networking.model.User;
import com.vimeo.networking.model.error.ErrorCode;
import com.vimeo.networking.model.error.VimeoError;
import com.vimeo.networking.utils.VimeoNetworkUtil;

import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Client class used for making networking calls to Vimeo API.
 * <p/>
 * Created by alfredhanssen on 4/12/15.
 */
public class VimeoClient {

    private Configuration configuration;
    private VimeoService vimeoService;
    private Cache cache;
    private String currentCodeGrantState;
    private Retrofit retrofit;

    /**
     * Currently authenticated account
     */
    private Account account;

    /**
     * -----------------------------------------------------------------------------------------------------
     * Configuration
     * -----------------------------------------------------------------------------------------------------
     */
    // <editor-fold desc="Configuration">

    /**
     * Dangerous interceptor that rewrites the server's cache-control header.
     * We are using this because our server sets all Cache-Control headers to no-store
     * [AH] 4/24/2015
     */
    private static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Response originalResponse = chain.proceed(chain.request());

            return originalResponse.newBuilder().header(Vimeo.HEADER_CACHE_CONTROL, "public").build();
        }
    };

    private static VimeoClient sharedInstance;

    public static VimeoClient getInstance() {
        if (sharedInstance == null) {
            throw new AssertionError("Instance must be configured before use");
        }

        return sharedInstance;
    }

    public static void configure(Configuration configuration) {
        sharedInstance = new VimeoClient(configuration);
    }

    private VimeoClient(Configuration configuration) {
        if (configuration == null) {
            throw new AssertionError("Configuration cannot be null");
        }

        this.configuration = configuration;

        this.cache = new Cache(this.configuration.cacheDirectory, this.configuration.cacheSize);

        this.retrofit = createRetrofit();

        this.vimeoService = retrofit.create(VimeoService.class);

        Account account = this.configuration.accountStore.loadAccount();
        this.setAccount(account);
    }

    private Retrofit createRetrofit() {
        return new Retrofit.Builder().baseUrl(configuration.baseURLString)
                .client(createOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(VimeoNetworkUtil.getGson()))
                .build();
    }

    private OkHttpClient createOkHttpClient() {
        RetrofitClientBuilder retrofitClientBuilder = new RetrofitClientBuilder();
        retrofitClientBuilder.setCache(cache);
        retrofitClientBuilder.addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR);
        if (configuration.certPinningEnabled) {
            try {
                retrofitClientBuilder.pinCertificates();
            } catch (Exception e) {
                this.configuration.debugLogger.e("Exception when pinning certificate: " + e.getMessage(), e);
            }
        }

        boolean shouldLog = false;

        retrofitClientBuilder.setReadTimeout(this.configuration.timeout, TimeUnit.SECONDS)
                .setConnectionTimeout(this.configuration.timeout, TimeUnit.SECONDS);
        if (shouldLog) {
            retrofitClientBuilder.addInterceptor(new LoggingInterceptor()).build();
        }
        retrofitClientBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                // Customize the request
                Request request = original.newBuilder()
                        .header(Vimeo.HEADER_USER_AGENT, configuration.userAgentString)
                        .header(Vimeo.HEADER_ACCEPT, getAcceptHeader())
                        .method(original.method(), original.body())
                        .build();

                // Customize or return the response
                return chain.proceed(request);
            }
        });

        return retrofitClientBuilder.build();
    }

    public void clearRequestCache() {
        try {
            this.cache.evictAll();
        } catch (IOException e) {
            configuration.debugLogger.e("Cache clearing error: " + e.getMessage(), e);
        }
    }

    public Retrofit getRetrofit() {
        return this.retrofit;
    }

    /**
     * Return a builder of the given cache control because for some reason this doesn't exist already.
     * Useful for adding more attributes to an already defined {@link CacheControl}
     *
     * @param cacheControl The CacheControl to convert to a builder
     * @return A builder with the same attributes as the CacheControl passed in
     */

    public CacheControl.Builder getCacheControlBuilder(CacheControl cacheControl) {
        CacheControl.Builder builder = new CacheControl.Builder();
        if (cacheControl.maxAgeSeconds() > -1) {
            builder.maxAge(cacheControl.maxAgeSeconds(), TimeUnit.SECONDS);
        }
        if (cacheControl.maxStaleSeconds() > -1) {
            builder.maxStale(cacheControl.maxStaleSeconds(), TimeUnit.SECONDS);
        }
        if (cacheControl.minFreshSeconds() > -1) {
            builder.minFresh(cacheControl.minFreshSeconds(), TimeUnit.SECONDS);
        }

        if (cacheControl.noCache()) {
            builder.noCache();
        }
        if (cacheControl.noStore()) {
            builder.noStore();
        }
        if (cacheControl.noTransform()) {
            builder.noTransform();
        }
        if (cacheControl.onlyIfCached()) {
            builder.onlyIfCached();
        }
        return builder;
    }


    public Account getAccount() {
        if (this.account == null) {
            throw new AssertionError("Account should never be null");
        }

        return this.account;
    }

    public void setAccount(Account account) {
        if (account == null) {
            account = new Account();
        }

        this.account = account;
    }

    public Configuration getConfiguration() {
        return this.configuration;
    }
    // </editor-fold>

    /**
     * -----------------------------------------------------------------------------------------------------
     * Authentication
     * -----------------------------------------------------------------------------------------------------
     */
    // <editor-fold desc="Authentication">

    /**
     * Provides a URI that can be opened in a web view that will prompt for login and permissions
     * or used currently logged in users credentials.
     * <p/>
     * If the user accepts your app, they are redirected to your redirect_uri along with two parameters:
     * {@link Vimeo#CODE_GRANT_RESPONSE_TYPE} or {@link Vimeo#CODE_GRANT_STATE}
     *
     * @return The URI that should be opened in a web view
     * @see <a href="https://developer.vimeo.com/api/authentication#generate-redirect">Vimeo API Docs</a>
     */
    public String getCodeGrantAuthorizationURI() {
        currentCodeGrantState = UUID.randomUUID().toString();

        // TODO: TEST
        // Will look like the following: https://api.vimeo.com/oauth/authorize?<UTF8 encoded params>
        HttpUrl baseUrl = HttpUrl.parse(this.configuration.baseURLString);
        HttpUrl uri = new HttpUrl.Builder().scheme(baseUrl.scheme())
                .host(baseUrl.host())
                .encodedPath(Vimeo.CODE_GRANT_PATH)
                .addQueryParameter(Vimeo.PARAMETER_REDIRECT_URI, this.configuration.codeGrantRedirectURI)
                .addQueryParameter(Vimeo.PARAMETER_RESPONSE_TYPE, Vimeo.CODE_GRANT_RESPONSE_TYPE)
                .addQueryParameter(Vimeo.PARAMETER_STATE, this.currentCodeGrantState)
                .addQueryParameter(Vimeo.PARAMETER_SCOPE, this.configuration.scope)
                .addQueryParameter(Vimeo.PARAMETER_CLIENT_ID, this.configuration.clientID)
                .build();
        return uri.toString();
    }


    /**
     * Authenticates the user from the {@link #getCodeGrantAuthorizationURI()}.
     * <p/>
     * Exchanges the code for the access token.
     *
     * @param uri      URI from {@link #getCodeGrantAuthorizationURI()}
     * @param callback Callback pertaining to authentication
     * @see <a href="https://developer.vimeo.com/api/authentication#generate-redirect">Vimeo API Docs</a>
     */
    @Nullable
    public Call<Account> authenticateWithCodeGrant(String uri, AuthCallback callback) {
        if (callback == null) {
            throw new AssertionError("Callback cannot be null");
        }

        if (uri == null || uri.isEmpty()) {
            callback.failure(new VimeoError("uri must not be null"));

            return null;
        }

        // TODO: TEST
        Map<String, String> queryMap = VimeoNetworkUtil.getSimpleQueryMap(uri);
        String code = queryMap.get(Vimeo.CODE_GRANT_RESPONSE_TYPE);
        String state = queryMap.get(Vimeo.CODE_GRANT_STATE);

        if (code == null || code.isEmpty() || state == null || state.isEmpty() ||
            !state.equals(this.currentCodeGrantState)) {
            this.currentCodeGrantState = null;

            callback.failure(new VimeoError("Code grant code is null or state has changed"));

            return null;
        }

        this.currentCodeGrantState = null;

        String redirectURI = this.configuration.codeGrantRedirectURI;

        Call<Account> call =
                this.vimeoService.authenticateWithCodeGrant(getBasicAuthHeader(), redirectURI, code,
                                                            Vimeo.CODE_GRANT_TYPE);
        call.enqueue(new AccountCallback(this, callback));
        return call;
    }

    /**
     * Authorizes users of the app who are not signed in.
     * <p/>
     * Leaves User as null in {@link Account} model and populates the rest
     * <p/>
     *
     * @param callback Callback pertaining to authentication
     */
    public Call<Account> authorizeWithClientCredentialsGrant(AuthCallback callback) {
        if (callback == null) {
            throw new AssertionError("Callback cannot be null");
        }

        Call<Account> call = this.vimeoService.authorizeWithClientCredentialsGrant(getBasicAuthHeader(),
                                                                                   Vimeo.CLIENT_CREDENTIALS_GRANT_TYPE,
                                                                                   configuration.scope);
        call.enqueue(new AccountCallback(this, callback));
        return call;
    }

    /**
     * Exchange OAuth1 token/secret combination for a new OAuth2 token
     *
     * @param callback    Callback pertaining to authentication
     * @param token       An OAuth1 token
     * @param tokenSecret An OAuth1 token secret
     * @return The Account
     */
    public Call<Account> exchangeOAuthOneToken(String token, String tokenSecret, AuthCallback callback) {
        if (callback == null) {
            throw new AssertionError("Callback cannot be null");
        }

        Call<Account> call =
                this.vimeoService.exchangeOAuthOneToken(getBasicAuthHeader(), Vimeo.OAUTH_ONE_GRANT_TYPE,
                                                        token, tokenSecret, configuration.scope);
        call.enqueue(new AccountCallback(this, callback));
        return call;
    }


    @Nullable
    public Call<Account> join(String displayName, String email, String password, AuthCallback callback) {
        if (callback == null) {
            throw new AssertionError("Callback cannot be null");
        }

        if (displayName == null || displayName.isEmpty() || email == null || email.isEmpty() ||
            password == null || password.isEmpty()) {

            VimeoError error = new VimeoError("Name, email, and password must be set.");

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

        HashMap<String, String> parameters = new HashMap<>();
        parameters.put(Vimeo.PARAMETER_USERS_NAME, displayName);
        parameters.put(Vimeo.PARAMETER_EMAIL, email);
        parameters.put(Vimeo.PARAMETER_PASSWORD, password);
        parameters.put(Vimeo.PARAMETER_SCOPE, configuration.scope);

        Call<Account> call = this.vimeoService.join(getBasicAuthHeader(), parameters);
        call.enqueue(new AccountCallback(this, email, password, callback));
        return call;
    }

    @Nullable
    public Call<Account> joinWithFacebookToken(String facebookToken, String email, AuthCallback callback) {
        if (callback == null) {
            throw new AssertionError("Callback cannot be null");
        }

        if (facebookToken == null || facebookToken.isEmpty()) {
            VimeoError error = new VimeoError("Facebook authentication error.");

            if (facebookToken == null || facebookToken.isEmpty()) {
                error.addInvalidParameter(Vimeo.FIELD_TOKEN, ErrorCode.UNABLE_TO_LOGIN_NO_TOKEN,
                                          "An empty or null Facebook access token was provided.");
            }
            callback.failure(error);
            return null;
        }

        HashMap<String, String> parameters = new HashMap<>();
        parameters.put(Vimeo.PARAMETER_TOKEN, facebookToken);
        parameters.put(Vimeo.PARAMETER_SCOPE, configuration.scope);

        Call<Account> call = this.vimeoService.join(getBasicAuthHeader(), parameters);
        call.enqueue(new AccountCallback(this, email, callback));
        return call;
    }

    @Nullable
    public Call<Account> logIn(String email, String password, AuthCallback callback) {
        if (callback == null) {
            throw new AssertionError("Callback cannot be null");
        }

        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            VimeoError error = new VimeoError("Email and password must be set.");

            if (email == null || email.isEmpty()) {
                error.addInvalidParameter(Vimeo.FIELD_USERNAME, ErrorCode.INVALID_INPUT_NO_EMAIL,
                                          "An empty or null email was provided.");
            }
            if (password == null || password.isEmpty()) {
                error.addInvalidParameter(Vimeo.FIELD_PASSWORD, ErrorCode.INVALID_INPUT_NO_PASSWORD,
                                          "An empty or null password was provided.");
            }
            callback.failure(error);

            return null;
        }

        Call<Account> call =
                this.vimeoService.logIn(getBasicAuthHeader(), email, password, Vimeo.PASSWORD_GRANT_TYPE,
                                        configuration.scope);
        call.enqueue(new AccountCallback(this, email, password, callback));
        return call;
    }

    /**
     * Synchronous version of login call
     * <p/>
     * Useful when dealing with Android AccountAuthenticator [AH]
     *
     * @param email    user's email address
     * @param password user's password
     * @return the account object since it is synchronous
     */
    public Account logIn(String email, String password) {
        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            return null;
        }

        Call<Account> call =
                this.vimeoService.logIn(getBasicAuthHeader(), email, password, Vimeo.PASSWORD_GRANT_TYPE,
                                        configuration.scope);

        Account account = null;
        try {
            retrofit2.Response<Account> response = call.execute();
            if (response.isSuccess()) {
                account = response.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.setAccount(account);

        this.configuration.accountStore.saveAccount(account, email, password);

        return account;
    }

    @Nullable
    public Call<Account> loginWithFacebookToken(String facebookToken, String email, AuthCallback callback) {
        if (callback == null) {
            throw new AssertionError("Callback cannot be null");
        }

        if (facebookToken == null || facebookToken.isEmpty()) {
            VimeoError error = new VimeoError("Facebook authentication error.");

            if (facebookToken == null || facebookToken.isEmpty()) {
                error.addInvalidParameter(Vimeo.FIELD_TOKEN, ErrorCode.UNABLE_TO_LOGIN_NO_TOKEN,
                                          "An empty or null Facebook access token was provided.");
            }
            callback.failure(error);
            return null;
        }

        Call<Account> call =
                this.vimeoService.logInWithFacebook(getBasicAuthHeader(), Vimeo.FACEBOOK_GRANT_TYPE,
                                                    facebookToken, configuration.scope);
        call.enqueue(new AccountCallback(this, email, callback));
        return call;
    }


    /**
     * Must be called when user logs out to ensure that the tokens have been invalidated
     * <p/>
     *
     * @param callback Callback for handling logout
     */
    public Call<Object> logOut(@Nullable final VimeoCallback<Object> callback) {

        Call<Object> call = this.vimeoService.logOut(getAuthHeader());
        if (callback != null) {
            callback.setCall(call);
        }
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
        this.configuration.accountStore.deleteAccount(account);
        this.setAccount(null);
        return call;
    }

    /**
     * Class responsible for setting the account on successful authorization.
     * <p/>
     * Sets the account on the {@link VimeoClient} as well as the {@link AccountStore}
     */
    private static class AccountCallback extends VimeoCallback<Account> {

        private final VimeoClient client;
        private String email;
        private String password;
        private final AuthCallback callback;

        public AccountCallback(VimeoClient client, AuthCallback callback) {
            if (client == null || callback == null) {
                throw new AssertionError("Client and Callback must not be null");
            }

            this.client = client;
            this.callback = callback;
        }

        public AccountCallback(VimeoClient client, String email, AuthCallback callback) {
            if (client == null || callback == null) {
                throw new AssertionError("Client and Callback must not be null");
            }

            this.client = client;
            this.email = email;
            this.callback = callback;
        }

        public AccountCallback(VimeoClient client, String email, String password, AuthCallback callback) {
            if (client == null || callback == null) {
                throw new AssertionError("Client and Callback must not be null");
            }

            this.client = client;
            this.email = email;
            this.password = password;
            this.callback = callback;
        }

        @Override
        public void success(Account account) {
            this.client.setAccount(account);
            if (account.getUser() != null && (this.email == null || this.email.isEmpty())) {
                // We must always have a `name` field, which is used by the Android Account Manager for
                // display in the device Settings -> Accounts [KZ] 12/17/15
                String name =
                        (account.getUser().name != null) ? account.getUser().name : account.getUser().uri;
                this.client.configuration.accountStore.saveAccount(account, name, null);
            } else {
                this.client.configuration.accountStore.saveAccount(account, this.email, this.password);
            }
            this.callback.success();
        }

        @Override
        public void failure(VimeoError error) {
            callback.failure(error);
        }
    }
    // </editor-fold>

    /**
     * -----------------------------------------------------------------------------------------------------
     * Editing (Video, User)
     * -----------------------------------------------------------------------------------------------------
     */
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

            String privacyString = privacyValue.getText();
            HashMap<String, String> privacyMap = new HashMap<>();
            privacyMap.put(Vimeo.PARAMETER_VIDEO_VIEW, privacyString);
            parameters.put(Vimeo.PARAMETER_VIDEO_PRIVACY, privacyMap);
        }

        Call<Object> call = this.vimeoService.edit(getAuthHeader(), uri, parameters);
        callback.setCall(call);
        call.enqueue(getRetrofitCallback(callback));

        return call;
    }

    @Nullable
    public Call<Object> editUser(String uri, @Nullable String name, @Nullable String location,
                                 @Nullable String bio, ModelCallback<Object> callback) {
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

        HashMap<String, Object> parameters = new HashMap<>();

        if (name != null) {
            parameters.put(Vimeo.PARAMETER_USERS_NAME, name);
        }

        if (location != null) {
            parameters.put(Vimeo.PARAMETER_USERS_LOCATION, location);
        }

        if (bio != null) {
            parameters.put(Vimeo.PARAMETER_USERS_BIO, bio);
        }

        Call<Object> call = this.vimeoService.edit(getAuthHeader(), uri, parameters);
        callback.setCall(call);
        call.enqueue(getRetrofitCallback(callback));
        return call;
    }

    // </editor-fold>

    /**
     * -----------------------------------------------------------------------------------------------------
     * Pictures
     * -----------------------------------------------------------------------------------------------------
     */
    // <editor-fold desc="Pictures">

    /**
     * Create a picture resource using the given uri
     * <p/>
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

        Call<PictureResource> call = this.vimeoService.createPictureResource(getAuthHeader(), uri);
        callback.setCall(call);
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
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put(Vimeo.PARAMETER_ACTIVE, true);
        Call<Object> call = this.vimeoService.edit(getAuthHeader(), uri, parameters);
        callback.setCall(call);
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

    /**
     * -----------------------------------------------------------------------------------------------------
     * Video actions (Like, Watch Later, Commenting)
     * -----------------------------------------------------------------------------------------------------
     */
    // <editor-fold desc="Video actions (Like, Watch Later, Commenting)">
    public Call<Object> updateFollow(boolean follow, String uri, ModelCallback callback) {
        return updateFollow(follow, uri, callback, true);
    }

    public Call<Object> updateFollow(boolean follow, String uri, ModelCallback callback, boolean enqueue) {
        if (follow) {
            return this.follow(uri, callback, enqueue);
        } else {
            return this.unfollow(uri, callback, enqueue);
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
            return this.likeVideo(uri, password, callback);
        } else {
            return this.unlikeVideo(uri, password, callback);
        }
    }

    public Call<Object> likeVideo(String uri, @Nullable String password, ModelCallback callback) {
        Map<String, String> options = new HashMap<>();
        if (password != null) {
            options.put(Vimeo.PARAMETER_PASSWORD, password);
        }
        return putContent(uri, options, callback, true);
    }

    public Call<Object> unlikeVideo(String uri, @Nullable String password, ModelCallback callback) {
        Map<String, String> options = new HashMap<>();
        if (password != null) {
            options.put(Vimeo.PARAMETER_PASSWORD, password);
        }
        return deleteContent(uri, options, callback, true);
    }

    public Call<Object> updateWatchLaterVideo(boolean watchLater, String uri, @Nullable String password,
                                              ModelCallback callback) {
        if (watchLater) {
            return this.watchLaterVideo(uri, password, callback);
        } else {
            return this.unwatchLaterVideo(uri, password, callback);
        }
    }

    public Call<Object> watchLaterVideo(String uri, @Nullable String password, ModelCallback callback) {
        Map<String, String> options = new HashMap<>();
        if (password != null) {
            options.put(Vimeo.PARAMETER_PASSWORD, password);
        }
        return putContent(uri, options, callback, true);
    }

    public Call<Object> unwatchLaterVideo(String uri, @Nullable String password, ModelCallback callback) {
        Map<String, String> options = new HashMap<>();
        if (password != null) {
            options.put(Vimeo.PARAMETER_PASSWORD, password);
        }
        return deleteContent(uri, options, callback, true);
    }


    @Nullable
    public Call<Comment> comment(String uri, String comment, @Nullable String password,
                                 ModelCallback<Comment> callback) {
        if (callback == null) {
            throw new AssertionError("Callback cannot be null");
        }

        if (uri == null || uri.isEmpty() || comment == null || comment.isEmpty()) {
            callback.failure(new VimeoError("uri cannot be empty!"));

            return null;
        }

        Map<String, String> options = new HashMap<>();
        if (password != null) {
            options.put(Vimeo.PARAMETER_PASSWORD, password);
        }

        HashMap<String, String> postBody = new HashMap<>();
        postBody.put(Vimeo.PARAMETER_COMMENT_TEXT_BODY, comment);

        Call<Comment> call = this.vimeoService.comment(getAuthHeader(), uri, options, postBody);
        callback.setCall(call);
        call.enqueue(callback);
        return call;
    }

    // </editor-fold>

    /**
     * -----------------------------------------------------------------------------------------------------
     * Gets, posts, puts, deletes
     * -----------------------------------------------------------------------------------------------------
     */
    // <editor-fold desc="Gets, posts, puts, deletes">
    public void deleteVideo(String uri, Map<String, String> options, ModelCallback callback) {
        deleteContent(uri, options, callback, true);
    }

    private VimeoCallback<Object> getRetrofitCallback(final ModelCallback<Object> callback) {
        return new VimeoCallback<Object>() {
            @Override
            public void success(Object o) {
                // Handle the gson parsing using a deserializer object
                configuration.deserializer.deserialize(VimeoNetworkUtil.getGson(), o, callback);
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

    public void fetchCurrentUser(ModelCallback<User> callback) {
        // Endpoints
        fetchContent(Vimeo.ENDPOINT_ME, CacheControl.FORCE_NETWORK, callback);
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
    public Call<Object> fetchContent(String uri, CacheControl cacheControl, ModelCallback callback,
                                     @Nullable String query, @Nullable Map<String, String> refinementMap,
                                     @Nullable String fieldFilter) {
        if (callback == null) {
            throw new AssertionError("Callback cannot be null");
        }

        if (uri == null || uri.isEmpty()) {
            callback.failure(new VimeoError("Uri cannot be empty!"));
            return null;
        }

        if (cacheControl != null) {
            if (cacheControl.onlyIfCached()) {
                CacheControl.Builder builder = getCacheControlBuilder(cacheControl);
                // If no max age specified on CacheControl then set it to our default [KV]
                if (cacheControl.maxAgeSeconds() == -1) {
                    builder.maxAge(configuration.cacheMaxAge, TimeUnit.SECONDS);
                }
                // CacheControl.FORCE_CACHE defaults stale to Integer.MAX so we need to overwrite it
                // so that a max age can actually do it's job [KV]
                builder.maxStale(0, TimeUnit.SECONDS);
                cacheControl = builder.build();
            }
        } else {
            cacheControl =
                    new CacheControl.Builder().maxAge(configuration.cacheMaxAge, TimeUnit.SECONDS).build();
        }
        String cacheHeaderValue = cacheControl.toString();

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

        Call<Object> call = this.vimeoService.GET(getAuthHeader(), uri, queryMap, cacheHeaderValue);
        callback.setCall(call);
        call.enqueue(getRetrofitCallback(callback));
        return call;
    }

    @Nullable
    public Call<Object> fetchContent(String uri, CacheControl cacheControl, ModelCallback callback) {
        return fetchContent(uri, cacheControl, callback, null, null, null);
    }

    @Nullable
    public Call<Object> fetchContent(String uri, CacheControl cacheControl, ModelCallback callback,
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
        return this.fetchContent(uri, CacheControl.FORCE_CACHE, callback);
    }

    @Nullable
    public Call<Object> fetchNetworkContent(String uri, ModelCallback callback) {
        return this.fetchContent(uri, CacheControl.FORCE_NETWORK, callback);
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
    public Call<Object> postContent(String uri, CacheControl cacheControl, HashMap<String, String> postBody,
                                    VimeoCallback callback) {
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

        String cacheHeaderValue = null;
        if (cacheControl != null) {
            cacheHeaderValue = cacheControl.toString();
        }

        // Don't use the deserialization callback because we don't get objects returned with post
        return POST(getAuthHeader(), uri, cacheHeaderValue, postBody, callback);
    }

    /**
     * A POST call where the API doesn't return any response body. This is only handled by Retrofit
     * if you specify a Void object response type.
     * Example: Forgot password call
     *
     * @param uri      URI of the resource to POST
     * @param callback The callback for the specific model type of the resource
     */
    @Nullable
    public Call<Void> emptyResponsePost(String uri, VimeoCallback<Void> callback) {
        if (callback == null) {
            throw new AssertionError("Callback cannot be null");
        }

        if (uri == null) {
            callback.failure(new VimeoError("uri cannot be empty!"));

            return null;
        }

        Call<Void> call = this.vimeoService.emptyResponsePost(getAuthHeader(), uri);
        callback.setCall(call);
        call.enqueue(callback);
        return call;
    }

    @Nullable
    public Call<Object> putContent(String uri, @Nullable Map<String, String> options, ModelCallback callback,
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
    public Call<Object> deleteContent(String uri, @Nullable Map<String, String> options,
                                      ModelCallback callback, boolean enqueue) {
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

    public Call<Object> deleteContent(String uri, ModelCallback callback, boolean enqueue) {
        return deleteContent(uri, null, callback, enqueue);
    }

    public Call<Object> deleteContent(String uri, ModelCallback callback) {
        return deleteContent(uri, callback, true);
    }

    private Call<Object> PUT(String authHeader, String uri, Map<String, String> options,
                             VimeoCallback<Object> callback, boolean enqueue) {
        Call<Object> call = this.vimeoService.PUT(authHeader, uri, options);
        callback.setCall(call);
        if (enqueue) {
            call.enqueue(callback);
        }
        return call;
    }

    private Call<Object> DELETE(String authHeader, String uri, Map<String, String> options,
                                VimeoCallback<Object> callback, boolean enqueue) {
        Call<Object> call = this.vimeoService.DELETE(authHeader, uri, options);
        callback.setCall(call);
        if (enqueue) {
            call.enqueue(callback);
        }
        return call;
    }

    private Call<Object> POST(String authHeader, String uri, String cacheHeaderValue,
                              HashMap<String, String> parameters, VimeoCallback<Object> callback) {
        Call<Object> call = this.vimeoService.POST(authHeader, uri, cacheHeaderValue, parameters);
        callback.setCall(call);
        call.enqueue(callback);
        return call;
    }

    // </editor-fold>

    /**
     * -----------------------------------------------------------------------------------------------------
     * Header values
     * -----------------------------------------------------------------------------------------------------
     */
    // <editor-fold desc="Header values">
    public String getUserAgent() {
        return this.configuration.userAgentString;
    }

    public String getAcceptHeader() {
        return "application/vnd.vimeo.*+json; version=" + this.configuration.apiVersionString;
    }

    public String getAuthHeader() {
        String credential;

        if (this.account != null && this.account.isAuthenticated()) {
            credential = "Bearer " + this.account.getAccessToken();
        } else {
            credential = getBasicAuthHeader();
        }

        return credential;
    }

    private String getBasicAuthHeader() {
        return Credentials.basic(configuration.clientID, configuration.clientSecret);
    }
    // </editor-fold>
}
