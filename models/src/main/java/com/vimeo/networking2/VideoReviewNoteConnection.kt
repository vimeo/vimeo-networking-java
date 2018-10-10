package com.vimeo.networking2

/**
 * Video review note connections.
 */
data class VideoReviewNoteConnection(

    /**
     * A standard connection object indicating how to get all replies to this note.
     * This connection only exists if this is a top level note, and has replies.
     */
    val replies: Connection? = null

)
