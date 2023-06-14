package com.vimeo.networking2.internal.params

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.vimeo.networking2.annotations.SafeObject
import com.vimeo.networking2.internal.adapters.SafeObjectAdapter
import java.lang.reflect.Type

/**
 *  A [JsonAdapter.Factory] that knows when how to serialize a empty list or object to object type.
 */
class SafeObjectJsonAdapterFactory : JsonAdapter.Factory {
    override fun create(type: Type, annotations: MutableSet<out Annotation>, moshi: Moshi): JsonAdapter<*>? {
        return if (Types.nextAnnotations(annotations, SafeObject::class.java) != null) {
            val elementAdapter = moshi.adapter<Any>(type)
            SafeObjectAdapter(elementAdapter)
        } else {
            null
        }
    }
}
