package com.vimeo.networking2

/**
 * All type of authentication flows will provide an access token.
 */
interface AccessTokenProvider {

    /**
     * Access token for making requests.
     */
    val accessToken: String?

}
