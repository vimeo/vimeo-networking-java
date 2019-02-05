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
