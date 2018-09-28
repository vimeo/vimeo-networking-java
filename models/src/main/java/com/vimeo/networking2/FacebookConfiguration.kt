package com.vimeo.networking2

/**
 * Based on CAPABILITY_PLATFORM_CONFIGS_OTA_FACEBOOK.
 */
data class FacebookConfiguration(

    /**
     * Facebook client id and client secret.
     */
   val auth: FacebookAuthData?,

   /**
    * An array of required scopes for connecting users to Facebook.
    */
   val requiredScopes: List<String>?

)
