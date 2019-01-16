package com.vimeo.networking2

import com.vimeo.networking2.config.RetrofitSetupModule
import com.vimeo.networking2.config.ServerConfig
import com.vimeo.networking2.requests.clientcredentials.ClientCredentialsAuthenticator
import com.vimeo.networking2.requests.clientcredentials.ClientCredentialsInteractor
import com.vimeo.networking2.utils.AuthModule
import com.vimeo.networking2.utils.AuthModule.authCoroutineScope
import retrofit2.Retrofit

/**
 * Authentication with email, google, facebook or pincode.
 * This is a temporary class that will be updated as requests are added.
 *
 * @param serverConfig All the server configuration (client id and secret, custom interceptors,
 *                     read timeouts, base url etc...) that can be set for authentication and
 *                     making requests.
 */
class Authenticator(private val serverConfig: ServerConfig) {

    /**
     * Used to create authentication service.
     */
    private var retrofit = RetrofitSetupModule.retrofit(serverConfig)

    /**
     * Used to store created retrofit services.
     */
    private var retrofitServicesCache = RetrofitSetupModule.retrofitCache(retrofit)

    /**
     * Used to convert response to [ApiError]. It is cached here so we don't have to create it every time
     * a request is made.
     */
    private val errorResponseConverter = RetrofitSetupModule.errorResponseConverter(retrofit)

    /**
     * Authenticate with a client id and client secret.
     */
    fun clientCredentials(): ClientCredentialsAuthenticator = ClientCredentialsInteractor(
            errorResponseConverter = errorResponseConverter,
            authService = AuthModule.authService(retrofitServicesCache),
            authHeaders = RetrofitSetupModule.authHeaders(serverConfig.clientId, serverConfig.clientSecret),
            apiScopes = serverConfig.scopes,
            coroutineScope = authCoroutineScope,
            authTokenCallback = this::setAccessToken
        )

    /**
     * After a successful authentication, an access token will be given. The [retrofit] object will
     * be updated to send the access token in every request. The dependency graph for setting up
     * [Retrofit] will re-created.
     */
    private fun setAccessToken(accessToken: String) {
        if (accessToken.isNotBlank()) {
            retrofit = RetrofitSetupModule.retrofit(serverConfig, accessToken)
            retrofitServicesCache = RetrofitSetupModule.retrofitCache(retrofit)
        }
    }

}
