package com.vimeo.networking2.enums

import com.squareup.moshi.JsonClass

/**
 * Enum class representing type of event scheduling.
 */
@JsonClass(generateAdapter = true)
enum class SchedulingType(override val value: String) : StringValue {
    WEEKLY("weekly"),
    SINGLE("single"),
    ;
}
