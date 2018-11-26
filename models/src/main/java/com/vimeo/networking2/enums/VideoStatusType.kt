package com.vimeo.networking2.enums

/**
 * Video statuses.
 */
enum class VideoStatusType(override val value: String?) : StringValue {

    AVAILABLE("available"),

    QUOTA_EXCEEDED("quota_exceeded"),

    TOTAL_CAP_EXCEEDED("total_cap_exceeded"),

    TRANSCODE_STARTING("transcode_starting"),

    TRANSCODING("transcoding"),

    TRANSCODING_ERROR("transcoding_error"),

    UNAVAILABLE("unavailable"),

    UPLOADING("uploading"),

    UPLOADING_ERROR("uploading_error"),

    UNKNOWN(null)
}
