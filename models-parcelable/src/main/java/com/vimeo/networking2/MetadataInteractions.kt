package com.vimeo.networking2

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

/**
 * Metadata with only interactions.
 */
@Parcelize
@JsonClass(generateAdapter = true)
data class MetadataInteractions<Interactions_T: Parcelable>(

    /**
     * Interactions for [Interactions_T].
     */
    @Json(name = "interactions")
    val interactions: Interactions_T? = null

): Parcelable
