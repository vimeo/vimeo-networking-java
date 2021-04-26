package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.common.Connection

/**
 * The default implementation of a connection that does not add any other properties.
 */
@JsonClass(generateAdapter = true)
data class DefaultConnection(
    @Json(name = "options")
    override val options: List<String>? = null,

    @Json(name = "uri")
    override val uri: String? = null
) : Connection
