package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Coordinates related to a video note.
 */
@JsonClass(generateAdapter = true)
data class Coordinates(

    /**
     * The relative X position of this note. Between 0.000 and 1.000. Final position can be
     * calculated by (relative_x * embed_width) + (embed_width - video_width).
     */
    @Json(name ="x")
    val x: Int? = null,

    /**
     * The relative Y position of this note. Between 0.000 and 1.000. Final position can be
     * calculated by (relative_y * embed_height) + (embed_height - video_height).
     */
    @Json(name ="y")
    val y: Int? = null

)
