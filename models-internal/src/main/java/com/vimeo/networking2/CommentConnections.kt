package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * All connections for a comment.
 */
@JsonClass(generateAdapter = true)
data class CommentConnections(

    /**
     * Information about this comment's replies.
     */
    @Json(name = "replies")
    val replies: Connection? = null

)
