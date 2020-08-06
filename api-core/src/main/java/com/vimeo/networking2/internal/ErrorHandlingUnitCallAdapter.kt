package com.vimeo.networking2.internal

import com.vimeo.networking2.ApiError
import com.vimeo.networking2.logging.VimeoLogger
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Converter
import java.lang.reflect.Type
import java.util.concurrent.Executor

/**
 * Custom call adapter to handle errors for calls of type [Unit].
 *
 * @param callbackExecutor The executor upon which the caller is notified.
 * @param responseBodyConverter Converter to parse the error response into a [ApiError].
 * @param vimeoLogger The logger used to log information about error handling.
 */
internal class ErrorHandlingUnitCallAdapter(
    private val callbackExecutor: Executor,
    private val responseBodyConverter: Converter<ResponseBody, ApiError>,
    private val vimeoLogger: VimeoLogger
) : CallAdapter<Unit, VimeoCall<Unit>> {
    override fun adapt(call: Call<Unit>): VimeoCall<Unit> =
        VimeoCallEmptyResponseAdapter(call, callbackExecutor, responseBodyConverter, vimeoLogger)

    override fun responseType(): Type = Unit::class.java
}
