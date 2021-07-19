package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Constraints put in place by a social media platform on uploading videos.
 *
 * @param duration The max length in seconds of a video for the corresponding platform.
 * @param size The max file size in gigabytes of a video for the corresponding platform.
 */
@JsonClass(generateAdapter = true)
data class PlatformConstraint(

    @Json(name = "duration")
    val duration: Int? = null,

    @Json(name = "size")
    val size: Long? = null
)
