package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.annotations.Internal

/**
 * This data requires a bearer token with the private scope.
 */
@Internal
@JsonClass(generateAdapter = true)
data class NotificationTypeCount(

    /**
     * The user's Plus or PRO account is about to expire.
     */
    @Internal
    @Json(name = "account_expiration_warning")
    val accountExpirationWarningTotal: Int? = null,

    /**
     * There are new comments on a video.
     */
    @Internal
    @Json(name = "comment")
    val comment: Int? = null,

    /**
     * The user has been added to the credits of a video.
     */
    @Internal
    @Json(name = "credit")
    val credit: Int? = null,

    /**
     * A user has followed the current user.
     */
    @Internal
    @Json(name = "follow")
    val follow: Int? = null,

    /**
     * Someone who the user follows has uploaded a new video.
     */
    @Internal
    @Json(name = "followed_user_video_available")
    val followedUserVideoAvailable: Int? = null,

    /**
     * There are new likes on the user's videos.
     */
    @Internal
    @Json(name = "like")
    val like: Int? = null,

    /**
     * The user has been at-mentioned in a comment.
     */
    @Internal
    @Json(name = "mention")
    val mention: Int? = null,

    /**
     * A comment by the usert has received a new reply.
     */
    @Internal
    @Json(name = "reply")
    val reply: Int? = null,

    /**
     * Someone has shared a video with the user.
     */
    @Internal
    @Json(name = "share")
    val share: Int? = null,

    /**
     * The user is approaching their weekly storage limit.
     */
    @Internal
    @Json(name = "storage_warning")
    val storageWarning: Int? = null,

    /**
     * The transcode is complete for the user's uploaded video, and the video has now been posted.
     */
    @Internal
    @Json(name = "video_available")
    val videoAvailable: Int? = null,

    /**
     * The user's preordered VOD is now available.
     */
    @Internal
    @Json(name = "vod_preorder_available")
    val vodPreorderAvailable: Int? = null,

    /**
     * The user has purchased VOD.
     */
    @Internal
    @Json(name = "vod_purchase")
    val vodPurchase: Int? = null,

    /**
     * The user's VOD rental is about to expire.
     */
    @Internal
    @Json(name = "vod_rental_expiration_warning")
    val vodRentalExpirationWarning: Int? = null

)
