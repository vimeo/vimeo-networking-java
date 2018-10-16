package com.vimeo.networking2

/**
 * List of search result that could be paged.
 */
data class SearchResultList(
    override val total: Int? = null,
    override val page: Int? = null,
    override val perPage: Int? = null,
    override val paging: Paging? = null,
    override val data: List<SearchResult>?
) : Pageable<SearchResult>
