package com.vimeo.networking2.internal

import com.vimeo.networking2.AuthCallback
import com.vimeo.networking2.Authenticator
import com.vimeo.networking2.VimeoRequest
import com.vimeo.networking2.config.RetrofitSetupModule
import com.vimeo.networking2.config.ServerConfig
import okhttp3.Credentials

/**
 * Authentication with email, google, facebook or pincode.
 *
 * @param serverConfig All the server configuration (client id and secret, custom interceptors,
 *                     read timeouts, base url etc...) that can be set for authentication and
 *                     making requests.
 */
internal class AuthenticatorImpl(private val serverConfig: ServerConfig): Authenticator {

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
    override fun clientCredentials(authCallback: AuthCallback): VimeoRequest {
        val interactor = ClientCredentialsInteractor(
            authService,
            authHeaders,
            serverConfig.scopes
        )
        return interactor.authenticate(authCallback)
    }

}
