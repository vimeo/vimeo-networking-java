package com.vimeo.networking2

import com.squareup.moshi.Json

/**
 * Provides the lists of scopes that are required for third-party connected app features.
 */
data class ConnectedScopes(

        /**
         * All scopes required for publishing to a specific social media platform.
         */
        @Json(name = "publish_to_social")
        val publishToSocial: List<String>? = null,

        /**
         * All scopes required for simulcasting to a specific social media platform.
         */
        @Json(name = "simulcast")
        val simulcast: List<String>? = null
)
