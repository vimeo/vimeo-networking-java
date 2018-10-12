package com.vimeo.networking2

import com.squareup.moshi.Json

/**
 * Video logging data.
 */
data class VideoLog(

    /**
     * The URL to record a Play logging event.
     */
    @Json(name = "play")
    val play: String? = null

)
