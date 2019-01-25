package com.vimeo.networking2

import com.vimeo.networking2.config.RetrofitSetupModule
import com.vimeo.networking2.config.ServerConfig
import com.vimeo.networking2.requests.clientcredentials.ClientCredentialsAuthenticator
import com.vimeo.networking2.requests.clientcredentials.ClientCredentialsInteractor
import com.vimeo.networking2.utils.AuthModule

/**
 * Authentication with email, google, facebook or pincode.
 *
 * @param serverConfig All the server configuration (client id and secret, custom interceptors,
 *                     read timeouts, base url etc...) that can be set for authentication and
 *                     making requests.
 */
class Authenticator(private val serverConfig: ServerConfig) {

    /**
     * Auth module provides authenticated related dependencies.
     */
    private val authModule = AuthModule(RetrofitSetupModule(serverConfig))

    /**
     * Authenticate with a client id and client secret.
     */
    fun clientCredentials(): ClientCredentialsAuthenticator = ClientCredentialsInteractor(authModule)

}
