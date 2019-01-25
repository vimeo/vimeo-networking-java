package com.vimeo.networking2.requests.clientcredentials

import com.vimeo.networking2.requests.AuthCallback
import com.vimeo.networking2.requests.VimeoRequest

/**
 * Authenticate client id and client secret.
 */
interface ClientCredentialsAuthenticator {

    /**
     * Authenticate client id and client secret.
     *
     * @param authCallback informs you of the result of the response.
     *
     * @return A [VimeoRequest] object to cancel API requests.
     */
    fun authenticate(authCallback: AuthCallback): VimeoRequest

}
