package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

/**
 * Basic authentication for client credentials. It contains an access token that consumers can use to make
 * unauthenticated requests.
 */
@JsonClass(generateAdapter = true)
data class BasicAccessToken(

    @Json(name = "access_token")
    override val accessToken: String?,

    /**
     * The scope or scopes that the token supports.
     */
    @Json(name = "scope")
    val scope: String? = null,

    /**
     * The token type.
     */
    @Json(name = "token_type")
    val tokenType: String? = null

) : AccessTokenProvider, Serializable {
    companion object {
        private const val serialVersionUID = -29177L
    }
}
