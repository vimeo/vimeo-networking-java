/*
 * Copyright (c) 2020 Vimeo (https://vimeo.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.vimeo.networking2.internal

import com.vimeo.networking2.*

/**
 * Authentication with email, google, facebook or pincode.
 *
 * @param authService Retrofit service for authentication.
 * @param authHeader Client id and client secret header.
 * @param scopes All the scopes for authentication.
 */
internal class AuthenticatorImpl(
    private val authService: AuthService,
    private val authHeader: String,
    private val scopes: Scopes
) : Authenticator {

    override fun clientCredentials(authCallback: VimeoCallback<BasicAccessToken>): VimeoRequest {
        val params = mapOf(
            AuthParam.FIELD_GRANT_TYPE to GrantType.CLIENT_CREDENTIALS.value,
            AuthParam.FIELD_SCOPES to scopes
        )
        val call = authService.authorizeWithClientCredentialsGrant(
            authorization = authHeader,
            grantType = GrantType.CLIENT_CREDENTIALS,
            scope = scopes
        )

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
        authCallback: VimeoCallback<VimeoAccount>
    ): VimeoRequest {
        val params = mapOf(
            AuthParam.FIELD_ID_TOKEN to token,
            AuthParam.FIELD_EMAIL to email,
            AuthParam.FIELD_MARKETING_OPT_IN to marketingOptIn.toString(),
            AuthParam.FIELD_SCOPES to scopes
        )
        val call = authService.joinWithGoogle(
            authorization = authHeader,
            email = email,
            idToken = token,
            scope = scopes,
            params = mapOf(AuthParam.FIELD_MARKETING_OPT_IN to marketingOptIn.toString())
        )

        val invalidAuthParams = params.validate()

        return if (invalidAuthParams.isNotEmpty()) {
            val apiError = ApiError(
                errorMessage = "Google authentication failure",
                invalidParameters = invalidAuthParams
            )
            call.enqueueError(apiError, authCallback)
        } else {
            call.enqueue(authCallback)
        }
    }

    override fun facebook(
        token: String,
        email: String,
        marketingOptIn: Boolean,
        authCallback: VimeoCallback<VimeoAccount>
    ): VimeoRequest {
        val params = mapOf(
            AuthParam.FIELD_TOKEN to token,
            AuthParam.FIELD_EMAIL to email,
            AuthParam.FIELD_MARKETING_OPT_IN to marketingOptIn.toString(),
            AuthParam.FIELD_SCOPES to scopes
        )
        val call = authService.joinWithFacebook(
            authorization = authHeader,
            email = email,
            token = token,
            scope = scopes,
            params = mapOf(AuthParam.FIELD_MARKETING_OPT_IN to marketingOptIn.toString())
        )

        val invalidAuthParams = params.validate()

        return if (invalidAuthParams.isNotEmpty()) {
            val apiError = ApiError(
                errorMessage = "Facebook authentication failure",
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
        authCallback: VimeoCallback<VimeoAccount>
    ): VimeoRequest {
        val params = mapOf(
            AuthParam.FIELD_NAME to displayName,
            AuthParam.FIELD_EMAIL to email,
            AuthParam.FIELD_PASSWORD to password,
            AuthParam.FIELD_MARKETING_OPT_IN to marketingOptIn.toString(),
            AuthParam.FIELD_SCOPES to scopes
        )
        val call = authService.joinWithEmail(
            authorization = authHeader,
            name = displayName,
            email = email,
            password = password,
            scope = scopes,
            params = mapOf(AuthParam.FIELD_MARKETING_OPT_IN to marketingOptIn.toString())
        )

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
        authCallback: VimeoCallback<VimeoAccount>
    ): VimeoRequest {

        val params = mapOf(
            AuthParam.FIELD_USERNAME to email,
            AuthParam.FIELD_PASSWORD to password,
            AuthParam.FIELD_SCOPES to scopes
        )
        val call = authService.logInWithEmail(
            authorization = authHeader,
            email = email,
            password = password,
            grantType = GrantType.PASSWORD,
            scope = scopes
        )

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

}
