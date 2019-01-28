package com.vimeo.networking2.config

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.vimeo.networking2.ApiConstants
import com.vimeo.networking2.adapters.ErrorHandlingCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Module which provides all the dependencies needed for setting up [Retrofit].
 */
class RetrofitSetupModule(val serverConfig: ServerConfig) {

    /**
     * Create [Moshi] object for serialization and deserialization.
     */
    private val moshi = Moshi.Builder()
        .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
        .build()

    /**
     * Create interceptor for adding a user agent.
     */
    private val userAgentInterceptor =
        Interceptor { chain ->
            val request = chain.request()
            val builder = request.newBuilder()

            if (request.header(USER_AGENT_HEADER).isNullOrBlank()) {
                builder.addHeader(USER_AGENT_HEADER, USER_AGENT_HEADER_VALUE)
            }
            chain.proceed(builder.build())
        }

    /**
     * Cached retrofit object.
     */
    var retrofit = retrofit(serverConfig)

    /**
     * Used to store created retrofit services.
     */
    var retrofitServicesCache = retrofitCache(retrofit)

    /**
     * Creates the object graph for the setup dependencies. After the graph is created, the method
     * return an instance of [Retrofit] which is root of the graph.
     */
    fun retrofit(serverConfig: ServerConfig, accessToken: String? = null): Retrofit {
        val interceptors = mutableListOf<Interceptor>()
        if (accessToken?.isNotBlank() == true) {
            interceptors.add(accessTokenInterceptor(accessToken))
        }
        interceptors.add(userAgentInterceptor)

        if (serverConfig.customInterceptors?.isNotEmpty() == true) {
            interceptors.addAll(serverConfig.customInterceptors)
        }
        val okHttpClient = okHttpClient(serverConfig, interceptors)
        return retrofit(serverConfig, okHttpClient)
    }

    /**
     * Creates a cache for storing Retrofit services.
     */
    private fun retrofitCache(retrofit: Retrofit) = RetrofitServicesCache(retrofit)

    /**
     * Create [Retrofit] with OkHttpClient and Moshi.
     */
    private fun retrofit(serverConfig: ServerConfig, okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(serverConfig.baseUrl)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(ErrorHandlingCallAdapterFactory())
            .build()

    /**
     * Create [OkHttpClient] with interceptors and timeoutSeconds configurations.
     */
    private fun okHttpClient(serverConfig: ServerConfig,
                             customInterceptors: List<Interceptor>?): OkHttpClient =
        OkHttpClient.Builder().apply {
            serverConfig.networkInterceptors?.forEach { addNetworkInterceptor(it) }
            serverConfig.customInterceptors?.forEach { addInterceptor(it) }

            connectTimeout(serverConfig.timeoutSeconds, TimeUnit.SECONDS)
            readTimeout(serverConfig.timeoutSeconds, TimeUnit.SECONDS)
            writeTimeout(serverConfig.timeoutSeconds, TimeUnit.SECONDS)
            retryOnConnectionFailure(false)

            if (!customInterceptors.isNullOrEmpty()) {
                interceptors().addAll(customInterceptors)
            }

        }.build()

    /**
     * Create interceptor for adding an access token.
     */
    private fun accessTokenInterceptor(accessToken: String) =
        Interceptor { chain ->
            val request = chain.request()
            val builder = request.newBuilder()

            if (request.header(AUTHORIZATION_HEADER).isNullOrBlank()) {
                builder.addHeader(AUTHORIZATION_HEADER, "Bearer $accessToken")
            }
            chain.proceed(builder.build())
        }

    /**
     * After a successful authentication, an access token will be given. The [retrofit] object will
     * be updated to send the access token in every request. The dependency graph for setting up
     * [Retrofit] will re-created.
     */
     fun resetRetrofit(accessToken: String) {
        if (accessToken.isNotBlank()) {
            retrofit = retrofit(serverConfig, accessToken)
            retrofitServicesCache = retrofitCache(retrofit)
        }
    }

    companion object {
        private const val USER_AGENT_HEADER = "User-Agent"
        private const val AUTHORIZATION_HEADER = "Authorization"
        private const val USER_AGENT_HEADER_VALUE = "vimeo-networking-java-sdk-${ApiConstants.API_VERSION}"
    }

}
