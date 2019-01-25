package com.vimeo.networking2.adapters

import com.vimeo.networking2.ApiError
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Converter
import retrofit2.Response
import java.util.concurrent.Executor

/**
 * A custom Retrofit call adapter. This was created in order to parse an error response from the API
 * in a background thread and return the result in the calling thread.
 *
 * @param call                  Retrofit call object.
 * @param callbackExecutor      Callback executor set by Retrofit to return the result. Retrofit itself sets it to the main thread
 *                              on Android.
 * @param responseBodyConverter Converter to convert the error response to [ApiError].
 */
class VimeoCallAdapter<T>(
    private val call: Call<T>,
    private val callbackExecutor: Executor,
    private val responseBodyConverter: Converter<ResponseBody, ApiError>
) : VimeoCall<T> {

    override fun enqueue(callback: VimeoCallback<T>) {
        call.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {

                if (response.hasBody()) {
                    sendResponse { callback.onSuccess(response) }
                } else {
                    val apiError = response.parseApiError()
                    if (apiError != null) {
                        sendResponse { callback.onApiError(apiError) }
                    } else {
                        sendResponse { callback.onGenericError(response.code()) }
                    }
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                sendResponse { callback.onExceptionError(t) }
            }
        })
    }

    /**
     * Send response on [callbackExecutor].
     */
    fun sendResponse(action: () -> Unit) {
        callbackExecutor.execute { action() }
    }

    /**
     * Determine is the response has a body.
     */
    fun Response<T>.hasBody() = isSuccessful && body() != null

    /**
     * Parse the error body into a [ApiError] object.
     */
    fun Response<T>.parseApiError() = errorBody()?.let { responseBodyConverter.convert(it) }

    /**
     * Cancel API request.
     */
    override fun cancel() {
        call.cancel()
    }

    /**
     * Clone call.
     */
    override fun clone() = VimeoCallAdapter(call.clone(), callbackExecutor, responseBodyConverter)
}
