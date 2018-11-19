@file:JvmName("CommentUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.common.Entity
import com.vimeo.networking2.enums.CommentType
import com.vimeo.networking2.enums.asEnum
import java.util.*

/**
 * Comment data.
 */
@JsonClass(generateAdapter = true)
data class Comment(

    /**
     * The time in ISO 8601 format when the comment was posted.
     */
    @Json(name = "created_on")
    val createdOn: Date? = null,

    /**
     * Metadata for comments.
     */
    @Json(name = "metadata")
    val metadata: MetadataConnections<CommentConnections>? = null,

    /**
     * The resource key string for the comment.
     */
    @Json(name = "resource_key")
    val resourceKey: String? = null,

    /**
     * The content of the comment.
     */
    @Json(name = "text")
    val text: String? = null,

    /**
     * The Vimeo content to which the comment relates.
     * @see Comment.type
     */
    @Json(name = "type")
    val rawType: String? = null,

    /**
     * The user who posted the comment.
     */
    @Json(name = "user")
    val user: User? = null

) : Entity {

    override val identifier: String? = resourceKey

}

/**
 * @see Comment.rawType
 * @see CommentType
 */
val Comment.type: CommentType
    get() = rawType.asEnum(CommentType.UNKNOWN)
