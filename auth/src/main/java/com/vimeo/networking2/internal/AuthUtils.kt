package com.vimeo.networking2.internal

import com.vimeo.networking2.*

/**
 * Extension to enqueue a [VimeoCallback] to a [VimeoCall]. The callback will transfer
 * the response to [authCallback].
 *
 * @return A instance of [VimeoRequest] to allow the consumer to cancel the request.
 */
internal fun VimeoCall<VimeoAccount>.enqueueAuthRequest(authCallback: AuthCallback): VimeoRequest {

    val apiResponseCallback = object : VimeoCallback<VimeoAccount> {

        override fun onSuccess(response: ApiResponse.Success<VimeoAccount>) {
            response.data.accessToken?.let {
                authCallback.onSuccess(ApiResponse.Success(it))
            }
        }

        override fun onApiError(apiError: ApiResponse.Failure.ApiFailure) {
            authCallback.onApiError(apiError)
        }

        override fun onGenericError(genericFailure: ApiResponse.Failure.GenericFailure) {
            authCallback.onGenericError(genericFailure)
        }

        override fun onExceptionError(exceptionFailure: ApiResponse.Failure.ExceptionFailure) {
            authCallback.onExceptionError(exceptionFailure)
        }
    }
    return enqueue(apiResponseCallback)
}

/**
 * Send an authentication error message back to the client.
 *
 * @param apiError          Error information.
 * @param authCallback      Callback to inform the client of the error.
 */
internal fun VimeoCall<VimeoAccount>.enqueueAuthError(
    apiError: ApiError,
    authCallback: AuthCallback
): VimeoRequest {

    val apiResponseCallback = object : ApiErrorVimeoCallback() {
        override fun onApiError(apiError: ApiResponse.Failure.ApiFailure) {
            authCallback.onApiError(apiError)
        }
    }
    return enqueueError(apiError, apiResponseCallback)
}

/**
 * Validates any authentication params given by the client.
 *
 * @return a list of invalid parameters.
 */
internal fun Map<AuthParam, String>.validate(): List<InvalidParameter> =
        filter { it.value.isEmpty() }
        .map { InvalidParameter(it.key.name, it.key.errorCode?.value, it.key.developerMessage) }
        .toList()
