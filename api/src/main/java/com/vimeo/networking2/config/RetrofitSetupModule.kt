package com.vimeo.networking2.config

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.vimeo.networking2.ApiConstants
import com.vimeo.networking2.ApiError
import com.vimeo.networking2.utils.ErrorResponseConverter
import com.vimeo.networking2.utils.ErrorResponseConverterImpl
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Module which provides all the dependencies needed for setting up [Retrofit].
 */
object RetrofitSetupModule {

    private const val USER_AGENT_HEADER = "User-Agent"
    private const val AUTHORIZATION_HEADER = "Authorization"
    private const val USER_AGENT_HEADER_VALUE = "vimeo-networking-java-sdk-${ApiConstants.API_VERSION}"

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
        return retrofit(serverConfig, moshi, okHttpClient)
    }

    /**
     * Creates a cache for storing Retrofit services.
     */
    fun retrofitCache(retrofit: Retrofit) = RetrofitServicesCache(retrofit)

    /**
     * Converter for an error response.
     */
    fun errorResponseConverter(retrofit: Retrofit): ErrorResponseConverter =
        ErrorResponseConverterImpl(retrofitErrorConverter(retrofit))

    /**
     * Get authentication headers.
     */
    fun authHeaders(clientId: String, clientSecret: String): String =
        Credentials.basic(clientId, clientSecret)

    /**
     * Create [Retrofit] with OkHttpClient and Moshi.
     */
    private fun retrofit(serverConfig: ServerConfig, moshi: Moshi,
                         okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(serverConfig.baseUrl)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    /**
     * Create [Moshi] object for serialization and deserialization.
     */
    private val moshi = Moshi.Builder()
        .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
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
     * Get a request body converter from Retrofit.
     */
    private fun retrofitErrorConverter(retrofit: Retrofit): Converter<ResponseBody, ApiError> =
        retrofit.responseBodyConverter<ApiError>(
            ApiError::class.java,
            emptyArray()
        )


}
