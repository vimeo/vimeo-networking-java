package com.vimeo.networking2.internal.adapters

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter

/**
 * Json adapter for [List] properties annotated with [SafeList].
 * Create a [List] based on single or list of objects in JSON response.
 * Possible json values: [], [{"a":1},{"a":2}], {"a":5}.
 */
class SafeListAdapter<T>(
    private val delegateAdapter: JsonAdapter<List<T>>,
    private val elementAdapter: JsonAdapter<T>
) : JsonAdapter<List<T>>() {

    override fun fromJson(reader: JsonReader): List<T>? {
        if (reader.peek() != JsonReader.Token.BEGIN_ARRAY) {
            return elementAdapter.fromJson(reader)?.let { listOf(it) }
        }
        return delegateAdapter.fromJson(reader)
    }

    override fun toJson(writer: JsonWriter, value: List<T>?) {
        if (value?.size == 1) {
            elementAdapter.toJson(writer, value[0])
        } else {
            delegateAdapter.toJson(writer, value)
        }
    }
}
