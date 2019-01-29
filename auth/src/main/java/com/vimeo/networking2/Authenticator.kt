package com.vimeo.networking2

import com.vimeo.networking2.config.RetrofitSetupModule
import com.vimeo.networking2.config.ServerConfig
import com.vimeo.networking2.requests.AuthService
import com.vimeo.networking2.requests.clientcredentials.ClientCredentialsAuthenticator
import com.vimeo.networking2.requests.clientcredentials.ClientCredentialsInteractor
import okhttp3.Credentials

/**
 * Authentication with email, google, facebook or pincode.
 *
 * @param serverConfig All the server configuration (client id and secret, custom interceptors,
 *                     read timeouts, base url etc...) that can be set for authentication and
 *                     making requests.
 */
/**
 * Authentication with email, google, facebook or pincode.
 *
 * @param serverConfig All the server configuration (client id and secret, custom interceptors,
 *                     read timeouts, base url etc...) that can be set for authentication and
 *                     making requests.
 */
class Authenticator(private val serverConfig: ServerConfig) {

    /**
     * Get the Retrofit service for authentication endpoints.
     */
    private val authService by lazy {
        val retrofit = RetrofitSetupModule.retrofit(serverConfig)
        retrofit.create(AuthService::class.java)
    }

    /**
     * Client id and client secret headers.
     */
    private val authHeaders: String =
        Credentials.basic(
            serverConfig.clientId,
            serverConfig.clientSecret
        )

    /**
     * Authenticate with a client id and client secret.
     */
    fun clientCredentials(): ClientCredentialsAuthenticator =
        ClientCredentialsInteractor(
            authService,
            authHeaders,
            serverConfig.scopes
        )
}
