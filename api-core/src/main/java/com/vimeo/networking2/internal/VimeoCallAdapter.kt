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

import com.vimeo.networking2.*
import com.vimeo.networking2.logging.VimeoLogger
import okhttp3.HttpUrl
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Converter
import retrofit2.Response
import java.util.concurrent.Executor

/**
 * A custom Retrofit call adapter. This was created in order to parse an error response from the API in a background
 * thread and return the result in the calling thread.
 *
 * @param call Retrofit call object.
 * @param callbackExecutor Callback executor set by Retrofit to return the result. Retrofit itself sets it to the main
 * thread on Android. If the executor is null, then the callback will be executed on the thread provided by OkHttp's
 * dispatcher.
 * @param responseBodyConverter Converter to convert the error response to [ApiError].
 * @param vimeoLogger The logger used to log information about error handling.
 */
internal class VimeoCallAdapter<T : Any>(
    private val call: Call<T>,
    private val callbackExecutor: Executor,
    private val responseBodyConverter: Converter<ResponseBody, ApiError>,
    private val vimeoLogger: VimeoLogger
) : VimeoCall<T> {

    override val url: HttpUrl
        get() = call.request().url()

    /**
     * Determine if the response has a body.
     */
    private fun Response<T>.hasBody() = isSuccessful && body() != null

    override fun enqueue(callback: VimeoCallback<T>): VimeoRequest {
        call.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.hasBody()) {
                    callbackExecutor.execute {
                        callback.onSuccess(VimeoResponse.Success(requireNotNull(response.body()), response.code()))
                    }
                } else {
                    val vimeoResponseError = response.parseErrorResponse(responseBodyConverter, vimeoLogger)
                    callbackExecutor.execute {
                        callback.onError(vimeoResponseError)
                    }
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                callbackExecutor.execute { callback.onError(VimeoResponse.Error.Exception(t)) }
            }
        })
        return CancellableVimeoRequest(call)
    }

    override fun enqueueError(apiError: ApiError, callback: VimeoCallback<T>): VimeoRequest {
        callbackExecutor.execute { callback.onError(VimeoResponse.Error.Api(apiError, ApiConstants.NONE)) }
        return NoOpVimeoRequest
    }

    override fun cancel() {
        call.cancel()
    }

    override fun clone() = VimeoCallAdapter(call.clone(), callbackExecutor, responseBodyConverter, vimeoLogger)
}
