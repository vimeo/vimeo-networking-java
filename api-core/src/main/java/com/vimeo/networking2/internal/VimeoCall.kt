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
import com.vimeo.networking2.VimeoCallback
import com.vimeo.networking2.VimeoRequest

/**
 * Custom Retrofit call that allows you to enqueue a custom callback.
 */
interface VimeoCall<T> {

    /**
     * The URL to which this call will be made.
     */
    val url: String

    /**
     * Register a [VimeoCallback] for the API request.
     *
     * @param callback  A callback that gives you back the data or the error from the API.
     */
    fun enqueue(callback: VimeoCallback<T>): VimeoRequest

    /**
     * Inform the client of an error.
     *
     * @param apiError  Information on the error.
     * @param callback  A callback to inform you of the error.
     */
    fun enqueueError(apiError: ApiError, callback: VimeoCallback<T>): VimeoRequest

    /**
     * Cancel API request.
     */
    fun cancel()

    /**
     * Clone the API call.
     */
    fun clone(): VimeoCall<T>
}
