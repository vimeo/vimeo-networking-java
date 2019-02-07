package com.vimeo.networking2.internal

import com.vimeo.networking2.*

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

    override fun clientCredentials(authCallback: VimeoCallback<BasicAccessToken>): VimeoRequest {
        val params = mapOf(
            AuthParam.FIELD_GRANT_TYPE to GrantType.CLIENT_CREDENTIALS.value,
            AuthParam.FIELD_SCOPES to scopes
        )
        val call = authService.authorizeWithClientCredentialsGrant(authHeaders, params)

        val invalidAuthParams = params.validate()

        return if (invalidAuthParams.isNotEmpty()) {
            val apiError = ApiError(
                "Client credentials authentication error.",
                invalidParameters = invalidAuthParams
            )
            call.enqueueError(apiError, authCallback)
        } else {
            call.enqueue(authCallback)
        }
    }

    override fun google(
        token: String,
        email: String,
        marketingOptIn: Boolean,
        authCallback: VimeoCallback<AuthenticatedAccessToken>
    ) = socialAuthenticate(token, email, marketingOptIn, SocialAuthType.GOOGLE, authCallback)

    override fun facebook(
        token: String,
        email: String,
        marketingOptIn: Boolean,
        authCallback: VimeoCallback<AuthenticatedAccessToken>
    ) = socialAuthenticate(token, email, marketingOptIn, SocialAuthType.FACEBOOK, authCallback)

    /**
     * Performs a Google or Facebook auth request. It will first validate the auth params given the
     * client. If they were invalid values such as a empty string, it will inform the client of the
     * error. Otherwise, an API request is made and the result is returned to the user.
     */
    private fun socialAuthenticate(
        token: String,
        email: String,
        marketingOptIn: Boolean,
        socialAuthType: SocialAuthType,
        authCallback: VimeoCallback<AuthenticatedAccessToken>
    ): VimeoRequest {

        val tokenField = when(socialAuthType) {
            SocialAuthType.GOOGLE -> AuthParam.FIELD_ID_TOKEN
            else -> AuthParam.FIELD_TOKEN
        }

        val errorMessage = when(socialAuthType) {
            SocialAuthType.GOOGLE -> "Google authentication error."
            else -> "Facebook authentication error."
        }

        val params = mapOf(
            tokenField to token,
            AuthParam.FIELD_EMAIL to email,
            AuthParam.FIELD_MARKETING_OPT_IN to marketingOptIn.toString()
        )
        val call = authService.join(authHeaders, params)

        val invalidAuthParams = params.validate()

        return if (invalidAuthParams.isNotEmpty()) {
            val apiError = ApiError(
                errorMessage,
                invalidParameters = invalidAuthParams
            )
            call.enqueueError(apiError, authCallback)
        } else {
            call.enqueue(authCallback)
        }
    }

    override fun emailJoin(
        displayName: String,
        email: String,
        password: String,
        marketingOptIn: Boolean,
        authCallback: VimeoCallback<AuthenticatedAccessToken>
    ): VimeoRequest {

        val params = mapOf(
            AuthParam.FIELD_NAME to displayName,
            AuthParam.FIELD_EMAIL to email,
            AuthParam.FIELD_PASSWORD to password,
            AuthParam.FIELD_MARKETING_OPT_IN to marketingOptIn.toString(),
            AuthParam.FIELD_SCOPES to scopes
        )
        val call = authService.join(authHeaders, params)

        val invalidAuthParams = params.validate()

        return if (invalidAuthParams.isNotEmpty()) {
            val apiError = ApiError(
                "Email join error.",
                invalidParameters = invalidAuthParams
            )
            call.enqueueError(apiError, authCallback)
        } else {
            call.enqueue(authCallback)
        }
    }

    override fun emailLogin(
        email: String,
        password: String,
        authCallback: VimeoCallback<AuthenticatedAccessToken>
    ): VimeoRequest {

        val params = mapOf(
            AuthParam.FIELD_USERNAME to email,
            AuthParam.FIELD_PASSWORD to password,
            AuthParam.FIELD_GRANT_TYPE to GrantType.PASSWORD.value,
            AuthParam.FIELD_SCOPES to scopes
        )
        val call = authService.logIn(authHeaders, params)

        val invalidAuthParams = params.validate()

        return if (invalidAuthParams.isNotEmpty()) {
            val apiError = ApiError(
                "Email login error.",
                invalidParameters = invalidAuthParams
            )
            call.enqueueError(apiError, authCallback)
        } else {
            call.enqueue(authCallback)
        }
    }

    /**
     * Google or Facebook authentication.
     */
    private enum class SocialAuthType {
        FACEBOOK,
        GOOGLE
    }


}
