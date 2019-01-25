package com.vimeo.networking2.utils

import com.vimeo.networking2.config.RetrofitSetupModule
import com.vimeo.networking2.requests.AuthService
import okhttp3.Credentials
import retrofit2.Retrofit

/**
 * Provides all dependencies for authentication.
 */
class AuthModule(private val retrofitSetupModule: RetrofitSetupModule) {

    /**
     * Get authentication headers.
     */
    val authHeaders: String =
        Credentials.basic(
            retrofitSetupModule.serverConfig.clientId,
            retrofitSetupModule.serverConfig.clientSecret
        )

    /**
     * Get the Retrofit service for authentication endpoints.
     */
    val authService = retrofitSetupModule
        .retrofitServicesCache
        .getService(AuthService::class.java)

    /**
     * Get specified scopes from the server config object.
     */
    val scopes = retrofitSetupModule.serverConfig.scopes

    /**
     * After a successful authentication, an access token will be given. The [retrofit] object will
     * be updated to send the access token in every request. The dependency graph for setting up
     * [Retrofit] will re-created.
     */
    fun setAccessToken(accessToken: String) {
        if (accessToken.isNotBlank()) {
            retrofitSetupModule.resetRetrofit(accessToken)
        }
    }

}
