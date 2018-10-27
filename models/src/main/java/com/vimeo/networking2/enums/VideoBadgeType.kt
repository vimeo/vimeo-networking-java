package com.vimeo.networking2.enums

import com.squareup.moshi.Json

/**
 * Different types of badges for videos.
 */
enum class VideoBadgeType {

    @Json(name = "cameo")
    CAMEO,

    @Json(name = "staffpick")
    STAFFPICK,

    @Json(name = "staffpick-best-of-the-month")
    STAFFPICK_BEST_OF_THE_MONTH,

    @Json(name = "staffpick-best-of-the-year")
    STAFFPICK_BEST_OF_THE_YEAR,

    @Json(name = "staffpick-premiere")
    STAFFPICK_PREMIERE,

    @Json(name = "weekendchallenge")
    WEEKENDCHALLENGE,

    UNKNOWN
}
