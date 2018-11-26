package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Live stats data.
 */
@JsonClass(generateAdapter = true)
data class LiveStats(

    /**
     * The current total amount of plays this video has received.
     */
    @Json(name = "plays")
    val plays: Long? = null,

    /**
     * The total amount of time spent watching this video by all viewers.
     */
    @Json(name = "total_view_time")
    val totalViewTime: Long? = null,

    /**
     * Information about the number of people watching the stream.
     */
    @Json(name = "viewers")
    val viewers: LiveStatsViewers? = null

)
