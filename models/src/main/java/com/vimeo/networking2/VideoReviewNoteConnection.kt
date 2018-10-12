package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Video review note connections.
 */
@JsonClass(generateAdapter = true)
data class VideoReviewNoteConnection(

    /**
     * A standard connection object indicating how to get all replies to this note.
     * This connection only exists if this is a top level note, and has replies.
     */
    @Json(name = "replies")
    val replies: Connection? = null

)
