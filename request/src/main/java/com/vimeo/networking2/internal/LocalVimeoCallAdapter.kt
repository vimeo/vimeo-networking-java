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
import retrofit2.Retrofit
import java.util.concurrent.Executor

/**
 * An adapter that can be used to notify a [VimeoCallback] of an error on the correct thread.
 *
 * @param retrofit The retrofit instance needed to obtain the callback executor.
 */
class LocalVimeoCallAdapter(private val retrofit: Retrofit) {

    /**
     * Enqueue the [VimeoCallback] with the provided [ApiError].
     */
    fun <T> enqueueError(apiError: ApiError, callback: VimeoCallback<T>): VimeoRequest {
        retrofit.callbackExecutor()
            .sendResponse { callback.onError(VimeoResponse.Error.Api(apiError, VimeoResponse.HTTP_NONE)) }

        return NoOpVimeoRequest
    }

    private fun Executor?.sendResponse(action: () -> Unit) {
        this?.execute(action) ?: action()
    }
}
