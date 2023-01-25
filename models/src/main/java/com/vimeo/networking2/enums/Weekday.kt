package com.vimeo.networking2.enums

/**
 * Enum class representing days of the week.
 */
enum class Weekday(override val value: Int) : IntValue {
    MONDAY(1),
    TUESDAY(2),
    WEDNESDAY(3),
    THURSDAY(4),
    FRIDAY(5),
    SATURDAY(6),
    SUNDAY(7),
    ;

    companion object {
        /**
         * Parse [Weekday] from its int value.
         */
        fun parse(value: Int) = values().first { it.value == value }
    }
}
