package com.vimeo.networking2.internal

import com.vimeo.networking2.AuthCallback
import com.vimeo.networking2.Authenticator
import com.vimeo.networking2.GrantType
import com.vimeo.networking2.VimeoRequest

/**
 * Authentication with email, google, facebook or pincode.
 *
 * @param authService   Retrofit service for authentication.
 * @param authHeaders   Client id and client secret header.
 * @param scopes        All the scopes for authentication.
 */
internal class AuthenticatorImpl(
    private val authService: AuthService,
    private val authHeaders: String,
    private val scopes: String
): Authenticator {

    override fun clientCredentials(authCallback: AuthCallback): VimeoRequest {
        val call = authService.authorizeWithClientCredentialsGrant(
            authHeaders,
            GrantType.CLIENT_CREDENTIALS.value,
            scopes)

        return call.enqueueAuthRequest(authCallback)
    }

}
