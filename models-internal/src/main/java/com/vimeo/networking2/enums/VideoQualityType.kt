package com.vimeo.networking2.enums

/**
 * Video qualities.
 */
enum class VideoQualityType(override val value: String?) : StringValue {

    HD("hd"),

    HLS("hls"),

    MOBILE("mobile"),

    SD("sd"),

    UNKNOWN(null)
}
