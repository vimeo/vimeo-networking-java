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

import retrofit.Call;
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
    @POST("oauth/access_token")
    Call<Account> authenticateWithCodeGrant(@Header("Authorization") String authHeader,
                                            @Field("redirect_uri") String redirectURI,
                                            @Field("code") String code,
                                            @Field("grant_type") String grantType);

    @FormUrlEncoded
    @POST("oauth/authorize/client")
    Call<Account> authorizeWithClientCredentialsGrant(@Header("Authorization") String authHeader,
                                                      @Field("grant_type") String grantType,
                                                      @Field("scope") String scope);

    @POST("users")
    Call<Account> join(@Header("Authorization") String authHeader, @Body HashMap<String, String> parameters);

    @FormUrlEncoded
    @POST("oauth/authorize/password")
    Call<Account> logIn(@Header("Authorization") String authHeader, @Field("username") String email,
                        @Field("password") String password, @Field("grant_type") String grantType,
                        @Field("scope") String scope);

    @FormUrlEncoded
    @POST("oauth/authorize/facebook")
    Call<Account> logInWithFacebook(@Header("Authorization") String authHeader,
                                    @Field("grant_type") String grantType, @Field("token") String token,
                                    @Field("scope") String scope);

    @Headers("Cache-Control: no-cache, no-store")
    @DELETE("tokens")
    Call<Object> logOut(@Header("Authorization") String authHeader);

    // region Editing

    @PATCH("{uri}")
    Call<Object> edit(@Header("Authorization") String authHeader,
                      @Path(value = "uri", encoded = true) String uri,
                      @Body HashMap<String, Object> parameters);

    @POST("{uri}")
    Call<Object> comment(@Header("Authorization") String authHeader,
                         @Path(value = "uri", encoded = true) String uri,
                         @QueryMap Map<String, String> options, @Body HashMap<String, String> parameters);

    // end region

    @POST("{uri}")
    Call<Object> createPictureResource(@Header("Authorization") String authHeader,
                                       @Path(value = "uri", encoded = false) String uri,
                                       @Body String emptyBodyack);

    // region Generic

    @PUT("{uri}")
    Call<Object> PUT(@Header("Authorization") String authHeader,
                     @Path(value = "uri", encoded = true) String uri, @QueryMap Map<String, String> options);

    @DELETE("{uri}")
    Call<Object> DELETE(@Header("Authorization") String authHeader,
                        @Path(value = "uri", encoded = true) String uri,
                        @QueryMap Map<String, String> options);

    @GET("{uri}")
    Call<Object> GET(@Header("Authorization") String authHeader,
                     @Path(value = "uri", encoded = true) String uri, @QueryMap Map<String, String> options,
                     @Header("Cache-Control") String cacheHeaderValue);

    @POST("{uri}")
    Call<Object> POST(@Header("Authorization") String authHeader,
                      @Path(value = "uri", encoded = true) String uri,
                      @Header("Cache-Control") String cacheHeaderValue,
                      @Body HashMap<String, String> parameters);

    // end region
}
