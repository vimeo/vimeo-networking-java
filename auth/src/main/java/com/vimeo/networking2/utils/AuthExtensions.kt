package com.vimeo.networking2.utils

import com.vimeo.networking2.ApiError
import com.vimeo.networking2.VimeoAccount
import com.vimeo.networking2.adapters.VimeoCall
import com.vimeo.networking2.adapters.VimeoCallback
import com.vimeo.networking2.requests.AuthCallback
import com.vimeo.networking2.requests.VimeoRequest
import retrofit2.Response

internal fun VimeoCall<VimeoAccount>.enqueueAuthRequest(authCallback: AuthCallback): VimeoRequest {
    val apiResponseCallback = object : VimeoCallback<VimeoAccount> {

        override fun onSuccess(response: Response<VimeoAccount>) {
            response.body()?.accessToken?.let {
                authCallback.onSuccess(com.vimeo.networking2.requests.ApiResponse.Success(it))
            }
        }

        override fun onApiError(apiError: ApiError) {
            authCallback.onApiError(com.vimeo.networking2.requests.ApiResponse.Failure.ApiFailure(apiError))
        }

        override fun onGenericError(requestCode: Int) {
            authCallback.onGenericError(com.vimeo.networking2.requests.ApiResponse.Failure.GenericFailure(requestCode))
        }

        override fun onExceptionError(t: Throwable) {
            authCallback.onExceptionError(com.vimeo.networking2.requests.ApiResponse.Failure.ExceptionFailure(t))
        }
    }
    enqueue(apiResponseCallback)

    return object: VimeoRequest {
        override fun cancel() {
            cancel()
        }
    }
}
