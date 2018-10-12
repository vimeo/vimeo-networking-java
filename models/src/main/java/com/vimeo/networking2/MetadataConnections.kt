package com.vimeo.networking2

import com.squareup.moshi.Json

/**
 * Metadata with only connections.
 */
data class MetadataConnections<Connections_T>(

    /**
     * Connections for [Connections_T].
     */
    @Json(name = "connections")
    val connections: Connections_T? = null

)
