package com.vimeo.networking2

/**
 * Requires [CapabilitiesType.CAPABILITY_LIVE_EVENT].
 */
data class StreamingError(

    /**
     * The error message that developers receive.
     */
    val developerMessage: String? = null,

    /**
     * The error message that non-developer users receive.
     */
    val error: String? = null,

    /**
     * The error code.
     */
    val errorCode: Int? = null,

    /**
     * A link to more information about the error.
     */
    val link: String? = null

)
