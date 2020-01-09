package com.vimeo.networking2.params

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Represents the required data for a Twitter post.
 */
data class PublishToTwitterPost(

        /**
         * The contents of the tweet as it will appear on Twitter.
         */
        @SerializedName(value = "tweet")
        val tweet: String

)
