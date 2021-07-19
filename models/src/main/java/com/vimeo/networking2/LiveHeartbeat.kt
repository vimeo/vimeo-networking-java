package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Holds information about the live heartbeat endpoint.
 *
 * @param heartbeat The endpoint that can be called to trigger a heartbeat for a streaming video.
 */
@JsonClass(generateAdapter = true)
data class LiveHeartbeat(

    @Json(name = "heartbeat")
    val heartbeat: String? = null
)
