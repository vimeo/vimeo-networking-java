package com.vimeo.networking2.enums

enum class UploadSpaceType(override val value: String?) : StringValue {

    LIFETIME("lifetime"),

    PERIODIC("periodic"),

    UNKNOWN(null)
}
