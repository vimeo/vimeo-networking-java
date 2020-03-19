package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

/**
 * Basic authentication for client credentials. It contains an access token
 * that you could use to make unauthenticated requests.
 */
@JsonClass(generateAdapter = true)
data class BasicAccessToken(

    @Json(name = "access_token")
    override val accessToken: String

) : AccessTokenProvider, Serializable {
    companion object {
        private const val serialVersionUID = -29177L
    }
}
