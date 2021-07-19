package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Live stats viewers data.
 *
 * @param current The current amount of people watching this video.
 * @param peak The peak amount of people watching this video at any time in the provided date range.
 */
@JsonClass(generateAdapter = true)
data class LiveStatsViewers(

    @Json(name = "current")
    val current: Long? = null,

    @Json(name = "peak")
    val peak: Long? = null
)
