package com.vimeo.networking2

/**
 * This data requires a bearer token with the private scope.
 *
 * Requires [CapabilitiesType.CAPABILITY_APP_NOTIFICATIONS].
 */
data class NotificationTypeCount(

    /**
     * The user's Plus or PRO account is about to expire.
     */
    val accountExpirationWarningTotal: Int = 0,

    /**
     * There are new comments on a video.
     */
    val comment: Int = 0,

    /**
     * The user has been added to the credits of a video.
     */
    val credit: Int = 0,

    /**
     * A user has followed the current user.
     */
    val follow: Int = 0,

    /**
     * Someone who the user follows has uploaded a new video.
     */
    val followedUserVideoAvailable: Int = 0,

    /**
     * There are new likes on the user's videos.
     */
    val like: Int = 0,

    /**
     * The user has been at-mentioned in a comment.
     */
    val mention: Int = 0,

    /**
     * A comment by the usert has received a new reply.
     */
    val reply: Int = 0,

    /**
     * Someone has shared a video with the user.
     */
    val share: Int = 0,

    /**
     * The user is approaching their weekly storage limit.
     */
    val storageWarning: Int = 0,

    /**
     * The transcode is complete for the user's uploaded video, and the video has now been posted.
     */
    val videoAvailable: Int = 0,

    /**
     * The user's preordered VOD is now available.
     */
    val vodPreorderAvailable: Int = 0,

    /**
     * The user has purchased VOD.
     */
    val vodPurchase: Int = 0,

    /**
     * The user's VOD rental is about to expire.
     */
    val vodRentalExpirationWarning: Int = 0

)
