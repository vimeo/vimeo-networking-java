package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * A model representing pin code information to be used in association with authorization.
 *
 * @param activateLink The activation URL.
 * @param authorizeLink The authorization URL.
 * @param deviceCode The device code string.
 * @param expiresIn The remaining time in seconds before the device code expires.
 * @param interval The interval.
 * @param userCode The user code.
 */
@JsonClass(generateAdapter = true)
data class PinCodeInfo(

    @Json(name = "activate_link")
    val activateLink: String? = null,

    @Json(name = "authorize_link")
    val authorizeLink: String? = null,

    @Json(name = "device_code")
    val deviceCode: String? = null,

    @Json(name = "expires_in")
    val expiresIn: Int? = null,

    @Json(name = "interval")
    val interval: Int? = null,

    @Json(name = "user_code")
    val userCode: String? = null
)
