package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Metadata with only connections.
 */
@JsonClass(generateAdapter = true)
data class MetadataConnections<Connections_T>(

    /**
     * Connections for [Connections_T].
     */
    @Json(name = "connections")
    val connections: Connections_T? = null

)
