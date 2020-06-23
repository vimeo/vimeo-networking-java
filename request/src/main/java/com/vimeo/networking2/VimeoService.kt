/*
 * Copyright (c) 2020 Vimeo (https://vimeo.com)
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
package com.vimeo.networking2

import com.vimeo.networking2.internal.VimeoCall
import com.vimeo.networking2.params.BatchPublishToSocialMedia
import com.vimeo.networking2.params.ModifyVideoInAlbumsSpecs
import com.vimeo.networking2.params.ModifyVideosInAlbumSpecs
import okhttp3.CacheControl
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.QueryMap
import retrofit2.http.Url

/**
 * Retrofit service for the Vimeo API.
 */
@Suppress("unused", "UndocumentedPublicFunction", "ComplexInterface")
interface VimeoService {

    @FormUrlEncoded
    @POST("oauth/access_token")
    fun authenticateWithCodeGrant(
        @Header(AUTHORIZATION) authorization: String,
        @Field("redirect_uri") redirectURI: String,
        @Field("code") code: String,
        @Field("grant_type") grantType: String
    ): VimeoCall<VimeoAccount>

    @FormUrlEncoded
    @POST("oauth/authorize/client")
    fun authorizeWithClientCredentialsGrant(
        @Header(AUTHORIZATION) authorization: String,
        @Field("grant_type") grantType: String,
        @Field("scope") scope: String
    ): VimeoCall<VimeoAccount>

    @POST("users")
    fun join(
        @Header(AUTHORIZATION) authorization: String,
        @Body parameters: Map<String, String>
    ): VimeoCall<VimeoAccount>

    @FormUrlEncoded
    @POST("oauth/authorize/password")
    fun logIn(
        @Header(AUTHORIZATION) authorization: String,
        @Field("username") email: String,
        @Field("password") password: String,
        @Field("grant_type") grantType: String,
        @Field("scope") scope: String
    ): VimeoCall<VimeoAccount>

    @FormUrlEncoded
    @POST("oauth/authorize/facebook")
    fun logInWithFacebook(
        @Header(AUTHORIZATION) authorization: String,
        @Field("grant_type") grantType: String,
        @Field("token") token: String,
        @Field("scope") scope: String
    ): VimeoCall<VimeoAccount>

    @FormUrlEncoded
    @POST("oauth/authorize/google")
    fun logInWithGoogle(
        @Header(AUTHORIZATION) authorization: String,
        @Field("grant_type") grantType: String,
        @Field("id_token") idToken: String,
        @Field("scope") scope: String
    ): VimeoCall<VimeoAccount>

    @Headers("Cache-Control: no-cache, no-store")
    @DELETE("tokens")
    fun logOut(
        @Header(AUTHORIZATION) authorization: String
    ): VimeoCall<Any>

    @FormUrlEncoded
    @POST("oauth/authorize/vimeo_oauth1")
    fun exchangeOAuthOneToken(
        @Header(AUTHORIZATION) authorization: String,
        @Field("grant_type") grantType: String,
        @Field("token") token: String,
        @Field("token_secret") tokenSecret: String,
        @Field("scope") scope: String
    ): VimeoCall<VimeoAccount>

    @FormUrlEncoded
    @POST("oauth/appexchange")
    fun ssoTokenExchange(
        @Header(AUTHORIZATION) basicAuth: String,
        @Field("access_token") token: String,
        @Field("scope") scope: String
    ): VimeoCall<VimeoAccount>

    @FormUrlEncoded
    @Headers("Cache-Control: no-cache, no-store")
    @POST("oauth/device")
    fun getPinCodeInfo(
        @Header(AUTHORIZATION) authorization: String,
        @Field("grant_type") grantType: String,
        @Field("scope") scope: String
    ): VimeoCall<PinCodeInfo>

    @FormUrlEncoded
    @Headers("Cache-Control: no-cache, no-store")
    @POST("oauth/device/authorize")
    fun logInWithPinCode(
        @Header(AUTHORIZATION) authorization: String,
        @Field("grant_type") grantType: String,
        @Field("user_code") pinCode: String,
        @Field("device_code") deviceCode: String,
        @Field("scope") scope: String
    ): VimeoCall<VimeoAccount>

    // TODO: end login

    @GET("me/connected_apps")
    fun getConnectedAppList(
        @Header(AUTHORIZATION) authorization: String,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<ConnectedAppList>

    @GET("me/connected_apps/{type}")
    fun getConnectedApp(
        @Header(AUTHORIZATION) authorization: String,
        @Path("type") type: String,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<ConnectedApp>

    @PUT("me/connected_apps/{type}")
    @Headers("Cache-Control: no-cache, no-store")
    fun createConnectedApp(
        @Header(AUTHORIZATION) authorization: String,
        @Path("type") type: String,
        @Body parameters: Map<String, Any>
    ): VimeoCall<ConnectedApp>

    @DELETE("me/connected_apps/{type}")
    @Headers("Cache-Control: no-cache, no-store")
    fun deleteConnectedApp(
        @Header(AUTHORIZATION) authorization: String,
        @Path("type") type: String
    ): VimeoCall<Void>

    @GET
    fun getPublishJob(
        @Header(AUTHORIZATION) authorization: String,
        @Url url: String,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<PublishJob>

    @PUT
    @Headers("Cache-Control: no-cache, no-store")
    fun putPublishJob(
        @Header(AUTHORIZATION) authorization: String,
        @Url url: String,
        @Body publishData: BatchPublishToSocialMedia?
    ): VimeoCall<PublishJob>

    // TODO: end publish

    @POST("me/albums")
    fun createAlbum(
        @Header(AUTHORIZATION) authorization: String,
        @Body parameters: Map<String, Any>
    ): VimeoCall<Album>

    @PATCH
    fun editAlbum(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Body parameters: Map<String, Any>
    ): VimeoCall<Album>

    @DELETE
    fun deleteAlbum(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String
    ): VimeoCall<Any>

    @PUT
    fun addToAlbum(
        @Header(AUTHORIZATION) authorization: String,
        @Url addToAlbumUri: String
    ): VimeoCall<Any>

    @DELETE
    fun removeFromAlbum(
        @Header(AUTHORIZATION) authorization: String,
        @Url addToAlbumUri: String
    ): VimeoCall<Any>

    @PATCH
    fun modifyVideosInAlbum(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Body modificationSpecs: ModifyVideosInAlbumSpecs
    ): VimeoCall<VideoList>

    @PATCH
    fun modifyVideoInAlbums(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Body modificationSpecs: ModifyVideoInAlbumsSpecs
    ): VimeoCall<AlbumList>

    // TODO: end albums

    @PATCH
    fun edit(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Body parameters: Map<String, Any>
    ): VimeoCall<Any>

    @PATCH
    fun editVideo(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Body parameters: Map<String, Any>
    ): VimeoCall<Video>

    @PATCH
    fun editUser(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Body parameters: Map<String, Any>
    ): VimeoCall<User>

    @PATCH
    fun editPictureCollection(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Body parameters: Map<String, Any>
    ): VimeoCall<PictureCollection>

    @PATCH("me/notifications/subscriptions")
    fun editNotificationSubscriptions(
        @Header(AUTHORIZATION) authorization: String,
        @Body parameters: Map<String, Boolean>
    ): VimeoCall<NotificationSubscriptions>

    @POST
    fun createComment(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @QueryMap options: Map<String, String>,
        @Body parameters: Map<String, String>
    ): VimeoCall<Comment>

    @POST
    fun createPictureCollection(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String
    ): VimeoCall<PictureCollection>

    @GET
    fun getAppConfiguration(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @QueryMap options: Map<String, String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<AppConfiguration>

    @GET
    fun getCategory(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @QueryMap options: Map<String, String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<Category>

    @GET
    fun getChannel(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @QueryMap options: Map<String, String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<Channel>

    @GET
    fun getComment(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @QueryMap options: Map<String, String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<Comment>

    @GET
    fun getDocument(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String
    ): VimeoCall<Document>

    @GET
    fun getTvodItem(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @QueryMap options: Map<String, String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<TvodItem>

    @GET
    fun getUser(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @QueryMap options: Map<String, String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<User>

    @GET
    fun getVideo(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @QueryMap options: Map<String, String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<Video>

    @GET
    fun getLiveStats(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @QueryMap options: Map<String, String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<LiveStats>

    @GET
    fun getProduct(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @QueryMap options: Map<String, String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<Product>

    @GET
    fun getAlbum(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @QueryMap options: Map<String, String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<Album>

    @GET
    fun getCategoryList(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @QueryMap options: Map<String, String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<CategoryList>

    @GET
    fun getChannelList(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @QueryMap options: Map<String, String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<ChannelList>

    @GET
    fun getCommentList(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @QueryMap options: Map<String, String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<CommentList>

    @GET
    fun getFeedList(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @QueryMap options: Map<String, String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<FeedList>

    @GET
    fun getNotificationList(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @QueryMap options: Map<String, String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<NotificationList>

    @GET
    fun getProgramContentItemList(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @QueryMap options: Map<String, String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<ProgrammedContentItemList>

    @GET
    fun getRecommendationList(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @QueryMap options: Map<String, String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<RecommendationList>

    @GET
    fun getSearchResultList(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @QueryMap options: Map<String, String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<SearchResultList>

    @GET
    fun getSeasonList(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @QueryMap options: Map<String, String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<SeasonList>

    @GET
    fun getTvodItemList(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @QueryMap options: Map<String, String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<TvodItemList>

    @GET
    fun getUserList(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @QueryMap options: Map<String, String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<UserList>

    @GET
    fun getVideoList(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @QueryMap options: Map<String, String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<VideoList>

    @GET
    fun getAlbumList(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @QueryMap options: Map<String, String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<AlbumList>

    @GET
    fun getTextTrackList(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String
    ): VimeoCall<TextTrackList>

    @GET("products")
    fun getProducts(
        @Header(AUTHORIZATION) authorization: String
    ): VimeoCall<ProductList>

    @GET
    fun getVideoStatus(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @QueryMap options: Map<String, String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<VideoStatus>

    @PUT
    fun putContentWithUserResponse(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?,
        @QueryMap options: Map<String, String>,
        @Body body: Any
    ): VimeoCall<User>

    @PUT
    fun putContentWithUserResponse(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @QueryMap options: Map<String, String>
    ): VimeoCall<User>

    @GET
    fun getVoid(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @QueryMap options: Map<String, String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<Void>

    @PUT
    fun put(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @QueryMap options: Map<String, String>
    ): VimeoCall<Any>

    @PUT
    fun put(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?,
        @QueryMap options: Map<String, String>,
        @Body body: Any
    ): VimeoCall<Any>

    @DELETE
    fun delete(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @QueryMap options: Map<String, String>
    ): VimeoCall<Any>

    @POST
    fun post(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?,
        @Body parameters: List<Any>
    ): VimeoCall<Any>

    @Headers("Cache-Control: no-cache, no-store")
    @PATCH
    fun emptyResponsePatch(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @QueryMap options: Map<String, String>,
        @Body parameters: Any
    ): VimeoCall<Void>

    @POST
    fun emptyResponsePost(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Body parameters: Map<String, String>
    ): VimeoCall<Void>

    @Headers("Cache-Control: no-cache, no-store")
    @GET("search")
    fun search(
        @Header(AUTHORIZATION) authorization: String,
        @QueryMap queryParams: Map<String, String>
    ): VimeoCall<SearchResultList>

    companion object {
        private const val CACHE_CONTROL = "Cache-Control"
        private const val AUTHORIZATION = "Authorization"
    }
}
