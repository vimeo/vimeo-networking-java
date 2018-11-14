package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.annotations.Internal

/**
 * All connections for a user.
 */
@JsonClass(generateAdapter = true)
data class UserConnections(

    /**
     * Information about the albums created by this user.
     */
    @Json(name = "albums")
    val albums: Connection? = null,

    /**
     * Information about the appearances of this user in other videos.
     */
    @Json(name = "appearances")
    val appearances: Connection? = null,

    /**
     * Information on the users that the current user has blocked. This data requires a
     * bearer token with the private scope.
     */
    @Json(name = "block")
    val block: Connection? = null,

    /**
     * Information about this user's followed categories.
     */
    @Json(name = "categories")
    val categories: Connection? = null,

    /**
     * Information about this user's subscribed channels.
     */
    @Json(name = "channels")
    val channels: Connection? = null,

    /**
     * Information about this user's feed.
     */
    @Json(name = "feed")
    val feed: Connection? = null,

    /**
     * Information about this user's folders.
     */
    @Json(name = "folders")
    val folders: Connection? = null,

    /**
     * Information about the user's followers.
     */
    @Json(name = "followers")
    val followers: Connection? = null,

    /**
     * Information about the users that the current user is following.
     */
    @Json(name = "following")
    val following: Connection? = null,

    /**
     * Information about the groups created by this user.
     */
    @Json(name = "groups")
    val groups: Connection? = null,

    /**
     * Information about the videos that this user has liked.
     */
    @Json(name = "likes")
    val likes: Connection? = null,

    /**
     * Information about the channels that this user moderates.
     */
    @Json(name = "moderated_channels")
    val moderatedChannels: Connection? = null,

    /**
     * Information about this user's notifications. This data requires a bearer
     * token with the private scope.
     */
    @Internal
    @Json(name = "notifications")
    val notifications: NotificationConnection? = null,

    /**
     * Information about this user's portraits.
     */
    @Json(name = "pictures")
    val pictures: Connection? = null,

    /**
     * Information about this user's portfolios.
     */
    @Json(name = "portfolios")
    val portfolios: Connection? = null,

    /**
     * A collection of recommended channels for the current user to follow. This data requires a
     * bearer token with the private scope.
     */
    @Json(name = "recommended_channels")
    val recommendedChannels: Connection? = null,

    /**
     * A Collection of recommended users for the current user to follow. This data requires a
     * bearer token with the private scope.
     */
    @Json(name = "recommended_users")
    val recommendedUsers: Connection? = null,

    /**
     * Information about the videos that have been shared with this user.
     */
    @Json(name = "shared")
    val shared: Connection? = null,

    /**
     * Information about the user's team.
     */
    @Json(name = "team_members")
    val teamMembers: Connection? = null,

    /**
     * Information about the videos uploaded by this user.
     */
    @Json(name = "videos")
    val videos: Connection? = null,

    /**
     * Information about the videos that this user wants to watch later.
     */
    @Json(name = "watchlater")
    val watchLater: Connection? = null

)
