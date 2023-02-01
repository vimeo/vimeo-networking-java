package com.vimeo.networking2.params

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.adapters.Time
import com.vimeo.networking2.enums.ScheduleType
import com.vimeo.networking2.enums.Weekday
import java.util.Date

/**
 * Represents parameters to schedule event.
 * @property type Type of schedule.
 * @property startTime Scheduled start [Date] for [ScheduleType.SINGLE].
 * @property scheduledTime Schedule start [Date] for [ScheduleType.WEEKLY].
 * @property dailyTime Schedule time for [ScheduleType.WEEKLY].
 * @property weekdays Days of the week when event is scheduled weekly.
 */
@JsonClass(generateAdapter = true)
data class Schedule(

    @Json(name = "type")
    val type: ScheduleType? = null,

    @Json(name = "start_time")
    val startTime: Date? = null,

    @Json(name = "scheduled_time")
    val scheduledTime: Date? = null,

    @Time
    @Json(name = "daily_time")
    val dailyTime: Date? = null,

    @Json(name = "weekdays")
    val weekdays: Set<Weekday>? = null,
) {
    companion object {
        /**
         * Empty [Schedule], used to clear event schedule.
         */
        val EMPTY = Schedule()
    }
}
