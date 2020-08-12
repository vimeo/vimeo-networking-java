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
import com.vimeo.networking2.account.CachingAccountStore
import com.vimeo.networking2.enums.ErrorCodeType
import okhttp3.HttpUrl

/**
 * Authentication using email, google, facebook, code grant, or pin code.
 *
 * @param authService Retrofit service for authentication.
 * @param basicAuthHeader Client id and client secret header.
 * @param clientId The client id used to generate the code grant URI.
 * @param redirectUri The URI to which the code grant will redirect after successful authentication.
 * @param scopes All the scopes for authentication.
 * @param accountStore The account store used to store and retrieve credentials.
 * @param localVimeoCallAdapter The adapter used to notify consumers of local errors.
 */
internal class AuthenticatorImpl(
    private val authService: AuthService,
    private val basicAuthHeader: String,
    private val clientId: String,
    private val redirectUri: String,
    private val scopes: Scopes,
    private val accountStore: CachingAccountStore,
    private val localVimeoCallAdapter: LocalVimeoCallAdapter
) : Authenticator {

    override val currentAccount: VimeoAccount?
        get() = accountStore.loadAccount()

    override fun clientCredentials(callback: VimeoCallback<VimeoAccount>): VimeoRequest {
        val invalidAuthParams = mapOf(
            AuthParam.FIELD_GRANT_TYPE to GrantType.CLIENT_CREDENTIALS.value,
            AuthParam.FIELD_SCOPES to scopes
        ).validate()

        val call = authService.authorizeWithClientCredentialsGrant(
            authorization = basicAuthHeader,
            grantType = GrantType.CLIENT_CREDENTIALS,
            scopes = scopes
        )

        return if (invalidAuthParams.isNotEmpty()) {
            val apiError = ApiError(
                developerMessage = "Client credentials authentication error.",
                invalidParameters = invalidAuthParams
            )
            call.enqueueError(apiError, callback)
        } else {
            call.enqueue(AccountStoringVimeoCallback(accountStore, callback))
        }
    }

    override fun google(
        token: String,
        email: String,
        marketingOptIn: Boolean,
        callback: VimeoCallback<VimeoAccount>
    ): VimeoRequest {
        val invalidAuthParams = mapOf(
            AuthParam.FIELD_ID_TOKEN to token,
            AuthParam.FIELD_EMAIL to email,
            AuthParam.FIELD_SCOPES to scopes
        ).validate()

        val call = authService.joinWithGoogle(
            authorization = basicAuthHeader,
            email = email,
            idToken = token,
            scopes = scopes,
            marketingOptIn = marketingOptIn
        )

        return if (invalidAuthParams.isNotEmpty()) {
            val apiError = ApiError(
                errorMessage = "Google authentication failure",
                invalidParameters = invalidAuthParams
            )
            call.enqueueError(apiError, callback)
        } else {
            call.enqueue(AccountStoringVimeoCallback(accountStore, callback))
        }
    }

    override fun facebook(
        token: String,
        email: String,
        marketingOptIn: Boolean,
        callback: VimeoCallback<VimeoAccount>
    ): VimeoRequest {
        val invalidAuthParams = mapOf(
            AuthParam.FIELD_TOKEN to token,
            AuthParam.FIELD_EMAIL to email,
            AuthParam.FIELD_SCOPES to scopes
        ).validate()

        val call = authService.joinWithFacebook(
            authorization = basicAuthHeader,
            email = email,
            token = token,
            scopes = scopes,
            marketingOptIn = marketingOptIn
        )

        return if (invalidAuthParams.isNotEmpty()) {
            val apiError = ApiError(
                errorMessage = "Facebook authentication failure",
                invalidParameters = invalidAuthParams
            )
            call.enqueueError(apiError, callback)
        } else {
            call.enqueue(AccountStoringVimeoCallback(accountStore, callback))
        }
    }

    override fun emailJoin(
        displayName: String,
        email: String,
        password: String,
        marketingOptIn: Boolean,
        callback: VimeoCallback<VimeoAccount>
    ): VimeoRequest {
        val invalidAuthParams = mapOf(
            AuthParam.FIELD_NAME to displayName,
            AuthParam.FIELD_EMAIL to email,
            AuthParam.FIELD_PASSWORD to password,
            AuthParam.FIELD_SCOPES to scopes
        ).validate()

        val call = authService.joinWithEmail(
            authorization = basicAuthHeader,
            name = displayName,
            email = email,
            password = password,
            scopes = scopes,
            marketingOptIn = marketingOptIn
        )

        return if (invalidAuthParams.isNotEmpty()) {
            val apiError = ApiError(
                developerMessage = "Email join error.",
                invalidParameters = invalidAuthParams
            )
            call.enqueueError(apiError, callback)
        } else {
            call.enqueue(AccountStoringVimeoCallback(accountStore, callback))
        }
    }

    override fun emailLogin(
        email: String,
        password: String,
        callback: VimeoCallback<VimeoAccount>
    ): VimeoRequest {
        val invalidAuthParams = mapOf(
            AuthParam.FIELD_USERNAME to email,
            AuthParam.FIELD_PASSWORD to password,
            AuthParam.FIELD_SCOPES to scopes
        ).validate()

        val call = authService.logInWithEmail(
            authorization = basicAuthHeader,
            email = email,
            password = password,
            grantType = GrantType.PASSWORD,
            scopes = scopes
        )

        return if (invalidAuthParams.isNotEmpty()) {
            val apiError = ApiError(
                developerMessage = "Email login error.",
                invalidParameters = invalidAuthParams
            )
            call.enqueueError(apiError, callback)
        } else {
            call.enqueue(AccountStoringVimeoCallback(accountStore, callback))
        }
    }

    override fun exchangeAccessToken(accessToken: String, callback: VimeoCallback<VimeoAccount>): VimeoRequest {
        return authService.ssoTokenExchange(basicAuthHeader, accessToken, scopes).enqueue(callback)
    }

    override fun exchangeOAuthOneToken(
        token: String,
        tokenSecret: String,
        callback: VimeoCallback<VimeoAccount>
    ): VimeoRequest {
        val invalidAuthParams = mapOf(
            AuthParam.FIELD_TOKEN to token,
            AuthParam.FIELD_TOKEN_SECRET to tokenSecret,
            AuthParam.FIELD_SCOPES to scopes
        ).validate()

        val call = authService.exchangeOAuthOneToken(
            authorization = basicAuthHeader,
            grantType = GrantType.OAUTH_ONE,
            token = token,
            tokenSecret = tokenSecret,
            scopes = scopes
        )

        return if (invalidAuthParams.isNotEmpty()) {
            val apiError = ApiError(
                developerMessage = "Auth token exchange error.",
                invalidParameters = invalidAuthParams
            )

            call.enqueueError(apiError, callback)
        } else {
            call.enqueue(callback)
        }
    }

    override fun authenticateWithCodeGrant(uri: String, callback: VimeoCallback<VimeoAccount>): VimeoRequest {
        val httpUrl: HttpUrl = HttpUrl.parse(uri) ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
        val authorizationCode = httpUrl.queryParameter("code")
            ?: return localVimeoCallAdapter.enqueueEmptyUri(callback)
        return authService.authenticateWithCodeGrant(
            basicAuthHeader,
            redirectUri,
            authorizationCode,
            GrantType.AUTHORIZATION_CODE
        ).enqueue(AccountStoringVimeoCallback(accountStore, callback))
    }

    override fun createCodeGrantAuthorizationUri(responseCode: String): String {
        return authService.codeGrantRequest(clientId, redirectUri, responseCode, scopes).request().url().toString()
    }

    override fun fetchPinCodeInfo(callback: VimeoCallback<PinCodeInfo>): VimeoRequest {
        return authService.getPinCodeInfo(basicAuthHeader, GrantType.DEVICE, scopes).enqueue(callback)
    }

    override fun authenticateWithPinCode(
        pinCodeInfo: PinCodeInfo,
        callback: VimeoCallback<VimeoAccount>
    ): VimeoRequest {
        val userCode = pinCodeInfo.userCode.orEmpty()
        val deviceCode = pinCodeInfo.deviceCode.orEmpty()

        return authService.logInWithPinCode(basicAuthHeader, GrantType.DEVICE, userCode, deviceCode, scopes)
            .enqueue(callback)
    }

    override fun logOut(callback: VimeoCallback<Unit>): VimeoRequest {
        val accessToken = currentAccount?.accessToken
        accountStore.removeAccount()
        accessToken ?: return NoOpVimeoRequest

        return authService.logOut(authorization = "Bearer $accessToken").enqueue(callback)
    }

    private fun <T> LocalVimeoCallAdapter.enqueueEmptyUri(callback: VimeoCallback<T>): VimeoRequest {
        return enqueueError(ApiError(
            invalidParameters = listOf(InvalidParameter(
                errorCode = ErrorCodeType.INVALID_URI.value
            ))
        ), callback)
    }
}
