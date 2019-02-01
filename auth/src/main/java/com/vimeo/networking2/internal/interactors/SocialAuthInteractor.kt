package com.vimeo.networking2.internal.interactors

import com.vimeo.networking2.AuthCallback
import com.vimeo.networking2.NoOpVimeoRequest
import com.vimeo.networking2.VimeoRequest
import com.vimeo.networking2.internal.AuthService
import com.vimeo.networking2.internal.createApiErrorForInvalidParams
import com.vimeo.networking2.internal.enqueueAuthRequest

/**
 * Interactor that handles logging into Vimeo with Google or Facebook.
 *
 * @param authService   Retrofit service for the sign in request.
 * @param authHeaders   Client id and client secret headers.
 */
internal class SocialAuthInteractor(
    private val authService: AuthService,
    private val authHeaders: String
) {

    /**
     * Validates the params given the client in [SocialAuthParams]. If they were
     * invalid values provided to them, it will inform the client of the error. Otherwise,
     * an API request is made and result is returned to the user.
     *
     * @param params        Google or Facebook authentication params such as token, email, etc...
     * @param authCallback  Callback to inform you of the result of the API request.
     */
    fun authenticate(params: SocialAuthParams, authCallback: AuthCallback): VimeoRequest {
        val invalidAuthParams = params.validate()

        return if (invalidAuthParams.isNotEmpty()) {
            val apiErrorResponse = createApiErrorForInvalidParams(
                "Google authentication error.",
                invalidAuthParams
            )
            authCallback.onApiError(apiErrorResponse)
            NoOpVimeoRequest()

        } else {
            val call = authService.join(authHeaders, params.toMap())
            call.enqueueAuthRequest(authCallback)
        }
    }

}
