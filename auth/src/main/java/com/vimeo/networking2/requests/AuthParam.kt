package com.vimeo.networking2.requests

import com.vimeo.networking2.enums.ErrorCodeType

enum class AuthParam(
    val paramName: String,
    val errorCode: ErrorCodeType,
    val developerMessage: String? = null
) {
    FIELD_NAME("name", ErrorCodeType.UNABLE_TO_LOGIN_NO_TOKEN, "An empty or null name was provided."),
    FIELD_EMAIL("email", ErrorCodeType.INVALID_INPUT_NO_EMAIL, "An empty email was provided."),
    FIELD_PASSWORD("password", ErrorCodeType.INVALID_INPUT_NO_PASSWORD, "An empty password was provided."),
    FIELD_TOKEN("token", ErrorCodeType.UNABLE_TO_LOGIN_NO_TOKEN, "An empty access token was provided."),
    FIELD_USERNAME("username", ErrorCodeType.INVALID_INPUT_NO_EMAIL, "An empty or null email was provided.")
}
