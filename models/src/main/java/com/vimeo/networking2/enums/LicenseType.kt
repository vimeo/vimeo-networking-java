package com.vimeo.networking2.enums

import com.squareup.moshi.Json

/**
 * Different types of licenses.
 */
enum class LicenseType {

    @Json(name = "by")
    BY,

    @Json(name = "by-nc")
    BY_NC,

    @Json(name = "by-nc-nd")
    BY_NC_ND,

    @Json(name = "by-nc-sa")
    BY_NC_SA,

    @Json(name = "by-nd")
    BY_ND,

    @Json(name = "by-sa")
    BY_SA,

    @Json(name = "by-cc0")
    CC0,

    UNKNOWN
}
