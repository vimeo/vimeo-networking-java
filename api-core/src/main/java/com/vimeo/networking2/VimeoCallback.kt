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
    fun onSuccess(response: VimeoResponse.Success<ResponseType_T>)

    /**
     * An error occurred when making the request.
     *
     * @param error Information on the error. This error could be due to an exception thrown or
     *              parsing response error.
     */
    fun onError(error: VimeoResponse.Error)

}
