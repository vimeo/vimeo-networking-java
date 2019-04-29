package com.vimeo.networking2.enums

/**
 * Type of comment.
 */
enum class CommentType(override val value: String?) : StringValue {

    /**
     * The comment is on a video.
     */
    VIDEO("video"),

    /**
     * Unknown comment type.
     */
    UNKNOWN(null)

}
