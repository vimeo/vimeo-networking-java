@file:JvmName("ApiErrorUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.vimeo.networking2.enums.ErrorCode
import com.vimeo.networking2.enums.asEnum

/**
 *  This class represents an error response from the Vimeo API. It holds useful getters to
 *  understand why your request might have failed.
 */
data class ApiError(

    /**
     * Developer friendly error message.
     */
    @Json(name = "developer_message")
    val developerMessage: String? = null,

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
     * A link to more information about the error.
     */
    @Json(name = "link")
    val link: String

)

/**
 * @see ApiError.errorCode
 * @see ErrorCode
 */
val ApiError.errorCodeType: ErrorCode
    get() = errorCode.asEnum(ErrorCode.DEFAULT)
