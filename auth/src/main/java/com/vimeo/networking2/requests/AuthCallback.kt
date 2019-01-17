package com.vimeo.networking2.requests

/**
 * Inform you of the result of an authentication request. The access token is provided upon
 * success and errors are propagated to you through the callback.
 */
interface AuthCallback {

    /**
     * The authentication was successful and the access token can be used to make API requests.
     *
     * @param accessToken Authenticated access token.
     */
    fun onSuccess(accessToken: ApiResponse.Success<String>)

    /**
     * A generic unsuccessful response. It contains the http code and message
     * indicating the problem which occurred. The generic failure maybe due to
     * parsing the response.
     *
     * @param genericError Information on the type of error that occurred.
     */
    fun onHttpError(genericError: ApiResponse.Failure.Http)

    /**
     * A specific API failure return from the Vimeo API. The response contains
     * a Vimeo specific error code and message.
     *
     * @param apiError Information on the type of error that occurred.
     */
    fun onVimeoError(apiError: ApiResponse.Failure.Vimeo)

}
