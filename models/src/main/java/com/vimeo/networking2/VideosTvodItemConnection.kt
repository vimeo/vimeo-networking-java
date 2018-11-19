package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * All connections for a [TvodItem].
 */
@JsonClass(generateAdapter = true)
data class VideosTvodItemConnection(

    /**
     * The total number of extra videos.
     */
    @Json(name = "extra_total")
    val extraTotal: Int? = null,

    /**
     * The total number of main videos.
     */
    @Json(name = "main_total")
    val mainTotal: Int? = null,

    /**
     * An array of HTTP methods permitted on this URI.
     */
    @Json(name = "options")
    val options: List<String>? = null,

    /**
     * The total number of albums on this connection.
     */
    @Json(name = "total")
    val total: Int? = null,

    /**
     * The API URI that resolves to the connection data.
     */
    @Json(name = "uri")
    val uri: String? = null,

    /**
     * The total number of viewable videos.
     */
    @Json(name = "viewable_total")
    val viewableTotal: Int? = null

)
