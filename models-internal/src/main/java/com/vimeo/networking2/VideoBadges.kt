package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Video badges data.
 */
@JsonClass(generateAdapter = true)
data class VideoBadges(

    /**
     * Whether the video has an HDR-compatible transcode.
     */
    @Json(name = "hdr")
    val hdr: Boolean? = null,

    /**
     * Live data.
     */
    @Json(name = "live")
    val live: Live? = null,

    /**
     * Whether the video is a Vimeo Weekend Challenge.
     */
    @Json(name = "weekendChallenge")
    val weekendChallenge: Boolean? = null
)
