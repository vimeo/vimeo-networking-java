package com.vimeo.networking2

/**
 * List of products that could be paged.
 */
data class ProductList(

    /**
     * Total number of products.
     */
    override val total: Int? = null,

    /**
     * The current page number of paging list.
     */
    override val page: Int? = null,

    /**
     * The number of albums to returns per page.
     */
    override val perPage: Int? = null,

    /**
     * Urls to the first, last page, next and previous pages.
     */
    override val paging: Paging? = null,

    /**
     * List of products.
     */
    override val data: List<Product>?

) : Pageable<Product>
