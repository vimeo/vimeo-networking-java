package com.vimeo.networking2.internal.interactors

import com.vimeo.networking2.internal.AuthParam

/**
 * Authentication params for signing in or joining with Google or Facebook.
 *
 * @param idToken           Google or Facebook token.
 * @param email             Email used on the Google or Facebook account.
 * @param marketingOptIn    Used to opt in or out of GDPR.
 *
 */
data class SocialAuthParams(val idToken: String, val email: String, val marketingOptIn: Boolean)

/**
 * Converts the [SocialAuthParams] to a map.
 */
fun SocialAuthParams.toMap() =
    mapOf("id_token" to idToken, "email" to email, "marketing_opt_in" to marketingOptIn.toString())

/**
 * Validates the submitted params by checking if they are empty. All empty params
 * are collected into a list of [AuthParam].
 *
 * @return A list of all params that are invalid.
 */
internal fun SocialAuthParams.validate(): List<AuthParam> {
    val invalidAuthParams = mutableListOf<AuthParam>()
    if (idToken.isEmpty()) {
        invalidAuthParams.add(AuthParam.FIELD_TOKEN)
    }
    if (email.isEmpty()) {
        invalidAuthParams.add(AuthParam.FIELD_EMAIL)
    }
    return invalidAuthParams
}
