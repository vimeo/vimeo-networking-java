package com.vimeo.networking2

/**
 * List of [TVodItem] that could be paged.
 */
data class TVoidItemList(

    /**
     * Total number of [TVodItem].
     */
    override val total: Int? = null,

    /**
     * The current page number of paging list.
     */
    override val page: Int? = null,

    /**
     * The number of [TVodItem] to returns per page.
     */
    override val perPage: Int? = null,

    /**
     * Urls to the first, last page, next and previous pages.
     */
    override val paging: Paging? = null,

    /**
     * List of [TVodItem].
     */
    override val data: List<TVodItem>?

) : Pageable<TVodItem>
