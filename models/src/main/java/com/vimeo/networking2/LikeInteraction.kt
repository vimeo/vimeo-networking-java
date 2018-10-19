package com.vimeo.networking2

import java.util.*

/**
 * Information on liking a video.
 */
data class LikeInteraction(
    override val added: Boolean? = null,
    override val addedTime: Date? = null,
    override val options: List<String>? = null,
    override val uri: String? = null
) : UpdatableInteraction
