package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.annotations.Internal

/**
 * Live heart beat configuration.
 */
@Internal
@JsonClass(generateAdapter = true)
data class LiveHeartbeatConfiguration(

    /**
     * Is live heartbeat logging enabled? If it is enabled, then mobile apps should send a
     * heartbeat log, play.{hls|dash}.live.heartbeat, so we can track the amount of concurrent
     * users viewing a stream.
     */
    @Internal
    @Json(name = "enabled")
    val enabled: Boolean? = null,

    /**
     * The interval, in seconds, at which a live heartbeat should be sent.
     */
    @Internal
    @Json(name = "interval")
    val interval: Int? = null

)
