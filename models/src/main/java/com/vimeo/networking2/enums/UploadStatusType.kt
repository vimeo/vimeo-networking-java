package com.vimeo.networking2.enums

/**
 * The status of an upload that was started in the past.
 */
enum class UploadStatusType(override val value: String?) : StringValue {

    COMPLETE("complete"),

    ERROR("error"),

    IN_PROGRESS("in_progress"),

    UNKNOWN(null)
}
