package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.enums.LiveStatus
import java.util.*

/**
 * Live video information.
 *
 * Requires [CapabilitiesType.CAPABILITY_LIVE_EVENT].
 */
@JsonClass(generateAdapter = true)
data class Live(

    /**
     * The time in ISO 8601 format when the live stream began.
     */
    @Json(name = "active_time")
    val activeTime: Date? = null,

    /**
     * The time in ISO 8601 format when the live stream was archived.
     */
    @Json(name = "archived_time")
    val archivedTime: Date? = null,

    /**
     * Information about the live clip's chat.
     */
    @Json(name = "chat")
    val chat: LiveChat? = null,

    /**
     * The time in ISO 8601 format when the live stream ended.
     */
    @Json(name = "ended_time")
    val endedTime: Date? = null,

    /**
     * The streaming key string, which is used in conjunction with the RTMP [link].
     */
    @Json(name = "key")
    val key: String? = null,

    /**
     * The upstream RTMP link. Send your live content to this link.
     */
    @Json(name = "link")
    val link: String? = null,

    /**
     * The time in ISO 8601 format when the live stream was scheduled to start.
     */
    @Json(name = "scheduled_start_time")
    val scheduledStartTime: Date? = null,

    /**
     * The number of seconds before the termination of the live stream.
     */
    @Json(name = "seconds_remaining")
    val secondsRemaining: Date? = null,

    /**
     * The status of the RTMP [link].
     */
    @Json(name = "status")
    val status: LiveStatus = LiveStatus.UNKNOWN,

    /**
     * If [status] is [LiveStatus.STREAMING_ERROR], this is the reason for that error.
     */
    @Json(name = "streaming_error")
    val streamingError: StreamingError? = null

)
