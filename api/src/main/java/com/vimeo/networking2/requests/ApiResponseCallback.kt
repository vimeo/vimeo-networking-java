package com.vimeo.networking2.requests

import com.vimeo.networking2.ApiError

/**
 * Informs you of the result of an API request. Provides the response or any errors.
 */
interface ApiResponseCallback<ResponseType_T> {

    fun onSuccess(response: ResponseType_T)

    fun onError(apiError: ApiError)

}
