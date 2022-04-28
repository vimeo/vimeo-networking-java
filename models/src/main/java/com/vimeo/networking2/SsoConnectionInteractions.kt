package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.annotations.Internal

/**
 * The interaction for an Single Sign-On connection.
 *
 * @param connect The interaction to connect via Single Sign-On.
 */
@Internal
@JsonClass(generateAdapter = true)
data class SsoConnectionInteractions(

    @Json(name = "connect")
    val connect: BasicInteraction? = null
)
