package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.annotations.Internal

/**
 * Live heart beat configuration.
 *
 * @param enabled Whether live heartbeat logging is enabled or not. If live heartbeat logging is enabled, then mobile
 * apps should send a heartbeat using [HlsVideoFile.live] or [DashVideoFile.live] so we can track the amount of
 * concurrent users viewing a stream.
 * @param interval The interval, in seconds, at which a live heartbeat should be sent.
 */
@Internal
@JsonClass(generateAdapter = true)
data class LiveHeartbeatConfiguration(

    @Internal
    @Json(name = "enabled")
    val enabled: Boolean? = null,

    @Internal
    @Json(name = "interval")
    val interval: Int? = null

)
