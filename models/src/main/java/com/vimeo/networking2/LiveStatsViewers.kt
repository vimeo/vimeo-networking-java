package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Live stats viewers data.
 */
@JsonClass(generateAdapter = true)
data class LiveStatsViewers(

    /**
     * The current amount of people watching this video.
     */
    @Json(name = "current")
    val current: Long? = null,

    /**
     * The peak amount of people watching this video at any time in the provided date range.
     */
    @Json(name = "peak")
    val peak: Long? = null
)
