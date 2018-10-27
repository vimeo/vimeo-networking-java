package com.vimeo.networking2.enums

import com.squareup.moshi.Json

enum class VideoPlayStatus {

    @Json(name = "playable")
    PLAYABLE,

    @Json(name = "purchase_required")
    PURCHASE_REQUIRED,

    @Json(name = "restricted")
    RESTRICTED,

    @Json(name = "unavailable")
    UNAVAILABLE,

    UNKNOWN
}
