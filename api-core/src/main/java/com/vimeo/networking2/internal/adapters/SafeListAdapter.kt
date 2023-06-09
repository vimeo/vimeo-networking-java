package com.vimeo.networking2.internal.adapters

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter

/**
 * Json adapter for [List] properties.
 * Create a [List] based on single object or list of objects in JSON response.
 * Possible json values: [], [{"a":1},{"a":2}], {"a":5}.
 */
class SafeListAdapter<T>(
    private val elementAdapter: JsonAdapter<T>
) : JsonAdapter<List<T>>() {

    override fun fromJson(reader: JsonReader): List<T>? {
        if (reader.peek() != JsonReader.Token.BEGIN_ARRAY) {
            return elementAdapter.fromJson(reader)?.let { listOf(it) }
        }
        val items = mutableListOf<T>()
        reader.beginArray()
        while (reader.hasNext()) {
            elementAdapter.fromJson(reader)?.let(items::add)
        }
        reader.endArray()
        return items
    }

    override fun toJson(writer: JsonWriter, value: List<T>?) {
        if (value?.size == 1) {
            elementAdapter.toJson(writer, value[0])
        } else {
            writer.beginArray()
            value?.forEach { elementAdapter.toJson(writer, it) }
            writer.endArray()
        }
    }
}
