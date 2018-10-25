package com.vimeo.networking2.enums

import com.squareup.moshi.Json

/**
 * Group privacy values.
 */
enum class GroupPrivacyValue {

    @Json(name = "all")
    ALL,

    @Json(name = "anybody")
    ANYBODY,

    @Json(name = "members")
    MEMBERS,

    UNKNOWN
}
