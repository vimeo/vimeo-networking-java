package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * A collection of stats associated with this video.
 */
@JsonClass(generateAdapter = true)
data class VideoStats(

    /**
     * The current total number of times that the video has been played.
     */
    @Json(name = "plays")
    val plays: Int? = null

)
