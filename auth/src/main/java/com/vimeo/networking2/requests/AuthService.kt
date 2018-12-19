package com.vimeo.networking2.requests

import com.vimeo.networking2.VimeoAccount
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

/**
 * All the authentication endpoints.
 */
interface AuthService {

    @FormUrlEncoded
    @POST("oauth/authorize/client")
    fun authorizeWithClientCredentialsGrant(@Header("Authorization") authHeader: String,
                                            @Field("grant_type") grantType: String,
                                            @Field("scope") scope: String
    ): Call<VimeoAccount>

}
