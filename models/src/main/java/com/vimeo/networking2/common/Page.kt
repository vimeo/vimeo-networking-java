package com.vimeo.networking2.common


/**
 *  Represents a single non-pageable list of data.
 *
 *  @see [Pageable] for lists of data that support pagination.
 */
interface Page<Data_T> {

    /**
     * Total number of items returned.
     */
    val total: Int?


    /**
     * If a filter is applied in the request this will reflect
     * the total, null if no filters applied.
     * TODO name this better
     */
    val filteredTotal: Int?


    /**
     * The data corresponding to this page.
     */
    val data: List<Data_T>?
}
