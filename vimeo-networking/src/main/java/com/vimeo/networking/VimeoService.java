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

import com.vimeo.networking2.Album;
import com.vimeo.networking2.AlbumList;
import com.vimeo.networking2.AppConfiguration;
import com.vimeo.networking2.Category;
import com.vimeo.networking2.CategoryList;
import com.vimeo.networking2.Channel;
import com.vimeo.networking2.ChannelList;
import com.vimeo.networking2.Comment;
import com.vimeo.networking2.CommentList;
import com.vimeo.networking2.Document;
import com.vimeo.networking2.FeedList;
import com.vimeo.networking2.LiveStats;
import com.vimeo.networking2.NotificationList;
import com.vimeo.networking2.NotificationSubscriptions;
import com.vimeo.networking2.PictureCollection;
import com.vimeo.networking2.PinCodeInfo;
import com.vimeo.networking2.Product;
import com.vimeo.networking2.ProductList;
import com.vimeo.networking2.ProgrammedContentItemList;
import com.vimeo.networking2.RecommendationList;
import com.vimeo.networking2.SearchResultList;
import com.vimeo.networking2.SeasonList;
import com.vimeo.networking2.TextTrackList;
import com.vimeo.networking2.TvodItem;
import com.vimeo.networking2.TvodItemList;
import com.vimeo.networking2.User;
import com.vimeo.networking2.UserList;
import com.vimeo.networking2.Video;
import com.vimeo.networking2.VideoList;
import com.vimeo.networking2.VideoStatus;
import com.vimeo.networking2.VimeoAccount;
import com.vimeo.networking2.params.BatchPublishToSocialMedia;
import com.vimeo.networking2.PublishJob;
import com.vimeo.networking2.params.ModifyVideoInAlbumsSpecs;
import com.vimeo.networking2.params.ModifyVideosInAlbumSpecs;

import java.util.List;
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
import retrofit2.http.Path;
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
                            @Body Map<String, String> parameters);

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
     * Connected Apps
     * -----------------------------------------------------------------------------------------------------
     */
    // <editor-fold desc="Connected Apps">
    @GET("me/connected_apps")
    Call<com.vimeo.networking2.ConnectedAppList> getConnectedApps(@Header("Authorization") String authHeader,
                                                                  @Header("Cache-Control") String cacheHeader);

    @GET("me/connected_apps/{type}")
    Call<com.vimeo.networking2.ConnectedApp> getConnectedApp(@Header("Authorization") String authHeader,
                                                             @Path("type") String type,
                                                             @Header("Cache-Control") String cacheHeader);

    @PUT("me/connected_apps/{type}")
    @Headers("Cache-Control: no-cache, no-store")
    Call<com.vimeo.networking2.ConnectedApp> createConnectedApp(@Header("Authorization") String authHeader,
                                                                @Path("type") String type,
                                                                @Body Map<String, Object> parameters);

    @DELETE("me/connected_apps/{type}")
    @Headers("Cache-Control: no-cache, no-store")
    Call<Void> deleteConnectedApp(@Header("Authorization") String authHeader, @Path("type") String type);

    // </editor-fold>

    /**
     * ----------------------------------------------------------------------------------------------------
     * Publish Jobs
     * -----------------------------------------------------------------------------------------------------
     */
    // <editor-fold desc="Publish Jobs">
    @GET
    Call<PublishJob> getPublishJob(@Header("Authorization") String authHeader,
                                   @Url String url,
                                   @Header("Cache-Control") String cacheHeader);

    @PUT
    @Headers("Cache-Control: no-cache, no-store")
    Call<PublishJob> putPublishJob(@Header("Authorization") String authHeader,
                                   @Url String url,
                                   @Body BatchPublishToSocialMedia publishData);
    // </editor-fold>

    /**
     * ----------------------------------------------------------------------------------------------------
     * Album Modifications
     * -----------------------------------------------------------------------------------------------------
     */
    // <editor-fold desc="Album Modifications">
    @POST("me/albums")
    Call<Album> createAlbum(@Header("Authorization") String authHeader, @Body Map<String, Object> parameters);

    @PATCH
    Call<Album> editAlbum(@Header("Authorization") String authHeader,
                          @Url String uri,
                          @Body Map<String, Object> parameters);

    @DELETE
    Call<Object> deleteAlbum(@Header("Authorization") String authHeader, @Url String uri);

    @PUT
    Call<Object> addToAlbum(@Header("Authorization") String authHeader, @Url String addToAlbumUri);

    @DELETE
    Call<Object> removeFromAlbum(@Header("Authorization") String authHeader, @Url String addToAlbumUri);

    @PATCH
    Call<VideoList> modifyVideosInAlbum(@Header("Authorization") String authHeader,
                                        @Url String uri,
                                        @Body ModifyVideosInAlbumSpecs modificationSpecs);

    @PATCH
    Call<AlbumList> modifyVideoInAlbums(@Header("Authorization") String authHeader,
                                        @Url String uri,
                                        @Body ModifyVideoInAlbumsSpecs modificationSpecs);
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
                      @Body Map<String, Object> parameters);

    @PATCH
    Call<Video> editVideo(@Header("Authorization") String authHeader,
                          @Url String uri,
                          @Body Map<String, Object> parameters);

    @PATCH
    Call<User> editUser(@Header("Authorization") String authHeader,
                        @Url String uri,
                        @Body Map<String, Object> parameters);

    @PATCH
    Call<PictureCollection> editPictureCollection(@Header("Authorization") String authHeader,
                                                  @Url String uri,
                                                  @Body Map<String, Object> parameters);


    @PATCH("me/notifications/subscriptions")
    Call<NotificationSubscriptions> editSubscriptions(@Header("Authorization") String authHeader,
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
                          @QueryMap Map<String, String> options, @Body Map<String, String> parameters);


    @POST
    Call<PictureCollection> createPictureCollection(@Header("Authorization") String authHeader, @Url String uri);

    @POST
    Call<Void> emptyResponsePost(@Header("Authorization") String authHeader, @Url String uri,
                                 @Body Map<String, String> parameters);
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

    @GET("me")
    Call<com.vimeo.networking2.User> getUserMoshi(@Header("Authorization") String authHeader,
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
    Call<NotificationList> getNotificationList(@Header("Authorization") String authHeader,
                                               @Url String uri,
                                               @QueryMap Map<String, String> options,
                                               @Header("Cache-Control") String cacheHeaderValue);

    @GET
    Call<ProgrammedContentItemList> getProgramContentItemList(@Header("Authorization") String authHeader,
                                                              @Url String uri,
                                                              @QueryMap Map<String, String> options,
                                                              @Header("Cache-Control") String cacheHeaderValue);

    @GET
    Call<RecommendationList> getRecommendationList(@Header("Authorization") String authHeader,
                                                   @Url String uri,
                                                   @QueryMap Map<String, String> options,
                                                   @Header("Cache-Control") String cacheHeaderValue);

    @GET
    Call<SearchResultList> getSearchResponse(@Header("Authorization") String authHeader,
                                             @Url String uri,
                                             @QueryMap Map<String, String> options,
                                             @Header("Cache-Control") String cacheHeaderValue);

    @GET
    Call<SeasonList> getSeasonList(@Header("Authorization") String authHeader,
                                   @Url String uri,
                                   @QueryMap Map<String, String> options,
                                   @Header("Cache-Control") String cacheHeaderValue);

    @GET
    Call<TvodItemList> getTvodList(@Header("Authorization") String authHeader,
                                   @Url String uri,
                                   @QueryMap Map<String, String> options,
                                   @Header("Cache-Control") String cacheHeaderValue);

    @GET
    Call<UserList> getUserList(@Header("Authorization") String authHeader,
                               @Url String uri,
                               @QueryMap Map<String, String> options,
                               @Header("Cache-Control") String cacheHeaderValue);

    @GET
    Call<VideoList> getVideoList(@Header("Authorization") String authHeader,
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
    Call<ProductList> getProducts(@Header("Authorization") String authHeader);

    @GET
    Call<VideoStatus> getVideoStatus(@Header("Authorization") String authHeader,
                                     @Url String uri,
                                     @QueryMap Map<String, String> options,
                                     @Header("Cache-Control") String cacheHeaderValue);
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

    @POST
    Call<Object> POST(@Header("Authorization") String authHeader,
                      @Url String uri,
                      @Header("Cache-Control") String cacheHeaderValue,
                      @Body List<Object> parameters);
    // </editor-fold>

    // -----------------------------------------------------------------------------------------------------
    // Search - new search, for whitelisted apps only
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Search">
    @Headers("Cache-Control: no-cache, no-store")
    @GET("search")
    Call<SearchResultList> search(@Header("Authorization") String authHeader,
                                  @QueryMap Map<String, String> queryParams);
    // </editor-fold>
}
