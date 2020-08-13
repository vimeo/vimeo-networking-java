/*
 * Copyright (c) 2020 Vimeo (https://vimeo.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.vimeo.networking2.internal

import com.vimeo.networking2.Authenticator
import com.vimeo.networking2.PinCodeInfo
import com.vimeo.networking2.SsoDomain
import com.vimeo.networking2.VimeoAccount
import com.vimeo.networking2.VimeoCallback
import com.vimeo.networking2.VimeoRequest

/**
 * An [Authenticator] that delegates its implementation to an internal mutable instance [actual]. The purpose of this
 * class is to allow the [Authenticator] instance to be re-initialized on the fly. It delegates to an underlying actual
 * implementation that can be changed dynamically. This allows the [Authenticator.initialize] function to change the
 * implementation used without changing the reference returned by [Authenticator.instance].
 *
 * @param actual The actual implementation of [Authenticator], defaults to null.
 */
class MutableAuthenticatorDelegate(var actual: Authenticator? = null) : Authenticator {
    private val authenticator: Authenticator
        get() = actual ?: throw IllegalStateException(
            "Must call Authenticator.initialize() before calling Authenticator.instance()"
        )

    override val currentAccount: VimeoAccount?
        get() = authenticator.currentAccount

    override fun authenticateWithClientCredentials(callback: VimeoCallback<VimeoAccount>): VimeoRequest =
        authenticator.authenticateWithClientCredentials(callback)

    override fun authenticateWithGoogle(
        token: String,
        email: String,
        marketingOptIn: Boolean,
        callback: VimeoCallback<VimeoAccount>
    ): VimeoRequest = authenticator.authenticateWithGoogle(token, email, marketingOptIn, callback)

    override fun authenticateWithFacebook(
        token: String,
        email: String,
        marketingOptIn: Boolean,
        callback: VimeoCallback<VimeoAccount>
    ): VimeoRequest = authenticator.authenticateWithFacebook(token, email, marketingOptIn, callback)

    override fun authenticateWithEmailJoin(
        displayName: String,
        email: String,
        password: String,
        marketingOptIn: Boolean,
        callback: VimeoCallback<VimeoAccount>
    ): VimeoRequest = authenticator.authenticateWithEmailJoin(displayName, email, password, marketingOptIn, callback)

    override fun authenticateWithEmailLogin(
        email: String,
        password: String,
        callback: VimeoCallback<VimeoAccount>
    ): VimeoRequest = authenticator.authenticateWithEmailLogin(email, password, callback)

    override fun exchangeAccessToken(accessToken: String, callback: VimeoCallback<VimeoAccount>): VimeoRequest =
        authenticator.exchangeAccessToken(accessToken, callback)

    override fun exchangeOAuth1Token(
        token: String,
        tokenSecret: String,
        callback: VimeoCallback<VimeoAccount>
    ): VimeoRequest = authenticator.exchangeOAuth1Token(token, tokenSecret, callback)

    override fun createCodeGrantAuthorizationUri(responseCode: String): String =
        authenticator.createCodeGrantAuthorizationUri(responseCode)

    override fun authenticateWithCodeGrant(uri: String, callback: VimeoCallback<VimeoAccount>): VimeoRequest =
        authenticator.authenticateWithCodeGrant(uri, callback)

    override fun fetchSsoDomain(domain: String, callback: VimeoCallback<SsoDomain>): VimeoRequest =
        authenticator.fetchSsoDomain(domain, callback)

    override fun createSsoAuthorizationUri(ssoDomain: SsoDomain, responseCode: String): String =
        authenticator.createSsoAuthorizationUri(ssoDomain, responseCode)

    override fun authenticateWithSso(
        uri: String,
        marketingOptIn: Boolean,
        callback: VimeoCallback<VimeoAccount>
    ): VimeoRequest = authenticator.authenticateWithSso(uri, marketingOptIn, callback)

    override fun fetchPinCodeInfo(callback: VimeoCallback<PinCodeInfo>): VimeoRequest =
        authenticator.fetchPinCodeInfo(callback)

    override fun authenticateWithPinCode(
        pinCodeInfo: PinCodeInfo,
        callback: VimeoCallback<VimeoAccount>
    ): VimeoRequest = authenticator.authenticateWithPinCode(pinCodeInfo, callback)

    override fun logOut(callback: VimeoCallback<Unit>): VimeoRequest =
        authenticator.logOut(callback)
}
