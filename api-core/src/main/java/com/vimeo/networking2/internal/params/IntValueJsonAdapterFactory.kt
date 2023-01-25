package com.vimeo.networking2.internal.params

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.vimeo.networking2.enums.IntValue
import com.vimeo.networking2.enums.Weekday
import java.lang.reflect.Type

/**
 * A [JsonAdapter.Factory] that knows when how to serialize a sub-type of [IntValue].
 */
class IntValueJsonAdapterFactory : JsonAdapter.Factory {
    override fun create(type: Type, annotations: MutableSet<out Annotation>, moshi: Moshi): JsonAdapter<*>? =
        if (type is Class<*> && IntValue::class.java.isAssignableFrom(type)) {
            if (Weekday::class.java.isAssignableFrom(type)) {
                IntValueJsonAdapter(Weekday.Companion::parse)
            } else {
                IntValueJsonAdapter.NON_READING
            }
        } else {
            null
        }
}
