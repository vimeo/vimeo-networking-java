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
    val albums: BasicConnection? = null,

    /**
     * Information about the appearances of this user in other videos.
     */
    @Json(name = "appearances")
    val appearances: BasicConnection? = null,

    /**
     * Information on the users that the current user has blocked. This data requires a
     * bearer token with the private scope.
     */
    @Json(name = "block")
    val block: BasicConnection? = null,

    /**
     * Information about this user's followed categories.
     */
    @Json(name = "categories")
    val categories: BasicConnection? = null,

    /**
     * Information about this user's subscribed channels.
     */
    @Json(name = "channels")
    val channels: BasicConnection? = null,

    /**
     * Information about this user's connected apps.
     */
    @Json(name = "connected_apps")
    val connectedApps: BasicConnection? = null,

    /**
     * Information about this user's feed.
     */
    @Json(name = "feed")
    val feed: BasicConnection? = null,

    /**
     * Information about this user's folders.
     */
    @Json(name = "folders_root")
    val folders: BasicConnection? = null,

    /**
     * Information about the user's followers.
     */
    @Json(name = "followers")
    val followers: BasicConnection? = null,

    /**
     * Information about the users that the current user is following.
     */
    @Json(name = "following")
    val following: BasicConnection? = null,

    /**
     * Information about the groups created by this user.
     */
    @Json(name = "groups")
    val groups: BasicConnection? = null,

    /**
     * Information about the videos that this user has liked.
     */
    @Json(name = "likes")
    val likes: BasicConnection? = null,

    /**
     * Information about the channels that this user moderates.
     */
    @Json(name = "moderated_channels")
    val moderatedChannels: BasicConnection? = null,

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
    val pictures: BasicConnection? = null,

    /**
     * Information about this user's portfolios.
     */
    @Json(name = "portfolios")
    val portfolios: BasicConnection? = null,

    /**
     * A collection of recommended channels for the current user to follow. This data requires a
     * bearer token with the private scope.
     */
    @Json(name = "recommended_channels")
    val recommendedChannels: BasicConnection? = null,

    /**
     * A Collection of recommended users for the current user to follow. This data requires a
     * bearer token with the private scope.
     */
    @Json(name = "recommended_users")
    val recommendedUsers: BasicConnection? = null,

    /**
     * Information about the videos that have been shared with this user.
     */
    @Json(name = "shared")
    val shared: BasicConnection? = null,

    /**
     * Information about the user's team members.
     */
    @Json(name = "team_members")
    val teamMembers: BasicConnection? = null,

    /**
     * Information about teams the user belongs to.
     */
    @Json(name = "teams")
    val teams: BasicConnection? = null,

    /**
     * Information about the videos uploaded by this user.
     */
    @Json(name = "videos")
    val videos: BasicConnection? = null,

    /**
     * Information about the videos that this user wants to watch later.
     */
    @Json(name = "watchlater")
    val watchLater: BasicConnection? = null

)
