@file:JvmName("ApiErrorFactory")

package com.vimeo.networking.utils

import com.vimeo.networking2.ApiError
import com.vimeo.networking2.InvalidParameter
import com.vimeo.networking2.enums.ErrorCodeType

/**
 * Create an [ApiError] with the provided parameters.
 *
 * @param message The message describing the error
 * @param invalidParameter The invalid parameters causing the error.
 */
fun createApiErrorWithInvalidParameters(message: String, vararg invalidParameter: InvalidParameter): ApiError =
        ApiError(
            developerMessage = message,
            invalidParameters = invalidParameter.asList()
        )

/**
 * Create an [InvalidParameter] with the provided parameters.
 *
 * @param field The field that was invalid.
 * @param code The type of error associated with the field.
 * @param message The error message describing the problem.
 */
fun createInvalidParameter(field: String, code: ErrorCodeType, message: String): InvalidParameter = InvalidParameter(
    field = field,
    errorCode = code.value,
    developerMessage = message
)
