package com.vimeo.networking2.extensions

import com.vimeo.networking2.VimeoCallback
import com.vimeo.networking2.VimeoResponse

/**
 * Transforms callback of type [T] to callback of type [R].
 * @param transformer maps value of type [R] to value of type [T].
 */
fun <T, R> VimeoCallback<T>.transform(transformer: (R) -> T): VimeoCallback<R> {
    val original = this
    return object : VimeoCallback<R> {
        override fun onSuccess(response: VimeoResponse.Success<R>) {
            original.onSuccess(response.transform(transformer))
        }

        override fun onError(error: VimeoResponse.Error) {
            original.onError(error)
        }
    }
}
