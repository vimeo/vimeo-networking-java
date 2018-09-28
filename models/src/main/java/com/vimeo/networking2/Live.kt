package com.vimeo.networking2

import java.util.*

/**
 * Based on CAPABILITY_LIVE_EVENT.
 */
data class Live(

    /**
     * The time in ISO 8601 format when the live stream began.
     */
    val activeTime: Date?,

    /**
     * The time in ISO 8601 format when the live stream was archived.
     */
    val archivedTime: Date?,

    /**
     * Information about the live clip's chat.
     */
    val chat: LiveChat?,

    /**
     * The time in ISO 8601 format when the live stream ended.
     */
    val endedTime: Date?,

    /**
     * The streaming key string, which is used in conjunction with the RTMP link.
     */
    val key: String?,

    /**
     * The upstream RTMP link. Send your live content to this link.
     */
    val link: String?,

    /**
     * The time in ISO 8601 format when the live stream was scheduled to start.
     */
    val scheduledStartTime: Date?,

    /**
     * The number of seconds before the termination of the live stream.
     */
    val secondsRemaining: Date?,

    /**
     * The status of the RTMP link.
     */
    val status: LiveStatus?,

    /**
     * If live.status is streaming_error, this is the reason for that error.
     */
    val streamingError: StreamingError?


)
