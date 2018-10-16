package com.vimeo.networking2.enums

/**
 * Video statuses.
 */
enum class VideoStatusType {
    AVAILABLE,
    QUOTA_EXCEEDED,
    TOTAL_CAP_EXCEEDED,
    TRANSCODE_STARTING,
    TRANSCODING,
    TRANSCODING_ERROR,
    UNAVAILABLE,
    UPLOADING,
    UPLOADING_ERROR,
    UNKNOWN
}
