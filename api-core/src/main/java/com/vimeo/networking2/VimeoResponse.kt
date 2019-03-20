package com.vimeo.networking2

import com.vimeo.networking2.VimeoResponse.Error
import com.vimeo.networking2.VimeoResponse.Success

/**
 * Result of the API response. [Success] contains the data while the [Error] class will
 * inform you of an api error.
 */
sealed class VimeoResponse<in T> {

    /**
     * A successful response.
     *
     * @param data The parsed data for the request.
     */
    data class Success<T>(val data: T) : VimeoResponse<T>()

    /**
     * An error occurred when making the request. This error may be due to invalid parameters,
     * exception thrown when making the request or there was an error parsing the response.
     *
     * @param message The error message.
     */
    sealed class Error(val message: String): VimeoResponse<Nothing>() {

        /**
         * Vimeo API returned an error response for the request you made.
         *
         * @param reason Info on the error.
         */
        data class Api(val reason: ApiError): Error("API error: ${reason.errorCode ?: "unknown"}")

        /**
         * Exception was thrown when making the request. This maybe due to no internet.
         *
         * @param throwable Info on the exception that was thrown.
         */
        data class Exception(val throwable: Throwable): Error("Exception thrown")

        /**
         * Generic error occurred. The request was successful, but the response could not be
         * parsed by the SDK. This is maybe because it is not formatted correctly. The raw response
         * will allow you to see info about the request.
         *
         * @param httpStatusCode    HTTP status code.
         * @param rawResponse       Raw response from the API.
         */
        data class Generic(val httpStatusCode: Int, val rawResponse: String): Error("Generic error")

    }

}
