package model;

import java.util.ArrayList;

/**
 * Created by alfredhanssen on 4/12/15.
 */

public class User
{
    public enum AccountType
    {
        BASIC,
        PRO,
        PLUS,
        STAFF
    }

    public String uri;
    public String name;
    public String link;
    public String location;
    public String bio;
    public String createdTime;
    public String account;
    public PictureCollection pictures;
    public ArrayList<Website> websites;
    public Metadata metadata;

    public AccountType getAccountType()
    {
        if (this.account.equals("basic"))
        {
            return AccountType.BASIC;
        }
        else if (this.account.equals("plus"))
        {
            return AccountType.PLUS;
        }
        else if (this.account.equals("pro"))
        {
            return AccountType.PRO;
        }
        else if (this.account.equals("staff"))
        {
            return AccountType.STAFF;
        }

        return AccountType.BASIC;
    }
}
