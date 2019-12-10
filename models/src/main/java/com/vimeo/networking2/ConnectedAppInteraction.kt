package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.common.Interaction

/**
 * All of the interactions for a connected app.
 */
@JsonClass(generateAdapter = true)
data class ConnectedAppInteraction(

        @Json(name = "options")
        override val options: List<String>? = null,

        @Json(name = "uri")
        override val uri: String? = null,

        /**
         * Whether an app is connected or not.
         */
        @Json(name = "is_connected")
        val isConnected: Boolean? = null,

        /**
         * Provides the lists of scopes that are required for third-party connected app features.
         */
        @Json(name = "all_scopes")
        val allScopes: ConnectedScopes? = null

) : Interaction
