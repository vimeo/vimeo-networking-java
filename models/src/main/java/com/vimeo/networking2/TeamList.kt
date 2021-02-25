package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.common.Page

/**
 * Non-paginated list of the logged in user's [Teams][Team].
 */
@JsonClass(generateAdapter = true)
data class TeamList(

    @Json(name = "total")
    override val total: Int? = null,

    @Json(name = "data")
    override val data: List<Team>? = null,

    @Json(name = "filtered_total")
    override val filteredTotal: Int? = null

) : Page<Team>
