@file:JvmName("SearchResultUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.enums.SearchType
import com.vimeo.networking2.enums.asEnum

/**
 * Search Result DTO.
 */
@JsonClass(generateAdapter = true)
data class SearchResult(

    /**
     * Channel data.
     */
    @Json(name = "channel")
    val channel: Channel? = null,

    /**
     * Blog.
     */
    @Json(name = "blog")
    val blog: String? = null,

    /**
     * Group data.
     */
    @Json(name = "group")
    val group: Group? = null,

    /**
     * Video data.
     */
    @Json(name = "video")
    val video: Video? = null,

    /**
     * Is this video a featured result?
     */
    @Json(name = "is_featured")
    val isFeatured: Boolean? = null,

    /**
     * Is this On Demand a 360 video?
     */
    @Json(name = "is_spatial")
    val isSpatial: Boolean? = null,

    /**
     * Is this video a Staff Pick?
     */
    @Json(name = "is_staffpick")
    val isStaffPick: Boolean? = null,

    /**
     * User data.
     */
    @Json(name = "people")
    val user: User? = null,

    /**
     * The type of object that this search result is representing.
     * @see SearchResult.type
     */
    @Json(name = "type")
    val rawType: String? = null

)

/**
 * @see SearchResult.rawType
 * @see SearchType
 */
val SearchResult.type: SearchType
    get() = rawType.asEnum(SearchType.UNKNOWN)
