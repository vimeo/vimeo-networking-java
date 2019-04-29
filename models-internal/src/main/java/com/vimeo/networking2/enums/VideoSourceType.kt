package com.vimeo.networking2.enums

enum class VideoSourceType(override val value: String?) : StringValue {

    SOURCE("source"),

    VIDEO_MP4("video/mp4"),

    VIDEO_WEBM("video/webm"),

    VP6_X_VIDEO("vp6/x-video"),

    UNKNOWN(null)
}
