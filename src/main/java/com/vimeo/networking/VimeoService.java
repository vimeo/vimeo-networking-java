package com.vimeo.networking;

import com.vimeo.networking.model.Account;

import java.util.HashMap;
import java.util.Map;

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
import retrofit.http.QueryMap;

/**
 * Interface of available API calls that can be made using Retrofit.
 * <p/>
 * Created by alfredhanssen on 4/12/15.
 */
public interface VimeoService {

    @FormUrlEncoded
    @POST("/oauth/access_token")
    void authenticateWithCodeGrant(@Header("Authorization") String authHeader,
                                   @Field("redirect_uri") String redirectURI, @Field("code") String code,
                                   @Field("grant_type") String grantType, Callback<Account> callback);

    @FormUrlEncoded
    @POST("/oauth/authorize/client")
    void authorizeWithClientCredentialsGrant(@Header("Authorization") String authHeader,
                                             @Field("grant_type") String grantType,
                                             @Field("scope") String scope, Callback<Account> callback);

    @POST("/users")
    void join(@Header("Authorization") String authHeader, @Body HashMap<String, String> parameters,
              Callback<Account> callback);

    @FormUrlEncoded
    @POST("/oauth/authorize/password")
    void logIn(@Header("Authorization") String authHeader, @Field("username") String email,
               @Field("password") String password, @Field("grant_type") String grantType,
               @Field("scope") String scope, Callback<Account> callback);

    // Synchronous version to be used with Android AccountAuthenticator [AH]
    @FormUrlEncoded
    @POST("/oauth/authorize/password")
    Account logIn(@Header("Authorization") String authHeader, @Field("username") String email,
                  @Field("password") String password, @Field("grant_type") String grantType,
                  @Field("scope") String scope);

    @FormUrlEncoded
    @POST("/oauth/authorize/facebook")
    void logInWithFacebook(@Header("Authorization") String authHeader, @Field("grant_type") String grantType,
                           @Field("token") String token, @Field("scope") String scope,
                           Callback<Account> callback);

    @Headers("Cache-Control: no-cache, no-store")
    @DELETE("/tokens")
    void logOut(@Header("Authorization") String authHeader, Callback<Object> callback);
   
    // region Editing

    @PATCH("/{uri}")
    void editVideo(@Header("Authorization") String authHeader,
                   @Path(value = "uri", encode = false) String uri, @Body HashMap<String, Object> parameters,
                   Callback callback);

    @PATCH("/{uri}")
    void editUser(@Header("Authorization") String authHeader, @Path(value = "uri", encode = false) String uri,
                  @Body HashMap<String, Object> parameters, Callback callback);

    @POST("/{uri}")
    void comment(@Path(value = "uri", encode = false) String uri, @Body String text, Callback callback);

    // end region

    // region Generic

    @PUT("/{uri}")
    void PUT(@Header("Authorization") String authHeader, @Path(value = "uri", encode = false) String uri,
             Callback<Object> callback);

    @DELETE("/{uri}")
    void DELETE(@Header("Authorization") String authHeader, @Path(value = "uri", encode = false) String uri,
                Callback<Object> callback);

    @GET("/{uri}")
    void GET(@Header("Authorization") String authHeader, @Path(value = "uri", encode = false) String uri,
             @QueryMap Map<String, String> options, @Header("Cache-Control") String cacheHeaderValue,
             Callback<Object> callback);

    @POST("/{uri}")
    void POST(@Header("Authorization") String authHeader, @Path(value = "uri", encode = false) String uri,
              @Header("Cache-Control") String cacheHeaderValue, @Body HashMap<String, String> parameters,
              Callback<Object> callback);

    // end region
}
