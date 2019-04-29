package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * A model representing pin code information to be used in association with authorization.
 */
@JsonClass(generateAdapter = true)
data class PinCodeInfo(

    /**
     * The activation URL.
     */
    @Json(name = "activate_link")
    val activateLink: String? = null,

    /**
     * The authorization URL.
     */
    @Json(name = "authorize_link")
    val authorizeLink: String? = null,

    /**
     * The device code string.
     */
    @Json(name = "device_code")
    val deviceCode: String? = null,

    /**
     * The remaining time in seconds before the device code expires.
     */
    @Json(name = "expires_in")
    val expiresIn: Int? = null,

    /**
     * The interval.
     */
    @Json(name = "interval")
    val interval: Int? = null,

    /**
     * The user code.
     */
    @Json(name = "user_code")
    val userCode: String? = null
)
