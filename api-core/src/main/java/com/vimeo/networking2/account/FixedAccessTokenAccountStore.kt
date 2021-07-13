package com.vimeo.networking2.account

import com.vimeo.networking2.VimeoAccount

/**
 * An [AccountStore] that always uses the same access token to access the API. By using this store and providing a fixed
 * token, you will not be able to log into a different account and will only be able to utilize the account associated
 * with this token. If you wish to support logging into a different account, utilize the default [InMemoryAccountStore]
 * or implement your own disk backed [AccountStore].
 *
 * @param accessToken The access token that will be used by the SDK.
 */
class FixedAccessTokenAccountStore(accessToken: String) : AccountStore {
    private val vimeoAccount = VimeoAccount(accessToken = accessToken)

    override fun loadAccount(): VimeoAccount = vimeoAccount

    override fun storeAccount(vimeoAccount: VimeoAccount) = Unit

    override fun removeAccount() = Unit
}
