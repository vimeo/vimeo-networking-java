package com.vimeo.networking2

/**
 * List of categories that are pageable.
 */
data class CategoryList(
    override val total: Int? = null,
    override val page: Int? = null,
    override val perPage: Int? = null,
    override val paging: Paging? = null,
    override val data: List<Category>? = null
) : Pageable<Category>
