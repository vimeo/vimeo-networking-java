package com.vimeo.networking2.internal

import com.vimeo.networking2.VimeoAccount
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
     * @param grantType  Determines whether you have access to public or private data.
     * @param scope      Determines what you want to access to in the Vimeo API.
     *
     * @return A [VimeoAccount] that has an access token.
     *
     */
    @POST("oauth/authorize/client")
    fun authorizeWithClientCredentialsGrant(
        @Header("Authorization") authHeader: String,
        @Body params: Map<AuthParam, String>
    ): VimeoCall<VimeoAccount>

    /**
     * Used to either join or login to Vimeo by either using Google or Facebook.
     *
     * @param authHeader  Created from the client id and client secret.
     * @param params      Google or Facebook Authentication token and email configuration.
     */
    @POST("users")
    fun join(
        @Header("Authorization") authHeader: String,
        @Body params: Map<AuthParam, String>
    ): VimeoCall<VimeoAccount>

    /**
     * Login with email and password.
     *
     * @param authHeader    Created from the client id and client secret.
     * @param params        Email address, password and GDPR option.
     */
    @POST("oauth/authorize/password")
    fun logIn(
        @Header("Authorization") authHeader: String,
        @Body params: Map<AuthParam, String>
    ): VimeoCall<VimeoAccount>

}
