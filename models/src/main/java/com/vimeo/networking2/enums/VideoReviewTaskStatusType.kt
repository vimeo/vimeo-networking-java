package com.vimeo.networking2.enums

import com.squareup.moshi.Json

/**
 * The task status described in a video's note.
 */
enum class VideoReviewTaskStatusType {

    /**
     * The task is closed.
     */
    @Json(name = "closed")
    CLOSED,

    /**
     * The task is open.
     */
    @Json(name = "open")
    OPEN,

    /**
     * Unknown status for the video review task.
     */
    UNKNOWN
}
