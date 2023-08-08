package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Chapter data.
 *
 * @param uri The relative URI of the chapter.
 * @param title The title of the chapter.
 * @param timeCode The timecode of the chapter in seconds from the start of the video.
 */
@JsonClass(generateAdapter = true)
data class Chapter(

    @Json(name = "uri")
    val uri: String? = null,

    @Json(name = "title")
    val title: String? = null,

    @Json(name = "timecode")
    val timeCode: Long? = null
)
