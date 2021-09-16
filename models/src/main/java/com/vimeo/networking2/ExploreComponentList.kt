package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.common.Page

/**
 * Class representing a non pageable singular list of explore components
 *
 * @param total the total number of items
 * @param filteredTotal the number items after filtering
 * @param data the actual list of components
 */
@JsonClass(generateAdapter = true)
data class ExploreComponentList(
    @Json(name = "total")
    override val total: Int? = null,
    @Json(name = "filtered_total")
    override val filteredTotal: Int? = null,
    @Json(name = "data")
    override val data: List<ExploreComponent>? = null
) : Page<ExploreComponent>