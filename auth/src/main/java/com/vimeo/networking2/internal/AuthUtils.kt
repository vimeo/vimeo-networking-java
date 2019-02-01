package com.vimeo.networking2.internal

import com.vimeo.networking2.*
import retrofit2.Response

/**
 * Extension to enqueue a [VimeoCallback] to a [VimeoCall]. The callback will transfer
 * the response to [authCallback].
 *
 * @return A instance of [VimeoRequest] to allow the consumer to cancel the request.
 */
internal fun VimeoCall<VimeoAccount>.enqueueAuthRequest(authCallback: AuthCallback): VimeoRequest {

    val apiResponseCallback = object : VimeoCallback<VimeoAccount> {

        override fun onSuccess(response: Response<VimeoAccount>) {
            response.body()?.accessToken?.let {
                authCallback.onSuccess(ApiResponse.Success(it))
            }
        }

        override fun onApiError(apiError: ApiError) {
            authCallback.onApiError(ApiResponse.Failure.ApiFailure(apiError))
        }

        override fun onGenericError(responseCode: Int) {
            authCallback.onGenericError(ApiResponse.Failure.GenericFailure(responseCode))
        }

        override fun onExceptionError(throwable: Throwable) {
            authCallback.onExceptionError(ApiResponse.Failure.ExceptionFailure(throwable))
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
        override fun onApiError(apiError: ApiError) {
            authCallback.onApiError(ApiResponse.Failure.ApiFailure(apiError))
        }
    }
    return enqueueError(apiError, apiResponseCallback)
}

/**
 * Utility method to create a [ApiError] for an authentication request where invalid parameters
 * were given such as an empty string for an email or password.
 */
internal fun createApiErrorForInvalidParams(
    developerMessage: String,
    authParams: List<AuthParam>
): ApiError {

    val invalidParameters = authParams.map {
        InvalidParameter(it.value, it.errorCode?.value, it.developerMessage)
    }.toList()
    return ApiError(developerMessage, invalidParameters = invalidParameters)
}

/**
 * Create a map of param name and value for sending it to the API.
 */
internal fun Map<AuthParam, String>.convertToKeyValueMap(): Map<String, String> {
    val keyValueMap = mutableMapOf<String, String>()
    for (entry in this) {
        keyValueMap[entry.key.value] = entry.value
    }
    return keyValueMap
}

/**
 * Validates any authentication params given by the client.
 *
 * @return a list of empty params given by the user.
 */
internal fun Map<AuthParam, String>.validate(): List<AuthParam> =
        filter { it.value.isEmpty() }
        .map { it.key }
        .toList()
