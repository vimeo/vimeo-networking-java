package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Video logging data.
 *
 * @param play The URL to record a Play logging event.
 */
@JsonClass(generateAdapter = true)
data class VideoLog(

    @Json(name = "play")
    val play: String? = null

)
