package com.vimeo.networking2.enums

import com.squareup.moshi.Json

enum class UploadSpaceType {

    @Json(name = "lifetime")
    LIFETIME,

    @Json(name = "periodic")
    PERIODIC,

    UNKNOWN
}
