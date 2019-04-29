package com.vimeo.networking2

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

/**
 * Metadata with connections and interactions.
 */
@Parcelize
@JsonClass(generateAdapter = true)
data class Metadata<out Connections_T: Parcelable, out Interactions_T: Parcelable>(

    /**
     * All connections for an object.
     */
    @Json(name = "connections")
    val connections: Connections_T? = null,

    /**
     * All interactions for an object.
     */
    @Json(name = "interactions")
    val interactions: Interactions_T? = null

) : Parcelable
