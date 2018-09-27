package com.vimeo.networking2

import java.util.*

data class Category(

    /**
     * The active icon for the category.
     */
    val icon: PictureCollection?,

    /**
     * The last time, in ISO 8601 format, that a video was featured.
     */
    val lastVideoFeaturedTime: Date?,

    /**
     * The URL to access the category in a browser.
     */
    val link: String?,

    /**
     * Metadata about the category.
     */
    val metadata: CategoryMetadata?,

    /**
     * The display name that identifies the category.
     */
    val name: String?,

    /**
     * The container of this category's parent category, if the current category is
     * a subcategory.
     */
    val parent: Category?,

    /**
     * The active picture for this category; defaults to vertical color bars.
     */
    val pictures: PictureCollection?,

    /**
     * The resource key of the category.
     */
    val resourceKey: String?,

    /**
     * All the subcategories that belong to this category, if the current category is
     * a top-level parent.
     */
    val subcategories: List<Category>?,

    /**
     * Whether the category is not a subcategory of another category.
     */
    val topLevel: Boolean?,

    /**
     * The unique identifier to access the category resource.
     */
    val uri: String?

)
