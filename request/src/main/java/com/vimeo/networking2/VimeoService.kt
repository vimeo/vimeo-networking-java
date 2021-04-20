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

import com.vimeo.networking2.ApiConstants.Parameters.PARAMETER_ACTIVE
import com.vimeo.networking2.ApiConstants.Parameters.PARAMETER_APP_TYPE
import com.vimeo.networking2.ApiConstants.Parameters.PARAMETER_AUTH_CODE
import com.vimeo.networking2.ApiConstants.Parameters.PARAMETER_CLIENT_ID
import com.vimeo.networking2.ApiConstants.Parameters.PARAMETER_COMMENT_TEXT_BODY
import com.vimeo.networking2.ApiConstants.Parameters.PARAMETER_EMAIL
import com.vimeo.networking2.ApiConstants.Parameters.PARAMETER_FOLDER_NAME
import com.vimeo.networking2.ApiConstants.Parameters.PARAMETER_FOLDER_PRIVACY
import com.vimeo.networking2.ApiConstants.Parameters.PARAMETER_FOLDER_URI
import com.vimeo.networking2.ApiConstants.Parameters.PARAMETER_PASSWORD
import com.vimeo.networking2.ApiConstants.Parameters.PARAMETER_PERMISSION_LEVEL
import com.vimeo.networking2.ApiConstants.Parameters.PARAMETER_ROLE
import com.vimeo.networking2.ApiConstants.Parameters.PARAMETER_USERS_BIO
import com.vimeo.networking2.ApiConstants.Parameters.PARAMETER_USERS_LOCATION
import com.vimeo.networking2.ApiConstants.Parameters.PARAMETER_USERS_NAME
import com.vimeo.networking2.enums.*
import com.vimeo.networking2.internal.VimeoCall
import com.vimeo.networking2.params.*
import okhttp3.CacheControl
import retrofit2.http.*

/**
 * Retrofit service for the Vimeo API.
 *
 * @see VimeoApiClient
 */
@Suppress("UndocumentedPublicFunction", "ComplexInterface")
internal interface VimeoService {

    @GET("me/connected_apps")
    fun getConnectedAppList(
        @Header(AUTHORIZATION) authorization: String,
        @Query(FIELD_FILTER) fieldFilter: String?,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<ConnectedAppList>

    @GET("me/connected_apps/{type}")
    fun getConnectedApp(
        @Header(AUTHORIZATION) authorization: String,
        @Path(TYPE) type: ConnectedAppType,
        @Query(FIELD_FILTER) fieldFilter: String?,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<ConnectedApp>

    @FormUrlEncoded
    @PUT("me/connected_apps/{type}")
    fun createConnectedApp(
        @Header(AUTHORIZATION) authorization: String,
        @Path(TYPE) type: ConnectedAppType,
        @Field(PARAMETER_AUTH_CODE) authorizationCode: String,
        @Field(PARAMETER_APP_TYPE) appType: ConnectedAppType,
        @Field(PARAMETER_CLIENT_ID) clientId: String
    ): VimeoCall<ConnectedApp>

    @DELETE("me/connected_apps/{type}")
    fun deleteConnectedApp(
        @Header(AUTHORIZATION) authorization: String,
        @Path(TYPE) type: ConnectedAppType
    ): VimeoCall<Unit>

    @GET("{path}")
    fun getPublishJob(
        @Header(AUTHORIZATION) authorization: String,
        @Path("{path}", encoded = true) uri: String,
        @Query(FIELD_FILTER) fieldFilter: String?,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<PublishJob>

    @PUT("{path}")
    @Headers(HEADER_NO_CACHE)
    fun putPublishJob(
        @Header(AUTHORIZATION) authorization: String,
        @Path("{path}", encoded = true) uri: String,
        @Body publishData: BatchPublishToSocialMedia
    ): VimeoCall<PublishJob>

    @POST("{path}")
    fun createAlbum(
        @Header(AUTHORIZATION) authorization: String,
        @Path("{path}", encoded = true) uri: String,
        @Body bodyParams: Map<String, @JvmSuppressWildcards Any>
    ): VimeoCall<Album>

    @PATCH("{path}")
    fun editAlbum(
        @Header(AUTHORIZATION) authorization: String,
        @Path("{path}", encoded = true) uri: String,
        @Body bodyParams: Map<String, @JvmSuppressWildcards Any>
    ): VimeoCall<Album>

    @PUT("{$ALBUM_URI}/{$VIDEO_URI}")
    fun addToAlbum(
        @Header(AUTHORIZATION) authorization: String,
        @Path(ALBUM_URI, encoded = true) albumUri: String,
        @Path(VIDEO_URI, encoded = true) videoUri: String
    ): VimeoCall<Unit>

    @DELETE("{$ALBUM_URI}/{$VIDEO_URI}")
    fun removeFromAlbum(
        @Header(AUTHORIZATION) authorization: String,
        @Path(ALBUM_URI, encoded = true) albumUri: String,
        @Path(VIDEO_URI, encoded = true) videoUri: String
    ): VimeoCall<Unit>

    @PATCH("{$ALBUM_URI}/videos")
    fun modifyVideosInAlbum(
        @Header(AUTHORIZATION) authorization: String,
        @Path(ALBUM_URI, encoded = true) albumUri: String,
        @Body modificationSpecs: ModifyVideosInAlbumSpecs
    ): VimeoCall<VideoList>

    @PATCH("{$VIDEO_URI}/albums")
    fun modifyVideoInAlbums(
        @Header(AUTHORIZATION) authorization: String,
        @Path(VIDEO_URI, encoded = true) videoUri: String,
        @Body modificationSpecs: ModifyVideoInAlbumsSpecs
    ): VimeoCall<AlbumList>

    @PATCH("{path}")
    fun editVideo(
        @Header(AUTHORIZATION) authorization: String,
        @Path("{path}", encoded = true) uri: String,
        @Body bodyParams: Map<String, @JvmSuppressWildcards Any>
    ): VimeoCall<Video>

    @FormUrlEncoded
    @PATCH("{path}")
    fun editUser(
        @Header(AUTHORIZATION) authorization: String,
        @Path("{path}", encoded = true) uri: String,
        @Field(PARAMETER_USERS_NAME) name: String?,
        @Field(PARAMETER_USERS_LOCATION) location: String?,
        @Field(PARAMETER_USERS_BIO) bio: String?
    ): VimeoCall<User>

    @FormUrlEncoded
    @PATCH("{path}")
    fun editPictureCollection(
        @Header(AUTHORIZATION) authorization: String,
        @Path("{path}", encoded = true) uri: String,
        @Field(PARAMETER_ACTIVE) isActive: Boolean
    ): VimeoCall<PictureCollection>

    @PATCH("me/notifications/subscriptions")
    fun editNotificationSubscriptions(
        @Header(AUTHORIZATION) authorization: String,
        @Body subscriptionMap: Map<NotificationType, Boolean>
    ): VimeoCall<NotificationSubscriptions>

    @FormUrlEncoded
    @POST("{path}")
    fun createComment(
        @Header(AUTHORIZATION) authorization: String,
        @Path("{path}", encoded = true) uri: String,
        @Query(PARAMETER_PASSWORD) password: String?,
        @Field(PARAMETER_COMMENT_TEXT_BODY) commentBody: String
    ): VimeoCall<Comment>

    @POST("{path}")
    fun createPictureCollection(
        @Header(AUTHORIZATION) authorization: String,
        @Path("{path}", encoded = true) uri: String
    ): VimeoCall<PictureCollection>

    @Suppress("LongParameterList")
    @FormUrlEncoded
    @POST("{path}")
    fun createFolder(
        @Header(AUTHORIZATION) authorization: String,
        @Path("{path}", encoded = true) uri: String,
        @Field(PARAMETER_FOLDER_NAME) name: String,
        @Field(PARAMETER_FOLDER_PRIVACY) privacy: FolderViewPrivacyType,
        @Field(SLACK_WEBHOOK_ID) slackWebhookId: String?,
        @Field(SLACK_LANGUAGE_PREF) slackLanguagePref: SlackLanguagePreferenceType?,
        @Field(SLACK_USER_PREF) slackUserPref: SlackUserPreferenceType?
    ): VimeoCall<Folder>

    @Suppress("LongParameterList")
    @FormUrlEncoded
    @PATCH("{path}")
    fun editFolder(
        @Header(AUTHORIZATION) authorization: String,
        @Path("{path}", encoded = true) uri: String,
        @Field(PARAMETER_FOLDER_NAME) name: String,
        @Field(PARAMETER_FOLDER_PRIVACY) privacy: FolderViewPrivacyType,
        @Field(SLACK_WEBHOOK_ID) slackWebhookId: String?,
        @Field(SLACK_LANGUAGE_PREF) slackLanguagePref: SlackLanguagePreferenceType?,
        @Field(SLACK_USER_PREF) slackUserPref: SlackUserPreferenceType?
    ): VimeoCall<Folder>

    @PUT("{$FOLDER_URI}/{$VIDEO_URI}")
    fun addToFolder(
        @Header(AUTHORIZATION) authorization: String,
        @Path(FOLDER_URI, encoded = true) folderUri: String,
        @Path(VIDEO_URI, encoded = true) videoUri: String
    ): VimeoCall<Unit>

    @DELETE("{$FOLDER_URI}/{$VIDEO_URI}")
    fun removeFromFolder(
        @Header(AUTHORIZATION) authorization: String,
        @Path(FOLDER_URI, encoded = true) folderUri: String,
        @Path(VIDEO_URI, encoded = true) videoUri: String
    ): VimeoCall<Unit>

    @GET("{path}")
    fun getAppConfiguration(
        @Header(AUTHORIZATION) authorization: String,
        @Path("{path}", encoded = true) uri: String,
        @Query(FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<AppConfiguration>

    @GET("{path}")
    fun getCategory(
        @Header(AUTHORIZATION) authorization: String,
        @Path("{path}", encoded = true) uri: String,
        @Query(FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<Category>

    @GET("{path}")
    fun getChannel(
        @Header(AUTHORIZATION) authorization: String,
        @Path("{path}", encoded = true) uri: String,
        @Query(FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<Channel>

    @GET("{path}")
    fun getComment(
        @Header(AUTHORIZATION) authorization: String,
        @Path("{path}", encoded = true) uri: String,
        @Query(FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<Comment>

    @GET("{path}")
    fun getDocument(
        @Header(AUTHORIZATION) authorization: String,
        @Path("{path}", encoded = true) uri: String
    ): VimeoCall<Document>

    @GET("{path}")
    fun getFolder(
        @Header(AUTHORIZATION) authorization: String,
        @Path("{path}", encoded = true) uri: String,
        @Query(FIELD_FILTER) fieldFilter: String?,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<Folder>

    @GET("{path}")
    fun getTvodItem(
        @Header(AUTHORIZATION) authorization: String,
        @Path("{path}", encoded = true) uri: String,
        @Query(FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<TvodItem>

    @GET("{path}")
    fun getUser(
        @Header(AUTHORIZATION) authorization: String,
        @Path("{path}", encoded = true) uri: String,
        @Query(FIELD_FILTER) fieldFilter: String?,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<User>

    @GET("{path}")
    fun getVideo(
        @Header(AUTHORIZATION) authorization: String,
        @Path("{path}", encoded = true) uri: String,
        @Query(FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<Video>

    @GET("{path}")
    fun getLiveStats(
        @Header(AUTHORIZATION) authorization: String,
        @Path("{path}", encoded = true) uri: String,
        @Query(FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<LiveStats>

    @GET("{path}")
    fun getProduct(
        @Header(AUTHORIZATION) authorization: String,
        @Path("{path}", encoded = true) uri: String,
        @Query(FIELD_FILTER) fieldFilter: String?,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<Product>

    @GET("{path}")
    fun getAlbum(
        @Header(AUTHORIZATION) authorization: String,
        @Path("{path}", encoded = true) uri: String,
        @Query(FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<Album>

    @GET("{path}")
    fun getCategoryList(
        @Header(AUTHORIZATION) authorization: String,
        @Path("{path}", encoded = true) uri: String,
        @Query(FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<CategoryList>

    @GET("{path}")
    fun getChannelList(
        @Header(AUTHORIZATION) authorization: String,
        @Path("{path}", encoded = true) uri: String,
        @Query(FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<ChannelList>

    @GET("{path}")
    fun getCommentList(
        @Header(AUTHORIZATION) authorization: String,
        @Path("{path}", encoded = true) uri: String,
        @Query(FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<CommentList>

    @GET("{path}")
    fun getFolderList(
        @Header(AUTHORIZATION) authorization: String,
        @Path("{path}", encoded = true) uri: String,
        @Query(FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<FolderList>

    @GET("{path}")
    fun getFeedList(
        @Header(AUTHORIZATION) authorization: String,
        @Path("{path}", encoded = true) uri: String,
        @Query(FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<FeedList>

    @GET("{path}")
    fun getProjectItemList(
        @Header(AUTHORIZATION) authorization: String,
        @Path("{path}", encoded = true) uri: String,
        @Query(FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<ProjectItemList>

    @GET("{path}")
    fun getTeamList(
        @Header(AUTHORIZATION) authorization: String,
        @Path("{path}", encoded = true) uri: String,
        @Query(FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<TeamList>

    @GET("{path}")
    fun getNotificationList(
        @Header(AUTHORIZATION) authorization: String,
        @Path("{path}", encoded = true) uri: String,
        @Query(FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<NotificationList>

    @GET("{path}")
    fun getProgramContentItemList(
        @Header(AUTHORIZATION) authorization: String,
        @Path("{path}", encoded = true) uri: String,
        @Query(FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<ProgrammedContentItemList>

    @GET("{path}")
    fun getRecommendationList(
        @Header(AUTHORIZATION) authorization: String,
        @Path("{path}", encoded = true) uri: String,
        @Query(FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<RecommendationList>

    @GET("{path}")
    fun getSearchResultList(
        @Header(AUTHORIZATION) authorization: String,
        @Path("{path}", encoded = true) uri: String,
        @Query(FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<SearchResultList>

    @GET("{path}")
    fun getSeasonList(
        @Header(AUTHORIZATION) authorization: String,
        @Path("{path}", encoded = true) uri: String,
        @Query(FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<SeasonList>

    @GET("{path}")
    fun getTvodItemList(
        @Header(AUTHORIZATION) authorization: String,
        @Path("{path}", encoded = true) uri: String,
        @Query(FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<TvodItemList>

    @GET("{path}")
    fun getUserList(
        @Header(AUTHORIZATION) authorization: String,
        @Path("{path}", encoded = true) uri: String,
        @Query(FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<UserList>

    @GET("{path}")
    fun getVideoList(
        @Header(AUTHORIZATION) authorization: String,
        @Path("{path}", encoded = true) uri: String,
        @Query(FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<VideoList>

    @GET("{path}")
    fun getAlbumList(
        @Header(AUTHORIZATION) authorization: String,
        @Path("{path}", encoded = true) uri: String,
        @Query(FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<AlbumList>

    @GET("{path}")
    fun getTextTrackList(
        @Header(AUTHORIZATION) authorization: String,
        @Path("{path}", encoded = true) uri: String,
        @Query(FIELD_FILTER) fieldFilter: String?,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<TextTrackList>

    @GET("products")
    fun getProducts(
        @Header(AUTHORIZATION) authorization: String,
        @Query(FIELD_FILTER) fieldFilter: String?,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<ProductList>

    @GET("{path}")
    fun getVideoStatus(
        @Header(AUTHORIZATION) authorization: String,
        @Path("{path}", encoded = true) uri: String,
        @Query(FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<VideoStatus>

    @GET("{path}")
    fun getTeamMembers(
        @Header(AUTHORIZATION) authorization: String,
        @Path("{path}", encoded = true) uri: String,
        @Query(FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<TeamMembershipList>

    @Suppress("LongParameterList")
    @FormUrlEncoded
    @POST("{path}")
    fun addUserToTeam(
        @Header(AUTHORIZATION) authorization: String,
        @Path("{path}", encoded = true) uri: String,
        @Field(PARAMETER_EMAIL) email: String,
        @Field(PARAMETER_PERMISSION_LEVEL) permissionLevel: TeamRoleType,
        @Field(PARAMETER_FOLDER_URI) folderUri: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>
    ): VimeoCall<TeamMembership>

    @DELETE("{path}")
    fun removeUserFromTeam(
        @Header(AUTHORIZATION) authorization: String,
        @Path("{path}", encoded = true) uri: String,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>
    ): VimeoCall<Unit>

    @FormUrlEncoded
    @PATCH("{path}")
    fun changeUserRole(
        @Header(AUTHORIZATION) authorization: String,
        @Path("{path}", encoded = true) uri: String,
        @Field(PARAMETER_ROLE) role: TeamRoleType,
        @Field(PARAMETER_FOLDER_URI) folderUri: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>
    ): VimeoCall<TeamMembership>

    @PUT("{path}")
    fun grantUsersAccessToFolder(
        @Header(AUTHORIZATION) authorization: String,
        @Path("{path}", encoded = true) uri: String,
        @Body usersIds: List<GrantFolderPermissionForUser>,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>
    ): VimeoCall<Unit>

    @GET("{path}")
    fun getUnit(
        @Header(AUTHORIZATION) authorization: String,
        @Path("{path}", encoded = true) uri: String,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<Unit>

    @PUT("{path}")
    fun putContentWithUserResponse(
        @Header(AUTHORIZATION) authorization: String,
        @Path("{path}", encoded = true) uri: String,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Body bodyParams: Any
    ): VimeoCall<User>

    @PUT("{path}")
    fun putContentWithUserResponse(
        @Header(AUTHORIZATION) authorization: String,
        @Path("{path}", encoded = true) uri: String,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>
    ): VimeoCall<User>

    @PUT("{path}")
    fun put(
        @Header(AUTHORIZATION) authorization: String,
        @Path("{path}", encoded = true) uri: String,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>
    ): VimeoCall<Unit>

    @PUT("{path}")
    fun put(
        @Header(AUTHORIZATION) authorization: String,
        @Path("{path}", encoded = true) uri: String,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Body bodyParams: Any
    ): VimeoCall<Unit>

    @DELETE("{path}")
    fun delete(
        @Header(AUTHORIZATION) authorization: String,
        @Path("{path}", encoded = true) uri: String,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>
    ): VimeoCall<Unit>

    @POST("{path}")
    fun post(
        @Header(AUTHORIZATION) authorization: String,
        @Path("{path}", encoded = true) uri: String,
        @Body bodyParams: List<@JvmSuppressWildcards Any>
    ): VimeoCall<Unit>

    @PATCH("{path}")
    fun emptyResponsePatch(
        @Header(AUTHORIZATION) authorization: String,
        @Path("{path}", encoded = true) uri: String,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Body bodyParams: Any
    ): VimeoCall<Unit>

    @POST("{path}")
    fun emptyResponsePost(
        @Header(AUTHORIZATION) authorization: String,
        @Path("{path}", encoded = true) uri: String,
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
        private const val TYPE = "type"
        private const val ALBUM_URI = "albumUri"
        private const val VIDEO_URI = "videoUri"
        private const val FOLDER_URI = "folderUri"
        private const val FIELD_FILTER = "fields"
        private const val SLACK_WEBHOOK_ID = "slack_incoming_webhooks_id"
        private const val SLACK_LANGUAGE_PREF = "slack_language_preference"
        private const val SLACK_USER_PREF = "slack_user_preferences"
    }
}
