package com.vimeo.example.vimeonetworking;

import android.Manifest.permission;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.OnAccountsUpdateListener;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.RequiresPermission;

import com.vimeo.example.TestApp;
import com.vimeo.networking.AccountStore;
import com.vimeo.networking.VimeoClient;
import com.vimeo.networking.model.VimeoAccount;

import java.lang.ref.WeakReference;

/**
 * Created by hanssena on 4/27/15.
 */
public class VimeoAccountStore implements AccountStore {

    public static final String ACCOUNT_MANAGER_KEY = "ACCOUNT_MANAGER";
    public static final String ACCOUNT_TYPE_KEY = "ACCOUNT_TYPE";
    public static final String VIMEO_AUTH_TOKEN_TYPE = "vimeo"; // TODO: make this more specific [AH]
    public static final String VIMEO_ACCOUNT_TYPE = "com.vimeo.account";
    // TODO: this value is in 2 places (xml), make 1 [AH]
    private static final String USER_JSON_KEY = "USER_JSON";
    private static final String SCOPE_KEY = "SCOPE";

    private Context mContext;
    private AccountManager mAccountManager;

    @RequiresPermission(permission.GET_ACCOUNTS)
    public VimeoAccountStore(Context context) {
        if (context == null || !(context instanceof TestApp)) {
            throw new AssertionError("context and vimeoApp must not be null");
        }
        mContext = context;
        mAccountManager = AccountManager.get(context);
        mAccountManager.addOnAccountsUpdatedListener(new VimeoAccountUpdateListener(this), null, true);
    }

    @Override
    public VimeoAccount loadAccount() {
        // NOTE: This is a good place to pull the account in SharedPreferences

        Account androidAccount = null;
        try {
            androidAccount = this.getAndroidAccount();
        } catch (AssertionError error) {
            error.printStackTrace();
        }

        if (androidAccount != null) {
            String authToken = mAccountManager.peekAuthToken(androidAccount, VIMEO_AUTH_TOKEN_TYPE);
            String scope = mAccountManager.getUserData(androidAccount, SCOPE_KEY);
            String userData = mAccountManager.getUserData(androidAccount, USER_JSON_KEY);

            VimeoAccount vimeoAccount = new VimeoAccount(authToken, VIMEO_AUTH_TOKEN_TYPE, scope, userData);
            // NOTE: Good place to store account in SharedPreferences
            return vimeoAccount;
        }

        return null;
    }

    @Override
    public void saveAccount(VimeoAccount vimeoAccount, String email, String password) {
        // NOTE: Good place to store account in SharedPreferences
        Account androidAccount = new Account(email, VIMEO_ACCOUNT_TYPE);

        Bundle userData = new Bundle();
        userData.putString(USER_JSON_KEY, vimeoAccount.getUserJSON());
        userData.putString(SCOPE_KEY, vimeoAccount.getScope());

        String authToken = vimeoAccount.getAccessToken();

        mAccountManager.addAccountExplicitly(androidAccount, password, userData);
        mAccountManager.setAuthToken(androidAccount, VIMEO_AUTH_TOKEN_TYPE, authToken);
    }

    @Override
    public void deleteAccount(VimeoAccount vimeoAccount) {
        Account[] accounts = mAccountManager.getAccountsByType(VIMEO_ACCOUNT_TYPE);
        for (Account androidAccount : accounts) {
            mAccountManager.removeAccount(androidAccount, null, null);
        }
        // NOTE: You'll now need a client credentials grant (without an authenticated user user)
    }

    public void updateAccount(VimeoAccount vimeoAccount) {
        VimeoClient.getInstance().setVimeoAccount(vimeoAccount);
        // NOTE: Good place to store account in SharedPreferences

        Account androidAccount = getAndroidAccount();

        if (androidAccount != null) {
            mAccountManager.setUserData(androidAccount, USER_JSON_KEY, vimeoAccount.getUserJSON());
        }
    }

    private Account getAndroidAccount() {
        Account[] accounts = mAccountManager.getAccountsByType(VIMEO_ACCOUNT_TYPE);

        if (accounts == null || accounts.length != 1) {
            // There shouldn't be more than one account
            return null;
        }
        return accounts[0];
    }

    private static class VimeoAccountUpdateListener implements OnAccountsUpdateListener {

        private final WeakReference<VimeoAccountStore> weakAccountStore;

        public VimeoAccountUpdateListener(VimeoAccountStore vimeoAccountStore) {
            this.weakAccountStore = new WeakReference<>(vimeoAccountStore);
        }

        @Override
        public void onAccountsUpdated(Account[] accounts) {
            if (this.weakAccountStore.get() != null) {
                Account androidAccount = null;
                try {
                    androidAccount = this.weakAccountStore.get().getAndroidAccount();
                } catch (AssertionError error) {
                    error.printStackTrace();
                }
                VimeoAccount vimeoAccount = VimeoClient.getInstance().getVimeoAccount();

                // If we have a user account and no Android account then user logged out via settings [AH] 5/4/15
                if (vimeoAccount != null && vimeoAccount.getUser() != null && androidAccount == null) {
                    VimeoClient.getInstance().logOut(null);
                }
            }
        }
    }
}
