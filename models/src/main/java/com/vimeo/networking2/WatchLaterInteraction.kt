package com.vimeo.networking2

import java.util.*

data class WatchLaterInteraction(

    /**
     * Whether the user has added the video to their Watch later list.
     */
    val added: Boolean?,

    /**
     * The time in ISO 8601 format when the user added the video to their Watch Later list.
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
