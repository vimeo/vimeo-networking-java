package com.vimeo.networking2

/**
 * Facebook client credentials.
 *
 * Requires [CapabilitiesType.CAPABILITY_PLATFORM_CONFIGS_OTA_FACEBOOK].
 */
data class FacebookAuthData(

    /**
     * The client ID for the Facebook API.
     */
    val clientId: String? = null,

    /**
     * The client secret for the Facebook API.
     */
    val clientSecret: String? = null

)
