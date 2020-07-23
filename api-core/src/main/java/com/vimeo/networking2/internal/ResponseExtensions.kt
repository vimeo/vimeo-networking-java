package com.vimeo.networking2.internal

import com.vimeo.networking2.ApiError
import com.vimeo.networking2.VimeoResponse
import com.vimeo.networking2.logging.VimeoLogger
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Response
import java.io.IOException

/**
 * Parse the error body into an appropriate [VimeoResponse.Error] instance.
 *
 * @param responseBodyConverter The converter used to parse the error body.
 * @param vimeoLogger The logger used to report information about the error handling.
 */
@Suppress("ComplexMethod")
internal fun <T> Response<T>.parseErrorResponse(
    responseBodyConverter: Converter<ResponseBody, ApiError>,
    vimeoLogger: VimeoLogger
): VimeoResponse.Error {
    require(!isSuccessful) { "Cannot get error from a successful response" }
    val errorBody = errorBody()
    val isUnauthorizedError = INVALID_TOKEN_VALUE == headers().get(AUTHENTICATE_HEADER)
    return when {
        errorBody != null ->
            try {
                val apiError: ApiError? = responseBodyConverter.convert(errorBody)
                when {
                    isUnauthorizedError -> VimeoResponse.Error.InvalidToken(apiError, code())
                    apiError != null -> VimeoResponse.Error.Api(apiError, code())
                    else -> VimeoResponse.Error.Unknown(errorBody.string(), code())
                }
            } catch (e: Exception) {
                vimeoLogger.e("Error while attempting to convert response body to VimeoError", e)
                val errorBodyString = errorBody.safeString(vimeoLogger) ?: "Unable to read error body"
                if (isUnauthorizedError) {
                    VimeoResponse.Error.InvalidToken(null, code())
                } else {
                    VimeoResponse.Error.Unknown(errorBodyString, code())
                }
            }
        isUnauthorizedError -> VimeoResponse.Error.InvalidToken(null, code())
        else -> VimeoResponse.Error.Unknown("Null error body", code())
    }
}

/**
 * Safely reads from [ResponseBody.string] without the risk of throwing an exception.
 *
 * @param vimeoLogger The logger used to log information about reading from the error body.
 */
private fun ResponseBody.safeString(vimeoLogger: VimeoLogger): String? = try {
    string()
} catch (e: IOException) {
    vimeoLogger.e("Unable to read error body", e)
    null
}

private const val INVALID_TOKEN_VALUE = "Bearer error=\"invalid_token\""
private const val AUTHENTICATE_HEADER = "WWW-Authenticate"
