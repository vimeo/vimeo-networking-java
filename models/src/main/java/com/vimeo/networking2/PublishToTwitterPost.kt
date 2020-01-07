package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Represents the required data for a Twitter post.
 */
@JsonClass(generateAdapter = true)
data class PublishToTwitterPost(

        /**
         * The contents of the tweet as it will appear on Twitter.
         */
        @Json(name = "tweet")
        val tweet: String

)
