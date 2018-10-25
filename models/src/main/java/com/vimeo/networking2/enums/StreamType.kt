package com.vimeo.networking2.enums

import com.squareup.moshi.Json

/**
 * The user's streaming access to this On Demand video.
 */
enum class StreamType {

    /**
     * The video is available for streaming.
     */
    @Json(name = "available")
    AVAILABLE,

    /**
     * The user has purchased the video.
     */
    @Json(name = "purchased")
    PURCHASED,

    /**
     * The user isn't permitted to stream the video.
     */
    @Json(name = "restricted")
    RESTRICTED,

    /**
     * The video isn't available for streaming
     */
    @Json(name = "unavailable")
    UNAVAILABLE,

    /**
     * Unknown stream type.
     */
    UNKNOWN
}
