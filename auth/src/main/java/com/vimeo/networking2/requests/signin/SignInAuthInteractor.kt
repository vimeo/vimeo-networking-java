package com.vimeo.networking2.requests.signin

import com.vimeo.networking2.requests.AuthCallback
import com.vimeo.networking2.requests.AuthService
import com.vimeo.networking2.requests.VimeoRequest
import com.vimeo.networking2.utils.enqueueAuthRequest

/**
 * Interactor that handles logging into Vimeo with Google or Facebook authentication.
 */
internal class SignInAuthInteractor(
    private val authService: AuthService,
    private val authHeaders: String
) : SignInAuthenticator {

    override fun signIn(signInAuthParams: SignInAuthParams,
                        authCallback: AuthCallback): VimeoRequest {

        val call = authService.join(authHeaders, signInAuthParams.toMap())
        return call.enqueueAuthRequest(authCallback)
    }

}
