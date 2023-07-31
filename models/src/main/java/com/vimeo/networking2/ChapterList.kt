package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.common.Page

/**
 * List of chapters that are pageable.
 */
@JsonClass(generateAdapter = true)
data class ChapterList(

    @Json(name = "total")
    override val total: Int? = null,

    @Json(name = "filtered_total")
    override val filteredTotal: Int? = null,

    @Json(name = "data")
    override val data: List<Chapter>? = null
) : Page<Chapter>
