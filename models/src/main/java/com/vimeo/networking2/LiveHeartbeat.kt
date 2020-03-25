package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

/**
 * Holds information about the live heartbeat endpoint.
 */
@JsonClass(generateAdapter = true)
data class LiveHeartbeat(
    /**
     * The endpoint that can be called to trigger a heartbeat for a streaming video.
     */
    @Json(name = "heartbeat")
    val heartbeat: String? = null
) : Serializable {

    companion object {
        private const val serialVersionUID = -44L
    }
}
