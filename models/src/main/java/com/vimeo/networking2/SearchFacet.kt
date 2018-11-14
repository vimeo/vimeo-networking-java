package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Search Facet.
 */
@JsonClass(generateAdapter = true)
data class SearchFacet(

    /**
     * Option name
     */
    @Json(name = "name")
    val name: String? = null,

    /**
     * Search options.
     */
    @Json(name = "options")
    val options: List<FacetOption>? = null
)
