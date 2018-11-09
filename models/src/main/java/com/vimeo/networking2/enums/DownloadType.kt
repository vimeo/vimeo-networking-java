package com.vimeo.networking2.enums

import com.squareup.moshi.Json
import com.vimeo.networking2.annotations.Internal

/**
 * The user's download access to this On Demand video.
 */
@Internal
enum class DownloadType {

    /**
     * The video is available for download.
     */
    @Json(name = "available")
    AVAILABLE,

    /**
     * The user has purchased the video.
     */
    @Json(name = "purchased")
    PURCHASED,

    /**
     * The user isn't permitted to download the video.
     */
    @Json(name = "restricted")
    RESTRICTED,

    /**
     * The video isn't available for download.
     */
    @Json(name = "unavailable")
    UNAVAILABLE,

    /**
     * Unknown download type.
     */
    UNKNOWN

}
