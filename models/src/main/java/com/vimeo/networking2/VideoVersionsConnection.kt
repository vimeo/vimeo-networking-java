package com.vimeo.networking2

/**
 * Based on CAPABILITY_VIDEO_VERSIONS.
 */
data class VideoVersionsConnection(

    /**
     * The URI of the current version of the video.
     */
    val currentUri: String? = null,

    /**
     * The API URI that resolves to the connection data.
     */
    val latestIncompleteVersion: String? = null,

    /**
     * An array of HTTP methods permitted on this URI.
     */
    val options: List<String>? = null,

    /**
     * The total number of versions on this connection.
     */
    val total: Int? = null,

    /**
     * The API URI that resolves to the connection data.
     */
    val uri: String? = null

)
