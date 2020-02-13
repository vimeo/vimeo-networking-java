package com.vimeo.networking2

import com.squareup.moshi.Json

/**
 * Authenticate token that you would receive from email, Google or Facebook login. In addition to
 * the token, you get a user object representing the authenticated user.
 */
data class AuthenticatedAccessToken(

    @Json(name = "access_token")
    override val accessToken: String,

    /**
     * Authenticated user.
     */
    @Json(name = "user")
    val user: User

) : AccessTokenProvider
