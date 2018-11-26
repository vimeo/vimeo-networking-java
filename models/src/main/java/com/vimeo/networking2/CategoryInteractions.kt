package com.vimeo.networking2

import com.vimeo.networking2.common.FollowableInteractions
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * All actions that can be taken on a category.
 */
@JsonClass(generateAdapter = true)
data class CategoryInteractions(

    @Json(name = "follow")
    override val follow: FollowInteraction? = null

): FollowableInteractions
