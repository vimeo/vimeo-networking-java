package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

/**
 * Provides the lists of scopes that are required for third-party connected app features.
 */
@JsonClass(generateAdapter = true)
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
): Serializable {

    companion object {
        private const val serialVersionUID = -1054080398L
    }
}
