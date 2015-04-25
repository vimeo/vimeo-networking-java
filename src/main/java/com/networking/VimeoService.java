package com.networking;

import java.util.HashMap;

import model.Account;
import model.UserList;
import model.VideoList;
import model.User;
import model.Video;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Headers;
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

    @FormUrlEncoded
    @POST("/oauth/authorize/client")
    void authorizeWithClientCredentialsGrant(
            @Field("grant_type") String grantType,
            @Field("scope") String scope,
            Callback<Account> callback
    );

    @POST("/users")
    void join(
            @Body HashMap<String, String> parameters,
            Callback<Account> callback
    );

    @FormUrlEncoded
    @POST("/oauth/authorize/password")
    void logIn(
            @Field("username") String email,
            @Field("password") String password,
            @Field("grant_type") String grantType,
            @Field("scope") String scope,
            Callback<Account> callback
    );

    @Headers("Cache-Control: no-cache, no-store")
    @DELETE("/tokens")
    void logOut(
            Callback<VideoList> callback
    );

    // region Channels

    @GET("/channels/staffpicks/videos")
    void fetchStaffPicks(
            Callback<VideoList> callback
    );

    // end region

    // region Search

    @GET("/videos")
    void searchVideos(
            @Query("query") String query,
            Callback callback
    );

    @GET("/users")
    void searchUsers(
            @Query("query") String query,
            Callback callback
    );

    // end region

    // region Generic

    @GET("/{uri}")
    void fetchContent(
            @Path(value="uri", encode=false) String uri,
            @Header("Cache-Control") String cacheHeaderValue,
            Callback callback
    );

    // end region
}
