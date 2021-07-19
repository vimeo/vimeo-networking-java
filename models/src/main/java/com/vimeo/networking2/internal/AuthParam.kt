package com.vimeo.networking2.internal

import com.squareup.moshi.Json
import com.vimeo.networking2.enums.ErrorCodeType

/**
 * All the params that can be sent for any type of authentication. This enum contains the name
 * of the param and error information if an invalid value was given for it.
 *
 * @param errorCode Error code in case the given value for the param is invalid.
 * @param developerMessage Message to the client on why the param is invalid.
 */
enum class AuthParam(
    val errorCode: ErrorCodeType? = null,
    val developerMessage: String? = null
) {

    @Json(name = "name")
    FIELD_NAME(ErrorCodeType.INVALID_INPUT_NO_NAME, "An empty name was provided."),

    @Json(name = "email")
    FIELD_EMAIL(ErrorCodeType.INVALID_INPUT_NO_EMAIL, "An empty email was provided."),

    @Json(name = "username")
    FIELD_USERNAME(ErrorCodeType.INVALID_INPUT_EMPTY_USER_NAME, "An empty username was provided."),

    @Json(name = "password")
    FIELD_PASSWORD(ErrorCodeType.INVALID_INPUT_NO_PASSWORD, "An empty password was provided."),

    @Json(name = "id_token")
    FIELD_ID_TOKEN(ErrorCodeType.INVALID_TOKEN, "An empty id token provided"),

    @Json(name = "token")
    FIELD_TOKEN(ErrorCodeType.INVALID_TOKEN, "An empty access token was provided."),

    @Json(name = "token_secret")
    FIELD_TOKEN_SECRET(ErrorCodeType.INVALID_TOKEN, "An empty token secret was provided."),

    @Json(name = "grant_type")
    FIELD_GRANT_TYPE(ErrorCodeType.INVALID_INPUT_GRANT_TYPE, "Grant type not provided."),

    @Json(name = "scope")
    FIELD_SCOPES(developerMessage = "Scopes were not provided."),

    @Json(name = "domain")
    DOMAIN(developerMessage = "An empty domain was provided."),

    @Json(name = "authorization_code")
    AUTHORIZATION_CODE(developerMessage = "An empty authorization code was provided."),

    @Json(name = "redirect_uri")
    REDIRECT_URI(developerMessage = "An empty redirect uri was provided.")
}
