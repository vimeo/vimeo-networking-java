package com.vimeo.networking2.internal

import com.vimeo.networking2.*
import com.vimeo.networking2.common.Followable
import com.vimeo.networking2.enums.CommentPrivacyType
import com.vimeo.networking2.enums.ConnectedAppType
import com.vimeo.networking2.enums.EmbedPrivacyType
import com.vimeo.networking2.enums.ViewPrivacyType
import com.vimeo.networking2.params.BatchPublishToSocialMedia
import com.vimeo.networking2.params.ModifyVideoInAlbumsSpecs
import com.vimeo.networking2.params.ModifyVideosInAlbumSpecs
import okhttp3.CacheControl

/**
 * A [VimeoApiClient] that delegates its implementation to an internal mutable instance [actual].
 *
 * @param actual The actual implementation of [VimeoApiClient], defaults to null.
 */
internal class MutableVimeoApiClientDelegate(var actual: VimeoApiClient? = null) : VimeoApiClient {

    private val client: VimeoApiClient
        get() = actual ?: throw IllegalStateException(
            "Must call VimeoApiClient.initialize() before calling VimeoApiClient.instance()"
        )

    override fun createAlbum(
        name: String,
        albumPrivacy: AlbumPrivacy,
        description: String?,
        parameters: Map<String, Any>?,
        callback: VimeoCallback<Album>
    ): VimeoRequest = client.createAlbum(name, albumPrivacy, description, parameters, callback)

    override fun editAlbum(
        album: Album,
        name: String,
        albumPrivacy: AlbumPrivacy,
        description: String?,
        parameters: Map<String, Any>?,
        callback: VimeoCallback<Album>
    ): VimeoRequest = client.editAlbum(album, name, albumPrivacy, description, parameters, callback)

    override fun deleteAlbum(album: Album, callback: VimeoCallback<Unit>): VimeoRequest =
        client.deleteAlbum(album, callback)

    override fun addToAlbum(album: Album, video: Video, callback: VimeoCallback<Unit>): VimeoRequest =
        client.addToAlbum(album, video, callback)

    override fun removeFromAlbum(album: Album, video: Video, callback: VimeoCallback<Unit>): VimeoRequest =
        client.removeFromAlbum(album, video, callback)

    override fun modifyVideosInAlbum(
        album: Album,
        modificationSpecs: ModifyVideosInAlbumSpecs,
        callback: VimeoCallback<VideoList>
    ): VimeoRequest = client.modifyVideosInAlbum(album, modificationSpecs, callback)

    override fun modifyVideoInAlbums(
        video: Video,
        modificationSpecs: ModifyVideoInAlbumsSpecs,
        callback: VimeoCallback<AlbumList>
    ): VimeoRequest = client.modifyVideoInAlbums(video, modificationSpecs, callback)

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
        parameters: Map<String, Any>?,
        callback: VimeoCallback<Video>
    ): VimeoRequest = client.editVideo(
        uri,
        title,
        description,
        password,
        commentPrivacyType,
        allowDownload,
        allowAddToCollections,
        embedPrivacyType,
        viewPrivacyType,
        parameters,
        callback
    )

    override fun editUser(
        uri: String,
        name: String?,
        location: String?,
        bio: String?,
        callback: VimeoCallback<User>
    ): VimeoRequest = client.editUser(uri, name, location, bio, callback)

    override fun editSubscriptions(
        subscriptionMap: Map<String, Boolean>,
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
        callback: VimeoCallback<ConnectedApp>
    ): VimeoRequest = client.createConnectedApp(type, authorization, callback)

    override fun deleteConnectedApp(type: ConnectedAppType, callback: VimeoCallback<Unit>): VimeoRequest =
        client.deleteConnectedApp(type, callback)

    override fun fetchPublishJob(
        uri: String,
        fieldFilter: String?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<PublishJob>
    ): VimeoRequest = client.fetchPublishJob(uri, fieldFilter, cacheControl, callback)

    override fun putPublishJob(
        publishUri: String,
        publishData: BatchPublishToSocialMedia,
        callback: VimeoCallback<PublishJob>
    ): VimeoRequest = client.putPublishJob(publishUri, publishData, callback)

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

    override fun fetchTextTrackList(
        uri: String,
        fieldFilter: String?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<TextTrackList>
    ): VimeoRequest = client.fetchTextTrackList(uri, fieldFilter, cacheControl, callback)

    override fun createPictureCollection(uri: String, callback: VimeoCallback<PictureCollection>): VimeoRequest =
        client.createPictureCollection(uri, callback)

    override fun activatePictureCollection(uri: String, callback: VimeoCallback<PictureCollection>): VimeoRequest =
        client.activatePictureCollection(uri, callback)

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
        callback: VimeoCallback<Comment>
    ): VimeoRequest = client.createComment(uri, comment, password, callback)

    override fun createComment(
        video: Video,
        comment: String,
        password: String?,
        callback: VimeoCallback<Comment>
    ): VimeoRequest = client.createComment(video, comment, password, callback)

    override fun fetchProducts(
        fieldFilter: String?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<ProductList>
    ): VimeoRequest = client.fetchProducts(fieldFilter, cacheControl, callback)

    override fun fetchProduct(
        uri: String,
        fieldFilter: String?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<Product>
    ): VimeoRequest = client.fetchProduct(uri, fieldFilter, cacheControl, callback)

    override fun fetchCurrentUser(
        fieldFilter: String?,
        refinementMap: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<User>
    ): VimeoRequest = client.fetchCurrentUser(fieldFilter, refinementMap, cacheControl, callback)

    override fun fetchVideo(
        uri: String,
        fieldFilter: String?,
        refinementMap: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<Video>
    ): VimeoRequest = client.fetchVideo(uri, fieldFilter, refinementMap, cacheControl, callback)

    override fun fetchVideoList(
        uri: String,
        fieldFilter: String?,
        refinementMap: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<VideoList>
    ): VimeoRequest = client.fetchVideoList(uri, fieldFilter, refinementMap, cacheControl, callback)

    override fun fetchFeedList(
        uri: String,
        fieldFilter: String?,
        refinementMap: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<FeedList>
    ): VimeoRequest = client.fetchFeedList(uri, fieldFilter, refinementMap, cacheControl, callback)

    override fun fetchProgrammedContentItemList(
        uri: String,
        fieldFilter: String?,
        refinementMap: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<ProgrammedContentItemList>
    ): VimeoRequest = client.fetchProgrammedContentItemList(uri, fieldFilter, refinementMap, cacheControl, callback)

    override fun fetchRecommendationList(
        uri: String,
        fieldFilter: String?,
        refinementMap: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<RecommendationList>
    ): VimeoRequest = client.fetchRecommendationList(uri, fieldFilter, refinementMap, cacheControl, callback)

    override fun fetchSearchResultList(
        uri: String,
        fieldFilter: String?,
        refinementMap: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<SearchResultList>
    ): VimeoRequest = client.fetchSearchResultList(uri, fieldFilter, refinementMap, cacheControl, callback)

    override fun fetchSeasonList(
        uri: String,
        fieldFilter: String?,
        refinementMap: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<SeasonList>
    ): VimeoRequest = client.fetchSeasonList(uri, fieldFilter, refinementMap, cacheControl, callback)

    override fun fetchNotificationList(
        uri: String,
        fieldFilter: String?,
        refinementMap: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<NotificationList>
    ): VimeoRequest = client.fetchNotificationList(uri, fieldFilter, refinementMap, cacheControl, callback)

    override fun fetchUser(
        uri: String,
        fieldFilter: String?,
        refinementMap: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<User>
    ): VimeoRequest = client.fetchUser(uri, fieldFilter, refinementMap, cacheControl, callback)

    override fun fetchUserList(
        uri: String,
        fieldFilter: String?,
        refinementMap: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<UserList>
    ): VimeoRequest = client.fetchUserList(uri, fieldFilter, refinementMap, cacheControl, callback)

    override fun fetchCategory(
        uri: String,
        fieldFilter: String?,
        refinementMap: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<Category>
    ): VimeoRequest = client.fetchCategory(uri, fieldFilter, refinementMap, cacheControl, callback)

    override fun fetchCategoryList(
        uri: String,
        fieldFilter: String?,
        refinementMap: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<CategoryList>
    ): VimeoRequest = client.fetchCategoryList(uri, fieldFilter, refinementMap, cacheControl, callback)

    override fun fetchChannel(
        uri: String,
        fieldFilter: String?,
        refinementMap: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<Channel>
    ): VimeoRequest = client.fetchChannel(uri, fieldFilter, refinementMap, cacheControl, callback)

    override fun fetchChannelList(
        uri: String,
        fieldFilter: String?,
        refinementMap: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<ChannelList>
    ): VimeoRequest = client.fetchChannelList(uri, fieldFilter, refinementMap, cacheControl, callback)

    override fun fetchAppConfiguration(
        uri: String,
        fieldFilter: String?,
        refinementMap: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<AppConfiguration>
    ): VimeoRequest = client.fetchAppConfiguration(uri, fieldFilter, refinementMap, cacheControl, callback)

    override fun fetchAlbum(
        uri: String,
        fieldFilter: String?,
        refinementMap: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<Album>
    ): VimeoRequest = client.fetchAlbum(uri, fieldFilter, refinementMap, cacheControl, callback)

    override fun fetchAlbumList(
        uri: String,
        fieldFilter: String?,
        refinementMap: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<AlbumList>
    ): VimeoRequest = client.fetchAlbumList(uri, fieldFilter, refinementMap, cacheControl, callback)

    override fun fetchTvodItem(
        uri: String,
        fieldFilter: String?,
        refinementMap: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<TvodItem>
    ): VimeoRequest = client.fetchTvodItem(uri, fieldFilter, refinementMap, cacheControl, callback)

    override fun fetchTvodItemList(
        uri: String,
        fieldFilter: String?,
        refinementMap: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<TvodItemList>
    ): VimeoRequest = client.fetchTvodItemList(uri, fieldFilter, refinementMap, cacheControl, callback)

    override fun fetchComment(
        uri: String,
        fieldFilter: String?,
        refinementMap: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<Comment>
    ): VimeoRequest = client.fetchComment(uri, fieldFilter, refinementMap, cacheControl, callback)

    override fun fetchCommentList(
        uri: String,
        fieldFilter: String?,
        refinementMap: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<CommentList>
    ): VimeoRequest = client.fetchCommentList(uri, fieldFilter, refinementMap, cacheControl, callback)

    override fun postContent(
        uri: String,
        postBody: List<Any>,
        callback: VimeoCallback<Unit>
    ): VimeoRequest = client.postContent(uri, postBody, callback)

    override fun emptyResponsePost(
        uri: String,
        postBody: Map<String, String>,
        callback: VimeoCallback<Unit>
    ): VimeoRequest = client.emptyResponsePost(uri, postBody, callback)

    override fun emptyResponsePatch(
        uri: String,
        queryParams: Map<String, String>,
        patchBody: Any,
        callback: VimeoCallback<Unit>
    ): VimeoRequest = client.emptyResponsePatch(uri, queryParams, patchBody, callback)

    override fun putContentWithUserResponse(
        uri: String,
        options: Map<String, String>,
        body: Any?,
        callback: VimeoCallback<User>
    ): VimeoRequest = client.putContentWithUserResponse(uri, options, body, callback)

    override fun putContent(
        uri: String,
        options: Map<String, String>,
        body: Any?,
        callback: VimeoCallback<Unit>
    ): VimeoRequest = client.putContent(uri, options, body, callback)

    override fun putContent(uri: String, options: Map<String, String>, callback: VimeoCallback<Unit>): VimeoRequest =
        client.putContent(uri, options, callback)

    override fun deleteContent(uri: String, options: Map<String, String>, callback: VimeoCallback<Unit>): VimeoRequest =
        client.deleteContent(uri, options, callback)
}
