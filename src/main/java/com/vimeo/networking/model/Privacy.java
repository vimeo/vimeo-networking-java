package com.vimeo.networking.model;

/**
 * Created by hanssena on 4/23/15.
 */
public class Privacy
{
    public enum PrivacyValue // TODO: use this [AH] 4/24/2015
    {
        NOBODY,
        USERS,
        ANYBODY,
        VOD, // "ptv"
        CONTACTS,
        PASSWORD,
        DISABLE
    }

    public String view;
    public String embed;
    public boolean download;
    public boolean add;
    public String comments;

    static public String privacyStringFromValue(PrivacyValue value)
    {
        String privacyString = null;

        switch (value)
        {
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
