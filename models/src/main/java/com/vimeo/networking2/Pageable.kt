package com.vimeo.networking2

/**
 * All lists that can be paged should implement this interface to provide
 * paging urls, total number of items returns for each page and the data for each page.
 */
interface Pageable<DATA_T> {

    /**
     * Total number of items returned.
     */
    val total: Int?

    /**
     * The current page number.
     */
    val page: Int?

    /**
     * The total number of items to return for each page.
     */
    val perPage: Int?

    /**
     * Urls to the first, last page, next and previous pages.
     */
    val paging: Paging?

    /**
     * The data return for each page.
     */
    val data: List<DATA_T>?
}
