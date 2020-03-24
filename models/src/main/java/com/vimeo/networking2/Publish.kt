package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable
import java.util.*

/**
 * [TvodItem] publish information.
 */
@JsonClass(generateAdapter = true)
data class Publish(

    /**
     * Whether the [TvodItem] has been published
     */
    @Json(name = "enabled")
    val enabled: Boolean? = null,

    /**
     * The time in IS 8601 format when this [TvodItem] was published.
     */
    @Json(name = "time")
    val time: Date? = null
) : Serializable {

    companion object {
        private const val serialVersionUID = -109L
    }
}
