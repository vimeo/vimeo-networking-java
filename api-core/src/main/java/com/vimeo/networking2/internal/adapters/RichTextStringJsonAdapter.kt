package com.vimeo.networking2.internal.adapters

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.ToJson
import com.vimeo.networking2.annotations.RichTextString
import com.vimeo.networking2.richtext.RichText

/**
 * Json adapter for [RichText] properties annotated with [RichTextString].
 */
class RichTextStringJsonAdapter {

    /**
     * Converts JSON string rich text representation to [RichText].
     */
    @FromJson
    @RichTextString
    fun fromJson(
        jsonReader: JsonReader,
        adapter: JsonAdapter<RichText>,
    ): RichText? = if (jsonReader.peek() == JsonReader.Token.NULL) {
        jsonReader.nextNull()
    } else {
        adapter.fromJson(jsonReader.nextString())
    }

    /**
     * Converts [RichText] instance to JSON string.
     */
    @ToJson
    fun toJson(
        jsonWriter: JsonWriter,
        @RichTextString value: RichText?,
        adapter: JsonAdapter<RichText>,
    ) {
        jsonWriter.value(value?.let(adapter::toJson))
    }
}
