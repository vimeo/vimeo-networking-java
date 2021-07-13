package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * All connections for a comment.
 *
 * @param replies Information about this comment's replies.
 */
@JsonClass(generateAdapter = true)
data class CommentConnections(

    @Json(name = "replies")
    val replies: BasicConnection? = null
)
