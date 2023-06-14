package com.vimeo.networking2.internal.adapters

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter

/**
 * Json adapter for object properties annotated with [SafeObject].
 * Create a object based on empty list or single object in JSON response.
 * Possible json values: [], {"a":1}.
 */
class SafeObjectAdapter<T>(
    private val elementAdapter: JsonAdapter<T>
) : JsonAdapter<T>() {

    override fun fromJson(reader: JsonReader): T? {
        return if (reader.peek() == JsonReader.Token.BEGIN_OBJECT) {
            elementAdapter.fromJson(reader)
        } else {
            // Skip wrong object
            reader.readJsonValue()
            null
        }
    }

    override fun toJson(writer: JsonWriter, value: T?) {
        if (value != null) {
            elementAdapter.toJson(writer, value)
        } else {
            writer.beginArray()
            writer.endArray()
        }
    }
}
