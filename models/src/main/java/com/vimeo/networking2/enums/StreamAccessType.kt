package com.vimeo.networking2.enums

import com.vimeo.networking2.annotations.Internal

/**
 * The user's streaming access to this On Demand video.
 */
@Internal
enum class StreamAccessType(override val value: String?) : StringValue {

    /**
     * The video is available for streaming.
     */
    AVAILABLE("available"),

    /**
     * The user has purchased the video.
     */
    PURCHASED("purchased"),

    /**
     * The user isn't permitted to stream the video.
     */
    RESTRICTED("restricted"),

    /**
     * The video isn't available for streaming
     */
    UNAVAILABLE("unavailable"),

    /**
     * Unknown stream type.
     */
    UNKNOWN(null)
}
