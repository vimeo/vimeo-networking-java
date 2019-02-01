package com.vimeo.networking2.internal

import com.vimeo.networking2.VimeoCallback

/**
 * Custom Retrofit call that allows you to enqueue a custom callback.
 */
interface VimeoCall<T> {

    /**
     * Register a [VimeoCallback] for the API request.
     *
     * @param callback  A callback that gives you back the data or the error from the API.
     */
    fun enqueue(callback: VimeoCallback<T>)

    /**
     * Cancel API request.
     */
    fun cancel()

    /**
     * Clone the API call.
     */
    fun clone(): VimeoCall<T>

}
