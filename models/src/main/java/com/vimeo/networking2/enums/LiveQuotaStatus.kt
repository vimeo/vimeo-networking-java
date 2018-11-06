package com.vimeo.networking2.enums

/**
 * Live Quota Status.
 */
enum class LiveQuotaStatus(override val value: String?) : StringValue {

    /**
     * The user has permission to live stream, and they haven't reached
     * their monthly limit on the number of live streams that they can create.
     */
    AVAILABLE("available"),

    /**
     * The user can't stream because their account is in Private mode and privacy
     * is not password or disabled.
     */
    PRIVATE_MODE("private_mode"),

    /**
     * The user has reached their monthly limit on the number of live streams that they can create.
     */
    STREAM_LIMIT("stream_limit"),

    /**
     * The user has reached their monthly or per-event limit on the amount of time that
     * they can live stream.
     */
    TIME_LIMIT("time_limit"),

    /**
     * Unknown live quota status.
     */
    UNKNOWN(null)
}
