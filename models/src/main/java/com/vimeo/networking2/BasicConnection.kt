package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.common.Connection

/**
 * Connection data.
 */
@JsonClass(generateAdapter = true)
data class BasicConnection(

    @Json(name = "options")
    override val options: List<String>? = null,

    @Json(name = "uri")
    override val uri: String? = null,

    /**
     * The total number of albums on this connection.
     */
    @Json(name = "total")
    val total: Int? = null

) : Connection
