package com.vimeo.networking2.requests.signin

/**
 * Authentication params for signing in or joining with Google or Facebook.
 *
 * @param idToken           Google or Facebook token.
 * @param email             Email used on the Google or Facebook account.
 * @param marketingOptIn    Used to opt in or out or GDPR.
 *
 */
data class SignInAuthParams(val idToken: String, val email: String, val marketingOptIn: Boolean)

/**
 * Converts the [SignInAuthParams] to a map.
 */
fun SignInAuthParams.toMap() =
    mapOf("id_token" to idToken, "email" to email, "marketing_opt_in" to marketingOptIn.toString())
