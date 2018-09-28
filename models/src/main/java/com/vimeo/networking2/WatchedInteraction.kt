package com.vimeo.networking2

import java.util.*

data class WatchedInteraction(

    /**
     * Whether the user has watched the video.
     */
    val added: Boolean?,

    /**
     * The time in ISO 8601 format when the user watched the video.
     */
    val addedTime: Date?,

    /**
     * An array of HTTP methods permitted on this URI.
     */
    val options: List<String>?,

    /**
     * The API URI that resolves to the connection data.
     */
    val uri: String?

)
