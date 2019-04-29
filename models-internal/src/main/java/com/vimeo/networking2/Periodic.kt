package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.*

/**
 * Periodic upload quota information.
 */
@JsonClass(generateAdapter = true)
data class Periodic(

    /**
     * The number of bytes remaining in your upload quota for the current period.
     */
    @Json(name = "free")
    val free: Long? = null,

    /**
     * The total number of bytes that you can upload per period.
     */
    @Json(name = "max")
    val max: Long? = null,

    /**
     * The time in ISO 8601 format when your upload quota resets.
     */
    @Json(name = "reset_date")
    val resetDate: Date? = null,

    /**
     * The number of bytes that you've already uploaded against your quota in the current period.
     */
    @Json(name = "used")
    val used: Long? = null

)
