package com.vimeo.networking2.requests

import com.vimeo.networking2.ApiError
import com.vimeo.networking2.requests.ApiResponse.Failure
import com.vimeo.networking2.requests.ApiResponse.Success

/**
 * Result of the API response. [Success] contains the accessToken while the
 * [Failure] class will inform you of an api reason.
 */
sealed class ApiResponse<out T> {

    /**
     * A successful response.
     *
     * @param data The accessToken parsed by this successful response.
     */
    data class Success<out T>(val data: T) : ApiResponse<T>()

    /**
     * An unsuccessful response.
     */
    sealed class Failure : ApiResponse<Nothing>() {

        /**
         * A generic unsuccessful response. The request didn't return a response at all.
         *
         *
         * @param code The http code of the request.
         */
        data class GenericFailure(val code: Int) : Failure()

        /**
         * An unsuccessful api response that contains additional information about the failure
         * in the form of a [ApiFailure]. The request returned a response from the API
         * containing the error code and a message indicating the problem.
         *
         * @param reason The reason given by the API for the failure.
         */
        data class ApiFailure(val reason: ApiError) : Failure()

        /**
         * An exception occurred in the API request.
         */
        data class ExceptionFailure(val throwable: Throwable) : Failure()
    }
}
