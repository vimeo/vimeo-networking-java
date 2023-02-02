package com.vimeo.networking2.enums

/**
 * Enum class representing type of event scheduling.
 */
enum class ScheduleType(override val value: String) : StringValue {
    WEEKLY("weekly"),
    SINGLE("single"),
    ;

    companion object {
        /**
         * Parse [ScheduleType] from its string value.
         */
        fun parse(value: String) = values().first { it.value == value }
    }
}
