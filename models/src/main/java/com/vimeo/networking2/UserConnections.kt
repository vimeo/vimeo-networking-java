package com.vimeo.networking2

/**
 * All connections for a user.
 */
data class UserConnections(

    /**
     * Information about the albums created by this user.
     */
    val albums: Connection? = null,

    /**
     * Information about the appearances of this user in other videos.
     */
    val appearances: Connection? = null,

    /**
     * Information on the users that the current user has blocked. This data requires a
     * bearer token with the private scope.
     */
    val block: Connection? = null,

    /**
     * Information about this user's followed categories.
     */
    val categories: Connection? = null,

    /**
     * Information about this user's subscribed channels.
     */
    val channels: Connection? = null,

    /**
     * Information about this user's feed.
     */
    val feed: Connection? = null,

    /**
     * Information about this user's folders.
     */
    val folders: Connection? = null,

    /**
     * Information about the user's followers.
     */
    val followers: Connection? = null,

    /**
     * Information about the users that the current user is following.
     */
    val following: Connection? = null,

    /**
     * Information about the groups created by this user.
     */
    val groups: Connection? = null,

    /**
     * Information about the videos that this user has liked.
     */
    val likes: Connection? = null,

    /**
     * Information about the channels that this user moderates.
     */
    val moderatedChannels: Connection? = null,

    /**
     * Information about this user's notifications. This data requires a bearer
     * token with the private scope.
     */
    val notifications: NotificationConnection? = null,

    /**
     * Information about this user's portraits.
     */
    val pictures: Connection? = null,

    /**
     * Information about this user's portfolios.
     */
    val portfolios: Connection? = null,

    /**
     * A collection of recommended channels for the current user to follow. This data requires a
     * bearer token with the private scope.
     */
    val recommendedChannels: Connection? = null,

    /**
     * A Collection of recommended users for the current user to follow. This data requires a
     * bearer token with the private scope.
     */
    val recommendedUsers: Connection? = null,

    /**
     * Information about the videos that have been shared with this user.
     */
    val shared: Connection? = null,

    /**
     * Information about the user's team.
     */
    val teamMembers: Connection? = null,

    /**
     * Information about the videos uploaded by this user.
     */
    val videos: Connection? = null,

    /**
     * Information about this user's violations.
     */
    val violations: Connection? = null,

    /**
     * Information about the videos that this user wants to watch later.
     */
    val watchLater: Connection? = null

)
