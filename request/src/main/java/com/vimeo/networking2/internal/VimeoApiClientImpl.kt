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
package com.vimeo.networking2.internal

import com.vimeo.networking2.Album
import com.vimeo.networking2.AlbumList
import com.vimeo.networking2.AlbumPrivacy
import com.vimeo.networking2.ApiConstants
import com.vimeo.networking2.ApiError
import com.vimeo.networking2.AppConfiguration
import com.vimeo.networking2.Authenticator
import com.vimeo.networking2.Capabilities
import com.vimeo.networking2.Category
import com.vimeo.networking2.CategoryList
import com.vimeo.networking2.Channel
import com.vimeo.networking2.ChannelList
import com.vimeo.networking2.ChapterList
import com.vimeo.networking2.Comment
import com.vimeo.networking2.CommentList
import com.vimeo.networking2.ConnectedApp
import com.vimeo.networking2.ConnectedAppList
import com.vimeo.networking2.Coordinates
import com.vimeo.networking2.CustomDomains
import com.vimeo.networking2.Document
import com.vimeo.networking2.FeaturedContent
import com.vimeo.networking2.FeedList
import com.vimeo.networking2.Folder
import com.vimeo.networking2.FolderList
import com.vimeo.networking2.InvalidParameter
import com.vimeo.networking2.LiveEvent
import com.vimeo.networking2.LiveEventList
import com.vimeo.networking2.LiveStats
import com.vimeo.networking2.Note
import com.vimeo.networking2.NoteList
import com.vimeo.networking2.NoteStatus
import com.vimeo.networking2.NotificationList
import com.vimeo.networking2.NotificationSubscriptions
import com.vimeo.networking2.PermissionPolicy
import com.vimeo.networking2.PermissionPolicyList
import com.vimeo.networking2.PictureCollection
import com.vimeo.networking2.Product
import com.vimeo.networking2.ProductList
import com.vimeo.networking2.ProgrammedCinemaItemList
import com.vimeo.networking2.ProjectItemList
import com.vimeo.networking2.PublishJob
import com.vimeo.networking2.RecommendationList
import com.vimeo.networking2.SearchResultList
import com.vimeo.networking2.SeasonList
import com.vimeo.networking2.StreamPrivacy
import com.vimeo.networking2.Team
import com.vimeo.networking2.TeamEntity
import com.vimeo.networking2.TeamList
import com.vimeo.networking2.TeamMembership
import com.vimeo.networking2.TeamMembershipList
import com.vimeo.networking2.TeamPermission
import com.vimeo.networking2.TeamPermissionCurrentPermissions
import com.vimeo.networking2.TeamPermissionInteraction
import com.vimeo.networking2.TeamPermissionList
import com.vimeo.networking2.TextTrackList
import com.vimeo.networking2.TvodItem
import com.vimeo.networking2.TvodItemList
import com.vimeo.networking2.User
import com.vimeo.networking2.UserList
import com.vimeo.networking2.UserSegmentSurveyList
import com.vimeo.networking2.Video
import com.vimeo.networking2.VideoList
import com.vimeo.networking2.VideoStatus
import com.vimeo.networking2.VimeoApiClient
import com.vimeo.networking2.VimeoCallback
import com.vimeo.networking2.VimeoRequest
import com.vimeo.networking2.VimeoService
import com.vimeo.networking2.common.Followable
import com.vimeo.networking2.config.VimeoApiConfiguration
import com.vimeo.networking2.enums.CommentPrivacyType
import com.vimeo.networking2.enums.ConnectedAppType
import com.vimeo.networking2.enums.EmbedPrivacyType
import com.vimeo.networking2.enums.ErrorCodeType
import com.vimeo.networking2.enums.FolderViewPrivacyType
import com.vimeo.networking2.enums.NotificationType
import com.vimeo.networking2.enums.SlackLanguagePreferenceType
import com.vimeo.networking2.enums.SlackUserPreferenceType
import com.vimeo.networking2.enums.StringValue
import com.vimeo.networking2.enums.TeamEntityType
import com.vimeo.networking2.enums.TeamRoleType
import com.vimeo.networking2.enums.ViewPrivacyType
import com.vimeo.networking2.params.BatchPublishToSocialMedia
import com.vimeo.networking2.params.DeleteTeamPermissionParams
import com.vimeo.networking2.params.GrantFolderPermissionForUser
import com.vimeo.networking2.params.ModifyVideoInAlbumsSpecs
import com.vimeo.networking2.params.ModifyVideosInAlbumSpecs
import com.vimeo.networking2.params.ReplaceTeamPermissionParams
import com.vimeo.networking2.params.Schedule
import com.vimeo.networking2.params.SearchDateType
import com.vimeo.networking2.params.SearchDurationType
import com.vimeo.networking2.params.SearchFacetType
import com.vimeo.networking2.params.SearchFilterType
import com.vimeo.networking2.params.SearchSortDirectionType
import com.vimeo.networking2.params.SearchSortType
import com.vimeo.networking2.teamPermissionsUri
import com.vimeo.networking2.type
import okhttp3.CacheControl

/**
 * The internal implementation of the [VimeoApiClient]. Once configured, this client cannot be re-configured.
 *
 * @param vimeoService The service used to make requests to the Vimeo API.
 * @param authenticator The authenticator used to obtain tokens which can be used to make requests.
 * @param vimeoApiConfiguration The configuration used by this client instance.
 * @param basicAuthHeader The basic auth header using the client ID and secret, used if the account store does not
 * provide an authenticated account.
 * @param localVimeoCallAdapter The adapter used to notify consumers of local errors.
 */
@Suppress("LargeClass")
internal class VimeoApiClientImpl(
    private val vimeoService: VimeoService,
    private val authenticator: Authenticator,
    private val vimeoApiConfiguration: VimeoApiConfiguration,
    private val basicAuthHeader: String,
    private val localVimeoCallAdapter: LocalVimeoCallAdapter
) : VimeoApiClient {

    private val authHeader: String
        get() = authenticator.currentAccount?.accessToken?.let { "Bearer $it" } ?: basicAuthHeader

    override fun createAlbum(
        uri: String,
        name: String,
        albumPrivacy: AlbumPrivacy,
        description: String?,
        bodyParams: Map<String, Any>?,
        callback: VimeoCallback<Album>
    ): VimeoRequest {
        val safeUri = uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        val body = bodyParams.intoMutableMap()
        body[ApiConstants.Parameters.PARAMETER_ALBUM_NAME] = name
        body[ApiConstants.Parameters.PARAMETER_ALBUM_PRIVACY] = albumPrivacy.viewPrivacy
            ?: error(INVALID_ENUM_MESSAGE)
        if (description != null) {
            body[ApiConstants.Parameters.PARAMETER_ALBUM_DESCRIPTION] = description
        }
        if (albumPrivacy.password != null) {
            body[ApiConstants.Parameters.PARAMETER_ALBUM_PASSWORD] = requireNotNull(albumPrivacy.password)
        }
        return vimeoService.createAlbum(authHeader, safeUri, body).enqueue(callback)
    }

    override fun createAlbum(
        user: User,
        name: String,
        albumPrivacy: AlbumPrivacy,
        description: String?,
        bodyParams: Map<String, Any>?,
        callback: VimeoCallback<Album>
    ): VimeoRequest {
        val safeUri = user.metadata?.connections?.albums?.uri.validate()
            ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return createAlbum(safeUri, name, albumPrivacy, description, bodyParams, callback)
    }

    override fun editAlbum(
        album: Album,
        name: String,
        albumPrivacy: AlbumPrivacy,
        description: String?,
        bodyParams: Map<String, Any>?,
        callback: VimeoCallback<Album>
    ): VimeoRequest {
        val uri = album.uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return editAlbum(uri, name, albumPrivacy, description, bodyParams, callback)
    }

    override fun editAlbum(
        uri: String,
        name: String,
        albumPrivacy: AlbumPrivacy,
        description: String?,
        bodyParams: Map<String, Any>?,
        callback: VimeoCallback<Album>
    ): VimeoRequest {
        val safeUri = uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        val body = bodyParams.intoMutableMap()
        body[ApiConstants.Parameters.PARAMETER_ALBUM_NAME] = name
        body[ApiConstants.Parameters.PARAMETER_ALBUM_PRIVACY] = albumPrivacy.viewPrivacy
            ?: error(INVALID_ENUM_MESSAGE)
        if (description != null) {
            body[ApiConstants.Parameters.PARAMETER_ALBUM_DESCRIPTION] = description
        }
        if (albumPrivacy.password != null) {
            body[ApiConstants.Parameters.PARAMETER_ALBUM_PASSWORD] = requireNotNull(albumPrivacy.password)
        }
        return vimeoService.editAlbum(authHeader, safeUri, body).enqueue(callback)
    }

    override fun deleteAlbum(album: Album, callback: VimeoCallback<Unit>): VimeoRequest {
        val uri = album.uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return deleteContent(uri, emptyMap(), callback)
    }

    override fun addToAlbum(album: Album, video: Video, callback: VimeoCallback<Unit>): VimeoRequest {
        val albumUri = album.uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        val videoUri = video.uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return addToAlbum(albumUri, videoUri, callback)
    }

    override fun addToAlbum(albumUri: String, videoUri: String, callback: VimeoCallback<Unit>): VimeoRequest {
        val safeAlbumUri = albumUri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        val safeVideoUri = videoUri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return vimeoService.addToAlbum(authHeader, safeAlbumUri, safeVideoUri).enqueue(callback)
    }

    override fun removeFromAlbum(album: Album, video: Video, callback: VimeoCallback<Unit>): VimeoRequest {
        val albumUri = album.uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        val videoUri = video.uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return removeFromAlbum(albumUri, videoUri, callback)
    }

    override fun removeFromAlbum(albumUri: String, videoUri: String, callback: VimeoCallback<Unit>): VimeoRequest {
        val safeAlbumUri = albumUri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        val safeVideoUri = videoUri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return vimeoService.removeFromAlbum(authHeader, safeAlbumUri, safeVideoUri).enqueue(callback)
    }

    override fun modifyVideosInAlbum(
        album: Album,
        modificationSpecs: ModifyVideosInAlbumSpecs,
        callback: VimeoCallback<VideoList>
    ): VimeoRequest {
        val uri = album.uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return modifyVideosInAlbum(uri, modificationSpecs, callback)
    }

    override fun modifyVideosInAlbum(
        uri: String,
        modificationSpecs: ModifyVideosInAlbumSpecs,
        callback: VimeoCallback<VideoList>
    ): VimeoRequest {
        val safeUri = uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return vimeoService.modifyVideosInAlbum(authHeader, safeUri, modificationSpecs).enqueue(callback)
    }

    override fun modifyVideoInAlbums(
        video: Video,
        modificationSpecs: ModifyVideoInAlbumsSpecs,
        callback: VimeoCallback<AlbumList>
    ): VimeoRequest {
        val uri = video.uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return modifyVideoInAlbums(uri, modificationSpecs, callback)
    }

    override fun modifyVideoInAlbums(
        uri: String,
        modificationSpecs: ModifyVideoInAlbumsSpecs,
        callback: VimeoCallback<AlbumList>
    ): VimeoRequest {
        val safeUri = uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return vimeoService.modifyVideoInAlbums(authHeader, safeUri, modificationSpecs).enqueue(callback)
    }

    @Suppress("ComplexMethod")
    override fun editVideo(
        uri: String,
        title: String?,
        description: String?,
        password: String?,
        commentPrivacyType: CommentPrivacyType?,
        allowDownload: Boolean?,
        allowAddToCollections: Boolean?,
        allowShareLink: Boolean?,
        embedPrivacyType: EmbedPrivacyType?,
        viewPrivacyType: ViewPrivacyType?,
        bodyParams: Map<String, Any>?,
        fieldFilter: String?,
        callback: VimeoCallback<Video>
    ): VimeoRequest {
        val safeUri = uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        val body = bodyParams.intoMutableMap()
        if (title != null) {
            body[ApiConstants.Parameters.PARAMETER_VIDEO_NAME] = title
        }
        if (description != null) {
            body[ApiConstants.Parameters.PARAMETER_VIDEO_DESCRIPTION] = description
        }
        if (password != null) {
            body[ApiConstants.Parameters.PARAMETER_VIDEO_PASSWORD] = password
        }
        val privacy = mutableMapOf<String, Any>()
        if (commentPrivacyType != null) {
            privacy[ApiConstants.Parameters.PARAMETER_VIDEO_COMMENTS] = commentPrivacyType.value
                ?: error(INVALID_ENUM_MESSAGE)
        }
        if (allowDownload != null) {
            privacy[ApiConstants.Parameters.PARAMETER_VIDEO_DOWNLOAD] = allowDownload
        }
        if (allowAddToCollections != null) {
            privacy[ApiConstants.Parameters.PARAMETER_VIDEO_ADD] = allowAddToCollections
        }
        if (allowShareLink != null) {
            privacy[ApiConstants.Parameters.PARAMETER_ALLOW_SHARE_LINK] = allowShareLink
        }
        if (embedPrivacyType != null) {
            privacy[ApiConstants.Parameters.PARAMETER_VIDEO_EMBED] = embedPrivacyType.value
                ?: error(INVALID_ENUM_MESSAGE)
        }
        if (viewPrivacyType != null) {
            privacy[ApiConstants.Parameters.PARAMETER_VIDEO_VIEW] = viewPrivacyType.value
                ?: error(INVALID_ENUM_MESSAGE)
        }
        if (privacy.isNotEmpty()) {
            body[ApiConstants.Parameters.PARAMETER_VIDEO_PRIVACY] = privacy
        }

        return vimeoService.editVideo(authHeader, safeUri, body, fieldFilter).enqueue(callback)
    }

    override fun editVideo(
        video: Video,
        title: String?,
        description: String?,
        password: String?,
        commentPrivacyType: CommentPrivacyType?,
        allowDownload: Boolean?,
        allowAddToCollections: Boolean?,
        allowShareLink: Boolean?,
        embedPrivacyType: EmbedPrivacyType?,
        viewPrivacyType: ViewPrivacyType?,
        bodyParams: Map<String, Any>?,
        fieldFilter: String?,
        callback: VimeoCallback<Video>
    ): VimeoRequest {
        val uri = video.uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return editVideo(
            uri,
            title,
            description,
            password,
            commentPrivacyType,
            allowDownload,
            allowAddToCollections,
            allowShareLink,
            embedPrivacyType,
            viewPrivacyType,
            bodyParams,
            fieldFilter,
            callback
        )
    }

    @Suppress("ComplexMethod")
    override fun editLiveEvent(
        liveEvent: LiveEvent,
        title: String?,
        streamTitle: String?,
        description: String?,
        password: String?,
        commentPrivacyType: CommentPrivacyType?,
        allowDownload: Boolean?,
        allowAddToCollections: Boolean?,
        allowShareLink: Boolean?,
        embedPrivacyType: EmbedPrivacyType?,
        viewPrivacyType: ViewPrivacyType?,
        schedule: Schedule?,
        enableLiveChat: Boolean?,
        bodyParams: Map<String, Any>?,
        fieldFilter: String?,
        callback: VimeoCallback<LiveEvent>
    ): VimeoRequest {
        val safeUri = liveEvent.uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        val body = bodyParams.intoMutableMap()
        if (title != null) {
            body[ApiConstants.Parameters.PARAMETER_LIVE_EVENT_TITLE] = title
        }
        if (streamTitle != null) {
            body[ApiConstants.Parameters.PARAMETER_LIVE_EVENT_STREAM_TITLE] = streamTitle
        }
        if (description != null) {
            body[ApiConstants.Parameters.PARAMETER_LIVE_EVENT_DESCRIPTION] = description
        }
        if (password != null) {
            body[ApiConstants.Parameters.PARAMETER_LIVE_EVENT_PASSWORD] = password
        }
        val privacy = mutableMapOf<String, Any>()
        if (commentPrivacyType != null) {
            privacy[ApiConstants.Parameters.PARAMETER_VIDEO_COMMENTS] = commentPrivacyType.value
                ?: error(INVALID_ENUM_MESSAGE)
        }
        if (allowDownload != null) {
            privacy[ApiConstants.Parameters.PARAMETER_VIDEO_DOWNLOAD] = allowDownload
        }
        if (allowAddToCollections != null) {
            privacy[ApiConstants.Parameters.PARAMETER_VIDEO_ADD] = allowAddToCollections
        }
        if (embedPrivacyType != null) {
            privacy[ApiConstants.Parameters.PARAMETER_VIDEO_EMBED] = embedPrivacyType.value
                ?: error(INVALID_ENUM_MESSAGE)
        }
        if (viewPrivacyType != null) {
            privacy[ApiConstants.Parameters.PARAMETER_VIDEO_VIEW] = viewPrivacyType.value
                ?: error(INVALID_ENUM_MESSAGE)
        }
        if (privacy.isNotEmpty()) {
            body[ApiConstants.Parameters.PARAMETER_STREAMING_PRIVACY] = privacy
        }
        if (schedule != null) {
            body[ApiConstants.Parameters.PARAMETER_LIVE_SCHEDULE] = schedule
        }
        if (enableLiveChat != null) {
            body[ApiConstants.Parameters.PARAMETER_LIVE_CHAT_ENABLED] = enableLiveChat
        }
        if (allowShareLink != null) {
            body[ApiConstants.Parameters.PARAMETER_ALLOW_SHARE_LINK] = allowShareLink
        }

        return vimeoService.editLiveEvent(authHeader, safeUri, body, fieldFilter).enqueue(callback)
    }

    override fun editUser(
        uri: String,
        name: String?,
        location: String?,
        bio: String?,
        callback: VimeoCallback<User>
    ): VimeoRequest {
        val safeUri = uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return vimeoService.editUser(authHeader, safeUri, name, location, bio).enqueue(callback)
    }

    override fun editUser(
        user: User,
        name: String?,
        location: String?,
        bio: String?,
        callback: VimeoCallback<User>
    ): VimeoRequest {
        val uri = user.uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return editUser(uri, name, location, bio, callback)
    }

    override fun editSubscriptions(
        subscriptionMap: Map<NotificationType, Boolean>,
        callback: VimeoCallback<NotificationSubscriptions>
    ): VimeoRequest {
        return vimeoService.editNotificationSubscriptions(authHeader, subscriptionMap).enqueue(callback)
    }

    override fun fetchConnectedApps(
        fieldFilter: String?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<ConnectedAppList>
    ): VimeoRequest {
        return vimeoService.getConnectedAppList(authHeader, fieldFilter, cacheControl)
            .enqueue(callback)
    }

    override fun fetchConnectedApp(
        type: ConnectedAppType,
        fieldFilter: String?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<ConnectedApp>
    ): VimeoRequest {
        return vimeoService.getConnectedApp(authHeader, type.validate(), fieldFilter, cacheControl).enqueue(callback)
    }

    override fun createConnectedApp(
        type: ConnectedAppType,
        authorization: String,
        clientId: String,
        callback: VimeoCallback<ConnectedApp>
    ): VimeoRequest {
        return vimeoService.createConnectedApp(
            authHeader,
            type.validate(),
            authorization,
            type.validate(),
            clientId
        ).enqueue(callback)
    }

    override fun deleteConnectedApp(type: ConnectedAppType, callback: VimeoCallback<Unit>): VimeoRequest {
        return vimeoService.deleteConnectedApp(authHeader, type.validate()).enqueue(callback)
    }

    override fun createFolder(
        uri: String,
        parentFolderUri: String?,
        name: String,
        privacy: FolderViewPrivacyType,
        slackWebhookId: String?,
        slackLanguagePreference: SlackLanguagePreferenceType?,
        slackUserPreference: SlackUserPreferenceType?,
        callback: VimeoCallback<Folder>
    ): VimeoRequest {
        val safeUri = uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return vimeoService.createFolder(
            authHeader,
            safeUri,
            parentFolderUri,
            name,
            privacy,
            slackWebhookId,
            slackLanguagePreference,
            slackUserPreference
        ).enqueue(callback)
    }

    override fun createFolder(
        user: User,
        parentFolder: Folder?,
        name: String,
        privacy: FolderViewPrivacyType,
        slackWebhookId: String?,
        slackLanguagePreference: SlackLanguagePreferenceType?,
        slackUserPreference: SlackUserPreferenceType?,
        callback: VimeoCallback<Folder>
    ): VimeoRequest {
        val safeUri = user.metadata?.connections?.folders?.uri.validate()
            ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return vimeoService.createFolder(
            authHeader,
            safeUri,
            parentFolder?.uri,
            name,
            privacy,
            slackWebhookId,
            slackLanguagePreference,
            slackUserPreference
        ).enqueue(callback)
    }

    override fun deleteFolder(
        folder: Folder,
        shouldDeleteClips: Boolean,
        callback: VimeoCallback<Unit>
    ): VimeoRequest {
        val uri = folder.uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return vimeoService.deleteFolder(authHeader, uri, shouldDeleteClips).enqueue(callback)
    }

    override fun editFolder(
        uri: String,
        name: String,
        privacy: FolderViewPrivacyType,
        slackWebhookId: String?,
        slackLanguagePreference: SlackLanguagePreferenceType?,
        slackUserPreference: SlackUserPreferenceType?,
        callback: VimeoCallback<Folder>
    ): VimeoRequest {
        val safeUri = uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return vimeoService.editFolder(
            authHeader,
            safeUri,
            name,
            privacy,
            slackWebhookId,
            slackLanguagePreference,
            slackUserPreference
        ).enqueue(callback)
    }

    override fun editFolder(
        folder: Folder,
        name: String,
        privacy: FolderViewPrivacyType,
        slackWebhookId: String?,
        slackLanguagePreference: SlackLanguagePreferenceType?,
        slackUserPreference: SlackUserPreferenceType?,
        callback: VimeoCallback<Folder>
    ): VimeoRequest {
        val safeUri = folder.uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return vimeoService.editFolder(
            authHeader,
            safeUri,
            name,
            privacy,
            slackWebhookId,
            slackLanguagePreference,
            slackUserPreference
        ).enqueue(callback)
    }

    override fun addToFolder(folder: Folder, video: Video, callback: VimeoCallback<Unit>): VimeoRequest {
        val folderUri = folder.uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        val videoUri = video.uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return addToFolder(folderUri, videoUri, callback)
    }

    override fun addToFolder(folderUri: String, videoUri: String, callback: VimeoCallback<Unit>): VimeoRequest {
        val safeFolderUri = folderUri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        val safeVideoUri = videoUri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return vimeoService.addToFolder(authHeader, safeFolderUri, safeVideoUri).enqueue(callback)
    }

    override fun removeFromFolder(folder: Folder, video: Video, callback: VimeoCallback<Unit>): VimeoRequest {
        val folderUri = folder.uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        val videoUri = video.uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return removeFromFolder(folderUri, videoUri, callback)
    }

    override fun removeFromFolder(folderUri: String, videoUri: String, callback: VimeoCallback<Unit>): VimeoRequest {
        val safeFolderUri = folderUri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        val safeVideoUri = videoUri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return vimeoService.removeFromFolder(authHeader, safeFolderUri, safeVideoUri).enqueue(callback)
    }

    override fun removeFromFolder(
        folderUri: String,
        queryParams: Map<String, String>?,
        callback: VimeoCallback<Unit>
    ): VimeoRequest {
        val safeFolderUri = folderUri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return vimeoService.removeFromFolder(authHeader, safeFolderUri, queryParams.orEmpty()).enqueue(callback)
    }

    override fun fetchPublishJob(
        uri: String,
        fieldFilter: String?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<PublishJob>
    ): VimeoRequest {
        val safeUri = uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return vimeoService.getPublishJob(authHeader, safeUri, fieldFilter, cacheControl)
            .enqueue(callback)
    }

    override fun putPublishJob(
        uri: String,
        publishData: BatchPublishToSocialMedia,
        callback: VimeoCallback<PublishJob>
    ): VimeoRequest {
        val safeUri = uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return vimeoService.putPublishJob(authHeader, safeUri, publishData).enqueue(callback)
    }

    override fun putPublishJob(
        video: Video,
        publishData: BatchPublishToSocialMedia,
        callback: VimeoCallback<PublishJob>
    ): VimeoRequest {
        val uri = video.uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return putPublishJob(uri, publishData, callback)
    }

    override fun fetchTermsOfService(cacheControl: CacheControl?, callback: VimeoCallback<Document>): VimeoRequest {
        return vimeoService.getDocument(authHeader, ApiConstants.Endpoints.ENDPOINT_TERMS_OF_SERVICE).enqueue(callback)
    }

    override fun fetchPrivacyPolicy(cacheControl: CacheControl?, callback: VimeoCallback<Document>): VimeoRequest {
        return vimeoService.getDocument(authHeader, ApiConstants.Endpoints.ENDPOINT_PRIVACY_POLICY).enqueue(callback)
    }

    override fun fetchPaymentAddendum(cacheControl: CacheControl?, callback: VimeoCallback<Document>): VimeoRequest {
        return vimeoService.getDocument(authHeader, ApiConstants.Endpoints.ENDPOINT_PAYMENT_ADDENDUM).enqueue(callback)
    }

    override fun fetchDocument(
        uri: String,
        cacheControl: CacheControl?,
        callback: VimeoCallback<Document>
    ): VimeoRequest {
        val safeUri = uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return vimeoService.getDocument(authHeader, safeUri).enqueue(callback)
    }

    override fun fetchFolder(
        uri: String,
        fieldFilter: String?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<Folder>
    ): VimeoRequest {
        val safeUri = uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return vimeoService.getFolder(authHeader, safeUri, fieldFilter, cacheControl).enqueue(callback)
    }

    override fun fetchFolderList(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<FolderList>
    ): VimeoRequest {
        val safeUri = uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return vimeoService.getFolderList(
            authHeader,
            safeUri,
            fieldFilter,
            queryParams.orEmpty(),
            cacheControl
        ).enqueue(callback)
    }

    override fun fetchPermissionPolicyList(
        uri: String,
        fieldFilter: String?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<PermissionPolicyList>
    ): VimeoRequest {
        val safeUri = uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)

        return vimeoService
            .getPermissionPolicyList(authHeader, safeUri, fieldFilter, cacheControl)
            .enqueue(callback)
    }

    override fun fetchPermissionPolicyList(
        user: User,
        fieldFilter: String?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<PermissionPolicyList>
    ): VimeoRequest {
        val safeUri = user.metadata?.connections?.permissionPolicies?.uri
            ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)

        return fetchPermissionPolicyList(safeUri, fieldFilter, cacheControl, callback)
    }

    override fun fetchPermissionPolicy(
        uri: String,
        fieldFilter: String?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<PermissionPolicy>
    ): VimeoRequest {
        val safeUri = uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)

        return vimeoService.getPermissionPolicy(authHeader, safeUri, fieldFilter, cacheControl).enqueue(callback)
    }

    override fun fetchPermissionPolicy(
        permissionPolicy: PermissionPolicy,
        fieldFilter: String?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<PermissionPolicy>
    ): VimeoRequest {
        val safeUri = permissionPolicy.uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)

        return vimeoService.getPermissionPolicy(authHeader, safeUri, fieldFilter, cacheControl).enqueue(callback)
    }

    override fun fetchPermissionPolicy(
        permissionPolicy: TeamPermissionCurrentPermissions,
        fieldFilter: String?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<PermissionPolicy>
    ): VimeoRequest {
        val safeUri = permissionPolicy.permissionPolicyUri.validate()
            ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)

        return vimeoService.getPermissionPolicy(authHeader, safeUri, fieldFilter, cacheControl).enqueue(callback)
    }

    override fun fetchTeamPermissions(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<TeamPermissionList>
    ): VimeoRequest {
        val safeUri = uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)

        return vimeoService.getTeamPermissions(
            authHeader,
            safeUri,
            fieldFilter,
            queryParams.orEmpty(),
            cacheControl
        ).enqueue(callback)
    }

    override fun fetchTeamPermissions(
        folder: Folder,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<TeamPermissionList>,
        teamEntityQuery: String?
    ): VimeoRequest {
        val safeUri = folder.teamPermissionsUri?.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)

        return vimeoService.getTeamPermissions(
            authHeader,
            safeUri,
            fieldFilter,
            teamEntityQuery,
            queryParams.orEmpty(),
            cacheControl
        ).enqueue(callback)
    }

    override fun replaceTeamPermission(
        uri: String,
        permissionPolicyUri: String,
        teamEntityType: TeamEntityType,
        teamEntityUri: String,
        callback: VimeoCallback<Unit>
    ): VimeoRequest {
        val safeUri = uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)

        val replaceTeamPermissionParams = ReplaceTeamPermissionParams(
            permissionPolicyUri = permissionPolicyUri,
            teamEntityType = teamEntityType,
            teamEntityUri = teamEntityUri
        )

        return vimeoService.putTeamPermission(authHeader, safeUri, replaceTeamPermissionParams).enqueue(callback)
    }

    override fun replaceTeamPermission(
        teamPermission: TeamPermission,
        permissionPolicy: PermissionPolicy,
        teamEntity: TeamEntity,
        callback: VimeoCallback<Unit>
    ): VimeoRequest {
        val safeUri = teamPermission.metadata?.interactions?.edit?.uri
            ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)

        val replaceTeamPermissionParams = ReplaceTeamPermissionParams(
            permissionPolicyUri = permissionPolicy.uri,
            teamEntityType = teamEntity.type,
            teamEntityUri = teamEntity.uri
        )

        return vimeoService.putTeamPermission(authHeader, safeUri, replaceTeamPermissionParams).enqueue(callback)
    }

    override fun replaceTeamPermission(
        teamPermissionInteraction: TeamPermissionInteraction,
        permissionPolicy: PermissionPolicy,
        teamEntity: TeamEntity,
        callback: VimeoCallback<Unit>
    ): VimeoRequest {
        val safeUri = teamPermissionInteraction.edit?.uri ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)

        val replaceTeamPermissionParams = ReplaceTeamPermissionParams(
            permissionPolicyUri = permissionPolicy.uri,
            teamEntityType = teamEntity.type,
            teamEntityUri = teamEntity.uri
        )

        return vimeoService.putTeamPermission(authHeader, safeUri, replaceTeamPermissionParams).enqueue(callback)
    }

    override fun replaceTeamPermission(
        teamPermission: TeamPermission,
        permissionPolicy: TeamPermissionCurrentPermissions,
        teamEntity: TeamEntity,
        callback: VimeoCallback<Unit>
    ): VimeoRequest {
        val safeUri = teamPermission.metadata?.interactions?.edit?.uri
            ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)

        val replaceTeamPermissionParams = ReplaceTeamPermissionParams(
            permissionPolicyUri = permissionPolicy.permissionPolicyUri,
            teamEntityType = teamEntity.type,
            teamEntityUri = teamEntity.uri
        )

        return vimeoService.putTeamPermission(authHeader, safeUri, replaceTeamPermissionParams).enqueue(callback)
    }

    override fun replaceTeamPermission(
        teamPermissionInteraction: TeamPermissionInteraction,
        permissionPolicy: TeamPermissionCurrentPermissions,
        teamEntity: TeamEntity,
        callback: VimeoCallback<Unit>
    ): VimeoRequest {
        val safeUri = teamPermissionInteraction.edit?.uri
            ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)

        val replaceTeamPermissionParams = ReplaceTeamPermissionParams(
            permissionPolicyUri = permissionPolicy.permissionPolicyUri,
            teamEntityType = teamEntity.type,
            teamEntityUri = teamEntity.uri
        )

        return vimeoService.putTeamPermission(authHeader, safeUri, replaceTeamPermissionParams).enqueue(callback)
    }

    override fun deleteTeamPermission(
        uri: String,
        teamEntityType: TeamEntityType,
        teamEntityUri: String,
        callback: VimeoCallback<Unit>
    ): VimeoRequest {
        val safeUri = uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)

        val deleteTeamPermissionParams = DeleteTeamPermissionParams(teamEntityType, teamEntityUri)

        return vimeoService.deleteTeamPermission(authHeader, safeUri, deleteTeamPermissionParams).enqueue(callback)
    }

    override fun deleteTeamPermission(
        teamPermission: TeamPermission,
        teamEntity: TeamEntity,
        callback: VimeoCallback<Unit>
    ): VimeoRequest {
        val safeUri = teamPermission.metadata?.interactions?.remove?.uri
            ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)

        val deleteTeamPermissionParams = DeleteTeamPermissionParams(teamEntity.type, teamEntity.uri)

        return vimeoService.deleteTeamPermission(authHeader, safeUri, deleteTeamPermissionParams).enqueue(callback)
    }

    override fun deleteTeamPermission(
        teamPermissionInteraction: TeamPermissionInteraction,
        teamEntity: TeamEntity,
        callback: VimeoCallback<Unit>
    ): VimeoRequest {
        val safeUri = teamPermissionInteraction.remove?.uri ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)

        val deleteTeamPermissionParams = DeleteTeamPermissionParams(teamEntity.type, teamEntity.uri)

        return vimeoService.deleteTeamPermission(authHeader, safeUri, deleteTeamPermissionParams).enqueue(callback)
    }

    override fun fetchTextTrackList(
        uri: String,
        fieldFilter: String?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<TextTrackList>
    ): VimeoRequest {
        val safeUri = uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return vimeoService.getTextTrackList(authHeader, safeUri, fieldFilter, cacheControl).enqueue(callback)
    }

    override fun fetchVideoStatus(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<VideoStatus>
    ): VimeoRequest {
        val safeUri = uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return vimeoService.getVideoStatus(authHeader, safeUri, fieldFilter, queryParams.orEmpty(), cacheControl)
            .enqueue(callback)
    }

    override fun fetchTeamMembersList(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<TeamMembershipList>
    ): VimeoRequest {
        val safeUri = uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return vimeoService.getTeamMembers(
            authHeader,
            safeUri,
            fieldFilter,
            queryParams.orEmpty(),
            cacheControl
        ).enqueue(callback)
    }

    override fun acceptTeamInvite(code: String, callback: VimeoCallback<TeamMembership>): VimeoRequest {
        val safeCode = code.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return vimeoService.acceptTeamInvite(authHeader, safeCode).enqueue(callback)
    }

    override fun addUserToTeam(
        uri: String,
        email: String,
        permissionLevel: TeamRoleType,
        folderUri: String?,
        customMessage: String?,
        queryParams: Map<String, String>?,
        callback: VimeoCallback<TeamMembership>
    ): VimeoRequest {
        val safeUri = uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return vimeoService.addUserToTeam(
            authHeader,
            safeUri,
            email,
            permissionLevel,
            folderUri,
            customMessage,
            queryParams.orEmpty()
        ).enqueue(callback)
    }

    override fun addUserToTeam(
        team: Team,
        email: String,
        permissionLevel: TeamRoleType,
        folder: Folder?,
        customMessage: String?,
        queryParams: Map<String, String>?,
        callback: VimeoCallback<TeamMembership>
    ): VimeoRequest {
        val safeUri = team.owner?.metadata?.connections?.teamMembers?.uri.validate()
            ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return vimeoService.addUserToTeam(
            authHeader,
            safeUri,
            email,
            permissionLevel,
            folder?.uri,
            customMessage,
            queryParams.orEmpty()
        ).enqueue(callback)
    }

    override fun addUserToVideoAsMember(
        team: Team,
        email: String,
        permissionLevel: TeamRoleType,
        videoUri: String?,
        customMessage: String?,
        queryParams: Map<String, String>?,
        callback: VimeoCallback<TeamMembership>
    ): VimeoRequest {
        val safeUri = team.owner?.metadata?.connections?.teamMembers?.uri.validate()
            ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return vimeoService.addUserToVideoAsMember(
            authHeader,
            safeUri,
            email,
            permissionLevel,
            videoUri,
            customMessage,
            queryParams.orEmpty()
        ).enqueue(callback)
    }

    override fun removeUserFromTeam(
        uri: String,
        queryParams: Map<String, String>?,
        callback: VimeoCallback<Unit>
    ): VimeoRequest {
        val safeUri = uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return vimeoService.removeUserFromTeam(authHeader, safeUri, queryParams.orEmpty()).enqueue(callback)
    }

    override fun removeUserFromTeam(
        membership: TeamMembership,
        queryParams: Map<String, String>?,
        callback: VimeoCallback<Unit>
    ): VimeoRequest {
        val safeUri = membership.uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return vimeoService.removeUserFromTeam(authHeader, safeUri, queryParams.orEmpty()).enqueue(callback)
    }

    override fun changeUserRole(
        uri: String,
        role: TeamRoleType,
        folderUri: String?,
        queryParams: Map<String, String>?,
        callback: VimeoCallback<TeamMembership>
    ): VimeoRequest {
        val safeUri = uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return vimeoService.changeUserRole(authHeader, safeUri, role, folderUri, queryParams.orEmpty())
            .enqueue(callback)
    }

    override fun changeUserRole(
        membership: TeamMembership,
        role: TeamRoleType,
        folder: Folder?,
        queryParams: Map<String, String>?,
        callback: VimeoCallback<TeamMembership>
    ): VimeoRequest {
        val safeUri = membership.uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return vimeoService.changeUserRole(authHeader, safeUri, role, folder?.uri, queryParams.orEmpty())
            .enqueue(callback)
    }

    override fun grantTeamMembersFolderAccess(
        uri: String,
        teamMemberIds: List<String>,
        queryParams: Map<String, String>?,
        callback: VimeoCallback<Unit>
    ): VimeoRequest {
        val safeUri = uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return vimeoService.grantUsersAccessToFolder(
            authHeader,
            safeUri,
            teamMemberIds.map(::GrantFolderPermissionForUser),
            queryParams.orEmpty()
        ).enqueue(callback)
    }

    override fun grantTeamMembersFolderAccess(
        folder: Folder,
        teamMembers: List<TeamMembership>,
        queryParams: Map<String, String>?,
        callback: VimeoCallback<Unit>
    ): VimeoRequest {
        val safeUri = folder.metadata?.connections?.teamMembers?.uri.validate()
            ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return vimeoService.grantUsersAccessToFolder(
            authHeader,
            safeUri,
            teamMembers.map { GrantFolderPermissionForUser(it.uri.orEmpty()) },
            queryParams.orEmpty()
        ).enqueue(callback)
    }

    override fun fetchEmpty(
        uri: String,
        cacheControl: CacheControl?,
        callback: VimeoCallback<Unit>
    ): VimeoRequest {
        val safeUri = uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return vimeoService.getUnit(authHeader, safeUri, emptyMap(), cacheControl).enqueue(callback)
    }

    @Suppress("ComplexMethod", "LongMethod")
    override fun search(
        query: String,
        searchFilterType: SearchFilterType,
        fieldFilter: String?,
        searchSortType: SearchSortType?,
        searchSortDirectionType: SearchSortDirectionType?,
        searchDateType: SearchDateType?,
        searchDurationType: SearchDurationType?,
        searchFacetTypes: List<SearchFacetType>?,
        category: String?,
        featuredVideoCount: Int?,
        containerFieldFilter: String?,
        queryParams: Map<String, String>?,
        callback: VimeoCallback<SearchResultList>
    ): VimeoRequest {
        val map = mutableMapOf(
            ApiConstants.Parameters.PARAMETER_GET_QUERY to query,
            ApiConstants.Parameters.FILTER_TYPE to (
                searchFilterType.value
                    ?: error(INVALID_ENUM_MESSAGE)
                )
        )
        if (fieldFilter != null) {
            map[ApiConstants.Parameters.PARAMETER_GET_FILTER] = fieldFilter
        }
        if (searchSortType != null) {
            map[ApiConstants.Parameters.PARAMETER_GET_SORT] = searchSortType.value
                ?: error(INVALID_ENUM_MESSAGE)
        }
        if (searchSortDirectionType != null) {
            map[ApiConstants.Parameters.PARAMETER_GET_DIRECTION] = searchSortDirectionType.value
                ?: error(INVALID_ENUM_MESSAGE)
        }
        if (searchDateType != null) {
            map[ApiConstants.Parameters.FILTER_UPLOADED] = searchDateType.value
                ?: error(INVALID_ENUM_MESSAGE)
        }
        if (searchDurationType != null) {
            map[ApiConstants.Parameters.FILTER_DURATION] = searchDurationType.value
                ?: error(INVALID_ENUM_MESSAGE)
        }
        if (searchFacetTypes != null) {
            map[ApiConstants.Parameters.PARAMETER_GET_FACETS] =
                searchFacetTypes.joinToString(
                    separator = ",",
                    transform = {
                        it.value ?: error(INVALID_ENUM_MESSAGE)
                    }
                )
        }
        if (category != null) {
            map[ApiConstants.Parameters.FILTER_CATEGORY] = category
        }
        if (featuredVideoCount != null) {
            map[ApiConstants.Parameters.FILTER_FEATURED_COUNT] = featuredVideoCount.toString()
        }
        if (containerFieldFilter != null) {
            map[ApiConstants.Parameters.PARAMETER_GET_CONTAINER_FIELD_FILTER] = containerFieldFilter
        }
        if (queryParams != null) {
            map.putAll(queryParams)
        }

        return vimeoService.search(authHeader, map).enqueue(callback)
    }

    override fun createPictureCollection(uri: String, callback: VimeoCallback<PictureCollection>): VimeoRequest {
        val safeUri = uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return vimeoService.createPictureCollection(authHeader, safeUri).enqueue(callback)
    }

    override fun activatePictureCollection(uri: String, callback: VimeoCallback<PictureCollection>): VimeoRequest {
        val safeUri = uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return vimeoService.editPictureCollection(
            authHeader,
            safeUri,
            true
        ).enqueue(callback)
    }

    override fun activatePictureCollection(
        pictureCollection: PictureCollection,
        callback: VimeoCallback<PictureCollection>
    ): VimeoRequest {
        val uri = pictureCollection.uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return activatePictureCollection(uri, callback)
    }

    override fun updateFollow(isFollowing: Boolean, uri: String, callback: VimeoCallback<Unit>): VimeoRequest {
        val safeUri = uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return if (isFollowing) {
            vimeoService.put(authHeader, safeUri, emptyMap()).enqueue(callback)
        } else {
            vimeoService.delete(authHeader, safeUri, emptyMap()).enqueue(callback)
        }
    }

    override fun updateFollow(
        isFollowing: Boolean,
        followable: Followable,
        callback: VimeoCallback<Unit>
    ): VimeoRequest {
        val uri = followable.metadata?.interactions?.follow?.uri.validate()
            ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return updateFollow(isFollowing, uri, callback)
    }

    override fun updateVideoLike(
        isLiked: Boolean,
        uri: String,
        password: String?,
        callback: VimeoCallback<Unit>
    ): VimeoRequest {
        val safeUri = uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        val optionsMap = password.asPasswordParameter()
        return if (isLiked) {
            vimeoService.put(authHeader, safeUri, optionsMap).enqueue(callback)
        } else {
            vimeoService.delete(authHeader, safeUri, optionsMap).enqueue(callback)
        }
    }

    override fun updateVideoLike(
        isLiked: Boolean,
        video: Video,
        password: String?,
        callback: VimeoCallback<Unit>
    ): VimeoRequest {
        val uri = video.uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return updateVideoLike(isLiked, uri, password, callback)
    }

    override fun updateVideoWatchLater(
        isWatchLater: Boolean,
        uri: String,
        password: String?,
        callback: VimeoCallback<Unit>
    ): VimeoRequest {
        val safeUri = uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        val optionsMap = password.asPasswordParameter()
        return if (isWatchLater) {
            vimeoService.put(authHeader, safeUri, optionsMap).enqueue(callback)
        } else {
            vimeoService.delete(authHeader, safeUri, optionsMap).enqueue(callback)
        }
    }

    override fun updateVideoWatchLater(
        isWatchLater: Boolean,
        video: Video,
        password: String?,
        callback: VimeoCallback<Unit>
    ): VimeoRequest {
        val uri = video.uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return updateVideoWatchLater(isWatchLater, uri, password, callback)
    }

    override fun createComment(
        uri: String,
        comment: String,
        password: String?,
        callback: VimeoCallback<Comment>,
        timeCode: Double?
    ): VimeoRequest {
        val safeUri = uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return vimeoService.createComment(authHeader, safeUri, password, comment, timeCode).enqueue(callback)
    }

    override fun createComment(
        video: Video,
        comment: String,
        password: String?,
        callback: VimeoCallback<Comment>,
        timeCode: Double?
    ): VimeoRequest {
        val uri = video.metadata?.connections?.comments?.uri.validate()
            ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return createComment(uri, comment, password, callback, timeCode)
    }

    override fun editComment(
        uri: String,
        text: String?,
        timeCode: Double?,
        callback: VimeoCallback<Comment>,
    ): VimeoRequest {
        val safeUri = uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return vimeoService.editComment(authHeader, safeUri, text, timeCode).enqueue(callback)
    }

    override fun createNote(
        uri: String,
        text: String,
        password: String?,
        coordinates: Coordinates,
        timeCode: Double,
        name: String?,
        callback: VimeoCallback<Note>
    ): VimeoRequest {
        val safeUri = uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        val body = mutableMapOf(
            ApiConstants.Parameters.PARAMETER_COMMENT_TEXT_BODY to text,
            ApiConstants.Parameters.PARAMETER_COORDINATES to coordinates,
            ApiConstants.Parameters.PARAMETER_TIME_CODE to timeCode,
        )

        name?.let { body[ApiConstants.Parameters.PARAMETER_USERS_NAME] = it }

        return vimeoService.createNote(authHeader, safeUri, password, body).enqueue(callback)
    }

    override fun editNote(
        uri: String,
        text: String?,
        coordinates: Coordinates?,
        timeCode: Double?,
        status: NoteStatus?,
        callback: VimeoCallback<Note>
    ): VimeoRequest {
        val safeUri = uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        val body = mutableMapOf<String, Any>()

        text?.let { body[ApiConstants.Parameters.PARAMETER_COMMENT_TEXT_BODY] = it }
        coordinates?.let { body[ApiConstants.Parameters.PARAMETER_COORDINATES] = it }
        timeCode?.let { body[ApiConstants.Parameters.PARAMETER_TIME_CODE] = it }
        status?.value?.let { body[ApiConstants.Parameters.PARAMETER_STATUS] = it }

        return vimeoService.editNote(authHeader, safeUri, body).enqueue(callback)
    }

    override fun fetchProductList(
        fieldFilter: String?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<ProductList>
    ): VimeoRequest {
        return vimeoService.getProducts(authHeader, fieldFilter, cacheControl).enqueue(callback)
    }

    override fun fetchSurveyQuestionList(
        fieldFilter: String?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<UserSegmentSurveyList>
    ): VimeoRequest {
        return vimeoService.getSurveyQuestions(authHeader, fieldFilter, cacheControl).enqueue(callback)
    }

    override fun fetchProduct(
        uri: String,
        fieldFilter: String?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<Product>
    ): VimeoRequest {
        val safeUri = uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return vimeoService.getProduct(authHeader, safeUri, fieldFilter, cacheControl).enqueue(callback)
    }

    override fun fetchCurrentUser(
        fieldFilter: String?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<User>
    ): VimeoRequest {
        return vimeoService.getUser(
            authHeader,
            ApiConstants.Endpoints.ENDPOINT_ME,
            fieldFilter,
            cacheControl
        ).enqueue(callback)
    }

    override fun postContent(uri: String, bodyParams: List<Any>, callback: VimeoCallback<Unit>): VimeoRequest {
        val safeUri = uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return vimeoService.post(authHeader, safeUri, bodyParams).enqueue(callback)
    }

    override fun emptyResponsePost(
        uri: String,
        bodyParams: Map<String, String>,
        callback: VimeoCallback<Unit>
    ): VimeoRequest {
        val safeUri = uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return vimeoService.emptyResponsePost(authHeader, safeUri, bodyParams).enqueue(callback)
    }

    override fun emptyResponsePatch(
        uri: String,
        queryParams: Map<String, String>,
        bodyParams: Any,
        callback: VimeoCallback<Unit>
    ): VimeoRequest {
        val safeUri = uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return vimeoService.emptyResponsePatch(authHeader, safeUri, queryParams, bodyParams).enqueue(callback)
    }

    override fun putContentWithUserResponse(
        uri: String,
        queryParams: Map<String, String>,
        bodyParams: Any?,
        callback: VimeoCallback<User>
    ): VimeoRequest {
        val safeUri = uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return if (bodyParams != null) {
            vimeoService.putContentWithUserResponse(authHeader, safeUri, queryParams, bodyParams).enqueue(callback)
        } else {
            vimeoService.putContentWithUserResponse(authHeader, safeUri, queryParams).enqueue(callback)
        }
    }

    override fun putContent(
        uri: String,
        queryParams: Map<String, String>,
        bodyParams: Any?,
        callback: VimeoCallback<Unit>
    ): VimeoRequest {
        val safeUri = uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return if (bodyParams != null) {
            vimeoService.put(authHeader, safeUri, queryParams, bodyParams).enqueue(callback)
        } else {
            vimeoService.put(authHeader, safeUri, queryParams).enqueue(callback)
        }
    }

    override fun deleteContent(
        uri: String,
        queryParams: Map<String, String>,
        callback: VimeoCallback<Unit>
    ): VimeoRequest {
        val safeUri = uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return vimeoService.delete(authHeader, safeUri, queryParams).enqueue(callback)
    }

    override fun fetchVideo(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<Video>
    ): VimeoRequest {
        val safeUri = uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return vimeoService.getVideo(authHeader, safeUri, fieldFilter, queryParams.orEmpty(), cacheControl)
            .enqueue(callback)
    }

    override fun fetchLiveEvent(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<LiveEvent>
    ): VimeoRequest {
        val safeUri = uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return vimeoService.getLiveEvent(authHeader, safeUri, fieldFilter, queryParams.orEmpty(), cacheControl)
            .enqueue(callback)
    }

    override fun createLiveEvent(
        uri: String,
        title: String,
        privacy: StreamPrivacy?,
        bodyParams: Map<String, Any>?,
        callback: VimeoCallback<LiveEvent>
    ): VimeoRequest {
        val safeUri = uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        val body = bodyParams?.toMutableMap() ?: mutableMapOf()

        privacy?.let { body[ApiConstants.Parameters.PARAMETER_STREAMING_PRIVACY] = it }

        body[ApiConstants.Parameters.PARAMETER_LIVE_EVENT_TITLE] = title
        body[ApiConstants.Parameters.PARAMETER_AUTOMATICALLY_TITLE_STREAM] = true

        return vimeoService.createLiveEvent(authHeader, safeUri, body).enqueue(callback)
    }

    override fun stopLiveEvent(uri: String, callback: VimeoCallback<Video>): VimeoRequest {
        val safeUri = uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return vimeoService.stopLiveEvent(authHeader, safeUri).enqueue(callback)
    }

    override fun fetchLiveEventList(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<LiveEventList>
    ): VimeoRequest {
        val safeUri = uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return vimeoService.getLiveEventList(authHeader, safeUri, fieldFilter, queryParams.orEmpty(), cacheControl)
            .enqueue(callback)
    }

    override fun fetchLiveStats(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<LiveStats>
    ): VimeoRequest {
        val safeUri = uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return vimeoService.getLiveStats(authHeader, safeUri, fieldFilter, queryParams.orEmpty(), cacheControl)
            .enqueue(callback)
    }

    override fun fetchVideoList(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<VideoList>
    ): VimeoRequest {
        val safeUri = uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return vimeoService.getVideoList(authHeader, safeUri, fieldFilter, queryParams.orEmpty(), cacheControl)
            .enqueue(callback)
    }

    override fun fetchFeedList(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<FeedList>
    ): VimeoRequest {
        val safeUri = uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return vimeoService.getFeedList(authHeader, safeUri, fieldFilter, queryParams.orEmpty(), cacheControl)
            .enqueue(callback)
    }

    override fun fetchProjectItemList(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<ProjectItemList>
    ): VimeoRequest {
        val safeUri = uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return vimeoService.getProjectItemList(authHeader, safeUri, fieldFilter, queryParams.orEmpty(), cacheControl)
            .enqueue(callback)
    }

    override fun fetchTeamList(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<TeamList>
    ): VimeoRequest {
        val safeUri = uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return vimeoService.getTeamList(authHeader, safeUri, fieldFilter, queryParams.orEmpty(), cacheControl)
            .enqueue(callback)
    }

    override fun fetchProgrammedContentItemList(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<ProgrammedCinemaItemList>
    ): VimeoRequest {
        val safeUri = uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return vimeoService.getProgramContentItemList(
            authHeader,
            safeUri,
            fieldFilter,
            queryParams.orEmpty(),
            cacheControl
        ).enqueue(callback)
    }

    override fun fetchRecommendationList(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<RecommendationList>
    ): VimeoRequest {
        val safeUri = uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return vimeoService.getRecommendationList(authHeader, safeUri, fieldFilter, queryParams.orEmpty(), cacheControl)
            .enqueue(callback)
    }

    override fun fetchSearchResultList(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<SearchResultList>
    ): VimeoRequest {
        val safeUri = uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return vimeoService.getSearchResultList(authHeader, safeUri, fieldFilter, queryParams.orEmpty(), cacheControl)
            .enqueue(callback)
    }

    override fun fetchSeasonList(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<SeasonList>
    ): VimeoRequest {
        val safeUri = uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return vimeoService.getSeasonList(authHeader, safeUri, fieldFilter, queryParams.orEmpty(), cacheControl)
            .enqueue(callback)
    }

    override fun fetchNotificationList(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<NotificationList>
    ): VimeoRequest {
        val safeUri = uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return vimeoService.getNotificationList(authHeader, safeUri, fieldFilter, queryParams.orEmpty(), cacheControl)
            .enqueue(callback)
    }

    override fun fetchUser(
        uri: String,
        fieldFilter: String?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<User>
    ): VimeoRequest {
        val safeUri = uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return vimeoService.getUser(authHeader, safeUri, fieldFilter, cacheControl)
            .enqueue(callback)
    }

    override fun fetchUserList(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<UserList>
    ): VimeoRequest {
        val safeUri = uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return vimeoService.getUserList(authHeader, safeUri, fieldFilter, queryParams.orEmpty(), cacheControl)
            .enqueue(callback)
    }

    override fun fetchCategory(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<Category>
    ): VimeoRequest {
        val safeUri = uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return vimeoService.getCategory(authHeader, safeUri, fieldFilter, queryParams.orEmpty(), cacheControl)
            .enqueue(callback)
    }

    override fun fetchCategoryList(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<CategoryList>
    ): VimeoRequest {
        val safeUri = uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return vimeoService.getCategoryList(authHeader, safeUri, fieldFilter, queryParams.orEmpty(), cacheControl)
            .enqueue(callback)
    }

    override fun fetchChannel(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<Channel>
    ): VimeoRequest {
        val safeUri = uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return vimeoService.getChannel(authHeader, safeUri, fieldFilter, queryParams.orEmpty(), cacheControl)
            .enqueue(callback)
    }

    override fun fetchChannelList(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<ChannelList>
    ): VimeoRequest {
        val safeUri = uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return vimeoService.getChannelList(authHeader, safeUri, fieldFilter, queryParams.orEmpty(), cacheControl)
            .enqueue(callback)
    }

    override fun fetchAppConfiguration(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<AppConfiguration>
    ): VimeoRequest {
        val safeUri = uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return vimeoService.getAppConfiguration(authHeader, safeUri, fieldFilter, queryParams.orEmpty(), cacheControl)
            .enqueue(callback)
    }

    override fun fetchAlbum(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<Album>
    ): VimeoRequest {
        val safeUri = uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return vimeoService.getAlbum(authHeader, safeUri, fieldFilter, queryParams.orEmpty(), cacheControl)
            .enqueue(callback)
    }

    override fun fetchAlbumList(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<AlbumList>
    ): VimeoRequest {
        val safeUri = uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return vimeoService.getAlbumList(authHeader, safeUri, fieldFilter, queryParams.orEmpty(), cacheControl)
            .enqueue(callback)
    }

    override fun fetchTvodItem(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<TvodItem>
    ): VimeoRequest {
        val safeUri = uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return vimeoService.getTvodItem(authHeader, safeUri, fieldFilter, queryParams.orEmpty(), cacheControl)
            .enqueue(callback)
    }

    override fun fetchTvodItemList(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<TvodItemList>
    ): VimeoRequest {
        val safeUri = uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return vimeoService.getTvodItemList(authHeader, safeUri, fieldFilter, queryParams.orEmpty(), cacheControl)
            .enqueue(callback)
    }

    override fun fetchComment(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<Comment>
    ): VimeoRequest {
        val safeUri = uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return vimeoService.getComment(authHeader, safeUri, fieldFilter, queryParams.orEmpty(), cacheControl)
            .enqueue(callback)
    }

    override fun fetchNote(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<Note>
    ): VimeoRequest {
        val safeUri = uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return vimeoService.getNote(authHeader, safeUri, fieldFilter, queryParams.orEmpty(), cacheControl)
            .enqueue(callback)
    }

    override fun fetchCommentList(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<CommentList>
    ): VimeoRequest {
        val safeUri = uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return vimeoService.getCommentList(authHeader, safeUri, fieldFilter, queryParams.orEmpty(), cacheControl)
            .enqueue(callback)
    }

    override fun fetchNoteList(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<NoteList>
    ): VimeoRequest {
        val safeUri = uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return vimeoService.getNoteList(authHeader, safeUri, fieldFilter, queryParams.orEmpty(), cacheControl)
            .enqueue(callback)
    }

    override fun fetchCapabilities(
        teamOwnerId: String,
        fieldFilter: String?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<Capabilities>
    ): VimeoRequest {
        return vimeoService.capabilities(authHeader, teamOwnerId, fieldFilter, cacheControl).enqueue(callback)
    }

    override fun getFeaturedContent(
        teamOwnerId: String,
        fieldFilter: String?,
        callback: VimeoCallback<FeaturedContent>,
        cacheControl: CacheControl?,
    ): VimeoRequest {
        return vimeoService.getFeaturedContent(authHeader, teamOwnerId, fieldFilter, cacheControl).enqueue(callback)
    }

    override fun getCustomDomains(email: String, callback: VimeoCallback<CustomDomains>): VimeoRequest {
        return vimeoService.getCustomDomains(authHeader, email).enqueue(callback)
    }

    override fun getVideoChapters(
        uri: String,
        callback: VimeoCallback<ChapterList>,
        cacheControl: CacheControl?
    ): VimeoRequest {
        val safeUrl = uri.validate() ?: return localVimeoCallAdapter.enqueueInvalidUri(callback)
        return vimeoService.getVideoChapters(authHeader, safeUrl, emptyMap(), cacheControl).enqueue(callback)
    }

    private fun <T> LocalVimeoCallAdapter.enqueueInvalidUri(callback: VimeoCallback<T>): VimeoRequest {
        return enqueueError(
            ApiError(
                developerMessage = "An invalid URI was provided, cannot be empty or contain ..",
                invalidParameters = listOf(
                    InvalidParameter(
                        errorCode = ErrorCodeType.INVALID_URI.value,
                        developerMessage = "An invalid URI was provided, cannot be empty or contain .."
                    )
                )
            ),
            callback
        )
    }

    /**
     * @return The [String] if it is not empty or blank and does not contain a path traversal, otherwise returns null.
     */
    private fun String?.validate(): String? = this?.takeIf { it.isNotBlank() && !it.contains("..") }

    private fun <T : StringValue> T.validate(): T =
        this.takeIf { it.value?.isNotEmpty() == true } ?: error(INVALID_ENUM_MESSAGE)

    private fun String?.asPasswordParameter(): Map<String, String> =
        this?.let { mapOf(ApiConstants.Parameters.PARAMETER_PASSWORD to it) } ?: emptyMap()

    private fun <T, V> Map<T, V>?.intoMutableMap(): MutableMap<T, V> = this?.toMutableMap() ?: mutableMapOf()

    private companion object {
        private const val INVALID_ENUM_MESSAGE = "Invalid enum type provided"
    }
}
