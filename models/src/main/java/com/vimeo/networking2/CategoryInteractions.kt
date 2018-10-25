package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * All actions that can be taken on a category.
 */
@JsonClass(generateAdapter = true)
data class CategoryInteractions(

    /**
     * An action indicating if the authenticated user has followed this category.
     */
    @Json(name = "follow")
    val follow: CategoryFollowInteraction? = null

)
