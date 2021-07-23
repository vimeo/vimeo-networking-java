package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.annotations.Internal

/**
 * The total number of unacknowledged notifications of various types. This data requires a bearer token with the private
 * scope.
 *
 * @param accountExpirationWarningTotal The user's Plus or PRO account is about to expire.
 * @param comment There are new comments on a video.
 * @param credit The user has been added to the credits of a video.
 * @param follow A user has followed the current user.
 * @param followedUserVideoAvailable Someone who the user follows has uploaded a new video.
 * @param like There are new likes on the user's videos.
 * @param mention The user has been at-mentioned in a comment.
 * @param reply A comment by the user that has received a new reply.
 * @param share Someone has shared a video with the user.
 * @param storageWarning The user is approaching their weekly storage limit.
 * @param videoAvailable The transcode is complete for the user's uploaded video, and the video has now been posted.
 * @param vodPreorderAvailable The user's preordered VOD is now available.
 * @param vodPurchase The user has purchased VOD.
 * @param vodRentalExpirationWarning The user's VOD rental is about to expire.
 */
@Internal
@JsonClass(generateAdapter = true)
data class NotificationTypeCount(

    @Internal
    @Json(name = "account_expiration_warning")
    val accountExpirationWarningTotal: Int? = null,

    @Internal
    @Json(name = "comment")
    val comment: Int? = null,

    @Internal
    @Json(name = "credit")
    val credit: Int? = null,

    @Internal
    @Json(name = "follow")
    val follow: Int? = null,

    @Internal
    @Json(name = "followed_user_video_available")
    val followedUserVideoAvailable: Int? = null,

    @Internal
    @Json(name = "like")
    val like: Int? = null,

    @Internal
    @Json(name = "mention")
    val mention: Int? = null,

    @Internal
    @Json(name = "reply")
    val reply: Int? = null,

    @Internal
    @Json(name = "share")
    val share: Int? = null,

    @Internal
    @Json(name = "storage_warning")
    val storageWarning: Int? = null,

    @Internal
    @Json(name = "video_available")
    val videoAvailable: Int? = null,

    @Internal
    @Json(name = "vod_preorder_available")
    val vodPreorderAvailable: Int? = null,

    @Internal
    @Json(name = "vod_purchase")
    val vodPurchase: Int? = null,

    @Internal
    @Json(name = "vod_rental_expiration_warning")
    val vodRentalExpirationWarning: Int? = null

)
