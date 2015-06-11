package com.vimeo.networking.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by alfredhanssen on 4/12/15.
 */

public class User implements Serializable {

    private static final long serialVersionUID = -4112910222188194647L;

    public enum AccountType {
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
    public Date createdTime;
    public String account;
    public PictureCollection pictures;
    public ArrayList<Website> websites;
    public Metadata metadata;

    public AccountType getAccountType() {
        if (this.account.equals("basic")) {
            return AccountType.BASIC;
        } else if (this.account.equals("plus")) {
            return AccountType.PLUS;
        } else if (this.account.equals("pro")) {
            return AccountType.PRO;
        } else if (this.account.equals("staff")) {
            return AccountType.STAFF;
        }

        return AccountType.BASIC;
    }

    public boolean canFollow() {
        if (metadata != null && metadata.interactions != null && metadata.interactions.follow != null) {
            return true;
        }
        return false;
    }

    public boolean isFollowing() {
        if (metadata != null && metadata.interactions != null && metadata.interactions.follow != null) {
            return metadata.interactions.follow.added;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User that = (User) o;

        return ((this.uri != null && that.uri != null) ? this.uri.equals(that.uri) : false);
    }

    @Override
    public int hashCode() {
        return this.uri != null ? this.uri.hashCode() : 0;
    }
}
