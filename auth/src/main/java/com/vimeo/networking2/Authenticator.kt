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
package com.vimeo.networking2

import com.vimeo.networking2.account.CachingAccountStore
import com.vimeo.networking2.annotations.Internal
import com.vimeo.networking2.config.RetrofitSetupModule
import com.vimeo.networking2.config.VimeoApiConfiguration
import com.vimeo.networking2.internal.AuthService
import com.vimeo.networking2.internal.AuthenticatorImpl
import com.vimeo.networking2.internal.LocalVimeoCallAdapter
import com.vimeo.networking2.internal.MutableAuthenticatorDelegate
import okhttp3.Credentials
import java.util.concurrent.Executor

/**
 * An interface that provides the following ways to authenticate with the Vimeo API:
 * - Client credentials
 * - Google
 * - Facebook
 * - Code grant
 * - Email login
 * - Email join
 * - Pin code
 * - Logout
 *
 * If no authentication is performed, by default basic authentication will be used for requests made by this SDK. It is
 * advised that client credentials are used instead of basic authentication if the consumer intends to use the API as a
 * "logged out" user. If the consumer intends to use the API as a "logged in" user, then obtaining credentials using the
 * code grant mechanism is the way to authenticate.
 *
 * Create an instance of the [Authenticator] by using its invoke function as follows.
 * ```
 * val authenticator = Authenticator(Configuration)
 * ```
 *
 * If the consumer does not want to manage the instance of the [Authenticator] themselves, they can utilize the
 * singleton instance.
 * ```
 * Authenticator.initialize(Configuration)
 * val instance = Authenticator.instance()
 * ```
 *
 * In order to obtain client credentials, make a request as follows.
 * ```
 * authenticator.clientCredentials(vimeoCallback(
 *     onSuccess = { authResponse: VimeoResponse.Success<VimeoAccount> -> }
 *     onError = { error: VimeoResponse.Error -> }
 * ))
 * ```
 * Once this request succeeds, all subsequent requests with the client will use the token obtained.
 *
 * In order to log in using a code grant, follow this flow. Note: [VimeoApiConfiguration.codeGrantRedirectUri] must be
 * set and must be registered at [https://developer.vimeo.com/apps].
 * ```
 * // Create the URI to log in.
 * val loginUri = createCodeGrantAuthorizationUri(requestCode = "1234567")
 *
 * // Open the URI in a browser (Android shown here).
 * val intent = Intent(Intent.ACTION_VIEW, Uri.parse(loginUri))
 * startActivity(intent)
 * ```
 * Once the user logs in at the URI, the API will redirect back to the redirect URI mentioned above.
 * ```
 * // Use the URI the browser redirects back to to log in.
 * authenticator.authenticateWithCodeGrant(redirectedUri, vimeoCallback(
 *     onSuccess = { authResponse: VimeoResponse.Success<VimeoAccount> -> }
 *     onError = { error: VimeoResponse.Error -> }
 * ))
 * ```
 *
 * See [https://developer.vimeo.com/api/authentication] for more information about the different authentication
 * approaches.
 */
@Suppress("ComplexInterface")
interface Authenticator {

    /**
     * The currently authenticated account, if the client has successfully authenticated. May represent either a logged
     * in or logged out user.
     */
    val currentAccount: VimeoAccount?

    /**
     * Authenticate using the client ID and client secret (set in the [VimeoApiConfiguration]) to obtain a logged out
     * access token.
     *
     * @param callback informs you of the result of the response.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun clientCredentials(callback: VimeoCallback<VimeoAccount>): VimeoRequest

    /**
     * Authenticate via Google sign in to obtain a logged in token.
     *
     * @param token Google authentication token.
     * @param email Email addressed used to sign in to Google.
     * @param marketingOptIn Opt in or out on GDPR.
     * @param callback Callback to be notified of the result of the request.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    @Internal
    fun google(
        token: String,
        email: String,
        marketingOptIn: Boolean,
        callback: VimeoCallback<VimeoAccount>
    ): VimeoRequest

    /**
     * Authenticate via Facebook sign in to obtain a logged in token.
     *
     * @param token Google authentication token.
     * @param email Email addressed used to sign in to Google.
     * @param marketingOptIn Opt in or out on GDPR.
     * @param callback Callback to be notified of the result of the request.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    @Internal
    fun facebook(
        token: String,
        email: String,
        marketingOptIn: Boolean,
        callback: VimeoCallback<VimeoAccount>
    ): VimeoRequest

    /**
     * Join Vimeo using email and obtain a logged in token.
     *
     * @param displayName User name to set for your Vimeo account.
     * @param email Email to use to login to your Vimeo account.
     * @param password Password for your Vimeo account.
     * @param marketingOptIn Opt in or out on GDPR.
     * @param callback Callback to be notified of the result of the request.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    @Internal
    fun emailJoin(
        displayName: String,
        email: String,
        password: String,
        marketingOptIn: Boolean,
        callback: VimeoCallback<VimeoAccount>
    ): VimeoRequest

    /**
     * Log in via email to obtain a logged in token.
     *
     * @param email Email address associated with your Vimeo account.
     * @param password Password for your Vimeo account.
     * @param callback Callback to be notified of the result of the request.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    @Internal
    fun emailLogin(
        email: String,
        password: String,
        callback: VimeoCallback<VimeoAccount>
    ): VimeoRequest

    /**
     * Exchange an access token from another app or [Authenticator] instance for a new access token for use by this
     * instance.
     *
     * @param accessToken The access token (such as one from [VimeoAccount.accessToken]) to exchange for another token
     * representing the same logged in user.
     * @param callback Callback to be notified of the result of the request.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun exchangeAccessToken(
        accessToken: String,
        callback: VimeoCallback<VimeoAccount>
    ): VimeoRequest

    /**
     * Exchange an OAuth 1 token and secret for a new OAuth 2 token.
     *
     * @param token The old token to use in the exchange.
     * @param tokenSecret The old token secret to use in the exchange.
     * @param callback Callback to be notified of the result of the request.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun exchangeOAuthOneToken(
        token: String,
        tokenSecret: String,
        callback: VimeoCallback<VimeoAccount>
    ): VimeoRequest

    /**
     * Create a URI which can be used to log in the user via a browser and will redirect to a URI that can be exchanged
     * using [authenticateWithCodeGrant] for a logged in account.
     *
     * @param responseCode An arbitrary response code that can be used to verify the origin of the redirect URI. The
     * API will return this value to later as a security measure in a query string parameter named `state` in the
     * callback URI.
     *
     * @return The URI which can be opened in a browser.
     */
    fun createCodeGrantAuthorizationUri(responseCode: String): String

    /**
     * Log in using an authorization code grant. Before authenticating with this function, the user should be directed
     * to log into Vimeo in a browser by navigating to the URI returned by [createCodeGrantAuthorizationUri]. After the
     * user logs into Vimeo, the API will redirect back to the URI specified in
     * [VimeoApiConfiguration.codeGrantRedirectUri] along with some additional parameters. The consumer should intercept
     * this URI and use it to authenticate by passing it to this function in the [uri] parameter.
     *
     * @param uri The URI which was redirected back to you. Must contain a `code` query parameter that contains the
     * authorization code.
     * @param callback Callback to be notified of the result of the request.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun authenticateWithCodeGrant(
        uri: String,
        callback: VimeoCallback<VimeoAccount>
    ): VimeoRequest

    /**
     * Find a supported SSO domain that matches the [domain] parameter.
     *
     * @param domain A domain, also known as hostname, that might be supported for SSO by the Vimeo API.
     * @param callback Callback to be notified of the result of the request.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    @Internal
    fun fetchSsoDomain(
        domain: String,
        callback: VimeoCallback<SsoDomain>
    ): VimeoRequest

    /**
     * Obtain the URI which can be used to log in the user and receive back a URI that can be exchanged using
     * [ssoCodeGrant] for a logged in account. Obtain the [SsoDomain] required by this function first using
     * [fetchSsoDomain].
     *
     * @param ssoDomain The domain info obtained from the API that specifies the [SsoDomain.connectUrl] which will be
     * used as the base for this URI.
     * @param responseCode The response code that can be used to identify the origin of the redirect URI.
     *
     * @return The URI which can be opened in a browser.
     */
    @Internal
    fun obtainSsoGrantAuthorizationUri(ssoDomain: SsoDomain, responseCode: String): String

    /**
     * Authenticate with the server using an authorization code grant from a supported enterprise SSO domain.
     *
     * @param authorizationCode The Auth0 code to verify.
     * @param marketingOptIn True if the user is opting into marketing emails, false otherwise.
     * @param callback Callback to be notified of the result of the request.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    @Internal
    fun ssoCodeGrant(
        authorizationCode: String,
        marketingOptIn: Boolean,
        callback: VimeoCallback<VimeoAccount>
    ): VimeoRequest

    /**
     * Fetch the [PinCodeInfo] that can be used to authenticate with the server. The user must go to the URI specified
     * by [PinCodeInfo.activateLink] and enter the [PinCodeInfo.userCode]. After fetching this info, if the user has
     * entered the pin code, calling [authenticateWithPinCode] with the pin code provided by this request will succeed.
     *
     * @param callback Callback to be notified of the result of the request.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun fetchPinCodeInfo(callback: VimeoCallback<PinCodeInfo>): VimeoRequest

    /**
     * Log in using a pin code obtained using [fetchPinCodeInfo]. Will fail if the user has not yet entered the pin code
     * at the activation link.
     *
     * @param pinCodeInfo The pin code instance obtained from [fetchPinCodeInfo].
     * @param callback Callback to be notified of the result of the request.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun authenticateWithPinCode(pinCodeInfo: PinCodeInfo, callback: VimeoCallback<VimeoAccount>): VimeoRequest

    /**
     * Log out of the currently authenticated account.
     *
     * @param callback Callback to be notified of the result of the request.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun logOut(callback: VimeoCallback<Unit>): VimeoRequest

    /**
     * Factory to create an instance of [Authenticator].
     */
    companion object {

        private val delegate: MutableAuthenticatorDelegate = MutableAuthenticatorDelegate()

        /**
         * Initialize the singleton instance of the [Authenticator] with a [VimeoApiConfiguration]. If the authenticator
         * was already initialized, this will reconfigure it. This function must be called before [instance] is used.
         *
         * @param vimeoApiConfiguration The configuration used by the authenticator.
         */
        @JvmStatic
        fun initialize(vimeoApiConfiguration: VimeoApiConfiguration) {
            delegate.actual = Authenticator(vimeoApiConfiguration)
        }

        /**
         * Access the singleton instance of the [Authenticator]. Always returns the same instance.
         */
        @JvmStatic
        fun instance(): Authenticator = delegate

        /**
         * Create an instance of Authenticator to make authentication requests.
         *
         * @param vimeoApiConfiguration All the server configuration (client id and secret, custom interceptors, read
         * timeouts, base url etc...) that can be set for authentication and making requests.
         */
        @JvmStatic
        @JvmName("create")
        operator fun invoke(vimeoApiConfiguration: VimeoApiConfiguration): Authenticator {
            val retrofit = RetrofitSetupModule.retrofit(vimeoApiConfiguration)
            val authService = retrofit.create(AuthService::class.java)
            val basicAuthHeader: String = Credentials.basic(
                vimeoApiConfiguration.clientId,
                vimeoApiConfiguration.clientSecret
            )
            val synchronousExecutor = Executor { it.run() }
            return AuthenticatorImpl(
                authService,
                basicAuthHeader,
                vimeoApiConfiguration.clientId,
                vimeoApiConfiguration.codeGrantRedirectUri,
                vimeoApiConfiguration.scope,
                CachingAccountStore(vimeoApiConfiguration.accountStore),
                LocalVimeoCallAdapter(retrofit.callbackExecutor() ?: synchronousExecutor)
            )
        }
    }
}
