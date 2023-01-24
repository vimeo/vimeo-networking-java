package com.vimeo.networking2.enums

/**
 * Enum class representing days of the week.
 */
enum class Weekday : IntValue {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY,
    ;

    override val value: Int get() = ordinal + 1
}
