package com.vimeo.networking2

data class UserConnections(

    /**
     * Information about the albums created by this user.
     */
    val albums: Connection?,

    /**
     * Information about the appearances of this user in other videos.
     */
    val appearances: Connection?,

    /**
     * Information on the users that the current user has blocked. This data requires a
     * bearer token with the private scope.
     */
    val block: Connection?,

    /**
     * Information about this user's followed categories.
     */
    val categories: Connection?,

    /**
     * Information about this user's subscribed channels.
     */
    val channels: Connection?,

    /**
     * Information about this user's feed.
     */
    val feed: Connection?,

    /**
     * Information about this user's folders.
     */
    val folders: Connection?,
    /**
     * Information about the user's followers.
     */
    val followers: Connection?,

    /**
     * Information about the users that the current user is following.
     */
    val following: Connection?,

    /**
     * Information about the groups created by this user.
     */
    val groups: Connection?,

    /**
     * Information about the videos that this user has liked.
     */
    val likes: Connection?,

    /**
     * Information about the channels that this user moderates.
     */
    val moderatedChannels: Connection?,

    /**
     * Information about this user's notifications. This data requires a bearer
     * token with the private scope.
     */
    val notifications: NotificationConnection?,

    /**
     * Information about this user's portraits.
     */
    val pictures: Connection?,

    /**
     * Information about this user's portfolios.
     */
    val portfolios: Connection?,

    /**
     * A collection of recommended channels for the current user to follow. This data requires a
     * bearer token with the private scope.
     */
    val recommendedChannels: Connection?,

    /**
     * A Collection of recommended users for the current user to follow. This data requires a
     * bearer token with the private scope.
     */
    val recommendedUsers: Connection?,

    /**
     * Information about the videos that have been shared with this user.
     */
    val shared: Connection?,

    /**
     * Information about the user's team.
     */
    val teamMembers: Connection?,

    /**
     * Information about the videos uploaded by this user.
     */
    val videos: Connection?,

    /**
     * Information about this user's violations.
     */
    val violations: Connection?,

    /**
     * Information about the videos that this user wants to watch later.
     */
    val watchlater: Connection?

)
