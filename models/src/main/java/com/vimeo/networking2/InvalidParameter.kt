@file:JvmName("InvalidParameterUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.enums.ErrorCodeType
import com.vimeo.networking2.enums.asEnum

/**
 * Similar to [ApiError] object, this holds error codes/error messages relevant to a specific invalid field.
 *
 * @param field Name of the invalid field.
 * @param errorCode Error code for the invalid field. See [ApiError.errorCodeType].
 * @param error The user readable error message detailing why the request was invalid.
 * @param developerMessage Detailed description of why the field is invalid.
 */
@JsonClass(generateAdapter = true)
data class InvalidParameter(

    @Json(name = "field")
    val field: String? = null,

    @Json(name = "error_code")
    val errorCode: String? = null,

    @Json(name = "error")
    val error: String? = null,

    @Json(name = "developer_message")
    val developerMessage: String? = null

)

/**
 * @see InvalidParameter.errorCode
 * @see ErrorCodeType
 */
val InvalidParameter.errorCodeType: ErrorCodeType
    get() = errorCode.asEnum(ErrorCodeType.DEFAULT)
