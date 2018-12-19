package com.vimeo.networking2.config

import okhttp3.Credentials
import okhttp3.Interceptor
import retrofit2.Retrofit

/**
 * Creates the object graph for the all Retrofit dependencies. This class contains methods
 * for getting the root object ([Retrofit], [RetrofitServicesCache]) of the dependency graph.
 */
object RetrofitSetupComponent {

    /**
     * Creates the object graph for the setup dependencies. After the graph is created, the method
     * return an instance of [Retrofit] which is root of the graph.
     */
    fun retrofit(serverConfig: ServerConfig, accessToken: String? = null): Retrofit {
        val moshi = RetrofitSetupModule.providesMoshi()

        val interceptors = mutableListOf<Interceptor>()
        if (accessToken?.isNotBlank() == true) {
            interceptors.add(RetrofitSetupModule.providesAccessTokenInterceptor(accessToken))
        }
        interceptors.add(RetrofitSetupModule.providesUserAgentInterceptor())

        if (serverConfig.customInterceptors?.isNotEmpty() == true) {
            interceptors.addAll(serverConfig.customInterceptors)
        }
        val okHttpClient = RetrofitSetupModule.providesOkHttpClient(serverConfig, interceptors)
        return RetrofitSetupModule.providesRetrofit(serverConfig, moshi, okHttpClient)
    }

    /**
     * Creates a cache for storing Retrofit services.
     */
    fun retrofitCache(retrofit: Retrofit) = RetrofitServicesCache(retrofit)

    /**
     * Get authentication headers.
     */
    fun getAuthHeaders(clientId: String, clientSecret: String) =
        Credentials.basic(clientId, clientSecret)

}
