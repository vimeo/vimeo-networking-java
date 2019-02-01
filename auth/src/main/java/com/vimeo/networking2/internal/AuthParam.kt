package com.vimeo.networking2.internal

import com.vimeo.networking2.enums.ErrorCodeType

/**
 * All the params that can be sent for any type of authentication. This enum contains the name
 * of the param and error information if an invalid value was given for it.
 *
 * @param value         Name of the param.
 * @param errorCode         Error code in case the given value for the param is invalid.
 * @param developerMessage  Message to the client on why the param is invalid.
 */
internal enum class AuthParam(
    val value: String,
    val errorCode: ErrorCodeType? = null,
    val developerMessage: String? = null
) {
    FIELD_NAME("name", ErrorCodeType.UNABLE_TO_LOGIN_NO_TOKEN, "An empty or null name was provided."),
    FIELD_EMAIL("email", ErrorCodeType.INVALID_INPUT_NO_EMAIL, "An empty email was provided."),
    FIELD_PASSWORD("password", ErrorCodeType.INVALID_INPUT_NO_PASSWORD, "An empty password was provided."),
    FIELD_TOKEN("id_token", ErrorCodeType.UNABLE_TO_LOGIN_NO_TOKEN, "An empty access token was provided."),
    FIELD_USERNAME("username", ErrorCodeType.INVALID_INPUT_NO_EMAIL, "An empty or null email was provided."),
    FIELD_MARKETING_OPT_IN("marketing_opt_in");
}
