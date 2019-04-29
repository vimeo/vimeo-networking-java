package com.vimeo.networking2.enums

/**
 * Different types of badges for videos.
 */
enum class VideoBadgeType(override val value: String?) : StringValue {

    CAMEO("cameo"),

    STAFFPICK("staffpick"),

    STAFFPICK_BEST_OF_THE_MONTH("staffpick-best-of-the-month"),

    STAFFPICK_BEST_OF_THE_YEAR("staffpick-best-of-the-year"),

    STAFFPICK_PREMIERE("staffpick-premiere"),

    WEEKENDCHALLENGE("weekendchallenge"),

    UNKNOWN(null)
}
