package com.vimeo.networking2

/**
 * Informs you of the result of an API request. Provides the response or any errors.
 */
interface VimeoCallback<ResponseType_T> {

    /**
     * Successful API request.
     *
     * @param response  Data returned by the API.
     */
    fun onSuccess(response: ApiResponse.Success<ResponseType_T>)

    /**
     * An error occurred on the API. [apiError] contains information about the error that occurred.
     *
     * @param apiError Information on the error returned by API.
     */
    fun onApiError(apiError: ApiResponse.Failure.ApiFailure)

    /**
     * Generic error occurred. This occurred was not caused by the API rather it was due to
     * parsing the data or the response body was null.
     *
     * @param genericFailure  The response code of the API request.
     */
    fun onGenericError(genericFailure: ApiResponse.Failure.GenericFailure)

    /**
     * An exception occurred by Retrofit in the API request.
     *
     * @param exceptionFailure  The exception thrown by Retrofit for the request.
     */
    fun onExceptionError(exceptionFailure: ApiResponse.Failure.ExceptionFailure)

}

