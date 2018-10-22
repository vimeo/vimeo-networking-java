package com.vimeo.networking2

/**
 * Paging urls for next, previous, fist and last pages.
 */
data class Paging(

    /**
     * Next page's url.
     */
    val next: String? = null,

    /**
     * Previous page's url.
     */
    val previous: String? = null,

    /**
     * First page's url.
     */
    val first: String? = null,

    /**
     * Last page's url.
     */
    val last: String? = null

)
