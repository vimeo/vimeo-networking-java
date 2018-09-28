package com.vimeo.networking2

/**
 * Based on CAPABILITY_LIVE_EVENT.
 */
data class StreamingError(

    /**
     * The error message that developers receive.
     */
    val developerMessage: String?,

    /**
     * The error message that non-developer users receive.
     */
    val error: String?,

    /**
     * The error code.
     */
    val errorCode: Int?,

    /**
     * A link to more information about the error.
     */
    val link: String?

)
