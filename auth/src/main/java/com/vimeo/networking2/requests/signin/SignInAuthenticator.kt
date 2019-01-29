package com.vimeo.networking2.requests.signin

import com.vimeo.networking2.requests.AuthCallback
import com.vimeo.networking2.requests.VimeoRequest

/**
 * Allows you to sign in or sign up to Vimeo via Google or Facebook.
 */
interface SignInAuthenticator {

    /**
     * Login or join Vimeo via Google or Facebook . If the user logs in with these social channels
     * and doesn't have a Vimeo account, an account will be created.
     *
     * @param signInAuthParams  Google or Facebook authentication params.
     * @param authCallback      Callback to inform you of the result of the authentication.
     */
    fun signIn(signInAuthParams: SignInAuthParams, authCallback: AuthCallback): VimeoRequest

}
