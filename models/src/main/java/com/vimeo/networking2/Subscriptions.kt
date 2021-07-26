package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * A model that holds the type of push subscriptions a user has.
 *
 * @param comment Whether or not the user has a subscription for the "new comments on your video" setting.
 * @param credit Whether or not the user has a subscription for the "you are added to the credits of a video" setting.
 * @param like Whether or not the user has a subscription for the "new likes on your videos" setting.
 * @param reply Whether or not the user has a subscription for the "new reply to your comment" setting.
 * @param follow Whether or not the user has a subscription for the "a user follows you" setting.
 * @param videoAvailable Whether or not the user has a subscription for the "new upload transcode complete (new video is
 * posted)" setting.
 * @param followedUserVideoAvailable Whether or not the user has a subscription for the "someone you follow uploaded a
 * new item" setting.
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
