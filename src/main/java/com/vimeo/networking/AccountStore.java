package com.vimeo.networking;

import com.vimeo.networking.model.Account;

/**
 * Responsible for handling the creation, deletion, and loading of Vimeo accounts on the client
 *
 * Created by hanssena on 4/27/15.
 */
public interface AccountStore {

    public Account loadAccount();

    public void saveAccount(Account account, String email, String password);//, boolean isAddingNewAccount);

    public void deleteAccount(Account account);
}
