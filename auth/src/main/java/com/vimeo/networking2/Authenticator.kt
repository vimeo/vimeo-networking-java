package com.vimeo.networking2

import com.vimeo.networking2.config.RetrofitServicesCache
import com.vimeo.networking2.config.RetrofitSetupComponent
import com.vimeo.networking2.config.ServerConfig
import com.vimeo.networking2.enums.GrantType
import com.vimeo.networking2.enums.ScopeType
import com.vimeo.networking2.requests.AuthService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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

    init {
        retrofit = RetrofitSetupComponent.retrofit(serverConfig)
        retrofitServicesCache = RetrofitSetupComponent.retrofitCache(retrofit)
    }

    /**
     * This is a temporary request that is added in to show how the access token will be set.
     * This will be removed in the next PR which is to design and implement all the auth requests.
     */
    fun authenticateWithClientCredentials() {
        val apiService = retrofitServicesCache.getService(AuthService::class.java)

        val call = apiService.authorizeWithClientCredentialsGrant(
            RetrofitSetupComponent.getAuthHeaders(serverConfig.clientId, serverConfig.clientSecret),
            GrantType.CLIENT_CREDENTIALS.toString(),
            ScopeType.PUBLIC.toString())

        call.enqueue(object : Callback<VimeoAccount> {

            override fun onResponse(call: Call<VimeoAccount>, response: Response<VimeoAccount>) {
                response.body()?.accessToken?.let { setAccessToken(it) }
            }
            override fun onFailure(call: Call<VimeoAccount>, t: Throwable) {}
        })
    }

    /**
     * After a successful authentication, an access token will be given. The [retrofit] object will
     * be updated to send the access token in every request. The dependency graph for setting up
     * [Retrofit] will re-created.
     */
    private fun setAccessToken(accessToken: String) {
        if (accessToken.isNotBlank()) {
            retrofit = RetrofitSetupComponent.retrofit(serverConfig, accessToken)
            retrofitServicesCache.retrofit = retrofit
        }
    }

}
