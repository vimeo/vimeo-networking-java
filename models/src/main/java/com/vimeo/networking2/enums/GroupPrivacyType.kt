package com.vimeo.networking2.enums

/**
 * Group privacy values.
 */
enum class GroupPrivacyType(override val value: String?) : StringValue {

    ALL("all"),

    ANYBODY("anybody"),

    MEMBERS("members"),

    UNKNOWN(null)
}
