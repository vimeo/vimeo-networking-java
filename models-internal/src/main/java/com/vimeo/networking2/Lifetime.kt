package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Lifetime data.
 */
@JsonClass(generateAdapter = true)
data class Lifetime(

    /**
     * The number of bytes remaining in your lifetime maximum.
     */
    @Json(name = "free")
    val free: Long? = null,

    /**
     * The total number of bytes that you can upload across the lifetime of your account.
     */
    @Json(name = "max")
    val max: Long? = null,

    /**
     * The number of bytes that you've already uploaded against your lifetime limit.
     */
    @Json(name = "used")
    val used: Long? = null
)
