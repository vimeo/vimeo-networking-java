package com.vimeo.networking2.internal.adapters

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import com.vimeo.networking2.annotations.Iso8601NoMillis
import java.util.Calendar
import java.util.Date
import java.util.GregorianCalendar
import java.util.Locale
import java.util.TimeZone

/**
 * Json adapter for [Date] properties annotated with [Iso8601NoMillis].
 * Formats [Date] instance to it's ISO8601 date representations without millis.
 */
class Iso8601NoMillisAdapter {
    private val timeZone = TimeZone.getTimeZone("GMT")

    /**
     * Converts [Date] instance to it's ISO8601 date representations without millis.
     * Modified version of https://github.com/square/moshi/blob/a13aa33f12bc7db6ec0c35d042b14ab7f8a89073/moshi-adapters/src/main/java/com/squareup/moshi/adapters/Iso8601Utils.kt#L48
     */
    @ToJson
    fun toJson(@Iso8601NoMillis date: Date?): String? {
        if (date == null) return null

        val calendar: Calendar = GregorianCalendar(timeZone, Locale.US).apply {
            time = date
        }

        val capacity = "yyyy-MM-ddThh:mm:ssZ".length
        val formatted = StringBuilder(capacity)
        formatted.padInt(calendar[Calendar.YEAR], "yyyy".length)
        formatted.append('-')
        formatted.padInt(calendar[Calendar.MONTH] + 1, "MM".length)
        formatted.append('-')
        formatted.padInt(calendar[Calendar.DAY_OF_MONTH], "dd".length)
        formatted.append('T')
        formatted.padInt(calendar[Calendar.HOUR_OF_DAY], "hh".length)
        formatted.append(':')
        formatted.padInt(calendar[Calendar.MINUTE], "mm".length)
        formatted.append(':')
        formatted.padInt(calendar[Calendar.SECOND], "ss".length)
        formatted.append('Z')
        return formatted.toString()
    }

    /**
     * Delegates [Date] parsing to previous moshi [Date] adapter.
     */
    @FromJson
    @Iso8601NoMillis
    fun fromJson(value: Date?): Date? = value

    private fun StringBuilder.padInt(value: Int, length: Int) {
        val strValue = value.toString()
        for (i in length - strValue.length downTo 1) {
            append('0')
        }
        append(strValue)
    }
}
