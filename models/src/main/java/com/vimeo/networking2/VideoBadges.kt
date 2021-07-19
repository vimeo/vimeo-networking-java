package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Video badges data.
 *
 * @param hdr Whether the video has an HDR-compatible transcode.
 * @param live Live data.
 * @param weekendChallenge Whether the video is a Vimeo Weekend Challenge.
 */
@JsonClass(generateAdapter = true)
data class VideoBadges(

    @Json(name = "hdr")
    val hdr: Boolean? = null,

    @Json(name = "live")
    val live: Live? = null,

    @Json(name = "weekendChallenge")
    val weekendChallenge: Boolean? = null
)
