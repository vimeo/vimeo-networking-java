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
import com.vimeo.networking2.config.Configuration
import com.vimeo.networking2.config.RetrofitSetupModule
import com.vimeo.networking2.enums.*
import com.vimeo.networking2.internal.LocalVimeoCallAdapter
import com.vimeo.networking2.internal.MutableVimeoApiClientDelegate
import com.vimeo.networking2.internal.VimeoApiClientImpl
import com.vimeo.networking2.params.BatchPublishToSocialMedia
import com.vimeo.networking2.params.ModifyVideoInAlbumsSpecs
import com.vimeo.networking2.params.ModifyVideosInAlbumSpecs
import okhttp3.CacheControl

/**
 * The Vimeo API client definition.
 *
 * Consumers can instantiate an instance directly using a convenient invoke function.
 * ```Kotlin
 * // Constructing in Kotlin
 * val client = VimeoApiClient(Configuration)
 * ```
 * ```
 * // Constructing in Java
 * val client = VimeoApiClient.create(Configuration)
 * ```
 * For consumers that don't want to manage the instance of the client themselves, there is also a convenient singleton
 * instance that can be initialized and used.
 * ```
 * VimeoApiClient.initialize(Configuration)
 * val clientInstance = VimeoApiClient.instance()
 * ```
 */
@Suppress("UndocumentedPublicFunction", "ComplexInterface", "LongParameterList")
interface VimeoApiClient {

    fun createAlbum(
        name: String,
        albumPrivacy: AlbumPrivacy,
        description: String?,
        parameters: Map<String, Any>?,
        callback: VimeoCallback<Album>
    ): VimeoRequest

    fun editAlbum(
        album: Album,
        name: String,
        albumPrivacy: AlbumPrivacy,
        description: String?,
        parameters: Map<String, Any>?,
        callback: VimeoCallback<Album>
    ): VimeoRequest

    fun deleteAlbum(
        album: Album,
        callback: VimeoCallback<Unit>
    ): VimeoRequest

    fun addToAlbum(
        album: Album,
        video: Video,
        callback: VimeoCallback<Unit>
    ): VimeoRequest

    fun removeFromAlbum(
        album: Album,
        video: Video,
        callback: VimeoCallback<Unit>
    ): VimeoRequest

    fun modifyVideosInAlbum(
        album: Album,
        modificationSpecs: ModifyVideosInAlbumSpecs,
        callback: VimeoCallback<VideoList>
    ): VimeoRequest

    fun modifyVideoInAlbums(
        video: Video,
        modificationSpecs: ModifyVideoInAlbumsSpecs,
        callback: VimeoCallback<AlbumList>
    ): VimeoRequest

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
        parameters: Map<String, Any>?,
        callback: VimeoCallback<Video>
    ): VimeoRequest

    fun editUser(
        uri: String,
        name: String?,
        location: String?,
        bio: String?,
        callback: VimeoCallback<User>
    ): VimeoRequest

    fun editSubscriptions(
        subscriptionMap: Map<String, Boolean>,
        callback: VimeoCallback<NotificationSubscriptions>
    ): VimeoRequest

    fun createConnectedApp(
        type: ConnectedAppType,
        authorization: String,
        callback: VimeoCallback<ConnectedApp>
    ): VimeoRequest

    fun deleteConnectedApp(type: ConnectedAppType, callback: VimeoCallback<Unit>): VimeoRequest

    fun putPublishJob(
        publishUri: String,
        publishData: BatchPublishToSocialMedia,
        callback: VimeoCallback<PublishJob>
    ): VimeoRequest

    fun createPictureCollection(
        uri: String,
        callback: VimeoCallback<PictureCollection>
    ): VimeoRequest

    fun activatePictureCollection(
        uri: String,
        callback: VimeoCallback<PictureCollection>
    ): VimeoRequest

    fun updateFollow(
        isFollowing: Boolean,
        uri: String,
        callback: VimeoCallback<Unit>
    ): VimeoRequest

    fun updateFollow(
        isFollowing: Boolean,
        followable: Followable,
        callback: VimeoCallback<Unit>
    ): VimeoRequest

    fun updateVideoLike(
        isLiked: Boolean,
        uri: String,
        password: String?,
        callback: VimeoCallback<Unit>
    ): VimeoRequest

    fun updateVideoLike(
        isLiked: Boolean,
        video: Video,
        password: String?,
        callback: VimeoCallback<Unit>
    ): VimeoRequest

    fun updateVideoWatchLater(
        isWatchLater: Boolean,
        uri: String,
        password: String?,
        callback: VimeoCallback<Unit>
    ): VimeoRequest

    fun updateVideoWatchLater(
        isWatchLater: Boolean,
        video: Video,
        password: String?,
        callback: VimeoCallback<Unit>
    ): VimeoRequest

    fun createComment(
        uri: String,
        comment: String,
        password: String?,
        callback: VimeoCallback<Comment>
    ): VimeoRequest

    fun createComment(
        video: Video,
        comment: String,
        password: String?,
        callback: VimeoCallback<Comment>
    ): VimeoRequest

    fun fetchProducts(
        fieldFilter: String?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<ProductList>
    ): VimeoRequest

    fun fetchCurrentUser(
        fieldFilter: String?,
        refinementMap: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<User>
    ): VimeoRequest

    fun fetchVideo(
        uri: String,
        fieldFilter: String?,
        refinementMap: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<Video>
    ): VimeoRequest

    fun fetchVideoList(
        uri: String,
        fieldFilter: String?,
        refinementMap: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<VideoList>
    ): VimeoRequest

    fun fetchFeedList(
        uri: String,
        fieldFilter: String?,
        refinementMap: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<FeedList>
    ): VimeoRequest

    fun fetchProgrammedContentItemList(
        uri: String,
        fieldFilter: String?,
        refinementMap: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<ProgrammedContentItemList>
    ): VimeoRequest

    fun fetchRecommendationList(
        uri: String,
        fieldFilter: String?,
        refinementMap: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<RecommendationList>
    ): VimeoRequest

    fun fetchSearchResultList(
        uri: String,
        fieldFilter: String?,
        refinementMap: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<SearchResultList>
    ): VimeoRequest

    fun fetchSeasonList(
        uri: String,
        fieldFilter: String?,
        refinementMap: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<SeasonList>
    ): VimeoRequest

    fun fetchNotificationList(
        uri: String,
        fieldFilter: String?,
        refinementMap: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<NotificationList>
    ): VimeoRequest

    fun fetchUser(
        uri: String,
        fieldFilter: String?,
        refinementMap: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<User>
    ): VimeoRequest

    fun fetchUserList(
        uri: String,
        fieldFilter: String?,
        refinementMap: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<UserList>
    ): VimeoRequest

    fun fetchCategory(
        uri: String,
        fieldFilter: String?,
        refinementMap: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<Category>
    ): VimeoRequest

    fun fetchCategoryList(
        uri: String,
        fieldFilter: String?,
        refinementMap: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<CategoryList>
    ): VimeoRequest

    fun fetchChannel(
        uri: String,
        fieldFilter: String?,
        refinementMap: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<Channel>
    ): VimeoRequest

    fun fetchChannelList(
        uri: String,
        fieldFilter: String?,
        refinementMap: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<ChannelList>
    ): VimeoRequest

    fun fetchAppConfiguration(
        uri: String,
        fieldFilter: String?,
        refinementMap: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<AppConfiguration>
    ): VimeoRequest

    fun fetchAlbum(
        uri: String,
        fieldFilter: String?,
        refinementMap: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<Album>
    ): VimeoRequest

    fun fetchAlbumList(
        uri: String,
        fieldFilter: String?,
        refinementMap: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<AlbumList>
    ): VimeoRequest

    fun fetchTvodItem(
        uri: String,
        fieldFilter: String?,
        refinementMap: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<TvodItem>
    ): VimeoRequest

    fun fetchTvodItemList(
        uri: String,
        fieldFilter: String?,
        refinementMap: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<TvodItemList>
    ): VimeoRequest

    fun fetchComment(
        uri: String,
        fieldFilter: String?,
        refinementMap: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<Comment>
    ): VimeoRequest

    fun fetchCommentList(
        uri: String,
        fieldFilter: String?,
        refinementMap: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<CommentList>
    ): VimeoRequest

    fun fetchTermsOfService(
        cacheControl: CacheControl?,
        callback: VimeoCallback<Document>
    ): VimeoRequest

    fun fetchPrivacyPolicy(
        cacheControl: CacheControl?,
        callback: VimeoCallback<Document>
    ): VimeoRequest

    fun fetchPaymentAddendum(
        cacheControl: CacheControl?,
        callback: VimeoCallback<Document>
    ): VimeoRequest

    fun fetchConnectedApps(
        fieldFilter: String?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<ConnectedAppList>
    ): VimeoRequest

    fun fetchConnectedApp(
        type: ConnectedAppType,
        fieldFilter: String?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<ConnectedApp>
    ): VimeoRequest

    fun fetchPublishJob(
        uri: String,
        fieldFilter: String?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<PublishJob>
    ): VimeoRequest

    fun fetchDocument(
        uri: String,
        cacheControl: CacheControl?,
        callback: VimeoCallback<Document>
    ): VimeoRequest

    fun fetchProduct(
        uri: String,
        fieldFilter: String?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<Product>
    ): VimeoRequest

    fun fetchTextTrackList(
        uri: String,
        fieldFilter: String?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<TextTrackList>
    ): VimeoRequest

    fun postContent(
        uri: String,
        postBody: List<Any>,
        callback: VimeoCallback<Unit>
    ): VimeoRequest

    fun emptyResponsePost(
        uri: String,
        postBody: Map<String, String>,
        callback: VimeoCallback<Unit>
    ): VimeoRequest

    fun emptyResponsePatch(
        uri: String,
        queryParams: Map<String, String>,
        patchBody: Any,
        callback: VimeoCallback<Unit>
    ): VimeoRequest

    fun putContentWithUserResponse(
        uri: String,
        options: Map<String, String>,
        body: Any?,
        callback: VimeoCallback<User>
    ): VimeoRequest

    fun putContent(
        uri: String,
        options: Map<String, String>,
        body: Any?,
        callback: VimeoCallback<Unit>
    ): VimeoRequest

    fun putContent(
        uri: String,
        options: Map<String, String>,
        callback: VimeoCallback<Unit>
    ): VimeoRequest

    fun deleteContent(
        uri: String,
        options: Map<String, String>,
        callback: VimeoCallback<Unit>
    ): VimeoRequest

    companion object {

        /**
         * Create an instance of [VimeoApiClient] to make requests.
         *
         * @param configuration All the server configuration (client id and secret, custom interceptors, read timeouts,
         * base url etc...) that can be set for authentication and making requests.
         */
        @JvmStatic
        @JvmName("create")
        operator fun invoke(configuration: Configuration): VimeoApiClient {
            val retrofit = RetrofitSetupModule.retrofit(configuration)
            val vimeoService = retrofit.create(VimeoService::class.java)

            return VimeoApiClientImpl(
                vimeoService,
                configuration.accountStore,
                configuration,
                LocalVimeoCallAdapter(retrofit)
            )
        }

        private val delegate: MutableVimeoApiClientDelegate = MutableVimeoApiClientDelegate()

        /**
         * Initialize the singleton instance of the [VimeoApiClient] with a [Configuration]. If the client was already
         * initialized, this will reconfigure it. This function must be called before [instance] is used.
         *
         * @param configuration The configuration used by the client.
         */
        @JvmStatic
        fun initialize(configuration: Configuration) {
            delegate.actual = VimeoApiClient(configuration)
        }

        /**
         * Access the singleton instance of the [VimeoApiClient]. Always returns the same instance.
         */
        @JvmStatic
        fun instance(): VimeoApiClient = delegate
    }

}
