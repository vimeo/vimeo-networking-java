package com.vimeo.networking2
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

import java.util.*

/**
 * Information on liking a video.
 */
@JsonClass(generateAdapter = true)
data class LikeInteraction(

    /**
     * Whether the user has liked the video.
     */
    @Json(name = "added")
    val added: Boolean? = null,

    /**
     * The time in ISO 8601 format when the user liked the video.
     */
    @Json(name = "added_time")
    val addedTime: Date? = null,

    /**
     * An array of HTTP methods permitted on this URI.
     */
    @Json(name = "options")
    val options: List<String>? = null,

    /**
     * The API URI that resolves to the connection data.
     */
    @Json(name = "uri")
    val uri: String? = null

)
