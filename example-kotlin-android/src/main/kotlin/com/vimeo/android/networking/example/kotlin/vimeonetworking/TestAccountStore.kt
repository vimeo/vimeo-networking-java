package com.vimeo.android.networking.example.kotlin.vimeonetworking

import android.Manifest.permission
import android.accounts.AccountManager
import android.content.Context
import android.content.SharedPreferences
import android.support.annotation.RequiresPermission

import com.vimeo.android.networking.example.kotlin.AccountPreferenceManager
import com.vimeo.android.networking.example.kotlin.TestApp
import com.vimeo.networking.AccountStore
import com.vimeo.networking.VimeoClient
import com.vimeo.networking.model.VimeoAccount

/**
 * An account store that is backed by [SharedPreferences].
 *
 *
 * Note: This class can be used with Android's [AccountManager] to tie into the Android device Accounts
 *
 *
 * Created by kylevenn on 1/27/16.
 */
class TestAccountStore
@RequiresPermission(permission.GET_ACCOUNTS)
constructor(context: Context?) : AccountStore {

    private val mAccountManager: AccountManager? = null

    init {
        if (context == null || context !is TestApp) {
            throw AssertionError("context and vimeoApp must not be null")
        }

        // NOTE: You can use the account manager in the below methods to hook into the Android Accounts
        //        mAccountManager = AccountManager.get(context);
    }

    override fun loadAccount(): VimeoAccount? {
        return AccountPreferenceManager.clientAccount
    }

    @Deprecated("Passwords should not be saved", ReplaceWith("saveAccount(vimeoAccount, email)"))
    override fun saveAccount(vimeoAccount: VimeoAccount, email: String, password: String) {
        saveAccount(vimeoAccount, email)
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
        VimeoClient.getInstance().setVimeoAccount(vimeoAccount)
    }
}
