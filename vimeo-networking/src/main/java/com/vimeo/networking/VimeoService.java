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

import com.vimeo.networking.model.Comment;
import com.vimeo.networking.model.PictureResource;
import com.vimeo.networking.model.PinCodeInfo;
import com.vimeo.networking.model.Video;
import com.vimeo.networking.model.VimeoAccount;
import com.vimeo.networking.model.search.SearchResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Interface of available API calls that can be made using Retrofit.
 * <p/>
 * Created by alfredhanssen on 4/12/15.
 */
public interface VimeoService {

    /**
     * -----------------------------------------------------------------------------------------------------
     * Authentication
     * -----------------------------------------------------------------------------------------------------
     */
    // <editor-fold desc="Authentication">
    @FormUrlEncoded
    @POST("oauth/access_token")
    Call<VimeoAccount> authenticateWithCodeGrant(@Header("Authorization") String authHeader,
                                                 @Field("redirect_uri") String redirectURI,
                                                 @Field("code") String code,
                                                 @Field("grant_type") String grantType);

    @FormUrlEncoded
    @POST("oauth/authorize/client")
    Call<VimeoAccount> authorizeWithClientCredentialsGrant(@Header("Authorization") String authHeader,
                                                           @Field("grant_type") String grantType,
                                                           @Field("scope") String scope);

    @POST("users")
    Call<VimeoAccount> join(@Header("Authorization") String authHeader,
                            @Body HashMap<String, String> parameters);

    @FormUrlEncoded
    @POST("oauth/authorize/password")
    Call<VimeoAccount> logIn(@Header("Authorization") String authHeader, @Field("username") String email,
                             @Field("password") String password, @Field("grant_type") String grantType,
                             @Field("scope") String scope);

    @FormUrlEncoded
    @POST("oauth/authorize/facebook")
    Call<VimeoAccount> logInWithFacebook(@Header("Authorization") String authHeader,
                                         @Field("grant_type") String grantType, @Field("token") String token,
                                         @Field("scope") String scope);

    @Headers("Cache-Control: no-cache, no-store")
    @DELETE("tokens")
    Call<Object> logOut(@Header("Authorization") String authHeader);

    @FormUrlEncoded
    @POST("oauth/authorize/vimeo_oauth1")
    Call<VimeoAccount> exchangeOAuthOneToken(@Header("Authorization") String authHeader,
                                             @Field("grant_type") String grantType,
                                             @Field("token") String token,
                                             @Field("token_secret") String tokenSecret,
                                             @Field("scope") String scope);

    @FormUrlEncoded
    @POST("oauth/appexchange")
    Call<VimeoAccount> ssoTokenExchange(@Header("Authorization") String basicAuth,
                                        @Field("access_token") String token, @Field("scope") String scope);

    @FormUrlEncoded
    @Headers("Cache-Control: no-cache, no-store")
    @POST("oauth/device")
    Call<PinCodeInfo> getPinCodeInfo(@Header("Authorization") String authHeader,
                                     @Field("grant_type") String grantType, @Field("scope") String scope);

    @FormUrlEncoded
    @Headers("Cache-Control: no-cache, no-store")
    @POST("oauth/device/authorize")
    Call<VimeoAccount> logInWithPinCode(@Header("Authorization") String authHeader,
                                        @Field("grant_type") String grantType,
                                        @Field("user_code") String pinCode,
                                        @Field("device_code") String deviceCode,
                                        @Field("scope") String scope);

    // </editor-fold>

    /**
     * ----------------------------------------------------------------------------------------------------
     * Editing
     * -----------------------------------------------------------------------------------------------------
     */
    // <editor-fold desc="Editing">
    @PATCH
    Call<Object> edit(@Header("Authorization") String authHeader, @Url String uri,
                      @Body HashMap<String, Object> parameters);

    // </editor-fold>

    /**
     * -----------------------------------------------------------------------------------------------------
     * POSTs
     * -----------------------------------------------------------------------------------------------------
     */
    // <editor-fold desc="POSTs">
    @POST
    Call<Comment> comment(@Header("Authorization") String authHeader, @Url String uri,
                          @QueryMap Map<String, String> options, @Body HashMap<String, String> parameters);


    @POST
    Call<PictureResource> createPictureResource(@Header("Authorization") String authHeader, @Url String uri);

    @POST
    Call<Void> emptyResponsePost(@Header("Authorization") String authHeader, @Url String uri,
                                 @Body HashMap<String, String> parameters);
    // </editor-fold>

    // -----------------------------------------------------------------------------------------------------
    // Concrete Region
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Concrete Region">
    @GET
    Call<Video> getVideo(@Header("Authorization") String authHeader,
                         @Header("Cache-Control") String cacheHeaderValue, @Url String uri,
                         @QueryMap Map<String, String> options);
    // </editor-fold>

    /**
     * ---------------------------------------------------------------------------------------------------
     * Generic Region
     * ---------------------------------------------------------------------------------------------------
     */
    // <editor-fold desc="Generic Region">
    @PUT
    Call<Object> PUT(@Header("Authorization") String authHeader, @Url String uri,
                     @QueryMap Map<String, String> options);

    @DELETE
    Call<Object> DELETE(@Header("Authorization") String authHeader, @Url String uri,
                        @QueryMap Map<String, String> options);

    @GET
    Call<Object> GET(@Header("Authorization") String authHeader, @Url String uri,
                     @QueryMap Map<String, String> options, @Header("Cache-Control") String cacheHeaderValue);

    @POST
    Call<Object> POST(@Header("Authorization") String authHeader, @Url String uri,
                      @Header("Cache-Control") String cacheHeaderValue,
                      @Body HashMap<String, String> parameters);

    @POST
    Call<Object> POST(@Header("Authorization") String authHeader, @Url String uri,
                      @Header("Cache-Control") String cacheHeaderValue,
                      @Body ArrayList<Object> parameters);
    // </editor-fold>

    // -----------------------------------------------------------------------------------------------------
    // Search - new search, for whitelisted apps only
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Search">
    @Headers("Cache-Control: no-cache, no-store")
    @GET("search")
    Call<SearchResponse> search(@Header("Authorization") String authHeader,
                                @QueryMap Map<String, String> queryParams);
    // </editor-fold>
}
