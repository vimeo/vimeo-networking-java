package com.vimeo.networking2

import java.util.*

/**
 * Follow a object.
 */
data class FollowInteraction(
    override val added: Boolean? = null,
    override val addedTime: Date? = null,
    override val options: List<String>? = null,
    override val uri: String? = null
): UpdatableInteraction
