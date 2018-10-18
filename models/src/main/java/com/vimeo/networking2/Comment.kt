package com.vimeo.networking2

import com.vimeo.networking2.enums.CommentType
import com.vimeo.networking2.enums.CommentType.UNKNOWN
import java.util.*

/**
 * Comment data.
 */
data class Comment(

    /**
     * The time in ISO 8601 format when the comment was posted.
     */
    val createdOn: Date? = null,

    /**
     * Metadata for comments.
     */
    val metadata: MetadataConnections<CommentConnections>? = null,

    /**
     * The resource key string for the comment.
     */
    val resourceKey: String? = null,

    /**
     * The content of the comment.
     */
    val text : String? = null,

    /**
     * The Vimeo content to which the comment relates.
     */
    val type: CommentType = UNKNOWN,

    /**
     * The user who posted the comment.
     */
    val user: User? = null

): Entity {

    override val identifier: String? = resourceKey

}
