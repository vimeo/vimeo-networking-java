package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.common.Entity
import com.vimeo.networking2.common.Followable
import java.util.Date

/**
 * Category information.
 *
 * @param icon The active icon for the category.
 * @param lastVideoFeaturedTime The last time, in ISO 8601 format, that a video was featured.
 * @param link The URL to access the category in a browser.
 * @param name The display name that identifies the category.
 * @param parent The container of this category's parent category, if the current category is  a subcategory.
 * @param pictures The active picture for this category; defaults to vertical color bars.
 * @param resourceKey The resource key of the category.
 * @param subcategories All the subcategories that belong to this category, if the current category is a top-level
 * parent.
 * @param topLevel Whether the category is not a subcategory of another category.
 * @param uri The unique identifier to access the category resource.
 */
@JsonClass(generateAdapter = true)
data class Category(

    @Json(name = "icon")
    val icon: PictureCollection? = null,

    @Json(name = "last_video_featured_time")
    val lastVideoFeaturedTime: Date? = null,

    @Json(name = "link")
    val link: String? = null,

    @Json(name = "metadata")
    override val metadata: Metadata<CategoryConnections, CategoryInteractions>? = null,

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "parent")
    val parent: Category? = null,

    @Json(name = "pictures")
    val pictures: PictureCollection? = null,

    @Json(name = "resource_key")
    val resourceKey: String? = null,

    @Json(name = "subcategories")
    val subcategories: List<Category>? = null,

    @Json(name = "top_level")
    val topLevel: Boolean? = null,

    @Json(name = "uri")
    val uri: String? = null

) : Followable, Entity {

    override val identifier: String? = resourceKey
}
