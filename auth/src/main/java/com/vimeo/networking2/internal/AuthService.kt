package com.vimeo.networking2.internal

import com.vimeo.networking2.AuthenticatedAccessToken
import com.vimeo.networking2.BasicAccessToken
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

/**
 * All the authentication endpoints.
 */
internal interface AuthService {

    /**
     * Get an access token by providing the client id and client secret along with grant
     * and scope types.
     *
     * @param authHeader It is created from the client id and client secret.
     * @param params     Client id, client secret and scopes.
     *
     * @return A [VimeoCall] that provides a [BasicAccessToken] that can be used for unauthenticated
     *         request.
     *
     */
    @POST("oauth/authorize/client")
    fun authorizeWithClientCredentialsGrant(
        @Header("Authorization") authHeader: String,
        @Body params: Map<AuthParam, String>
    ): VimeoCall<BasicAccessToken>

    /**
     * Used to either join or login to Vimeo by either using Google or Facebook.
     *
     * @param authHeader  Created from the client id and client secret.
     * @param params      Google or Facebook Authentication token and email configuration.
     *
     * @return A [VimeoCall] that provides a [AuthenticatedAccessToken] that can be used for
     *         authenticated request and also contains a user object.
     *
     */
    @POST("users")
    fun join(
        @Header("Authorization") authHeader: String,
        @Body params: Map<AuthParam, String>
    ): VimeoCall<AuthenticatedAccessToken>

    /**
     * Login with email and password.
     *
     * @param authHeader    Created from the client id and client secret.
     * @param params        Email address, password and GDPR option.
     *
     * @return A [VimeoCall] that provides a [AuthenticatedAccessToken] that can be used for
     *         authenticated request and also contains a user object.
     */
    @POST("oauth/authorize/password")
    fun logIn(
        @Header("Authorization") authHeader: String,
        @Body params: Map<AuthParam, String>
    ): VimeoCall<AuthenticatedAccessToken>

}
