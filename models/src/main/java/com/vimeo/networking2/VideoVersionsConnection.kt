package com.vimeo.networking2

/**
 * Based on CAPABILITY_VIDEO_VERSIONS.
 */
data class VideoVersionsConnection(

    /**
     * The URI of the current version of the video.
     */
    val currentUri: String?,

    /**
     * The API URI that resolves to the connection data.
     */
    val latestIncompleteVersion: String?,

    /**
     * An array of HTTP methods permitted on this URI.
     */
    val options: List<String>?,

    /**
     * The total number of versions on this connection.
     */
    val total: Int?,

    /**
     * The API URI that resolves to the connection data.
     */
    val uri: String?

)
