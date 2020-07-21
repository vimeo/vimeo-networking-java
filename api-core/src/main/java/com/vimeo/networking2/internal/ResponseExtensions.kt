package com.vimeo.networking2.internal

import com.vimeo.networking2.ApiError
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Response

/**
 * Parse the error body into a [ApiError] object.
 */
internal fun <T> Response<T>.parseApiError(responseBodyConverter: Converter<ResponseBody, ApiError>) =
    errorBody()?.let { responseBodyConverter.convert(it) }
