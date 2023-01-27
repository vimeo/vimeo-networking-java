package com.vimeo.networking2

import com.vimeo.networking2.common.Entity

/**
 * Represents entity that contains [Video].
 */
sealed interface VideoContainer<T : VideoContainer<T>> : Entity {

    /**
     * Container URI (video or live event URI).
     */
    val uri: String?

    /**
     * Related [Video].
     */
    val video: Video?

    /**
     * Copies this [VideoContainer] with new [video].
     */
    fun copyVideoContainer(
        video: Video? = null,
    ): T
}
