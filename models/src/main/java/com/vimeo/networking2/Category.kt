package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.common.Entity
import com.vimeo.networking2.common.Followable
import java.util.*

/**
 * Category information.
 */
@JsonClass(generateAdapter = true)
data class Category(

    /**
     * The active icon for the category.
     */
    @Json(name = "icon")
    val icon: PictureCollection? = null,

    /**
     * The last time, in ISO 8601 format, that a video was featured.
     */
    @Json(name = "last_video_featured_time")
    val lastVideoFeaturedTime: Date? = null,

    /**
     * The URL to access the category in a browser.
     */
    @Json(name = "link")
    val link: String? = null,

    /**
     * Metadata about the category.
     */
    @Json(name = "metadata")
    override val metadata: Metadata<CategoryConnections, CategoryInteractions>? = null,

    /**
     * The display name that identifies the category.
     */
    @Json(name = "name")
    val name: String? = null,

    /**
     * The container of this category's parent category, if the current category is
     * a subcategory.
     */
    @Json(name = "parent")
    val parent: Category? = null,

    /**
     * The active picture for this category; defaults to vertical color bars.
     */
    @Json(name = "pictures")
    val pictures: PictureCollection? = null,

    /**
     * The resource key of the category.
     */
    @Json(name = "resource_key")
    val resourceKey: String? = null,

    /**
     * All the subcategories that belong to this category, if the current category is
     * a top-level parent.
     */
    @Json(name = "subcategories")
    val subcategories: List<Category>? = null,

    /**
     * Whether the category is not a subcategory of another category.
     */
    @Json(name = "top_level")
    val topLevel: Boolean? = null,

    /**
     * The unique identifier to access the category resource.
     */
    @Json(name = "uri")
    val uri: String? = null

): Followable, Entity {

    override val identifier: String? = resourceKey

}
