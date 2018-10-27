package com.vimeo.networking2.enums

import com.squareup.moshi.Json

/**
 * Different types of content filters for videos.
 */
enum class ContentFilterType {

    @Json(name = "drugs")
    DRUGS,

    @Json(name = "language")
    LANGUAGE,

    @Json(name = "nudity")
    NUDITY,

    @Json(name = "safe")
    SAFE,

    @Json(name = "unrated")
    UNRATED,

    @Json(name = "violence")
    VIOLENCE,

    UNKNOWN
}
