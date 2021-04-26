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

import com.vimeo.networking2.*
import com.vimeo.networking2.common.Followable
import com.vimeo.networking2.config.VimeoApiConfiguration
import com.vimeo.networking2.enums.*
import com.vimeo.networking2.params.*
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
        val safeUri = uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
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
        val safeUri =
            user.metadata?.connections?.albums?.uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
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
        val uri = album.uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
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
        val safeUri = uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
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
        val uri = album.uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
        return deleteContent(uri, emptyMap(), callback)
    }

    override fun addToAlbum(album: Album, video: Video, callback: VimeoCallback<Unit>): VimeoRequest {
        val albumUri = album.uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
        val videoUri = video.uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
        return addToAlbum(albumUri, videoUri, callback)
    }

    override fun addToAlbum(albumUri: String, videoUri: String, callback: VimeoCallback<Unit>): VimeoRequest {
        val safeAlbumUri = albumUri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
        val safeVideoUri = videoUri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
        return vimeoService.addToAlbum(authHeader, safeAlbumUri, safeVideoUri).enqueue(callback)
    }

    override fun removeFromAlbum(album: Album, video: Video, callback: VimeoCallback<Unit>): VimeoRequest {
        val albumUri = album.uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
        val videoUri = video.uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
        return removeFromAlbum(albumUri, videoUri, callback)
    }

    override fun removeFromAlbum(albumUri: String, videoUri: String, callback: VimeoCallback<Unit>): VimeoRequest {
        val safeAlbumUri = albumUri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
        val safeVideoUri = videoUri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
        return vimeoService.removeFromAlbum(authHeader, safeAlbumUri, safeVideoUri).enqueue(callback)
    }

    override fun modifyVideosInAlbum(
        album: Album,
        modificationSpecs: ModifyVideosInAlbumSpecs,
        callback: VimeoCallback<VideoList>
    ): VimeoRequest {
        val uri = album.uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
        return modifyVideosInAlbum(uri, modificationSpecs, callback)
    }

    override fun modifyVideosInAlbum(
        uri: String,
        modificationSpecs: ModifyVideosInAlbumSpecs,
        callback: VimeoCallback<VideoList>
    ): VimeoRequest {
        val safeUri = uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
        return vimeoService.modifyVideosInAlbum(authHeader, safeUri, modificationSpecs).enqueue(callback)
    }

    override fun modifyVideoInAlbums(
        video: Video,
        modificationSpecs: ModifyVideoInAlbumsSpecs,
        callback: VimeoCallback<AlbumList>
    ): VimeoRequest {
        val uri = video.uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
        return modifyVideoInAlbums(uri, modificationSpecs, callback)
    }

    override fun modifyVideoInAlbums(
        uri: String,
        modificationSpecs: ModifyVideoInAlbumsSpecs,
        callback: VimeoCallback<AlbumList>
    ): VimeoRequest {
        val safeUri = uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
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
        embedPrivacyType: EmbedPrivacyType?,
        viewPrivacyType: ViewPrivacyType?,
        bodyParams: Map<String, Any>?,
        callback: VimeoCallback<Video>
    ): VimeoRequest {
        val safeUri = uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
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

        return vimeoService.editVideo(authHeader, safeUri, body).enqueue(callback)
    }

    override fun editVideo(
        video: Video,
        title: String?,
        description: String?,
        password: String?,
        commentPrivacyType: CommentPrivacyType?,
        allowDownload: Boolean?,
        allowAddToCollections: Boolean?,
        embedPrivacyType: EmbedPrivacyType?,
        viewPrivacyType: ViewPrivacyType?,
        bodyParams: Map<String, Any>?,
        callback: VimeoCallback<Video>
    ): VimeoRequest {
        val uri = video.uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
        return editVideo(
            uri,
            title,
            description,
            password,
            commentPrivacyType,
            allowDownload,
            allowAddToCollections,
            embedPrivacyType,
            viewPrivacyType,
            bodyParams,
            callback
        )
    }

    override fun editUser(
        uri: String,
        name: String?,
        location: String?,
        bio: String?,
        callback: VimeoCallback<User>
    ): VimeoRequest {
        val safeUri = uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
        return vimeoService.editUser(authHeader, safeUri, name, location, bio).enqueue(callback)
    }

    override fun editUser(
        user: User,
        name: String?,
        location: String?,
        bio: String?,
        callback: VimeoCallback<User>
    ): VimeoRequest {
        val uri = user.uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
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
        parentFolderId: String?,
        name: String,
        privacy: FolderViewPrivacyType,
        slackWebhookId: String?,
        slackLanguagePreference: SlackLanguagePreferenceType?,
        slackUserPreference: SlackUserPreferenceType?,
        callback: VimeoCallback<Folder>
    ): VimeoRequest {
        val safeUri = uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
        return vimeoService.createFolder(
            authHeader,
            safeUri,
            parentFolderId,
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
        val safeUri = user.metadata?.connections?.folders?.uri.notEmpty()
            ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
        val parentFolderId = parentFolder?.uri?.lastPathSegment()
        return vimeoService.createFolder(
            authHeader,
            safeUri,
            parentFolderId,
            name,
            privacy,
            slackWebhookId,
            slackLanguagePreference,
            slackUserPreference
        ).enqueue(callback)
    }

    override fun deleteFolder(folder: Folder, callback: VimeoCallback<Unit>): VimeoRequest {
        val uri = folder.uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
        return deleteContent(uri, emptyMap(), callback)
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
        val safeUri = uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
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
        val safeUri = folder.uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
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
        val folderUri = folder.uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
        val videoUri = video.uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
        return addToFolder(folderUri, videoUri, callback)
    }

    override fun addToFolder(folderUri: String, videoUri: String, callback: VimeoCallback<Unit>): VimeoRequest {
        val safeFolderUri = folderUri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
        val safeVideoUri = videoUri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
        return vimeoService.addToFolder(authHeader, safeFolderUri, safeVideoUri).enqueue(callback)
    }

    override fun removeFromFolder(folder: Folder, video: Video, callback: VimeoCallback<Unit>): VimeoRequest {
        val folderUri = folder.uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
        val videoUri = video.uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
        return removeFromFolder(folderUri, videoUri, callback)
    }

    override fun removeFromFolder(folderUri: String, videoUri: String, callback: VimeoCallback<Unit>): VimeoRequest {
        val safeFolderUri = folderUri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
        val safeVideoUri = videoUri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
        return vimeoService.removeFromFolder(authHeader, safeFolderUri, safeVideoUri).enqueue(callback)
    }

    override fun fetchPublishJob(
        uri: String,
        fieldFilter: String?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<PublishJob>
    ): VimeoRequest {
        val safeUri = uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
        return vimeoService.getPublishJob(authHeader, safeUri, fieldFilter, cacheControl)
            .enqueue(callback)
    }

    override fun putPublishJob(
        uri: String,
        publishData: BatchPublishToSocialMedia,
        callback: VimeoCallback<PublishJob>
    ): VimeoRequest {
        val safeUri = uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
        return vimeoService.putPublishJob(authHeader, safeUri, publishData).enqueue(callback)
    }

    override fun putPublishJob(
        video: Video,
        publishData: BatchPublishToSocialMedia,
        callback: VimeoCallback<PublishJob>
    ): VimeoRequest {
        val uri = video.uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
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
        val safeUri = uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
        return vimeoService.getDocument(authHeader, safeUri).enqueue(callback)
    }

    override fun fetchFolder(
        uri: String,
        fieldFilter: String?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<Folder>
    ): VimeoRequest {
        val safeUri = uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
        return vimeoService.getFolder(authHeader, safeUri, fieldFilter, cacheControl).enqueue(callback)
    }

    override fun fetchFolderList(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<FolderList>
    ): VimeoRequest {
        val safeUri = uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
        return vimeoService.getFolderList(
            authHeader,
            safeUri,
            fieldFilter,
            queryParams.orEmpty(),
            cacheControl
        ).enqueue(callback)
    }

    override fun fetchTextTrackList(
        uri: String,
        fieldFilter: String?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<TextTrackList>
    ): VimeoRequest {
        val safeUri = uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
        return vimeoService.getTextTrackList(authHeader, safeUri, fieldFilter, cacheControl)
            .enqueue(callback)
    }

    override fun fetchVideoStatus(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<VideoStatus>
    ): VimeoRequest {
        val safeUri = uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
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
        val safeUri = uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
        return vimeoService.getTeamMembers(
            authHeader,
            safeUri,
            fieldFilter,
            queryParams.orEmpty(),
            cacheControl
        ).enqueue(callback)
    }

    override fun acceptTeamInvite(code: String, callback: VimeoCallback<TeamMembership>): VimeoRequest {
        val safeCode = code.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
        return vimeoService.acceptTeamInvite(authHeader, safeCode).enqueue(callback)
    }

    override fun addUserToTeam(
        uri: String,
        email: String,
        permissionLevel: TeamRoleType,
        folderUri: String?,
        queryParams: Map<String, String>?,
        callback: VimeoCallback<TeamMembership>
    ): VimeoRequest {
        val safeUri = uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
        return vimeoService.addUserToTeam(
            authHeader,
            safeUri,
            email,
            permissionLevel,
            folderUri,
            queryParams.orEmpty()
        ).enqueue(callback)
    }

    override fun addUserToTeam(
        team: Team,
        email: String,
        permissionLevel: TeamRoleType,
        folder: Folder?,
        queryParams: Map<String, String>?,
        callback: VimeoCallback<TeamMembership>
    ): VimeoRequest {
        val safeUri = team.owner?.metadata?.connections?.teamMembers?.uri.notEmpty()
            ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
        return vimeoService.addUserToTeam(
            authHeader,
            safeUri,
            email,
            permissionLevel,
            folder?.uri,
            queryParams.orEmpty()
        ).enqueue(callback)
    }

    override fun removeUserFromTeam(
        uri: String,
        queryParams: Map<String, String>?,
        callback: VimeoCallback<Unit>
    ): VimeoRequest {
        val safeUri = uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
        return vimeoService.removeUserFromTeam(authHeader, safeUri, queryParams.orEmpty()).enqueue(callback)
    }

    override fun removeUserFromTeam(
        membership: TeamMembership,
        queryParams: Map<String, String>?,
        callback: VimeoCallback<Unit>
    ): VimeoRequest {
        val safeUri = membership.uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
        return vimeoService.removeUserFromTeam(authHeader, safeUri, queryParams.orEmpty()).enqueue(callback)
    }

    override fun changeUserRole(
        uri: String,
        role: TeamRoleType,
        folderUri: String?,
        queryParams: Map<String, String>?,
        callback: VimeoCallback<TeamMembership>
    ): VimeoRequest {
        val safeUri = uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
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
        val safeUri = membership.uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
        return vimeoService.changeUserRole(authHeader, safeUri, role, folder?.uri, queryParams.orEmpty())
            .enqueue(callback)
    }

    override fun grantTeamMembersFolderAccess(
        uri: String,
        teamMemberIds: List<String>,
        queryParams: Map<String, String>?,
        callback: VimeoCallback<Unit>
    ): VimeoRequest {
        val safeUri = uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
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
        val safeUri = folder.metadata?.connections?.teamMembers?.uri.notEmpty()
            ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
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
        val safeUri = uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
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
            ApiConstants.Parameters.FILTER_TYPE to (searchFilterType.value
                ?: error(INVALID_ENUM_MESSAGE))
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
                searchFacetTypes.joinToString(separator = ",", transform = {
                    it.value ?: error(INVALID_ENUM_MESSAGE)
                })
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
        val safeUri = uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
        return vimeoService.createPictureCollection(authHeader, safeUri).enqueue(callback)
    }

    override fun activatePictureCollection(uri: String, callback: VimeoCallback<PictureCollection>): VimeoRequest {
        val safeUri = uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
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
        val uri = pictureCollection.uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
        return activatePictureCollection(uri, callback)
    }

    override fun updateFollow(isFollowing: Boolean, uri: String, callback: VimeoCallback<Unit>): VimeoRequest {
        val safeUri = uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
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
        val uri = followable.metadata?.interactions?.follow?.uri.notEmpty()
            ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
        return updateFollow(isFollowing, uri, callback)
    }

    override fun updateVideoLike(
        isLiked: Boolean,
        uri: String,
        password: String?,
        callback: VimeoCallback<Unit>
    ): VimeoRequest {
        val safeUri = uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
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
        val uri = video.uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
        return updateVideoLike(isLiked, uri, password, callback)
    }

    override fun updateVideoWatchLater(
        isWatchLater: Boolean,
        uri: String,
        password: String?,
        callback: VimeoCallback<Unit>
    ): VimeoRequest {
        val safeUri = uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
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
        val uri = video.uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
        return updateVideoWatchLater(isWatchLater, uri, password, callback)
    }

    override fun createComment(
        uri: String,
        comment: String,
        password: String?,
        callback: VimeoCallback<Comment>
    ): VimeoRequest {
        val safeUri = uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
        return vimeoService.createComment(authHeader, safeUri, password, comment).enqueue(callback)
    }

    override fun createComment(
        video: Video,
        comment: String,
        password: String?,
        callback: VimeoCallback<Comment>
    ): VimeoRequest {
        val uri = video.metadata?.connections?.comments?.uri.notEmpty()
            ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
        return createComment(uri, comment, password, callback)
    }

    override fun fetchProductList(
        fieldFilter: String?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<ProductList>
    ): VimeoRequest {
        return vimeoService.getProducts(authHeader, fieldFilter, cacheControl).enqueue(callback)
    }

    override fun fetchProduct(
        uri: String,
        fieldFilter: String?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<Product>
    ): VimeoRequest {
        val safeUri = uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
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
        val safeUri = uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
        return vimeoService.post(authHeader, safeUri, bodyParams).enqueue(callback)
    }

    override fun emptyResponsePost(
        uri: String,
        bodyParams: Map<String, String>,
        callback: VimeoCallback<Unit>
    ): VimeoRequest {
        val safeUri = uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
        return vimeoService.emptyResponsePost(authHeader, safeUri, bodyParams).enqueue(callback)
    }

    override fun emptyResponsePatch(
        uri: String,
        queryParams: Map<String, String>,
        bodyParams: Any,
        callback: VimeoCallback<Unit>
    ): VimeoRequest {
        val safeUri = uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
        return vimeoService.emptyResponsePatch(authHeader, safeUri, queryParams, bodyParams).enqueue(callback)
    }

    override fun putContentWithUserResponse(
        uri: String,
        queryParams: Map<String, String>,
        bodyParams: Any?,
        callback: VimeoCallback<User>
    ): VimeoRequest {
        val safeUri = uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
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
        val safeUri = uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
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
        val safeUri = uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
        return vimeoService.delete(authHeader, safeUri, queryParams).enqueue(callback)
    }

    override fun fetchVideo(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<Video>
    ): VimeoRequest {
        val safeUri = uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
        return vimeoService.getVideo(authHeader, safeUri, fieldFilter, queryParams.orEmpty(), cacheControl)
            .enqueue(callback)
    }

    override fun fetchLiveStats(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<LiveStats>
    ): VimeoRequest {
        val safeUri = uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
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
        val safeUri = uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
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
        val safeUri = uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
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
        val safeUri = uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
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
        val safeUri = uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
        return vimeoService.getTeamList(authHeader, safeUri, fieldFilter, queryParams.orEmpty(), cacheControl)
            .enqueue(callback)
    }

    override fun fetchProgrammedContentItemList(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<ProgrammedContentItemList>
    ): VimeoRequest {
        val safeUri = uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
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
        val safeUri = uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
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
        val safeUri = uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
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
        val safeUri = uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
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
        val safeUri = uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
        return vimeoService.getNotificationList(authHeader, safeUri, fieldFilter, queryParams.orEmpty(), cacheControl)
            .enqueue(callback)
    }

    override fun fetchUser(
        uri: String,
        fieldFilter: String?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<User>
    ): VimeoRequest {
        val safeUri = uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
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
        val safeUri = uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
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
        val safeUri = uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
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
        val safeUri = uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
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
        val safeUri = uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
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
        val safeUri = uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
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
        val safeUri = uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
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
        val safeUri = uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
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
        val safeUri = uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
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
        val safeUri = uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
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
        val safeUri = uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
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
        val safeUri = uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
        return vimeoService.getComment(authHeader, safeUri, fieldFilter, queryParams.orEmpty(), cacheControl)
            .enqueue(callback)
    }

    override fun fetchCommentList(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<CommentList>
    ): VimeoRequest {
        val safeUri = uri.notEmpty() ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
        return vimeoService.getCommentList(authHeader, safeUri, fieldFilter, queryParams.orEmpty(), cacheControl)
            .enqueue(callback)
    }

    private fun <T> LocalVimeoCallAdapter.enqueueEmptyUri(callback: VimeoCallback<T>): VimeoRequest {
        return enqueueError(ApiError(
            developerMessage = "An empty URI was provided",
            invalidParameters = listOf(InvalidParameter(
                errorCode = ErrorCodeType.INVALID_URI.value,
                developerMessage = "An empty URI was provided"
            ))
        ), callback)
    }

    private fun String.lastPathSegment(): String = this.substringAfterLast(delimiter = '/')

    /**
     * @return The [String] if it is not empty or blank, otherwise returns null.
     */
    private fun String?.notEmpty(): String? = this?.takeIf { it.isNotBlank() }

    private fun <T : StringValue> T.validate(): T =
        this.takeIf { it.value?.isNotEmpty() == true } ?: error(INVALID_ENUM_MESSAGE)

    private fun String?.asPasswordParameter(): Map<String, String> =
        this?.let { mapOf(ApiConstants.Parameters.PARAMETER_PASSWORD to it) } ?: emptyMap()

    private fun <T, V> Map<T, V>?.intoMutableMap(): MutableMap<T, V> = this?.toMutableMap() ?: mutableMapOf()

    private companion object {
        private const val INVALID_ENUM_MESSAGE = "Invalid enum type provided"
    }
}
