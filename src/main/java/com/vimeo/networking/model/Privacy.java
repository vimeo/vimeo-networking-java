package com.vimeo.networking.model;

import java.io.Serializable;

/**
 * Created by hanssena on 4/23/15.
 */
public class Privacy implements Serializable {

    private static final long serialVersionUID = -1679908652622815871L;

    public enum PrivacyValue // TODO: use this [AH] 4/24/2015
    {
        NOBODY("nobody"),
        USERS("users"),
        ANYBODY("anybody"),
        VOD("ptv"), // "ptv"
        CONTACTS("contacts"),
        PASSWORD("password"),
        DISABLE("disable");

        private String text;

        PrivacyValue(String text) {
            this.text = text;
        }

        public String getText() {
            return this.text;
        }

        public static PrivacyValue fromString(String text) {
            if (text != null) {
                for (PrivacyValue b : PrivacyValue.values()) {
                    if (text.equalsIgnoreCase(b.text)) {
                        return b;
                    }
                }
            }
            return null;
        }
    }

    public String view;
    public String embed;
    public boolean download;
    public boolean add;
    public String comments;

    // TODO: eventually this shouldn't be necessary
    public PrivacyValue getView() {
        return PrivacyValue.fromString(view);
    }

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
