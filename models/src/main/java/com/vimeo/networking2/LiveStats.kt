package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Live stats data.
 *
 * @param plays The current total amount of plays this video has received.
 * @param totalViewTime The total amount of time spent watching this video by all viewers.
 * @param viewers Information about the number of people watching the stream.
 */
@JsonClass(generateAdapter = true)
data class LiveStats(

    @Json(name = "plays")
    val plays: Long? = null,

    @Json(name = "total_view_time")
    val totalViewTime: Long? = null,

    @Json(name = "viewers")
    val viewers: LiveStatsViewers? = null

)
