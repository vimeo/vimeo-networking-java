@file:JvmName("VimeoAccountUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.Date

/**
 * This class represents an authenticated user of Vimeo, either logged in or logged out.
 *
 * @param expiresOn The date and time that the token expires.
 * @param refreshToken The refresh token string.
 * @param scope The scope or scopes that the token supports.
 * @param user The authenticated and logged in user.
 * @param tokenType The token type.
 */
@JsonClass(generateAdapter = true)
data class VimeoAccount(

    @Json(name = "access_token")
    override val accessToken: String,

    @Json(name = "expires_on")
    val expiresOn: Date? = null,

    @Json(name = "refresh_token")
    val refreshToken: String? = null,

    @Json(name = "scope")
    val scope: String? = null,

    @Json(name = "user")
    val user: User? = null,

    @Json(name = "token_type")
    val tokenType: String? = null

) : AccessTokenProvider

/**
 * True if the account represents a logged in user, false if it represents a logged out user.
 */
val VimeoAccount.isLoggedIn: Boolean
    get() = user != null
