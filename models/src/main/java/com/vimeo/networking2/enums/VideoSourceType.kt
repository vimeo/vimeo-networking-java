package com.vimeo.networking2.enums

/**
 * The type of the video file that a source represents.
 */
enum class VideoSourceType(override val value: String?) : StringValue {

    SOURCE("source"),

    VIDEO_MP4("video/mp4"),

    VIDEO_WEBM("video/webm"),

    VP6_X_VIDEO("vp6/x-video"),

    UNKNOWN(null)
}
