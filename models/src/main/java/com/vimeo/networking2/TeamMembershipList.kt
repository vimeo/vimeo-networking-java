package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.common.Page

/**
 * List of [TeamMemberships][TeamMembership] that cannot be paged.
 */
@JsonClass(generateAdapter = true)
data class TeamMembershipList(
    @Json(name = "total")
    override val total: Int? = null,

    @Json(name = "data")
    override val data: List<TeamMembership>? = null,

    @Json(name = "filtered_total")
    override val filteredTotal: Int? = null
) : Page<TeamMembership>
