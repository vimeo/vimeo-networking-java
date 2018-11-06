@file:JvmName("SearchResultUtils")

package com.vimeo.networking2

import com.vimeo.networking2.enums.SearchType
import com.vimeo.networking2.enums.asEnum

/**
 * Search Result DTO.
 */
data class SearchResult(

    /**
     * Channel data.
     */
    val channel: Channel? = null,

    /**
     * Blog.
     */
    val blog: String? = null,

    /**
     * Group data.
     */
    val group: Group? = null,

    /**
     * Video data.
     */
    val video: Video? = null,

    /**
     * Is this video a featured result?
     */
    val isFeatured: Boolean? = null,

    /**
     * Is this On Demand a 360 video?
     */
    val isSpatial: Boolean? = null,

    /**
     * Is this video a Staff Pick?
     */
    val isStaffPick: Boolean? = null,

    /**
     * User data.
     */
    val user: User? = null,

    /**
     * The type of object that this search result is representing.
     */
    val type: String? = null

)

/**
 * @see SearchResult.type
 */
val SearchResult.searchType: SearchType
    get() = type.asEnum(SearchType.UNKNOWN)
