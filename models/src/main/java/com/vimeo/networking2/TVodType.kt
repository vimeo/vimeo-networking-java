package com.vimeo.networking2

import com.squareup.moshi.Json

/**
 * Type of [TVodItem].
 */
enum class TVodType {

    @Json(name = "film")
    FILM,

    @Json(name = "series")
    SERIES,

    UNKNOWN

}
