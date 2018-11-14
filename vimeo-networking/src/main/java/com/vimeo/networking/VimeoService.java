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

import com.vimeo.networking.model.Album;
import com.vimeo.networking.model.AlbumList;
import com.vimeo.networking.AnnotatedConverterFactory.ConverterType;
import com.vimeo.networking.AnnotatedConverterFactory.Serializer;
import com.vimeo.networking.model.Category;
import com.vimeo.networking.model.CategoryList;
import com.vimeo.networking.model.Channel;
import com.vimeo.networking.model.ChannelList;
import com.vimeo.networking.model.Comment;
import com.vimeo.networking.model.CommentList;
import com.vimeo.networking.model.Document;
import com.vimeo.networking.model.FeedList;
import com.vimeo.networking.model.PictureCollection;
import com.vimeo.networking.model.PictureResource;
import com.vimeo.networking.model.PinCodeInfo;
import com.vimeo.networking.model.RecommendationList;
import com.vimeo.networking.model.TextTrackList;
import com.vimeo.networking.model.User;
import com.vimeo.networking.model.UserList;
import com.vimeo.networking.model.Video;
import com.vimeo.networking.model.VideoList;
import com.vimeo.networking.model.VimeoAccount;
import com.vimeo.networking.model.appconfiguration.AppConfiguration;
import com.vimeo.networking.model.cinema.ProgramContentItemList;
import com.vimeo.networking.model.iap.Product;
import com.vimeo.networking.model.iap.Products;
import com.vimeo.networking.model.live.LiveStats;
import com.vimeo.networking.model.notifications.NotificationList;
import com.vimeo.networking.model.notifications.SubscriptionCollection;
import com.vimeo.networking.model.search.SearchResponse;
import com.vimeo.networking.model.search.SuggestionResponse;
import com.vimeo.networking.model.tvod.SeasonList;
import com.vimeo.networking.model.tvod.TvodItem;
import com.vimeo.networking.model.tvod.TvodList;

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
 * <p>
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
                                         @Field("grant_type") String grantType,
                                         @Field("token") String token,
                                         @Field("scope") String scope);

    @FormUrlEncoded
    @POST("oauth/authorize/google")
    Call<VimeoAccount> logInWithGoogle(@Header("Authorization") String authHeader,
                                       @Field("grant_type") String grantType,
                                       @Field("id_token") String idToken,
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
    Call<Object> edit(@Header("Authorization") String authHeader,
                      @Url String uri,
                      @Body HashMap<String, Object> parameters);

    @PATCH
    Call<Video> editVideo(@Header("Authorization") String authHeader,
                          @Url String uri,
                          @Body HashMap<String, Object> parameters);

    @PATCH
    Call<User> editUser(@Header("Authorization") String authHeader,
                        @Url String uri,
                        @Body HashMap<String, Object> parameters);

    @PATCH
    Call<PictureCollection> editPictureCollection(@Header("Authorization") String authHeader,
                                                  @Url String uri,
                                                  @Body HashMap<String, Object> parameters);


    @PATCH("me/notifications/subscriptions")
    Call<SubscriptionCollection> editSubscriptions(@Header("Authorization") String authHeader,
                                                   @Body Map<String, Boolean> parameters);

    @Headers("Cache-Control: no-cache, no-store")
    @PATCH
    Call<Void> emptyResponsePatch(@Header("Authorization") String authHeader, @Url String uri,
                                  @QueryMap Map<String, String> options, @Body Object parameters);
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
    Call<AppConfiguration> getAppConfiguration(@Header("Authorization") String authHeader,
                                               @Url String uri,
                                               @QueryMap Map<String, String> options,
                                               @Header("Cache-Control") String cacheHeaderValue);

    @GET
    @Serializer(converter = ConverterType.MOSHI)
    Call<com.vimeo.networking2.AppConfiguration> getAppConfigurationMoshi(@Header("Authorization") String authHeader,
                                                                          @Url String uri,
                                                                          @QueryMap Map<String, String> options,
                                                                          @Header("Cache-Control") String cacheHeader);

    @GET
    Call<Category> getCategory(@Header("Authorization") String authHeader,
                               @Url String uri,
                               @QueryMap Map<String, String> options,
                               @Header("Cache-Control") String cacheHeaderValue);

    @GET
    Call<Channel> getChannel(@Header("Authorization") String authHeader,
                             @Url String uri,
                             @QueryMap Map<String, String> options,
                             @Header("Cache-Control") String cacheHeaderValue);

    @GET
    Call<Comment> getComment(@Header("Authorization") String authHeader,
                             @Url String uri,
                             @QueryMap Map<String, String> options,
                             @Header("Cache-Control") String cacheHeaderValue);

    @GET
    Call<Document> getDocument(@Header("Authorization") String authHeader, @Url String uri);

    @GET
    Call<TvodItem> getTvodItem(@Header("Authorization") String authHeader,
                               @Url String uri,
                               @QueryMap Map<String, String> options,
                               @Header("Cache-Control") String cacheHeaderValue);

    @GET
    Call<User> getUser(@Header("Authorization") String authHeader,
                       @Url String uri,
                       @QueryMap Map<String, String> options,
                       @Header("Cache-Control") String cacheHeaderValue);

    @GET
    Call<Video> getVideo(@Header("Authorization") String authHeader,
                         @Url String uri,
                         @QueryMap Map<String, String> options,
                         @Header("Cache-Control") String cacheHeaderValue);

    @GET
    Call<LiveStats> getLiveStats(@Header("Authorization") String authHeader,
                                 @Url String uri,
                                 @QueryMap Map<String, String> options,
                                 @Header("Cache-Control") String cacheHeaderValue);

    @GET
    Call<Void> getVoid(@Header("Authorization") String authHeader,
                       @Url String uri,
                       @QueryMap Map<String, String> options,
                       @Header("Cache-Control") String cacheHeaderValue);

    @GET
    Call<Product> getProduct(@Header("Authorization") String authHeader,
                             @Url String uri,
                             @QueryMap Map<String, String> options,
                             @Header("Cache-Control") String cacheHeaderValue);

    @GET
    Call<Album> getAlbum(@Header("Authorization") String authHeader,
                           @Url String uri,
                           @QueryMap Map<String, String> options,
                           @Header("Cache-Control") String cacheHeaderValue);

    // </editor-fold>


    // -----------------------------------------------------------------------------------------------------
    // General List Getters
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="General List Getters">

    @GET
    Call<CategoryList> getCategoryList(@Header("Authorization") String authHeader,
                                       @Url String uri,
                                       @QueryMap Map<String, String> options,
                                       @Header("Cache-Control") String cacheHeaderValue);

    @GET
    Call<ChannelList> getChannelList(@Header("Authorization") String authHeader,
                                     @Url String uri,
                                     @QueryMap Map<String, String> options,
                                     @Header("Cache-Control") String cacheHeaderValue);

    @GET
    Call<CommentList> getCommentList(@Header("Authorization") String authHeader,
                                     @Url String uri,
                                     @QueryMap Map<String, String> options,
                                     @Header("Cache-Control") String cacheHeaderValue);

    @GET
    Call<FeedList> getFeedList(@Header("Authorization") String authHeader,
                               @Url String uri,
                               @QueryMap Map<String, String> options,
                               @Header("Cache-Control") String cacheHeaderValue);

    @GET
    @Serializer(converter = ConverterType.MOSHI)
    Call<com.vimeo.networking2.FeedList> getFeedListMoshi(@Header("Authorization") String authHeader,
                                                          @Url String uri,
                                                          @QueryMap Map<String, String> options,
                                                          @Header("Cache-Control") String cacheHeaderValue);

    @GET
    Call<NotificationList> getNotificationList(@Header("Authorization") String authHeader,
                                               @Url String uri,
                                               @QueryMap Map<String, String> options,
                                               @Header("Cache-Control") String cacheHeaderValue);

    @GET
    Call<ProgramContentItemList> getProgramContentItemList(@Header("Authorization") String authHeader,
                                                           @Url String uri,
                                                           @QueryMap Map<String, String> options,
                                                           @Header("Cache-Control") String cacheHeaderValue);

    @GET
    Call<RecommendationList> getRecommendationList(@Header("Authorization") String authHeader,
                                                   @Url String uri,
                                                   @QueryMap Map<String, String> options,
                                                   @Header("Cache-Control") String cacheHeaderValue);

    @GET
    Call<SearchResponse> getSearchResponse(@Header("Authorization") String authHeader,
                                           @Url String uri,
                                           @QueryMap Map<String, String> options,
                                           @Header("Cache-Control") String cacheHeaderValue);

    @GET
    Call<SeasonList> getSeasonList(@Header("Authorization") String authHeader,
                                   @Url String uri,
                                   @QueryMap Map<String, String> options,
                                   @Header("Cache-Control") String cacheHeaderValue);

    @GET
    Call<TvodList> getTvodList(@Header("Authorization") String authHeader,
                               @Url String uri,
                               @QueryMap Map<String, String> options,
                               @Header("Cache-Control") String cacheHeaderValue);

    @GET
    Call<UserList> getUserList(@Header("Authorization") String authHeader,
                               @Url String uri,
                               @QueryMap Map<String, String> options,
                               @Header("Cache-Control") String cacheHeaderValue);

    @GET
    @Serializer(converter = ConverterType.MOSHI)
    Call<com.vimeo.networking2.UserList> getUserListMoshi(@Header("Authorization") String authHeader,
                                                          @Url String uri,
                                                          @QueryMap Map<String, String> options,
                                                          @Header("Cache-Control") String cacheHeaderValue);

    @GET
    Call<VideoList> getVideoList(@Header("Authorization") String authHeader,
                                 @Url String uri,
                                 @QueryMap Map<String, String> options,
                                 @Header("Cache-Control") String cacheHeaderValue);

    @GET
    @Serializer(converter = ConverterType.MOSHI)
    Call<com.vimeo.networking2.VideoList> getVideoListMoshi(@Header("Authorization") String authHeader,
                                                            @Url String uri,
                                                            @QueryMap Map<String, String> options,
                                                            @Header("Cache-Control") String cacheHeaderValue);

    @GET
    Call<AlbumList> getAlbumList(@Header("Authorization") String authHeader,
                                 @Url String uri,
                                 @QueryMap Map<String, String> options,
                                 @Header("Cache-Control") String cacheHeaderValue);

    @GET
    Call<TextTrackList> getTextTrackList(@Header("Authorization") String authHeader, @Url String uri);

    @GET("products")
    Call<Products> getProducts(@Header("Authorization") String authHeader);
    // </editor-fold>

    @PUT
    Call<User> putContentWithUserResponse(@Header("Authorization") String authHeader,
                                          @Url String uri,
                                          @Header("Cache-Control") String cacheHeaderValue,
                                          @QueryMap Map<String, String> options,
                                          @Body Object body);

    @PUT
    Call<User> putContentWithUserResponse(@Header("Authorization") String authHeader,
                                          @Url String uri,
                                          @QueryMap Map<String, String> options);

    /**
     * ---------------------------------------------------------------------------------------------------
     * Generic Region
     * ---------------------------------------------------------------------------------------------------
     */
    // <editor-fold desc="Generic Region">
    @PUT
    Call<Object> PUT(@Header("Authorization") String authHeader,
                     @Url String uri,
                     @QueryMap Map<String, String> options);

    @PUT
    Call<Object> PUT(@Header("Authorization") String authHeader,
                     @Url String uri,
                     @Header("Cache-Control") String cacheHeaderValue,
                     @QueryMap Map<String, String> options,
                     @Body Object body);

    @DELETE
    Call<Object> DELETE(@Header("Authorization") String authHeader,
                        @Url String uri,
                        @QueryMap Map<String, String> options);

    @GET
    Call<Object> GET(@Header("Authorization") String authHeader,
                     @Url String uri,
                     @QueryMap Map<String, String> options,
                     @Header("Cache-Control") String cacheHeaderValue);

    @POST
    Call<Object> POST(@Header("Authorization") String authHeader,
                      @Url String uri,
                      @Header("Cache-Control") String cacheHeaderValue,
                      @Body HashMap<String, String> parameters);

    @POST
    Call<Object> POST(@Header("Authorization") String authHeader,
                      @Url String uri,
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

    @GET("suggest")
    Call<SuggestionResponse> suggest(@Header("Authorization") String authHeader,
                                     @QueryMap Map<String, String> queryParams);
}
