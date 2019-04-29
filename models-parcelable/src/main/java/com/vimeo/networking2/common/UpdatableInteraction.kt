package com.vimeo.networking2.common

import java.util.*

/**
 * Interactions that update the state of a video, category, channel, etc..
 * These interactions are like, follow and watcher later.
 */
interface UpdatableInteraction: Interaction {

    /**
     * Whether the authenticated user performed the interaction.
     */
    val added: Boolean?

    /**
     * The time when the user took the interaction on the entity.
     */
    val addedTime: Date?

}
