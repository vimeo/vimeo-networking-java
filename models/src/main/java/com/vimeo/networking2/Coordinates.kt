package com.vimeo.networking2

/**
 * Coordinates related to a video note.
 */
data class Coordinates(

    /**
     * The relative X position of this note. Between 0.000 and 1.000. Final position can be
     * calculated by (relative_x * embed_width) + (embed_width - video_width).
     */
    val x: Int? = null,

    /**
     * The relative Y position of this note. Between 0.000 and 1.000. Final position can be
     * calculated by (relative_y * embed_height) + (embed_height - video_height).
     */
    val y: Int? = null

)
