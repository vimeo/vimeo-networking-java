package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

import java.util.*

/**
 * This class represents an authenticated account with Vimeo. It can be through client credentials
 * or a truly authenticated [User].
 */
@JsonClass(generateAdapter = true)
data class VimeoAccount(

    /**
     * The access token string.
     */
    @Json(name = "access_token")
    val accessToken: String? = null,

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
    val user: User? = null,

    /**
     * The token type.
     */
    @Json(name = "token_type")
    val tokenType: String? = null

)
