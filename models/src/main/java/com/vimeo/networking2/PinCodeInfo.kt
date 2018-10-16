package com.vimeo.networking2

/**
 * A model representing pin code information to be used in association with authorization.
 */
data class PinCodeInfo(

    /**
     * The activation URL.
     */
    val activateLink: String? = null,

    /**
     * The authorization URL.
     */
    val authorizeLink: String? = null,

    /**
     * The device code string.
     */
    val deviceCode: String? = null,

    /**
     * The remaining time in seconds before the device code expires.
     */
    val expiresIn: Int? = null,

    /**
     * The interval.
     */
    val interval: Int? = null,

    /**
     * The user code.
     */
    val userCode: String? = null
)
