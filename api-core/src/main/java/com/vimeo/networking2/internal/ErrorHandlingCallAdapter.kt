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
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Converter
import java.lang.reflect.Type
import java.util.concurrent.Executor

/**
 * Custom call adapter to handle errors.
 *
 * @param responseType              Type of the response.
 * @param callbackExecutor          The executor upon which the caller is notified.
 * @param responseBodyConverter     Converter to parse the error response into a [ApiError].
 */
internal class ErrorHandlingCallAdapter<T : Any>(
    private val responseType: Type,
    private val callbackExecutor: Executor?,
    private val responseBodyConverter: Converter<ResponseBody, ApiError>
) : CallAdapter<T, VimeoCall<T>> {

    /**
     * Get the response type of the request.
     */
    override fun responseType() = responseType

    /**
     * Create a call adapter for the specific call used in the request.
     */
    override fun adapt(call: Call<T>) = VimeoCallAdapter(call, callbackExecutor, responseBodyConverter)
}
