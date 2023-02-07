package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Featured content data.
 *
 * @param video Featured video.
 * @param liveEvent Featured live event.
 */
@JsonClass(generateAdapter = true)
data class FeaturedContent(

    @Json(name = "video")
    val video: Video?,

    @Json(name = "live_event")
    val liveEvent: LiveEvent?,
)
