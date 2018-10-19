package com.vimeo.networking2.common

import com.vimeo.networking2.Interaction
import java.util.*

/**
 * Interactions that update the state of a video, category, channel, etc..
 * These interactions can be like, follow, report etc...
 */
interface UpdatableInteraction: Interaction {

    /**
     * Whether the authenticated user performed the interaction.
     */
    val added: Boolean?

    /**
     * Whether the authenticated user performed the interaction.
     */
    val addedTime: Date?

}
