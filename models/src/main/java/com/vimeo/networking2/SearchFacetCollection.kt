package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * A grouping of all facets.
 *
 * @param categoryFacet A specific category you want videos for.
 * @param durationFacet How long you want videos to be.
 * @param licenseFacet A specific license you want videos to be.
 * @param typeFacet The type of filter to use to return result.
 * @param uploadedFacet How fresh you want videos.
 * @param userTypeFacet The account level of users you want returned.
 */
@JsonClass(generateAdapter = true)
data class SearchFacetCollection(

    @Json(name = "category")
    val categoryFacet: SearchFacet? = null,

    @Json(name = "duration")
    val durationFacet: SearchFacet? = null,

    @Json(name = "license")
    val licenseFacet: SearchFacet? = null,

    @Json(name = "type")
    val typeFacet: SearchFacet? = null,

    @Json(name = "uploaded")
    val uploadedFacet: SearchFacet? = null,

    @Json(name = "user_type")
    val userTypeFacet: SearchFacet? = null

)
