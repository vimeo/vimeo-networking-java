package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Search Facet.
 *
 * @param name Option name.
 * @param options Search options.
 */
@JsonClass(generateAdapter = true)
data class SearchFacet(

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "options")
    val options: List<FacetOption>? = null
)
