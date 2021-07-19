package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Metadata with connections and interactions.
 *
 * @param connections All connections for an object.
 * @param interactions All interactions for an object.
 */
@JsonClass(generateAdapter = true)
data class Metadata<Connections_T, Interactions_T>(

    @Json(name = "connections")
    val connections: Connections_T? = null,

    @Json(name = "interactions")
    val interactions: Interactions_T? = null
)
