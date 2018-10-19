package com.vimeo.networking2

data class BasicInteraction(
    override val options: List<String>? = null,
    override val uri: String? = null
): Interaction
