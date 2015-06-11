package com.vimeo.networking.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by hanssena on 4/23/15.
 */
public class Privacy implements Serializable {

    private static final long serialVersionUID = -1679908652622815871L;

    public enum PrivacyValue // TODO: use this [AH] 4/24/2015
    {
        @SerializedName("nobody")
        NOBODY,
        @SerializedName("users")
        USERS,
        @SerializedName("anybody")
        ANYBODY,
        @SerializedName("ptv")
        VOD, // "ptv"
        @SerializedName("contacts")
        CONTACTS,
        @SerializedName("password")
        PASSWORD,
        @SerializedName("disable")
        DISABLE
    }

    public PrivacyValue view;
    public String embed;
    public boolean download;
    public boolean add;
    public String comments;

    static public String privacyStringFromValue(PrivacyValue value) {
        String privacyString = null;

        switch (value) {
            case NOBODY:
                privacyString = "nobody";
                break;

            case USERS:
                privacyString = "users";
                break;

            case ANYBODY:
                privacyString = "anybody";
                break;

            case VOD:
                privacyString = "ptv";
                break;

            case CONTACTS:
                privacyString = "contacts";
                break;

            case PASSWORD:
                privacyString = "password";
                break;

            case DISABLE:
                privacyString = "disable";
                break;

            default:
                privacyString = "anybody";
                break;
        }

        return privacyString;
    }
}
