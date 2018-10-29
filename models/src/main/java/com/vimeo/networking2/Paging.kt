package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Paging urls for next, previous, fist and last pages.
 */
@JsonClass(generateAdapter = true)
data class Paging(

    /**
     * Next page's url.
     */
    @Json(name = "next")
    val next: String? = null,

    /**
     * Previous page's url.
     */
    @Json(name = "previous")
    val previous: String? = null,

    /**
     * First page's url.
     */
    @Json(name = "first")
    val first: String? = null,

    /**
     * Last page's url.
     */
    @Json(name = "last")
    val last: String? = null

)
