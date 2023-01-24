package com.vimeo.networking2.params

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.enums.SchedulingType
import com.vimeo.networking2.enums.Weekday
import java.util.Date

/**
 * Represents parameters to schedule event.
 */
@JsonClass(generateAdapter = true)
sealed class SchedulingParam {

    /**
     * Type of schedule.
     */
    @Json(name = "type")
    abstract val type: SchedulingType

    /**
     * Schedule start [Date].
     */
    @Json(name = "scheduled_time")
    abstract val scheduledTime: Date

    /**
     * Represents one time schedule.
     */
    data class Single(
        override val scheduledTime: Date,
    ) : SchedulingParam() {

        override val type: SchedulingType get() = SchedulingType.SINGLE
    }

    /**
     * Represents weekly (periodic) schedule.
     */
    @JsonClass(generateAdapter = true)
    data class Weekly(
        override val scheduledTime: Date,

        /**
         * Schedule time.
         */
        @Json(name = "daily_time")
        val dailyTime: Date?,

        /**
         * Days of the week when event is scheduled.
         */
        @Json(name = "weekdays")
        val weekdays: Set<Weekday>,
    ) : SchedulingParam() {

        override val type get() = SchedulingType.WEEKLY
    }
}
