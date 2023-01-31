package com.vimeo.networking2.adapters

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone

@Suppress("SimpleDateFormat")
class TimeAdapter {
    private val outputFormat = SimpleDateFormat(API_TIME_FORMAT)
    private val appenderFormat = SimpleDateFormat(ALIGNMENT_DATE_FORMAT)
    private val parserFormat = SimpleDateFormat(createParserTimestamp(ALIGNMENT_DATE_FORMAT, API_TIME_FORMAT))

    init {
        sequenceOf(outputFormat, appenderFormat, parserFormat).forEach { it.timeZone = TimeZone.getTimeZone("UTC") }
    }

    @ToJson
    fun toJson(@Time date: Date?): String? = date?.let(outputFormat::format)

    @FromJson
    @Time
    fun fromJson(value: String?): Date? {
        if (value == null) return null
        // This is needed to align time, since it could vary depending on date and time zone
        // 01-01-1970T22:00:00Z transforms into 1:00 AM GMT, however should be 12:00 AM.
        val aligned = createParserTimestamp(appenderFormat.format(Date()), value)
        return parserFormat.parse(aligned)
    }

    companion object {
        private const val API_TIME_FORMAT = "HH:mm:ss'Z'"
        private const val ALIGNMENT_DATE_FORMAT = "yyyy-MM-dd"

        fun createParserTimestamp(date: String, time: String) = "$date $time"
    }
}
