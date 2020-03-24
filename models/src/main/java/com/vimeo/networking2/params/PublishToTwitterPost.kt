package com.vimeo.networking2.params

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

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

) : Serializable {

    companion object {
        private const val serialVersionUID = -977132L
    }
}
