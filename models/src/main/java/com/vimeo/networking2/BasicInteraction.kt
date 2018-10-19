package com.vimeo.networking2

/**
 * Interaction with only options and uri information.
 */
data class BasicInteraction(
    override val options: List<String>? = null,
    override val uri: String? = null
): Interaction
