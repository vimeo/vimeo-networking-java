package com.vimeo.networking2.requests

import com.vimeo.networking2.ApiError
import com.vimeo.networking2.requests.ApiResponse.Failure
import com.vimeo.networking2.requests.ApiResponse.Success

/**
 * Result of the API response. [Success] contains the accessToken while the
 * [Failure] class will inform you of an api error.
 */
sealed class ApiResponse<out T> {

    /**
     * A successful response.
     *
     * @param accessToken The accessToken parsed by this successful response.
     */
    data class Success<out T>(val accessToken: T) : ApiResponse<T>()

    /**
     * An unsuccessful response.
     */
    sealed class Failure : ApiResponse<Nothing>() {

        /**
         * A generic unsuccessful Http response.
         *
         * @param code The Http code of the response.
         */
        data class Http(val code: Int) : Failure()

        /**
         * An unsuccessful Http response that contains additional information about the failure
         * in the form of a [ApiError].
         *
         * @param reason The reason given by the API for the failure.
         */
        data class Vimeo(val reason: ApiError) : Failure()
    }
}
