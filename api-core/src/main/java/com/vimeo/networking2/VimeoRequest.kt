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
package com.vimeo.networking2

import com.vimeo.networking2.internal.VimeoCall
import retrofit2.Call

/**
 * Common actions such as canceling all API requests.
 */
interface VimeoRequest {

    /**
     * Cancels the API request for you.
     */
    fun cancel()

}

/**
 * No-op API request. Used when an invalid param was given by the client. In this case,
 * the actual API request is not made. So, there is nothing to cancel.
 */
object NoOpVimeoRequest: VimeoRequest {

    override fun cancel() {}

}

/**
 * Cancellable request.
 *
 * @param call  A [VimeoCall] object for the API request.
 */
class CancellableVimeoRequest<T>(private val call: Call<T>): VimeoRequest {
    override fun cancel() {
        call.cancel()
    }
}
