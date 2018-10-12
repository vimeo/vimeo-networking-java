package com.vimeo.networking2
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

import java.util.*

/**
 * Follow action information for a category.
 */
@JsonClass(generateAdapter = true)
data class CategoryFollowInteraction(

    /**
     * Whether the authenticated user has followed this category.
     */
    @Json(name = "added")
    val added: Boolean? = null,

    /**
     * Whether the authenticated user has followed this category.
     */
    @Json(name = "added_time")
    val addedTime: Date? = null,

    /**
     * The URI for following or unfollowing this category: PUT to this URI to follow the category,
     * or DELETE to this URI to unfollow the category.
     */
    @Json(name = "uri")
    val uri: String? = null

)
