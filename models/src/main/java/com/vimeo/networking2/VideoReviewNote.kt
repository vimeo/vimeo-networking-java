@file:JvmName("VideoReviewNoteUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.enums.VideoReviewTaskStatusType
import com.vimeo.networking2.enums.asEnum
import java.util.*

/**
 * Video review note data.
 */
@JsonClass(generateAdapter = true)
data class VideoReviewNote(

    /**
     * Coordinates related to this note.
     */
    @Json(name = "coordinates")
    val coordinates: Coordinates? = null,

    /**
     * The time the note was created in ISO 8601 format.
     */
    @Json(name = "created_time")
    val createdTime: Date? = null,

    /**
     * The note's metadata.
     */
    @Json(name = "metadata")
    val metadata: MetadataConnections<VideoReviewNoteConnection>? = null,

    /**
     * The time the note was last modified in ISO 8601 format.
     */
    @Json(name = "modified_time")
    val modifiedTime: Date? = null,

    /**
     * Note resource key.
     */
    @Json(name = "resource_key")
    val resourceKey: String? = null,

    /**
     * Whether the task described in this note has been completed or not.
     */
    @Json(name = "status")
    val status: String? = null,

    /**
     * The content of the note.
     */
    @Json(name = "text")
    val text: String? = null,

    /**
     * The time, to the thousandth of a second, related to this note.
     */
    @Json(name = "time_code")
    val timeCode: Double? = null,

    /**
     * The unique identifier you use to access the note resource.
     */
    @Json(name = "uri")
    val uri: String? = null,

    /**
     * The user who added the note. Null if it was not a logged in user.
     */
    @Json(name = "user")
    val user: User? = null,

    /**
     * The name of the person who added the note.
     */
    @Json(name = "user_name")
    val userName: String? = null

)

/**
 * @see VideoReviewNote.status
 */
val VideoReviewNote.statusType: VideoReviewTaskStatusType
    get() = status.asEnum(VideoReviewTaskStatusType.UNKNOWN)
