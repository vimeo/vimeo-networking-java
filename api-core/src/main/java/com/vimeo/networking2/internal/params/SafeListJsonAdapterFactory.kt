package com.vimeo.networking2.internal.params

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.rawType
import com.vimeo.networking2.internal.adapters.SafeListAdapter
import java.lang.reflect.Type

/**
 *  A [JsonAdapter.Factory] that knows when how to serialize a list or object type to list type.
 */
class SafeListJsonAdapterFactory : JsonAdapter.Factory {
    override fun create(type: Type, annotations: MutableSet<out Annotation>, moshi: Moshi): JsonAdapter<*>? {
        return if (type.rawType.isAssignableFrom(List::class.java)) {
            val elementType: Type = Types.collectionElementType(type, List::class.java)
            val elementAdapter = moshi.adapter<Any?>(elementType)
            SafeListAdapter(elementAdapter)
        } else {
            null
        }
    }
}
