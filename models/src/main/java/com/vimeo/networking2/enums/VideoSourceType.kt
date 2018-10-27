package com.vimeo.networking2.enums

import com.squareup.moshi.Json

enum class VideoSourceType {

    @Json(name = "source")
    SOURCE,

    @Json(name = "video/mp4")
    VIDEO_MP4,

    @Json(name = "video/webm")
    VIDEO_WEBM,

    @Json(name = "vp6/x-video")
    VP6_X_VIDEO,

    UNKNOWN
}
