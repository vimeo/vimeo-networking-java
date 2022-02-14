package com.vimeo.networking2.internal

import com.vimeo.networking2.Authenticator
import com.vimeo.networking2.PinCodeInfo
import com.vimeo.networking2.SsoDomain
import com.vimeo.networking2.TeamToken
import com.vimeo.networking2.VimeoAccount
import com.vimeo.networking2.VimeoCallback
import com.vimeo.networking2.VimeoRequest
import com.vimeo.networking2.account.AccountStore

/**
 * A no-op [Authenticator] that only exposes the current account and otherwise rejects any authentication attempts.
 *
 * @param accountStore The store used to access the current account.
 */
class NoOpAuthenticatorImpl(
    private val accountStore: AccountStore
) : Authenticator {

    override val currentAccount: VimeoAccount?
        get() = accountStore.loadAccount()

    override fun authenticateWithClientCredentials(callback: VimeoCallback<VimeoAccount>): VimeoRequest = reject()

    override fun authenticateWithGoogle(
        token: String,
        username: String,
        marketingOptIn: Boolean,
        callback: VimeoCallback<VimeoAccount>
    ): VimeoRequest = reject()

    override fun authenticateWithFacebook(
        token: String,
        username: String,
        marketingOptIn: Boolean,
        callback: VimeoCallback<VimeoAccount>
    ): VimeoRequest = reject()

    override fun authenticateWithEmailJoin(
        displayName: String,
        email: String,
        password: String,
        marketingOptIn: Boolean,
        callback: VimeoCallback<VimeoAccount>
    ): VimeoRequest = reject()

    override fun authenticateWithEmailLogin(
        username: String,
        password: String,
        callback: VimeoCallback<VimeoAccount>
    ): VimeoRequest = reject()

    override fun exchangeAccessToken(accessToken: String, callback: VimeoCallback<VimeoAccount>): VimeoRequest =
        reject()

    override fun exchangeOAuth1Token(
        token: String,
        tokenSecret: String,
        callback: VimeoCallback<VimeoAccount>
    ): VimeoRequest = reject()

    override fun createCodeGrantAuthorizationUri(responseCode: String): String = reject()

    override fun authenticateWithCodeGrant(uri: String, callback: VimeoCallback<VimeoAccount>): VimeoRequest = reject()

    override fun fetchSsoDomain(domain: String, callback: VimeoCallback<SsoDomain>): VimeoRequest = reject()

    override fun createSsoAuthorizationUri(ssoDomain: SsoDomain, responseCode: String): String = reject()

    override fun authenticateWithSso(
        uri: String,
        marketingOptIn: Boolean,
        callback: VimeoCallback<VimeoAccount>
    ): VimeoRequest = reject()

    override fun fetchPinCodeInfo(callback: VimeoCallback<PinCodeInfo>): VimeoRequest = reject()

    override fun authenticateWithPinCode(
        pinCodeInfo: PinCodeInfo,
        callback: VimeoCallback<VimeoAccount>
    ): VimeoRequest = reject()

    override fun logOut(callback: VimeoCallback<Unit>): VimeoRequest = reject()

    override fun logOutLocally() = Unit

    override fun getTeamToken(teamId: String, callback: VimeoCallback<TeamToken>) = reject()

    private fun reject(): Nothing = error("Authentication is not supported when using a fixed access token")
}
