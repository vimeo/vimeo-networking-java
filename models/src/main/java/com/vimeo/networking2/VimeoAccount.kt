@file:JvmName("VimeoAccountUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

import java.util.Date

/**
 * This class represents an authenticated user of Vimeo, either logged in or logged out.
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
     * The authenticated and logged in user.
     */
    @Json(name = "user")
    val user: User? = null,

    /**
     * The token type.
     */
    @Json(name = "token_type")
    val tokenType: String? = null

) : AccessTokenProvider

/**
 * True if the account represents a logged in user, false if it represents a logged out user.
 */
val VimeoAccount.isLoggedIn: Boolean
    get() = user != null
