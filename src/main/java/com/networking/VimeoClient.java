package com.networking;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.common.base.Splitter;

import com.squareup.okhttp.Cache;
import com.squareup.okhttp.CacheControl;
import com.squareup.okhttp.Credentials;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import model.Account;
import model.UserList;
import model.VideoList;
import model.User;
import model.Video;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;

/**
 * Created by alfredhanssen on 4/12/15.
 */
public class VimeoClient
{
    private static final String CODE_GRANT_PATH = "oauth/authorize";
    private static final String CODE_GRANT_RESPONSE_TYPE = "code";
    private static final String CODE_GRANT_STATE = "state";
    private static final String CODE_GRANT_TYPE = "authorization_code";
    private static final String PASSWORD_GRANT_TYPE = "password";
    private static final String CLIENT_CREDENTIALS_GRANT_TYPE = "client_credentials";

    private VimeoClientConfiguration configuration;
    private VimeoService vimeoService;
    private Cache cache;
    private String currentCodeGrantState;
    private Account account;

    /** Dangerous interceptor that rewrites the server's cache-control header.
     * We are using this for now because our server sets all Cache-Control headers to no-store
     * [AH] 4/24/2015
     * */
    private static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor()
    {
        @Override
        public com.squareup.okhttp.Response intercept(Chain chain) throws IOException
        {
            com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());

            return originalResponse.newBuilder().header("Cache-Control", "public").build();
        }
    };

    private static VimeoClient sharedInstance;

    public static VimeoClient getInstance()
    {
        return sharedInstance;
    }

    public static void configure(VimeoClientConfiguration configuration)
    {
        sharedInstance = new VimeoClient(configuration);
    }

    private VimeoClient(final VimeoClientConfiguration configuration)
    {
        if (configuration == null) throw new AssertionError("Configuration cannot be null");

        this.configuration = configuration;

        final VimeoClient client = this;
        RequestInterceptor requestInterceptor = new RequestInterceptor()
        {
            @Override
            public void intercept(RequestFacade request)
            {
                request.addHeader("User-Agent", client.getUserAgent());
                request.addHeader("Accept", client.getAcceptHeader());
                request.addHeader("Authorization", client.getAuthHeader());
            }
        };

        OkHttpClient okHttpClient = new OkHttpClient();
        try
        {
            Integer cacheSize = 10 * 1024 * 1024; // TODO: this should be dynamic [AH]
            this.cache = new Cache(this.configuration.cacheDirectory, cacheSize);
            okHttpClient.setCache(cache);
        }
        catch (IOException e)
        {
            System.out.println("Exception when creating cache: " + e.getMessage());
        }

        okHttpClient.networkInterceptors().add(REWRITE_CACHE_CONTROL_INTERCEPTOR);

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(configuration.baseURLString)
                .setClient(new OkClient(okHttpClient))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setRequestInterceptor(requestInterceptor)
                .setConverter(new GsonConverter(gson))
                .build();

        this.vimeoService = restAdapter.create(VimeoService.class);
    }

    // region Authentication

    public String getCodeGrantAuthorizationURI()
    {
        currentCodeGrantState = UUID.randomUUID().toString();

        Map<String,String> map = new HashMap<String,String>();
        map.put("redirect_uri", this.configuration.codeGrantRedirectURI);
        map.put("response_type", CODE_GRANT_RESPONSE_TYPE);
        map.put("state", this.currentCodeGrantState);
        map.put("scope", this.configuration.scope);
        map.put("client_id", this.configuration.clientID);

        String uri = urlEncodeUTF8(map);

        // TODO: find a better way to build a URL and query string [AH]
        return this.configuration.baseURLString + CODE_GRANT_PATH + "?" + uri;
    }

    public void authenticateWithCodeGrant(String uri, final AuthCallback callback)
    {
        if (callback == null) throw new AssertionError("Callback cannot be null");

        if (uri == null)
        {
            callback.failure(new Error("Uri must not be null"));

            return;
        }

        // TODO: find a better way to do this [AH]
        String query = uri.split("\\?")[1];
        Map<String, String> queryMap = Splitter.on('&').trimResults().withKeyValueSeparator("=").split(query);
        String code = queryMap.get(CODE_GRANT_RESPONSE_TYPE);
        String state = queryMap.get(CODE_GRANT_STATE);

        if (code == null || state == null || !state.equals(this.currentCodeGrantState))
        {
            this.currentCodeGrantState = null;

            callback.failure(new Error("Code grant code is null or state has changed"));

            return;
        }

        this.currentCodeGrantState = null;

        String redirectURI = this.configuration.codeGrantRedirectURI;

        final VimeoClient client = this;
        this.vimeoService.authenticateWithCodeGrant(redirectURI, code, CODE_GRANT_TYPE, new Callback<Account>() {
            @Override
            public void success(Account account, Response response)
            {
                client.account = account;

                callback.success();
            }

            @Override
            public void failure(RetrofitError error)
            {
                callback.failure(new Error(error.toString()));
            }
        });
    }

    public void authorizeWithClientCredentialsGrant(final AuthCallback callback)
    {
        if (callback == null) throw new AssertionError("Callback cannot be null");

        final VimeoClient client = this;
        this.vimeoService.authorizeWithClientCredentialsGrant(CLIENT_CREDENTIALS_GRANT_TYPE, configuration.scope, new Callback<Account>()
        {
            @Override
            public void success(Account account, Response response)
            {
                client.account = account;

                callback.success();
            }

            @Override
            public void failure(RetrofitError error)
            {
                callback.failure(new Error(error.toString()));
            }
        });
    }

    public void join(String displayName, String email, String password, final AuthCallback callback)
    {
        if (callback == null) throw new AssertionError("Callback cannot be null");

        if (displayName == null || displayName.length() == 0 || email == null || email.length() == 0 || password == null || password.length() == 0)
        {
            callback.failure(new Error("displayName, email, password must be set"));

            return;
        }

        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("name", displayName);
        parameters.put("email", email);
        parameters.put("password", password);
        parameters.put("scope", configuration.scope);

        final VimeoClient client = this;
        this.vimeoService.join(parameters, new Callback<Account>()
        {
            @Override
            public void success(Account account, Response response)
            {
                client.account = account;

                callback.success();
            }

            @Override
            public void failure(RetrofitError error)
            {
                callback.failure(new Error(error.toString()));
            }
        });
    }

    public void logIn(String email, String password, final AuthCallback callback)
    {
        if (callback == null) throw new AssertionError("Callback cannot be null");

        if (email == null || email.length() == 0 || password == null || password.length() == 0)
        {
            callback.failure(new Error("email, password must be set"));

            return;
        }

        final VimeoClient client = this;
        this.vimeoService.logIn(email, password, PASSWORD_GRANT_TYPE, configuration.scope, new Callback<Account>()
        {
            @Override
            public void success(Account account, Response response)
            {
                client.account = account;

                callback.success();
            }

            @Override
            public void failure(RetrofitError error)
            {
                callback.failure(new Error(error.toString()));
            }
        });
    }

    public void logOut()
    {
        // TODO: We should set account to null immediately, rather than in the callback [AH]

        final VimeoClient client = this;
        this.vimeoService.logOut(new Callback<VideoList>()
        {
            @Override
            public void success(VideoList serverResponse, Response response)
            {
                client.account = null;
            }

            @Override
            public void failure(RetrofitError error)
            {
                client.account = null;
            }
        });
    }

    // end region

    // region Channels

    public void fetchStaffPicks(final Callback<VideoList> callback)
    {
        if (callback == null) throw new AssertionError("Callback cannot be null");

        this.vimeoService.fetchStaffPicks(callback);
    }

    // end region

    // region Generic

    public void fetchContent(String uri, CacheControl cacheControl, Callback callback)
    {
        if (callback == null) throw new AssertionError("Callback cannot be null");

        if (uri == null)
        {
            callback.failure(null); // TODO: create error here

            return;
        }

        String cacheHeaderValue = null;
        if (cacheControl != null)
        {
            cacheHeaderValue = cacheControl.toString();
        }

        this.vimeoService.fetchContent(uri, cacheHeaderValue, callback);
    }

    public void fetchCachedContent(String uri, Callback callback)
    {
        if (callback == null) throw new AssertionError("Callback cannot be null");

        if (uri == null)
        {
            callback.failure(null); // TODO: create error here

            return;
        }

        this.vimeoService.fetchContent(uri, CacheControl.FORCE_CACHE.toString(), callback);
    }

    public void fetchNetworkContent(String uri, Callback callback)
    {
        if (callback == null) throw new AssertionError("Callback cannot be null");

        if (uri == null)
        {
            callback.failure(null); // TODO: create error here

            return;
        }

        this.vimeoService.fetchContent(uri, CacheControl.FORCE_NETWORK.toString(), callback);
    }

    // end region

    // region HeaderValues

    public String getUserAgent()
    {
        return "sample_user_agent";
    }

    public String getAcceptHeader()
    {
        return "application/vnd.vimeo.*+json; version=" + this.configuration.APIVersionString;
    }

    public String getAuthHeader()
    {
        String credential = null;

        if (this.account != null && this.account.isAuthenticated())
        {
            credential = "Bearer " + this.account.getAccessToken();
        }
        else
        {
            credential = Credentials.basic(configuration.clientID, configuration.clientSecret);
        }

        return credential;
    }

    // end region

    // region Utilities

    // TODO: This is shitty, revisit [AH]

    static String urlEncodeUTF8(Map<String,String> map)
    {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String,String> entry : map.entrySet())
        {
            if (sb.length() > 0)
            {
                sb.append("&");
            }

            sb.append(String.format("%s=%s",
                    urlEncodeUTF8(entry.getKey()),
                    urlEncodeUTF8(entry.getValue())
            ));
        }

        return sb.toString();
    }

    static String urlEncodeUTF8(String s)
    {
        try
        {
            return URLEncoder.encode(s, "UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
            throw new UnsupportedOperationException(e);
        }
    }

    // end region

}
