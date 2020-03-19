@file:JvmName("InvalidParameterUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.enums.ErrorCodeType
import com.vimeo.networking2.enums.asEnum
import java.io.Serializable

/**
 * Similar to [ApiError] object, this holds error codes/error messages relevant to a
 * specific invalid field.
 */
@JsonClass(generateAdapter = true)
data class InvalidParameter(

    /**
     * Name of the invalid field.
     */
    @Json(name = "field")
    val field: String? = null,

    /**
     * Error code for the invalid field.
     * @see ApiError.errorCodeType
     */
    @Json(name = "error_code")
    val errorCode: String? = null,

    /**
     * The user readable error message detailing why the request was invalid.
     */
    @Json(name = "error")
    val error: String? = null,

    /**
     * Detailed description on why the field is invalid.
     */
    @Json(name = "developer_message")
    val developerMessage: String? = null

): Serializable {

    companion object {
        private const val serialVersionUID = -324967066105L
    }
}

/**
 * @see InvalidParameter.errorCode
 * @see ErrorCodeType
 */
val InvalidParameter.errorCodeType: ErrorCodeType
    get() = errorCode.asEnum(ErrorCodeType.DEFAULT)
