package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Connection data.
 */
@JsonClass(generateAdapter = true)
data class Connection(

    /**
     * An array of HTTP methods permitted on this URI.
     */
    @Json(name = "options")
    val options: List<String>? = null,

    /**
     * The API URI that resolves to the connection data.
     */
    @Json(name = "uri")
    val uri: String? = null,

    /**
     * The total number of albums on this connection.
     */
    @Json(name = "total")
    val total: Int? = null

)
