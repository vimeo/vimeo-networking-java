package com.vimeo.networking2

import com.vimeo.networking2.enums.ScopeType
import okhttp3.Interceptor

/**
 * Configuration for authentication and making requests to the Vimeo API.
 *
 * @param clientId          Your client id provided from https://developer.vimeo.com/apps/.
 * @param clientSecret      Your client secret for authentication provided from https://developer.vimeo.com/apps/.
 * @param scopes            A list of your scopes. See https://developer.vimeo.com/api/authentication#scopes.
 * @param accessToken       If you've only provided an access token, you'll only be able to make requests
 *                          for the given access token's account.
 * @param baseUrl           The base url for all requests to the Vimeo API. This is can be overridden to test against a staging sever.
 * @param timeout           Read and the connection timeout for a request. The default time is 60 seconds.
 */
class Config(val clientId: String? = null,
             val clientSecret: String? = null,
             val scopes: List<ScopeType> = listOf(ScopeType.PUBLIC),
             val accessToken: String? = null,
             val baseUrl: String = "https://api.vimeo.com",
             val timeout: Int = 60,
             val networkInterceptors: List<Interceptor> = emptyList(),
             val customInterceptors: List<Interceptor> = emptyList())
