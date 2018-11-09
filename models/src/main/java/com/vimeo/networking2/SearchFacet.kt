package com.vimeo.networking2

import com.squareup.moshi.Json

/**
 * Search Facet.
 */
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
