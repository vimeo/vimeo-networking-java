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
 * Created by anthonyr on 5/8/17.
 */
@Suppress("unused")
object AccountPreferenceManager {

    private lateinit var sharedPreferences: SharedPreferences

    @Synchronized
    fun initializeInstance(context: Context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    }

    // <editor-fold desc="Account">

    private const val CLIENT_ACCOUNT_JSON = "CLIENT_ACCOUNT_JSON"
    private const val CACHED_CLIENT_CREDENTIALS_ACCOUNT_JSON = "CACHED_CLIENT_CREDENTIALS_ACCOUNT_JSON"

    fun removeClientAccount() {
        sharedPreferences.edit().remove(CLIENT_ACCOUNT_JSON).apply()
    }

    // NOTE: This happens on the main thread, don't do this
    // NOTE: This happens on the main thread, don't do this
    var clientAccount: VimeoAccount?
        get() {
            val accountJSON = sharedPreferences.getString(CLIENT_ACCOUNT_JSON, null) ?: return null

            return VimeoNetworkUtil.getGson().fromJson(accountJSON, VimeoAccount::class.java)
        }
        set(vimeoAccount) {
            if (vimeoAccount != null) {
                VimeoNetworkUtil.getGson().toJson(vimeoAccount)?.also {
                    sharedPreferences.edit().putString(CLIENT_ACCOUNT_JSON, it).apply()
                } ?: removeClientAccount()
            }
        }

    val currentUser: User? get() = AccountPreferenceManager.clientAccount?.user

    fun cacheClientCredentialsAccount(vimeoAccount: VimeoAccount?) {
        vimeoAccount?.run {
            val accountJSON = VimeoNetworkUtil.getGson().toJson(vimeoAccount) ?: return
            sharedPreferences.edit().putString(CACHED_CLIENT_CREDENTIALS_ACCOUNT_JSON, accountJSON).apply()
        }
    }

    val cachedClientCredentialsAccount: VimeoAccount?
        get() {
            val accountJSON = sharedPreferences.getString(CACHED_CLIENT_CREDENTIALS_ACCOUNT_JSON, null)
                ?: return null

            return VimeoNetworkUtil.getGson().fromJson(accountJSON, VimeoAccount::class.java)
        }

    // </editor-fold>

}
