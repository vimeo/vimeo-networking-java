package com.vimeo.networking2.enums

/**
 * Live Quota Status.
 */
enum class LiveQuotaStatus {

    /**
     * The user has permission to live stream, and they haven't reached
     * their monthly limit on the number of live streams that they can create.
     */
    AVAILABLE,

    /**
     * The user can't stream because their account is in Private mode and privacy
     * is not password or disabled.
     */
    PRIVATE_MODE,

    /**
     * The user has reached their monthly limit on the number of live streams that they can create.
     */
    STREAM_LIMIT,

    /**
     * The user has reached their monthly or per-event limit on the amount of time that
     * they can live stream.
     */
    TIME_LIMIT,

    /**
     * Unknown live quota status.
     */
    UNKNOWN
}
