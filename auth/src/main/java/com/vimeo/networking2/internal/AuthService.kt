/*
 * Copyright (c) 2020 Vimeo (https://vimeo.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.vimeo.networking2.internal

import com.vimeo.networking2.BasicAccessToken
import com.vimeo.networking2.GrantType
import com.vimeo.networking2.PinCodeInfo
import com.vimeo.networking2.Scopes
import com.vimeo.networking2.VimeoAccount
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

/**
 * All the authentication endpoints.
 */
@Suppress("unused", "LongParameterList", "ComplexInterface")
internal interface AuthService {

    /**
     * Get an access token by providing the client id and client secret along with grant and scope types.
     *
     * @param authorization It is created from the client id and client secret.
     * @param grantType The type of authorization grant that is being performed.
     * @param scope The permissions scope that should be granted to the client.
     *
     * @return A [VimeoCall] that provides a [BasicAccessToken] that can be used to perform unauthenticated requests.
     */
    @FormUrlEncoded
    @POST("oauth/authorize/client")
    fun authorizeWithClientCredentialsGrant(
        @Header(AUTHORIZATION) authorization: String,
        @Field(GRANT_TYPE) grantType: GrantType,
        @Field(SCOPE) scope: Scopes
    ): VimeoCall<BasicAccessToken>

    /**
     * Used to sign up for Vimeo using email.
     *
     * @param authorization Created from the client id and client secret.
     * @param name The name of the account that should be created.
     * @param email The email of the account that should be created.
     * @param password The password of the account that should be created.
     * @param scope The permissions scope that should be granted to the client.
     * @param params Extra parameters associated with the account creation, such as marketing opt in.
     *
     * @return A [VimeoCall] that provides a [VimeoAccount] that can be used to perform authenticated requests and also
     * contains a user object.
     */
    @FormUrlEncoded
    @POST("users")
    fun joinWithEmail(
        @Header(AUTHORIZATION) authorization: String,
        @Field("name") name: String,
        @Field("username") email: String,
        @Field("password") password: String,
        @Field(SCOPE) scope: Scopes,
        @Body params: Map<AuthParam, String>
    ): VimeoCall<VimeoAccount>

    @FormUrlEncoded
    @POST("users")
    fun joinWithFacebook(
        @Header(AUTHORIZATION) authorization: String,
        @Field("username") email: String,
        @Field("token") token: String,
        @Field(SCOPE) scope: Scopes,
        @Body params: Map<AuthParam, String>
    ): VimeoCall<VimeoAccount>

    @FormUrlEncoded
    @POST("users")
    fun joinWithGoogle(
        @Header(AUTHORIZATION) authorization: String,
        @Field("username") email: String,
        @Field("id_token") idToken: String,
        @Field(SCOPE) scope: Scopes,
        @Body params: Map<AuthParam, String>
    ): VimeoCall<VimeoAccount>

    /**
     * Login with email and password.
     *
     * @param authorization Created from the client id and client secret.
     * @param email The email the user used to create their account.
     * @param password The user's password.
     * @param grantType The type of authorization grant that is being performed.
     * @param scope The permissions scope that should be granted to the client.
     *
     * @return A [VimeoCall] that provides a [VimeoAccount] that can be used to perform authenticated requests and also
     * contains a user object.
     */
    @FormUrlEncoded
    @POST("oauth/authorize/password")
    fun logInWithEmail(
        @Header(AUTHORIZATION) authorization: String,
        @Field("username") email: String,
        @Field("password") password: String,
        @Field(GRANT_TYPE) grantType: GrantType,
        @Field(SCOPE) scope: Scopes
    ): VimeoCall<VimeoAccount>

    @FormUrlEncoded
    @POST("oauth/authorize/facebook")
    fun logInWithFacebook(
        @Header(AUTHORIZATION) authorization: String,
        @Field(GRANT_TYPE) grantType: GrantType,
        @Field("token") token: String,
        @Field(SCOPE) scope: Scopes
    ): VimeoCall<VimeoAccount>

    @FormUrlEncoded
    @POST("oauth/authorize/google")
    fun logInWithGoogle(
        @Header(AUTHORIZATION) authorization: String,
        @Field(GRANT_TYPE) grantType: GrantType,
        @Field("id_token") idToken: String,
        @Field(SCOPE) scope: Scopes
    ): VimeoCall<VimeoAccount>

    @FormUrlEncoded
    @POST("oauth/access_token")
    fun authenticateWithCodeGrant(
        @Header(AUTHORIZATION) authorization: String,
        @Field("redirect_uri") redirectURI: String,
        @Field("code") code: String,
        @Field(GRANT_TYPE) grantType: GrantType
    ): VimeoCall<VimeoAccount>

    @FormUrlEncoded
    @POST("oauth/authorize/vimeo_oauth1")
    fun exchangeOAuthOneToken(
        @Header(AUTHORIZATION) authorization: String,
        @Field(GRANT_TYPE) grantType: GrantType,
        @Field("token") token: String,
        @Field("token_secret") tokenSecret: String,
        @Field(SCOPE) scope: Scopes
    ): VimeoCall<VimeoAccount>

    @FormUrlEncoded
    @POST("oauth/appexchange")
    fun ssoTokenExchange(
        @Header(AUTHORIZATION) basicAuth: String,
        @Field("access_token") token: String,
        @Field(SCOPE) scope: Scopes
    ): VimeoCall<VimeoAccount>

    @FormUrlEncoded
    @Headers("Cache-Control: no-cache, no-store")
    @POST("oauth/device")
    fun getPinCodeInfo(
        @Header(AUTHORIZATION) authorization: String,
        @Field(GRANT_TYPE) grantType: GrantType,
        @Field(SCOPE) scope: Scopes
    ): VimeoCall<PinCodeInfo>

    @FormUrlEncoded
    @POST("oauth/device/authorize")
    fun logInWithPinCode(
        @Header(AUTHORIZATION) authorization: String,
        @Field(GRANT_TYPE) grantType: GrantType,
        @Field("user_code") pinCode: String,
        @Field("device_code") deviceCode: String,
        @Field(SCOPE) scope: Scopes
    ): VimeoCall<VimeoAccount>

    @DELETE("tokens")
    fun logOut(
        @Header(AUTHORIZATION) authorization: String
    ): VimeoCall<Unit>

    companion object {
        private const val AUTHORIZATION = "Authorization"
        private const val SCOPE = "scope"
        private const val GRANT_TYPE = "grant_type"
    }

}
