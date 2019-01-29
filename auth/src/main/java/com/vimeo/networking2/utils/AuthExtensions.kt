package com.vimeo.networking2.utils

import com.vimeo.networking2.ApiError
import com.vimeo.networking2.VimeoAccount
import com.vimeo.networking2.adapters.VimeoCall
import com.vimeo.networking2.adapters.VimeoCallback
import com.vimeo.networking2.requests.ApiResponse
import com.vimeo.networking2.requests.AuthCallback
import com.vimeo.networking2.requests.VimeoRequest
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

    return object: VimeoRequest {
        override fun cancel() {
            cancel()
        }
    }
}
