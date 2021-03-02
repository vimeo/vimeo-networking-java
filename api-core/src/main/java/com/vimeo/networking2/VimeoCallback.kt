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
@file:JvmName("VimeoCallbackUtils")

package com.vimeo.networking2

/**
 * Informs you of the result of an API request. Provides the response or any errors.
 */
interface VimeoCallback<ResponseType_T> {

    /**
     * A successful API response.
     *
     * @param response Data returned by the API.
     */
    fun onSuccess(response: VimeoResponse.Success<ResponseType_T>)

    /**
     * An error occurred when making the request.
     *
     * @param error Information on the error. This error could be due to an exception thrown or parsing response error.
     */
    fun onError(error: VimeoResponse.Error)
}

/**
 * Create a lambda friendly instance of the [VimeoCallback].
 *
 * @param onSuccess Called when the request completes successfully.
 * @param onError Called when the request completes unsuccessfully.
 */
fun <ResponseType_T> vimeoCallback(
    onSuccess: (VimeoResponse.Success<ResponseType_T>) -> Unit,
    onError: (VimeoResponse.Error) -> Unit
): VimeoCallback<ResponseType_T> = object : VimeoCallback<ResponseType_T> {
    override fun onSuccess(response: VimeoResponse.Success<ResponseType_T>) = onSuccess(response)
    override fun onError(error: VimeoResponse.Error) = onError(error)
}
