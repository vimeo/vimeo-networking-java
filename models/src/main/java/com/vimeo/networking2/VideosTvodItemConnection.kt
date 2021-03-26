package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * All connections for a [TvodItem].
 *
 * @param extraTotal The total number of extra videos.
 * @param mainTotal The total number of main videos.
 * @param options An array of HTTP methods permitted on this URI.
 * @param total The total number of albums on this connection.
 * @param uri The API URI that resolves to the connection data.
 * @param viewableTotal The total number of viewable videos.
 */
@JsonClass(generateAdapter = true)
data class VideosTvodItemConnection(

    @Json(name = "extra_total")
    val extraTotal: Int? = null,

    @Json(name = "main_total")
    val mainTotal: Int? = null,

    @Json(name = "options")
    val options: List<String>? = null,

    @Json(name = "total")
    val total: Int? = null,

    @Json(name = "uri")
    val uri: String? = null,

    @Json(name = "viewable_total")
    val viewableTotal: Int? = null

)
