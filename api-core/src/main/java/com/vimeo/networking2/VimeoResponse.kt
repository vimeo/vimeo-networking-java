package com.vimeo.networking2

import com.vimeo.networking2.VimeoResponse.Error
import com.vimeo.networking2.VimeoResponse.Success

/**
 * Result of the API response. [Success] contains the data while the [Error] class will
 * inform you of an api error.
 *
 * @param httpStatusCode HTTP status code.
 */
sealed class VimeoResponse<in T>(open val httpStatusCode: Int) {

    /**
     * A successful response.
     *
     * @param data The parsed data for the request.
     * @param httpStatusCode HTTP status code.
     */
    data class Success<T>(val data: T, override val httpStatusCode: Int) : VimeoResponse<T>(httpStatusCode)

    /**
     * An error occurred when making the request. This error may be due to invalid parameters,
     * exception thrown when making the request or there was an error parsing the response.
     *
     * @param message The error message.
     * @param httpStatusCode HTTP status code.
     */
    sealed class Error(val message: String, override val httpStatusCode: Int) : VimeoResponse<Nothing>(httpStatusCode) {

        /**
         * Vimeo API returned an error response for the request you made.
         *
         * @param reason Info on the error.
         * @param httpStatusCode HTTP status code.
         */
        data class Api(
            val reason: ApiError,
            override val httpStatusCode: Int
        ) : Error("API error: ${reason.errorCode ?: "unknown"}", httpStatusCode)

        /**
         * Exception was thrown when making the request. This maybe due to no internet.
         *
         * @param throwable Info on the exception that was thrown.
         * @param httpStatusCode HTTP status code.
         */
        data class Exception(
            val throwable: Throwable,
            override val httpStatusCode: Int
        ) : Error("Exception thrown", httpStatusCode)

        /**
         * Generic error occurred. The request was successful, but the response could not be
         * parsed by the SDK. This is maybe because it is not formatted correctly. The raw response
         * will allow you to see info about the request.
         *
         * @param rawResponse Raw response from the API.
         * @param httpStatusCode HTTP status code.
         */
        data class Generic(
            val rawResponse: String,
            override val httpStatusCode: Int
        ) : Error("Generic error", httpStatusCode)

    }

}
