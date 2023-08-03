package com.vimeo.networking2.richtext

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * [RichText] node model that that contains other nodes.
 *
 * @param type The [RichTextType] of this node.
 * @param content The [RichText] nodes this node contains.
 */
@JsonClass(generateAdapter = true)
data class RichTextContainer(
    @Json(name = "type")
    override val type: RichTextType? = null,

    @Json(name = "content")
    val content: List<RichText>? = null,
) : RichText
