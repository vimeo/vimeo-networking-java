package com.vimeo.networking2

import com.vimeo.networking2.enums.VideoReviewTaskStatusType
import java.util.*

/**
 * Video review note data.
 */
data class VideoReviewNote(

    /**
     * Coordinates related to this note.
     */
    val coordinates: Coordinates? = null,

    /**
     * The time the note was created in ISO 8601 format.
     */
    val createdTime: Date? = null,

    /**
     * The note's metadata.
     */
    val metadata: MetadataConnections<VideoReviewNoteConnection>? = null,

    /**
     * The time the note was last modified in ISO 8601 format.
     */
    val modifiedTime: Date? = null,

    /**
     * Note resource key.
     */
    val resourceKey: String? = null,

    /**
     * Whether the task described in this note has been completed or not.
     */
    val status: VideoReviewTaskStatusType? = null,

    /**
     * The content of the note.
     */
    val text: String? = null,

    /**
     * The time, to the thousandth of a second, related to this note.
     */
    val timeCode: Double? = null,

    /**
     * The unique identifier you use to access the note resource.
     */
    val uri: String? = null,

    /**
     * The user who added the note. Null if it was not a logged in user.
     */
    val user: User? = null,

    /**
     * The name of the person who added the note.
     */
    val userName: String? = null

)
