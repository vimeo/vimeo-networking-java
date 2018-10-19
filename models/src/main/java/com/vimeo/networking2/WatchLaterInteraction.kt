package com.vimeo.networking2

import com.vimeo.networking2.common.UpdatableInteraction
import java.util.*

/**
 * All actions for watch later.
 */
data class WatchLaterInteraction(
    override val added: Boolean? = null,
    override val addedTime: Date? = null,
    override val options: List<String>? = null,
    override val uri: String? = null
): UpdatableInteraction
