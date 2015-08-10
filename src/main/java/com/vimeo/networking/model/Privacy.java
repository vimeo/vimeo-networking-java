package com.vimeo.networking.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by hanssena on 4/23/15.
 */
public class Privacy implements Serializable {

    private static final long serialVersionUID = -1679908652622815871L;
    private static final String PRIVACY_NOBODY = "nobody";
    private static final String PRIVACY_USERS = "users";
    private static final String PRIVACY_ANYBODY = "anybody";
    private static final String PRIVACY_VOD = "ptv";
    private static final String PRIVACY_CONTACTS = "contacts";
    private static final String PRIVACY_PASSWORD = "password";
    private static final String PRIVACY_DISABLE = "disable";

    public enum PrivacyValue {
        @SerializedName(PRIVACY_NOBODY)
        NOBODY,
        @SerializedName(PRIVACY_USERS)
        USERS,
        @SerializedName(PRIVACY_ANYBODY)
        ANYBODY,
        @SerializedName(PRIVACY_VOD)
        VOD, // "ptv"
        @SerializedName(PRIVACY_CONTACTS)
        CONTACTS,
        @SerializedName(PRIVACY_PASSWORD)
        PASSWORD,
        @SerializedName(PRIVACY_DISABLE)
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
                privacyString = PRIVACY_NOBODY;
                break;

            case USERS:
                privacyString = PRIVACY_USERS;
                break;

            case VOD:
                privacyString = PRIVACY_VOD;
                break;

            case CONTACTS:
                privacyString = PRIVACY_CONTACTS;
                break;

            case PASSWORD:
                privacyString = PRIVACY_PASSWORD;
                break;

            case DISABLE:
                privacyString = PRIVACY_DISABLE;
                break;

            case ANYBODY:
            default:
                privacyString = PRIVACY_ANYBODY;
                break;
        }

        return privacyString;
    }

    static public PrivacyValue privacyValueFromString(final String string) {
        PrivacyValue privacyValue = PrivacyValue.ANYBODY;
        switch (string) {
            case PRIVACY_NOBODY:
                privacyValue = PrivacyValue.NOBODY;
                break;
            case PRIVACY_USERS:
                privacyValue = PrivacyValue.USERS;
                break;
            case PRIVACY_ANYBODY:
                privacyValue = PrivacyValue.ANYBODY;
                break;
            case PRIVACY_VOD:
                privacyValue = PrivacyValue.VOD;
                break;
            case PRIVACY_CONTACTS:
                privacyValue = PrivacyValue.CONTACTS;
                break;
            case PRIVACY_PASSWORD:
                privacyValue = PrivacyValue.PASSWORD;
                break;
            case PRIVACY_DISABLE:
                privacyValue = PrivacyValue.DISABLE;
                break;
        }
        return privacyValue;
    }
}
