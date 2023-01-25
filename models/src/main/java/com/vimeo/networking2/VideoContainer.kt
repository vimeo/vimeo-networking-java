package com.vimeo.networking2

/**
 * Represents entity that contains [Video].
 */
sealed interface VideoContainer<T : VideoContainer<T>> {

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
