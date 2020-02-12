package com.vimeo.networking2.enums

/**
 * The amount of time being represented by an upload space.
 */
enum class UploadSpaceType(override val value: String?) : StringValue {

    LIFETIME("lifetime"),

    PERIODIC("periodic"),

    UNKNOWN(null)
}
