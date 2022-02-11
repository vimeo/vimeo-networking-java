package com.vimeo.networking2.internal

import com.vimeo.networking2.Authenticator
import com.vimeo.networking2.VimeoCallback
import com.vimeo.networking2.VimeoRequest
import com.vimeo.networking2.vimeoCallback
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class MutableAuthenticatorDelegateTest {

    private val request = mock(VimeoRequest::class.java)
    private val authenticator: Authenticator = mock {
        on { logOut(any()) } doReturn request
    }
    private val callback: VimeoCallback<Unit> = vimeoCallback(onSuccess = {}, onError = {})

    private val delegate = MutableAuthenticatorDelegate(authenticator)

    @Test
    fun `logOut delegates to actual`() {
        assertEquals(request, delegate.logOut(callback))
        verify(authenticator).logOut(callback)
    }

    @Test
    fun `logOutLocally delegates to actual`() {
        delegate.logOutLocally()
        verify(authenticator).logOutLocally()
    }
}
