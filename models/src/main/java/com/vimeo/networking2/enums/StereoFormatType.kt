package com.vimeo.networking2.enums

import com.squareup.moshi.Json

enum class StereoFormatType {

    /**
     * The stereo format is left-right.
     */
    @Json(name = "left-right")
    LEFT_RIGHT,

    /**
     * The audio is monaural. top-bottom - The stereo
     */
    @Json(name = "mono")
    MONO,

    /**
     * The stereo format is top-bottom.
     */
    @Json(name = "top-bottom")
    TOP_BOTTOM,

    /**
     * Unknown stereo format type.
     */
    UNKNOWN
}
