package com.vimeo.networking2.internal

import com.vimeo.networking2.Scopes
import com.vimeo.networking2.VimeoAccount
import com.vimeo.networking2.VimeoCallback
import com.vimeo.networking2.account.CachingAccountStore
import com.vimeo.networking2.config.AuthenticationMethod
import com.vimeo.networking2.vimeoCallback
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoInteractions

class AuthenticatorImplTest {

    private val accessToken = "token"
    private val vimeoAccount = VimeoAccount(accessToken)
    private val authService: AuthService = mock {
        on { logOut(any()) } doReturn mock()
    }
    private val clientCredentials = AuthenticationMethod.ClientCredentials("id", "secret", Scopes(listOf()))
    private val cachingAccountStorage: CachingAccountStore = mock {
        on { loadAccount() } doReturn vimeoAccount
    }
    private val callback: VimeoCallback<Unit> = vimeoCallback(onSuccess = {}, onError = {})
    private val localVimeoCallAdapter = mock(LocalVimeoCallAdapter::class.java)

    private val authenticator = AuthenticatorImpl(
        authService,
        clientCredentials,
        "test://redirect",
        cachingAccountStorage,
        localVimeoCallAdapter
    )

    @Test
    fun `log out removes access token on server`() {
        authenticator.logOut(callback)
        verify(authService).logOut("Bearer $accessToken")
    }

    @Test
    fun `log out keeps access token on server given flag passed`() {
        authenticator.logOut(callback, keepOnServer = true)
        verifyNoInteractions(authService)
    }
}
