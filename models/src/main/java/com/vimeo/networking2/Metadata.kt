package com.vimeo.networking2

import com.squareup.moshi.Json

/**
 * Metadata with connections and interactions.
 */
data class Metadata<Connections_T, Interactions_T>(

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
)
