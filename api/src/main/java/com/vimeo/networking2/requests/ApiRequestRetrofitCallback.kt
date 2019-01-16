package com.vimeo.networking2.requests

import com.vimeo.networking2.ApiError
import com.vimeo.networking2.utils.ErrorResponseConverter
import com.vimeo.networking2.utils.createApiErrorForCustomMessage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Custom Retrofit [Callback] to listen for requests and parse any errors.
 *
 * @param errorResponseConverter Used to convert an error in the response to [ApiError].
 * @param apiResponseCallback       Used to notify you of the response or error.
 */
class ApiRequestRetrofitCallback<ResponseType_T>(
    private val errorResponseConverter: ErrorResponseConverter,
    private val apiResponseCallback: ApiResponseCallback<ResponseType_T>
) : Callback<ResponseType_T> {

    override fun onResponse(call: Call<ResponseType_T>, response: Response<ResponseType_T>) {

        if (response.isSuccessful) {
            response.body()?.let {
                apiResponseCallback.onSuccess(it)
            } ?: apiResponseCallback.onError(
                createApiErrorForCustomMessage(response.code(), "Response body is null")

            )
        } else {
            apiResponseCallback.onError(errorResponseConverter.getErrorFromResponse(response))
        }
    }

    override fun onFailure(call: Call<ResponseType_T>, t: Throwable) {
        apiResponseCallback.onError(createApiErrorForCustomMessage(errorMessage = t.message))
    }

}
