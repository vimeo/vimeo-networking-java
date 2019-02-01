package com.vimeo.networking2

/**
 * Authentication params for signing in or joining with Google or Facebook.
 *
 * @param idToken           Google or Facebook token.
 * @param email             Email used on the Google or Facebook account.
 * @param marketingOptIn    Used to opt in or out of GDPR.
 *
 */
data class SocialAuthParams(val idToken: String, val email: String, val marketingOptIn: Boolean)

