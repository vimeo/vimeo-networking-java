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
import com.vimeo.networking2.AppConfiguration
import com.vimeo.networking2.Capabilities
import com.vimeo.networking2.Category
import com.vimeo.networking2.CategoryList
import com.vimeo.networking2.Channel
import com.vimeo.networking2.ChannelList
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
import com.vimeo.networking2.common.Followable
import com.vimeo.networking2.enums.CommentPrivacyType
import com.vimeo.networking2.enums.ConnectedAppType
import com.vimeo.networking2.enums.EmbedPrivacyType
import com.vimeo.networking2.enums.FolderViewPrivacyType
import com.vimeo.networking2.enums.NotificationType
import com.vimeo.networking2.enums.SlackLanguagePreferenceType
import com.vimeo.networking2.enums.SlackUserPreferenceType
import com.vimeo.networking2.enums.TeamEntityType
import com.vimeo.networking2.enums.TeamRoleType
import com.vimeo.networking2.enums.ViewPrivacyType
import com.vimeo.networking2.params.BatchPublishToSocialMedia
import com.vimeo.networking2.params.ModifyVideoInAlbumsSpecs
import com.vimeo.networking2.params.ModifyVideosInAlbumSpecs
import com.vimeo.networking2.params.Schedule
import com.vimeo.networking2.params.SearchDateType
import com.vimeo.networking2.params.SearchDurationType
import com.vimeo.networking2.params.SearchFacetType
import com.vimeo.networking2.params.SearchFilterType
import com.vimeo.networking2.params.SearchSortDirectionType
import com.vimeo.networking2.params.SearchSortType
import okhttp3.CacheControl

/**
 * A [VimeoApiClient] that delegates its implementation to an internal mutable instance [actual]. The purpose of this
 * class is to allow the [VimeoApiClient] instance to be re-initialized on the fly. It delegates to an underlying actual
 * implementation that can be changed dynamically. This allows the [VimeoApiClient.initialize] function to change the
 * implementation used without changing the reference returned by [VimeoApiClient.instance].
 *
 * @param actual The actual implementation of [VimeoApiClient], defaults to null.
 */
@Suppress("LargeClass")
internal class MutableVimeoApiClientDelegate(var actual: VimeoApiClient? = null) : VimeoApiClient {

    private val client: VimeoApiClient
        get() = actual ?: error("Must call VimeoApiClient.initialize() before calling VimeoApiClient.instance()")

    override fun createAlbum(
        uri: String,
        name: String,
        albumPrivacy: AlbumPrivacy,
        description: String?,
        bodyParams: Map<String, Any>?,
        callback: VimeoCallback<Album>
    ): VimeoRequest = client.createAlbum(uri, name, albumPrivacy, description, bodyParams, callback)

    override fun createAlbum(
        user: User,
        name: String,
        albumPrivacy: AlbumPrivacy,
        description: String?,
        bodyParams: Map<String, Any>?,
        callback: VimeoCallback<Album>
    ): VimeoRequest = client.createAlbum(user, name, albumPrivacy, description, bodyParams, callback)

    override fun editAlbum(
        album: Album,
        name: String,
        albumPrivacy: AlbumPrivacy,
        description: String?,
        bodyParams: Map<String, Any>?,
        callback: VimeoCallback<Album>
    ): VimeoRequest = client.editAlbum(album, name, albumPrivacy, description, bodyParams, callback)

    override fun editAlbum(
        uri: String,
        name: String,
        albumPrivacy: AlbumPrivacy,
        description: String?,
        bodyParams: Map<String, Any>?,
        callback: VimeoCallback<Album>
    ): VimeoRequest = client.editAlbum(uri, name, albumPrivacy, description, bodyParams, callback)

    override fun deleteAlbum(album: Album, callback: VimeoCallback<Unit>): VimeoRequest =
        client.deleteAlbum(album, callback)

    override fun addToAlbum(album: Album, video: Video, callback: VimeoCallback<Unit>): VimeoRequest =
        client.addToAlbum(album, video, callback)

    override fun addToAlbum(albumUri: String, videoUri: String, callback: VimeoCallback<Unit>): VimeoRequest =
        client.addToAlbum(albumUri, videoUri, callback)

    override fun removeFromAlbum(album: Album, video: Video, callback: VimeoCallback<Unit>): VimeoRequest =
        client.removeFromAlbum(album, video, callback)

    override fun removeFromAlbum(albumUri: String, videoUri: String, callback: VimeoCallback<Unit>): VimeoRequest =
        client.removeFromAlbum(albumUri, videoUri, callback)

    override fun modifyVideosInAlbum(
        album: Album,
        modificationSpecs: ModifyVideosInAlbumSpecs,
        callback: VimeoCallback<VideoList>
    ): VimeoRequest = client.modifyVideosInAlbum(album, modificationSpecs, callback)

    override fun modifyVideosInAlbum(
        uri: String,
        modificationSpecs: ModifyVideosInAlbumSpecs,
        callback: VimeoCallback<VideoList>
    ): VimeoRequest = client.modifyVideosInAlbum(uri, modificationSpecs, callback)

    override fun modifyVideoInAlbums(
        video: Video,
        modificationSpecs: ModifyVideoInAlbumsSpecs,
        callback: VimeoCallback<AlbumList>
    ): VimeoRequest = client.modifyVideoInAlbums(video, modificationSpecs, callback)

    override fun modifyVideoInAlbums(
        uri: String,
        modificationSpecs: ModifyVideoInAlbumsSpecs,
        callback: VimeoCallback<AlbumList>
    ): VimeoRequest = client.modifyVideoInAlbums(uri, modificationSpecs, callback)

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
    ): VimeoRequest = client.editVideo(
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
    ): VimeoRequest = client.editVideo(
        video,
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
    ): VimeoRequest = client.editLiveEvent(
        liveEvent,
        title,
        streamTitle,
        description,
        password,
        commentPrivacyType,
        allowDownload,
        allowAddToCollections,
        allowShareLink,
        embedPrivacyType,
        viewPrivacyType,
        schedule,
        enableLiveChat,
        bodyParams,
        fieldFilter,
        callback
    )

    override fun editUser(
        uri: String,
        name: String?,
        location: String?,
        bio: String?,
        callback: VimeoCallback<User>
    ): VimeoRequest = client.editUser(uri, name, location, bio, callback)

    override fun editUser(
        user: User,
        name: String?,
        location: String?,
        bio: String?,
        callback: VimeoCallback<User>
    ): VimeoRequest = client.editUser(user, name, location, bio, callback)

    override fun editSubscriptions(
        subscriptionMap: Map<NotificationType, Boolean>,
        callback: VimeoCallback<NotificationSubscriptions>
    ): VimeoRequest = client.editSubscriptions(subscriptionMap, callback)

    override fun fetchConnectedApps(
        fieldFilter: String?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<ConnectedAppList>
    ): VimeoRequest = client.fetchConnectedApps(fieldFilter, cacheControl, callback)

    override fun fetchConnectedApp(
        type: ConnectedAppType,
        fieldFilter: String?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<ConnectedApp>
    ): VimeoRequest = client.fetchConnectedApp(type, fieldFilter, cacheControl, callback)

    override fun createConnectedApp(
        type: ConnectedAppType,
        authorization: String,
        clientId: String,
        callback: VimeoCallback<ConnectedApp>
    ): VimeoRequest = client.createConnectedApp(type, authorization, clientId, callback)

    override fun deleteConnectedApp(type: ConnectedAppType, callback: VimeoCallback<Unit>): VimeoRequest =
        client.deleteConnectedApp(type, callback)

    override fun createFolder(
        uri: String,
        parentFolderUri: String?,
        name: String,
        privacy: FolderViewPrivacyType,
        slackWebhookId: String?,
        slackLanguagePreference: SlackLanguagePreferenceType?,
        slackUserPreference: SlackUserPreferenceType?,
        callback: VimeoCallback<Folder>
    ): VimeoRequest = client.createFolder(
        uri,
        parentFolderUri,
        name,
        privacy,
        slackWebhookId,
        slackLanguagePreference,
        slackUserPreference,
        callback
    )

    override fun createFolder(
        user: User,
        parentFolder: Folder?,
        name: String,
        privacy: FolderViewPrivacyType,
        slackWebhookId: String?,
        slackLanguagePreference: SlackLanguagePreferenceType?,
        slackUserPreference: SlackUserPreferenceType?,
        callback: VimeoCallback<Folder>
    ): VimeoRequest = client.createFolder(
        user,
        parentFolder,
        name,
        privacy,
        slackWebhookId,
        slackLanguagePreference,
        slackUserPreference,
        callback
    )

    override fun deleteFolder(folder: Folder, shouldDeleteClips: Boolean, callback: VimeoCallback<Unit>): VimeoRequest =
        client.deleteFolder(folder, shouldDeleteClips, callback)

    override fun editFolder(
        uri: String,
        name: String,
        privacy: FolderViewPrivacyType,
        slackWebhookId: String?,
        slackLanguagePreference: SlackLanguagePreferenceType?,
        slackUserPreference: SlackUserPreferenceType?,
        callback: VimeoCallback<Folder>
    ): VimeoRequest = client.editFolder(
        uri,
        name,
        privacy,
        slackWebhookId,
        slackLanguagePreference,
        slackUserPreference,
        callback
    )

    override fun editFolder(
        folder: Folder,
        name: String,
        privacy: FolderViewPrivacyType,
        slackWebhookId: String?,
        slackLanguagePreference: SlackLanguagePreferenceType?,
        slackUserPreference: SlackUserPreferenceType?,
        callback: VimeoCallback<Folder>
    ): VimeoRequest = client.editFolder(
        folder,
        name,
        privacy,
        slackWebhookId,
        slackLanguagePreference,
        slackUserPreference,
        callback
    )

    override fun addToFolder(folder: Folder, video: Video, callback: VimeoCallback<Unit>): VimeoRequest =
        client.addToFolder(folder, video, callback)

    override fun addToFolder(folderUri: String, videoUri: String, callback: VimeoCallback<Unit>): VimeoRequest =
        client.addToFolder(folderUri, videoUri, callback)

    override fun removeFromFolder(folder: Folder, video: Video, callback: VimeoCallback<Unit>): VimeoRequest =
        client.removeFromFolder(folder, video, callback)

    override fun removeFromFolder(folderUri: String, videoUri: String, callback: VimeoCallback<Unit>): VimeoRequest =
        client.removeFromFolder(folderUri, videoUri, callback)

    override fun removeFromFolder(
        folderUri: String,
        queryParams: Map<String, String>?,
        callback: VimeoCallback<Unit>
    ): VimeoRequest = client.removeFromFolder(folderUri, queryParams, callback)

    override fun fetchPublishJob(
        uri: String,
        fieldFilter: String?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<PublishJob>
    ): VimeoRequest = client.fetchPublishJob(uri, fieldFilter, cacheControl, callback)

    override fun putPublishJob(
        uri: String,
        publishData: BatchPublishToSocialMedia,
        callback: VimeoCallback<PublishJob>
    ): VimeoRequest = client.putPublishJob(uri, publishData, callback)

    override fun putPublishJob(
        video: Video,
        publishData: BatchPublishToSocialMedia,
        callback: VimeoCallback<PublishJob>
    ): VimeoRequest = client.putPublishJob(video, publishData, callback)

    override fun fetchTermsOfService(cacheControl: CacheControl?, callback: VimeoCallback<Document>): VimeoRequest =
        client.fetchTermsOfService(cacheControl, callback)

    override fun fetchPrivacyPolicy(cacheControl: CacheControl?, callback: VimeoCallback<Document>): VimeoRequest =
        client.fetchPrivacyPolicy(cacheControl, callback)

    override fun fetchPaymentAddendum(cacheControl: CacheControl?, callback: VimeoCallback<Document>): VimeoRequest =
        client.fetchPaymentAddendum(cacheControl, callback)

    override fun fetchDocument(
        uri: String,
        cacheControl: CacheControl?,
        callback: VimeoCallback<Document>
    ): VimeoRequest = client.fetchDocument(uri, cacheControl, callback)

    override fun fetchFolder(
        uri: String,
        fieldFilter: String?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<Folder>
    ): VimeoRequest = client.fetchFolder(uri, fieldFilter, cacheControl, callback)

    override fun fetchFolderList(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<FolderList>
    ): VimeoRequest = client.fetchFolderList(uri, fieldFilter, queryParams, cacheControl, callback)

    override fun fetchTextTrackList(
        uri: String,
        fieldFilter: String?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<TextTrackList>
    ): VimeoRequest = client.fetchTextTrackList(uri, fieldFilter, cacheControl, callback)

    override fun fetchVideoStatus(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<VideoStatus>
    ): VimeoRequest = client.fetchVideoStatus(uri, fieldFilter, queryParams, cacheControl, callback)

    override fun fetchTeamMembersList(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<TeamMembershipList>
    ): VimeoRequest = client.fetchTeamMembersList(uri, fieldFilter, queryParams, cacheControl, callback)

    override fun acceptTeamInvite(code: String, callback: VimeoCallback<TeamMembership>): VimeoRequest =
        client.acceptTeamInvite(code, callback)

    override fun addUserToTeam(
        uri: String,
        email: String,
        permissionLevel: TeamRoleType,
        folderUri: String?,
        customMessage: String?,
        queryParams: Map<String, String>?,
        callback: VimeoCallback<TeamMembership>
    ): VimeoRequest = client.addUserToTeam(uri, email, permissionLevel, folderUri, customMessage, queryParams, callback)

    override fun addUserToTeam(
        team: Team,
        email: String,
        permissionLevel: TeamRoleType,
        folder: Folder?,
        customMessage: String?,
        queryParams: Map<String, String>?,
        callback: VimeoCallback<TeamMembership>
    ): VimeoRequest = client.addUserToTeam(team, email, permissionLevel, folder, customMessage, queryParams, callback)

    override fun addUserToVideoAsMember(
        team: Team,
        email: String,
        permissionLevel: TeamRoleType,
        videoUri: String?,
        customMessage: String?,
        queryParams: Map<String, String>?,
        callback: VimeoCallback<TeamMembership>
    ): VimeoRequest = client.addUserToVideoAsMember(
        team, email, permissionLevel, videoUri, customMessage, queryParams, callback
    )

    override fun removeUserFromTeam(
        uri: String,
        queryParams: Map<String, String>?,
        callback: VimeoCallback<Unit>
    ): VimeoRequest = client.removeUserFromTeam(uri, queryParams, callback)

    override fun removeUserFromTeam(
        membership: TeamMembership,
        queryParams: Map<String, String>?,
        callback: VimeoCallback<Unit>
    ): VimeoRequest = client.removeUserFromTeam(membership, queryParams, callback)

    override fun changeUserRole(
        uri: String,
        role: TeamRoleType,
        folderUri: String?,
        queryParams: Map<String, String>?,
        callback: VimeoCallback<TeamMembership>
    ): VimeoRequest = client.changeUserRole(uri, role, folderUri, queryParams, callback)

    override fun changeUserRole(
        membership: TeamMembership,
        role: TeamRoleType,
        folder: Folder?,
        queryParams: Map<String, String>?,
        callback: VimeoCallback<TeamMembership>
    ): VimeoRequest = client.changeUserRole(membership, role, folder, queryParams, callback)

    override fun grantTeamMembersFolderAccess(
        uri: String,
        teamMemberIds: List<String>,
        queryParams: Map<String, String>?,
        callback: VimeoCallback<Unit>
    ): VimeoRequest =
        client.grantTeamMembersFolderAccess(uri, teamMemberIds, queryParams, callback)

    override fun fetchEmpty(
        uri: String,
        cacheControl: CacheControl?,
        callback: VimeoCallback<Unit>
    ): VimeoRequest = client.fetchEmpty(uri, cacheControl, callback)

    override fun grantTeamMembersFolderAccess(
        folder: Folder,
        teamMembers: List<TeamMembership>,
        queryParams: Map<String, String>?,
        callback: VimeoCallback<Unit>
    ): VimeoRequest = client.grantTeamMembersFolderAccess(folder, teamMembers, queryParams, callback)

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
    ): VimeoRequest = client.search(
        query,
        searchFilterType,
        fieldFilter,
        searchSortType,
        searchSortDirectionType,
        searchDateType,
        searchDurationType,
        searchFacetTypes,
        category,
        featuredVideoCount,
        containerFieldFilter,
        queryParams,
        callback
    )

    override fun createPictureCollection(uri: String, callback: VimeoCallback<PictureCollection>): VimeoRequest =
        client.createPictureCollection(uri, callback)

    override fun activatePictureCollection(uri: String, callback: VimeoCallback<PictureCollection>): VimeoRequest =
        client.activatePictureCollection(uri, callback)

    override fun activatePictureCollection(
        pictureCollection: PictureCollection,
        callback: VimeoCallback<PictureCollection>
    ): VimeoRequest = client.activatePictureCollection(pictureCollection, callback)

    override fun updateFollow(isFollowing: Boolean, uri: String, callback: VimeoCallback<Unit>): VimeoRequest =
        client.updateFollow(isFollowing, uri, callback)

    override fun updateFollow(
        isFollowing: Boolean,
        followable: Followable,
        callback: VimeoCallback<Unit>
    ): VimeoRequest = client.updateFollow(isFollowing, followable, callback)

    override fun updateVideoLike(
        isLiked: Boolean,
        uri: String,
        password: String?,
        callback: VimeoCallback<Unit>
    ): VimeoRequest = client.updateVideoLike(isLiked, uri, password, callback)

    override fun updateVideoLike(
        isLiked: Boolean,
        video: Video,
        password: String?,
        callback: VimeoCallback<Unit>
    ): VimeoRequest = client.updateVideoLike(isLiked, video, password, callback)

    override fun updateVideoWatchLater(
        isWatchLater: Boolean,
        uri: String,
        password: String?,
        callback: VimeoCallback<Unit>
    ): VimeoRequest = client.updateVideoWatchLater(isWatchLater, uri, password, callback)

    override fun updateVideoWatchLater(
        isWatchLater: Boolean,
        video: Video,
        password: String?,
        callback: VimeoCallback<Unit>
    ): VimeoRequest = client.updateVideoWatchLater(isWatchLater, video, password, callback)

    override fun createComment(
        uri: String,
        comment: String,
        password: String?,
        timeCode: Double?,
        callback: VimeoCallback<Comment>
    ): VimeoRequest = client.createComment(uri, comment, password, timeCode, callback,)

    override fun createComment(
        video: Video,
        comment: String,
        password: String?,
        timeCode: Double?,
        callback: VimeoCallback<Comment>
    ): VimeoRequest = client.createComment(video, comment, password, timeCode, callback)

    override fun createNote(
        uri: String,
        text: String,
        password: String?,
        coordinates: Coordinates,
        timeCode: Double,
        callback: VimeoCallback<Note>
    ): VimeoRequest = client.createNote(uri, text, password, coordinates, timeCode, callback)

    override fun editNote(
        uri: String,
        text: String?,
        coordinates: Coordinates?,
        timeCode: Double?,
        status: NoteStatus?,
        callback: VimeoCallback<Note>
    ): VimeoRequest = client.editNote(uri, text, coordinates, timeCode, status, callback)

    override fun fetchProductList(
        fieldFilter: String?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<ProductList>
    ): VimeoRequest = client.fetchProductList(fieldFilter, cacheControl, callback)

    override fun fetchProduct(
        uri: String,
        fieldFilter: String?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<Product>
    ): VimeoRequest = client.fetchProduct(uri, fieldFilter, cacheControl, callback)

    override fun fetchCurrentUser(
        fieldFilter: String?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<User>
    ): VimeoRequest = client.fetchCurrentUser(fieldFilter, cacheControl, callback)

    override fun fetchSurveyQuestionList(
        fieldFilter: String?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<UserSegmentSurveyList>
    ): VimeoRequest = client.fetchSurveyQuestionList(fieldFilter, cacheControl, callback)

    override fun fetchVideo(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<Video>
    ): VimeoRequest = client.fetchVideo(uri, fieldFilter, queryParams, cacheControl, callback)

    override fun fetchLiveEvent(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<LiveEvent>
    ): VimeoRequest = client.fetchLiveEvent(uri, fieldFilter, queryParams, cacheControl, callback)

    override fun createLiveEvent(
        uri: String,
        title: String,
        privacy: StreamPrivacy?,
        bodyParams: Map<String, Any>?,
        callback: VimeoCallback<LiveEvent>
    ): VimeoRequest = client.createLiveEvent(uri, title, privacy, bodyParams, callback)

    override fun stopLiveEvent(uri: String, callback: VimeoCallback<Video>): VimeoRequest =
        client.stopLiveEvent(uri, callback)

    override fun fetchLiveEventList(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<LiveEventList>
    ): VimeoRequest = client.fetchLiveEventList(uri, fieldFilter, queryParams, cacheControl, callback)

    override fun fetchLiveStats(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<LiveStats>
    ): VimeoRequest = client.fetchLiveStats(uri, fieldFilter, queryParams, cacheControl, callback)

    override fun fetchVideoList(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<VideoList>
    ): VimeoRequest = client.fetchVideoList(uri, fieldFilter, queryParams, cacheControl, callback)

    override fun fetchFeedList(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<FeedList>
    ): VimeoRequest = client.fetchFeedList(uri, fieldFilter, queryParams, cacheControl, callback)

    override fun fetchProjectItemList(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<ProjectItemList>
    ): VimeoRequest = client.fetchProjectItemList(uri, fieldFilter, queryParams, cacheControl, callback)

    override fun fetchTeamList(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<TeamList>
    ): VimeoRequest = client.fetchTeamList(uri, fieldFilter, queryParams, cacheControl, callback)

    override fun fetchProgrammedContentItemList(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<ProgrammedCinemaItemList>
    ): VimeoRequest = client.fetchProgrammedContentItemList(uri, fieldFilter, queryParams, cacheControl, callback)

    override fun fetchRecommendationList(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<RecommendationList>
    ): VimeoRequest = client.fetchRecommendationList(uri, fieldFilter, queryParams, cacheControl, callback)

    override fun fetchSearchResultList(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<SearchResultList>
    ): VimeoRequest = client.fetchSearchResultList(uri, fieldFilter, queryParams, cacheControl, callback)

    override fun fetchSeasonList(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<SeasonList>
    ): VimeoRequest = client.fetchSeasonList(uri, fieldFilter, queryParams, cacheControl, callback)

    override fun fetchNotificationList(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<NotificationList>
    ): VimeoRequest = client.fetchNotificationList(uri, fieldFilter, queryParams, cacheControl, callback)

    override fun fetchUser(
        uri: String,
        fieldFilter: String?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<User>
    ): VimeoRequest = client.fetchUser(uri, fieldFilter, cacheControl, callback)

    override fun fetchUserList(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<UserList>
    ): VimeoRequest = client.fetchUserList(uri, fieldFilter, queryParams, cacheControl, callback)

    override fun fetchCategory(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<Category>
    ): VimeoRequest = client.fetchCategory(uri, fieldFilter, queryParams, cacheControl, callback)

    override fun fetchCategoryList(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<CategoryList>
    ): VimeoRequest = client.fetchCategoryList(uri, fieldFilter, queryParams, cacheControl, callback)

    override fun fetchChannel(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<Channel>
    ): VimeoRequest = client.fetchChannel(uri, fieldFilter, queryParams, cacheControl, callback)

    override fun fetchChannelList(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<ChannelList>
    ): VimeoRequest = client.fetchChannelList(uri, fieldFilter, queryParams, cacheControl, callback)

    override fun fetchAppConfiguration(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<AppConfiguration>
    ): VimeoRequest = client.fetchAppConfiguration(uri, fieldFilter, queryParams, cacheControl, callback)

    override fun fetchAlbum(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<Album>
    ): VimeoRequest = client.fetchAlbum(uri, fieldFilter, queryParams, cacheControl, callback)

    override fun fetchAlbumList(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<AlbumList>
    ): VimeoRequest = client.fetchAlbumList(uri, fieldFilter, queryParams, cacheControl, callback)

    override fun fetchTvodItem(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<TvodItem>
    ): VimeoRequest = client.fetchTvodItem(uri, fieldFilter, queryParams, cacheControl, callback)

    override fun fetchTvodItemList(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<TvodItemList>
    ): VimeoRequest = client.fetchTvodItemList(uri, fieldFilter, queryParams, cacheControl, callback)

    override fun fetchComment(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<Comment>
    ): VimeoRequest = client.fetchComment(uri, fieldFilter, queryParams, cacheControl, callback)

    override fun fetchNote(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<Note>
    ): VimeoRequest = client.fetchNote(uri, fieldFilter, queryParams, cacheControl, callback)

    override fun fetchCommentList(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<CommentList>
    ): VimeoRequest = client.fetchCommentList(uri, fieldFilter, queryParams, cacheControl, callback)

    override fun fetchNoteList(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<NoteList>
    ): VimeoRequest = client.fetchNoteList(uri, fieldFilter, queryParams, cacheControl, callback)

    override fun postContent(
        uri: String,
        bodyParams: List<Any>,
        callback: VimeoCallback<Unit>
    ): VimeoRequest = client.postContent(uri, bodyParams, callback)

    override fun emptyResponsePost(
        uri: String,
        bodyParams: Map<String, String>,
        callback: VimeoCallback<Unit>
    ): VimeoRequest = client.emptyResponsePost(uri, bodyParams, callback)

    override fun emptyResponsePatch(
        uri: String,
        queryParams: Map<String, String>,
        bodyParams: Any,
        callback: VimeoCallback<Unit>
    ): VimeoRequest = client.emptyResponsePatch(uri, queryParams, bodyParams, callback)

    override fun putContentWithUserResponse(
        uri: String,
        queryParams: Map<String, String>,
        bodyParams: Any?,
        callback: VimeoCallback<User>
    ): VimeoRequest = client.putContentWithUserResponse(uri, queryParams, bodyParams, callback)

    override fun putContent(
        uri: String,
        queryParams: Map<String, String>,
        bodyParams: Any?,
        callback: VimeoCallback<Unit>
    ): VimeoRequest = client.putContent(uri, queryParams, bodyParams, callback)

    override fun deleteContent(
        uri: String,
        queryParams: Map<String, String>,
        callback: VimeoCallback<Unit>
    ): VimeoRequest = client.deleteContent(uri, queryParams, callback)

    override fun fetchTeamPermissions(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<TeamPermissionList>
    ): VimeoRequest = client.fetchTeamPermissions(uri, fieldFilter, queryParams, cacheControl, callback)

    override fun fetchTeamPermissions(
        folder: Folder,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<TeamPermissionList>,
        teamEntityQuery: String?
    ): VimeoRequest = client.fetchTeamPermissions(
        folder,
        fieldFilter,
        queryParams,
        cacheControl,
        callback,
        teamEntityQuery
    )

    override fun fetchPermissionPolicyList(
        uri: String,
        fieldFilter: String?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<PermissionPolicyList>
    ): VimeoRequest = client.fetchPermissionPolicyList(uri, fieldFilter, cacheControl, callback)

    override fun fetchPermissionPolicyList(
        user: User,
        fieldFilter: String?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<PermissionPolicyList>
    ): VimeoRequest = client.fetchPermissionPolicyList(user, fieldFilter, cacheControl, callback)

    override fun fetchPermissionPolicy(
        uri: String,
        fieldFilter: String?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<PermissionPolicy>
    ): VimeoRequest = client.fetchPermissionPolicy(uri, fieldFilter, cacheControl, callback)

    override fun fetchPermissionPolicy(
        permissionPolicy: PermissionPolicy,
        fieldFilter: String?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<PermissionPolicy>
    ): VimeoRequest = client.fetchPermissionPolicy(permissionPolicy, fieldFilter, cacheControl, callback)

    override fun fetchPermissionPolicy(
        permissionPolicy: TeamPermissionCurrentPermissions,
        fieldFilter: String?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<PermissionPolicy>
    ): VimeoRequest = client.fetchPermissionPolicy(permissionPolicy, fieldFilter, cacheControl, callback)

    override fun replaceTeamPermission(
        uri: String,
        permissionPolicyUri: String,
        teamEntityType: TeamEntityType,
        teamEntityUri: String,
        callback: VimeoCallback<Unit>
    ): VimeoRequest = client.replaceTeamPermission(uri, permissionPolicyUri, teamEntityType, teamEntityUri, callback)

    override fun replaceTeamPermission(
        teamPermission: TeamPermission,
        permissionPolicy: PermissionPolicy,
        teamEntity: TeamEntity,
        callback: VimeoCallback<Unit>
    ): VimeoRequest = client.replaceTeamPermission(teamPermission, permissionPolicy, teamEntity, callback)

    override fun replaceTeamPermission(
        teamPermissionInteraction: TeamPermissionInteraction,
        permissionPolicy: PermissionPolicy,
        teamEntity: TeamEntity,
        callback: VimeoCallback<Unit>
    ): VimeoRequest = client.replaceTeamPermission(teamPermissionInteraction, permissionPolicy, teamEntity, callback)

    override fun replaceTeamPermission(
        teamPermission: TeamPermission,
        permissionPolicy: TeamPermissionCurrentPermissions,
        teamEntity: TeamEntity,
        callback: VimeoCallback<Unit>
    ): VimeoRequest = replaceTeamPermission(teamPermission, permissionPolicy, teamEntity, callback)

    override fun replaceTeamPermission(
        teamPermissionInteraction: TeamPermissionInteraction,
        permissionPolicy: TeamPermissionCurrentPermissions,
        teamEntity: TeamEntity,
        callback: VimeoCallback<Unit>
    ): VimeoRequest = client.replaceTeamPermission(teamPermissionInteraction, permissionPolicy, teamEntity, callback)

    override fun deleteTeamPermission(
        uri: String,
        teamEntityType: TeamEntityType,
        teamEntityUri: String,
        callback: VimeoCallback<Unit>
    ): VimeoRequest = client.deleteTeamPermission(uri, teamEntityType, teamEntityUri, callback)

    override fun deleteTeamPermission(
        teamPermission: TeamPermission,
        teamEntity: TeamEntity,
        callback: VimeoCallback<Unit>
    ): VimeoRequest = client.deleteTeamPermission(teamPermission, teamEntity, callback)

    override fun deleteTeamPermission(
        teamPermissionInteraction: TeamPermissionInteraction,
        teamEntity: TeamEntity,
        callback: VimeoCallback<Unit>
    ): VimeoRequest = client.deleteTeamPermission(teamPermissionInteraction, teamEntity, callback)

    override fun fetchCapabilities(
        teamOwnerId: String,
        fieldFilter: String?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<Capabilities>
    ): VimeoRequest = client.fetchCapabilities(teamOwnerId, fieldFilter, cacheControl, callback)

    override fun getFeaturedContent(
        teamOwnerId: String,
        fieldFilter: String?,
        callback: VimeoCallback<FeaturedContent>,
        cacheControl: CacheControl?,
    ): VimeoRequest = client.getFeaturedContent(teamOwnerId, fieldFilter, callback, cacheControl)

    override fun getCustomDomains(email: String, callback: VimeoCallback<CustomDomains>): VimeoRequest =
        client.getCustomDomains(email, callback)
}
