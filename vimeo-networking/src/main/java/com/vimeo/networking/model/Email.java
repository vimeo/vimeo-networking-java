package com.vimeo.networking.model;

import com.vimeo.stag.GsonAdapterKey;

import java.io.Serializable;

/**
 * An email that the user has verified.
 * <p/>
 * Created by anthonyrestaino on 6/16/16.
 */
public class Email implements Serializable {

    private static final long serialVersionUID = -4112910222188194649L;
    @GsonAdapterKey("email")
    public String email;
}
