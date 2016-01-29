package com.vimeo.networkexample;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;

import com.vimeo.networking.model.User;
import com.vimeo.networking.model.VimeoAccount;
import com.vimeo.networking.utils.VimeoNetworkUtil;

/**
 * Class for persisting the the auth access token (and possibly the user)
 * <p/>
 * Created by kylevenn on 5/26/15.
 */
public class AccountPreferenceManager {

    private static AccountPreferenceManager mInstance;
    private static SharedPreferences mSharedPreferences;

    private AccountPreferenceManager(Context context) {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static synchronized void initializeInstance(Context context) {
        if (mInstance == null) {
            mInstance = new AccountPreferenceManager(context.getApplicationContext());
        }
    }

    // <editor-fold desc="Account">

    public static final String CLIENT_ACCOUNT_JSON = "CLIENT_ACCOUNT_JSON";
    public static final String CACHED_CLIENT_CREDENTIALS_ACCOUNT_JSON =
            "CACHED_CLIENT_CREDENTIALS_ACCOUNT_JSON";

    public static void removeClientAccount() {
        mSharedPreferences.edit().remove(CLIENT_ACCOUNT_JSON).apply();
    }

    @Nullable
    public static VimeoAccount getClientAccount() {
        // NOTE: This happens on the main thread, don't do this
        String accountJSON = mSharedPreferences.getString(CLIENT_ACCOUNT_JSON, null);
        return accountJSON == null ? null : VimeoNetworkUtil.getGson()
                .fromJson(accountJSON, VimeoAccount.class);
    }

    public static void setClientAccount(VimeoAccount vimeoAccount) {
        if (vimeoAccount != null) {
            // NOTE: This happens on the main thread, don't do this
            String accountJSON = VimeoNetworkUtil.getGson().toJson(vimeoAccount);
            if (accountJSON == null) {
                removeClientAccount();
                return;
            }
            mSharedPreferences.edit().putString(CLIENT_ACCOUNT_JSON, accountJSON).apply();
        }
    }

    @Nullable
    public static User getCurrentUser() {
        VimeoAccount clientAccount = AccountPreferenceManager.getClientAccount();
        return clientAccount != null ? clientAccount.getUser() : null;
    }

    public static void cacheClientCredentialsAccount(VimeoAccount vimeoAccount) {
        if (vimeoAccount != null) {
            String accountJSON = VimeoNetworkUtil.getGson().toJson(vimeoAccount);
            if (accountJSON == null) {
                return;
            }
            mSharedPreferences.edit().putString(CACHED_CLIENT_CREDENTIALS_ACCOUNT_JSON, accountJSON).apply();
        }
    }

    public static VimeoAccount getCachedClientCredentialsAccount() {
        String accountJSON = mSharedPreferences.getString(CACHED_CLIENT_CREDENTIALS_ACCOUNT_JSON, null);
        return accountJSON == null ? null : VimeoNetworkUtil.getGson()
                .fromJson(accountJSON, VimeoAccount.class);
    }

    // </editor-fold>

}
