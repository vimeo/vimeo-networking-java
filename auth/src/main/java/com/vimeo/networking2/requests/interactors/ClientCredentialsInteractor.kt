package com.vimeo.networking2.requests.interactors

import com.vimeo.networking2.GrantType
import com.vimeo.networking2.ScopeType
import com.vimeo.networking2.VimeoRequest
import com.vimeo.networking2.requests.AuthCallback
import com.vimeo.networking2.requests.AuthService
import com.vimeo.networking2.utils.enqueueAuthRequest

/**
 * Interactor which performs auth request with client credentials.
 *
 * @param authService   Retrofit API service for auth.
 * @param authHeaders   Client id and client secret headers.
 * @param scopes        API scopes to support.
 */
internal class ClientCredentialsInteractor(
    private val authService: AuthService,
    private val authHeaders: String,
    private val scopes: List<ScopeType>
) {

    fun authenticate(authCallback: AuthCallback): VimeoRequest {

        val call = authService.authorizeWithClientCredentialsGrant(
            authHeaders,
            GrantType.CLIENT_CREDENTIALS.value,
            scopes.joinToString())

        return call.enqueueAuthRequest(authCallback)

    }

}
