package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.enums.asEnum
import java.util.Date

/**
 * Note data.
 *
 * @param uri The URI of this note.
 * @param createdOn The time in ISO 8601 format when the note was posted.
 * @param metadata Metadata for notes.
 * @param resourceKey The resource key string for the note.
 * @param text The content of the note.
 * @param user The user who posted the note.
 * @param rawStatus The status of the note.
 * @param timeCode Time code at which point in the video the note was left.
 * @param coordinates Point on the video surface which note relates to.
 */
@JsonClass(generateAdapter = true)
data class Note(

    @Json(name = "uri")
    override val uri: String? = null,

    @Json(name = "created_time")
    override val createdOn: Date? = null,

    @Json(name = "metadata")
    val metadata: MetadataConnections<CommentConnections>? = null,

    @Json(name = "resource_key")
    override val resourceKey: String? = null,

    @Json(name = "text")
    override val text: String? = null,

    @Json(name = "user")
    override val user: User? = null,

    @Json(name = "status")
    val rawStatus: String? = null,

    @Json(name = "time_code")
    val timeCode: Double? = null,

    @Json(name = "coordinates")
    val coordinates: Coordinates? = null,
) : AbstractComment

/**
 * @see Note.rawStatus
 * @see NoteStatus
 */
val Note.status: NoteStatus
    get() = rawStatus.asEnum(NoteStatus.UNKNOWN)
