package com.vimeo.networking2

/**
 * List of search result that could be paged.
 */
data class SearchResultList(

    /**
     * Total number of search results.
     */
    override val total: Int? = null,

    /**
     * The current page number of paging list.
     */
    override val page: Int? = null,

    /**
     * The number of search results to return per page.
     */
    override val perPage: Int? = null,

    /**
     * Urls to the first, last page, next and previous pages.
     */
    override val paging: Paging? = null,

    /**
     * List of search results.
     */
    override val data: List<SearchResult>?

) : Pageable<SearchResult>
