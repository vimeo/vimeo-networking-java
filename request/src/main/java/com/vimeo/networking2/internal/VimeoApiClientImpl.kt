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
import com.vimeo.networking2.config.Configuration
import com.vimeo.networking2.enums.CommentPrivacyType
import com.vimeo.networking2.enums.ConnectedAppType
import com.vimeo.networking2.enums.EmbedPrivacyType
import com.vimeo.networking2.enums.ViewPrivacyType
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

/**
 * The internal implementation of the [VimeoApiClient]. Once configured, this client cannot be re-configured.
 *
 * @param vimeoService The service used to make requests to the Vimeo API.
 * @param authenticator The authenticator used to obtain tokens which can be used to make requests.
 * @param configuration The configuration used by this client instance.
 * @param basicAuthHeader The basic auth header using the client ID and secret, used if the account store does not
 * provide an authenticated account.
 * @param localVimeoCallAdapter The adapter used to notify consumers of local errors.
 */
@Suppress("UnsafeCallOnNullableType", "LargeClass")
internal class VimeoApiClientImpl(
    private val vimeoService: VimeoService,
    private val authenticator: Authenticator,
    private val configuration: Configuration,
    private val basicAuthHeader: String,
    private val localVimeoCallAdapter: LocalVimeoCallAdapter
) : VimeoApiClient {

    private val authHeader: String
        get() {
            val vimeoAccount = authenticator.currentAccount

            return vimeoAccount?.accessToken?.let { "Bearer $it" } ?: basicAuthHeader
        }

    override fun createAlbum(
        name: String,
        albumPrivacy: AlbumPrivacy,
        description: String?,
        parameters: Map<String, Any>?,
        callback: VimeoCallback<Album>
    ): VimeoRequest {
        val body = parameters.intoMutableMap()
        body[ApiConstants.Parameters.PARAMETER_ALBUM_NAME] = name
        body[ApiConstants.Parameters.PARAMETER_ALBUM_PRIVACY] = albumPrivacy.viewPrivacy!!
        if (description != null) {
            body[ApiConstants.Parameters.PARAMETER_ALBUM_DESCRIPTION] = description
        }
        if (albumPrivacy.password != null) {
            body[ApiConstants.Parameters.PARAMETER_ALBUM_PASSWORD] = albumPrivacy.password!!
        }
        return vimeoService.createAlbum(authHeader, body).enqueue(callback)
    }

    override fun editAlbum(
        album: Album,
        name: String,
        albumPrivacy: AlbumPrivacy,
        description: String?,
        parameters: Map<String, Any>?,
        callback: VimeoCallback<Album>
    ): VimeoRequest {
        val body = parameters.intoMutableMap()
        body[ApiConstants.Parameters.PARAMETER_ALBUM_NAME] = name
        body[ApiConstants.Parameters.PARAMETER_ALBUM_PRIVACY] = albumPrivacy.viewPrivacy!!
        if (description != null) {
            body[ApiConstants.Parameters.PARAMETER_ALBUM_DESCRIPTION] = description
        }
        if (albumPrivacy.password != null) {
            body[ApiConstants.Parameters.PARAMETER_ALBUM_PASSWORD] = albumPrivacy.password!!
        }
        return vimeoService.editAlbum(authHeader, album.uri!!, body).enqueue(callback)
    }

    override fun deleteAlbum(album: Album, callback: VimeoCallback<Unit>): VimeoRequest {
        return vimeoService.delete(authHeader, album.uri!!, emptyMap()).enqueue(callback)
    }

    override fun addToAlbum(album: Album, video: Video, callback: VimeoCallback<Unit>): VimeoRequest {
        return vimeoService.addToAlbum(authHeader, album.uri!!, video.uri!!).enqueue(callback)
    }

    override fun removeFromAlbum(album: Album, video: Video, callback: VimeoCallback<Unit>): VimeoRequest {
        return vimeoService.removeFromAlbum(authHeader, album.uri!!, video.uri!!).enqueue(callback)
    }

    override fun modifyVideosInAlbum(
        album: Album,
        modificationSpecs: ModifyVideosInAlbumSpecs,
        callback: VimeoCallback<VideoList>
    ): VimeoRequest {
        return vimeoService.modifyVideosInAlbum(authHeader, album.uri!!, modificationSpecs).enqueue(callback)
    }

    override fun modifyVideoInAlbums(
        video: Video,
        modificationSpecs: ModifyVideoInAlbumsSpecs,
        callback: VimeoCallback<AlbumList>
    ): VimeoRequest {
        return vimeoService.modifyVideoInAlbums(authHeader, video.uri!!, modificationSpecs).enqueue(callback)
    }

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
    ): VimeoRequest {
        val body = parameters.intoMutableMap()
        if (title != null) {
            body[ApiConstants.Parameters.PARAMETER_VIDEO_NAME] = title
        }
        if (description != null) {
            body[ApiConstants.Parameters.PARAMETER_VIDEO_DESCRIPTION] = description
        }
        if (password != null) {
            body[ApiConstants.Parameters.PARAMETER_VIDEO_PASSWORD] = password
        }
        if (commentPrivacyType != null) {
            body[ApiConstants.Parameters.PARAMETER_VIDEO_COMMENTS] = commentPrivacyType.value!!
        }
        if (allowDownload != null) {
            body[ApiConstants.Parameters.PARAMETER_VIDEO_DOWNLOAD] = allowDownload
        }
        if (allowAddToCollections != null) {
            body[ApiConstants.Parameters.PARAMETER_VIDEO_ADD] = allowAddToCollections
        }
        if (embedPrivacyType != null) {
            body[ApiConstants.Parameters.PARAMETER_VIDEO_EMBED] = embedPrivacyType.value!!
        }

        return vimeoService.editVideo(authHeader, uri, body).enqueue(callback)
    }

    override fun editUser(
        uri: String,
        name: String?,
        location: String?,
        bio: String?,
        callback: VimeoCallback<User>
    ): VimeoRequest {
        val body = mutableMapOf<String, Any>()
        if (name != null) {
            body[ApiConstants.Parameters.PARAMETER_USERS_NAME] = name
        }
        if (location != null) {
            body[ApiConstants.Parameters.PARAMETER_USERS_LOCATION] = location
        }
        if (bio != null) {
            body[ApiConstants.Parameters.PARAMETER_USERS_BIO] = bio
        }
        return vimeoService.editUser(authHeader, uri, body).enqueue(callback)
    }

    override fun editSubscriptions(
        subscriptionMap: Map<String, Boolean>,
        callback: VimeoCallback<NotificationSubscriptions>
    ): VimeoRequest {
        return vimeoService.editNotificationSubscriptions(authHeader, subscriptionMap).enqueue(callback)
    }

    override fun fetchConnectedApps(
        fieldFilter: String?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<ConnectedAppList>
    ): VimeoRequest {
        return vimeoService.getConnectedAppList(authHeader, fieldFilter.asRefinementMap(), cacheControl)
            .enqueue(callback)
    }

    override fun fetchConnectedApp(
        type: ConnectedAppType,
        fieldFilter: String?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<ConnectedApp>
    ): VimeoRequest {
        return vimeoService.getConnectedApp(authHeader, type.value!!, fieldFilter.asRefinementMap(), cacheControl)
            .enqueue(callback)
    }

    override fun createConnectedApp(
        type: ConnectedAppType,
        authorization: String,
        clientId: String,
        callback: VimeoCallback<ConnectedApp>
    ): VimeoRequest {
        return vimeoService.createConnectedApp(
            authHeader,
            type.value!!,
            mapOf(
                ApiConstants.Parameters.PARAMETER_AUTH_CODE to authorization,
                ApiConstants.Parameters.PARAMETER_APP_TYPE to type.value!!,
                ApiConstants.Parameters.PARAMETER_CLIENT_ID to clientId
            )
        ).enqueue(callback)
    }

    override fun deleteConnectedApp(type: ConnectedAppType, callback: VimeoCallback<Unit>): VimeoRequest {
        return vimeoService.deleteConnectedApp(authHeader, type.value!!).enqueue(callback)
    }

    override fun fetchPublishJob(
        uri: String,
        fieldFilter: String?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<PublishJob>
    ): VimeoRequest {
        return vimeoService.getPublishJob(authHeader, uri, fieldFilter.asRefinementMap(), cacheControl)
            .enqueue(callback)
    }

    override fun putPublishJob(
        publishUri: String,
        publishData: BatchPublishToSocialMedia,
        callback: VimeoCallback<PublishJob>
    ): VimeoRequest {
        return vimeoService.putPublishJob(authHeader, publishUri, publishData).enqueue(callback)
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
        return vimeoService.getDocument(authHeader, uri).enqueue(callback)
    }

    override fun fetchTextTrackList(
        uri: String,
        fieldFilter: String?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<TextTrackList>
    ): VimeoRequest {
        return vimeoService.getTextTrackList(authHeader, uri, fieldFilter.asRefinementMap(), cacheControl)
            .enqueue(callback)
    }

    @Suppress("ComplexMethod")
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
            ApiConstants.Parameters.FILTER_TYPE to searchFilterType.value!!
        )
        if (fieldFilter != null) {
            map[ApiConstants.Parameters.PARAMETER_GET_FILTER] = fieldFilter
        }
        if (searchSortType != null) {
            map[ApiConstants.Parameters.PARAMETER_GET_SORT] = searchSortType.value!!
        }
        if (searchSortDirectionType != null) {
            map[ApiConstants.Parameters.PARAMETER_GET_DIRECTION] = searchSortDirectionType.value!!
        }
        if (searchDateType != null) {
            map[ApiConstants.Parameters.FILTER_UPLOADED] = searchDateType.value!!
        }
        if (searchDurationType != null) {
            map[ApiConstants.Parameters.FILTER_DURATION] = searchDurationType.value!!
        }
        if (searchFacetTypes != null) {
            map[ApiConstants.Parameters.PARAMETER_GET_FACETS] =
                searchFacetTypes.joinToString(separator = ",", transform = { it.value!! })
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
        return vimeoService.createPictureCollection(authHeader, uri).enqueue(callback)
    }

    override fun activatePictureCollection(uri: String, callback: VimeoCallback<PictureCollection>): VimeoRequest {
        return vimeoService.editPictureCollection(
            authHeader,
            uri,
            mapOf(ApiConstants.Parameters.PARAMETER_ACTIVE to true)
        ).enqueue(callback)
    }

    override fun updateFollow(isFollowing: Boolean, uri: String, callback: VimeoCallback<Unit>): VimeoRequest {
        return if (isFollowing) {
            vimeoService.put(authHeader, uri, emptyMap()).enqueue(callback)
        } else {
            vimeoService.delete(authHeader, uri, emptyMap()).enqueue(callback)
        }
    }

    override fun updateFollow(
        isFollowing: Boolean,
        followable: Followable,
        callback: VimeoCallback<Unit>
    ): VimeoRequest {
        return updateFollow(isFollowing, followable.metadata?.interactions?.follow?.uri!!, callback)
    }

    override fun updateVideoLike(
        isLiked: Boolean,
        uri: String,
        password: String?,
        callback: VimeoCallback<Unit>
    ): VimeoRequest {
        val optionsMap = password.asPasswordParameter()
        return if (isLiked) {
            vimeoService.put(authHeader, uri, optionsMap).enqueue(callback)
        } else {
            vimeoService.delete(authHeader, uri, optionsMap).enqueue(callback)
        }
    }

    override fun updateVideoLike(
        isLiked: Boolean,
        video: Video,
        password: String?,
        callback: VimeoCallback<Unit>
    ): VimeoRequest {
        return updateVideoLike(isLiked, video.uri!!, password, callback)
    }

    override fun updateVideoWatchLater(
        isWatchLater: Boolean,
        uri: String,
        password: String?,
        callback: VimeoCallback<Unit>
    ): VimeoRequest {
        val optionsMap = password.asPasswordParameter()
        return if (isWatchLater) {
            vimeoService.put(authHeader, uri, optionsMap).enqueue(callback)
        } else {
            vimeoService.delete(authHeader, uri, optionsMap).enqueue(callback)
        }
    }

    override fun updateVideoWatchLater(
        isWatchLater: Boolean,
        video: Video,
        password: String?,
        callback: VimeoCallback<Unit>
    ): VimeoRequest {
        return updateVideoWatchLater(isWatchLater, video.uri!!, password, callback)
    }

    override fun createComment(
        uri: String,
        comment: String,
        password: String?,
        callback: VimeoCallback<Comment>
    ): VimeoRequest {
        val optionsMap = password.asPasswordParameter()
        return vimeoService.createComment(
            authHeader,
            uri,
            optionsMap,
            mapOf(ApiConstants.Parameters.PARAMETER_COMMENT_TEXT_BODY to comment)
        ).enqueue(callback)
    }

    override fun createComment(
        video: Video,
        comment: String,
        password: String?,
        callback: VimeoCallback<Comment>
    ): VimeoRequest {
        return createComment(video.metadata?.connections?.comments?.uri!!, comment, password, callback)
    }

    override fun fetchProducts(
        fieldFilter: String?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<ProductList>
    ): VimeoRequest {
        return vimeoService.getProducts(authHeader, fieldFilter.asRefinementMap(), cacheControl).enqueue(callback)
    }

    override fun fetchProduct(
        uri: String,
        fieldFilter: String?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<Product>
    ): VimeoRequest {
        return vimeoService.getProduct(authHeader, uri, fieldFilter.asRefinementMap(), cacheControl).enqueue(callback)
    }

    override fun fetchCurrentUser(
        fieldFilter: String?,
        refinementMap: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<User>
    ): VimeoRequest {
        return vimeoService.getUser(
            authHeader,
            ApiConstants.Endpoints.ENDPOINT_ME,
            refinementMap.include(fieldFilter),
            cacheControl
        ).enqueue(callback)
    }

    override fun postContent(uri: String, postBody: List<Any>, callback: VimeoCallback<Unit>): VimeoRequest {
        return vimeoService.post(authHeader, uri, postBody).enqueue(callback)
    }

    override fun emptyResponsePost(
        uri: String,
        postBody: Map<String, String>,
        callback: VimeoCallback<Unit>
    ): VimeoRequest {
        return vimeoService.emptyResponsePost(authHeader, uri, postBody).enqueue(callback)
    }

    override fun emptyResponsePatch(
        uri: String,
        queryParams: Map<String, String>,
        patchBody: Any,
        callback: VimeoCallback<Unit>
    ): VimeoRequest {
        return vimeoService.emptyResponsePatch(authHeader, uri, queryParams, patchBody).enqueue(callback)
    }

    override fun putContentWithUserResponse(
        uri: String,
        options: Map<String, String>,
        body: Any?,
        callback: VimeoCallback<User>
    ): VimeoRequest {
        return vimeoService.putContentWithUserResponse(authHeader, uri, options, body).enqueue(callback)
    }

    override fun putContent(
        uri: String,
        options: Map<String, String>,
        body: Any?,
        callback: VimeoCallback<Unit>
    ): VimeoRequest {
        return vimeoService.put(authHeader, uri, options, body).enqueue(callback)
    }

    override fun putContent(uri: String, options: Map<String, String>, callback: VimeoCallback<Unit>): VimeoRequest {
        return vimeoService.put(authHeader, uri, options).enqueue(callback)
    }

    override fun deleteContent(uri: String, options: Map<String, String>, callback: VimeoCallback<Unit>): VimeoRequest {
        return vimeoService.delete(authHeader, uri, options).enqueue(callback)
    }

    override fun fetchVideo(
        uri: String,
        fieldFilter: String?,
        refinementMap: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<Video>
    ): VimeoRequest {
        return vimeoService.getVideo(authHeader, uri, refinementMap.include(fieldFilter), cacheControl)
            .enqueue(callback)
    }

    override fun fetchVideoList(
        uri: String,
        fieldFilter: String?,
        refinementMap: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<VideoList>
    ): VimeoRequest {
        return vimeoService.getVideoList(authHeader, uri, refinementMap.include(fieldFilter), cacheControl)
            .enqueue(callback)
    }

    override fun fetchFeedList(
        uri: String,
        fieldFilter: String?,
        refinementMap: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<FeedList>
    ): VimeoRequest {
        return vimeoService.getFeedList(authHeader, uri, refinementMap.include(fieldFilter), cacheControl)
            .enqueue(callback)
    }

    override fun fetchProgrammedContentItemList(
        uri: String,
        fieldFilter: String?,
        refinementMap: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<ProgrammedContentItemList>
    ): VimeoRequest {
        return vimeoService.getProgramContentItemList(authHeader, uri, refinementMap.include(fieldFilter), cacheControl)
            .enqueue(callback)
    }

    override fun fetchRecommendationList(
        uri: String,
        fieldFilter: String?,
        refinementMap: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<RecommendationList>
    ): VimeoRequest {
        return vimeoService.getRecommendationList(authHeader, uri, refinementMap.include(fieldFilter), cacheControl)
            .enqueue(callback)
    }

    override fun fetchSearchResultList(
        uri: String,
        fieldFilter: String?,
        refinementMap: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<SearchResultList>
    ): VimeoRequest {
        return vimeoService.getSearchResultList(authHeader, uri, refinementMap.include(fieldFilter), cacheControl)
            .enqueue(callback)
    }

    override fun fetchSeasonList(
        uri: String,
        fieldFilter: String?,
        refinementMap: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<SeasonList>
    ): VimeoRequest {
        return vimeoService.getSeasonList(authHeader, uri, refinementMap.include(fieldFilter), cacheControl)
            .enqueue(callback)
    }

    override fun fetchNotificationList(
        uri: String,
        fieldFilter: String?,
        refinementMap: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<NotificationList>
    ): VimeoRequest {
        return vimeoService.getNotificationList(authHeader, uri, refinementMap.include(fieldFilter), cacheControl)
            .enqueue(callback)
    }

    override fun fetchUser(
        uri: String,
        fieldFilter: String?,
        refinementMap: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<User>
    ): VimeoRequest {
        return vimeoService.getUser(authHeader, uri, refinementMap.include(fieldFilter), cacheControl).enqueue(callback)
    }

    override fun fetchUserList(
        uri: String,
        fieldFilter: String?,
        refinementMap: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<UserList>
    ): VimeoRequest {
        return vimeoService.getUserList(authHeader, uri, refinementMap.include(fieldFilter), cacheControl)
            .enqueue(callback)
    }

    override fun fetchCategory(
        uri: String,
        fieldFilter: String?,
        refinementMap: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<Category>
    ): VimeoRequest {
        return vimeoService.getCategory(authHeader, uri, refinementMap.include(fieldFilter), cacheControl)
            .enqueue(callback)
    }

    override fun fetchCategoryList(
        uri: String,
        fieldFilter: String?,
        refinementMap: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<CategoryList>
    ): VimeoRequest {
        return vimeoService.getCategoryList(authHeader, uri, refinementMap.include(fieldFilter), cacheControl)
            .enqueue(callback)
    }

    override fun fetchChannel(
        uri: String,
        fieldFilter: String?,
        refinementMap: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<Channel>
    ): VimeoRequest {
        return vimeoService.getChannel(authHeader, uri, refinementMap.include(fieldFilter), cacheControl)
            .enqueue(callback)
    }

    override fun fetchChannelList(
        uri: String,
        fieldFilter: String?,
        refinementMap: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<ChannelList>
    ): VimeoRequest {
        return vimeoService.getChannelList(authHeader, uri, refinementMap.include(fieldFilter), cacheControl)
            .enqueue(callback)
    }

    override fun fetchAppConfiguration(
        uri: String,
        fieldFilter: String?,
        refinementMap: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<AppConfiguration>
    ): VimeoRequest {
        return vimeoService.getAppConfiguration(authHeader, uri, refinementMap.include(fieldFilter), cacheControl)
            .enqueue(callback)
    }

    override fun fetchAlbum(
        uri: String,
        fieldFilter: String?,
        refinementMap: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<Album>
    ): VimeoRequest {
        return vimeoService.getAlbum(authHeader, uri, refinementMap.include(fieldFilter), cacheControl)
            .enqueue(callback)
    }

    override fun fetchAlbumList(
        uri: String,
        fieldFilter: String?,
        refinementMap: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<AlbumList>
    ): VimeoRequest {
        return vimeoService.getAlbumList(authHeader, uri, refinementMap.include(fieldFilter), cacheControl)
            .enqueue(callback)
    }

    override fun fetchTvodItem(
        uri: String,
        fieldFilter: String?,
        refinementMap: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<TvodItem>
    ): VimeoRequest {
        return vimeoService.getTvodItem(authHeader, uri, refinementMap.include(fieldFilter), cacheControl)
            .enqueue(callback)
    }

    override fun fetchTvodItemList(
        uri: String,
        fieldFilter: String?,
        refinementMap: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<TvodItemList>
    ): VimeoRequest {
        return vimeoService.getTvodItemList(authHeader, uri, refinementMap.include(fieldFilter), cacheControl)
            .enqueue(callback)
    }

    override fun fetchComment(
        uri: String,
        fieldFilter: String?,
        refinementMap: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<Comment>
    ): VimeoRequest {
        return vimeoService.getComment(authHeader, uri, refinementMap.include(fieldFilter), cacheControl)
            .enqueue(callback)
    }

    override fun fetchCommentList(
        uri: String,
        fieldFilter: String?,
        refinementMap: Map<String, String>?,
        cacheControl: CacheControl?,
        callback: VimeoCallback<CommentList>
    ): VimeoRequest {
        return vimeoService.getCommentList(authHeader, uri, refinementMap.include(fieldFilter), cacheControl)
            .enqueue(callback)
    }

    private fun String?.asPasswordParameter(): Map<String, String> =
        this?.let { mapOf(ApiConstants.Parameters.PARAMETER_PASSWORD to it) } ?: emptyMap()

    private fun Map<String, String>?.include(fieldFilter: String?): Map<String, String> {
        return if (this == null && fieldFilter == null) {
            emptyMap()
        } else if (this == null && fieldFilter != null) {
            mapOf(ApiConstants.Parameters.PARAMETER_GET_FIELD_FILTER to fieldFilter)
        } else if (this != null && fieldFilter != null) {
            toMutableMap().apply { put(ApiConstants.Parameters.PARAMETER_GET_FIELD_FILTER, fieldFilter) }
        } else {
            this ?: emptyMap()
        }
    }

    private fun <T, V> Map<T, V>?.intoMutableMap(): MutableMap<T, V> {
        return this?.toMutableMap() ?: mutableMapOf()
    }

    private fun String?.asRefinementMap(): Map<String, String> =
        this?.let { mapOf(ApiConstants.Parameters.PARAMETER_GET_FIELD_FILTER to this) } ?: emptyMap()
}
