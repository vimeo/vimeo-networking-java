package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * An object representing the information on which social network the corresponding video was published to.
 */
@JsonClass(generateAdapter = true)
data class PublishJobDestinations(

        /**
         * true or false depending on whether the video was ever published to Facebook.
         */
        @Json(name = "facebook")
        val facebook: Boolean = false,

        /**
         * true or false depending on whether the video was ever published to YouTube.
         */
        @Json(name = "youtube")
        val youtube: Boolean = false,

        /**
         * true or false depending on whether the video was ever published to LinkedIn.
         */
        @Json(name = "linkedin")
        val linkedin: Boolean = false,

        /**
         * true or false depending on whether the video was ever published to Twitter.
         */
        @Json(name = "twitter")
        val twitter: Boolean = false
)
