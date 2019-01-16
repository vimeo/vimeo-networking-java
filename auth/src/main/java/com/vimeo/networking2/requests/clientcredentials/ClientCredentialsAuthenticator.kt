package com.vimeo.networking2.requests.clientcredentials

import com.vimeo.networking2.requests.ApiRequest
import com.vimeo.networking2.requests.AuthCallback

/**
 * Authenticate client id and client secret.
 */
interface ClientCredentialsAuthenticator: ApiRequest {

    /**
     * Authenticate client id and client secret.
     *
     * @param authCallback informs you of the result of the response.
     */
    fun authenticate(authCallback: AuthCallback)

    /**
     * Cancel client credentials request.
     */
    override fun cancel()

}
