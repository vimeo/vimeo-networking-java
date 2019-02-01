package com.vimeo.networking2.internal

import com.vimeo.networking2.AuthCallback
import com.vimeo.networking2.Authenticator
import com.vimeo.networking2.GrantType
import com.vimeo.networking2.VimeoRequest
import com.vimeo.networking2.internal.interactors.SocialAuthInteractor
import com.vimeo.networking2.internal.interactors.SocialAuthParams

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

    /**
     * Authenticate using Google.
     */
    override fun google(params: SocialAuthParams, authCallback: AuthCallback): VimeoRequest =
        socialAuthenticate(params, authCallback)

    /**
     * Authenticate using Facebook.
     */
    override fun facebook(params: SocialAuthParams, authCallback: AuthCallback): VimeoRequest =
        socialAuthenticate(params, authCallback)

    /**
     * Google and Facebook authentication have the same authentication flows. Both of them require
     * the client to supply a token and email. For re-usability, both authentication flows use
     * a single interactor.
     */
    private fun socialAuthenticate(params: SocialAuthParams, authCallback: AuthCallback): VimeoRequest {
        val interactor = SocialAuthInteractor(authService, authHeaders)
        return interactor.authenticate(params, authCallback)
    }

}
