package com.vimeo.networking2

/**
 * Authenticate token that you would receive from email, Google or Facebook login. In addition to
 * the token, you get a user object representing the authenticated user.
 */
data class AuthenticatedAccessToken(

    override val accessToken: String,

    /**
     * Authenticated user.
     */
    val user: User

): AccessTokenProvider
