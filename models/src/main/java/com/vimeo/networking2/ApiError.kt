@file:JvmName("ApiErrorUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.enums.ErrorCodeType
import com.vimeo.networking2.enums.asEnum

/**
 * This class represents an error response from the Vimeo API. It holds useful getters to
 * understand why your request might have failed.
 *
 * @param developerMessage Developer friendly error message.
 * @param userMessage User friendly error message, can be used for displaying messages to the user.
 * @param errorMessage User friendly error message.
 * @param errorCode The error code. See [ApiError.errorCodeType].
 * @param invalidParameters Information on invalid parameters send in the request.
 * @param link A link to more information about the error.
 */
@JsonClass(generateAdapter = true)
data class ApiError(

    @Json(name = "developer_message")
    val developerMessage: String? = null,

    @Json(name = "user_message")
    val userMessage: String? = null,

    @Json(name = "error")
    val errorMessage: String? = null,

    @Json(name = "error_code")
    val errorCode: String? = null,

    @Json(name = "invalid_parameters")
    val invalidParameters: List<InvalidParameter>? = null,

    @Json(name = "link")
    val link: String? = null
)

/**
 * @see ApiError.errorCode
 * @see ErrorCodeType
 */
val ApiError.errorCodeType: ErrorCodeType
    get() = errorCode.asEnum(ErrorCodeType.DEFAULT)
