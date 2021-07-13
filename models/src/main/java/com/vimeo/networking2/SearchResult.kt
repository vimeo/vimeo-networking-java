@file:JvmName("SearchResultUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.enums.SearchType
import com.vimeo.networking2.enums.asEnum

/**
 * Search Result DTO.
 *
 * @param channel Channel data.
 * @param blog Blog.
 * @param group Group data.
 * @param video Video data.
 * @param isFeatured Is this video a featured result?
 * @param isSpatial Is this On Demand a 360 video?
 * @param isStaffPick Is this video a Staff Pick?
 * @param user User data.
 * @param rawType The type of object that this search result is representing. See [SearchResult.type].
 */
@JsonClass(generateAdapter = true)
data class SearchResult(

    @Json(name = "channel")
    val channel: Channel? = null,

    @Json(name = "blog")
    val blog: String? = null,

    @Json(name = "group")
    val group: Group? = null,

    @Json(name = "clip")
    val video: Video? = null,

    @Json(name = "is_featured")
    val isFeatured: Boolean? = null,

    @Json(name = "is_spatial")
    val isSpatial: Boolean? = null,

    @Json(name = "is_staffpick")
    val isStaffPick: Boolean? = null,

    @Json(name = "people")
    val user: User? = null,

    @Json(name = "type")
    val rawType: String? = null

)

/**
 * @see SearchResult.rawType
 * @see SearchType
 */
val SearchResult.type: SearchType
    get() = rawType.asEnum(SearchType.UNKNOWN)
