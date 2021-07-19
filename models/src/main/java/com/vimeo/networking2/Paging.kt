package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Paging urls for next, previous, fist and last pages.
 *
 * @param next Next page's url.
 * @param previous Previous page's url.
 * @param first First page's url.
 * @param last Last page's url.
 */
@JsonClass(generateAdapter = true)
data class Paging(

    @Json(name = "next")
    val next: String? = null,

    @Json(name = "previous")
    val previous: String? = null,

    @Json(name = "first")
    val first: String? = null,

    @Json(name = "last")
    val last: String? = null

)
