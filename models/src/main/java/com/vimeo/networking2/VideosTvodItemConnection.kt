package com.vimeo.networking2

/**
 * All connections for a [TVodItem].
 */
data class VideosTvodItemConnection(

    /**
     * The total number of extra videos.
     */
    val extraTotal: Int? = null,

    /**
     * The total number of main videos.
     */
    val mainTotal: Int? = null,

    /**
     * An array of HTTP methods permitted on this URI.
     */
    val options: List<String>? = null,

    /**
     * The total number of albums on this connection.
     */
    val total: Int? = null,

    /**
     * The API URI that resolves to the connection data.
     */
    val uri: String? = null,

    /**
     * The total number of viewable videos.
     */
    val viewableTotal: Int? = null

)
