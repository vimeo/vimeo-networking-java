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
    val accountExpirationWarningTotal: Int? = null,

    /**
     * There are new comments on a video.
     */
    val comment: Int? = null,

    /**
     * The user has been added to the credits of a video.
     */
    val credit: Int? = null,

    /**
     * A user has followed the current user.
     */
    val follow: Int? = null,

    /**
     * Someone who the user follows has uploaded a new video.
     */
    val followedUserVideoAvailable: Int? = null,

    /**
     * There are new likes on the user's videos.
     */
    val like: Int? = null,

    /**
     * The user has been at-mentioned in a comment.
     */
    val mention: Int? = null,

    /**
     * A comment by the usert has received a new reply.
     */
    val reply: Int? = null,

    /**
     * Someone has shared a video with the user.
     */
    val share: Int? = null,

    /**
     * The user is approaching their weekly storage limit.
     */
    val storageWarning: Int? = null,

    /**
     * The transcode is complete for the user's uploaded video, and the video has now been posted.
     */
    val videoAvailable: Int? = null,

    /**
     * The user's preordered VOD is now available.
     */
    val vodPreorderAvailable: Int? = null,

    /**
     * The user has purchased VOD.
     */
    val vodPurchase: Int? = null,

    /**
     * The user's VOD rental is about to expire.
     */
    val vodRentalExpirationWarning: Int? = null

)
