package com.vimeo.networking2

/**
 * List of categories that are pageable.
 */
data class CategoryList(

    /**
     * Total number of categories.
     */
    override val total: Int? = null,

    /**
     * Current page number.
     */
    override val page: Int? = null,

    /**
     * Total number of categories per page.
     */
    override val perPage: Int? = null,

    /**
     * Urls to the first, last page, next and previous pages.
     */
    override val paging: Paging? = null,

    /**
     * Categories for each page.
     */
    override val data: List<Category>? = null

): Pageable<Category>
