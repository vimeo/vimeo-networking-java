package com.vimeo.networking2

import com.squareup.moshi.Json

/**
 * Metadata with only interactions.
 */
data class MetadataInteractions<Interactions_T>(

    /**
     * Interactions for [Interactions_T].
     */
    @Json(name = "interactions")
    val interactions: Interactions_T? = null
)
