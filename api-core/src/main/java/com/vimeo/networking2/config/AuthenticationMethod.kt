package com.vimeo.networking2.config

import com.vimeo.networking2.Scopes
import okhttp3.Credentials

/**
 * The authentication method that will be used to access the API.
 *
 * @param basicAuthHeader The authentication header that will be used when there is no available logged in account.
 */
sealed class AuthenticationMethod(val basicAuthHeader: String) {

    /**
     * A fixed access token that will be used to access the Vimeo API and disallows auth requests.
     *
     * @param accessToken The access token that will be used.
     */
    class AccessToken(accessToken: String) : AuthenticationMethod("Bearer $accessToken")

    /**
     * The client credentials, including the identifier and secret, that will be used to authenticate with the API.
     *
     * @param clientId The Vimeo API client ID. Should be obtained from [https://developer.vimeo.com/apps].
     * @param clientSecret The Vimeo API client secret. Should be obtained from [https://developer.vimeo.com/apps].
     * @param scopes The permission scopes requested by the client.
     */
    class ClientCredentials(
        val clientId: String,
        clientSecret: String,
        val scopes: Scopes
    ) : AuthenticationMethod(Credentials.basic(clientId, clientSecret))
}
