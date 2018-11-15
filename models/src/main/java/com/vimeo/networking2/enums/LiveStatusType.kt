package com.vimeo.networking2.enums

/**
 * Live statuses.
 */
enum class LiveStatusType(override val value: String?) : StringValue {

    /**
     * There was a problem archiving the stream.
     */
    ARCHIVE_ERROR("archive_error"),

    /**
     * The stream has ended, and it is currently being archived.
     */
    ARCHIVING("archiving"),

    /**
     * The user intentionally ended the stream.
     */
    DONE("done"),

    /**
     * Vimeo is working on setting up the connection.
     */
    PENDING("pending"),

    /**
     * The RTMP URL is ready to receive video content.
     */
    READY("read"),

    /**
     * The stream is open and receiving content.
     */
    STREAMING("streaming"),

    /**
     * The stream has been terminated by Vimeo.
     */
    STREAMING_ERROR("streaming_error"),

    /**
     * The RTMP link is visible, but it can't yet receive the stream.
     */
    UNAVAILABLE("unavailable"),

    /**
     * Unknown live status.
     */
    UNKNOWN(null)
}
