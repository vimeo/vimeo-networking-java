package com.vimeo.networking2.enums

import com.squareup.moshi.Json

/**
 * Video qualities.
 */
enum class VideoQualityType {

    @Json(name = "hd")
    HD,

    @Json(name = "hls")
    HLS,

    @Json(name = "mobile")
    MOBILE,

    @Json(name = "sd")
    SD,

    UNKNOWN
}
