/*
 * Copyright (c) 2015 Vimeo (https://vimeo.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.vimeo.networking.model;

import com.google.gson.annotations.SerializedName;
import com.vimeo.networking.Vimeo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.annotation.Nullable;

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
    @SerializedName("upload_quota")
    public UploadQuota uploadQuota;

    public AccountType getAccountType() {
        if (this.account == null) {
            //We should assume the account object could be null; also, a User object could be created with
            // just a uri, then updated when fetched from the server, so account would be null until then.
            // Scenario: deeplinking. [KZ] 9/29/15
            return AccountType.BASIC;
        }
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

    /**
     * -----------------------------------------------------------------------------------------------------
     * Follow Accessors/Helpers
     * -----------------------------------------------------------------------------------------------------
     */
    // <editor-fold desc="Follow Accessors/Helpers">
    public boolean canFollow() {
        return getFollowInteraction() != null;
    }

    public boolean isFollowing() {
        return getFollowInteraction() != null && metadata.interactions.follow.added;
    }

    @Nullable
    public Interaction getFollowInteraction() {
        if (metadata != null && metadata.interactions != null && metadata.interactions.follow != null) {
            return metadata.interactions.follow;
        }
        return null;
    }

    @Nullable
    public Connection getFollowingConnection() {
        if (metadata != null && metadata.connections != null) {
            return metadata.connections.following;
        }
        return null;
    }

    @Nullable
    public Connection getFollowersConnection() {
        if (metadata != null && metadata.connections != null) {
            return metadata.connections.followers;
        }
        return null;
    }

    public int getFollowerCount() {
        if (getFollowersConnection() != null) {
            return getFollowersConnection().total;
        }
        return 0;
    }

    public int getFollowingCount() {
        if (getFollowingConnection() != null) {
            return getFollowingConnection().total;
        }
        return 0;
    }

    // TODO: The below methods might be better suited in UserUtils 11/15/15 [KV]
    // Object modification shouldn't really be condoned...unless it should?
    // Returns if it was able to successfully change the count
    public boolean changeFollowerCount(boolean follow) {
        if (getFollowersConnection() != null) {
            if (follow) {
                getFollowersConnection().total += 1;
            } else {
                getFollowersConnection().total -= 1;
            }
            return true;
        }
        return false;
    }

    // Returns if it was able to successfully increment the count
    public boolean changeFollowingCount(boolean follow) {
        if (getFollowingConnection() != null) {
            if (follow) {
                getFollowingConnection().total += 1;
            } else {
                getFollowingConnection().total -= 1;
            }
            return true;
        }
        return false;
    }

    public boolean changeFollowStatus(boolean follow) {
        if (getFollowInteraction() != null) {
            getFollowInteraction().added = follow;
            return true;
        }
        return false;
    }
    // </editor-fold>

    public int getVideoCount() {
        if ((metadata != null) && (metadata.connections != null) && (metadata.connections.videos != null)) {
            return metadata.connections.videos.total;
        }
        return 0;
    }

    public boolean isPlusOrPro() {
        boolean plusOrPro = false;
        if (((getAccountType() == AccountType.PLUS) || (getAccountType() == AccountType.PRO))) {
            plusOrPro = true;
        }
        return plusOrPro;
    }

    public boolean canUploadPicture() {
        if ((metadata != null) && (metadata.connections != null) &&
            (metadata.connections.pictures != null) &&
            (metadata.connections.pictures.options != null)) {
            return metadata.connections.pictures.options.contains(Vimeo.OPTIONS_POST);
        }
        return false;
    }

    public UploadQuota getUploadQuota() {
        return uploadQuota;
    }

    // Returns -1 if there is no space object on this user
    public long getFreeUploadSpace() {
        if (uploadQuota != null) {
            return uploadQuota.getFreeUploadSpace();
        }
        return -1;
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
