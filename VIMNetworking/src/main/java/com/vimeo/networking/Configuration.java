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

import com.vimeo.networking.logging.DebugLogger;
import com.vimeo.networking.logging.DebugLoggerInterface;

import java.io.File;

/**
 * The configuration object for making API calls with Retrofit.
 * <p/>
 * An instance of this class is used the initialize the {@link VimeoClient}.
 * <p/>
 * Created by alfredhanssen on 4/12/15.
 */
public class Configuration {

    private static final String DEFAULT_VERSION_STRING = "3.3";
    // TODO: this cache length should be set from appconfig to match video file invalidation 10/7/15 [KV]
    private static final int DEFAULT_CACHE_MAX_AGE = 60 * 60 * 2; // Default to 2 hours
    private static final int DEFAULT_TIMEOUT = 60; // seconds
    private static final String DEFAULT_USER_AGENT = "sample_user_agent";

    public String baseURLString;
    public String clientID;
    public String clientSecret;
    public String scope;
    public AccountStore accountStore;
    public GsonDeserializer deserializer;

    public String apiVersionString;
    public String codeGrantRedirectURI;
    public File cacheDirectory;
    public int cacheSize;
    public int cacheMaxAge; // in seconds
    public String userAgentString;

    public int timeout; // in seconds

    public boolean certPinningEnabled;
    public DebugLoggerInterface debugLogger;

    private boolean isValid() {
        return (this.baseURLString != null && !this.baseURLString.isEmpty() &&
                this.clientID != null && !this.clientID.isEmpty() &&
                this.clientSecret != null && !this.clientSecret.isEmpty() &&
                this.scope != null && !this.scope.isEmpty() &&
                this.accountStore != null);
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
        private GsonDeserializer deserializer;

        private String apiVersionString = DEFAULT_VERSION_STRING;
        private File cacheDirectory;
        private int cacheSize;
        private int cacheMaxAge = DEFAULT_CACHE_MAX_AGE;
        private String userAgentString = DEFAULT_USER_AGENT;
        public int timeout = DEFAULT_TIMEOUT;

        private boolean certPinningEnabled = true;
        // Default to the stock logger which just prints - this makes it optional
        public DebugLoggerInterface debugLogger = new DebugLogger();

        // TODO: make accountstore optional, deserializer comment
        public Builder(String baseURLString, String clientID, String clientSecret, String scope,
                       AccountStore accountStore, GsonDeserializer deserializer) {
            this.baseURLString = baseURLString;
            this.clientID = clientID;
            this.clientSecret = clientSecret;
            this.scope = scope;
            this.accountStore = accountStore;
            this.deserializer = deserializer;
        }

        public Builder setApiVersionString(String APIVersionString) {
            this.apiVersionString = APIVersionString;
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

        public Configuration build() throws Exception {
            return new Configuration(this);
        }
    }

    private Configuration(Builder builder) throws Exception {
        this.baseURLString = builder.baseURLString;
        this.clientID = builder.clientID;
        this.clientSecret = builder.clientSecret;
        this.scope = builder.scope;
        this.accountStore = builder.accountStore;
        this.deserializer = builder.deserializer;

        if (!this.isValid()) {
            throw new Exception("Built invalid VimeoClientConfiguration");
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
    }
}
