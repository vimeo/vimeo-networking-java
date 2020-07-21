package com.vimeo.networking2.internal

import com.vimeo.networking2.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Converter
import retrofit2.Response
import java.util.concurrent.Executor

/**
 * A custom Retrofit call adapter for requests with an expected empty response. This was created in order to parse an
 * error response from the API in a background thread and return the result in the calling thread.
 *
 * @param call Retrofit call object with empty response.
 * @param callbackExecutor Callback executor set by Retrofit to return the result. Retrofit itself sets it to the main
 * thread on Android. If the executor is null, then the callback will be executed on the thread provided b OkHttp's
 * dispatcher.
 * @param responseBodyConverter Converter to convert the error response to [ApiError].
 */
internal class VimeoCallEmptyResponseAdapter(
    private val call: Call<Unit>,
    private val callbackExecutor: Executor,
    private val responseBodyConverter: Converter<ResponseBody, ApiError>
) : VimeoCall<Unit> {
    override fun enqueue(callback: VimeoCallback<Unit>): VimeoRequest {
        call.enqueue(object : Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                if (response.isSuccessful) {
                    callbackExecutor.execute {
                        callback.onSuccess(VimeoResponse.Success(Unit, response.code()))
                    }
                } else {
                    val apiError = response.parseApiError(responseBodyConverter)
                    if (apiError != null) {
                        callbackExecutor.execute {
                            callback.onError(VimeoResponse.Error.Api(apiError, response.code()))
                        }
                    } else {
                        callbackExecutor.execute {
                            callback.onError(
                                VimeoResponse.Error.Unknown(
                                    response.raw().toString(),
                                    response.code()
                                )
                            )
                        }
                    }
                }
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                callbackExecutor.execute { callback.onError(VimeoResponse.Error.Exception(t)) }
            }
        })
        return CancellableVimeoRequest(call)
    }

    override fun enqueueError(apiError: ApiError, callback: VimeoCallback<Unit>): VimeoRequest {
        callbackExecutor.execute { callback.onError(VimeoResponse.Error.Api(apiError, VimeoResponse.HTTP_NONE)) }
        return NoOpVimeoRequest
    }

    override fun cancel() {
        call.cancel()
    }

    override fun clone() = VimeoCallEmptyResponseAdapter(call.clone(), callbackExecutor, responseBodyConverter)
}
