package com.vimeo.networking2.richtext

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * [RichText] node model for simple text.
 *
 * @param text The text this node represents.
 */
@JsonClass(generateAdapter = true)
data class Text(
    @Json(name = "text")
    val text: String? = null
) : RichText {

    @Json(name = "type")
    override val type: RichTextType = RichTextType.TEXT
}
