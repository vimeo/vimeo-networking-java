package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.common.Connection

/**
 * Information about the season the video is associated with.
 *
 * @param name The name of the season.
 */
@JsonClass(generateAdapter = true)
data class VideoSeasonConnection(

    @Json(name = "options")
    override val options: List<String>? = null,

    @Json(name = "uri")
    override val uri: String? = null,

    @Json(name = "name")
    val name: String? = null
) : Connection
