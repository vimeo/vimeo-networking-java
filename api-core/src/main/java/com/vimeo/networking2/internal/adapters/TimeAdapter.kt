package com.vimeo.networking2.internal.adapters

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import com.vimeo.networking2.annotations.Time
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.GregorianCalendar
import java.util.TimeZone

@Suppress("SimpleDateFormat")
class TimeAdapter {
    private val timeZone = TimeZone.getTimeZone("UTC")
    private val timeRegex = Regex("^(\\d{2}):(\\d{2}):(\\d{2})Z$")
    private val outputFormat = SimpleDateFormat("HH:mm:ss'Z'").apply {
        timeZone = this@TimeAdapter.timeZone
    }

    @ToJson
    fun toJson(@Time date: Date?): String? = date?.let(outputFormat::format)

    @FromJson
    @Time
    fun fromJson(value: String?): Date? {
        if (value == null) return null
        // Can't use outputFormat, since time could vary depending on date and time zone.
        // E.g. 01-01-1970T22:00:00Z transforms into 1:00 AM GMT, however should be 12:00 AM.
        val (hour, minute, second) = timeRegex.matchEntire(value)?.destructured ?: return null
        return GregorianCalendar(timeZone).apply {
            set(Calendar.HOUR_OF_DAY, hour.toInt())
            set(Calendar.MINUTE, minute.toInt())
            set(Calendar.SECOND, second.toInt())
            set(Calendar.MILLISECOND, 0)
        }.time
    }
}
