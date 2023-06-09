package com.vimeo.networking2.internal.params

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.vimeo.networking2.annotations.SafeList
import com.vimeo.networking2.internal.adapters.SafeListAdapter
import java.lang.reflect.Type

/**
 *  A [JsonAdapter.Factory] that knows when how to serialize a list or object type to list type.
 */
class SafeListJsonAdapterFactory : JsonAdapter.Factory {
    override fun create(type: Type, annotations: MutableSet<out Annotation>, moshi: Moshi): JsonAdapter<*>? {
        val delegateAnnotations = Types.nextAnnotations(annotations, SafeList::class.java) ?: return null

        val elementType: Type = Types.collectionElementType(type, List::class.java)
        val delegateAdapter = moshi.adapter<List<*>>(type, delegateAnnotations)
        val elementAdapter = moshi.adapter<Any>(elementType)
        return SafeListAdapter(delegateAdapter, elementAdapter)
    }
}
