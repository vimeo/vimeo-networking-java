package com.vimeo.networking2

import java.util.*

/**
 * Interactions that update the state of a video, category, channel, etc..
 * These interactions can be like, follow, report etc...
 */
interface UpdatableInteraction {

    /**
     * Whether the authenticated user performed the interaction.
     */
    val added: Boolean?

    /**
     * Whether the authenticated user performed the interaction.
     */
    val addedTime: Date?

    /**
     * An array of the HTTP methods permitted on this URI.
     */
    val options: List<String>?

    /**
     * The URI for performing the updating interaction. The GET, POST, PUT or DELETE options
     * that you can use on the uri are defined in the [options] array.
     */
    val uri: String?
}
