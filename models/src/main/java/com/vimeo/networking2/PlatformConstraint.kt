package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Constraints put in place by a social media platform on uploading videos.
 */
@JsonClass(generateAdapter = true)
data class PlatformConstraint(
        /**
         * The max length of a video for the corresponding platform.
         */
        @Json(name = "duration")
        val duration: Int = 0,

        /**
         * The max file size of a video for the corresponding platform.
         */
        @Json(name = "size")
        val size: Long = 0
)