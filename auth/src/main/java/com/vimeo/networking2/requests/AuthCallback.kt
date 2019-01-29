package com.vimeo.networking2.requests

/**
 * Informs you of the result of an authentication request. The access token is provided upon
 * success and errors are propagated to you through the callback.
 */
interface AuthCallback {

    /**
     * The authentication was successful and the access token can be used to make API requests.
     *
     * @param authResponse Authenticated access token.
     */
    fun onSuccess(authResponse: ApiResponse.Success<String>)

    /**
     * A generic unsuccessful response. It contains the http code of the request.
     * The generic failure maybe due to parsing the response. The callback
     * indicates the error did not occur from the API rather it was a generic
     * failure.
     *
     * @param genericFailure Information on the generic error that occurred.
     */
    fun onGenericError(genericFailure: ApiResponse.Failure.GenericFailure)

    /**
     * An failure response was returned from the API. The response contains
     * the specific code and message of the error.
     *
     * @param apiFailure Information on the type of API failure that occurred.
     */
    fun onApiError(apiFailure: ApiResponse.Failure.ApiFailure)

    /**
     * An exception occurred when making the API request.
     */
    fun onExceptionError(exceptionFailure: ApiResponse.Failure.ExceptionFailure)

}
