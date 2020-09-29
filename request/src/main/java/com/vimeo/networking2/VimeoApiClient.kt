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

import com.vimeo.networking2.common.Followable
import com.vimeo.networking2.config.VimeoApiConfiguration
import com.vimeo.networking2.config.RetrofitSetupModule
import com.vimeo.networking2.enums.CommentPrivacyType
import com.vimeo.networking2.enums.ConnectedAppType
import com.vimeo.networking2.enums.EmbedPrivacyType
import com.vimeo.networking2.enums.NotificationType
import com.vimeo.networking2.enums.ViewPrivacyType
import com.vimeo.networking2.internal.LocalVimeoCallAdapter
import com.vimeo.networking2.internal.MutableVimeoApiClientDelegate
import com.vimeo.networking2.internal.VimeoApiClientImpl
import com.vimeo.networking2.params.BatchPublishToSocialMedia
import com.vimeo.networking2.params.ModifyVideoInAlbumsSpecs
import com.vimeo.networking2.params.ModifyVideosInAlbumSpecs
import com.vimeo.networking2.params.SearchDateType
import com.vimeo.networking2.params.SearchDurationType
import com.vimeo.networking2.params.SearchFacetType
import com.vimeo.networking2.params.SearchFilterType
import com.vimeo.networking2.params.SearchSortDirectionType
import com.vimeo.networking2.params.SearchSortType
import okhttp3.CacheControl
import okhttp3.Credentials
import java.util.concurrent.Executor

/**
 * The Vimeo API client definition.
 *
 * Consumers can instantiate an instance directly using a convenient invoke function.
 * ```Kotlin
 * // Constructing in Kotlin
 * val authenticator = Authenticator(Configuration)
 * val client = VimeoApiClient(Configuration)
 * ```
 * ```
 * // Constructing in Java
 * final Authenticator authenticator = Authenticator.create(Configuration)
 * final VimeoApiClient client = VimeoApiClient.create(Configuration, authenticator)
 * ```
 * For consumers that don't want to manage the instance of the client themselves, there is also a convenient singleton
 * instance that can be initialized and used.
 * ```
 * Authenticator.initialize(Configuration)
 * VimeoApiClient.initialize(Configuration, Authenticator.instance())
 * val clientInstance = VimeoApiClient.instance()
 * ```
 */
@Suppress("ComplexInterface", "LongParameterList")
interface VimeoApiClient {

    /**
     * Create an album.
     *
     * @param name The name of the album.
     * @param albumPrivacy The album's privacy.
     * @param description The optional description of the album.
     * @param bodyParams Other parameters about the album.
     * @param callback The callback which will be notified of the request completion.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun createAlbum(
        name: String,
        albumPrivacy: AlbumPrivacy,
        description: String?,
        bodyParams: Map<String, Any>?,
        callback: VimeoCallback<Album>
    ): VimeoRequest

    /**
     * Edit an album.
     *
     * @param album The album being edited, URI should not be null or empty.
     * @param name The name of the album.
     * @param albumPrivacy The album's privacy.
     * @param description The optional description of the album.
     * @param bodyParams Other parameters about the album.
     * @param callback The callback which will be notified of the request completion.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun editAlbum(
        album: Album,
        name: String,
        albumPrivacy: AlbumPrivacy,
        description: String?,
        bodyParams: Map<String, Any>?,
        callback: VimeoCallback<Album>
    ): VimeoRequest

    /**
     * Edit an album.
     *
     * @param uri The URI of the album being edited, should not be empty.
     * @param name The name of the album.
     * @param albumPrivacy The album's privacy.
     * @param description The optional description of the album.
     * @param bodyParams Other parameters about the album.
     * @param callback The callback which will be notified of the request completion.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun editAlbum(
        uri: String,
        name: String,
        albumPrivacy: AlbumPrivacy,
        description: String?,
        bodyParams: Map<String, Any>?,
        callback: VimeoCallback<Album>
    ): VimeoRequest

    /**
     * Delete an album.
     *
     * @param album The album being deleted, URI should not be null or empty.
     * @param callback The callback which will be notified of the request completion.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun deleteAlbum(
        album: Album,
        callback: VimeoCallback<Unit>
    ): VimeoRequest

    /**
     * Delete an album.
     *
     * @param uri The URI of the album being deleted, should not be empty.
     * @param callback The callback which will be notified of the request completion.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun deleteAlbum(
        uri: String,
        callback: VimeoCallback<Unit>
    ): VimeoRequest

    /**
     * Add a video to an album.
     *
     * @param album The album to which a video is being added.
     * @param video The video which should be added to the album.
     * @param callback The callback which will be notified of the request completion.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun addToAlbum(
        album: Album,
        video: Video,
        callback: VimeoCallback<Unit>
    ): VimeoRequest

    /**
     * Add a video to an album.
     *
     * @param albumUri The URI of the album to which a video is being added, should not be empty.
     * @param videoUri The URI of the video which should be added to the album, should not be empty.
     * @param callback The callback which will be notified of the request completion.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun addToAlbum(
        albumUri: String,
        videoUri: String,
        callback: VimeoCallback<Unit>
    ): VimeoRequest

    /**
     * Remove a video from an album.
     *
     * @param album The album from which the video should be removed, URI should not be null or empty.
     * @param video The video which should be removed from the album, URI should not be null or empty.
     * @param callback The callback which will be notified of the request completion.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun removeFromAlbum(
        album: Album,
        video: Video,
        callback: VimeoCallback<Unit>
    ): VimeoRequest

    /**
     * Remove a video from an album.
     *
     * @param albumUri The URI of the album from which the video should be removed, should not be empty.
     * @param videoUri The URI of the video which should be removed from the album, should not be empty.
     * @param callback The callback which will be notified of the request completion.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun removeFromAlbum(
        albumUri: String,
        videoUri: String,
        callback: VimeoCallback<Unit>
    ): VimeoRequest

    /**
     * Bulk add or remove videos to/from an album.
     *
     * @param album The album to/from which the videos should be added/removed.
     * @param modificationSpecs The bulk list of videos that should be added or removed.
     * @param callback The callback which will be notified of the request completion.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun modifyVideosInAlbum(
        album: Album,
        modificationSpecs: ModifyVideosInAlbumSpecs,
        callback: VimeoCallback<VideoList>
    ): VimeoRequest

    /**
     * Bulk add or remove videos to/from an album.
     *
     * @param uri The URI of the album to/from which the videos should be added/removed, should not be empty.
     * @param modificationSpecs The bulk list of videos that should be added or removed.
     * @param callback The callback which will be notified of the request completion.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun modifyVideosInAlbum(
        uri: String,
        modificationSpecs: ModifyVideosInAlbumSpecs,
        callback: VimeoCallback<VideoList>
    ): VimeoRequest

    /**
     * Bulk add or remove a video to/from multiple albums.
     *
     * @param video The video which should be added/removed to/from multiple albums.
     * @param modificationSpecs The bulk list of albums to/from which the video will be added/removed.
     * @param callback The callback which will be notified of the request completion.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun modifyVideoInAlbums(
        video: Video,
        modificationSpecs: ModifyVideoInAlbumsSpecs,
        callback: VimeoCallback<AlbumList>
    ): VimeoRequest

    /**
     * Bulk add or remove a video to/from multiple albums.
     *
     * @param uri The URI video which should be added/removed to/from multiple albums, should not be empty.
     * @param modificationSpecs The bulk list of albums to/from which the video will be added/removed.
     * @param callback The callback which will be notified of the request completion.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun modifyVideoInAlbums(
        uri: String,
        modificationSpecs: ModifyVideoInAlbumsSpecs,
        callback: VimeoCallback<AlbumList>
    ): VimeoRequest

    /**
     * Edit a video.
     *
     * @param uri The URI of the video.
     * @param title The optional title of the video.
     * @param description The optional description of the video.
     * @param password The optional password for the video, should be supplied if the [viewPrivacyType] is set to
     * [ViewPrivacyType.PASSWORD].
     * @param commentPrivacyType The optional comment privacy type.
     * @param allowDownload True to allow downloads of the video, false to disallow, null to leave unchanged.
     * @param allowAddToCollections True to allow the video to be added to collections, false to disallow, null to leave
     * unchanged.
     * @param embedPrivacyType The optional embed privacy type.
     * @param viewPrivacyType The optional view privacy type.
     * @param bodyParams Other parameters that can be set on the video.
     * @param callback The callback which will be notified of the request completion.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun editVideo(
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
    ): VimeoRequest

    /**
     * Edit a video.
     *
     * @param video The video to be edited, URI should not be null or empty.
     * @param title The optional title of the video.
     * @param description The optional description of the video.
     * @param password The optional password for the video, should be supplied if the [viewPrivacyType] is set to
     * [ViewPrivacyType.PASSWORD].
     * @param commentPrivacyType The optional comment privacy type.
     * @param allowDownload True to allow downloads of the video, false to disallow, null to leave unchanged.
     * @param allowAddToCollections True to allow the video to be added to collections, false to disallow, null to leave
     * unchanged.
     * @param embedPrivacyType The optional embed privacy type.
     * @param viewPrivacyType The optional view privacy type.
     * @param bodyParams Other parameters that can be set on the video.
     * @param callback The callback which will be notified of the request completion.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun editVideo(
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
    ): VimeoRequest

    /**
     * Edit a user.
     *
     * @param uri The URI of the user.
     * @param name The optional name of the user.
     * @param location The optional location description of the user.
     * @param bio The optional bio of the user.
     * @param callback The callback which will be notified of the request completion.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun editUser(
        uri: String,
        name: String?,
        location: String?,
        bio: String?,
        callback: VimeoCallback<User>
    ): VimeoRequest

    /**
     * Edit a user.
     *
     * @param user The user to be edited, URI should not be null or empty.
     * @param name The optional name of the user.
     * @param location The optional location description of the user.
     * @param bio The optional bio of the user.
     * @param callback The callback which will be notified of the request completion.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun editUser(
        user: User,
        name: String?,
        location: String?,
        bio: String?,
        callback: VimeoCallback<User>
    ): VimeoRequest

    /**
     * Edit the notifications that the user is subscribed to.
     *
     * @param subscriptionMap The map of subscription names and whether they are enabled or disabled.
     * @param callback The callback which will be notified of the request completion.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun editSubscriptions(
        subscriptionMap: Map<NotificationType, Boolean>,
        callback: VimeoCallback<NotificationSubscriptions>
    ): VimeoRequest

    /**
     * Create a connection between Vimeo and the provided destination. Only one connection can exist at a time between
     * Vimeo and each destination.
     *
     * @param type The type of destination to which the consumer is connecting.
     * @param authorization The authorization that enables the connection.
     * @param clientId The ID of the client used to perform the connection.
     * @param callback The callback which will be notified of the request completion.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun createConnectedApp(
        type: ConnectedAppType,
        authorization: String,
        clientId: String,
        callback: VimeoCallback<ConnectedApp>
    ): VimeoRequest

    /**
     * Delete the connection between Vimeo and the provided destination if it exists.
     *
     * @param type The type of destination from which the consumer is disconnecting.
     * @param callback The callback which will be notified of the request completion.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun deleteConnectedApp(type: ConnectedAppType, callback: VimeoCallback<Unit>): VimeoRequest

    /**
     * Publish a post to any of several destinations.
     *
     * @param uri The URI to which the consumer supplies the data to publish.
     * @param publishData The post information which will be published to each of the platforms.
     * @param callback The callback which will be notified of the request completion.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun putPublishJob(
        uri: String,
        publishData: BatchPublishToSocialMedia,
        callback: VimeoCallback<PublishJob>
    ): VimeoRequest

    /**
     * Publish a post to any of several destinations.
     *
     * @param video The video which will be published to a destination.
     * @param publishData The post information which will be published to each of the platforms.
     * @param callback The callback which will be notified of the request completion.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun putPublishJob(
        video: Video,
        publishData: BatchPublishToSocialMedia,
        callback: VimeoCallback<PublishJob>
    ): VimeoRequest

    /**
     * Create a picture collection which can be used to upload pictures.
     *
     * @param uri The URI of the collection creation endpoint.
     * @param callback The callback which will be notified of the request completion.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun createPictureCollection(
        uri: String,
        callback: VimeoCallback<PictureCollection>
    ): VimeoRequest

    /**
     * Mark picture collection created by [createPictureCollection] as active after images have been uploaded to it.
     *
     * @param uri The URI of the collection being activated.
     * @param callback The callback which will be notified of the request completion.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun activatePictureCollection(
        uri: String,
        callback: VimeoCallback<PictureCollection>
    ): VimeoRequest

    /**
     * Mark picture collection created by [createPictureCollection] as active after images have been uploaded to it.
     *
     * @param pictureCollection The collection being activated, URI should not be null or empty.
     * @param callback The callback which will be notified of the request completion.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun activatePictureCollection(
        pictureCollection: PictureCollection,
        callback: VimeoCallback<PictureCollection>
    ): VimeoRequest

    /**
     * Update a [Followable] interaction as either followed or unfollowed.
     *
     * @param isFollowing True if the user should be following, false otherwise.
     * @param uri The URI of the [Followable].
     * @param callback The callback which will be notified of the request completion.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun updateFollow(
        isFollowing: Boolean,
        uri: String,
        callback: VimeoCallback<Unit>
    ): VimeoRequest

    /**
     * Update a [Followable] interaction as either followed or unfollowed.
     *
     * @param isFollowing True if the user should be following, false otherwise.
     * @param followable The followable that should be followed or unfollowed.
     * @param callback The callback which will be notified of the request completion.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun updateFollow(
        isFollowing: Boolean,
        followable: Followable,
        callback: VimeoCallback<Unit>
    ): VimeoRequest

    /**
     * Update the like interaction of a [Video].
     *
     * @param isLiked True if the video should be liked, false otherwise.
     * @param uri The URI of the [Video].
     * @param password The optional password that will be needed to like the [Video] if it is password protected.
     * @param callback The callback which will be notified of the request completion.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun updateVideoLike(
        isLiked: Boolean,
        uri: String,
        password: String?,
        callback: VimeoCallback<Unit>
    ): VimeoRequest

    /**
     * Update the like interaction of a [Video].
     *
     * @param isLiked True if the video should be liked, false otherwise.
     * @param video The [Video] which should be liked or disliked.
     * @param password The optional password that will be needed to like the [Video] if it is password protected.
     * @param callback The callback which will be notified of the request completion.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun updateVideoLike(
        isLiked: Boolean,
        video: Video,
        password: String?,
        callback: VimeoCallback<Unit>
    ): VimeoRequest

    /**
     * Update the watch later interaction of a [Video].
     *
     * @param isWatchLater True if the video should be added to watch later, false otherwise.
     * @param uri The URI of the [Video].
     * @param password The optional password that will be needed to add the [Video] watch later if it is password
     * protected.
     * @param callback The callback which will be notified of the request completion.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun updateVideoWatchLater(
        isWatchLater: Boolean,
        uri: String,
        password: String?,
        callback: VimeoCallback<Unit>
    ): VimeoRequest

    /**
     * Update the watch later interaction of a [Video].
     *
     * @param isWatchLater True if the video should be added to watch later, false otherwise.
     * @param video The [Video] which should be added/removed to/from watch later.
     * @param password The optional password that will be needed to add the [Video] watch later if it is password
     * protected.
     * @param callback The callback which will be notified of the request completion.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun updateVideoWatchLater(
        isWatchLater: Boolean,
        video: Video,
        password: String?,
        callback: VimeoCallback<Unit>
    ): VimeoRequest

    /**
     * Create a comment on a [Video].
     *
     * @param uri The URI of the [Video] comment endpoint.
     * @param comment The content of the comment.
     * @param password The optional password will be needed to comment on the [Video] if it is password protected.
     * @param callback The callback which will be notified of the request completion.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun createComment(
        uri: String,
        comment: String,
        password: String?,
        callback: VimeoCallback<Comment>
    ): VimeoRequest

    /**
     * Create a comment on a [Video].
     *
     * @param video The video upon which the user is commenting.
     * @param comment The content of the comment.
     * @param password The optional password will be needed to comment on the [Video] if it is password protected.
     * @param callback The callback which will be notified of the request completion.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun createComment(
        video: Video,
        comment: String,
        password: String?,
        callback: VimeoCallback<Comment>
    ): VimeoRequest

    /**
     * Fetch the products that a consumer can purchase from Vimeo.
     *
     * @param fieldFilter The fields that should be returned by the server in the response, null indicates all should be
     * returned.
     * @param cacheControl The optional cache behavior for the request, null indicates that the default cache behavior
     * should be used.
     * @param callback The callback which will be notified of the request completion.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun fetchProductList(
        fieldFilter: String?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<ProductList>
    ): VimeoRequest

    /**
     * Fetch a [Product] from the provided endpoint.
     *
     * @param uri The URI from which content will be requested.
     * @param fieldFilter The fields that should be returned by the server in the response, null indicates all should be
     * returned.
     * @param cacheControl The optional cache behavior for the request, null indicates that the default cache behavior
     * should be used.
     * @param callback The callback which will be notified of the request completion.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun fetchProduct(
        uri: String,
        fieldFilter: String?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<Product>
    ): VimeoRequest

    /**
     * Fetch the [User] that is currently logged into the client.
     *
     * @param fieldFilter The fields that should be returned by the server in the response, null indicates all should be
     * returned.
     * @param cacheControl The optional cache behavior for the request, null indicates that the default cache behavior
     * should be used.
     * @param callback The callback which will be notified of the request completion.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun fetchCurrentUser(
        fieldFilter: String?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<User>
    ): VimeoRequest

    /**
     * Fetch a [Video] from the provided endpoint.
     *
     * @param uri The URI from which content will be requested.
     * @param fieldFilter The fields that should be returned by the server in the response, null indicates all should be
     * returned.
     * @param queryParams Optional map used to refine the response from the API.
     * @param cacheControl The optional cache behavior for the request, null indicates that the default cache behavior
     * should be used.
     * @param callback The callback which will be notified of the request completion.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun fetchVideo(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<Video>
    ): VimeoRequest

    /**
     * Fetch [LiveStats] from the provided endpoint.
     *
     * @param uri The URI from which content will be requested.
     * @param fieldFilter The fields that should be returned by the server in the response, null indicates all should be
     * returned.
     * @param queryParams Optional map used to refine the response from the API.
     * @param cacheControl The optional cache behavior for the request, null indicates that the default cache behavior
     * should be used.
     * @param callback The callback which will be notified of the request completion.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun fetchLiveStats(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<LiveStats>
    ): VimeoRequest

    /**
     * Fetch a [VideoList] from the provided endpoint.
     *
     * @param uri The URI from which content will be requested.
     * @param fieldFilter The fields that should be returned by the server in the response, null indicates all should be
     * returned.
     * @param queryParams Optional map used to refine the response from the API.
     * @param cacheControl The optional cache behavior for the request, null indicates that the default cache behavior
     * should be used.
     * @param callback The callback which will be notified of the request completion.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun fetchVideoList(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<VideoList>
    ): VimeoRequest

    /**
     * Fetch a [FeedList] from the provided endpoint.
     *
     * @param uri The URI from which content will be requested.
     * @param fieldFilter The fields that should be returned by the server in the response, null indicates all should be
     * returned.
     * @param queryParams Optional map used to refine the response from the API.
     * @param cacheControl The optional cache behavior for the request, null indicates that the default cache behavior
     * should be used.
     * @param callback The callback which will be notified of the request completion.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun fetchFeedList(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<FeedList>
    ): VimeoRequest

    /**
     * Fetch a [ProjectItemList] from the provided endpoint.
     *
     * @param uri The URI from which content will be requested.
     * @param fieldFilter The fields that should be returned by the server in the response, null indicates all should be
     * returned.
     * @param queryParams Optional map used to refine the response from the API.
     * @param cacheControl The optional cache behavior for the request, null indicates that the default cache behavior
     * should be used.
     * @param callback The callback which will be notified of the request completion.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun fetchProjectItemList(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<ProjectItemList>
    ): VimeoRequest

    /**
     * Fetch a [ProgrammedContentItemList] from the provided endpoint.
     *
     * @param uri The URI from which content will be requested.
     * @param fieldFilter The fields that should be returned by the server in the response, null indicates all should be
     * returned.
     * @param queryParams Optional map used to refine the response from the API.
     * @param cacheControl The optional cache behavior for the request, null indicates that the default cache behavior
     * should be used.
     * @param callback The callback which will be notified of the request completion.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun fetchProgrammedContentItemList(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<ProgrammedContentItemList>
    ): VimeoRequest

    /**
     * Fetch a [RecommendationList] from the provided endpoint.
     *
     * @param uri The URI from which content will be requested.
     * @param fieldFilter The fields that should be returned by the server in the response, null indicates all should be
     * returned.
     * @param queryParams Optional map used to refine the response from the API.
     * @param cacheControl The optional cache behavior for the request, null indicates that the default cache behavior
     * should be used.
     * @param callback The callback which will be notified of the request completion.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun fetchRecommendationList(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<RecommendationList>
    ): VimeoRequest

    /**
     * Fetch a [SearchResultList] from the provided endpoint.
     *
     * @param uri The URI from which content will be requested.
     * @param fieldFilter The fields that should be returned by the server in the response, null indicates all should be
     * returned.
     * @param queryParams Optional map used to refine the response from the API.
     * @param cacheControl The optional cache behavior for the request, null indicates that the default cache behavior
     * should be used.
     * @param callback The callback which will be notified of the request completion.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun fetchSearchResultList(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<SearchResultList>
    ): VimeoRequest

    /**
     * Fetch a [SeasonList] from the provided endpoint.
     *
     * @param uri The URI from which content will be requested.
     * @param fieldFilter The fields that should be returned by the server in the response, null indicates all should be
     * returned.
     * @param queryParams Optional map used to refine the response from the API.
     * @param cacheControl The optional cache behavior for the request, null indicates that the default cache behavior
     * should be used.
     * @param callback The callback which will be notified of the request completion.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun fetchSeasonList(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<SeasonList>
    ): VimeoRequest

    /**
     * Fetch a [NotificationList] from the provided endpoint.
     *
     * @param uri The URI from which content will be requested.
     * @param fieldFilter The fields that should be returned by the server in the response, null indicates all should be
     * returned.
     * @param queryParams Optional map used to refine the response from the API.
     * @param cacheControl The optional cache behavior for the request, null indicates that the default cache behavior
     * should be used.
     * @param callback The callback which will be notified of the request completion.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun fetchNotificationList(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<NotificationList>
    ): VimeoRequest

    /**
     * Fetch a [User] from the provided endpoint.
     *
     * @param uri The URI from which content will be requested.
     * @param fieldFilter The fields that should be returned by the server in the response, null indicates all should be
     * returned.
     * @param cacheControl The optional cache behavior for the request, null indicates that the default cache behavior
     * should be used.
     * @param callback The callback which will be notified of the request completion.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun fetchUser(
        uri: String,
        fieldFilter: String?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<User>
    ): VimeoRequest

    /**
     * Fetch a [UserList] from the provided endpoint.
     *
     * @param uri The URI from which content will be requested.
     * @param fieldFilter The fields that should be returned by the server in the response, null indicates all should be
     * returned.
     * @param queryParams Optional map used to refine the response from the API.
     * @param cacheControl The optional cache behavior for the request, null indicates that the default cache behavior
     * should be used.
     * @param callback The callback which will be notified of the request completion.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun fetchUserList(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<UserList>
    ): VimeoRequest

    /**
     * Fetch a [Category] from the provided endpoint.
     *
     * @param uri The URI from which content will be requested.
     * @param fieldFilter The fields that should be returned by the server in the response, null indicates all should be
     * returned.
     * @param queryParams Optional map used to refine the response from the API.
     * @param cacheControl The optional cache behavior for the request, null indicates that the default cache behavior
     * should be used.
     * @param callback The callback which will be notified of the request completion.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun fetchCategory(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<Category>
    ): VimeoRequest

    /**
     * Fetch a [CategoryList] from the provided endpoint.
     *
     * @param uri The URI from which content will be requested.
     * @param fieldFilter The fields that should be returned by the server in the response, null indicates all should be
     * returned.
     * @param queryParams Optional map used to refine the response from the API.
     * @param cacheControl The optional cache behavior for the request, null indicates that the default cache behavior
     * should be used.
     * @param callback The callback which will be notified of the request completion.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun fetchCategoryList(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<CategoryList>
    ): VimeoRequest

    /**
     * Fetch a [Channel] from the provided endpoint.
     *
     * @param uri The URI from which content will be requested.
     * @param fieldFilter The fields that should be returned by the server in the response, null indicates all should be
     * returned.
     * @param queryParams Optional map used to refine the response from the API.
     * @param cacheControl The optional cache behavior for the request, null indicates that the default cache behavior
     * should be used.
     * @param callback The callback which will be notified of the request completion.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun fetchChannel(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<Channel>
    ): VimeoRequest

    /**
     * Fetch a [ChannelList] from the provided endpoint.
     *
     * @param uri The URI from which content will be requested.
     * @param fieldFilter The fields that should be returned by the server in the response, null indicates all should be
     * returned.
     * @param queryParams Optional map used to refine the response from the API.
     * @param cacheControl The optional cache behavior for the request, null indicates that the default cache behavior
     * should be used.
     * @param callback The callback which will be notified of the request completion.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun fetchChannelList(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<ChannelList>
    ): VimeoRequest

    /**
     * Fetch an [AppConfiguration] from the provided endpoint.
     *
     * @param uri The URI from which content will be requested.
     * @param fieldFilter The fields that should be returned by the server in the response, null indicates all should be
     * returned.
     * @param queryParams Optional map used to refine the response from the API.
     * @param cacheControl The optional cache behavior for the request, null indicates that the default cache behavior
     * should be used.
     * @param callback The callback which will be notified of the request completion.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun fetchAppConfiguration(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<AppConfiguration>
    ): VimeoRequest

    /**
     * Fetch an [Album] from the provided endpoint.
     *
     * @param uri The URI from which content will be requested.
     * @param fieldFilter The fields that should be returned by the server in the response, null indicates all should be
     * returned.
     * @param queryParams Optional map used to refine the response from the API.
     * @param cacheControl The optional cache behavior for the request, null indicates that the default cache behavior
     * should be used.
     * @param callback The callback which will be notified of the request completion.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun fetchAlbum(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<Album>
    ): VimeoRequest

    /**
     * Fetch an [AlbumList] from the provided endpoint.
     *
     * @param uri The URI from which content will be requested.
     * @param fieldFilter The fields that should be returned by the server in the response, null indicates all should be
     * returned.
     * @param queryParams Optional map used to refine the response from the API.
     * @param cacheControl The optional cache behavior for the request, null indicates that the default cache behavior
     * should be used.
     * @param callback The callback which will be notified of the request completion.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun fetchAlbumList(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<AlbumList>
    ): VimeoRequest

    /**
     * Fetch a [TvodItem] from the provided endpoint.
     *
     * @param uri The URI from which content will be requested.
     * @param fieldFilter The fields that should be returned by the server in the response, null indicates all should be
     * returned.
     * @param queryParams Optional map used to refine the response from the API.
     * @param cacheControl The optional cache behavior for the request, null indicates that the default cache behavior
     * should be used.
     * @param callback The callback which will be notified of the request completion.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun fetchTvodItem(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<TvodItem>
    ): VimeoRequest

    /**
     * Fetch a [TvodItemList] from the provided endpoint.
     *
     * @param uri The URI from which content will be requested.
     * @param fieldFilter The fields that should be returned by the server in the response, null indicates all should be
     * returned.
     * @param queryParams Optional map used to refine the response from the API.
     * @param cacheControl The optional cache behavior for the request, null indicates that the default cache behavior
     * should be used.
     * @param callback The callback which will be notified of the request completion.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun fetchTvodItemList(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<TvodItemList>
    ): VimeoRequest

    /**
     * Fetch a [Comment] from the provided endpoint.
     *
     * @param uri The URI from which content will be requested.
     * @param fieldFilter The fields that should be returned by the server in the response, null indicates all should be
     * returned.
     * @param queryParams Optional map used to refine the response from the API.
     * @param cacheControl The optional cache behavior for the request, null indicates that the default cache behavior
     * should be used.
     * @param callback The callback which will be notified of the request completion.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun fetchComment(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<Comment>
    ): VimeoRequest

    /**
     * Fetch a [CommentList] from the provided endpoint.
     *
     * @param uri The URI from which content will be requested.
     * @param fieldFilter The fields that should be returned by the server in the response, null indicates all should be
     * returned.
     * @param queryParams Optional map used to refine the response from the API.
     * @param cacheControl The optional cache behavior for the request, null indicates that the default cache behavior
     * should be used.
     * @param callback The callback which will be notified of the request completion.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun fetchCommentList(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<CommentList>
    ): VimeoRequest

    /**
     * Fetch the Terms of Service [Document].
     *
     * @param cacheControl The optional cache behavior for the request, null indicates that the default cache behavior
     * should be used.
     * @param callback The callback which will be notified of the request completion.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun fetchTermsOfService(
        cacheControl: CacheControl?,
        callback: VimeoCallback<Document>
    ): VimeoRequest

    /**
     * Fetch the Privacy Policy [Document].
     *
     * @param cacheControl The optional cache behavior for the request, null indicates that the default cache behavior
     * should be used.
     * @param callback The callback which will be notified of the request completion.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun fetchPrivacyPolicy(
        cacheControl: CacheControl?,
        callback: VimeoCallback<Document>
    ): VimeoRequest

    /**
     * Fetch the Payment Addendum [Document].
     *
     * @param cacheControl The optional cache behavior for the request, null indicates that the default cache behavior
     * should be used.
     * @param callback The callback which will be notified of the request completion.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun fetchPaymentAddendum(
        cacheControl: CacheControl?,
        callback: VimeoCallback<Document>
    ): VimeoRequest

    /**
     * Fetch a generic [Document] from the provided endpoint.
     *
     * @param uri The URI from which content will be requested.
     * @param cacheControl The optional cache behavior for the request, null indicates that the default cache behavior
     * should be used.
     * @param callback The callback which will be notified of the request completion.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun fetchDocument(
        uri: String,
        cacheControl: CacheControl?,
        callback: VimeoCallback<Document>
    ): VimeoRequest

    /**
     * Fetch a [Folder] from the provided endpoint.
     *
     * @param uri The URI from which content will be requested.
     * @param fieldFilter The fields that should be returned by the server in the response, null indicates all should be
     * returned.
     * @param cacheControl The optional cache behavior for the request, null indicates that the default cache behavior
     * should be used.
     * @param callback The callback which will be notified of the request completion.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun fetchFolder(
        uri: String,
        fieldFilter: String?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<Folder>
    ): VimeoRequest

    /**
     * Fetch the [ConnectedAppList] that contains the list of connected destinations.
     *
     * @param fieldFilter The fields that should be returned by the server in the response, null indicates all should be
     * returned.
     * @param cacheControl The optional cache behavior for the request, null indicates that the default cache behavior
     * should be used.
     * @param callback The callback which will be notified of the request completion.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun fetchConnectedApps(
        fieldFilter: String?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<ConnectedAppList>
    ): VimeoRequest

    /**
     * Fetch the [ConnectedApp] associated with a specific [ConnectedAppType].
     *
     * @param type The type of destination that should be returned.
     * @param fieldFilter The fields that should be returned by the server in the response, null indicates all should be
     * returned.
     * @param cacheControl The optional cache behavior for the request, null indicates that the default cache behavior
     * should be used.
     * @param callback The callback which will be notified of the request completion.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun fetchConnectedApp(
        type: ConnectedAppType,
        fieldFilter: String?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<ConnectedApp>
    ): VimeoRequest

    /**
     * Fetch a [PublishJob] from the provided endpoint.
     *
     * @param uri The URI from which content will be requested.
     * @param fieldFilter The fields that should be returned by the server in the response, null indicates all should be
     * returned.
     * @param cacheControl The optional cache behavior for the request, null indicates that the default cache behavior
     * should be used.
     * @param callback The callback which will be notified of the request completion.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun fetchPublishJob(
        uri: String,
        fieldFilter: String?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<PublishJob>
    ): VimeoRequest

    /**
     * Fetch a [TextTrackList] for a [Video] from the provided endpoint.
     *
     * @param uri The URI from which content will be requested.
     * @param fieldFilter The fields that should be returned by the server in the response, null indicates all should be
     * returned.
     * @param cacheControl The optional cache behavior for the request, null indicates that the default cache behavior
     * should be used.
     * @param callback The callback which will be notified of the request completion.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun fetchTextTrackList(
        uri: String,
        fieldFilter: String?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<TextTrackList>
    ): VimeoRequest

    /**
     * Fetch a [VideoStatus] from the provided endpoint.
     *
     * @param uri The URI from which content will be requested.
     * @param fieldFilter The fields that should be returned by the server in the response, null indicates all should be
     * returned.
     * @param queryParams Optional map used to refine the response from the API.
     * @param cacheControl The optional cache behavior for the request, null indicates that the default cache behavior
     * should be used.
     * @param callback The callback which will be notified of the request completion.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun fetchVideoStatus(
        uri: String,
        fieldFilter: String?,
        queryParams: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<VideoStatus>
    ): VimeoRequest

    /**
     * Fetch an empty response from the provided endpoint.
     *
     * @param uri The URI from which content will be requested.
     * @param cacheControl The optional cache behavior for the request, null indicates that the default cache behavior
     * should be used.
     * @param callback The callback which will be notified of the request completion.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun fetchEmpty(
        uri: String,
        cacheControl: CacheControl?,
        callback: VimeoCallback<Unit>
    ): VimeoRequest

    /**
     * Search Vimeo with the provided search parameters.
     *
     * @param query The query string for which Vimeo will be searched.
     * @param searchFilterType The type of content being searched (e.g. videos or users), required.
     * @param fieldFilter The fields that should be returned by the server in the response, null indicates all should be
     * returned.
     * @param searchSortType The type of sorting that should be performed, if any.
     * @param searchSortDirectionType The direction in which the sorting specified by [searchSortType] should be
     * performed.
     * @param searchDateType The time period during which a video was uploaded. Only used for searching video types.
     * @param searchDurationType The relative length of the video. Only used for searching video types.
     * @param searchFacetTypes The facets that should be included in the search.
     * @param category The category being searched.
     * @param featuredVideoCount The number of videos that that are featured.
     * @param containerFieldFilter The field filters that should be applied to the container, null indicates all should
     * be returned.
     * @param queryParams Additional parameters that should be included in the composite query.
     * @param callback The callback which will be notified of the request completion.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun search(
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
    ): VimeoRequest

    /**
     * Perform a POST to a generic endpoint with an empty response.
     *
     * @param uri The URI of the endpoint.
     * @param bodyParams The generic body of the POST.
     * @param callback The callback which will be notified of the request completion.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun postContent(
        uri: String,
        bodyParams: List<Any>,
        callback: VimeoCallback<Unit>
    ): VimeoRequest

    /**
     * Perform a POST to a generic endpoint with an empty response.
     *
     * @param uri The URI of the endpoint.
     * @param bodyParams The body of the POST as a [Map] of [Strings][String].
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun emptyResponsePost(
        uri: String,
        bodyParams: Map<String, String>,
        callback: VimeoCallback<Unit>
    ): VimeoRequest

    /**
     * Perform a PATCH to a generic endpoint with an empty response.
     *
     * @param uri The URI of the endpoint.
     * @param queryParams The query parameters included in the PATCH.
     * @param bodyParams The body of the PATCH.
     * @param callback The callback which will be notified of the request completion.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun emptyResponsePatch(
        uri: String,
        queryParams: Map<String, String>,
        bodyParams: Any,
        callback: VimeoCallback<Unit>
    ): VimeoRequest

    /**
     * Perform a PUT to a generic endpoint that returns a [User].
     *
     * @param uri The URI of the endpoint.
     * @param queryParams The query parameters included in the PUT.
     * @param bodyParams The optional body of the PUT.
     * @param callback The callback which will be notified of the request completion.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun putContentWithUserResponse(
        uri: String,
        queryParams: Map<String, String>,
        bodyParams: Any?,
        callback: VimeoCallback<User>
    ): VimeoRequest

    /**
     * Perform a PUT to a generic endpoint with an empty response..
     *
     * @param uri The URI of the endpoint.
     * @param queryParams The query parameters included in the PUT.
     * @param bodyParams The optional body of the PUT.
     * @param callback The callback which will be notified of the request completion.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun putContent(
        uri: String,
        queryParams: Map<String, String>,
        bodyParams: Any?,
        callback: VimeoCallback<Unit>
    ): VimeoRequest

    /**
     * Delete content at the provided generic endpoint.
     *
     * @param uri The URI of the endpoint.
     * @param queryParams The query parameters to include in the DELETE.
     * @param callback The callback which will be notified of the request completion.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun deleteContent(
        uri: String,
        queryParams: Map<String, String>,
        callback: VimeoCallback<Unit>
    ): VimeoRequest

    companion object {

        private val delegate: MutableVimeoApiClientDelegate = MutableVimeoApiClientDelegate()

        /**
         * Initialize the singleton instance of the [VimeoApiClient] with a [VimeoApiConfiguration]. If the client was
         * already initialized, this will reconfigure it. This function must be called before [instance] is used.
         *
         * @param vimeoApiConfiguration The configuration used by the client.
         * @param authenticator The authenticator used by the client to obtain authorization to make requests.
         */
        @JvmStatic
        fun initialize(vimeoApiConfiguration: VimeoApiConfiguration, authenticator: Authenticator) {
            delegate.actual = VimeoApiClient(vimeoApiConfiguration, authenticator)
        }

        /**
         * Access the singleton instance of the [VimeoApiClient]. Always returns the same instance.
         */
        @JvmStatic
        fun instance(): VimeoApiClient = delegate

        /**
         * Create an instance of [VimeoApiClient] to make requests.
         *
         * @param vimeoApiConfiguration All the server configuration (client id and secret, custom interceptors, read
         * timeouts, base url etc...) that can be set for authentication and making requests.
         * @param authenticator The authenticator instance used to obtain authorization to make requests.
         */
        @JvmStatic
        @JvmName("create")
        operator fun invoke(
            vimeoApiConfiguration: VimeoApiConfiguration,
            authenticator: Authenticator
        ): VimeoApiClient {
            val retrofit = RetrofitSetupModule.retrofit(vimeoApiConfiguration)
            val vimeoService = retrofit.create(VimeoService::class.java)
            val basicAuthHeader = Credentials.basic(vimeoApiConfiguration.clientId, vimeoApiConfiguration.clientSecret)
            val synchronousExecutor = Executor { it.run() }
            return VimeoApiClientImpl(
                vimeoService,
                authenticator,
                vimeoApiConfiguration,
                basicAuthHeader,
                LocalVimeoCallAdapter(retrofit.callbackExecutor() ?: synchronousExecutor)
            )
        }
    }

}
