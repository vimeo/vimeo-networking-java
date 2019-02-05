package com.vimeo.networking2.enums

import com.squareup.moshi.Json

/**
 * All the params that can be sent for any type of authentication. This enum contains the name
 * of the param and error information if an invalid value was given for it.
 *
 * @param errorCode         Error code in case the given value for the param is invalid.
 * @param developerMessage  Message to the client on why the param is invalid.
 */
enum class AuthParam(
    val errorCode: ErrorCodeType? = null,
    val developerMessage: String? = null
) {

    @Json(name = "name")
    FIELD_NAME(ErrorCodeType.UNABLE_TO_LOGIN_NO_TOKEN, "An empty or null name was provided."),

    @Json(name = "email")
    FIELD_EMAIL(ErrorCodeType.INVALID_INPUT_NO_EMAIL, "An empty email was provided."),

    @Json(name = "password")
    FIELD_PASSWORD(ErrorCodeType.INVALID_INPUT_NO_PASSWORD, "An empty password was provided."),

    @Json(name = "id_token")
    FIELD_TOKEN(ErrorCodeType.UNABLE_TO_LOGIN_NO_TOKEN, "An empty access token was provided."),

    @Json(name = "username")
    FIELD_USERNAME(ErrorCodeType.INVALID_INPUT_NO_EMAIL, "An empty or null email was provided."),

    @Json(name = "marketing_opt_in")
    FIELD_MARKETING_OPT_IN();
}
