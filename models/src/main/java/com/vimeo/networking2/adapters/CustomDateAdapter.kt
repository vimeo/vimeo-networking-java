package com.vimeo.networking2.adapters

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import java.io.IOException
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Custom adapter that returns a null value if the date
 * was not specified in the response. Otherwise, it will
 * attempt to parse the date.
 */
class CustomDateAdapter : JsonAdapter<Date>() {

    @Synchronized
    @Throws(IOException::class)
    override fun fromJson(reader: JsonReader): Date? {
        val token: JsonReader.Token = reader.peek()
        return if (token == JsonReader.Token.NULL) {
            reader.skipValue()
            null
        } else {
            parseDate(reader)
        }
    }

    private fun parseDate(reader: JsonReader): Date? {
        return try {
            dateFormat.parse(reader.nextString())
        } catch (e: ParseException) {
            throw IOException("Date parsing failed", e)
        }
    }

    @Synchronized
    @Throws(IOException::class)
    override fun toJson(writer: JsonWriter, value: Date?) {
        writer.value(dateFormat.format(value))
    }

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ", Locale.ENGLISH)
}
