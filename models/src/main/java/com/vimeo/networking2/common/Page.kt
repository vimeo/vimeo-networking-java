package com.vimeo.networking2.common


/**
 * Represents a single non-pageable list of data.
 *
 * @see [Pageable] for lists of data that support pagination.
 */
interface Page<Data_T> {

    /**
     * The total number of items for the request without taking into account any applied filters.
     */
    val total: Int?


    /**
     * The total number of items in a request taking into account the applied filters.
     */
    val filteredTotal: Int?


    /**
     * The data corresponding to this page.
     */
    val data: List<Data_T>?
}
