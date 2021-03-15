package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.common.Pageable

/**
 * List of [TeamMemberships][TeamMembership] that can be paged.
 */
@JsonClass(generateAdapter = true)
data class TeamMembershipList(
    @Json(name = "total")
    override val total: Int? = null,
    @Json(name = "page")
    override val page: Int? = null,
    @Json(name = "per_page")
    override val perPage: Int? = null,
    @Json(name = "paging")
    override val paging: Paging? = null,
    @Json(name = "data")
    override val data: List<TeamMembership>? = null,
    @Json(name = "filtered_total")
    override val filteredTotal: Int? = null
) : Pageable<TeamMembership>
