package com.vimeo.networking;

import java.util.HashMap;

import com.vimeo.networking.model.Account;
import com.vimeo.networking.model.UserList;
import com.vimeo.networking.model.VideoList;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.PATCH;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by alfredhanssen on 4/12/15.
 */
public interface VimeoService {

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

    // Synchronous version to be used with Android AccountAuthenticator [AH]
    @FormUrlEncoded
    @POST("/oauth/authorize/password")
    Account logIn(
            @Field("username") String email,
            @Field("password") String password,
            @Field("grant_type") String grantType,
            @Field("scope") String scope
                 );

    @Headers("Cache-Control: no-cache, no-store")
    @DELETE("/tokens")
    void logOut(
            Callback<Object> callback
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
            Callback<VideoList> callback
                     );

    @GET("/users")
    void searchUsers(
            @Query("query") String query,
            Callback<UserList> callback
                    );

    // end region

    // region Editing

    @PATCH("/{uri}")
    void editVideo(
            @Path(value = "uri", encode = false) String uri,
            @Body HashMap<String, Object> parameters,
            Callback<Object> callback
                  );

    @PATCH("/{uri}")
    void editUser(
            @Path(value = "uri", encode = false) String uri,
            @Body HashMap<String, Object> parameters,
            Callback<Object> callback
                 );

    // end region

    // region Generic

    @PUT("/{uri}")
    void PUT(
            @Path(value = "uri", encode = false) String uri,
            Callback<Object> callback
            );

    @DELETE("/{uri}")
    void DELETE(
            @Path(value = "uri", encode = false) String uri,
            Callback<Object> callback
               );

    @GET("/{uri}")
    void GET(
            @Path(value = "uri", encode = false) String uri,
            @Header("Cache-Control") String cacheHeaderValue,
            Callback<Object> callback
            );

    // end region
}
