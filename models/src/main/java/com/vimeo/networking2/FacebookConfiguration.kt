package com.vimeo.networking2

/**
 * Facebook API configuration data.
 *
 * Requires [CapabilitiesType.CAPABILITY_PLATFORM_CONFIGS_OTA_FACEBOOK].
 */
data class FacebookConfiguration(

    /**
     * Facebook client id and client secret.
     */
   val auth: FacebookAuthData? = null,

   /**
    * An array of required scopes for connecting users to Facebook.
    */
   val requiredScopes: List<String>? = null

)
