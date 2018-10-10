package com.vimeo.networking2

import com.vimeo.networking2.enums.LiveStatus
import java.util.*

/**
 * Live video information.
 *
 * Requires [CapabilitiesType.CAPABILITY_LIVE_EVENT].
 */
data class Live(

    /**
     * The time in ISO 8601 format when the live stream began.
     */
    val activeTime: Date? = null,

    /**
     * The time in ISO 8601 format when the live stream was archived.
     */
    val archivedTime: Date? = null,

    /**
     * Information about the live clip's chat.
     */
    val chat: LiveChat? = null,

    /**
     * The time in ISO 8601 format when the live stream ended.
     */
    val endedTime: Date? = null,

    /**
     * The streaming key string, which is used in conjunction with the RTMP link.
     */
    val key: String? = null,

    /**
     * The upstream RTMP link. Send your live content to this link.
     */
    val link: String? = null,

    /**
     * The time in ISO 8601 format when the live stream was scheduled to start.
     */
    val scheduledStartTime: Date? = null,

    /**
     * The number of seconds before the termination of the live stream.
     */
    val secondsRemaining: Date? = null,

    /**
     * The status of the RTMP link.
     */
    val status: LiveStatus? = null,

    /**
     * If live.status is streaming_error, this is the reason for that error.
     */
    val streamingError: StreamingError? = null


)
