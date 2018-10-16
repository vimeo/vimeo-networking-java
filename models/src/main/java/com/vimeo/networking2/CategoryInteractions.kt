package com.vimeo.networking2

/**
 * All actions that can be taken on a category.
 */
data class CategoryInteractions(

    /**
     * An action indicating if the authenticated user has followed this category.
     */
    val follow: CategoryFollowInteraction? = null

)
