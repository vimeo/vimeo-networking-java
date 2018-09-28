package com.vimeo.networking2

enum class LiveStatus {

    /**
     * There was a problem archiving the stream
     */
    ARCHIVE_ERROR,

    /**
     * The stream has ended, and it is currently being archived.
     */
    ARCHIVING,

    /**
     * The user intentionally ended the stream.
     */
    DONE,

    /**
     * Vimeo is working on setting up the connection.
     */
    PENDING,

    /**
     * The RTMP URL is ready to receive video content.
     */
    READY,

    /**
     * The stream is open and receiving content.
     */
    STREAMING,

    /**
     * The stream has been terminated by Vimeo.
     */
    STREAMING_ERROR,

    /**
     * The RTMP link is visible, but it can't yet receive the stream.
     */
    UNAVAILABLE
}
