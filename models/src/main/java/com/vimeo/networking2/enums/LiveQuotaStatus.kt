package com.vimeo.networking2.enums

import com.squareup.moshi.Json

/**
 * Live Quota Status.
 */
enum class LiveQuotaStatus {

    /**
     * The user has permission to live stream, and they haven't reached
     * their monthly limit on the number of live streams that they can create.
     */
    @Json(name = "available")
    AVAILABLE,

    /**
     * The user can't stream because their account is in Private mode and privacy
     * is not password or disabled.
     */
    @Json(name = "private_mode")
    PRIVATE_MODE,

    /**
     * The user has reached their monthly limit on the number of live streams that they can create.
     */
    @Json(name = "stream_limit")
    STREAM_LIMIT,

    /**
     * The user has reached their monthly or per-event limit on the amount of time that
     * they can live stream.
     */
    @Json(name = "time_limit")
    TIME_LIMIT,

    /**
     * Unknown live quota status.
     */
    UNKNOWN
}
