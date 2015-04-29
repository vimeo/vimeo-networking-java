package com.networking;

import java.io.File;

/**
 * Created by alfredhanssen on 4/12/15.
 */
public class Configuration
{
    private static final String DEFAULT_VERSION_STRING = "3.2";

    public String baseURLString;
    public String clientID;
    public String clientSecret;
    public String scope;
    public AccountStore accountStore;

    public String APIVersionString;
    public String codeGrantRedirectURI;
    public File cacheDirectory;
    public String userAgentString;

    private Boolean isValid()
    {
        return (this.baseURLString != null && this.baseURLString.length() != 0 &&
                this.clientID != null && this.clientID.length() != 0 &&
                this.clientSecret != null && this.clientSecret.length() != 0 &&
                this.scope != null && this.scope.length() != 0 &&
                this.accountStore != null);
    }

    public static class Builder
    {
        private String baseURLString;
        private String clientID;
        private String clientSecret;
        private String scope;
        private AccountStore accountStore;

        private String APIVersionString = DEFAULT_VERSION_STRING;
        private File cacheDirectory;
        private String userAgentString;

        public Builder(String baseURLString, String clientID, String clientSecret, String scope, AccountStore accountStore)
        {
            this.baseURLString = baseURLString;
            this.clientID = clientID;
            this.clientSecret = clientSecret;
            this.scope = scope;
            this.accountStore = accountStore;
        }

        public Builder APIVersionString(String APIVersionString)
        {
            this.APIVersionString = APIVersionString;
            return this;
        }

        public Builder cacheDirectory(File cacheDirectory)
        {
            this.cacheDirectory = cacheDirectory;
            return this;
        }

        public Builder userAgentString(String userAgentString)
        {
            this.userAgentString = userAgentString;
            return this;
        }

        public Configuration build() throws Exception
        {
            return new Configuration(this);
        }
    }

    private Configuration(Builder builder) throws Exception
    {
        this.baseURLString = builder.baseURLString;
        this.clientID = builder.clientID;
        this.clientSecret = builder.clientSecret;
        this.scope = builder.scope;
        this.accountStore = builder.accountStore;

        if (!this.isValid()) throw new Exception("Built invalid VimeoClientConfiguration");

        this.codeGrantRedirectURI = "vimeo" + clientID + "://auth";

        this.APIVersionString = builder.APIVersionString;
        this.cacheDirectory = builder.cacheDirectory;
        this.userAgentString = builder.userAgentString;
    }
}
