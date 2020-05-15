@file:JvmName("ApiErrorUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.enums.ErrorCodeType
import com.vimeo.networking2.enums.asEnum
import java.io.Serializable

/**
 *  This class represents an error response from the Vimeo API. It holds useful getters to
 *  understand why your request might have failed.
 */
@JsonClass(generateAdapter = true)
data class ApiError(

    /**
     * Developer friendly error message.
     */
    @Json(name = "developer_message")
    val developerMessage: String? = null,

    /**
     * User friendly error message, can be used for displaying messages to the user.
     */
    @Json(name = "user_message")
    val userMessage: String? = null,

    /**
     * User friendly error message.
     */
    @Json(name = "error")
    val errorMessage: String? = null,

    /**
     * The error code.
     * @see ApiError.errorCodeType
     */
    @Json(name = "error_code")
    val errorCode: String? = null,

    /**
     * Information on invalid parameters send in the request.
     */
    @Json(name = "invalid_parameters")
    val invalidParameters: List<InvalidParameter>? = null,

    /**
     * A link to more information about the error.
     */
    @Json(name = "link")
    val link: String? = null

) : Serializable {
    companion object {
        private const val serialVersionUID = -4906209L
    }
}

/**
 * @see ApiError.errorCode
 * @see ErrorCodeType
 */
val ApiError.errorCodeType: ErrorCodeType
    get() = errorCode.asEnum(ErrorCodeType.DEFAULT)
