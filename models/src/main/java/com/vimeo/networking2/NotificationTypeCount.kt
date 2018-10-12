package com.vimeo.networking2
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * This data requires a bearer token with the private scope.
 *
 * Requires [CapabilitiesType.CAPABILITY_APP_NOTIFICATIONS].
 */
@JsonClass(generateAdapter = true)
data class NotificationTypeCount(

    /**
     * The user's Plus or PRO account is about to expire.
     */
    @Json(name = "account_expiration_warning")
    val accountExpirationWarningTotal: Int = 0,

    /**
     * There are new comments on a video.
     */
    @Json(name = "comment")
    val comment: Int = 0,

    /**
     * The user has been added to the credits of a video.
     */
    @Json(name = "credit")
    val credit: Int = 0,

    /**
     * A user has followed the current user.
     */
    @Json(name = "follow")
    val follow: Int = 0,

    /**
     * Someone who the user follows has uploaded a new video.
     */
    @Json(name = "followed_user_video_available")
    val followedUserVideoAvailable: Int = 0,

    /**
     * There are new likes on the user's videos.
     */
    @Json(name = "like")
    val like: Int = 0,

    /**
     * The user has been at-mentioned in a comment.
     */
    @Json(name = "mention")
    val mention: Int = 0,

    /**
     * A comment by the usert has received a new reply.
     */
    @Json(name = "reply")
    val reply: Int = 0,

    /**
     * Someone has shared a video with the user.
     */
    @Json(name = "share")
    val share: Int = 0,

    /**
     * The user is approaching their weekly storage limit.
     */
    @Json(name = "storage_warning")
    val storageWarning: Int = 0,

    /**
     * The transcode is complete for the user's uploaded video, and the video has now been posted.
     */
    @Json(name = "video_available")
    val videoAvailable: Int = 0,

    /**
     * The user's preordered VOD is now available.
     */
    @Json(name = "vod_preorder_available")
    val vodPreorderAvailable: Int = 0,

    /**
     * The user has purchased VOD.
     */
    @Json(name = "vod_purchase")
    val vodPurchase: Int = 0,

    /**
     * The user's VOD rental is about to expire.
     */
    @Json(name = "vod_rental_expiration_warning")
    val vodRentalExpirationWarning: Int = 0

)
