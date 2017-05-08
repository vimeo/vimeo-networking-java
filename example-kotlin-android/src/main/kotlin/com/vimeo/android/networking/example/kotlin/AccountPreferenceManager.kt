package com.vimeo.android.networking.example.kotlin

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

import com.vimeo.networking.model.User
import com.vimeo.networking.model.VimeoAccount
import com.vimeo.networking.utils.VimeoNetworkUtil

/**
 * Class for persisting the the auth access token (and possibly the user)
 *
 *
 * Created by kylevenn on 5/26/15.
 */
object AccountPreferenceManager {

    private var sSharedPreferences: SharedPreferences? = null

    @Synchronized fun initializeInstance(context: Context) {
        if (sSharedPreferences == null) {
            sSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        }
    }

    // <editor-fold desc="Account">

    val CLIENT_ACCOUNT_JSON = "CLIENT_ACCOUNT_JSON"
    val CACHED_CLIENT_CREDENTIALS_ACCOUNT_JSON = "CACHED_CLIENT_CREDENTIALS_ACCOUNT_JSON"

    fun removeClientAccount() {
        sSharedPreferences!!.edit().remove(CLIENT_ACCOUNT_JSON).apply()
    }

    // NOTE: This happens on the main thread, don't do this
    // NOTE: This happens on the main thread, don't do this
    var clientAccount: VimeoAccount?
        get() {
            val accountJSON = sSharedPreferences!!.getString(CLIENT_ACCOUNT_JSON, null)
            return if (accountJSON == null) {
                null
            } else {
                VimeoNetworkUtil.getGson().fromJson(accountJSON, VimeoAccount::class.java)
            }
        }
        set(vimeoAccount) {
            if (vimeoAccount != null) {
                val accountJSON = VimeoNetworkUtil.getGson().toJson(vimeoAccount)
                if (accountJSON == null) {
                    removeClientAccount()
                    return
                }
                sSharedPreferences!!.edit().putString(CLIENT_ACCOUNT_JSON, accountJSON).apply()
            }
        }

    val currentUser: User?
        get() {
            val clientAccount = AccountPreferenceManager.clientAccount
            return clientAccount?.user
        }

    fun cacheClientCredentialsAccount(vimeoAccount: VimeoAccount?) {
        if (vimeoAccount != null) {
            val accountJSON = VimeoNetworkUtil.getGson().toJson(vimeoAccount) ?: return
            sSharedPreferences!!.edit().putString(CACHED_CLIENT_CREDENTIALS_ACCOUNT_JSON, accountJSON).apply()
        }
    }

    val cachedClientCredentialsAccount: VimeoAccount?
        get() {
            val accountJSON = sSharedPreferences!!.getString(CACHED_CLIENT_CREDENTIALS_ACCOUNT_JSON, null)
            return if (accountJSON == null) {
                null
            } else {
                VimeoNetworkUtil.getGson().fromJson(accountJSON, VimeoAccount::class.java)
            }
        }

    // </editor-fold>

}
