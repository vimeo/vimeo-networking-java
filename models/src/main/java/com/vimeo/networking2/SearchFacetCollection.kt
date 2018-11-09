package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * A grouping of all facets.
 */
@JsonClass(generateAdapter = true)
data class SearchFacetCollection(

    /**
     * A specific category you want videos for.
     */
    @Json(name = "category")
    val categoryFacet: SearchFacet? = null,

    /**
     * How long you want videos to be.
     */
    @Json(name = "duration")
    val durationFacet: SearchFacet? = null,

    /**
     * A specific license you want videos to be.
     */
    @Json(name = "license")
    val licenseFacet: SearchFacet? = null,

    /**
     * The type of filter to use to return result.
     */
    @Json(name = "type")
    val typeFacet: SearchFacet? = null,

    /**
     * How fresh you want videos.
     */
    @Json(name = "uploaded")
    val uploadedFacet: SearchFacet? = null,

    /**
     * The account level of users you want returned.
     */
    @Json(name = "user_type")
    val userTypeFacet: SearchFacet? = null

)
