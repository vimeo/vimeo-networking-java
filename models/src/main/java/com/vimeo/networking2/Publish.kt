package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.Date

/**
 * [TvodItem] publish information.
 *
 * @param enabled Whether the [TvodItem] has been published.
 * @param time The time in ISO 8601 format when this [TvodItem] was published.
 */
@JsonClass(generateAdapter = true)
data class Publish(

    @Json(name = "enabled")
    val enabled: Boolean? = null,

    @Json(name = "time")
    val time: Date? = null
)
