package com.vimeo.networking2

import com.vimeo.networking2.common.FollowableInteractions
import com.vimeo.networking2.common.UpdatableInteraction

/**
 * All actions that can be taken on a category.
 */
data class CategoryInteractions(

    override val follow: FollowInteraction? = null

): FollowableInteractions<UpdatableInteraction>
