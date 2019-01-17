package com.vimeo.networking2.requests.clientcredentials

import com.vimeo.networking2.ApiError
import com.vimeo.networking2.VimeoAccount
import com.vimeo.networking2.enums.GrantType
import com.vimeo.networking2.enums.ScopeType
import com.vimeo.networking2.requests.ApiResponse
import com.vimeo.networking2.requests.AuthCallback
import com.vimeo.networking2.requests.AuthService
import com.vimeo.networking2.utils.ErrorResponseConverter
import com.vimeo.networking2.utils.awaitResponse
import com.vimeo.networking2.utils.createApiErrorForCustomMessage
import kotlinx.coroutines.*
import retrofit2.Response

/**
 * Interactor which performs auth request with client credentials.
 *
 * @param errorResponseConverter    Used to convert error response to [ApiError].
 * @param authService               Retrofit service for all auth requests.
 * @param authHeaders               Auth Header generated from the client id and client secret.
 * @param apiScopes                 List of [ScopeType]s.
 * @param authTokenCallback         Callback to inform you that the authentication was successful
 *                                  and the access token can be set for every request.
 */
class ClientCredentialsInteractor(
    private val errorResponseConverter: ErrorResponseConverter,
    private val authService: AuthService,
    private val authHeaders: String,
    private val apiScopes: List<ScopeType>,
    private val coroutineScope: CoroutineScope,
    private val authTokenCallback: (String) -> Unit
) : ClientCredentialsAuthenticator {

    override fun authenticate(authCallback: AuthCallback) {
        coroutineScope.launch {
            val authResponse = performAuthRequest()
            withContext(Dispatchers.Main) {
                when (authResponse) {
                    is ApiResponse.Success -> {
                        authTokenCallback(authResponse.accessToken)
                        authCallback.onSuccess(authResponse)
                    }
                    is ApiResponse.Failure.Http -> {
                        authCallback.onHttpError(authResponse)
                    }
                    is ApiResponse.Failure.Vimeo -> {
                        authCallback.onVimeoError(authResponse)
                    }
                }
            }
        }
    }

    /**
     * Make a request to authenticate and return the results using a sealed class
     * [ApiResponse].
     */
    private suspend fun performAuthRequest(): ApiResponse<String> =
        try {
            val call = authService.authorizeWithClientCredentialsGrant(
                authHeaders,
                GrantType.CLIENT_CREDENTIALS.value,
                apiScopes.joinToString())

            val response = call.awaitResponse()
            parseApiResponse(response)

        } catch (e: Exception) {
            ApiResponse.Failure.Vimeo(createApiErrorForCustomMessage(errorMessage = e.message))
        }

    /**
     * Parse the return response from the API. Check whether it was successful or failed.
     *
     * @return the result of the API request.
     */
    private fun parseApiResponse(response: Response<VimeoAccount>) =
        if (response.isSuccessful) {
            response.body()?.accessToken?.let { ApiResponse.Success(it) }
                ?: ApiResponse.Failure.Http(response.code())
        } else {
            ApiResponse.Failure.Vimeo(errorResponseConverter.getErrorFromResponse(response))
        }

    override fun cancel() {
        coroutineScope.coroutineContext[Job]?.cancel()
    }

}
