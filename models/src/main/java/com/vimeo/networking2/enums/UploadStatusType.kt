package com.vimeo.networking2.enums

enum class UploadStatusType(override val value: String?) : StringValue {

    COMPLETE("complete"),

    ERROR("error"),

    IN_PROGRESS("in_progress"),

    UNKNOWN(null)
}
