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
package com.vimeo.networking2.internal

import com.vimeo.networking2.ApiError
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.util.concurrent.Executor

/**
 * Factory for creating a custom [ErrorHandlingCallAdapter].
 */
internal class ErrorHandlingCallAdapterFactory : CallAdapter.Factory() {

    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (getRawType(returnType) != VimeoCall::class.java) {
            return null
        }

        if (returnType !is ParameterizedType) {
            throw IllegalStateException("VimeoCall must have generic type (e.g., VimeoCall<ResponseBody>)")
        }
        val responseType = getParameterUpperBound(0, returnType)
        val callbackExecutor = retrofit.callbackExecutor()
        val errorResponseConverter = retrofit.responseBodyConverter<ApiError>(
            ApiError::class.java,
            emptyArray()
        )

        if (responseType == Unit::class.java) {
            // Requests with an expected empty response need to be handled differently, as empty responses are otherwise
            // treated as errors.
            return ErrorHandlingUnitCallAdapter(
                callbackExecutor ?: synchronousExecutor,
                errorResponseConverter
            )
        }
        return ErrorHandlingCallAdapter<Any>(
            responseType,
            callbackExecutor ?: synchronousExecutor,
            errorResponseConverter
        )
    }

    private companion object {
        /**
         * If no executor is provided, we will execute runnables synchronously.
         */
        private val synchronousExecutor = Executor { it.run() }
    }
}
