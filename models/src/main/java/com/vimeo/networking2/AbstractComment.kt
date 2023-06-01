package com.vimeo.networking2

import com.vimeo.networking2.common.Entity
import java.util.Date

/**
 * Sealed interface for comment/note entities.
 */
sealed interface AbstractComment : Entity {

    /**
     * The URI of this comment.
     */
    val uri: String?

    /**
     * The time in ISO 8601 format when the comment was posted.
     */
    val createdOn: Date?

    /**
     * The resource key string for the comment.
     */
    val resourceKey: String?

    /**
     * The content of the comment.
     */
    val text: String?

    /**
     * The user who posted the comment.
     */
    val user: User?

    override val identifier: String? get() = resourceKey
}
