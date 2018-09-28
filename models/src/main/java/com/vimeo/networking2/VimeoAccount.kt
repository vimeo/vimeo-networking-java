package com.vimeo.networking2

import java.util.*

data class VimeoAccount(

    /**
     * The access token string.
     */
    val accessToken: String?,

    /**
     * The date and time that the token expires.
     */
    val expiresOn: Date?,

    /**
     * The refresh token string.
     */
    val refreshToken: String?,

    /**
     * The scope or scopes that the token supports.
     */
    val scope: String?,

    /**
     * The token type.
     */
    val tokenType: String?

)
