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

import com.vimeo.networking.Vimeo.LogLevel;
import com.vimeo.networking.logging.LogProvider;
import com.vimeo.networking.model.VimeoAccount;

import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Cache;
import okhttp3.Interceptor;

/**
 * The configuration object for making API calls with Retrofit.
 * <p/>
 * An instance of this class is used the initialize the {@link VimeoClient}.
 * <p/>
 * Created by alfredhanssen on 4/12/15.
 */
@SuppressWarnings("unused")
public class Configuration {

    private static final String DEFAULT_VERSION_STRING = "3.2";
    // Potentially set max_age to match the age of video expiration 10/7/15 [KV]
    private static final int DEFAULT_CACHE_MAX_AGE = 60 * 60 * 2; // Default to 2 hours
    // If implementing on Android, it will be cleared when space is needed automatically 1/27/16 [KV]
    private static final int DEFAULT_CACHE_SIZE = 10 * 1024 * 1024; // 10MB
    private static final int DEFAULT_TIMEOUT = 60; // seconds
    private static final String DEFAULT_USER_AGENT = "sample_user_agent";

    protected String mBaseURLString;
    protected String mClientID;
    protected String mClientSecret;
    protected String mScope;

    protected String mAccessToken;

    @Nullable
    private AccountStore mAccountStore;
    protected GsonDeserializer mGsonDeserializer;

    protected final List<Interceptor> mNetworkInterceptors = new ArrayList<>();
    protected final List<Interceptor> mInterceptors = new ArrayList<>();

    protected String mApiVersionString;
    protected String mCodeGrantRedirectURI;
    protected String mUserAgentString;

    @Nullable
    protected File mCacheDirectory;
    protected int mCacheSize;

    protected int mCacheMaxAge; // in seconds
    protected int mTimeout; // in seconds

    protected boolean mCertPinningEnabled;
    @Nullable
    protected LogProvider mLogProvider;
    protected LogLevel mLogLevel;

    /**
     * -----------------------------------------------------------------------------------------------------
     * Accessors
     * -----------------------------------------------------------------------------------------------------
     */
    // <editor-fold desc="Accessors">
    public String getClientID() {
        return mClientID;
    }

    public String getClientSecret() {
        return mClientSecret;
    }

    public String getScope() {
        return mScope;
    }

    public String getAccessToken() {
        return mAccessToken;
    }

    @Nullable
    public AccountStore getAccountStore() {
        return mAccountStore;
    }

    public GsonDeserializer getGsonDeserializer() {
        return mGsonDeserializer;
    }

    public List<Interceptor> getNetworkInterceptors() {
        return mNetworkInterceptors;
    }

    public List<Interceptor> getInterceptors() {
        return mInterceptors;
    }

    public String getApiVersionString() {
        return mApiVersionString;
    }

    public String getCodeGrantRedirectURI() {
        return mCodeGrantRedirectURI;
    }

    public String getUserAgentString() {
        return mUserAgentString;
    }

    @Nullable
    public File getCacheDirectory() {
        return mCacheDirectory;
    }

    public int getCacheSize() {
        return mCacheSize;
    }

    public int getCacheMaxAge() {
        return mCacheMaxAge;
    }

    public int getTimeout() {
        return mTimeout;
    }

    public boolean isCertPinningEnabled() {
        return mCertPinningEnabled;
    }

    @Nullable
    public LogProvider getLogProvider() {
        return mLogProvider;
    }

    public LogLevel getLogLevel() {
        return mLogLevel;
    }

    public String getBaseURLString() {
        return mBaseURLString;
    }

    @Nullable
    public Cache getCache() {
        if (mCacheDirectory == null) {
            return null;
        }
        return new Cache(mCacheDirectory, mCacheSize);
    }

    /**
     * @deprecated use {@link #saveAccount(VimeoAccount, String)} instead
     * <p/>
     * We find no use in storing the password when you can persist the {@link VimeoAccount} across
     * application sessions.
     */
    @Deprecated
    public void saveAccount(VimeoAccount account, String email, String password) {
        saveAccount(account, email);
    }

    public void saveAccount(VimeoAccount account, String email) {
        if (mAccountStore != null) {
            mAccountStore.saveAccount(account, email);
        }
    }

    public void deleteAccount(VimeoAccount account) {
        if (mAccountStore != null) {
            mAccountStore.deleteAccount(account);
        }
    }

    @Nullable
    public VimeoAccount loadAccount() {
        if (mAccountStore == null) {
            return null;
        }
        return mAccountStore.loadAccount();
    }
    // </editor-fold>

    // -----------------------------------------------------------------------------------------------------
    // Setters
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Setters">

    public void setBaseURLString(String baseURLString) {
        mBaseURLString = baseURLString;
    }

    public void setCertPinningEnabled(boolean certPinningEnabled) {
        mCertPinningEnabled = certPinningEnabled;
    }

    // </editor-fold>

    /**
     * -----------------------------------------------------------------------------------------------------
     * Builder
     * -----------------------------------------------------------------------------------------------------
     */
    // <editor-fold desc="Builder">
    private Configuration(Builder builder) {
        this.mBaseURLString = builder.mBaseURLString;
        this.mClientID = builder.mClientID;
        this.mClientSecret = builder.mClientSecret;
        this.mScope = builder.mScope;

        this.mAccessToken = builder.mAccessToken;

        this.mAccountStore = builder.mAccountStore;
        this.mGsonDeserializer = builder.mDeserializer;

        if (!this.isValid()) {
            throw new AssertionError("Built invalid VimeoClientConfiguration");
        }

        this.mCodeGrantRedirectURI = builder.mCodeGrantRedirectUri;

        this.mApiVersionString = builder.mApiVersionString;
        this.mCacheDirectory = builder.mCacheDirectory;
        this.mCacheSize = builder.mCacheSize;
        this.mCacheMaxAge = builder.mCacheMaxAge;
        this.mUserAgentString = builder.mUserAgentString;

        this.mTimeout = builder.mTimeout;

        this.mCertPinningEnabled = builder.mCertPinningEnabled;
        this.mLogProvider = builder.mLogProvider;
        this.mLogLevel = builder.mLogLevel;
        this.mNetworkInterceptors.addAll(builder.mNetworkInterceptors);
        this.mInterceptors.addAll(builder.mInterceptors);
    }

    private boolean isValid() {
        return this.mBaseURLString != null && (!this.mBaseURLString.trim().isEmpty() &&
                                               this.mClientID != null && !this.mClientID.trim().isEmpty() &&
                                               this.mClientSecret != null &&
                                               !this.mClientSecret.trim().isEmpty() &&
                                               this.mScope != null && !this.mScope.trim().isEmpty()) ||
               (this.mAccessToken != null && !this.mAccessToken.trim().isEmpty());
    }

    /**
     * Builder used to construct the Configuration
     */
    public static class Builder {

        private String mBaseURLString = Vimeo.VIMEO_BASE_URL_STRING;
        private String mClientID;
        private String mClientSecret;
        private String mScope;

        private String mAccessToken;

        private AccountStore mAccountStore;
        private GsonDeserializer mDeserializer = new GsonDeserializer();

        private String mApiVersionString = DEFAULT_VERSION_STRING;
        private File mCacheDirectory;
        private int mCacheSize = DEFAULT_CACHE_SIZE;
        private int mCacheMaxAge = DEFAULT_CACHE_MAX_AGE;
        private String mUserAgentString = DEFAULT_USER_AGENT;
        public int mTimeout = DEFAULT_TIMEOUT;

        private final List<Interceptor> mNetworkInterceptors = new ArrayList<>();
        private final List<Interceptor> mInterceptors = new ArrayList<>();

        public String mCodeGrantRedirectUri = "vimeo" + mClientID + "://auth";

        private boolean mCertPinningEnabled = true;
        // Default to the stock logger which just prints - this makes it optional
        @Nullable
        public LogProvider mLogProvider;
        public LogLevel mLogLevel = LogLevel.DEBUG;

        /**
         * The most basic builder constructor. If you've only provided an access token, you'll only be able to
         * make requests for the given access token's account.
         * <p/>
         * If you'd like the ability to switch accounts or request a client credentials grant, you'll have to set a client id and client secret.
         * If you'd like the ability to persist accounts, you'll have to set an account store.
         * If you'd like the ability to issue code grant, you'll have to set a code grant redirect uri.
         *
         * @param accessToken Token provided by the Vimeo developer console
         */
        public Builder(String accessToken) {
            this.mAccessToken = accessToken;
        }

        public Builder(String clientID, String clientSecret, String scope) {
            this(null, clientID, clientSecret, scope, null, null);
        }

        @Deprecated
        public Builder(String baseURLString, String clientID, String clientSecret, String scope) {
            this(baseURLString, clientID, clientSecret, scope, null, null);
        }

        public Builder(String clientId, String clientSecret, String scope,
                       @Nullable AccountStore accountStore, @Nullable GsonDeserializer deserializer) {
            this(null, clientId, clientSecret, scope, accountStore, deserializer);
        }

        /**
         * The constructor for the Configuration Builder. Only the last two arguments are optional but it is
         * highly recommended that you pass in a deserializer since, without one, deserialization will occur
         * on the main thread (which can be a lengthy operation)
         *
         * @param baseURLString The base url pointing to the Vimeo api. Something like: {@link Vimeo#VIMEO_BASE_URL_STRING}
         * @param clientId      The client id provided to you from <a href="https://developer.vimeo.com/apps/">the developer console</a>
         * @param clientSecret  The client secret provided to you from <a href="https://developer.vimeo.com/apps/">the developer console</a>
         * @param scope         Space separated list of <a href="https://developer.vimeo.com/api/authentication#scopes">scopes</a>
         *                      <p/>
         *                      Example: "private public create"
         * @param accountStore  (Optional, Recommended) An implementation that can be used to interface with Androids <a href="http://developer.android.com/reference/android/accounts/AccountManager.html">Account Manager</a>
         * @param deserializer  (Optional, Recommended) Extend GsonDeserializer to allow for deserialization on a background thread
         */
        @Deprecated
        public Builder(@Nullable String baseURLString, String clientId, String clientSecret, String scope,
                       @Nullable AccountStore accountStore, @Nullable GsonDeserializer deserializer) {
            this.mBaseURLString = baseURLString == null ? this.mBaseURLString : baseURLString;
            this.mClientID = clientId;
            this.mClientSecret = clientSecret;
            this.mScope = scope;
            this.mAccountStore = accountStore;
            this.mDeserializer = deserializer;
        }

        public Builder setBaseUrl(String baseUrl) {
            this.mBaseURLString = baseUrl;
            return this;
        }

        /** If you used the basic Builder access token constructor but have the intent of */
        public Builder setClientIdAndSecret(String clientId, String clientSecret) {
            this.mClientID = clientId;
            this.mClientSecret = clientSecret;
            return this;
        }

        public Builder setAccountStore(AccountStore accountStore) {
            this.mAccountStore = accountStore;
            return this;
        }

        public Builder setGsonDeserializer(GsonDeserializer deserializer) {
            this.mDeserializer = deserializer;
            return this;
        }

        public Builder setAccessToken(String accessToken) {
            this.mAccessToken = accessToken;
            return this;
        }

        public Builder setCodeGrantRedirectUri(String redirectUri) {
            this.mCodeGrantRedirectUri = redirectUri;
            return this;
        }

        public Builder setApiVersionString(String apiVersionString) {
            this.mApiVersionString = apiVersionString;
            return this;
        }

        public Builder setCacheDirectory(File cacheDirectory) {
            this.mCacheDirectory = cacheDirectory;
            return this;
        }

        public Builder setCacheSize(int cacheSize) {
            this.mCacheSize = cacheSize;
            return this;
        }

        public Builder setCacheMaxAge(int cacheMaxAge) {
            this.mCacheMaxAge = cacheMaxAge;
            return this;
        }

        public Builder setUserAgentString(String userAgentString) {
            this.mUserAgentString = userAgentString;
            return this;
        }

        public Builder setTimeout(int timeout) {
            this.mTimeout = timeout;
            return this;
        }

        public Builder enableCertPinning(boolean enabled) {
            this.mCertPinningEnabled = enabled;
            return this;
        }

        public Builder setDebugLogger(LogProvider logger) {
            this.mLogProvider = logger;
            return this;
        }

        public Builder setLogLevel(LogLevel level) {
            this.mLogLevel = level;
            return this;
        }

        public Builder addNetworkInterceptor(Interceptor interceptor) {
            if (interceptor != null) {
                this.mNetworkInterceptors.add(interceptor);
            }
            return this;
        }

        public Builder addNetworkInterceptors(List<Interceptor> interceptors) {
            if (interceptors != null) {
                this.mNetworkInterceptors.addAll(interceptors);
            }
            return this;
        }

        public Builder addInterceptor(Interceptor interceptor) {
            if (interceptor != null) {
                this.mInterceptors.add(interceptor);
            }
            return this;
        }

        public Builder addInterceptors(List<Interceptor> interceptors) {
            if (interceptors != null) {
                this.mInterceptors.addAll(interceptors);
            }
            return this;
        }

        public Configuration build() {
            return new Configuration(this);
        }
    }
    // </editor-fold>
}
