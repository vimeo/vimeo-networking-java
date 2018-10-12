package com.vimeo.networking2
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * An object returned from the configs endpoint. This is a way for the
 * api to specify configuration for our application.
 */
@JsonClass(generateAdapter = true)
data class AppConfiguration(

    /**
     * API configuration data.
     */
    @Json(name = "api")
    val api: ApiConfiguration? = null,

    /**
     * Facebook API configuration data.
     *
     * Requires [CapabilitiesType.CAPABILITY_PLATFORM_CONFIGS_OTA_FACEBOOK].
     */
    @Json(name = "facebook")
    val facebook: FacebookConfiguration? = null,

    /**
     * Various feature configuration data.
     *
     * Requires [CapabilitiesType.CAPABILITY_PLATFORM_CONFIGS_OTA_FEATURES].
     */
    @Json(name = "features")
    val features: FeaturesConfiguration? = null,

    /**
     * Various livestreaming configuration data.
     *
     * Requires [CapabilitiesType.CAPABILITY_PLATFORM_CONFIGS_OTA_LIVE].
     */
    @Json(name = "live")
    val live: LiveConfiguration? = null

)
