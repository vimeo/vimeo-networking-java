package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * All connections for a video review.
 *
 * Requires [CapabilitiesType.CAPABILITY_VIDEO_REVIEW].
 */
@JsonClass(generateAdapter = true)
data class VideoReviewConnection(

    /**
     * The time codes associated with all closed notes.
     */
    @Json(name = "closed_time_codes")
    val closedTimeCodes: List<String>? = null,

    /**
     * The time codes associated with all open notes.
     */
    @Json(name = "open_time_codes")
    val openTimeCodes: List<String>? = null,

    /**
     * The total number of notes on this connection.
     */
    @Json(name = "total")
    val total: Int? = null,

    /**
     * The API URI that resolves to the connection data.
     */
    @Json(name = "uri")
    val uri: String? = null

)
