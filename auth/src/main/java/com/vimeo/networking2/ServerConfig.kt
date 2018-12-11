package com.vimeo.networking2

import com.vimeo.networking2.enums.ScopeType
import okhttp3.Interceptor

/**
 * Configuration for authentication and making requests to the Vimeo API.
 *
 * @param clientId              Your client id provided from https://developer.vimeo.com/apps/.
 * @param clientSecret          Your client secret for authentication provided from https://developer.vimeo.com/apps/.
 * @param baseUrl               The base url for all requests to the Vimeo API. This is can be overridden to test against a staging sever.
 * @param scopes                A list of your scopes. See https://developer.vimeo.com/api/authentication#scopes.
 * @param certPinningEnabled    Enable certificate pining. It is disabled by default.
 * @param timeout               Read and the connection timeout for a request. The default time is 60 seconds.
 * @param interceptors          Interceptors to add to for requests.
 */
@Suppress("unused", "MemberVisibilityCanBePrivate")
class ServerConfig @JvmOverloads constructor(
    val clientId: String,
    val clientSecret: String,
    val baseUrl: String = "https://api.vimeo.com",
    val scopes: List<ScopeType> = listOf(ScopeType.PUBLIC),
    val certPinningEnabled: Boolean = false,
    val timeout: Int = 60,
    val interceptors: List<Interceptor>? = null)
