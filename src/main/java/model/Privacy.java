package model;

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
}
