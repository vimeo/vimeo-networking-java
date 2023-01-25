package com.vimeo.networking2.internal.params

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.vimeo.networking2.enums.IntValue

/**
 * A [JsonAdapter] that can convert [IntValue] implementation to its JSON value.
 */
class IntValueJsonAdapter<T : IntValue>(
    private val creator: (Int) -> T?
) : JsonAdapter<T>() {
    override fun fromJson(reader: JsonReader): T? = if (reader.peek() == JsonReader.Token.NULL) {
        reader.nextNull()
    } else {
        creator(reader.nextInt())
    }

    override fun toJson(writer: JsonWriter, value: T?) {
        writer.value(value?.value)
    }

    companion object {
        /**
         * Generic [IntValueJsonAdapter] that can't deserialize.
         */
        val NON_READING get() = IntValueJsonAdapter<IntValue>() { error("Reading is unsupported by this adapter") }
    }
}
