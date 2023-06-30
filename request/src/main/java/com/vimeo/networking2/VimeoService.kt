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
import com.vimeo.networking2.ApiConstants.Parameters.PARAMETER_VIDEO_URI
import com.vimeo.networking2.enums.ConnectedAppType
import com.vimeo.networking2.enums.FolderViewPrivacyType
import com.vimeo.networking2.enums.NotificationType
import com.vimeo.networking2.enums.SlackLanguagePreferenceType
import com.vimeo.networking2.enums.SlackUserPreferenceType
import com.vimeo.networking2.enums.TeamRoleType
import com.vimeo.networking2.internal.VimeoCall
import com.vimeo.networking2.params.BatchPublishToSocialMedia
import com.vimeo.networking2.params.DeleteTeamPermissionParams
import com.vimeo.networking2.params.GrantFolderPermissionForUser
import com.vimeo.networking2.params.ModifyVideoInAlbumsSpecs
import com.vimeo.networking2.params.ModifyVideosInAlbumSpecs
import com.vimeo.networking2.params.ReplaceTeamPermissionParams
import okhttp3.CacheControl
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.HTTP
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
@Suppress("UndocumentedPublicFunction", "ComplexInterface", "LargeClass")
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

    @GET
    fun getPublishJob(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Query(FIELD_FILTER) fieldFilter: String?,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<PublishJob>

    @PUT
    @Headers(HEADER_NO_CACHE)
    fun putPublishJob(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Body publishData: BatchPublishToSocialMedia
    ): VimeoCall<PublishJob>

    @POST
    fun createAlbum(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Body bodyParams: Map<String, @JvmSuppressWildcards Any>
    ): VimeoCall<Album>

    @PATCH
    fun editAlbum(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
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

    @PATCH
    fun editVideo(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Body bodyParams: Map<String, @JvmSuppressWildcards Any>,
        @Query(FIELD_FILTER) fieldFilter: String?,
    ): VimeoCall<Video>

    @PATCH
    fun editLiveEvent(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Body bodyParams: Map<String, @JvmSuppressWildcards Any>,
        @Query(FIELD_FILTER) fieldFilter: String?,
    ): VimeoCall<LiveEvent>

    @FormUrlEncoded
    @PATCH
    fun editUser(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Field(PARAMETER_USERS_NAME) name: String?,
        @Field(PARAMETER_USERS_LOCATION) location: String?,
        @Field(PARAMETER_USERS_BIO) bio: String?
    ): VimeoCall<User>

    @FormUrlEncoded
    @PATCH
    fun editPictureCollection(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Field(PARAMETER_ACTIVE) isActive: Boolean
    ): VimeoCall<PictureCollection>

    @PATCH("me/notifications/subscriptions")
    fun editNotificationSubscriptions(
        @Header(AUTHORIZATION) authorization: String,
        @Body subscriptionMap: Map<NotificationType, Boolean>
    ): VimeoCall<NotificationSubscriptions>

    @FormUrlEncoded
    @POST
    fun createComment(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Query(PARAMETER_PASSWORD) password: String?,
        @Field(PARAMETER_COMMENT_TEXT_BODY) commentBody: String
    ): VimeoCall<Comment>

    @POST
    fun createNote(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Query(PARAMETER_PASSWORD) password: String?,
        @Body bodyParams: Map<String, @JvmSuppressWildcards Any>,
    ): VimeoCall<Note>

    @JvmSuppressWildcards
    @PATCH
    fun editNote(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Body bodyParams: Map<String, @JvmSuppressWildcards Any>,
    ): VimeoCall<Note>

    @POST
    fun createPictureCollection(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String
    ): VimeoCall<PictureCollection>

    @Suppress("LongParameterList")
    @FormUrlEncoded
    @POST
    fun createFolder(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Field(PARENT_FOLDER_URI) parentFolderUri: String?,
        @Field(PARAMETER_FOLDER_NAME) name: String,
        @Field(PARAMETER_FOLDER_PRIVACY) privacy: FolderViewPrivacyType,
        @Field(SLACK_WEBHOOK_ID) slackWebhookId: String?,
        @Field(SLACK_LANGUAGE_PREF) slackLanguagePref: SlackLanguagePreferenceType?,
        @Field(SLACK_USER_PREF) slackUserPref: SlackUserPreferenceType?
    ): VimeoCall<Folder>

    @Suppress("LongParameterList")
    @FormUrlEncoded
    @PATCH
    fun editFolder(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Field(PARAMETER_FOLDER_NAME) name: String,
        @Field(PARAMETER_FOLDER_PRIVACY) privacy: FolderViewPrivacyType,
        @Field(SLACK_WEBHOOK_ID) slackWebhookId: String?,
        @Field(SLACK_LANGUAGE_PREF) slackLanguagePref: SlackLanguagePreferenceType?,
        @Field(SLACK_USER_PREF) slackUserPref: SlackUserPreferenceType?
    ): VimeoCall<Folder>

    @FormUrlEncoded
    @HTTP(method = "DELETE", hasBody = true)
    fun deleteFolder(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Field(SHOULD_DELETE_CLIPS) shouldDeleteClips: Boolean
    ): VimeoCall<Unit>

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

    @DELETE("{$FOLDER_URI}/items")
    fun removeFromFolder(
        @Header(AUTHORIZATION) authorization: String,
        @Path(FOLDER_URI, encoded = true) folderUri: String,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
    ): VimeoCall<Unit>

    @GET
    fun getAppConfiguration(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Query(FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<AppConfiguration>

    @GET
    fun getCategory(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Query(FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<Category>

    @GET
    fun getChannel(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Query(FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<Channel>

    @GET
    fun getComment(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Query(FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<Comment>

    @GET
    fun getNote(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Query(FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<Note>

    @GET
    fun getDocument(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String
    ): VimeoCall<Document>

    @GET
    fun getFolder(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Query(FIELD_FILTER) fieldFilter: String?,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<Folder>

    @GET
    fun getTvodItem(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Query(FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<TvodItem>

    @GET
    fun getUser(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Query(FIELD_FILTER) fieldFilter: String?,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<User>

    @GET
    fun getVideo(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Query(FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<Video>

    @GET
    fun getLiveEvent(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Query(FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<LiveEvent>

    @POST
    fun createLiveEvent(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Body bodyParams: Map<String, @JvmSuppressWildcards Any>
    ): VimeoCall<LiveEvent>

    @POST("{$LIVE_EVENT_URI}/end")
    fun stopLiveEvent(
        @Header(AUTHORIZATION) authorization: String,
        @Path(LIVE_EVENT_URI, encoded = true) liveEventUri: String,
    ): VimeoCall<Video>

    @GET
    fun getLiveEventList(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Query(FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<LiveEventList>

    @GET
    fun getLiveStats(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Query(FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<LiveStats>

    @GET
    fun getProduct(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Query(FIELD_FILTER) fieldFilter: String?,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<Product>

    @GET
    fun getAlbum(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Query(FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<Album>

    @GET
    fun getCategoryList(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Query(FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<CategoryList>

    @GET
    fun getChannelList(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Query(FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<ChannelList>

    @GET
    fun getCommentList(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Query(FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<CommentList>

    @GET
    fun getNoteList(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Query(FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<NoteList>

    @GET
    fun getFolderList(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Query(FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<FolderList>

    @GET
    fun getTeamPermissions(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Query(FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<TeamPermissionList>

    @GET
    @Suppress("LongParameterList")
    fun getTeamPermissions(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Query(FIELD_FILTER) fieldFilter: String?,
        @Query(QUERY_STRING_PARAM_QUERY) query: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<TeamPermissionList>

    @GET
    fun getPermissionPolicyList(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Query(FIELD_FILTER) fieldFilter: String?,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<PermissionPolicyList>

    @GET
    fun getPermissionPolicy(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Query(FIELD_FILTER) fieldFilter: String?,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<PermissionPolicy>

    @GET
    fun getFeedList(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Query(FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<FeedList>

    @GET
    fun getProjectItemList(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Query(FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<ProjectItemList>

    @GET
    fun getTeamList(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Query(FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<TeamList>

    @GET
    fun getNotificationList(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Query(FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<NotificationList>

    @GET
    fun getProgramContentItemList(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Query(FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<ProgrammedCinemaItemList>

    @GET
    fun getRecommendationList(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Query(FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<RecommendationList>

    @GET
    fun getSearchResultList(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Query(FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<SearchResultList>

    @GET
    fun getSeasonList(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Query(FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<SeasonList>

    @GET
    fun getTvodItemList(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Query(FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<TvodItemList>

    @GET
    fun getUserList(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Query(FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<UserList>

    @GET
    fun getVideoList(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Query(FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<VideoList>

    @GET
    fun getAlbumList(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Query(FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<AlbumList>

    @GET
    fun getTextTrackList(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Query(FIELD_FILTER) fieldFilter: String?,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<TextTrackList>

    @GET("products")
    fun getProducts(
        @Header(AUTHORIZATION) authorization: String,
        @Query(FIELD_FILTER) fieldFilter: String?,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<ProductList>

    @GET("surveys")
    fun getSurveyQuestions(
        @Header(AUTHORIZATION) authorization: String,
        @Query(FIELD_FILTER) fieldFilter: String?,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<UserSegmentSurveyList>

    @GET
    fun getVideoStatus(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Query(FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<VideoStatus>

    @PUT("users/seat/{code}")
    fun acceptTeamInvite(
        @Header(AUTHORIZATION) authorization: String,
        @Path(CODE) code: String
    ): VimeoCall<TeamMembership>

    @GET
    fun getTeamMembers(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Query(FIELD_FILTER) fieldFilter: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<TeamMembershipList>

    @Suppress("LongParameterList")
    @FormUrlEncoded
    @POST
    fun addUserToTeam(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Field(PARAMETER_EMAIL) email: String,
        @Field(PARAMETER_PERMISSION_LEVEL) permissionLevel: TeamRoleType,
        @Field(PARAMETER_FOLDER_URI) folderUri: String?,
        @Field(CUSTOM_MESSAGE) customMessage: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>
    ): VimeoCall<TeamMembership>

    @Suppress("LongParameterList")
    @FormUrlEncoded
    @POST
    fun addUserToVideoAsMember(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Field(PARAMETER_EMAIL) email: String,
        @Field(PARAMETER_PERMISSION_LEVEL) permissionLevel: TeamRoleType,
        @Field(PARAMETER_VIDEO_URI) videoUri: String?,
        @Field(CUSTOM_MESSAGE) customMessage: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>
    ): VimeoCall<TeamMembership>

    @DELETE
    fun removeUserFromTeam(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>
    ): VimeoCall<Unit>

    @FormUrlEncoded
    @PATCH
    fun changeUserRole(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Field(PARAMETER_ROLE) role: TeamRoleType,
        @Field(PARAMETER_FOLDER_URI) folderUri: String?,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>
    ): VimeoCall<TeamMembership>

    @PUT
    fun grantUsersAccessToFolder(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Body usersIds: List<GrantFolderPermissionForUser>,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>
    ): VimeoCall<Unit>

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
        @Body bodyParams: Any
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
        @Body bodyParams: Any
    ): VimeoCall<Unit>

    @PUT
    fun putTeamPermission(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Body bodyParams: ReplaceTeamPermissionParams
    ): VimeoCall<Unit>

    @DELETE
    fun delete(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @QueryMap queryParams: Map<String, @JvmSuppressWildcards String>
    ): VimeoCall<Unit>

    @HTTP(method = "DELETE", hasBody = true)
    fun deleteTeamPermission(
        @Header(AUTHORIZATION) authorization: String,
        @Url uri: String,
        @Body bodyParams: DeleteTeamPermissionParams
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

    @Headers(HEADER_NO_CACHE)
    @GET("users/{$TEAM_OWNER_ID}/capabilities")
    fun capabilities(
        @Header(AUTHORIZATION) authorization: String,
        @Path(TEAM_OWNER_ID, encoded = true) teamOwnerId: String,
        @Query(FIELD_FILTER) fieldFilter: String?,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?
    ): VimeoCall<Capabilities>

    @GET("users/{$TEAM_OWNER_ID}/team/featured_content")
    fun getFeaturedContent(
        @Header(AUTHORIZATION) authorization: String,
        @Path(TEAM_OWNER_ID, encoded = true) teamOwnerId: String,
        @Query(FIELD_FILTER) fieldFilter: String?,
        @Header(CACHE_CONTROL) cacheControl: CacheControl?,
    ): VimeoCall<FeaturedContent>

    @FormUrlEncoded
    @POST("/custom_domains/domains")
    fun getCustomDomains(
        @Header(AUTHORIZATION) authorization: String,
        @Field(EMAIL) email: String
    ): VimeoCall<CustomDomains>

    companion object {
        private const val CACHE_CONTROL = "Cache-Control"
        private const val AUTHORIZATION = "Authorization"
        private const val HEADER_NO_CACHE = "Cache-Control: no-cache, no-store"
        private const val TYPE = "type"
        private const val CODE = "code"
        private const val ALBUM_URI = "albumUri"
        private const val VIDEO_URI = "videoUri"
        private const val FOLDER_URI = "folderUri"
        private const val FIELD_FILTER = "fields"
        private const val PARENT_FOLDER_URI = "parent_folder_uri"
        private const val SHOULD_DELETE_CLIPS = "should_delete_clips"
        private const val SLACK_WEBHOOK_ID = "slack_incoming_webhooks_id"
        private const val SLACK_LANGUAGE_PREF = "slack_language_preference"
        private const val SLACK_USER_PREF = "slack_user_preferences"
        private const val QUERY_STRING_PARAM_QUERY = "query"
        private const val CUSTOM_MESSAGE = "custom_message"
        private const val TEAM_OWNER_ID = "teamOwnerId"
        private const val LIVE_EVENT_URI = "liveEventUri"
        private const val EMAIL = "email"
    }
}
