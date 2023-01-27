package com.vimeo.networking2.extensions

import com.vimeo.networking2.VimeoResponse


fun <T, R> VimeoResponse.Success<T>.transform(transformer: (T) -> R): VimeoResponse.Success<R> =
    VimeoResponse.Success(
        transformer(data),
        responseOrigin,
        httpStatusCode,
    )
