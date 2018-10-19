package com.vimeo.networking2

import java.util.*

/**
 * Category information.
 */
data class Category(

    /**
     * The active icon for the category.
     */
    val icon: PictureCollection? = null,

    /**
     * The last time, in ISO 8601 format, that a video was featured.
     */
    val lastVideoFeaturedTime: Date? = null,

    /**
     * The URL to access the category in a browser.
     */
    val link: String? = null,

    /**
     * Metadata about the category.
     */
    override val metadata: Metadata<CategoryConnections, CategoryInteractions>? = null,

    /**
     * The display name that identifies the category.
     */
    val name: String? = null,

    /**
     * The container of this category's parent category, if the current category is
     * a subcategory.
     */
    val parent: Category? = null,

    /**
     * The active picture for this category; defaults to vertical color bars.
     */
    val pictures: PictureCollection? = null,

    /**
     * The resource key of the category.
     */
    val resourceKey: String? = null,

    /**
     * All the subcategories that belong to this category, if the current category is
     * a top-level parent.
     */
    val subcategories: List<Category>? = null,

    /**
     * Whether the category is not a subcategory of another category.
     */
    val topLevel: Boolean? = null,

    /**
     * The unique identifier to access the category resource.
     */
    val uri: String? = null

): Followable
