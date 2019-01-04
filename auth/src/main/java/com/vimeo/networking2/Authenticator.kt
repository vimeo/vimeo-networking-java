package com.vimeo.networking2

import com.vimeo.networking2.config.RetrofitServicesCache
import com.vimeo.networking2.config.RetrofitSetupComponent
import com.vimeo.networking2.config.ServerConfig
import com.vimeo.networking2.utils.ApiResponseErrorConverter
import com.vimeo.networking2.requests.AuthService
import com.vimeo.networking2.requests.clientcredentials.ClientCredentialsAuthenticator
import com.vimeo.networking2.requests.clientcredentials.ClientCredentialsInteractor
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
    private var retrofit: Retrofit

    /**
     * Used to store created retrofit services.
     */
    private var retrofitServicesCache: RetrofitServicesCache

    private var apiErrorResponseErrorConverter: ApiResponseErrorConverter

    init {
        retrofit = RetrofitSetupComponent.retrofit(serverConfig)
        retrofitServicesCache = RetrofitSetupComponent.retrofitCache(retrofit)
        apiErrorResponseErrorConverter = RetrofitSetupComponent.apiResponseErrorConverter(retrofit)
    }

    fun clientCredentials(): ClientCredentialsAuthenticator {
        return ClientCredentialsInteractor(
            apiErrorResponseErrorConverter,
            retrofitServicesCache.getService(AuthService::class.java),
            RetrofitSetupComponent.authHeaders(serverConfig.clientId, serverConfig.clientSecret),
            serverConfig.scopes.joinToString { it.value }
        ) {
            setAccessToken(it)
        }
    }

    /**
     * After a successful authentication, an access token will be given. The [retrofit] object will
     * be updated to send the access token in every request. The dependency graph for setting up
     * [Retrofit] will re-created.
     */
    private fun setAccessToken(accessToken: String) {
        if (accessToken.isNotBlank()) {
            retrofit = RetrofitSetupComponent.retrofit(serverConfig, accessToken)
            retrofitServicesCache = RetrofitSetupComponent.retrofitCache(retrofit)
        }
    }

}
