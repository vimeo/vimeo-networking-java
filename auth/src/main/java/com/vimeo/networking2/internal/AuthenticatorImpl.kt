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

import com.vimeo.networking2.Authenticator
import com.vimeo.networking2.GrantType
import com.vimeo.networking2.NoOpVimeoRequest
import com.vimeo.networking2.PinCodeInfo
import com.vimeo.networking2.Scopes
import com.vimeo.networking2.SsoDomain
import com.vimeo.networking2.VimeoAccount
import com.vimeo.networking2.VimeoCallback
import com.vimeo.networking2.VimeoRequest
import com.vimeo.networking2.account.CachingAccountStore
import com.vimeo.networking2.internal.AuthService.Companion.AUTHORIZATION_CODE
import com.vimeo.networking2.internal.AuthService.Companion.CODE
import com.vimeo.networking2.internal.AuthService.Companion.DEVICE_CODE
import com.vimeo.networking2.internal.AuthService.Companion.EMAIL
import com.vimeo.networking2.internal.AuthService.Companion.ID_TOKEN
import com.vimeo.networking2.internal.AuthService.Companion.NAME
import com.vimeo.networking2.internal.AuthService.Companion.PASSWORD
import com.vimeo.networking2.internal.AuthService.Companion.REDIRECT_URI
import com.vimeo.networking2.internal.AuthService.Companion.TOKEN
import com.vimeo.networking2.internal.AuthService.Companion.TOKEN_SECRET
import com.vimeo.networking2.internal.AuthService.Companion.USERNAME
import com.vimeo.networking2.internal.AuthService.Companion.USER_CODE
import com.vimeo.networking2.internal.params.ApiParameter
import com.vimeo.networking2.internal.params.validateParameters
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

    override fun authenticateWithClientCredentials(callback: VimeoCallback<VimeoAccount>): VimeoRequest {
        return authService.authorizeWithClientCredentialsGrant(
            authorization = basicAuthHeader,
            grantType = GrantType.CLIENT_CREDENTIALS,
            scopes = scopes
        ).enqueueWithAccountStore(callback)
    }

    override fun authenticateWithGoogle(
        token: String,
        username: String,
        marketingOptIn: Boolean,
        callback: VimeoCallback<VimeoAccount>
    ): VimeoRequest {
        val tokenParameter = ApiParameter(ID_TOKEN, token)
        val emailParameter = ApiParameter(USERNAME, username)

        return localVimeoCallAdapter.validateParameters(
            callback,
            "Google authentication error",
            tokenParameter,
            emailParameter
        ) ?: authService.joinWithGoogle(
            authorization = basicAuthHeader,
            username = emailParameter.validatedParameterValue,
            idToken = tokenParameter.validatedParameterValue,
            scopes = scopes,
            marketingOptIn = marketingOptIn
        ).enqueueWithAccountStore(callback)
    }

    override fun authenticateWithFacebook(
        token: String,
        username: String,
        marketingOptIn: Boolean,
        callback: VimeoCallback<VimeoAccount>
    ): VimeoRequest {
        val tokenParameter = ApiParameter(TOKEN, token)
        val emailParameter = ApiParameter(USERNAME, username)

        return localVimeoCallAdapter.validateParameters(
            callback,
            "Facebook authentication error",
            tokenParameter,
            emailParameter
        ) ?: authService.joinWithFacebook(
            authorization = basicAuthHeader,
            username = emailParameter.validatedParameterValue,
            token = tokenParameter.validatedParameterValue,
            scopes = scopes,
            marketingOptIn = marketingOptIn
        ).enqueueWithAccountStore(callback)
    }

    override fun authenticateWithEmailJoin(
        displayName: String,
        email: String,
        password: String,
        marketingOptIn: Boolean,
        callback: VimeoCallback<VimeoAccount>
    ): VimeoRequest {
        val nameParameter = ApiParameter(NAME, displayName)
        val emailParameter = ApiParameter(EMAIL, email)
        val passwordParameter = ApiParameter(PASSWORD, password)

        return localVimeoCallAdapter.validateParameters(
            callback,
            "Email join error",
            nameParameter,
            emailParameter,
            passwordParameter
        ) ?: authService.joinWithEmail(
            authorization = basicAuthHeader,
            name = nameParameter.validatedParameterValue,
            email = emailParameter.validatedParameterValue,
            password = passwordParameter.validatedParameterValue,
            scopes = scopes,
            marketingOptIn = marketingOptIn
        ).enqueueWithAccountStore(callback)
    }

    override fun authenticateWithEmailLogin(
        username: String,
        password: String,
        callback: VimeoCallback<VimeoAccount>
    ): VimeoRequest {
        val emailParameter = ApiParameter(USERNAME, username)
        val passwordParameter = ApiParameter(PASSWORD, password)

        return localVimeoCallAdapter.validateParameters(
            callback,
            "Email login error",
            emailParameter,
            passwordParameter
        ) ?: authService.logInWithEmail(
            authorization = basicAuthHeader,
            username = emailParameter.validatedParameterValue,
            password = passwordParameter.validatedParameterValue,
            grantType = GrantType.PASSWORD,
            scopes = scopes
        ).enqueueWithAccountStore(callback)
    }

    override fun exchangeAccessToken(accessToken: String, callback: VimeoCallback<VimeoAccount>): VimeoRequest {
        return authService.exchangeAccessToken(basicAuthHeader, accessToken, scopes).enqueueWithAccountStore(callback)
    }

    override fun exchangeOAuth1Token(
        token: String,
        tokenSecret: String,
        callback: VimeoCallback<VimeoAccount>
    ): VimeoRequest {
        val tokenParameter = ApiParameter(TOKEN, token)
        val tokenSecretParameter = ApiParameter(TOKEN_SECRET, tokenSecret)

        return localVimeoCallAdapter.validateParameters(
            callback,
            "OAuth1 token exchange error",
            tokenParameter,
            tokenSecretParameter
        ) ?: authService.exchangeOAuth1Token(
            authorization = basicAuthHeader,
            grantType = GrantType.OAUTH_ONE,
            token = tokenParameter.validatedParameterValue,
            tokenSecret = tokenSecretParameter.validatedParameterValue,
            scopes = scopes
        ).enqueueWithAccountStore(callback)
    }

    override fun authenticateWithCodeGrant(uri: String, callback: VimeoCallback<VimeoAccount>): VimeoRequest {
        val authorizationCode = ApiParameter(
            CODE,
            HttpUrl.parse(uri)?.queryParameter(PARAM_CODE),
            "URI was invalid or did not contain a '$PARAM_CODE' parameter"
        )
        val redirectUri = ApiParameter(REDIRECT_URI, redirectUri)

        return localVimeoCallAdapter.validateParameters(
            callback,
            "Code grant authentication error",
            authorizationCode,
            redirectUri
        ) ?: authService.authenticateWithCodeGrant(
            authorization = basicAuthHeader,
            redirectUri = redirectUri.validatedParameterValue,
            authorizationCode = authorizationCode.validatedParameterValue,
            grantType = GrantType.AUTHORIZATION_CODE
        ).enqueueWithAccountStore(callback)
    }

    override fun createCodeGrantAuthorizationUri(responseCode: String): String {
        return authService.createCodeGrantRequest(
            clientId = clientId,
            redirectUri = redirectUri,
            state = responseCode,
            scopes = scopes
        ).url
    }

    override fun fetchSsoDomain(domain: String, callback: VimeoCallback<SsoDomain>): VimeoRequest {
        val domainParameter = ApiParameter(AuthService.DOMAIN, domain)

        return localVimeoCallAdapter.validateParameters(callback, "SSO domain fetch error", domainParameter)
            ?: authService.getSsoDomain(
                authorization = basicAuthHeader,
                domain = domainParameter.validatedParameterValue
            ).enqueue(callback)
    }

    override fun createSsoAuthorizationUri(ssoDomain: SsoDomain, responseCode: String): String? {
        val connectUrl = ssoDomain.connectUrl ?: return null
        return authService.createSsoGrantRequest(
            uri = connectUrl,
            redirectUri = redirectUri,
            state = responseCode
        ).url
    }

    override fun authenticateWithSso(
        uri: String,
        marketingOptIn: Boolean,
        callback: VimeoCallback<VimeoAccount>
    ): VimeoRequest {
        val authorizationCode = ApiParameter(
            AUTHORIZATION_CODE,
            HttpUrl.parse(uri)?.queryParameter(PARAM_CODE),
            "URI was invalid or did not contain a '$PARAM_CODE' parameter"
        )
        val redirectUri = ApiParameter(REDIRECT_URI, redirectUri)

        return localVimeoCallAdapter.validateParameters(
            callback,
            "SSO authentication error",
            authorizationCode,
            redirectUri
        ) ?: authService.joinWithSsoCodeGrant(
            authorization = basicAuthHeader,
            authorizationCode = authorizationCode.validatedParameterValue,
            redirectUri = redirectUri.validatedParameterValue,
            marketingOptIn = marketingOptIn
        ).enqueueWithAccountStore(callback)
    }

    override fun fetchPinCodeInfo(callback: VimeoCallback<PinCodeInfo>): VimeoRequest {
        return authService.getPinCodeInfo(
            authorization = basicAuthHeader,
            grantType = GrantType.DEVICE,
            scopes = scopes
        ).enqueue(callback)
    }

    override fun authenticateWithPinCode(
        pinCodeInfo: PinCodeInfo,
        callback: VimeoCallback<VimeoAccount>
    ): VimeoRequest {
        val userCode = ApiParameter(USER_CODE, pinCodeInfo.userCode)
        val deviceCode = ApiParameter(DEVICE_CODE, pinCodeInfo.deviceCode)

        return localVimeoCallAdapter.validateParameters(
            callback,
            "Pin code authentication error",
            userCode,
            deviceCode
        ) ?: authService.logInWithPinCode(
            authorization = basicAuthHeader,
            grantType = GrantType.DEVICE,
            pinCode = userCode.validatedParameterValue,
            deviceCode = deviceCode.validatedParameterValue,
            scopes = scopes
        ).enqueueWithAccountStore(callback)
    }

    override fun logOut(callback: VimeoCallback<Unit>): VimeoRequest {
        val accessToken = currentAccount?.accessToken
        accountStore.removeAccount()
        accessToken ?: return NoOpVimeoRequest

        return authService.logOut(authorization = "Bearer $accessToken").enqueue(callback)
    }

    private fun VimeoCall<VimeoAccount>.enqueueWithAccountStore(callback: VimeoCallback<VimeoAccount>): VimeoRequest =
        enqueue(AccountStoringVimeoCallback(accountStore, callback))

    private companion object {
        private const val PARAM_CODE = "code"
    }
}
