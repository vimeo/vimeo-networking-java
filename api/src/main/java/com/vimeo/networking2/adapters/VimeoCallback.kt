package com.vimeo.networking2.adapters

import com.vimeo.networking2.ApiError
import retrofit2.Response

/**
 * Informs you of the result of an API request. Provides the response or any errors.
 */
interface VimeoCallback<ResponseType_T> {

    /**
     * Successful API request.
     */
    fun onSuccess(response: Response<ResponseType_T>)

    /**
     * An error occurred on the API. [apiError] contains information about the error that occurred.
     */
    fun onApiError(apiError: ApiError)

    /**
     * Generic error occurred. This occurred was not caused by the API rather it was due to
     * parsing the data or no response body was null.
     */
    fun onGenericError(requestCode: Int)

    /**
     * An exception occurred by Retrofit in the API request.
     */
    fun onExceptionError(throwable: Throwable)

}
