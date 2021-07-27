/*
 * Copyright (c) 2020 Vimeo (https://vimeo.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.vimeo.networking2.internal.params

import com.vimeo.networking2.ApiError
import com.vimeo.networking2.InvalidParameter
import com.vimeo.networking2.VimeoCallback
import com.vimeo.networking2.VimeoRequest
import com.vimeo.networking2.enums.ErrorCodeType
import com.vimeo.networking2.internal.LocalVimeoCallAdapter

/**
 * A class representing an API parameter that can be validated by using [validateParameters].
 *
 * @param parameterName The name of the field being validated.
 * @param parameterValue The original pre-validation value being provided.
 * @param developerMessage The optional message to provide if the parameter is invalid. A default message will be added
 * by [validateParameters] when the [parameterValue] is invalid if none is provided here.
 */
@Suppress("DataClassShouldBeImmutable")
data class ApiParameter(
    val parameterName: String,
    val parameterValue: String?,
    val developerMessage: String? = null
) {
    /**
     * The validated version of the [parameterValue].
     */
    var validatedParameterValue: String = ""
        internal set
}

/**
 * Validate the parameters provided and enqueue an error if any of the parameters are invalid.
 *
 * @param callback The callback to enqueue if there are invalid parameters.
 * @param developerMessage The developer message that describes the request that failed.
 * @param parameters The parameters that will be validated. Upon validation, these parameters will be updated with the
 * validated parameter value.
 *
 * @return A [VimeoRequest] if there were invalid parameters and the callback was enqueued, null if there were no
 * invalid parameters.
 */
fun <T> LocalVimeoCallAdapter.validateParameters(
    callback: VimeoCallback<T>,
    developerMessage: String,
    vararg parameters: ApiParameter
): VimeoRequest? {
    val invalidParameters = parameters.mapNotNull {
        if (it.parameterValue.isNullOrBlank()) {
            InvalidParameter(
                field = it.parameterName,
                errorCode = ErrorCodeType.INVALID_INPUT.value,
                developerMessage = it.developerMessage ?: "${it.parameterName} is empty or null"
            )
        } else {
            it.validatedParameterValue = it.parameterValue
            null
        }
    }

    return if (invalidParameters.isNotEmpty()) {
        enqueueError(
            ApiError(
                invalidParameters = invalidParameters,
                developerMessage = developerMessage
            ),
            callback
        )
    } else {
        null
    }
}
