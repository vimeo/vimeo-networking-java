package com.vimeo.networking2.config

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.vimeo.networking2.ApiConstants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Module which provides all the dependencies needed for setting up [Retrofit].
 */
internal object RetrofitSetupModule {

    private const val USER_AGENT_HEADER = "User-Agent"
    private const val AUTHORIZATION_HEADER = "Authorization"
    private const val USER_AGENT_HEADER_VALUE = "vimeo-networking-java-sdk-${ApiConstants.API_VERSION}"

    /**
     * Create [Retrofit] with OkHttpClient and Moshi.
     */
    fun providesRetrofit(serverConfig: ServerConfig, moshi: Moshi, okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(serverConfig.baseUrl)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

    /**
     * Create [Moshi] object for serialization and deserialization.
     */
    fun providesMoshi(): Moshi =
        Moshi.Builder()
            .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
            .build()

    /**
     * Create [OkHttpClient] with interceptors and timeoutSeconds configurations.
     */
    fun providesOkHttpClient(serverConfig: ServerConfig, customInterceptors: List<Interceptor>?): OkHttpClient =
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
    fun providesAccessTokenInterceptor(accessToken: String) =
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
    fun providesUserAgentInterceptor() =
        Interceptor { chain ->
            val request = chain.request()
            val builder = request.newBuilder()

            if (request.header(USER_AGENT_HEADER).isNullOrBlank()) {
                builder.addHeader(USER_AGENT_HEADER, USER_AGENT_HEADER_VALUE)
            }
            chain.proceed(builder.build())
        }

}
