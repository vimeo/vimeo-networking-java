package com.vimeo.networking2.enums

/**
 * The task status described in a video's note.
 */
enum class VideoReviewTaskStatusType(override val value: String?) : StringValue {

    /**
     * The task is closed.
     */
    CLOSED("closed"),

    /**
     * The task is open.
     */
    OPEN("open"),

    /**
     * Unknown status for the video review task.
     */
    UNKNOWN(null)
}
