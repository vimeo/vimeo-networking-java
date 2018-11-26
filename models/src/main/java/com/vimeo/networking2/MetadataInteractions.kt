package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Metadata with only interactions.
 */
@JsonClass(generateAdapter = true)
data class MetadataInteractions<Interactions_T>(

    /**
     * Interactions for [Interactions_T].
     */
    @Json(name = "interactions")
    val interactions: Interactions_T? = null
)
