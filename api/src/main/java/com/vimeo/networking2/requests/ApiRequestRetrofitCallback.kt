package com.vimeo.networking2.requests

import com.vimeo.networking2.ApiError
import com.vimeo.networking2.utils.ApiResponseErrorConverter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Custom Retrofit [Callback] to listen for requests and parse any errors.
 *
 * @param apiResponseErrorConverter Used to convert an error in the response to [ApiError].
 * @param apiResponseCallback       Used to notify you of the response or error.
 */
class ApiRequestRetrofitCallback<ResponseType_T>(
    private val apiResponseErrorConverter: ApiResponseErrorConverter,
    private val apiResponseCallback: ApiResponseCallback<ResponseType_T>
) : Callback<ResponseType_T> {

    override fun onResponse(call: Call<ResponseType_T>, response: Response<ResponseType_T>) {
        if (response.isSuccessful) {
            response.body()?.let {
                apiResponseCallback.onSuccess(it)
            } ?: apiResponseCallback.onError(ApiError(errorMessage = "Failed to parse data."))
        } else {
            apiResponseCallback.onError(apiResponseErrorConverter.getErrorFromResponse(response))
        }
    }

    override fun onFailure(call: Call<ResponseType_T>, t: Throwable) {
        apiResponseCallback.onError(ApiError(errorMessage = t.message))
    }

}
