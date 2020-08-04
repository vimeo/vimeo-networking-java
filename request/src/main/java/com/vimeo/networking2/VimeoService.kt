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

import com.vimeo.networking2.ApiConstants.Parameters.PARAMETER_GET_FIELD_FILTER
import com.vimeo.networking2.internal.VimeoCall
import com.vimeo.networking2.params.BatchPublishToSocialMedia
import com.vimeo.networking2.params.ModifyVideoInAlbumsSpecs
import com.vimeo.networking2.params.ModifyVideosInAlbumSpecs
import okhttp3.CacheControl
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap
import retrofit2.http.Url

/**
 * Retrofit service for the Vimeo API.
 *
 * @see VimeoApiClient
 */
@Suppress("unused", "UndocumentedPublicFunction", "ComplexInterface")
internal interface VimeoService {

    @GET("me/connected_apps")
    fun getConnectedAppList(
        @Header(AUTHORIZATION) authorization: String,
        @Query(PARAMETER_GET_FIELD_FILTER) fieldFilter: String?,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<ConnectedAppList>

    @GET("me/connected_apps/{type}")
    fun getConnectedApp(
        @Header(AUTHORIZATION) authorization: String,
        @Path("type") type: String,
        @Query(PARAMETER_GET_FIELD_FILTER) fieldFilter: String?,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<ConnectedApp>

    @PUT("me/connected_apps/{type}")
    fun createConnectedApp(
        @Header(AUTHORIZATION) authorization: String,
        @Path("type") type: String,
        @Body bodyParams: Map<String, @JvmSuppressWildcards Any>
    ): VimeoCall<ConnectedApp>

    @DELETE("me/connected_apps/{type}")
    fun deleteConnectedApp(
        @Header(AUTHORIZATION) authorization: String,
        @Path("type") type: String
    ): VimeoCall<Unit>

    @GET
    fun getPublishJob(
        @Header(AUTHORIZATION) authorization: String,
        @Url url: String,
        @Query(PARAMETER_GET_FIELD_FILTER) fieldFilter: String?,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<PublishJob>

    @PUT
    @Headers(HEADER_NO_CACHE)
    fun putPublishJob(
        @Header(AUTHORIZATION) authorization: String,
        @Url url: String,
        @Body publishData: BatchPublishToSocialMedia?
    ): VimeoCall<PublishJob>

    @POST("me/albums")
    fun createAlbum(
        @Header(AUTHORIZATION) authorization: String,
        @Body bodyParams: Map<String, @JvmSuppressWildcards Any>
    ): VimeoCall<Album>

    @PATCH
    fun editAlbum(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Body bodyParams: Map<String, @JvmSuppressWildcards Any>
    ): VimeoCall<Album>

    @PUT("{albumUri}/{videoUri}")
    fun addToAlbum(
        @Header(AUTHORIZATION) authorization: String,
        @Path("albumUri", encoded = true) albumUri: String,
        @Path("videoUri", encoded = true) videoUri: String
    ): VimeoCall<Unit>

    @DELETE("{albumUri}/{videoUri}")
    fun removeFromAlbum(
        @Header(AUTHORIZATION) authorization: String,
        @Path("albumUri", encoded = true) albumUri: String,
        @Path("videoUri", encoded = true) videoUri: String
    ): VimeoCall<Unit>

    @PATCH("{albumUri}/videos")
    fun modifyVideosInAlbum(
        @Header(AUTHORIZATION) authorization: String,
        @Path("albumUri", encoded = true) albumUri: String,
        @Body modificationSpecs: ModifyVideosInAlbumSpecs
    ): VimeoCall<VideoList>

    @PATCH("{videoUri}/albums")
    fun modifyVideoInAlbums(
        @Header(AUTHORIZATION) authorization: String,
        @Path("videoUri", encoded = true) videoUri: String,
        @Body modificationSpecs: ModifyVideoInAlbumsSpecs
    ): VimeoCall<AlbumList>

    @PATCH
    fun editVideo(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Body bodyParams: Map<String, @JvmSuppressWildcards Any>
    ): VimeoCall<Video>

    @PATCH
    fun editUser(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Body bodyParams: Map<String, @JvmSuppressWildcards Any>
    ): VimeoCall<User>

    @PATCH
    fun editPictureCollection(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Body bodyParams: Map<String, @JvmSuppressWildcards Any>
    ): VimeoCall<PictureCollection>

    @PATCH("me/notifications/subscriptions")
    fun editNotificationSubscriptions(
        @Header(AUTHORIZATION) authorization: String,
        @Body bodyParams: Map<String, Boolean>
    ): VimeoCall<NotificationSubscriptions>

    @POST
    fun createComment(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Body bodyParams: Map<String, @JvmSuppressWildcards String>
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
        @Query(PARAMETER_GET_FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<AppConfiguration>

    @GET
    fun getCategory(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Query(PARAMETER_GET_FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<Category>

    @GET
    fun getChannel(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Query(PARAMETER_GET_FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<Channel>

    @GET
    fun getComment(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Query(PARAMETER_GET_FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
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
        @Query(PARAMETER_GET_FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<TvodItem>

    @GET
    fun getUser(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Query(PARAMETER_GET_FIELD_FILTER) fieldFilter: String?,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<User>

    @GET
    fun getVideo(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Query(PARAMETER_GET_FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<Video>

    @GET
    fun getLiveStats(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Query(PARAMETER_GET_FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<LiveStats>

    @GET
    fun getProduct(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Query(PARAMETER_GET_FIELD_FILTER) fieldFilter: String?,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<Product>

    @GET
    fun getAlbum(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Query(PARAMETER_GET_FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<Album>

    @GET
    fun getCategoryList(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Query(PARAMETER_GET_FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<CategoryList>

    @GET
    fun getChannelList(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Query(PARAMETER_GET_FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<ChannelList>

    @GET
    fun getCommentList(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Query(PARAMETER_GET_FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<CommentList>

    @GET
    fun getFeedList(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Query(PARAMETER_GET_FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<FeedList>

    @GET
    fun getProjectItemList(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Query(PARAMETER_GET_FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<ProjectItemList>

    @GET
    fun getNotificationList(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Query(PARAMETER_GET_FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<NotificationList>

    @GET
    fun getProgramContentItemList(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Query(PARAMETER_GET_FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<ProgrammedContentItemList>

    @GET
    fun getRecommendationList(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Query(PARAMETER_GET_FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<RecommendationList>

    @GET
    fun getSearchResultList(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Query(PARAMETER_GET_FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<SearchResultList>

    @GET
    fun getSeasonList(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Query(PARAMETER_GET_FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<SeasonList>

    @GET
    fun getTvodItemList(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Query(PARAMETER_GET_FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<TvodItemList>

    @GET
    fun getUserList(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Query(PARAMETER_GET_FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<UserList>

    @GET
    fun getVideoList(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Query(PARAMETER_GET_FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<VideoList>

    @GET
    fun getAlbumList(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Query(PARAMETER_GET_FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<AlbumList>

    @GET
    fun getTextTrackList(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Query(PARAMETER_GET_FIELD_FILTER) fieldFilter: String?,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<TextTrackList>

    @GET("products")
    fun getProducts(
        @Header(AUTHORIZATION) authorization: String,
        @Query(PARAMETER_GET_FIELD_FILTER) fieldFilter: String?,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<ProductList>

    @GET
    fun getVideoStatus(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Query(PARAMETER_GET_FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<VideoStatus>

    @GET
    fun getUnit(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<Unit>

    @PUT
    fun putContentWithUserResponse(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Body bodyParams: Any?
    ): VimeoCall<User>

    @PUT
    fun putContentWithUserResponse(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>
    ): VimeoCall<User>

    @PUT
    fun put(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>
    ): VimeoCall<Unit>

    @PUT
    fun put(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Body bodyParams: Any?
    ): VimeoCall<Unit>

    @DELETE
    fun delete(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>
    ): VimeoCall<Unit>

    @POST
    fun post(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Body bodyParams: List<@JvmSuppressWildcards Any>
    ): VimeoCall<Unit>

    @PATCH
    fun emptyResponsePatch(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Body bodyParams: Any
    ): VimeoCall<Unit>

    @POST
    fun emptyResponsePost(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Body bodyParams: Map<String, @JvmSuppressWildcards String>
    ): VimeoCall<Unit>

    @Headers(HEADER_NO_CACHE)
    @GET("search")
    fun search(
        @Header(AUTHORIZATION) authorization: String,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>
    ): VimeoCall<SearchResultList>

    companion object {
        private const val CACHE_CONTROL = "Cache-Control"
        private const val AUTHORIZATION = "Authorization"
        private const val HEADER_NO_CACHE = "Cache-Control: no-cache, no-store"
    }
}
