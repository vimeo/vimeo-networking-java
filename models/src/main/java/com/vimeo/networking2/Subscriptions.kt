package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * A model that holds the type of push subscriptions a user has.
 */
@JsonClass(generateAdapter = true)
data class Subscriptions(

    /**
     * The "new comments on your video" setting.
     */
    @Json(name = "comment")
    val comment: Boolean = false,

    /**
     * The "you are added to the credits of a video".
     */
    @Json(name = "credit")
    val  credit: Boolean = false,

    /**
     * The "new likes on your videos" setting.
     */
    @Json(name = "like")
    val like: Boolean = false,

    /**
     * The "new reply to your comment" setting.
     */
    @Json(name = "reply")
    val reply: Boolean = false,

    /**
     * The "a user follows you" setting.
     */
    @Json(name = "follow")
    val follow: Boolean = false,

    /**
     * The "new upload transcode complete (new video is posted)" setting.
     */
    @Json(name = "video_available")
    val videoAvailable: Boolean = false,

    /**
     * The "someone you follow uploaded a new item" setting.
     */
    @Json(name = "followed_user_video_available")
    val followedUserVideoAvailable: Boolean = false

)
