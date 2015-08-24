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
        NOBODY(PRIVACY_NOBODY),
        @SerializedName(PRIVACY_USERS)
        USERS(PRIVACY_USERS),
        @SerializedName(PRIVACY_ANYBODY)
        ANYBODY(PRIVACY_ANYBODY),
        @SerializedName(PRIVACY_VOD)
        VOD(PRIVACY_VOD), // "ptv"
        @SerializedName(PRIVACY_CONTACTS)
        CONTACTS(PRIVACY_CONTACTS),
        @SerializedName(PRIVACY_PASSWORD)
        PASSWORD(PRIVACY_PASSWORD),
        @SerializedName(PRIVACY_DISABLE)
        DISABLE(PRIVACY_DISABLE);

        private String text;

        PrivacyValue(String text) {
            this.text = text;
        }

        public String getText() {
            return this.text;
        }

        public static PrivacyValue privacyValueFromString(String text) {
            if (text != null) {
                for (PrivacyValue privacyValue : PrivacyValue.values()) {
                    if (text.equalsIgnoreCase(privacyValue.text)) {
                        return privacyValue;
                    }
                }
            }
            return null;
        }
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
}
