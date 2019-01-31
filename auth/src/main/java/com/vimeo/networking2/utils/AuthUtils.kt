package com.vimeo.networking2.utils

import com.vimeo.networking2.ApiError
import com.vimeo.networking2.InvalidParameter
import com.vimeo.networking2.VimeoAccount
import com.vimeo.networking2.adapters.VimeoCall
import com.vimeo.networking2.adapters.VimeoCallback
import com.vimeo.networking2.requests.*
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
    enqueue(apiResponseCallback)
    return CancellableVimeoRequest(this)
}

/**
 * Utility method to create a [ApiError] for an authentication request where invalid parameters
 * were given such as an empty string for an email or password.
 */
internal fun createApiErrorForInvalidParams(developerMessage: String,
                                            authParams: List<AuthParam>
): ApiResponse.Failure.ApiFailure {
    val invalidParameters = authParams.map {
        InvalidParameter(it.paramName, it.errorCode.value, it.developerMessage)
    }.toList()
    return ApiResponse.Failure.ApiFailure(ApiError(developerMessage, invalidParameters =  invalidParameters))
}
