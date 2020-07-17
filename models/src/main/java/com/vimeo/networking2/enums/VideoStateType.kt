package com.vimeo.networking2.enums

/**
 * Video transcoding states.
 */
enum class VideoStateType(override val value: String?) : StringValue {

    ACTIVE("active"),

    BLOCKED("blocked"),

    EXCEEDS_QUOTA("exceeds_quota"),

    EXCEEDS_TOTAL_CAP("exceeds_total_cap"),

    FAILED("failed"),

    FINISHING("finishing"),

    INTERNAL_ERROR("internal_error"),

    INVALID_FILE("invalid_file"),

    PENDING("pending"),

    READY("ready"),

    RETRIEVED("retrieved"),

    STANDBY("standby"),

    STARTING("starting"),

    UPLOAD_COMPLETE("upload_complete"),

    UPLOAD_INCOMPLETE("upload_incomplete"),

    UNKNOWN("unknown")
}
