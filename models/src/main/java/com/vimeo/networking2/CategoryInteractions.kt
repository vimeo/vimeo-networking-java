package com.vimeo.networking2

/**
 * All actions that can be taken on a category.
 */
data class CategoryInteractions(

    override val follow: FollowInteraction? = null

): FollowableInteractions<UpdatableInteraction>
