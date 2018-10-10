package com.vimeo.networking2

import java.util.*

/**
 * This class represents an authenticated account with Vimeo. It can be through client credentials
 * or a truly authenticated [User].
 */
data class VimeoAccount(

    /**
     * The access token string.
     */
    val accessToken: String? = null,

    /**
     * The date and time that the token expires.
     */
    val expiresOn: Date? = null,

    /**
     * The refresh token string.
     */
    val refreshToken: String? = null,

    /**
     * The scope or scopes that the token supports.
     */
    val scope: String? = null,

    /**
     * The token type.
     */
    val tokenType: String? = null

)
