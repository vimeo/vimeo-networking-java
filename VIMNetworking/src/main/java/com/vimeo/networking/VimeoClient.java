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

import com.google.common.base.Splitter;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.CacheControl;
import com.squareup.okhttp.Credentials;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.vimeo.networking.logging.LoggingInterceptor;
import com.vimeo.networking.model.Account;
import com.vimeo.networking.model.Comment;
import com.vimeo.networking.model.PictureResource;
import com.vimeo.networking.model.Privacy;
import com.vimeo.networking.model.User;
import com.vimeo.networking.model.error.ErrorCode;
import com.vimeo.networking.model.error.VimeoError;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.annotation.Nullable;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

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
        public com.squareup.okhttp.Response intercept(Chain chain) throws IOException {
            com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());

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

    private VimeoClient(final Configuration configuration) {
        if (configuration == null) {
            throw new AssertionError("Configuration cannot be null");
        }

        this.configuration = configuration;

        final VimeoClient client = this;

        this.cache = new Cache(this.configuration.cacheDirectory, this.configuration.cacheSize);

        RetrofitClientBuilder retrofitClientBuilder = new RetrofitClientBuilder();
        retrofitClientBuilder.setCache(cache);
        retrofitClientBuilder.addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR);
        if (configuration.certPinningEnabled) {
            try {
                retrofitClientBuilder.pinCertificates();
            } catch (Exception e) {
                this.configuration.networkingLogger.e("Exception when pinning certificate: " + e.getMessage(),
                                                      e);
            }
        }

        OkHttpClient okHttpClient = retrofitClientBuilder.build();
        okHttpClient.interceptors().add(new LoggingInterceptor());
        okHttpClient.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                // Customize the request
                Request request = original.newBuilder()
                        .header(Vimeo.HEADER_USER_AGENT, configuration.userAgentString)
                        .header(Vimeo.HEADER_ACCEPT, client.getAcceptHeader())
                        .method(original.method(), original.body())
                        .build();

                Response response = chain.proceed(request);

                // Customize or return the response
                return response;
            }
        });

        Retrofit retrofit = new Retrofit.Builder().baseUrl(configuration.baseURLString)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(getGson()))
                .build();

        this.vimeoService = retrofit.create(VimeoService.class);

        Account account = this.configuration.accountStore.loadAccount();
        this.setAccount(account);
    }

    public void clearRequestCache() {
        try {
            this.cache.evictAll();
        } catch (IOException e) {
            configuration.networkingLogger.e("Cache clearing error: " + e.getMessage(), e);
        }
    }

    /**
     * Static helper method that automatically applies the VimeoClient Gson preferences
     * </p>
     * This includes formatting for dates as well as a LOWER_CASE_WITH_UNDERSCORES field naming policy
     * </p>
     *
     * @return Gson object that can be passed into a {@link GsonConverterFactory} create() method
     */
    public static Gson getGson() {
        // Example date: "2015-05-21T14:24:03+00:00"
        return getGsonBuilder().create();
    }

    /**
     * Static helper method that automatically applies the VimeoClient Gson preferences
     * </p>
     * This includes formatting for dates as well as a LOWER_CASE_WITH_UNDERSCORES field naming policy
     * </p>
     *
     * @return GsonBuilder that can be built upon and then created
     */
    public static GsonBuilder getGsonBuilder() {
        // Example date: "2015-05-21T14:24:03+00:00"
        return new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(Date.class, ISO8601.getDateSerializer())
                .registerTypeAdapter(Date.class, ISO8601.getDateDeserializer());
        /** Refer to {@link ISO8601} for explanation of deserialization */
        // .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ")
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

    public void setBaseUrlString(String baseUrlString) {
        this.configuration.baseURLString = baseUrlString;
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
     * code and state
     * <p/>
     *
     * @return The URI that should be opened in a web view
     * @see <a href="https://developer.vimeo.com/api/authentication#generate-redirect">Vimeo API Docs</a>
     */
    public String getCodeGrantAuthorizationURI() {
        currentCodeGrantState = UUID.randomUUID().toString();

        Map<String, String> map = new HashMap<>();
        map.put(Vimeo.PARAMETER_REDIRECT_URI, this.configuration.codeGrantRedirectURI);
        map.put(Vimeo.PARAMETER_RESPONSE_TYPE, Vimeo.CODE_GRANT_RESPONSE_TYPE);
        map.put(Vimeo.PARAMETER_STATE, this.currentCodeGrantState);
        map.put(Vimeo.PARAMETER_SCOPE, this.configuration.scope);
        map.put(Vimeo.PARAMETER_CLIENT_ID, this.configuration.clientID);

        String uri = urlEncodeUTF8(map);

        // TODO: find a better way to build a URL and query string [AH]
        return this.configuration.baseURLString + Vimeo.CODE_GRANT_PATH + "?" + uri;
    }


    /**
     * Authenticates the user from the codeGrantAuthorizationURI().
     * <p/>
     * Exchanges the code for the access token.
     * <p/>
     *
     * @param uri      URI from {@link #getCodeGrantAuthorizationURI() getCodeGrantAuthorizationURI}
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

        // TODO: find a better way to do this [AH]
        String query = uri.split("\\?")[1];
        Map<String, String> queryMap = Splitter.on('&').trimResults().withKeyValueSeparator("=").split(query);
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
    public Call<Account> authorizeWithClientCredentialsGrant(final AuthCallback callback) {
        if (callback == null) {
            throw new AssertionError("Callback cannot be null");
        }

        Call<Account> call = this.vimeoService.authorizeWithClientCredentialsGrant(getBasicAuthHeader(),
                                                                                   Vimeo.CLIENT_CREDENTIALS_GRANT_TYPE,
                                                                                   configuration.scope);
        call.enqueue(new AccountCallback(this, callback));
        return call;
    }

    @Nullable
    public Call<Account> join(String displayName, String email, String password,
                              final AuthCallback callback) {
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
    public Call<Account> joinWithFacebookToken(final String facebookToken, final String email,
                                               final AuthCallback callback) {
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
    public Call<Account> logIn(final String email, final String password, final AuthCallback callback) {
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
     * <p/>
     *
     * @param email    user's email address
     * @param password user's password
     * @return the account object since it is synchronous
     */
    public Account logIn(final String email, final String password) {
        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            return null;
        }

        Call<Account> call =
                this.vimeoService.logIn(getBasicAuthHeader(), email, password, Vimeo.PASSWORD_GRANT_TYPE,
                                        configuration.scope);

        Account account = null;
        try {
            retrofit.Response<Account> response = call.execute();
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
    public Call<Account> loginWithFacebookToken(final String facebookToken, final String email,
                                                final AuthCallback callback) {
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
    public Call<Object> logOut(final VimeoCallback<Object> callback) {

        Call<Object> call = this.vimeoService.logOut(getAuthHeader());
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
                this.client.configuration.accountStore.saveAccount(account, account.getUser().name, null);
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
    public Call<Object> editVideo(String uri, String title, String description, String password,
                                  Privacy.PrivacyValue privacyValue, ModelCallback callback) {
        if (callback == null) {
            throw new AssertionError("Callback cannot be null");
        }

        if (uri == null || uri.isEmpty()) {
            callback.failure(new VimeoError("uri cannot be empty!"));

            return null;
        }

        if (title == null && description == null && privacyValue == null) {
            // No point in editing video
            callback.failure(new VimeoError("title, description, and privacyValue cannot be empty!"));

            return null;
        }

        String privacyString = Privacy.privacyStringFromValue(privacyValue);

        HashMap<String, String> privacyMap = new HashMap<>();
        privacyMap.put(Vimeo.PARAMETER_VIDEO_VIEW, privacyString);

        HashMap<String, Object> parameters = new HashMap<>();

        if (title != null) {
            parameters.put(Vimeo.PARAMETER_VIDEO_NAME, title);
        }

        if (description != null) {
            parameters.put(Vimeo.PARAMETER_VIDEO_DESCRIPTION, description);
        }

        parameters.put(Vimeo.PARAMETER_VIDEO_PRIVACY, privacyMap);

        if ((privacyValue == Privacy.PrivacyValue.PASSWORD) &&
            ((password == null) || (password.trim().isEmpty()))) {
            callback.failure(new VimeoError("password is required for password privacy type"));
            return null;
        } else if (privacyValue == Privacy.PrivacyValue.PASSWORD) {
            parameters.put(Vimeo.PARAMETER_VIDEO_PASSWORD, password);
        }

        Call<Object> call = this.vimeoService.edit(getAuthHeader(), validateUri(uri), parameters);
        call.enqueue(getRetrofitCallback(callback));

        return call;
    }

    @Nullable
    public Call<Object> editUser(String uri, @Nullable String name, @Nullable String location,
                                 @Nullable String bio, ModelCallback callback) {
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

        Call<Object> call = this.vimeoService.edit(getAuthHeader(), validateUri(uri), parameters);
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
    public Call<Object> createPictureResource(String uri, ModelCallback<PictureResource> callback) {
        if (uri == null || uri.trim().isEmpty()) {
            callback.failure(new VimeoError("uri cannot be empty!"));
            return null;
        }

        // Body is empty, but cannot be null
        Call<Object> call = this.vimeoService.createPictureResource(getAuthHeader(), validateUri(uri), "");
        call.enqueue(getRetrofitCallback(callback));
        return call;
    }

    /**
     * Activate a picture resource
     *
     * @param uri      The Uri that is found in the PictureResource returned from
     *                 {@link #createPictureResource(String, ModelCallback)}
     * @param callback
     */
    @Nullable
    public Call<Object> activatePictureResource(String uri, ModelCallback callback) {
        if (uri == null || uri.trim().isEmpty()) {
            callback.failure(new VimeoError("uri cannot be empty!"));
            return null;
        }
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put(Vimeo.PARAMETER_ACTIVE, true);
        Call<Object> call = this.vimeoService.edit(getAuthHeader(), validateUri(uri), parameters);
        call.enqueue(getRetrofitCallback(callback));
        return call;
    }

    /**
     * Delete a picture resource
     * This is helpful if your activation or upload step fails - you can delete the resource allocated
     *
     * @param uri      The Uri that is found in the PictureResource returned from
     *                 {@link #createPictureResource(String, ModelCallback)}
     * @param callback
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
    public void updateFollow(boolean follow, String uri, ModelCallback callback) {
        if (follow) {
            this.follow(uri, callback);
        } else {
            this.unfollow(uri, callback);
        }
    }

    public void follow(String uri, ModelCallback callback) {
        putContent(uri, null, callback);
    }

    public void unfollow(String uri, ModelCallback callback) {
        deleteContent(uri, callback);
    }

    public void updateLikeVideo(boolean like, String uri, @Nullable String password, ModelCallback callback) {
        if (like) {
            this.likeVideo(uri, password, callback);
        } else {
            this.unlikeVideo(uri, password, callback);
        }
    }

    public void likeVideo(String uri, @Nullable String password, ModelCallback callback) {
        Map<String, String> options = new HashMap<>();
        if (password != null) {
            options.put(Vimeo.PARAMETER_PASSWORD, password);
        }
        putContent(uri, options, callback);
    }

    public void unlikeVideo(String uri, @Nullable String password, ModelCallback callback) {
        Map<String, String> options = new HashMap<>();
        if (password != null) {
            options.put(Vimeo.PARAMETER_PASSWORD, password);
        }
        deleteContent(uri, options, callback);
    }

    public void updateWatchLaterVideo(boolean watchLater, String uri, @Nullable String password,
                                      ModelCallback callback) {
        if (watchLater) {
            this.watchLaterVideo(uri, password, callback);
        } else {
            this.unwatchLaterVideo(uri, password, callback);
        }
    }

    public void watchLaterVideo(String uri, @Nullable String password, ModelCallback callback) {
        Map<String, String> options = new HashMap<>();
        if (password != null) {
            options.put(Vimeo.PARAMETER_PASSWORD, password);
        }
        putContent(uri, options, callback);
    }

    public void unwatchLaterVideo(String uri, @Nullable String password, ModelCallback callback) {
        Map<String, String> options = new HashMap<>();
        if (password != null) {
            options.put(Vimeo.PARAMETER_PASSWORD, password);
        }
        deleteContent(uri, options, callback);
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

        Call<Comment> call = this.vimeoService.comment(getAuthHeader(), validateUri(uri), options, postBody);
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
        deleteContent(uri, options, callback);
    }

    private Callback<Object> getRetrofitCallback(final ModelCallback callback) {
        return new VimeoCallback<Object>() {
            @Override
            public void success(Object o) {
                //Handle the gson parsing using a deserializer object
                configuration.deserializer.deserialize(getGson(), o, callback);
            }

            @Override
            public void failure(VimeoError error) {
                callback.failure(error);
            }
        };
    }

    @Nullable
    public Call<Object> search(String uri, String query, final ModelCallback callback,
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

    // TODO: this may end up living in VIMUpload 10/15/15 [KV]
    public void getCurrentUser(ModelCallback<User> callback) {
        // Endpoints
        String ENDPOINT_ME = "me";
        fetchContent(ENDPOINT_ME, null, callback);
    }

    /**
     * A generic GET call that takes in the URI of the specific resource.
     * <p/>
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

        Call<Object> call =
                this.vimeoService.GET(getAuthHeader(), validateUri(uri), queryMap, cacheHeaderValue);
        call.enqueue(getRetrofitCallback(callback));
        return call;
    }

    @Nullable
    public Call<Object> fetchContent(String uri, CacheControl cacheControl, final ModelCallback callback) {
        return fetchContent(uri, cacheControl, callback, null, null, null);
    }

    @Nullable
    public Call<Object> fetchContent(String uri, CacheControl cacheControl, final ModelCallback callback,
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
     * <p/>
     *
     * @param uri          URI of the resource to POST
     * @param cacheControl Cache control type
     * @param postBody     The body of the POST request
     * @param callback     The callback for the specific model type of the resource
     */
    @Nullable
    public Call<Object> postContent(String uri, CacheControl cacheControl, HashMap<String, String> postBody,
                                    final VimeoCallback callback) {
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

    @Nullable
    public Call<Object> putContent(String uri, @Nullable Map<String, String> options,
                                   ModelCallback callback) {
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

        return PUT(getAuthHeader(), uri, options, getRetrofitCallback(callback));
    }

    @Nullable
    public Call<Object> deleteContent(String uri, @Nullable Map<String, String> options,
                                      ModelCallback callback) {
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

        return DELETE(getAuthHeader(), uri, options, getRetrofitCallback(callback));
    }

    public void deleteContent(String uri, ModelCallback callback) {
        deleteContent(uri, null, callback);
    }

    private Call<Object> PUT(String authHeader, String uri, Map<String, String> options,
                             Callback<Object> callback) {
        Call<Object> call = this.vimeoService.PUT(authHeader, validateUri(uri), options);
        call.enqueue(callback);
        return call;
    }

    private Call<Object> DELETE(String authHeader, String uri, Map<String, String> options,
                                Callback<Object> callback) {
        Call<Object> call = this.vimeoService.DELETE(authHeader, validateUri(uri), options);
        call.enqueue(callback);
        return call;
    }

    private Call<Object> POST(String authHeader, String uri, String cacheHeaderValue,
                              HashMap<String, String> parameters, Callback<Object> callback) {
        Call<Object> call =
                this.vimeoService.POST(authHeader, validateUri(uri), cacheHeaderValue, parameters);
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
        return "sample_user_agent";
    }

    public String getAcceptHeader() {
        return "application/vnd.vimeo.*+json; version=" + this.configuration.APIVersionString;
    }

    public String getAuthHeader() {
        String credential = null;

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

    /**
     * -----------------------------------------------------------------------------------------------------
     * Utilities
     * -----------------------------------------------------------------------------------------------------
     */
    // <editor-fold desc="Utilities">

    // TODO: This is shitty, revisit [AH]
    static String urlEncodeUTF8(Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (sb.length() > 0) {
                sb.append("&");
            }

            sb.append(String.format("%s=%s", urlEncodeUTF8(entry.getKey()), urlEncodeUTF8(entry.getValue())));
        }

        return sb.toString();
    }

    static String urlEncodeUTF8(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedOperationException(e);
        }
    }

    private String validateUri(String uri) {
        // TODO: We shouldn't have to do this but Retrofit doesn't support removing the leading slash
        // I asked a question on StackOverflow which we can keep our eye on.
        // http://stackoverflow.com/questions/30623580/duplicate-slashes-in-retrofit-url [KV]
        if (uri.charAt(0) == '/') {
            uri = uri.substring(1);
        }
        return uri;
    }

    // </editor-fold>

}
