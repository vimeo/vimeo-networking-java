package com.vimeo.networking2.enums

import com.squareup.moshi.Json

/**
 * Video statuses.
 */
enum class VideoStatusType {

    @Json(name = "available")
    AVAILABLE,

    @Json(name = "quota_exceeded")
    QUOTA_EXCEEDED,

    @Json(name = "total_cap_exceeded")
    TOTAL_CAP_EXCEEDED,

    @Json(name = "transcode_starting")
    TRANSCODE_STARTING,

    @Json(name = "transcoding")
    TRANSCODING,

    @Json(name = "transcoding_error")
    TRANSCODING_ERROR,

    @Json(name = "unavailable")
    UNAVAILABLE,

    @Json(name = "uploading")
    UPLOADING,

    @Json(name = "uploading_error")
    UPLOADING_ERROR,

    UNKNOWN
}
