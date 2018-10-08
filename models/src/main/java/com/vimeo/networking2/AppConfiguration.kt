package com.vimeo.networking2

data class AppConfiguration(

    /**
     * API configuration data.
     */
    val api: ApiConfiguration? = null,

    /**
     * Facebook API configuration data.
     *
     * Based on CAPABILITY_PLATFORM_CONFIGS_OTA_FACEBOOK.
     */
    val facebook: FacebookConfiguration? = null,

    /**
     * Various feature configuration data.
     *
     * Based on CAPABILITY_PLATFORM_CONFIGS_OTA_FEATURES.
     */
    val features: FeaturesConfiguration? = null,

    /**
     * Various livestreaming configuration data.
     *
     * Based on CAPABILITY_PLATFORM_CONFIGS_OTA_LIVE.
     */
    val live: LiveConfiguration? = null

)
