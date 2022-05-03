package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.annotations.Internal

/**
 * A connection used to log into Vimeo via Single Sign-On.
 *
 * @param connectionName The name of the connection.
 * @param metadata The metadata containing the interactions of the connection.
 * @param uri The URI of the connection.
 */
@Internal
@JsonClass(generateAdapter = true)
data class SsoConnection(

    @Json(name = "connection_name")
    val connectionName: String? = null,

    @Json(name = "metadata")
    val metadata: MetadataInteractions<SsoConnectionInteractions>? = null,

    @Json(name = "uri")
    val uri: String? = null
)
