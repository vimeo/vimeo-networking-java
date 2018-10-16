package com.vimeo.networking2

/**
 * An object returned from the configs endpoint. This is a way for the
 * api to specify configuration for our application.
 */
data class AppConfiguration(

    /**
     * API configuration data.
     */
    val api: ApiConfiguration? = null,

    /**
     * Facebook API configuration data.
     *
     * Requires [CapabilitiesType.CAPABILITY_PLATFORM_CONFIGS_OTA_FACEBOOK].
     */
    val facebook: FacebookConfiguration? = null,

    /**
     * Various feature configuration data.
     *
     * Requires [CapabilitiesType.CAPABILITY_PLATFORM_CONFIGS_OTA_FEATURES].
     */
    val features: FeaturesConfiguration? = null,

    /**
     * Various livestreaming configuration data.
     *
     * Requires [CapabilitiesType.CAPABILITY_PLATFORM_CONFIGS_OTA_LIVE].
     */
    val live: LiveConfiguration? = null

)
