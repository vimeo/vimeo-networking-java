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
import com.vimeo.networking.logging.DebugLogger;
import com.vimeo.networking.logging.DebugLoggerInterface;
import com.vimeo.networking.model.VimeoAccount;

import org.jetbrains.annotations.Nullable;

import java.io.File;

import okhttp3.Cache;

/**
 * The configuration object for making API calls with Retrofit.
 * <p/>
 * An instance of this class is used the initialize the {@link VimeoClient}.
 * <p/>
 * Created by alfredhanssen on 4/12/15.
 */
public class Configuration {

    private static final String DEFAULT_VERSION_STRING = "3.3";
    // Potentially set max_age to match the age of video expiration 10/7/15 [KV]
    private static final int DEFAULT_CACHE_MAX_AGE = 60 * 60 * 2; // Default to 2 hours
    // If implementing on Android, it will be cleared when space is needed automatically 1/27/16 [KV]
    private static final int DEFAULT_CACHE_SIZE = 10 * 1024 * 1024; // 10MB
    private static final int DEFAULT_TIMEOUT = 60; // seconds
    private static final String DEFAULT_USER_AGENT = "sample_user_agent";

    public String baseURLString;
    public String clientID;
    public String clientSecret;
    public String scope;

    @Nullable
    private AccountStore accountStore;
    public GsonDeserializer deserializer;

    public String apiVersionString;
    public String codeGrantRedirectURI;
    public String userAgentString;

    @Nullable
    public File cacheDirectory;
    public int cacheSize;

    public int cacheMaxAge; // in seconds
    public int timeout; // in seconds

    public boolean certPinningEnabled;
    public DebugLoggerInterface debugLogger;
    public LogLevel logLevel;

    /**
     * -----------------------------------------------------------------------------------------------------
     * Accessors
     * -----------------------------------------------------------------------------------------------------
     */
    // <editor-fold desc="Accessors">
    @Nullable
    public Cache getCache() {
        if (cacheDirectory == null) {
            return null;
        }
        return new Cache(cacheDirectory, cacheSize);
    }

    public void saveAccount(VimeoAccount account, String email, String password) {
        if (accountStore != null) {
            accountStore.saveAccount(account, email, password);
        }
    }

    public void deleteAccount(VimeoAccount account) {
        if (accountStore != null) {
            accountStore.deleteAccount(account);
        }
    }

    public VimeoAccount loadAccount() {
        if (accountStore == null) {
            return null;
        }
        return accountStore.loadAccount();
    }
    // </editor-fold>

    /**
     * -----------------------------------------------------------------------------------------------------
     * Builder
     * -----------------------------------------------------------------------------------------------------
     */
    // <editor-fold desc="Builder">
    private Configuration(Builder builder) {
        this.baseURLString = builder.baseURLString;
        this.clientID = builder.clientID;
        this.clientSecret = builder.clientSecret;
        this.scope = builder.scope;
        this.accountStore = builder.accountStore;
        this.deserializer = builder.deserializer;

        if (!this.isValid()) {
            throw new AssertionError("Built invalid VimeoClientConfiguration");
        }

        this.codeGrantRedirectURI = "vimeo" + clientID + "://auth";

        this.apiVersionString = builder.apiVersionString;
        this.cacheDirectory = builder.cacheDirectory;
        this.cacheSize = builder.cacheSize;
        this.cacheMaxAge = builder.cacheMaxAge;
        this.userAgentString = builder.userAgentString;

        this.timeout = builder.timeout;

        this.certPinningEnabled = builder.certPinningEnabled;
        this.debugLogger = builder.debugLogger;
        this.logLevel = builder.logLevel;
    }

    private boolean isValid() {
        return (this.baseURLString != null && !this.baseURLString.isEmpty() &&
                this.clientID != null && !this.clientID.isEmpty() &&
                this.clientSecret != null && !this.clientSecret.isEmpty() &&
                this.scope != null && !this.scope.isEmpty());
    }

    /**
     * Builder used to construct the Configuration
     */
    public static class Builder {

        private String baseURLString;
        private String clientID;
        private String clientSecret;
        private String scope;
        private AccountStore accountStore;
        private GsonDeserializer deserializer = new GsonDeserializer();

        private String apiVersionString = DEFAULT_VERSION_STRING;
        private File cacheDirectory;
        private int cacheSize = DEFAULT_CACHE_SIZE;
        private int cacheMaxAge = DEFAULT_CACHE_MAX_AGE;
        private String userAgentString = DEFAULT_USER_AGENT;
        public int timeout = DEFAULT_TIMEOUT;

        private boolean certPinningEnabled = true;
        // Default to the stock logger which just prints - this makes it optional
        public DebugLoggerInterface debugLogger = new DebugLogger();
        public LogLevel logLevel = LogLevel.DEBUG;

        public Builder(String baseURLString, String clientID, String clientSecret, String scope) {
            this(baseURLString, clientID, clientSecret, scope, null, null);
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
        public Builder(String baseURLString, String clientId, String clientSecret, String scope,
                       @Nullable AccountStore accountStore, @Nullable GsonDeserializer deserializer) {
            this.baseURLString = baseURLString;
            this.clientID = clientId;
            this.clientSecret = clientSecret;
            this.scope = scope;
            this.accountStore = accountStore;
            this.deserializer = deserializer;
        }

        public Builder setAccountStore(AccountStore accountStore) {
            this.accountStore = accountStore;
            return this;
        }

        public Builder setGsonDeserializer(GsonDeserializer deserializer) {
            this.deserializer = deserializer;
            return this;
        }

        public Builder setApiVersionString(String apiVersionString) {
            this.apiVersionString = apiVersionString;
            return this;
        }

        public Builder setCacheDirectory(File cacheDirectory) {
            this.cacheDirectory = cacheDirectory;
            return this;
        }

        public Builder setCacheSize(int cacheSize) {
            this.cacheSize = cacheSize;
            return this;
        }

        public Builder setCacheMaxAge(int cacheMaxAge) {
            this.cacheMaxAge = cacheMaxAge;
            return this;
        }

        public Builder setUserAgentString(String userAgentString) {
            this.userAgentString = userAgentString;
            return this;
        }

        public Builder setTimeout(int timeout) {
            this.timeout = timeout;
            return this;
        }

        public Builder enableCertPinning(boolean enabled) {
            this.certPinningEnabled = enabled;
            return this;
        }

        public Builder setDebugLogger(DebugLoggerInterface debugLogger) {
            this.debugLogger = debugLogger;
            return this;
        }

        public Builder setLogLevel(LogLevel level) {
            this.logLevel = level;
            return this;
        }

        public Configuration build() {
            return new Configuration(this);
        }
    }
    // </editor-fold>
}
