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

import com.vimeo.networking.Vimeo;
import com.vimeo.networking.model.Privacy.PrivacyValue;
import com.vimeo.networking.model.UserBadge.UserBadgeType;
import com.vimeo.stag.GsonAdapterKey;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by alfredhanssen on 4/12/15.
 */

public class User implements Serializable, Followable {

    private static final long serialVersionUID = -4112910222188194647L;
    private static final String ACCOUNT_BASIC = "basic";
    private static final String ACCOUNT_BUSINESS = "business";
    private static final String ACCOUNT_PLUS = "plus";
    private static final String ACCOUNT_PRO = "pro";
    private static final String ACCOUNT_STAFF = "staff";

    public enum AccountType {
        BASIC,
        BUSINESS,
        PRO,
        PLUS,
        STAFF
    }

    @GsonAdapterKey("uri")
    public String uri;
    @GsonAdapterKey("name")
    public String name;
    @GsonAdapterKey("link")
    public String link;
    @GsonAdapterKey("location")
    public String location;
    @GsonAdapterKey("bio")
    public String bio;
    @GsonAdapterKey("created_time")
    public Date createdTime;
    @GsonAdapterKey("account")
    public String account;
    @GsonAdapterKey("pictures")
    public PictureCollection pictures;
    @GsonAdapterKey("emails")
    public ArrayList<Email> emails;
    @GsonAdapterKey("websites")
    public ArrayList<Website> websites;
    @GsonAdapterKey("metadata")
    public Metadata metadata;
    @GsonAdapterKey("upload_quota")
    public UploadQuota uploadQuota;
    @Nullable
    @GsonAdapterKey("preferences")
    public Preferences preferences;
    @Nullable
    @GsonAdapterKey("badge")
    public UserBadge badge;

    public AccountType getAccountType() {
        if (this.account == null) {
            //We should assume the account object could be null; also, a User object could be created with
            // just a uri, then updated when fetched from the server, so account would be null until then.
            // Scenario: deeplinking. [KZ] 9/29/15
            return AccountType.BASIC;
        }
        switch (this.account) {
            case ACCOUNT_BUSINESS:
                return AccountType.BUSINESS;
            case ACCOUNT_PLUS:
                return AccountType.PLUS;
            case ACCOUNT_PRO:
                return AccountType.PRO;
            case ACCOUNT_STAFF:
                return AccountType.STAFF;
            case ACCOUNT_BASIC:
            default:
                return AccountType.BASIC;
        }
    }

    public UserBadgeType getUserBadgeType() {
        return badge == null ? UserBadgeType.NONE : badge.getBadgeType();
    }

    /**
     * -----------------------------------------------------------------------------------------------------
     * Interaction Accessors/Helpers
     * -----------------------------------------------------------------------------------------------------
     */
    // <editor-fold desc="Accessors/Helpers">
    @Override
    public boolean canFollow() {
        return getFollowInteraction() != null;
    }

    @Override
    public boolean isFollowing() {
        return getFollowInteraction() != null && metadata.interactions.follow.added;
    }

    @Nullable
    @Override
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

    @Nullable
    public Connection getLikesConnection() {
        if (metadata != null && metadata.connections != null && metadata.connections.likes != null) {
            return metadata.connections.likes;
        }
        return null;
    }

    public int getLikesCount() {
        if (getLikesConnection() != null) {
            return getLikesConnection().total;
        }
        return 0;
    }

    @Nullable
    public Connection getFollowedChannelsConnection() {
        if (metadata != null && metadata.connections != null && metadata.connections.channels != null) {
            return metadata.connections.channels;
        }
        return null;
    }

    public int getChannelsCount() {
        if (getFollowedChannelsConnection() != null) {
            return getFollowedChannelsConnection().total;
        }
        return 0;
    }

    @Nullable
    public Connection getModeratedChannelsConnection() {
        if (metadata != null && metadata.connections != null &&
            metadata.connections.moderatedChannels != null) {
            return metadata.connections.moderatedChannels;
        }
        return null;
    }

    public int getModeratedChannelsConnectionCount() {
        if (getFollowedChannelsConnection() != null) {
            return getModeratedChannelsConnection().total;
        }
        return 0;
    }

    @Nullable
    public Connection getAppearancesConnection() {
        if (metadata != null && metadata.connections != null && metadata.connections.appearances != null) {
            return metadata.connections.appearances;
        }
        return null;
    }

    public int getAppearancesCount() {
        if (getAppearancesConnection() != null) {
            return getAppearancesConnection().total;
        }
        return 0;
    }

    @Nullable
    public Connection getWatchLaterConnection() {
        if (metadata != null && metadata.connections != null && metadata.connections.watchlater != null) {
            return metadata.connections.watchlater;
        }
        return null;
    }
    // </editor-fold>

    public ArrayList<Picture> getPictures() {
        if (pictures == null || pictures.sizes == null) {
            return new ArrayList<>();
        }
        return pictures.sizes;
    }

    @Nullable
    public Connection getVideosConnection() {
        if ((metadata != null) && (metadata.connections != null) && (metadata.connections.videos != null)) {
            return metadata.connections.videos;
        }
        return null;
    }

    public int getVideoCount() {
        if (getVideosConnection() != null) {
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

    @Nullable
    public PrivacyValue getPreferredVideoPrivacyValue() {
        PrivacyValue privacyValue = null;
        if (getPreferences() != null && getPreferences().getVideos() != null &&
            getPreferences().getVideos().getPrivacy() != null) {
            privacyValue = PrivacyValue.privacyValueFromString(getPreferences().getVideos().getPrivacy());
        }
        return privacyValue;
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
        return Vimeo.NOT_FOUND;
    }

    /**
     * -----------------------------------------------------------------------------------------------------
     * Getters
     * -----------------------------------------------------------------------------------------------------
     */
    // <editor-fold desc="Getters">
    public String getUri() {
        return uri;
    }

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }

    public String getLocation() {
        return location;
    }

    public String getBio() {
        return bio;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public String getAccount() {
        return account;
    }

    public ArrayList<Email> getVerifiedEmails() {
        return emails;
    }

    public ArrayList<Website> getWebsites() {
        return websites;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    @Nullable
    public Preferences getPreferences() {
        return preferences;
    }

    // </editor-fold>

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
