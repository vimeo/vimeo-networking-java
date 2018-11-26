package com.vimeo.networking2.enums

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
