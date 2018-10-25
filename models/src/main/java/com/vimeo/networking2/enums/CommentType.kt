package com.vimeo.networking2.enums

import com.squareup.moshi.Json

/**
 * Type of comment.
 */
enum class CommentType {

    /**
     * The comment is on a video.
     */
    @Json(name = "video")
    VIDEO,

    /**
     * Unknown comment type.
     */
    UNKNOWN

}
