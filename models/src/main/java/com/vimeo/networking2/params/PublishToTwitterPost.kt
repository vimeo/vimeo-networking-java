package com.vimeo.networking2.params

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Represents the required data for a Twitter post.
 *
 * @param tweet The contents of the tweet as it will appear on Twitter.
 */
@JsonClass(generateAdapter = true)
data class PublishToTwitterPost(

    @Json(name = "tweet")
    val tweet: String
)
