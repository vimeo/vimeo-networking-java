package com.vimeo.networking2

import com.vimeo.networking2.config.RetrofitSetupModule
import com.vimeo.networking2.config.ServerConfig
import com.vimeo.networking2.internal.AuthService
import com.vimeo.networking2.internal.AuthenticatorImpl
import okhttp3.Credentials

/**
 * API that allow you to make the following authentication requests:
 *
 * - Client credentials.
 * - Google
 * - Facebook
 * - Email login
 * - Email join
 * - Logout
 *
 * Create an instance of the Authenticator by using its factory and make requests as follows.
 *
 * Ex:
 *
 * ```
 * val authenticator = Authenticator.create(serverConfig)
 * authenticator.clientCredentials(object: VimeoCallback<BasicAuthToken>() {
 *
 *       override fun onSuccess(authResponse: VimeoResponse.Success<BasicAuthToken>) {
 *
 *       }
 *
 *       override fun onError(error: VimeoResponse.Error) {
 *
 *       }
 * })
 * ```
 *
 */
interface Authenticator {

    /**
     * Authenticate client id and client secret.
     *
     * @param authCallback informs you of the result of the response.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun clientCredentials(authCallback: VimeoCallback<BasicAccessToken>): VimeoRequest

    /**
     * Authenticate via Google.
     *
     * @param token             Google authentication token.
     * @param email             Email addressed used to sign in to Google.
     * @param marketingOptIn    Opt in or out on GDPR.
     * @param authCallback      Callback to be notified of the result of the request.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun google(
        token: String,
        email: String,
        marketingOptIn: Boolean,
        authCallback: VimeoCallback<VimeoAccount>
    ): VimeoRequest

    /**
     * Authenticate via Facebook.
     *
     * @param token             Google authentication token.
     * @param email             Email addressed used to sign in to Google.
     * @param marketingOptIn    Opt in or out on GDPR.
     * @param authCallback      Callback to be notified of the result of the request.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun facebook(
        token: String,
        email: String,
        marketingOptIn: Boolean,
        authCallback: VimeoCallback<VimeoAccount>
    ): VimeoRequest

    /**
     * Join Vimeo by email.
     *
     * @param displayName       User name to set for your Vimeo account.
     * @param email             Email to use to login to your Vimeo account.
     * @param password          Password for your Vimeo account.
     * @param marketingOptIn    Opt in or out on GDPR.
     * @param authCallback      Callback to be notified of the result of the request.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun emailJoin(
        displayName: String,
        email: String,
        password: String,
        marketingOptIn: Boolean,
        authCallback: VimeoCallback<VimeoAccount>
    ): VimeoRequest

    /**
     * Login via email.
     *
     * @param email         Email address associated with your Vimeo account.
     * @param password      Password for your Vimeo account.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun emailLogin(
        email: String,
        password: String,
        authCallback: VimeoCallback<VimeoAccount>
    ): VimeoRequest

    /**
     * Factory to create an instance of [Authenticator].
     */
    companion object Factory {

        /**
         * Create an instance of Authenticator to make authentication
         * requests.
         *
         * @param serverConfig All the server configuration (client id and secret, custom
         *                     interceptors, read timeouts, base url etc...) that can be set for
         *                     authentication and making requests.
         */
        fun create(serverConfig: ServerConfig): Authenticator {

            val authService = RetrofitSetupModule
                    .retrofit(serverConfig)
                    .create(AuthService::class.java)

            val authHeaders: String =
                    Credentials.basic(
                        serverConfig.clientId,
                        serverConfig.clientSecret
                    )

            return AuthenticatorImpl(authService, authHeaders, Scopes(serverConfig.scopes))
        }

    }

}
