package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.annotations.Internal

/**
 * The total number of unacknowledged notifications of various types. This data requires a bearer token with the private
 * scope.
 *
 * @param accountExpirationWarningTotal The count of unacknowledged notifications that the user's Plus or PRO account is
 * about to expire.
 * @param comment The count of unacknowledged notifications for new comments on a user's video.
 * @param credit The count of unacknowledged notifications that the user has been added to the credits of a video.
 * @param follow The count of unacknowledged notifications that a user has followed the current user.
 * @param followedUserVideoAvailable The count of unacknowledged notifications that someone who the user follows has
 * uploaded a new video.
 * @param like The count of unacknowledged notifications that there are new likes on the user's videos.
 * @param mention The count of unacknowledged notifications that the user has been mentioned in a comment.
 * @param reply The count of unacknowledged notifications that a comment by the user that has received a new reply.
 * @param share The count of unacknowledged notifications that someone has shared a video with the user.
 * @param storageWarning The count of unacknowledged notifications that the user is approaching their weekly storage
 * limit.
 * @param videoAvailable The count of unacknowledged notifications that the transcode is complete for the user's
 * uploaded video, and the video has now been posted.
 * @param vodPreorderAvailable The count of unacknowledged notifications that the user's preordered VOD is now
 * available.
 * @param vodPurchase The count of unacknowledged notifications that the user has purchased VOD.
 * @param vodRentalExpirationWarning The count of unacknowledged notifications that the user's VOD rental is about to
 * expire.
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
