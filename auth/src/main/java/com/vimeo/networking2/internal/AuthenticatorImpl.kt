package com.vimeo.networking2.internal

import com.vimeo.networking2.*
import com.vimeo.networking2.enums.AuthParam

/**
 * Authentication with email, google, facebook or pincode.
 *
 * @param authService           Retrofit service for authentication.
 * @param authHeaders           Client id and client secret header.
 * @param scopes                All the scopes for authentication.
 */
internal class AuthenticatorImpl(
    private val authService: AuthService,
    private val authHeaders: String,
    private val scopes: String
) : Authenticator {

    override fun clientCredentials(authCallback: AuthCallback): VimeoRequest {
        val call = authService.authorizeWithClientCredentialsGrant(
            authHeaders,
            GrantType.CLIENT_CREDENTIALS.value,
            scopes)

        return call.enqueueAuthRequest(authCallback)
    }

    override fun google(token: String, email: String, marketingOptIn: Boolean, authCallback: AuthCallback) =
        socialAuthenticate(token, email, marketingOptIn, authCallback)

    override fun facebook(token: String, email: String, marketingOptIn: Boolean, authCallback: AuthCallback) =
        socialAuthenticate(token, email, marketingOptIn, authCallback)

    /**
     * Performs a Google or Facebook auth request. It will first validate the auth params given the
     * client. If they were invalid values such as a empty string, it will inform the client of the
     * error. Otherwise, an API request is made and the result is returned to the user.
     */
    private fun socialAuthenticate(
        token: String,
        email: String,
        marketingOptIn: Boolean,
        authCallback: AuthCallback
    ): VimeoRequest {

        val params = mapOf(
            AuthParam.FIELD_TOKEN to token,
            AuthParam.FIELD_EMAIL to email,
            AuthParam.FIELD_MARKETING_OPT_IN to marketingOptIn.toString()
        )
        val call = authService.join(authHeaders, params)

        val invalidAuthParams = params.validate()

        return if (invalidAuthParams.isNotEmpty()) {
            val apiError = ApiError(
                "Google authentication error.",
                invalidParameters = invalidAuthParams
            )
            call.enqueueAuthError(apiError, authCallback)
        } else {
            call.enqueueAuthRequest(authCallback)
        }
    }

}
