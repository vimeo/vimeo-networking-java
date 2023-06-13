package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Meta info for ProjectItem.
 *
 * @param captionMatchTimestampMs Timestamp where caption relates to.
 * @param caption Caption text.
 */
@JsonClass(generateAdapter = true)
data class ProjectItemMeta(

    @Json(name = "caption_match_ts")
    val captionMatchTimestampMs: Long?,

    @Json(name = "caption")
    val caption: String?,
)
