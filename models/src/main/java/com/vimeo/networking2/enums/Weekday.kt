package com.vimeo.networking2.enums

import java.util.Calendar

/**
 * Enum class representing days of the week.
 * @property calendarWeekday relation to weekday in [Calendar].
 */
enum class Weekday(
    override val value: Int,
    val calendarWeekday: Int,
) : IntValue {
    MONDAY(1, Calendar.MONDAY),
    TUESDAY(2, Calendar.TUESDAY),
    WEDNESDAY(3, Calendar.WEDNESDAY),
    THURSDAY(4, Calendar.THURSDAY),
    FRIDAY(5, Calendar.FRIDAY),
    SATURDAY(6, Calendar.SATURDAY),
    SUNDAY(7, Calendar.SUNDAY),
    ;

    companion object {
        /**
         * Parse [Weekday] from its int value.
         */
        fun parse(value: Int) = values().first { it.value == value }
    }
}
