package com.vimeo.networking2

/**
 * Various feature configuration data.
 *
 * Requires [CapabilitiesType.CAPABILITY_PLATFORM_CONFIGS_OTA_FEATURES].
 */
data class FeaturesConfiguration(

    /**
     * The Chromecast receiver app ID.
     */
    val chromecastAppId: String? = null,

    /**
     * Is Comscore enabled?
     */
    val comscore: Boolean? = null,

    /**
     * Does the user reside within a GDPR-compliant country?
     */
    val gdprEnabled: Boolean? = null,

    /**
     * Is play tracking enabled?
     */
    val playTracking: Boolean? = null

)
