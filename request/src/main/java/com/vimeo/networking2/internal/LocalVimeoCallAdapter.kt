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
