package com.vimeo.networking2.requests.clientcredentials

import com.vimeo.networking2.ApiError
import com.vimeo.networking2.VimeoAccount
import com.vimeo.networking2.adapters.VimeoCallback
import com.vimeo.networking2.enums.GrantType
import com.vimeo.networking2.enums.ScopeType
import com.vimeo.networking2.requests.ApiResponse
import com.vimeo.networking2.requests.AuthCallback
import com.vimeo.networking2.requests.VimeoRequest
import com.vimeo.networking2.utils.AuthModule
import retrofit2.Response

/**
 * Interactor which performs auth request with client credentials.
 *
 * @param authService               Retrofit service for all auth requests.
 * @param authHeaders               Auth Header generated from the client id and client secret.
 * @param scopes                    List of [ScopeType]s.
 * @param authTokenCallback         Callback to inform you that the authentication was successful
 *                                  and the access token can be set for every request.
 */
class ClientCredentialsInteractor(
    private val authModule: AuthModule
) : ClientCredentialsAuthenticator {

    override fun authenticate(authCallback: AuthCallback): VimeoRequest {
        val call = authModule.authService.authorizeWithClientCredentialsGrant(
            authModule.authHeaders,
            GrantType.CLIENT_CREDENTIALS.value,
            authModule.scopes.joinToString())

        val apiResponseCallback = object : VimeoCallback<VimeoAccount> {

            override fun onSuccess(response: Response<VimeoAccount>) {
                response.body()?.accessToken?.let {
                    authModule.setAccessToken(it)
                    authCallback.onSuccess(ApiResponse.Success(it))
                }
            }

            override fun onApiError(apiError: ApiError) {
                authCallback.onApiError(ApiResponse.Failure.ApiFailure(apiError))
            }

            override fun onGenericError(requestCode: Int) {
                authCallback.onGenericError(ApiResponse.Failure.GenericFailure(requestCode))
            }

            override fun onExceptionError(t: Throwable) {
                authCallback.onExceptionError(ApiResponse.Failure.ExceptionFailure(t))
            }
        }
        call.enqueue(apiResponseCallback)

        return object: VimeoRequest {
            override fun cancel() {
                call.cancel()
            }
        }
    }

}
