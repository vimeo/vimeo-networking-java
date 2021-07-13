package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Facet data.
 *
 * @param name Option name.
 * @param text Option text.
 * @param total Option total.
 */
@JsonClass(generateAdapter = true)
data class FacetOption(

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "text")
    val text: String? = null,

    @Json(name = "total")
    val total: Int? = null
)
