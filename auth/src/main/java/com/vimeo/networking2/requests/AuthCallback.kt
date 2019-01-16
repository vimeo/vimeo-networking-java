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
     * There was an error in the authentication request.
     *
     * @param error Information on the type of error that occurred.
     */
    fun onHttpError(error: ApiResponse.Failure.Http)

    fun onVimeoError(error: ApiResponse.Failure.Vimeo)

}