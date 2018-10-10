package com.vimeo.networking2

import java.util.*

/**
 * Information on liking a video.
 */
data class LikeInteraction(

    /**
     * Whether the user has liked the video.
     */
    val added: Boolean? = null,

    /**
     * The time in ISO 8601 format when the user liked the video.
     */
    val addedTime: Date? = null,

    /**
     * An array of HTTP methods permitted on this URI.
     */
    val options: List<String>? = null,

    /**
     * The API URI that resolves to the connection data.
     */
    val uri: String? = null

)
