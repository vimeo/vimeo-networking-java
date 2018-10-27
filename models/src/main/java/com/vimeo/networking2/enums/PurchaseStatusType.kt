package com.vimeo.networking2.enums

import com.squareup.moshi.Json

enum class PurchaseStatusType {

    @Json(name = "app_mismatch")
    APP_MISMATCH,

    @Json(name = "available")
    AVAILABLE,

    @Json(name = "purchased")
    PURCHASED,

    @Json(name = "unavailable")
    UNAVAILABLE,

    UNKNOWN
}
