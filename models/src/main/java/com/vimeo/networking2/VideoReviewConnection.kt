package com.vimeo.networking2

/**
 * Based on CAPABILITY_VIDEO_REVIEW.
 */
data class VideoReviewConnection(

    /**
     * The time codes associated with all closed notes.
     */
    val closedTimeCodes: List<String>?,

    /**
     * The time codes associated with all open notes.
     */
    val openTimeCodes: List<String>?,

    /**
     * The total number of notes on this connection.
     */
    val total: Int?,

    /**
     * The API URI that resolves to the connection data.
     */
    val uri: String?

)
