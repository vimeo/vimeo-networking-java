package com.networking;

import model.Account;

/**
 * Created by hanssena on 4/27/15.
 */
public interface AccountStore
{
    public void saveAccount(Account account, String email, String password, boolean isAddingNewAccount);

    public Account loadAccount();

    public void deleteAccount(Account account);
}
