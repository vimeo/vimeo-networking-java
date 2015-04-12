package model;

import model.User;

/**
 * Created by alfredhanssen on 4/12/15.
 */
public class Account
{
    //    private static final String TOKEN_TYPE_BEARER = "bearer";

    private String accessToken;
    private String tokenType;
    private String scope;
    private User user;

    public Boolean isAuthenticated()
    {
        return (accessToken != null && accessToken.length() != 0);
    }

    public User getUser()
    {
        return user;
    }

    public String getAccessToken()
    {
        return accessToken;
    }
}
