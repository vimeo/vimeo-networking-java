package com.vimeo.networking2.utils

import com.vimeo.networking2.ApiError
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Response

/**
 * Implementation of [ErrorResponseConverter] that converts an error to a
 * [ApiError] object.
 *
 * @param retrofitErrorConverter Retrofit's response body converter.
 */
class ErrorResponseConverterImpl(
    private val retrofitErrorConverter: Converter<ResponseBody, ApiError>
) : ErrorResponseConverter {

    override fun <T> getErrorFromResponse(response: Response<T>): ApiError {
        val httpCode = response.code()

        return try {
            response.errorBody()?.let { retrofitErrorConverter.convert(it) }
                ?: createApiErrorForCustomMessage(httpCode, "Error body is null.")

        } catch (e: Exception) {
            createApiErrorForCustomMessage(httpCode, e.message)
        }
    }

}

/**
 * Create [ApiError] for a custom [errorMessage] and [httpCode].
 */
fun createApiErrorForCustomMessage(httpCode: Int? = null, errorMessage: String?) =
    ApiError(errorMessage = errorMessage, errorCode = httpCode.toString())

/**
 * Converter for parsing an error from the API into a [ApiError] object.
 */
interface ErrorResponseConverter {

    fun <T> getErrorFromResponse(response: Response<T>): ApiError

}
