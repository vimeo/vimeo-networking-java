package com.vimeo.networking2

import com.vimeo.networking2.config.ServerConfig
import com.vimeo.networking2.requests.AuthCallback

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
         * @param serverConfig Server configuration.
         */
        fun create(serverConfig: ServerConfig): Authenticator = AuthenticatorImpl(serverConfig)

    }

}
