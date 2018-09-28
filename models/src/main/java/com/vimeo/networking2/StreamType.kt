package com.vimeo.networking2

/**
 * The user's streaming access to this On Demand video.
 */
enum class StreamType {

    /**
     * The video is available for streaming.
     */
    AVAILABLE,

    /**
     * The user has purchased the video.
     */
    PURCHASED,

    /**
     * The user isn't permitted to stream the video.
     */
    RESTRICTED,

    /**
     * The video isn't available for streaming
     */
    UNAVAILABLE
}
