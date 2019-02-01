package com.vimeo.networking2.internal

import com.vimeo.networking2.*

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

    override fun google(params: SocialAuthParams, authCallback: AuthCallback): VimeoRequest =
        socialAuthenticate(params, authCallback)

    override fun facebook(params: SocialAuthParams, authCallback: AuthCallback): VimeoRequest =
        socialAuthenticate(params, authCallback)

    /**
     * Validates the params given the client in [SocialAuthParams]. If they were
     * invalid values provided to them, it will inform the client of the error. Otherwise,
     * an API request is made and result is returned to the user.
     *
     * @param params        Google or Facebook authentication params such as token, email, etc...
     * @param authCallback  Callback to inform you of the result of the API request.
     */
    private fun socialAuthenticate(params: SocialAuthParams, authCallback: AuthCallback): VimeoRequest {
        val invalidAuthParams = params.validate()

        return if (invalidAuthParams.isNotEmpty()) {
            val apiErrorResponse = createApiErrorForInvalidParams(
                "Google authentication error.",
                invalidAuthParams
            )
            authCallback.onApiError(apiErrorResponse)
            NoOpVimeoRequest()

        } else {
            val call = authService.join(authHeaders, params.toMap())
            call.enqueueAuthRequest(authCallback)
        }
    }

}
