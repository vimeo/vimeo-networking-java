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
 * authenticator.clientCredentials(object: AuthCallback() {
 *
 *      override fun onSuccess(authResponse: ApiResponse.Success<String>) {
 *
 *       }
 *
 *       override fun onGenericError(genericFailure: ApiResponse.Failure.GenericFailure) {
 *
 *       }
 *
 *       override fun onApiError(apiFailure: ApiResponse.Failure.ApiFailure) {
 *
 *       }
 *
 *       override fun onExceptionError(exceptionFailure: ApiResponse.Failure.ExceptionFailure) {
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
    fun clientCredentials(authCallback: AuthCallback): VimeoRequest

    /**
     * Factory to create an instance of [Authenticator].
     */
    companion object Factory {

        /**
         * Create an instance of Authenticator to make authentication
         * requests.
         *
         * @param serverConfig All the server configuration (client id and secret, custom interceptors,
         *                     read timeouts, base url etc...) that can be set for authentication and
         *                     making requests.
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

            return AuthenticatorImpl(authService, authHeaders, serverConfig.scopes.joinToString())
        }

    }

}
