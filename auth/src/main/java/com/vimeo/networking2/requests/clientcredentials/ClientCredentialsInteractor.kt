package com.vimeo.networking2.requests.clientcredentials

import com.vimeo.networking2.ApiError
import com.vimeo.networking2.VimeoAccount
import com.vimeo.networking2.enums.GrantType
import com.vimeo.networking2.requests.ApiRequestRetrofitCallback
import com.vimeo.networking2.requests.ApiResponseCallback
import com.vimeo.networking2.requests.AuthCallback
import com.vimeo.networking2.requests.AuthService
import com.vimeo.networking2.utils.ApiResponseErrorConverter

/**
 * Interactor which performs auth request with client credentials.
 *
 * @param apiResponseErrorConverter Used to convert error response to [ApiError].
 * @param authService               Retrofit service for all auth requests.
 * @param authHeaders               Auth Header generated from the client id and client secret.
 * @param scopes                    Comma separated list of scopes.
 * @param setAuthTokenCallback      Callback to inform you that the authentication was successful
 *                                  and the access token can be set for every request.
 */
class ClientCredentialsInteractor(
    private val apiResponseErrorConverter: ApiResponseErrorConverter,
    private val authService: AuthService,
    private val authHeaders: String,
    private val scopes: String,
    private val setAuthTokenCallback: (String) -> Unit
): ClientCredentialsAuthenticator {

    override fun authenticate(authCallback: AuthCallback) {
        val call = authService.authorizeWithClientCredentialsGrant(
            authHeaders,
            GrantType.CLIENT_CREDENTIALS.value,
            scopes)

        val apiResponseCallback = object : ApiResponseCallback<VimeoAccount> {

            override fun onSuccess(response: VimeoAccount) {
                response.accessToken?.let {
                    setAuthTokenCallback(it)
                    authCallback.onSuccess(it)
                }
            }
            override fun onError(apiError: ApiError) {
                authCallback.onError(apiError)
            }
        }

        call.enqueue(ApiRequestRetrofitCallback(apiResponseErrorConverter, apiResponseCallback))
    }

}
