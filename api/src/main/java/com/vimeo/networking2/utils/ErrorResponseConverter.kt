package com.vimeo.networking2.utils

import com.squareup.moshi.JsonAdapter
import com.vimeo.networking2.ApiError
import retrofit2.Response

/**
 * Implementation of [ErrorResponseConverter] that converts an reason to a
 * [ApiError] object.
 */
class ErrorResponseConverterImpl(private val errorJsonAdapter: JsonAdapter<ApiError>) : ErrorResponseConverter {

    override fun <T> getErrorFromResponse(response: Response<T>): ApiError {
        val httpCode = response.code()

        return try {
            response.errorBody()?.let {
                
                errorJsonAdapter.fromJson(it.string())
            }
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
 * Converter for parsing an reason from the API into a [ApiError] object.
 */
interface ErrorResponseConverter {

    fun <T> getErrorFromResponse(response: Response<T>): ApiError

}
