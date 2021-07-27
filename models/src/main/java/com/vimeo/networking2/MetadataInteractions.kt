package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Metadata with only interactions.
 *
 * @param interactions Interactions for [Interactions_T].
 */
@JsonClass(generateAdapter = true)
data class MetadataInteractions<Interactions_T>(

    @Json(name = "interactions")
    val interactions: Interactions_T? = null
)
