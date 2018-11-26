package com.vimeo.networking2.enums

import com.vimeo.networking2.annotations.Internal

/**
 * The user's download access to this On Demand video.
 */
@Internal
enum class DownloadType(override val value: String?) : StringValue {

    /**
     * The video is available for download.
     */
    AVAILABLE("available"),

    /**
     * The user has purchased the video.
     */
    PURCHASED("purchased"),

    /**
     * The user isn't permitted to download the video.
     */
    RESTRICTED("restricted"),

    /**
     * The video isn't available for download.
     */
    UNAVAILABLE("unavailable"),

    /**
     * Unknown download type.
     */
    UNKNOWN(null)

}
