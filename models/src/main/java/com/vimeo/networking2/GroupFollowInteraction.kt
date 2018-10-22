package com.vimeo.networking2

import com.vimeo.networking2.common.UpdatableInteraction
import com.vimeo.networking2.enums.ApiOptionsType
import com.vimeo.networking2.enums.FollowType
import java.util.*

/**
 * Follow a group interaction.
 */
data class GroupFollowInteraction(

    override val added: Boolean? = null,

    override val addedTime: Date? = null,

    override val options: List<ApiOptionsType>? = null,

    override val uri: String? = null,

    /**
     * The user's title, or the null value if not applicable.
     */
    val title: String? = null,

    /**
     * Whether the authenticated user is a moderator or subscriber.
     */
    val type: FollowType? = null

): UpdatableInteraction
