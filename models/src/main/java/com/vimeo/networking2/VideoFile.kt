package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.annotations.Internal
import java.util.*

/**
 * Video file data.
 */
@Internal
@JsonClass(generateAdapter = true)
data class VideoFile(

    /**
     * The direct link to the video file.
     */
    @Internal
    @Json(name = "link")
    val link: String? = null,

    /**
     * The time in ISO 8601 format when the link to the video file expires.
     */
    @Internal
    @Json(name = "link_expiration_time")
    val linkExpirationTime: Date? = null,

    /**
     * The URL for logging events.
     */
    @Internal
    @Json(name = "log")
    val log: String? = null
)
