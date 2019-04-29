package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.common.Pageable

/**
 * List of search result that could be paged.
 */
@JsonClass(generateAdapter = true)
data class SearchResultList(

    /**
     * The search facets.
     */
    @Json(name = "facets")
    val facetCollection: SearchFacetCollection? = null,

    /**
     * The number of videos that were hidden from the results due to mature content.
     */
    @Json(name = "mature_hidden_count")
    val matureHiddenCount: Int? = null,

    @Json(name = "total")
    override val total: Int? = null,

    @Json(name = "page")
    override val page: Int? = null,

    @Json(name = "per_page")
    override val perPage: Int? = null,

    @Json(name = "paging")
    override val paging: Paging? = null,

    @Json(name = "data")
    override val data: List<SearchResult>? = null

) : Pageable<SearchResult>
