package com.vimeo.networking2.enums

import com.squareup.moshi.Json

/**
 * Search type.
 */
enum class SearchType {

    @Json(name = "blog")
    BLOG,

    @Json(name = "channel")
    CHANNEL,

    @Json(name = "video")
    VIDEO,

    @Json(name = "group")
    GROUP,

    @Json(name = "ondemand")
    ONDEMAND,

    @Json(name = "people")
    PEOPLE,

    UNKNOWN
}
