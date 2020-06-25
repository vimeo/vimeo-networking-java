package com.vimeo.networking2

import com.vimeo.networking2.enums.StringValue

/**
 * Grant types.
 */
enum class GrantType(override val value: String) : StringValue {
    CLIENT_CREDENTIALS("client_credentials"),
    AUTHORIZATION_CODE("authorization_code"),
    PASSWORD("password"),
    FACEBOOK("facebook"),
    GOOGLE("google")
}
