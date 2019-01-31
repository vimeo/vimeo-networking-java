package com.vimeo.networking2

import com.vimeo.networking2.config.RetrofitSetupModule
import com.vimeo.networking2.config.ServerConfig
import com.vimeo.networking2.requests.AuthCallback
import com.vimeo.networking2.requests.AuthService
import com.vimeo.networking2.requests.VimeoRequest
import com.vimeo.networking2.requests.interactors.ClientCredentialsInteractor
import com.vimeo.networking2.requests.interactors.SocialAuthInteractor
import com.vimeo.networking2.requests.interactors.SocialAuthParams
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

    /**
     * Authenticate using Google.
     */
    override fun google(params: SocialAuthParams, authCallback: AuthCallback): VimeoRequest =
        socialAuthenticate(params, authCallback)

    /**
     * Authenticate using Facebook.
     */
    override fun facebook(params: SocialAuthParams, authCallback: AuthCallback): VimeoRequest =
        socialAuthenticate(params, authCallback)

    /**
     * Google and Facebook authentication have the same authentication flows. Both of them require
     * the client to supply a token and email. For re-usability, both authentication flows use
     * a single interactor.
     */
    private fun socialAuthenticate(params: SocialAuthParams, authCallback: AuthCallback): VimeoRequest {
        val interactor = SocialAuthInteractor(authService, authHeaders)
        return interactor.authenticate(params, authCallback)
    }

}
