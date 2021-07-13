package com.vimeo.networking2.params

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Represents the required data for a LinkedIn post.
 *
 * @param pageId The LinkedIn page identifier that the video will be posted to.
 * @param title The title of the post as it will appear on LinkedIn.
 * @param description The description of the post as it will appear on LinkedIn.
 */
@JsonClass(generateAdapter = true)
data class PublishToLinkedInPost(

    @Json(name = "page_id")
    val pageId: Int,

    @Json(name = "title")
    val title: String,

    @Json(name = "description")
    val description: String
)
