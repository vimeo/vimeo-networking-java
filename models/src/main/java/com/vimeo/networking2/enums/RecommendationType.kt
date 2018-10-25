package com.vimeo.networking2.enums

import com.squareup.moshi.Json

/**
 * Type of recommendation.
 */
enum class RecommendationType {

    @Json(name = "channel")
    CHANNEL,

    @Json(name = "user")
    USER,

    UNKNOWN
}
