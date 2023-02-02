package com.vimeo.networking2.extensions

import com.vimeo.networking2.VimeoResponse

/**
 * Transforms success response of type [T] to success response of type [R].
 * @param transformer maps value of type [T] to value of type [R].
 */
fun <T, R> VimeoResponse.Success<T>.transform(transformer: (T) -> R): VimeoResponse.Success<R> =
    VimeoResponse.Success(
        transformer(data),
        responseOrigin,
        httpStatusCode,
    )
