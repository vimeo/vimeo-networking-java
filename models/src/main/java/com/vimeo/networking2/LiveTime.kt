package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

/**
 * Live time data.
 */
@JsonClass(generateAdapter = true)
data class LiveTime(

    /**
     * The amount of time per event that the user is allowed to live stream.
     */
    @Json(name = "event_maximum")
    val eventMaximum: Long? = null,

    /**
     * The amount of time this month, in seconds, that the user can live stream.
     */
    @Json(name = "monthly_maximum")
    val monthlyMaximum: Long? = null,

    /**
     * The amount of time remaining this month, in seconds, that the user can live stream.
     */
    @Json(name = "monthly_remaining")
    val monthlyRemaining: Long? = null

): Serializable {

    companion object {
        private const val serialVersionUID = -6308L
    }
}
