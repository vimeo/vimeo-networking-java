package com.vimeo.networking;

import com.google.common.base.Splitter;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.CacheControl;
import com.squareup.okhttp.Credentials;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.vimeo.networking.model.Account;
import com.vimeo.networking.model.Privacy;
import com.vimeo.networking.model.UserList;
import com.vimeo.networking.model.VideoList;
import com.vimeo.networking.model.error.VimeoError;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;
import retrofit.mime.TypedByteArray;

/**
 * Client class used for making networking calls to Vimeo API.
 * <p/>
 * Created by alfredhanssen on 4/12/15.
 */
public class VimeoClient {

    public static final String VIMEO_BASE_URL_STRING = "https://api.vimeo.com/";
    private static final String CODE_GRANT_PATH = "oauth/authorize";
    private static final String CODE_GRANT_RESPONSE_TYPE = "code";
    private static final String CODE_GRANT_STATE = "state";
    private static final String CODE_GRANT_TYPE = "authorization_code";
    private static final String FACEBOOK_GRANT_TYPE = "facebook";
    private static final String PASSWORD_GRANT_TYPE = "password";
    private static final String CLIENT_CREDENTIALS_GRANT_TYPE = "client_credentials";

    private Configuration configuration;
    private VimeoService vimeoService;
    private Cache cache;
    private String currentCodeGrantState;
    /**
     * Currently authenticated account
     */
    private Account account;

    /**
     * Dangerous interceptor that rewrites the server's cache-control header.
     * We are using this because our server sets all Cache-Control headers to no-store
     * [AH] 4/24/2015
     */
    private static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        @Override
        public com.squareup.okhttp.Response intercept(Chain chain) throws IOException {
            com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());

            return originalResponse.newBuilder().header("Cache-Control", "public").build();
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
        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader("User-Agent", configuration.userAgentString);
                request.addHeader("Accept", client.getAcceptHeader());
                request.addHeader("Authorization", client.getAuthHeader());
            }
        };

        OkHttpClient okHttpClient = new OkHttpClient();
        try {
            this.cache = new Cache(this.configuration.cacheDirectory, this.configuration.cacheSize);
            okHttpClient.setCache(cache);
        } catch (IOException e) {
            System.out.println("Exception when creating cache: " + e.getMessage());
        }

        okHttpClient.networkInterceptors().add(REWRITE_CACHE_CONTROL_INTERCEPTOR);

        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(configuration.baseURLString)
                                                           .setClient(new OkClient(okHttpClient))
                                                           .setLogLevel(RestAdapter.LogLevel.FULL)
                                                           .setRequestInterceptor(requestInterceptor)
                                                           .setConverter(new GsonConverter(getGson()))
                                                           .build();

        this.vimeoService = restAdapter.create(VimeoService.class);

        Account account = this.configuration.accountStore.loadAccount();
        this.setAccount(account);
    }

    /**
     * Static helper method that automatically applies the VimeoClient Gson preferences
     * </p>
     * This includes formatting for dates as well as a LOWER_CASE_WITH_UNDERSCORES field naming policy
     * </p>
     *
     * @return Gson object that can be passed into a {@link GsonConverter}
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
                                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssz");
    }

    // region Accessors

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

    // end region

    // region Authentication

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
        map.put("redirect_uri", this.configuration.codeGrantRedirectURI);
        map.put("response_type", CODE_GRANT_RESPONSE_TYPE);
        map.put("state", this.currentCodeGrantState);
        map.put("scope", this.configuration.scope);
        map.put("client_id", this.configuration.clientID);

        String uri = urlEncodeUTF8(map);

        // TODO: find a better way to build a URL and query string [AH]
        return this.configuration.baseURLString + CODE_GRANT_PATH + "?" + uri;
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
    public void authenticateWithCodeGrant(String uri, AuthCallback callback) {
        if (callback == null) {
            throw new AssertionError("Callback cannot be null");
        }

        if (uri == null || uri.isEmpty()) {
            callback.failure(new Error("Uri must not be null"));

            return;
        }

        // TODO: find a better way to do this [AH]
        String query = uri.split("\\?")[1];
        Map<String, String> queryMap = Splitter.on('&').trimResults().withKeyValueSeparator("=").split(query);
        String code = queryMap.get(CODE_GRANT_RESPONSE_TYPE);
        String state = queryMap.get(CODE_GRANT_STATE);

        if (code == null || code.isEmpty() || state == null || state.isEmpty() ||
            !state.equals(this.currentCodeGrantState)) {
            this.currentCodeGrantState = null;

            callback.failure(new Error("Code grant code is null or state has changed"));

            return;
        }

        this.currentCodeGrantState = null;

        String redirectURI = this.configuration.codeGrantRedirectURI;

        this.vimeoService.authenticateWithCodeGrant(redirectURI, code, CODE_GRANT_TYPE,
                                                    new AccountCallback(this, callback));
    }

    /**
     * Authorizes users of the app who are not signed in.
     * <p/>
     * Leaves User as null in {@link Account} model and populates the rest
     * <p/>
     *
     * @param callback Callback pertaining to authentication
     */
    public void authorizeWithClientCredentialsGrant(final AuthCallback callback) {
        if (callback == null) {
            throw new AssertionError("Callback cannot be null");
        }

        this.vimeoService
                .authorizeWithClientCredentialsGrant(CLIENT_CREDENTIALS_GRANT_TYPE, configuration.scope,
                                                     new AccountCallback(this, callback));
    }

    public void join(String displayName, String email, String password, final AuthCallback callback) {
        if (callback == null) {
            throw new AssertionError("Callback cannot be null");
        }

        if (displayName == null || displayName.isEmpty() || email == null || email.isEmpty() ||
            password == null || password.isEmpty()) {
            callback.failure(new Error("displayName, email, password must be set"));

            return;
        }

        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("name", displayName);
        parameters.put("email", email);
        parameters.put("password", password);
        parameters.put("scope", configuration.scope);

        this.vimeoService.join(parameters, new AccountCallback(this, email, password, callback));
    }

    public void joinWithFacebookToken(String facebookToken, final AuthCallback callback) {
        if (callback == null) {
            throw new AssertionError("Callback cannot be null");
        }

        if (facebookToken == null || facebookToken.isEmpty()) {
            callback.failure(new Error("facebookToken must be set"));
            return;
        }

        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("token", facebookToken);
        parameters.put("scope", configuration.scope);

        this.vimeoService.join(parameters, new AccountCallback(this, callback));
    }

    public void logIn(String email, String password, final AuthCallback callback) {
        if (callback == null) {
            throw new AssertionError("Callback cannot be null");
        }

        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            callback.failure(new Error("email, password must be set"));

            return;
        }

        this.vimeoService.logIn(email, password, PASSWORD_GRANT_TYPE, configuration.scope,
                                new AccountCallback(this, email, password, callback));
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
    public Account logIn(String email, String password) {
        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            return null;
        }

        Account account = this.vimeoService.logIn(email, password, PASSWORD_GRANT_TYPE, configuration.scope);

        this.setAccount(account);

        this.configuration.accountStore.saveAccount(account, email, password);

        return account;
    }

    public void loginWithFacebookToken(String facebookToken, final AuthCallback callback) {
        if (callback == null) {
            throw new AssertionError("Callback cannot be null");
        }

        if (facebookToken == null || facebookToken.isEmpty()) {
            callback.failure(new Error("facebookToken must be set"));
            return;
        }

        this.vimeoService.logInWithFacebook(FACEBOOK_GRANT_TYPE, facebookToken, configuration.scope,
                                            new AccountCallback(this, callback));
    }


    /**
     * Must be called when user logs out to ensure that the tokens have been invalidated
     * <p/>
     *
     * @param callback Callback for handling logout
     */
    public void logOut(final VimeoCallback<Object> callback) {

        this.vimeoService.logOut(new VimeoCallback<Object>() {
            @Override
            public void success(Object o, VimeoResponse response) {
                if (callback != null) {
                    callback.success(o, response);
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
        public void success(Account account, VimeoResponse response) {
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
            String json = new String(
                    ((TypedByteArray) error.getRetrofitError().getResponse().getBody()).getBytes());
            String message = error.toString();
            Gson gson = new Gson();
            JsonElement element = gson.fromJson(json, JsonElement.class);
            JsonObject jsonObj = element.getAsJsonObject();
            if (jsonObj.has("error_description")) {
                message = jsonObj.get("error_description").toString();
            }
            callback.failure(new Error(message));
        }
    }

    // end region

    // region Channels

    public void fetchStaffPicks(VimeoCallback<VideoList> callback) {
        if (callback == null) {
            throw new AssertionError("Callback cannot be null");
        }

        this.vimeoService.fetchStaffPicks(callback);
    }

    // end region

    // region Search

    public void searchVideos(String query, VimeoCallback<VideoList> callback) {
        if (callback == null) {
            throw new AssertionError("Callback cannot be null");
        }

        if (query == null || query.isEmpty()) {
            callback.failure(new VimeoError(
                    RetrofitError.unexpectedError("/videos", new Throwable("Query cannot be empty!"))));

            return;
        }

        this.vimeoService.searchVideos(query, callback);
    }

    public void searchUsers(String query, VimeoCallback<UserList> callback) {
        if (callback == null) {
            throw new AssertionError("Callback cannot be null");
        }

        if (query == null || query.isEmpty()) {
            callback.failure(new VimeoError(
                    RetrofitError.unexpectedError("/users", new Throwable("Query cannot be empty!"))));

            return;
        }

        this.vimeoService.searchUsers(query, callback);
    }

    // end region

    // region Editing

    public void editVideo(String uri, String title, String description, Privacy.PrivacyValue privacyValue,
                          VimeoCallback callback) {
        if (callback == null) {
            throw new AssertionError("Callback cannot be null");
        }

        if (uri == null || uri.isEmpty()) {
            callback.failure(new VimeoError(
                    RetrofitError.unexpectedError("/{uri}", new Throwable("uri cannot be empty!"))));

            return;
        }

        if (title == null && description == null &&
            privacyValue == null) // No point in editing video
        {
            callback.failure(new VimeoError(RetrofitError.unexpectedError("/{uri}", new Throwable(
                    "title, description, and privacyValue cannot be empty!"))));

            return;
        }

        String privacyString = Privacy.privacyStringFromValue(privacyValue);

        HashMap<String, String> privacyMap = new HashMap<>();
        privacyMap.put("view", privacyString);

        HashMap<String, Object> parameters = new HashMap<>();

        if (title != null) {
            parameters.put("name", title);
        }

        if (description != null) {
            parameters.put("description", description);
        }

        parameters.put("privacy", privacyMap);

        this.vimeoService.editVideo(uri, parameters, callback);
    }

    public void editUser(String uri, String name, String location, VimeoCallback callback) {
        if (callback == null) {
            throw new AssertionError("Callback cannot be null");
        }

        if (uri == null || uri.isEmpty()) {
            callback.failure(new VimeoError(
                    RetrofitError.unexpectedError("/{uri}", new Throwable("uri cannot be empty!"))));

            return;
        }

        if (name == null && location == null) // No point in editing user
        {
            callback.failure(new VimeoError(RetrofitError.unexpectedError("/{uri}", new Throwable(
                    "name and location cannot be empty!"))));

            return;
        }

        HashMap<String, Object> parameters = new HashMap<>();

        if (name != null) {
            parameters.put("name", name);
        }

        if (location != null) {
            parameters.put("location", location);
        }

        this.vimeoService.editUser(uri, parameters, callback);
    }

    public void updateFollowUser(boolean follow, String uri, VimeoCallback callback) {
        if (follow) {
            this.followUser(uri, callback);
        } else {
            this.unfollowUser(uri, callback);
        }
    }

    public void followUser(String uri, VimeoCallback callback) {
        if (callback == null) {
            throw new AssertionError("Callback cannot be null");
        }

        if (uri == null || uri.isEmpty()) {
            callback.failure(new VimeoError(
                    RetrofitError.unexpectedError("/{uri}", new Throwable("uri cannot be empty!"))));

            return;
        }

        this.vimeoService.PUT(uri, callback);
    }

    public void unfollowUser(String uri, VimeoCallback callback) {
        if (callback == null) {
            throw new AssertionError("Callback cannot be null");
        }

        if (uri == null || uri.isEmpty()) {
            callback.failure(new VimeoError(
                    RetrofitError.unexpectedError("/{uri}", new Throwable("uri cannot be empty!"))));

            return;
        }

        this.vimeoService.DELETE(uri, callback);
    }

    public void updateLikeVideo(boolean like, String uri, VimeoCallback callback) {
        if (like) {
            this.likeVideo(uri, callback);
        } else {
            this.unlikeVideo(uri, callback);
        }
    }

    public void likeVideo(String uri, VimeoCallback callback) {
        if (callback == null) {
            throw new AssertionError("Callback cannot be null");
        }

        if (uri == null || uri.isEmpty()) {
            callback.failure(new VimeoError(
                    RetrofitError.unexpectedError("/{uri}", new Throwable("uri cannot be empty!"))));

            return;
        }

        this.vimeoService.PUT(uri, callback);
    }

    public void unlikeVideo(String uri, VimeoCallback callback) {
        if (callback == null) {
            throw new AssertionError("Callback cannot be null");
        }

        if (uri == null || uri.isEmpty()) {
            callback.failure(new VimeoError(
                    RetrofitError.unexpectedError("/{uri}", new Throwable("uri cannot be empty!"))));

            return;
        }

        this.vimeoService.DELETE(uri, callback);
    }

    public void updateWatchLaterVideo(boolean watchLater, String uri, VimeoCallback callback) {
        if (watchLater) {
            this.watchLaterVideo(uri, callback);
        } else {
            this.unwatchLaterVideo(uri, callback);
        }
    }

    public void watchLaterVideo(String uri, VimeoCallback callback) {
        if (callback == null) {
            throw new AssertionError("Callback cannot be null");
        }


        if (uri == null || uri.isEmpty()) {
            callback.failure(new VimeoError(
                    RetrofitError.unexpectedError("/{uri}", new Throwable("uri cannot be empty!"))));

            return;
        }

        this.vimeoService.PUT(uri, callback);
    }

    public void unwatchLaterVideo(String uri, VimeoCallback callback) {
        if (callback == null) {
            throw new AssertionError("Callback cannot be null");
        }


        if (uri == null || uri.isEmpty()) {
            callback.failure(new VimeoError(
                    RetrofitError.unexpectedError("/{uri}", new Throwable("uri cannot be empty!"))));

            return;
        }

        this.vimeoService.DELETE(uri, callback);
    }


    public void comment(String uri, String comment, VimeoCallback callback) {
        if (callback == null) {
            throw new AssertionError("Callback cannot be null");
        }

        if (uri == null || uri.isEmpty() || comment == null || comment.isEmpty()) {
            callback.failure(new VimeoError(
                    RetrofitError.unexpectedError("/{uri}", new Throwable("uri cannot be empty!"))));

            return;
        }

        this.vimeoService.comment(uri, comment, callback);
    }

    public void deleteVideo(String uri, VimeoCallback callback) {
        if (callback == null) {
            throw new AssertionError("Callback cannot be null");
        }

        if (uri == null || uri.isEmpty()) {
            callback.failure(new VimeoError(
                    RetrofitError.unexpectedError("/{uri}", new Throwable("uri cannot be empty!"))));

            return;
        }

        this.vimeoService.DELETE(uri, callback);
    }

    // end region

    // region Generic

    /**
     * A generic GET call that takes in the URI of the specific resource.
     * <p/>
     *
     * @param uri          URI of the resource to GET
     * @param cacheControl Cache control type
     * @param callback     The callback for the specific model type of the resource
     */
    public void fetchContent(String uri, CacheControl cacheControl, final ModelCallback callback) {
        if (callback == null) {
            throw new AssertionError("Callback cannot be null");
        }

        if (uri == null || uri.isEmpty()) {
            callback.failure(new VimeoError(
                    RetrofitError.unexpectedError("/{uri}", new Throwable("uri cannot be empty!"))));

            return;
        }

        String cacheHeaderValue = null;
        if (cacheControl != null) {
            cacheHeaderValue = cacheControl.toString();
        }

        this.vimeoService.GET(uri, cacheHeaderValue, new VimeoCallback<Object>() {
            @Override
            public void success(Object o, VimeoResponse response) {
                Gson gson = getGson();
                String JSON = gson.toJson(o);
                Object object = gson.fromJson(JSON, callback.getObjectType());
                callback.success(object, response);
            }

            @Override
            public void failure(VimeoError error) {
                callback.failure(error);
            }
        });
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
    public void postContent(String uri, CacheControl cacheControl, HashMap<String, String> postBody,
                            final VimeoCallback<Object> callback) {
        if (callback == null) {
            throw new AssertionError("Callback cannot be null");
        }

        if (uri == null) {
            callback.failure(new VimeoError(
                    RetrofitError.unexpectedError("/{uri}", new Throwable("uri cannot be empty!"))));

            return;
        }

        if (postBody == null) {
            postBody = new HashMap<>();
        }

        String cacheHeaderValue = null;
        if (cacheControl != null) {
            cacheHeaderValue = cacheControl.toString();
        }

        this.vimeoService.POST(uri, cacheHeaderValue, postBody, new VimeoCallback<Object>() {
            @Override
            public void success(Object o, VimeoResponse response) {
                callback.success(o, response);
            }

            @Override
            public void failure(VimeoError error) {
                callback.failure(error);
            }
        });
    }

    public void fetchCachedContent(String uri, ModelCallback callback) {
        this.fetchContent(uri, CacheControl.FORCE_CACHE, callback);
    }

    public void fetchNetworkContent(String uri, ModelCallback callback) {
        this.fetchContent(uri, CacheControl.FORCE_NETWORK, callback);
    }

    // end region

    // region HeaderValues

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
            credential = Credentials.basic(configuration.clientID, configuration.clientSecret);
        }

        return credential;
    }

    // end region

    // region Utilities

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

    // end region

}
