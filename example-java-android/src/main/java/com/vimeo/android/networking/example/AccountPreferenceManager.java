package com.vimeo.android.networking.example;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;

import com.vimeo.networking.utils.VimeoNetworkUtil;
import com.vimeo.networking2.User;
import com.vimeo.networking2.VimeoAccount;

import java.io.IOException;

/**
 * Class for persisting the the auth access token (and possibly the user)
 * <p/>
 * Created by kylevenn on 5/26/15.
 */
public class AccountPreferenceManager {

    private static SharedPreferences sSharedPreferences;

    public static synchronized void initializeInstance(Context context) {
        if (sSharedPreferences == null) {
            sSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        }
    }

    // <editor-fold desc="Account">

    public static final String CLIENT_ACCOUNT_JSON = "CLIENT_ACCOUNT_JSON";
    public static final String CACHED_CLIENT_CREDENTIALS_ACCOUNT_JSON =
            "CACHED_CLIENT_CREDENTIALS_ACCOUNT_JSON";

    public static void removeClientAccount() {
        sSharedPreferences.edit().remove(CLIENT_ACCOUNT_JSON).apply();
    }

    @Nullable
    public static VimeoAccount getClientAccount() throws IOException {
        // NOTE: This happens on the main thread, don't do this
        String accountJSON = sSharedPreferences.getString(CLIENT_ACCOUNT_JSON, null);
        return accountJSON == null ? null : VimeoNetworkUtil.getMoshi()
                .adapter(VimeoAccount.class).fromJson(accountJSON);
    }

    public static void setClientAccount(VimeoAccount vimeoAccount) {
        if (vimeoAccount != null) {
            // NOTE: This happens on the main thread, don't do this
            String accountJSON = VimeoNetworkUtil.getMoshi()
                    .adapter(VimeoAccount.class).toJson(vimeoAccount);
            sSharedPreferences.edit().putString(CLIENT_ACCOUNT_JSON, accountJSON).apply();
        }
    }

    @Nullable
    public static User getCurrentUser() throws IOException {
        VimeoAccount clientAccount = AccountPreferenceManager.getClientAccount();
        return clientAccount != null ? clientAccount.getUser() : null;
    }

    public static void cacheClientCredentialsAccount(VimeoAccount vimeoAccount) {
        if (vimeoAccount != null) {
            String accountJSON = VimeoNetworkUtil.getMoshi()
                    .adapter(VimeoAccount.class).toJson(vimeoAccount);
            sSharedPreferences.edit().putString(CACHED_CLIENT_CREDENTIALS_ACCOUNT_JSON, accountJSON).apply();
        }
    }

    public static VimeoAccount getCachedClientCredentialsAccount() throws IOException {
        String accountJSON = sSharedPreferences.getString(CACHED_CLIENT_CREDENTIALS_ACCOUNT_JSON, null);
        return accountJSON == null ? null : VimeoNetworkUtil.getMoshi()
                .adapter(VimeoAccount.class).fromJson(accountJSON);
    }

    // </editor-fold>

}
