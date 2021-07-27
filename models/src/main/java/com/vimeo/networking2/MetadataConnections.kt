package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Metadata with only connections.
 *
 * @param connections Connections for [Connections_T].
 */
@JsonClass(generateAdapter = true)
data class MetadataConnections<Connections_T>(

    @Json(name = "connections")
    val connections: Connections_T? = null

)
