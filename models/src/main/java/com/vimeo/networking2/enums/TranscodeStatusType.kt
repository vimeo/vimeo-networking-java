package com.vimeo.networking2.enums

/**
 * Status code for video availability.
 */
enum class TranscodeStatusType(override val value: String?) : StringValue {

    COMPLETE("complete"),

    ERROR("error"),

    IN_PROGRESS("in_progress"),

    UNKNOWN(null)
}
