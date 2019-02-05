package com.vimeo.networking2.internal

import com.vimeo.networking2.ApiError
import com.vimeo.networking2.ApiErrorVimeoCallback
import com.vimeo.networking2.VimeoCallback
import com.vimeo.networking2.VimeoRequest

/**
 * Custom Retrofit call that allows you to enqueue a custom callback.
 */
interface VimeoCall<T> {

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
    fun enqueueError(apiError: ApiError, callback: ApiErrorVimeoCallback): VimeoRequest

    /**
     * Cancel API request.
     */
    fun cancel()

    /**
     * Clone the API call.
     */
    fun clone(): VimeoCall<T>

}
