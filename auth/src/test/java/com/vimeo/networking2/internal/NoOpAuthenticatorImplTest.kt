package com.vimeo.networking2.internal

import com.vimeo.networking2.VimeoCallback
import com.vimeo.networking2.account.AccountStore
import com.vimeo.networking2.vimeoCallback
import org.junit.Assert.*

import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.verifyNoInteractions

class NoOpAuthenticatorImplTest {

    private val callback: VimeoCallback<Unit> = vimeoCallback(onSuccess = {}, onError = {})
    private val accountStore = mock(AccountStore::class.java)

    private val authenticator = NoOpAuthenticatorImpl(accountStore)

    @Test(expected = IllegalStateException::class)
    fun `logOut throws error`() {
        authenticator.logOut(callback)
    }

    @Test
    fun `logOutLocally does nothing`() {
        authenticator.logOutLocally()
        verifyNoInteractions(accountStore)
    }
}
