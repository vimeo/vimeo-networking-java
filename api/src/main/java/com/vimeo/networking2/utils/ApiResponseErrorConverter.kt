package com.vimeo.networking2.utils

import com.vimeo.networking2.ApiError
import retrofit2.Response
import retrofit2.Retrofit

/**
 * Converter for parsing an error from the API into a [ApiError] object.
 *
 * @param retrofit Required for using the response body converter to do the conversion.
 */
class ApiResponseErrorConverter(private val retrofit: Retrofit) {

    fun <T> getErrorFromResponse(response: Response<T>): ApiError {
        return try {
            val errorConverter = retrofit.responseBodyConverter<ApiError>(
                ApiError::class.java,
                arrayOfNulls<Annotation>(0)
            )
            response.errorBody()?.let { errorConverter.convert(it) } ?: ApiError()
        } catch (e: Exception) {
            ApiError()
        }
    }

}
