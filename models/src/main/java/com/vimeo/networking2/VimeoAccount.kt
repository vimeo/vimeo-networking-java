package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

import java.util.Date

/**
 * This class represents an authenticated user of Vimeo.
 */
@JsonClass(generateAdapter = true)
data class VimeoAccount(

    @Json(name = "access_token")
    override val accessToken: String,

    /**
     * The date and time that the token expires.
     */
    @Json(name = "expires_on")
    val expiresOn: Date? = null,

    /**
     * The refresh token string.
     */
    @Json(name = "refresh_token")
    val refreshToken: String? = null,

    /**
     * The scope or scopes that the token supports.
     */
    @Json(name = "scope")
    val scope: String? = null,

    /**
     * The authenticated user.
     */
    @Json(name = "user")
    val user: User,

    /**
     * The token type.
     */
    @Json(name = "token_type")
    val tokenType: String? = null

) : AccessTokenProvider, Serializable {

    companion object {
        private const val serialVersionUID = -104L
    }
}
