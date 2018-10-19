package com.vimeo.networking2

import com.vimeo.networking2.enums.FollowType
import java.util.*

/**
 * Follow a group interaction.
 */
data class GroupFollowInteraction(
    override val added: Boolean? = null,
    override val addedTime: Date? = null,
    override val options: List<String>?,
    override val uri: String? = null,
    val title: String? = null,
    val type: FollowType? = null
): UpdatableInteraction
