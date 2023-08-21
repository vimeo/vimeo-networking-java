package com.vimeo.networking2.richtext

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * [RichText] node model for mentions.
 *
 * @param attrs The attributes of the mention.
 * @param text The text this node represents.
 */
@JsonClass(generateAdapter = true)
data class Mention(
    @Json(name = "attrs")
    val attrs: MentionAttrs? = null,

    @Json(name = "text")
    val text: String? = null
) : RichText {

    @Json(name = "type")
    override val type: RichTextType = RichTextType.MENTION
}
