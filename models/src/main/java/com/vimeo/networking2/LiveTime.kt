package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Live time data.
 *
 * @param eventMaximum The amount of time per event, in seconds, that the user is allowed to live stream.
 * @param monthlyMaximum The amount of time this month, in seconds, that the user can live stream.
 * @param monthlyRemaining The amount of time remaining this month, in seconds, that the user can live stream.
 */
@JsonClass(generateAdapter = true)
data class LiveTime(

    @Json(name = "event_maximum")
    val eventMaximum: Long? = null,

    @Json(name = "monthly_maximum")
    val monthlyMaximum: Long? = null,

    @Json(name = "monthly_remaining")
    val monthlyRemaining: Long? = null

)
