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
                   Callback<Object> callback);

    @PATCH("/{uri}")
    void editUser(@Header("Authorization") String authHeader, @Path(value = "uri", encode = false) String uri,
                  @Body HashMap<String, Object> parameters, Callback<Object> callback);

    @POST("/{uri}")
    void comment(@Header("Authorization") String authHeader, @Path(value = "uri", encode = false) String uri,
                 @QueryMap Map<String, String> options, @Body HashMap<String, String> parameters,
                 Callback<Object> callback);

    // end region

    // region Generic

    @PUT("/{uri}")
    void PUT(@Header("Authorization") String authHeader, @Path(value = "uri", encode = false) String uri,
             @QueryMap Map<String, String> options, Callback<Object> callback);

    @DELETE("/{uri}")
    void DELETE(@Header("Authorization") String authHeader, @Path(value = "uri", encode = false) String uri,
                @QueryMap Map<String, String> options, Callback<Object> callback);

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
