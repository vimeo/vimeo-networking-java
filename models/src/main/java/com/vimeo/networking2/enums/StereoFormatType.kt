package com.vimeo.networking2.enums

/**
 * The video layout format used for 360 videos.
 */
enum class StereoFormatType(override val value: String?) : StringValue {

    /**
     * The stereo format is left-right.
     */
    LEFT_RIGHT("left-right"),

    /**
     * The audio is monaural. top-bottom - The stereo
     */
    MONO("mono"),

    /**
     * The stereo format is top-bottom.
     */
    TOP_BOTTOM("top-bottom"),

    /**
     * Unknown stereo format type.
     */
    UNKNOWN(null)
}
