package com.vimeo.networking2

import java.util.*

/**
 * Follow action information for a category.
 */
data class CategoryFollowInteraction(

    /**
     * Whether the authenticated user has followed this category.
     */
    val added: Boolean? = null,

    /**
     * Whether the authenticated user has followed this category.
     */
    val addedTime: Date? = null,

    /**
     * The URI for following or unfollowing this category: PUT to this URI to follow the category,
     * or DELETE to this URI to unfollow the category.
     */
    val uri: String? = null

)
