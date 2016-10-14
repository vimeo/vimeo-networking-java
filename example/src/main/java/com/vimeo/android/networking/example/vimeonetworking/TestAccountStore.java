package com.vimeo.android.networking.example.vimeonetworking;

import android.Manifest.permission;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;

import com.vimeo.android.networking.example.AccountPreferenceManager;
import com.vimeo.android.networking.example.TestApp;
import com.vimeo.networking.AccountStore;
import com.vimeo.networking.VimeoClient;
import com.vimeo.networking.model.VimeoAccount;

/**
 * An account store that is backed by {@link SharedPreferences}.
 * <p/>
 * Note: This class can be used with Android's {@link AccountManager} to tie into the Android device Accounts
 * <p/>
 * Created by kylevenn on 1/27/16.
 */
public class TestAccountStore implements AccountStore {

    private AccountManager mAccountManager;

    @RequiresPermission(permission.GET_ACCOUNTS)
    public TestAccountStore(Context context) {
        if (context == null || !(context instanceof TestApp)) {
            throw new AssertionError("context and vimeoApp must not be null");
        }

        // NOTE: You can use the account manager in the below methods to hook into the Android Accounts
//        mAccountManager = AccountManager.get(context);
    }

    @Nullable
    @Override
    public VimeoAccount loadAccount() {
        return AccountPreferenceManager.getClientAccount();
    }

    @Override
    public void saveAuthenticatedUserAccount(@NonNull VimeoAccount vimeoAccount,
                                             @NonNull String accountName) {
        // In non-demo code, you may want to store the account name. It is left off in
        // this demo, but may be used as a key (such as in the Android Account Manager).
        AccountPreferenceManager.setClientAccount(vimeoAccount);
    }

    @Override
    public void saveNonUserAccount(@NonNull VimeoAccount vimeoAccount) {
        AccountPreferenceManager.setClientAccount(vimeoAccount);
    }

    @Override
    public void deleteAccount(@NonNull VimeoAccount vimeoAccount) {
        AccountPreferenceManager.removeClientAccount();
        // NOTE: You'll now need a client credentials grant (without an authenticated user)
    }

    public void updateAccount(VimeoAccount vimeoAccount) {
        AccountPreferenceManager.setClientAccount(vimeoAccount);
        VimeoClient.getInstance().setVimeoAccount(vimeoAccount);
    }
}
