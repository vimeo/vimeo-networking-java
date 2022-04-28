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
import com.vimeo.networking2.SsoConnection
import com.vimeo.networking2.SsoDomain
import com.vimeo.networking2.TeamToken
import com.vimeo.networking2.VimeoAccount
import com.vimeo.networking2.annotations.Internal
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * All the authentication endpoints.
 */
@Suppress("LongParameterList", "ComplexInterface")
internal interface AuthService {

    /**
     * Get an access token by providing the client id and client secret along with grant and scope types.
     *
     * @param authorization Created from the client id and client secret.
     * @param grantType The type of authorization grant that is being performed.
     * @param scopes The permissions scope that should be granted to the client.
     *
     * @return A [VimeoCall] that provides a [VimeoAccount] that can be used to perform unauthenticated requests.
     */
    @FormUrlEncoded
    @POST("oauth/authorize/client")
    fun authorizeWithClientCredentialsGrant(
        @Header(AUTHORIZATION) authorization: String,
        @Field(GRANT_TYPE) grantType: GrantType,
        @Field(SCOPE) scopes: Scopes
    ): VimeoCall<VimeoAccount>

    /**
     * Used to sign up for Vimeo using email.
     *
     * @param authorization Created from the client id and client secret.
     * @param name The name of the account that should be created.
     * @param email The email of the account that should be created.
     * @param password The password of the account that should be created.
     * @param scopes The permissions scope that should be granted to the client.
     * @param marketingOptIn True if the user is opting into marketing emails, false otherwise.
     *
     * @return A [VimeoCall] that provides a [VimeoAccount] that can be used to perform authenticated requests and also
     * contains a user object.
     */
    @FormUrlEncoded
    @POST("users")
    fun joinWithEmail(
        @Header(AUTHORIZATION) authorization: String,
        @Field(NAME) name: String,
        @Field(EMAIL) email: String,
        @Field(PASSWORD) password: String,
        @Field(SCOPE) scopes: Scopes,
        @Field(MARKETING_OPT_IN) marketingOptIn: Boolean
    ): VimeoCall<VimeoAccount>

    /**
     * Used to sign up for Vimeo using a Facebook authorization token.
     *
     * @param authorization Created from the client id and client secret.
     * @param username The username, usually an email address, that the user uses to log into Facebook.
     * @param token The Facebook token used to authorize with Facebook.
     * @param scopes The permissions scope that should be granted to the client.
     * @param marketingOptIn True if the user is opting into marketing emails, false otherwise.
     *
     * @return A [VimeoCall] that provides a [VimeoAccount] that can be used to perform authenticated requests and also
     * contains a user object.
     */
    @FormUrlEncoded
    @POST("users")
    fun joinWithFacebook(
        @Header(AUTHORIZATION) authorization: String,
        @Field(USERNAME) username: String,
        @Field(TOKEN) token: String,
        @Field(SCOPE) scopes: Scopes,
        @Field(MARKETING_OPT_IN) marketingOptIn: Boolean
    ): VimeoCall<VimeoAccount>

    /**
     * Used to sign up for Vimeo using a Google authorization token.
     *
     * @param authorization Created from the client id and client secret.
     * @param username The username, usually an email address, that the user uses to log into Google.
     * @param idToken The Google token used to authorize with Google.
     * @param scopes The permissions scope that should be granted to the client.
     * @param marketingOptIn True if the user is opting into marketing emails, false otherwise.     *
     *
     * @return A [VimeoCall] that provides a [VimeoAccount] that can be used to perform authenticated requests and also
     * contains a user object.
     */
    @FormUrlEncoded
    @POST("users")
    fun joinWithGoogle(
        @Header(AUTHORIZATION) authorization: String,
        @Field(USERNAME) username: String,
        @Field(ID_TOKEN) idToken: String,
        @Field(SCOPE) scopes: Scopes,
        @Field(MARKETING_OPT_IN) marketingOptIn: Boolean
    ): VimeoCall<VimeoAccount>

    /**
     * Login with email and password.
     *
     * @param authorization Created from the client id and client secret.
     * @param username The username, usually an email address, that the user used to create their account.
     * @param password The user's password.
     * @param grantType The type of authorization grant that is being performed.
     * @param scopes The permissions scope that should be granted to the client.
     *
     * @return A [VimeoCall] that provides a [VimeoAccount] that can be used to perform authenticated requests and also
     * contains a user object.
     */
    @FormUrlEncoded
    @POST("oauth/authorize/password")
    fun logInWithEmail(
        @Header(AUTHORIZATION) authorization: String,
        @Field(USERNAME) username: String,
        @Field(PASSWORD) password: String,
        @Field(GRANT_TYPE) grantType: GrantType,
        @Field(SCOPE) scopes: Scopes
    ): VimeoCall<VimeoAccount>

    /**
     * Used to log into Vimeo using a Facebook authorization token. If an account does not exist, this request will
     * fail. For this reason, [joinWithFacebook] is preferred since it supports both login and join, leaving this
     * function intentionally unused.
     *
     * @param authorization Created from the client id and client secret.
     * @param grantType The type of authorization grant that is being performed.
     * @param token The Facebook token used to authorize with Facebook.
     * @param scopes The permissions scope that should be granted to the client.
     *
     * @return A [VimeoCall] that provides a [VimeoAccount] that can be used to perform authenticated requests and also
     * contains a user object.
     */
    @Suppress("unused")
    @FormUrlEncoded
    @POST("oauth/authorize/facebook")
    fun logInWithFacebook(
        @Header(AUTHORIZATION) authorization: String,
        @Field(GRANT_TYPE) grantType: GrantType,
        @Field(TOKEN) token: String,
        @Field(SCOPE) scopes: Scopes
    ): VimeoCall<VimeoAccount>

    /**
     * Used to log into Vimeo using a Google authorization token. If an account does not exist, this request will fail.
     * For this reason, [joinWithGoogle] is preferred since it also supports both login and join, leaving this
     * function intentionally unused.
     *
     * @param authorization Created from the client id and client secret.
     * @param grantType The type of authorization grant that is being performed.
     * @param idToken The Google token used to authorize with Google.
     * @param scopes The permissions scope that should be granted to the client.
     *
     * @return A [VimeoCall] that provides a [VimeoAccount] that can be used to perform authenticated requests and also
     * contains a user object.
     */
    @Suppress("unused")
    @FormUrlEncoded
    @POST("oauth/authorize/google")
    fun logInWithGoogle(
        @Header(AUTHORIZATION) authorization: String,
        @Field(GRANT_TYPE) grantType: GrantType,
        @Field(ID_TOKEN) idToken: String,
        @Field(SCOPE) scopes: Scopes
    ): VimeoCall<VimeoAccount>

    /**
     * Creates a request to the code grant authorization endpoint. Used to direct the user to a URL where they can log
     * in and then redirect back to the app.
     *
     * @param clientId The client id of the app.
     * @param redirectUri The URI which should be redirected back to.
     * @param state An arbitrary value that will be returned in the redirect to identify the origin of the request.
     * @param scopes The permissions scope that should be granted to the client.
     *
     * @return A [VimeoCall] that creates a request to the code grant endpoint.
     */
    @GET("oauth/authorize?response_type=code")
    fun createCodeGrantRequest(
        @Query(CLIENT_ID) clientId: String,
        @Query(REDIRECT_URI) redirectUri: String,
        @Query(STATE) state: String,
        @Query(SCOPE) scopes: Scopes
    ): VimeoCall<Unit>

    /**
     * Authorize with the server using a code grant from a redirect URL.
     *
     * @param authorization Created from the client id and client secret.
     * @param redirectUri The URI which the user was redirected from.
     * @param authorizationCode The code obtained from the authorization grant.
     * @param grantType The type of authorization grant that is being performed.
     *
     * @return A [VimeoCall] that provides a [VimeoAccount] that can be used to perform authenticated requests and also
     * contains a user object.
     */
    @FormUrlEncoded
    @POST("oauth/access_token")
    fun authenticateWithCodeGrant(
        @Header(AUTHORIZATION) authorization: String,
        @Field(REDIRECT_URI) redirectUri: String,
        @Field(CODE) authorizationCode: String,
        @Field(GRANT_TYPE) grantType: GrantType
    ): VimeoCall<VimeoAccount>

    /**
     * Exchanges an old OAuth1 token for a shiny new OAuth2 token.
     *
     * @param authorization Created from the client id and client secret.
     * @param grantType The type of authorization grant that is being performed.
     * @param token The token being exchanged.
     * @param tokenSecret The token secret being exchanged.
     * @param scopes The permissions scope that should be granted to the client.
     *
     * @return A [VimeoCall] that provides a [VimeoAccount] that can be used to perform authenticated requests and also
     * contains a user object.
     */
    @FormUrlEncoded
    @POST("oauth/authorize/vimeo_oauth1")
    fun exchangeOAuth1Token(
        @Header(AUTHORIZATION) authorization: String,
        @Field(GRANT_TYPE) grantType: GrantType,
        @Field(TOKEN) token: String,
        @Field(TOKEN_SECRET) tokenSecret: String,
        @Field(SCOPE) scopes: Scopes
    ): VimeoCall<VimeoAccount>

    /**
     * Exchange an access token obtained from another source for an authenticated account.
     *
     * @param authorization Created from the client id and client secret.
     * @param token The token obtained from another source which will be used to authenticate the user.
     * @param scopes The permissions scope that should be granted to the client.
     *
     * @return A [VimeoCall] that provides a [VimeoAccount] that can be used to perform authenticated requests and also
     * contains a user object.
     */
    @FormUrlEncoded
    @POST("oauth/appexchange")
    fun exchangeAccessToken(
        @Header(AUTHORIZATION) authorization: String,
        @Field(ACCESS_TOKEN) token: String,
        @Field(SCOPE) scopes: Scopes
    ): VimeoCall<VimeoAccount>

    /**
     * Obtain a pin code which can be used to authorize a client application from another location.
     *
     * @param authorization Created from the client id and client secret.
     * @param grantType The type of authorization grant that is being performed.
     * @param scopes The permissions scope that should be granted to the client.
     *
     * @return A [VimeoCall] that provides a [PinCodeInfo] that can be used to log in with [logInWithPinCode].
     */
    @FormUrlEncoded
    @Headers("Cache-Control: no-cache, no-store")
    @POST("oauth/device")
    fun getPinCodeInfo(
        @Header(AUTHORIZATION) authorization: String,
        @Field(GRANT_TYPE) grantType: GrantType,
        @Field(SCOPE) scopes: Scopes
    ): VimeoCall<PinCodeInfo>

    /**
     * Log in using a pin code.
     *
     * @param authorization Created from the client id and client secret.
     * @param grantType The type of authorization grant that is being performed.
     * @param pinCode The pin code that is used to log the user in.
     * @param deviceCode The device code that is used to log the user in.
     * @param scopes The permissions scope that should be granted to the client.
     *
     * @return A [VimeoCall] that provides a [VimeoAccount] that can be used to perform authenticated requests and also
     * contains a user object.
     */
    @FormUrlEncoded
    @POST("oauth/device/authorize")
    fun logInWithPinCode(
        @Header(AUTHORIZATION) authorization: String,
        @Field(GRANT_TYPE) grantType: GrantType,
        @Field(USER_CODE) pinCode: String,
        @Field(DEVICE_CODE) deviceCode: String,
        @Field(SCOPE) scopes: Scopes
    ): VimeoCall<VimeoAccount>

    /**
     * Check whether an [SsoConnection] exists for the provided [email].
     *
     * @param authorization Created from the client id and client secret.
     * @param email The email used to check for the presence of an [SsoConnection].
     *
     * @return A [VimeoCall] that provides an [SsoConnection] that can be used to perform SSO.
     */
    @FormUrlEncoded
    @PUT("sso_connections/verify")
    fun checkSsoConnection(
        @Header(AUTHORIZATION) authorization: String,
        @Field(EMAIL) email: String
    ): VimeoCall<SsoConnection>

    /**
     * Searches Vimeo for the presence of a supported SSO domain that matches the one provided by the [domain]
     * parameter.
     *
     * @param authorization Created from the client id and client secret.
     * @param domain A domain, also known as hostname, that might be supported for SSO by the Vimeo API.
     *
     * @return A [VimeoCall] that provides a [SsoDomain] that can be used to perform SSO.
     */
    @Deprecated("deprecated in favor of checkSsoConnection")
    @Internal
    @GET("sso_domains")
    fun getSsoDomain(
        @Header(AUTHORIZATION) authorization: String,
        @Query(DOMAIN) domain: String
    ): VimeoCall<SsoDomain>

    /**
     * Creates a request to the SSO grant authorization endpoint. Used to direct the user to a URL where they can log
     * in via SSO and then redirect back to the app.
     *
     * @param uri The base URI obtained from [SsoDomain.connectUrl].
     * @param redirectUri The URI which should be redirected back to.
     * @param state A random value that will be returned in the redirect to identify the origin of the request.
     */
    @Internal
    @GET
    fun createSsoGrantRequest(
        @Url uri: String,
        @Query(REDIRECT_URI) redirectUri: String,
        @Query(STATE) state: String
    ): VimeoCall<Unit>

    /**
     * Log into the server using an authorization code grant from an enterprise SSO supported domain using Auth0. If an
     * account does not already exist, this request will fail. For this reason, [joinWithSsoCodeGrant] is preferred
     * since it supports both join and login, leaving this function intentionally unused.
     *
     * @param authorization Created from the client id and client secret.
     * @param authorizationCode The Auth0 code to verify.
     * @param redirectUri The URI used to verify the token.
     * @param marketingOptIn True if the user is opting into marketing emails, false otherwise.
     *
     * @return A [VimeoCall] that provides a [VimeoAccount] that can be used to perform authenticated requests and also
     * contains a user object.
     */
    @Suppress("unused")
    @FormUrlEncoded
    @Internal
    @POST("oauth/authorize/auth0")
    fun logInWithSsoCodeGrant(
        @Header(AUTHORIZATION) authorization: String,
        @Field(AUTHORIZATION_CODE) authorizationCode: String,
        @Field(REDIRECT_URI) redirectUri: String,
        @Field(MARKETING_OPT_IN) marketingOptIn: Boolean
    ): VimeoCall<VimeoAccount>

    /**
     * Log into the server or join using an authorization code grant from an enterprise SSO supported domain using
     * Auth0. If an account does not already exist, an account will be created for the user.
     *
     * @param authorization Created from the client id and client secret.
     * @param authorizationCode The Auth0 code to verify.
     * @param redirectUri The URI used to verify the token.
     * @param marketingOptIn True if the user is opting into marketing emails, false otherwise.
     *
     * @return A [VimeoCall] that provides a [VimeoAccount] that can be used to perform authenticated requests and also
     * contains a user object.
     */
    @FormUrlEncoded
    @Internal
    @POST("users")
    fun joinWithSsoCodeGrant(
        @Header(AUTHORIZATION) authorization: String,
        @Field(AUTHORIZATION_CODE) authorizationCode: String,
        @Field(REDIRECT_URI) redirectUri: String,
        @Field(MARKETING_OPT_IN) marketingOptIn: Boolean
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

    /**
     * Provides a team's token by team's id.
     *
     * @param teamId The team's id which is used to find token.
     *
     * @return A [VimeoCall] that provides a [TeamToken].
     */
    @GET("auth/teams/{team_id}/magisto_access_token")
    fun getMagistoTeamToken(
        @Header(AUTHORIZATION) authorization: String,
        @Path(value = "team_id") teamId: String
    ): VimeoCall<TeamToken>

    companion object {
        const val AUTHORIZATION = "Authorization"
        const val SCOPE = "scope"
        const val GRANT_TYPE = "grant_type"
        const val REDIRECT_URI = "redirect_uri"
        const val STATE = "state"
        const val MARKETING_OPT_IN = "marketing_opt_in"
        const val AUTHORIZATION_CODE = "authorization_code"
        const val NAME = "name"
        const val EMAIL = "email"
        const val PASSWORD = "password"
        const val TOKEN = "token"
        const val TOKEN_SECRET = "token_secret"
        const val DOMAIN = "domain"
        const val USER_CODE = "user_code"
        const val DEVICE_CODE = "device_code"
        const val ACCESS_TOKEN = "access_token"
        const val CODE = "code"
        const val CLIENT_ID = "client_id"
        const val ID_TOKEN = "id_token"
        const val USERNAME = "username"
    }
}
