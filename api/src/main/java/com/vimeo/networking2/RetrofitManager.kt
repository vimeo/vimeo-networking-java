package com.vimeo.networking2

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Create an instance of this class to create and configure a [RetrofitManager].
 *
 * @param serverConfig All server related configuration.
 */
class RetrofitManager(private val serverConfig: ServerConfig) {

    /**
     * User Agent String.
     */
    private val userAgentString = "vimeo-networking-java-sdk-" + 0.1

    /**
     * Lazy load okHttpClient.
     */
    private var okHttpClient = createOkHttpClient()

    /**
     * Creates an OkHttpClient object and sets an application level interceptor that
     * adds the User-Agent and Authorization headers to each request.
     *
     * @return OkHttpClient [OkHttpClient]
     */
    private fun createOkHttpClient() =
        OkHttpClient.Builder().apply {
            serverConfig.networkInterceptors?.forEach { addNetworkInterceptor(it) }
            serverConfig.customInterceptors?.forEach { addInterceptor(it) }

            connectTimeout(serverConfig.timeout, TimeUnit.SECONDS)
            readTimeout(serverConfig.timeout, TimeUnit.SECONDS)
            writeTimeout(serverConfig.timeout, TimeUnit.SECONDS)
            retryOnConnectionFailure(false)

            interceptors().add(Interceptor { chain ->
                val request = chain.request()
                val builder = request.newBuilder()
                builder.addHeader(USER_AGENT, userAgentString)
                chain.proceed(builder.build())
            })
        }.build()

    /**
     * Lazy load Retrofit.
     */
    internal var retrofit =
        Retrofit.Builder()
            .baseUrl(serverConfig.baseUrl)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    /**
     * Set the access token to be sent as part of the header by adding an interceptor to [okHttpClient].
     */
    fun setAccessToken(accessToken: String) {
        if (accessToken.isNotBlank()) {
            okHttpClient = okHttpClient.newBuilder().apply {
                interceptors().add(createAccessTokenInterceptor(accessToken))
            }.build()
            retrofit = retrofit.newBuilder().client(okHttpClient).build()
        }
    }

    /**
     * Get interceptor for adding access token.
     *
     * @param accessToken  The access token to add in the auth header.
     *
     * @return interceptor for adding access token to the auth header.
     */
    private fun createAccessTokenInterceptor(accessToken: String) =
        Interceptor { chain ->
            val request = chain.request()
            val builder = request.newBuilder()

            builder.addHeader(USER_AGENT, userAgentString)
            if (request.header(AUTHORIZATION).isNullOrBlank()) {
                builder.addHeader(AUTHORIZATION, "Bearer $accessToken")
            }
            chain.proceed(builder.build())
        }

    companion object {
        private const val AUTHORIZATION = "Authorization"
        private const val USER_AGENT = "User-Agent"
    }

}
