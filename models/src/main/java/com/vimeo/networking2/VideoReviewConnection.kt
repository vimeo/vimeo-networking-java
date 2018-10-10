package com.vimeo.networking2

/**
 * All connections for a video review.
 *
 * Requires [CapabilitiesType.CAPABILITY_VIDEO_REVIEW].
 */
data class VideoReviewConnection(

    /**
     * The time codes associated with all closed notes.
     */
    val closedTimeCodes: List<String>? = null,

    /**
     * The time codes associated with all open notes.
     */
    val openTimeCodes: List<String>? = null,

    /**
     * The total number of notes on this connection.
     */
    val total: Int? = null,

    /**
     * The API URI that resolves to the connection data.
     */
    val uri: String? = null

)
