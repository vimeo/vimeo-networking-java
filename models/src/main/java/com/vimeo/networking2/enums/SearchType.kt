package com.vimeo.networking2.enums

/**
 * Search type.
 */
enum class SearchType(override val value: String?) : StringValue {

    BLOG("blog"),

    CHANNEL("channel"),

    VIDEO("video"),

    GROUP("group"),

    ONDEMAND("ondemand"),

    PEOPLE("people"),

    UNKNOWN(null)
}
