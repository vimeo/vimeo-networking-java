package com.vimeo.networking2.common

import com.vimeo.networking2.Paging

/**
 * All lists that can be paged should implement this interface to provide
 * paging urls, total number of items returns for each page and the data for each page.
 */
interface Pageable<Data_T> : Page<Data_T> {

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
}
