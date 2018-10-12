package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Facebook client credentials.
 *
 * Requires [CapabilitiesType.CAPABILITY_PLATFORM_CONFIGS_OTA_FACEBOOK].
 */
@JsonClass(generateAdapter = true)
data class FacebookAuthData(

    /**
     * The client ID for the Facebook API.
     */
    @Json(name = "client_id")
    val clientId: String? = null,

    /**
     * The client secret for the Facebook API.
     */
    @Json(name = "client_secret")
    val clientSecret: String? = null

)
