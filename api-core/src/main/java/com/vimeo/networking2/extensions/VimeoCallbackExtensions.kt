package com.vimeo.networking2.extensions

import com.vimeo.networking2.VimeoCallback
import com.vimeo.networking2.VimeoResponse

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
