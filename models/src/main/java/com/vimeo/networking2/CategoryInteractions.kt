package com.vimeo.networking2

data class CategoryInteractions(

    /**
     * An action indicating if the authenticated user has followed this category.
     */
    val follow: CategoryFollowInteraction?

)
