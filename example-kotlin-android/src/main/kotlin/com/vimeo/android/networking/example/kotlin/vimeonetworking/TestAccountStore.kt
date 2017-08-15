package com.vimeo.android.networking.example.kotlin.vimeonetworking

import android.accounts.AccountManager
import android.content.Context
import android.content.SharedPreferences
import com.vimeo.android.networking.example.kotlin.AccountPreferenceManager
import com.vimeo.networking.AccountStore
import com.vimeo.networking.VimeoClient
import com.vimeo.networking.model.VimeoAccount

/**
 * An account store that is backed by [SharedPreferences].
 *
 * Note: This class can be used with Android's [AccountManager] to tie into the Android device Accounts
 *
 * Created by anthonyr on 5/8/17.
 */
class TestAccountStore(context: Context?) : AccountStore {

    // NOTE: You can use the account manager in the below methods to hook into the Android Accounts
    // @RequiresPermission(Manifest.permission.GET_ACCOUNTS)
    // val mAccountManager: AccountManager? = AccountManager.get(context!!)

    override fun loadAccount(): VimeoAccount? {
        return AccountPreferenceManager.clientAccount
    }

    override fun saveAccount(vimeoAccount: VimeoAccount, email: String) {
        AccountPreferenceManager.clientAccount = vimeoAccount
    }

    override fun deleteAccount(vimeoAccount: VimeoAccount) {
        AccountPreferenceManager.removeClientAccount()
        // NOTE: You'll now need a client credentials grant (without an authenticated user)
    }

    fun updateAccount(vimeoAccount: VimeoAccount) {
        AccountPreferenceManager.clientAccount = vimeoAccount
        VimeoClient.getInstance().vimeoAccount = vimeoAccount
    }

}