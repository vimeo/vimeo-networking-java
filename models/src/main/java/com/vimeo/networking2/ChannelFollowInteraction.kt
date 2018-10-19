package com.vimeo.networking2

import com.vimeo.networking2.common.UpdatableInteraction
import com.vimeo.networking2.enums.FollowType
import java.util.*

/**
 * Follow a channel interaction.
 */
data class ChannelFollowInteraction(
    override val added: Boolean? = null,
    override val addedTime: Date? = null,
    override val options: List<String>?,
    override val uri: String? = null,
    val type: FollowType = FollowType.UNKNOWN
): UpdatableInteraction
