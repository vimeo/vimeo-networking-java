package com.vimeo.networking2.internal.params

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.vimeo.networking2.enums.IntValue

/**
 * A [JsonAdapter] that can write convert [IntValue] implementation to its JSON value.
 */
class IntValueJsonAdapter : JsonAdapter<IntValue>() {
    override fun fromJson(reader: JsonReader): IntValue? = error("Reading is unsupported by this adapter")

    override fun toJson(writer: JsonWriter, value: IntValue?) {
        writer.value(value?.value)
    }
}
