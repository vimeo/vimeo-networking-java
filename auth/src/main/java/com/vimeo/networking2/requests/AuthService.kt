package com.vimeo.networking2.requests

import com.vimeo.networking2.VimeoAccount
import com.vimeo.networking2.adapters.VimeoCall
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

/**
 * All the authentication endpoints.
 */
interface AuthService {

    /**
     * Get an access token by providing the client id and client secret along with grant
     * and scope types.
     *
     * @param authHeader It is created from the client id and client secret.
     * @param grantType  Determines whether you have access to public or private accessToken.
     * @param scope      Determines what you want to access to in the ApiFailure API.
     *
     * @return A [VimeoAccount] that has an access token.
     *
     */
    @FormUrlEncoded
    @POST("oauth/authorize/client")
    fun authorizeWithClientCredentialsGrant(
        @Header("Authorization") authHeader: String,
        @Field("grant_type") grantType: String,
        @Field("scope") scope: String
    ): VimeoCall<VimeoAccount>

}
