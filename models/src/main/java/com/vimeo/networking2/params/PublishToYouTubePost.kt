package com.vimeo.networking2.params

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Represents the required data for a YouTube post.
 *
 * @param title The title of the video as it will appear on YouTube.
 * @param description An optional description of the video as it will appear on YouTube.
 * @param tags An optional list of tags for the video on YouTube.
 * @param privacy The privacy option for this video on YouTube.
 * @param categoryId The YouTube category of the video.
 */
@JsonClass(generateAdapter = true)
data class PublishToYouTubePost(

    @Json(name = "title")
    val title: String,

    @Json(name = "description")
    val description: String? = null,

    @Json(name = "tags")
    val tags: List<String>? = null,

    @Json(name = "privacy")
    val privacy: PublishToYouTubePrivacyType,

    @Json(name = "category_id")
    val categoryId: String? = null
)
