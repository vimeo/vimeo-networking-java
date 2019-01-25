package com.vimeo.networking2.config

import com.vimeo.networking2.ApiConstants
import com.vimeo.networking2.enums.ScopeType
import okhttp3.Interceptor

/**
 * Configuration for authentication and making requests to the ApiFailure API.
 *
 * @param clientId                    Your client id provided from https://developer.vimeo.com/apps/.
 * @param clientSecret                Your client secret for authentication provided from https://developer.vimeo.com/apps/.
 * @param baseUrl                     The base url for all requests to the ApiFailure API. This is can be overridden to test against a staging server.
 * @param scopes                      A list of your scopes. See https://developer.vimeo.com/api/authentication#scopes.
 * @param certPinningEnabled          Enable certificate pining. It is disabled by default.
 * @param timeoutSeconds              Read and connection timeout for a request. The default time is 60 seconds.
 * @param networkInterceptors         Network interceptors to add for requests.
 * @param customInterceptors          Custom network interceptors to add for requests.
 */
@Suppress("unused", "MemberVisibilityCanBePrivate")
class ServerConfig @JvmOverloads constructor(
    val clientId: String,
    val clientSecret: String,
    val baseUrl: String = ApiConstants.BASE_URL,
    val scopes: List<ScopeType> = listOf(ScopeType.PUBLIC),
    val certPinningEnabled: Boolean = false,
    val timeoutSeconds: Long = ApiConstants.READ_CONNECTION_TIMEOUT_SECONDS,
    val networkInterceptors: List<Interceptor>? = null,
    val customInterceptors: List<Interceptor>? = null
)
