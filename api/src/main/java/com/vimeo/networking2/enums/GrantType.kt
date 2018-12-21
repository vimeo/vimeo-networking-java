package com.vimeo.networking2.enums

/**
 * Grant types.
 */
enum class GrantType(val value: String) {
    CLIENT_CREDENTIALS("client_credentials"),
    AUTHORIZATION_CODE("authorization_code"),
    PASSWORD("password"),
    FACEBOOK("facebook"),
    GOOGLE("google")
}
