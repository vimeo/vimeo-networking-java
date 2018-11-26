package com.vimeo.networking2.enums

/**
 * Different types of content filters for videos.
 */
enum class ContentFilterType(override val value: String?) : StringValue {

    DRUGS("drugs"),

    LANGUAGE("language"),

    NUDITY("nudity"),

    SAFE("safe"),

    UNRATED("unrated"),

    VIOLENCE("violence"),

    UNKNOWN(null)
}
