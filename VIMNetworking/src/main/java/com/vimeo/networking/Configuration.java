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

import java.io.File;

/**
 * The configuration object for making API call with Retrofit.
 * <p/>
 * An instance of this class is used the initialize the {@link VimeoClient}.
 * <p/>
 * Created by alfredhanssen on 4/12/15.
 */
public class Configuration {

    private static final String DEFAULT_VERSION_STRING = "3.3";
    private static final int DEFAULT_CACHE_MAX_AGE = 60 * 60; // Default to 60 minutes

    public String baseURLString;
    public String clientID;
    public String clientSecret;
    public String scope;
    public AccountStore accountStore;
    public GsonDeserializer deserializer;

    public String APIVersionString;
    public String codeGrantRedirectURI;
    public File cacheDirectory;
    public int cacheSize;
    public int cacheMaxAge; // in seconds
    public String userAgentString;

    public boolean certPinningEnabled;

    private Boolean isValid() {
        return (this.baseURLString != null && this.baseURLString.length() != 0 &&
                this.clientID != null && this.clientID.length() != 0 &&
                this.clientSecret != null && this.clientSecret.length() != 0 &&
                this.scope != null && this.scope.length() != 0 &&
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

        private String APIVersionString = DEFAULT_VERSION_STRING;
        private File cacheDirectory;
        private int cacheSize;
        private int cacheMaxAge = DEFAULT_CACHE_MAX_AGE;
        private String userAgentString;

        private boolean certPinningEnabled = true;

        public Builder(String baseURLString, String clientID, String clientSecret, String scope,
                       AccountStore accountStore, GsonDeserializer deserializer) {
            this.baseURLString = baseURLString;
            this.clientID = clientID;
            this.clientSecret = clientSecret;
            this.scope = scope;
            this.accountStore = accountStore;
            this.deserializer = deserializer;
        }

        public Builder APIVersionString(String APIVersionString) {
            this.APIVersionString = APIVersionString;
            return this;
        }

        public Builder cacheDirectory(File cacheDirectory) {
            this.cacheDirectory = cacheDirectory;
            return this;
        }

        public Builder cacheSize(int cacheSize) {
            this.cacheSize = cacheSize;
            return this;
        }

        public Builder cacheMaxAge(int cacheMaxAge) {
            this.cacheMaxAge = cacheMaxAge;
            return this;
        }

        public Builder userAgentString(String userAgentString) {
            this.userAgentString = userAgentString;
            return this;
        }

        public Builder enableCertPinning(boolean enabled) {
            this.certPinningEnabled = enabled;
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

        this.APIVersionString = builder.APIVersionString;
        this.cacheDirectory = builder.cacheDirectory;
        this.cacheSize = builder.cacheSize;
        this.cacheMaxAge = builder.cacheMaxAge;
        this.userAgentString = builder.userAgentString;

        this.certPinningEnabled = builder.certPinningEnabled;
    }
}
