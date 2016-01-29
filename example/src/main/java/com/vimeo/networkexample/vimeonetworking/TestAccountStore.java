package com.vimeo.networkexample.vimeonetworking;

import android.Manifest.permission;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.RequiresPermission;

import com.vimeo.networkexample.AccountPreferenceManager;
import com.vimeo.networkexample.TestApp;
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

    @Override
    public VimeoAccount loadAccount() {
        return AccountPreferenceManager.getClientAccount();
    }

    @Override
    public void saveAccount(VimeoAccount vimeoAccount, String email, String password) {
        AccountPreferenceManager.setClientAccount(vimeoAccount);
    }

    @Override
    public void deleteAccount(VimeoAccount vimeoAccount) {
        AccountPreferenceManager.removeClientAccount();
        // NOTE: You'll now need a client credentials grant (without an authenticated user)
    }

    public void updateAccount(VimeoAccount vimeoAccount) {
        AccountPreferenceManager.setClientAccount(vimeoAccount);
        VimeoClient.getInstance().setVimeoAccount(vimeoAccount);
    }
}
