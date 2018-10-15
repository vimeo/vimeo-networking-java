package com.vimeo.networking2

import com.vimeo.networking2.enums.FollowType
import java.util.*

/**
 * Follow a group interaction.
 */
data class GroupFollowInteraction(

    /**
     * Whether the authenticated user has followed this group.
     */
    val added: Boolean? = null,

    /**
     * The time in ISO 8601 format when the user joined this group.
     */
    val addedTime: Date? = null,

    /**
     * The user's title, or the null value if not applicable.
     */
    val title: String? = null,

    /**
     * WWhether the authenticated user is a moderator or subscriber.
     */
    val type: FollowType? = null,

    /**
     * The URI for following. PUT to this URI to follow, or DELETE to this URI to unfollow.
     */
    val uri: String? = null

)
