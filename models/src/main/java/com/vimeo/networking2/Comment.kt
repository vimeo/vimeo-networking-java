package com.vimeo.networking2

import com.vimeo.networking2.enums.CommentType
import java.util.*

data class Comment(

    /**
     * The time in ISO 8601 format when the comment was posted.
     */
    val createdOn: Date?,

    /**
     * Metadata for comments.
     */
    val metadata: CommentMetadata?,

    /**
     * The resource key string for the comment.
     */
    val resourceKey: String?,

    /**
     * The content of the comment.
     */
    val text : String?,

    /**
     * The Vimeo content to which the comment relates.
     */
    val type: CommentType?,

    /**
     * The user who posted the comment.
     */
    val user: User?

)
