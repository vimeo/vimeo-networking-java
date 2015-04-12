package com.networking;

import model.Account;
import model.ServerResponse;
import model.User;
import model.Video;

import retrofit.Callback;
import retrofit.http.DELETE;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by alfredhanssen on 4/12/15.
 */
public interface VimeoService
{
    @FormUrlEncoded
    @POST("/oauth/access_token")
    void authenticateWithCodeGrant(
            @Field("redirect_uri") String redirectURI,
            @Field("code") String code,
            @Field("grant_type") String grantType,
            Callback<Account> callback
    );

    @POST("/oauth/authorize/client")
    void authorizeWithClientCredentialsGrant(
            @Query("grant_type") String grantType,
            @Query("scope") String scope,
            Callback<Account> callback
    );

    @POST("/oauth/authorize/password")
    void logIn(
            @Query("username") String email,
            @Query("password") String password,
            @Query("grant_type") String grantType,
            @Query("client_secret") String clientSecret,
            @Query("scope") String scope,
            Callback<Account> callback
    );

    @DELETE("/tokens")
    void logOut(
            Callback<ServerResponse> callback
    );

    @POST("/users")
    void join(
            @Query("display_name") String displayName,
            @Query("username") String email,
            @Query("password") String password,
            @Query("client_id") String clientID,
            @Query("client_secret") String clientSecret,
            @Query("scope") String scope,
            Callback<Account> callback
    );

    @GET("{uri}")
    void fetchVideos(
            @Path("uri") String uri,
            Callback<ServerResponse> callback
    );

    @GET("{uri}")
    void fetchVideo(
            @Path("uri") String uri,
            Callback<Video> callback
    );

    @GET("{uri}")
    void fetchUsers(
            @Path("uri") String uri,
            Callback<ServerResponse> callback
    );

    @GET("{uri}")
    void fetchUser(
            @Path("uri") String uri,
            Callback<User> callback
    );
}
