package com.vimeo.networking;

import java.io.InputStream;

/**
 * A class that holds a trust store file (of type .bks) and the accompanying password
 * </p>
 * Created by kylevenn on 6/10/15.
 */
public class TrustKeyStore {

    public InputStream mKeyStoreInputStream;
    public String mPassword;

    public TrustKeyStore(InputStream certificate, String password) {
        mKeyStoreInputStream = certificate;
        mPassword = password;
    }
}
