package com.vimeo.networking.model;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by alfredhanssen on 4/12/15.
 */
public class Account {
    //    private static final String TOKEN_TYPE_BEARER = "bearer";

    private String accessToken;
    private String tokenType;
    private String scope;
    private User user;
    private String userJSON;

    public Account() {

    }

    public Account(String accessToken, String tokenType, String scope, String userJSON) {
        if (accessToken == null || accessToken.length() == 0 || tokenType == null ||
            tokenType.length() == 0 || scope == null || scope.length() == 0) {
            throw new AssertionError("Account can only be created with token, tokenType, scope");
        }

        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.scope = scope;

        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                                     .create();

        this.user = gson.fromJson(userJSON, User.class);
    }

    public boolean isAuthenticated() {
        return (this.accessToken != null && this.accessToken.length() != 0);
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public String getTokenType() {
        return this.tokenType;
    }

    public String getScope() {
        return this.scope;
    }

    public User getUser() {
        return this.user;
    }

    public String getUserJSON() // For AccountManager.userData [AH]
    {
        if (this.user == null) {
            return null;
        }

        if (this.userJSON != null) {
            return this.userJSON;
        }

        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                                     .create();

        this.userJSON = gson.toJson(this.user);

        return this.userJSON;
    }
}
