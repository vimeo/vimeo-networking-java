package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Featured content data.
 *
 * @param video Featured video.
 */
@JsonClass(generateAdapter = true)
data class FeaturedContent(

    @Json(name = "video")
    val video: Video?,

)
