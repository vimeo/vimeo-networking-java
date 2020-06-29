package com.vimeo.networking2

/**
 * An account store that just holds the accounts in memory.
 */
class InMemoryAccountStore : AccountStore {
    private var vimeoAccount: VimeoAccount? = null

    override fun loadAccount(): VimeoAccount? = vimeoAccount

    override fun saveAccount(vimeoAccount: VimeoAccount, email: String) {
        this.vimeoAccount = vimeoAccount
    }

    override fun deleteAccount(vimeoAccount: VimeoAccount) {
        this.vimeoAccount = null
    }
}
