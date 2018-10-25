package com.vimeo.networking2.enums

import com.squareup.moshi.Json

/**
 * Live statuses.
 */
enum class LiveStatus {

    /**
     * There was a problem archiving the stream.
     */
    @Json(name = "archive_error")
    ARCHIVE_ERROR,

    /**
     * The stream has ended, and it is currently being archived.
     */
    @Json(name = "archiving")
    ARCHIVING,

    /**
     * The user intentionally ended the stream.
     */
    @Json(name = "done")
    DONE,

    /**
     * Vimeo is working on setting up the connection.
     */
    @Json(name = "pending")
    PENDING,

    /**
     * The RTMP URL is ready to receive video content.
     */
    @Json(name = "read")
    READY,

    /**
     * The stream is open and receiving content.
     */
    @Json(name = "streaming")
    STREAMING,

    /**
     * The stream has been terminated by Vimeo.
     */
    @Json(name = "streaming_error")
    STREAMING_ERROR,

    /**
     * The RTMP link is visible, but it can't yet receive the stream.
     */
    @Json(name = "unavailable")
    UNAVAILABLE,

    /**
     * Unknown live status.
     */
    UNKNOWN
}
