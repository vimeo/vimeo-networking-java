package com.vimeo.networking2

import retrofit2.Response

/**
 * Informs you of the result of an API request. Provides the response or any errors.
 */
interface VimeoCallback<ResponseType_T> {

    /**
     * Successful API request.
     *
     * @param response  Data returned by the API.
     */
    fun onSuccess(response: Response<ResponseType_T>)

    /**
     * An error occurred on the API. [apiError] contains information about the error that occurred.
     *
     * @param apiError Information on the error returned by API.
     */
    fun onApiError(apiError: ApiError)

    /**
     * Generic error occurred. This occurred was not caused by the API rather it was due to
     * parsing the data or the response body was null.
     *
     * @param responseCode  The response code of the API request.
     */
    fun onGenericError(responseCode: Int)

    /**
     * An exception occurred by Retrofit in the API request.
     *
     * @param throwable  The exception thrown by Retrofit for the request.
     */
    fun onExceptionError(throwable: Throwable)

}

/**
 * Callback for only notify the client of an error.
 */
abstract class ApiErrorVimeoCallback: VimeoCallback<Nothing> {

    override fun onSuccess(response: Response<Nothing>) {}

    override fun onGenericError(responseCode: Int) {}

    override fun onExceptionError(throwable: Throwable) {}
}
