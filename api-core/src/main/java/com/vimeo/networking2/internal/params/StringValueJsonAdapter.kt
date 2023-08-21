/*
 * Copyright (c) 2020 Vimeo (https://vimeo.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.vimeo.networking2.internal.params

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.vimeo.networking2.enums.StringValue

/**
 * A [JsonAdapter] that can convert [StringValue] implementation to its JSON value.
 */
class StringValueJsonAdapter<T : StringValue>(
    private val creator: (String) -> T,
) : JsonAdapter<T>() {

    constructor(values: Array<T>, fallback: T? = null) : this({ value ->
        values.firstOrNull { it.value == value }
            ?: fallback
            ?: error("No value matching: \"$value\". Provide fallback.")
    })

    override fun fromJson(reader: JsonReader): T? = if (reader.peek() == JsonReader.Token.NULL) {
        reader.nextNull()
    } else {
        creator(reader.nextString())
    }

    override fun toJson(writer: JsonWriter, value: T?) {
        writer.value(value?.value)
    }

    companion object {
        /**
         * Generic [StringValueJsonAdapter] that can't deserialize.
         */
        val NON_READING
            get() = StringValueJsonAdapter<StringValue>() {
                error("Reading is unsupported by this adapter")
            }
    }
}
