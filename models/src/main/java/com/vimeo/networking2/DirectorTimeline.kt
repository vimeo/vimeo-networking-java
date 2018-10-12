package com.vimeo.networking2
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Director timeline data.
 */
@JsonClass(generateAdapter = true)
data class DirectorTimeline(

    /**
     * The director timeline pitch, from -90 (minimum) to 90 (maximum).
     */
    @Json(name = "pitch")
    val pitch: Int? = null,

    /**
     * The director timeline roll.
     */
    @Json(name = "roll")
    val roll: Int? = null,

    /**
     * The director timeline time code.
     */
    @Json(name = "time_code")
    val timeCode: Int? = null,

    /**
     * The director timeline yaw, from 0 (minimum) to 360 (maximum).
     */
    @Json(name = "yaw")
    val yaw: Int? = null

)
