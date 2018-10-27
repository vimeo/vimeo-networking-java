package com.vimeo.networking2.enums

import com.squareup.moshi.Json

enum class TranscodeType {

    @Json(name = "complete")
    COMPLETE,

    @Json(name = "error")
    ERROR,

    @Json(name = "in_progress")
    IN_PROGRESS,

    UNKNOWN
}
