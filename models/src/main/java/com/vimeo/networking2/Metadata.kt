package com.vimeo.networking2

/**
 * Metadata with connections and interactions.
 */
data class Metadata<Connections_T, Interactions_T>(

    /**
     * All connections for an object.
     */
    val connections: Connections_T? = null,

    /**
     * All interactions for an object.
     */
    val interactions: Interactions_T? = null
)
