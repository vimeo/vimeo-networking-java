package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.common.Pageable
import java.io.Serializable

/**
 * List of text tracks for a video.
 */
@JsonClass(generateAdapter = true)
data class TextTrackList(

        @Json(name = "total")
        override val total: Int? = null,

        @Json(name = "page")
        override val page: Int? = null,

        @Json(name = "per_page")
        override val perPage: Int? = null,

        @Json(name = "paging")
        override val paging: Paging? = null,

        @Json(name = "data")
        override val data: List<TextTrack>? = null

) : Pageable<TextTrack>, Serializable {

    companion object {
        private const val serialVersionUID = -56L
    }
}
