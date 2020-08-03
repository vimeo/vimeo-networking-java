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

import com.vimeo.networking2.GrantType
import com.vimeo.networking2.PinCodeInfo
import com.vimeo.networking2.Scopes
import com.vimeo.networking2.VimeoAccount
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
     * @param authorization Created from the client id and client secret.
     * @param grantType The type of authorization grant that is being performed.
     * @param scope The permissions scope that should be granted to the client.
     *
     * @return A [VimeoCall] that provides a [VimeoAccount] that can be used to perform unauthenticated requests.
     */
    @FormUrlEncoded
    @POST("oauth/authorize/client")
    fun authorizeWithClientCredentialsGrant(
        @Header(AUTHORIZATION) authorization: String,
        @Field(GRANT_TYPE) grantType: GrantType,
        @Field(SCOPE) scope: Scopes
    ): VimeoCall<VimeoAccount>

    /**
     * Used to sign up for Vimeo using email.
     *
     * @param authorization Created from the client id and client secret.
     * @param name The name of the account that should be created.
     * @param email The email of the account that should be created.
     * @param password The password of the account that should be created.
     * @param scope The permissions scope that should be granted to the client.
     * @param marketingOptIn True if the user is opting into marketing emails, false otherwise.
     *
     * @return A [VimeoCall] that provides a [VimeoAccount] that can be used to perform authenticated requests and also
     * contains a user object.
     */
    @FormUrlEncoded
    @POST("users")
    fun joinWithEmail(
        @Header(AUTHORIZATION) authorization: String,
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field(SCOPE) scope: Scopes,
        @Field("marketing_opt_in") marketingOptIn: Boolean
    ): VimeoCall<VimeoAccount>

    /**
     * Used to sign up for Vimeo using a Facebook authorization token.
     *
     * @param authorization Created from the client id and client secret.
     * @param email The email the user uses to log into Facebook.
     * @param token The Facebook token used to authorize with Facebook.
     * @param scope The permissions scope that should be granted to the client.
     * @param marketingOptIn True if the user is opting into marketing emails, false otherwise.
     *
     * @return A [VimeoCall] that provides a [VimeoAccount] that can be used to perform authenticated requests and also
     * contains a user object.
     */
    @FormUrlEncoded
    @POST("users")
    fun joinWithFacebook(
        @Header(AUTHORIZATION) authorization: String,
        @Field("username") email: String,
        @Field("token") token: String,
        @Field(SCOPE) scope: Scopes,
        @Field("marketing_opt_in") marketingOptIn: Boolean
    ): VimeoCall<VimeoAccount>

    /**
     * Used to sign up for Vimeo using a Google authorization token.
     *
     * @param authorization Created from the client id and client secret.
     * @param email The email the user uses to log into Google.
     * @param idToken The Google token used to authorize with Google.
     * @param scope The permissions scope that should be granted to the client.
     * @param marketingOptIn True if the user is opting into marketing emails, false otherwise.     *
     *
     * @return A [VimeoCall] that provides a [VimeoAccount] that can be used to perform authenticated requests and also
     * contains a user object.
     */
    @FormUrlEncoded
    @POST("users")
    fun joinWithGoogle(
        @Header(AUTHORIZATION) authorization: String,
        @Field("username") email: String,
        @Field("id_token") idToken: String,
        @Field(SCOPE) scope: Scopes,
        @Field("marketing_opt_in") marketingOptIn: Boolean
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

    /**
     * Used to log into Vimeo using a Facebook authorization token.
     *
     * @param authorization Created from the client id and client secret.
     * @param grantType The type of authorization grant that is being performed.
     * @param token The Facebook token used to authorize with Facebook.
     * @param scope The permissions scope that should be granted to the client.
     *
     * @return A [VimeoCall] that provides a [VimeoAccount] that can be used to perform authenticated requests and also
     * contains a user object.
     */
    @FormUrlEncoded
    @POST("oauth/authorize/facebook")
    fun logInWithFacebook(
        @Header(AUTHORIZATION) authorization: String,
        @Field(GRANT_TYPE) grantType: GrantType,
        @Field("token") token: String,
        @Field(SCOPE) scope: Scopes
    ): VimeoCall<VimeoAccount>

    /**
     * Used to log into Vimeo using a Google authorization token.
     *
     * @param authorization Created from the client id and client secret.
     * @param grantType The type of authorization grant that is being performed.
     * @param idToken The Google token used to authorize with Google.
     * @param scope The permissions scope that should be granted to the client.
     *
     * @return A [VimeoCall] that provides a [VimeoAccount] that can be used to perform authenticated requests and also
     * contains a user object.
     */
    @FormUrlEncoded
    @POST("oauth/authorize/google")
    fun logInWithGoogle(
        @Header(AUTHORIZATION) authorization: String,
        @Field(GRANT_TYPE) grantType: GrantType,
        @Field("id_token") idToken: String,
        @Field(SCOPE) scope: Scopes
    ): VimeoCall<VimeoAccount>

    /**
     * Authorize with the server using a code grant from a redirect URL.
     *
     * @param authorization Created from the client id and client secret.
     * @param redirectUri The URI which the user was redirected from.
     * @param code The code obtained from the authorization grant.
     * @param grantType The type of authorization grant that is being performed.
     *
     * @return A [VimeoCall] that provides a [VimeoAccount] that can be used to perform authenticated requests and also
     * contains a user object.
     */
    @FormUrlEncoded
    @POST("oauth/access_token")
    fun authenticateWithCodeGrant(
        @Header(AUTHORIZATION) authorization: String,
        @Field("redirect_uri") redirectUri: String,
        @Field("code") code: String,
        @Field(GRANT_TYPE) grantType: GrantType
    ): VimeoCall<VimeoAccount>

    /**
     * Exchanges an old OAuth One token for a shiny new OAuth2 token.
     *
     * @param authorization Created from the client id and client secret.
     * @param grantType The type of authorization grant that is being performed.
     * @param token The token being exchanged.
     * @param tokenSecret The token secret being exchanged.
     * @param scope The permissions scope that should be granted to the client.
     *
     * @return A [VimeoCall] that provides a [VimeoAccount] that can be used to perform authenticated requests and also
     * contains a user object.
     */
    @FormUrlEncoded
    @POST("oauth/authorize/vimeo_oauth1")
    fun exchangeOAuthOneToken(
        @Header(AUTHORIZATION) authorization: String,
        @Field(GRANT_TYPE) grantType: GrantType,
        @Field("token") token: String,
        @Field("token_secret") tokenSecret: String,
        @Field(SCOPE) scope: Scopes
    ): VimeoCall<VimeoAccount>

    /**
     * Exchange a token obtained through SSO for an authenticated account.
     *
     * @param authorization Created from the client id and client secret.
     * @param token The token obtained through SSO which will be used to authenticate the user.
     * @param scope The permissions scope that should be granted to the client.
     *
     * @return A [VimeoCall] that provides a [VimeoAccount] that can be used to perform authenticated requests and also
     * contains a user object.
     */
    @FormUrlEncoded
    @POST("oauth/appexchange")
    fun ssoTokenExchange(
        @Header(AUTHORIZATION) authorization: String,
        @Field("access_token") token: String,
        @Field(SCOPE) scope: Scopes
    ): VimeoCall<VimeoAccount>

    /**
     * Obtain a pin code which can be used to authorize a client application from another location.
     *
     * @param authorization Created from the client id and client secret.
     * @param grantType The type of authorization grant that is being performed.
     * @param scope The permissions scope that should be granted to the client.
     *
     * @return A [VimeoCall] that provides a [PinCodeInfo] that can be used to log in with [logInWithPinCode].
     */
    @FormUrlEncoded
    @Headers("Cache-Control: no-cache, no-store")
    @POST("oauth/device")
    fun getPinCodeInfo(
        @Header(AUTHORIZATION) authorization: String,
        @Field(GRANT_TYPE) grantType: GrantType,
        @Field(SCOPE) scope: Scopes
    ): VimeoCall<PinCodeInfo>

    /**
     * Log in using a pin code.
     *
     * @param authorization Created from the client id and client secret.
     * @param grantType The type of authorization grant that is being performed.
     * @param pinCode The pin code that is used to log the user in.
     * @param deviceCode The device code that is used to log the user in.
     * @param scope The permissions scope that should be granted to the client.
     *
     * @return A [VimeoCall] that provides a [VimeoAccount] that can be used to perform authenticated requests and also
     * contains a user object.
     */
    @FormUrlEncoded
    @POST("oauth/device/authorize")
    fun logInWithPinCode(
        @Header(AUTHORIZATION) authorization: String,
        @Field(GRANT_TYPE) grantType: GrantType,
        @Field("user_code") pinCode: String,
        @Field("device_code") deviceCode: String,
        @Field(SCOPE) scope: Scopes
    ): VimeoCall<VimeoAccount>

    /**
     * Logs out of the provided authorization header.
     *
     * @param authorization The token which will be deleted on the server. After this token is deleted, it will no
     * longer work when making other requests.
     *
     * @return A [VimeoCall] that will respond with an empty response when the deletion completes successfully.
     */
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
