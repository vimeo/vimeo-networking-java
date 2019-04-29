package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

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

): Serializable {

    companion object {

        private const val serialVersionUID: Long = 828710596885975215L
    }

}
