package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * A model that holds the type of push subscriptions a user has.
 *
 * @param comment The "new comments on your video" setting.
 * @param credit The "you are added to the credits of a video".
 * @param like The "new likes on your videos" setting.
 * @param reply The "new reply to your comment" setting.
 * @param follow The "a user follows you" setting.
 * @param videoAvailable The "new upload transcode complete (new video is posted)" setting.
 * @param followedUserVideoAvailable The "someone you follow uploaded a new item" setting.
 */
@JsonClass(generateAdapter = true)
data class Subscriptions(

    @Json(name = "comment")
    val comment: Boolean = false,

    @Json(name = "credit")
    val credit: Boolean = false,

    @Json(name = "like")
    val like: Boolean = false,

    @Json(name = "reply")
    val reply: Boolean = false,

    @Json(name = "follow")
    val follow: Boolean = false,

    @Json(name = "video_available")
    val videoAvailable: Boolean = false,

    @Json(name = "followed_user_video_available")
    val followedUserVideoAvailable: Boolean = false

)
