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
import com.vimeo.networking.model.Privacy.PrivacyValue;
import com.vimeo.networking.model.UserBadge.UserBadgeType;
import com.vimeo.networking.model.notifications.NotificationConnection;
import com.vimeo.stag.UseStag;
import com.vimeo.stag.UseStag.FieldOption;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * This object contains the data for a Vimeo user
 * <p>
 * Created by alfredhanssen on 4/12/15.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
@UseStag(FieldOption.SERIALIZED_NAME)
public class User implements Serializable, Followable {

    private static final long serialVersionUID = 4317573825273169510L;
    private static final String ACCOUNT_BASIC = "basic";
    private static final String ACCOUNT_BUSINESS = "business";
    private static final String ACCOUNT_PLUS = "plus";
    private static final String ACCOUNT_PRO = "pro";
    private static final String ACCOUNT_STAFF = "staff";
    private static final String ACCOUNT_LIVE_BUSINESS = "live_business";
    private static final String ACCOUNT_LIVE_PRO = "live_pro";
    private static final String ACCOUNT_PRO_UNLIMITED = "pro_unlimited";

    public enum AccountType {
        BASIC,
        BUSINESS,
        PRO,
        PLUS,
        STAFF,
        LIVE_BUSINESS,
        LIVE_PRO,
        PRO_UNLIMITED
    }

    @SerializedName("uri")
    public String mUri;

    @SerializedName("name")
    public String mName;

    @SerializedName("link")
    public String mLink;

    @SerializedName("location")
    public String mLocation;

    @SerializedName("bio")
    public String mBio;

    @SerializedName("created_time")
    public Date mCreatedTime;

    @SerializedName("account")
    public String mAccount;

    @SerializedName("pictures")
    public PictureCollection mPictures;

    @SerializedName("emails")
    public ArrayList<Email> mEmails;

    @SerializedName("websites")
    public ArrayList<Website> mWebsites;

    @SerializedName("metadata")
    public Metadata mMetadata;

    @SerializedName("upload_quota")
    public UploadQuota mUploadQuota;

    @Nullable
    @SerializedName("preferences")
    public Preferences mPreferences;

    @Nullable
    @SerializedName("badge")
    public UserBadge mBadge;

    /**
     * Live streaming quota information
     */
    @Nullable
    @SerializedName("live_quota")
    public LiveQuota mLiveQuota;

    @Nullable
    @SerializedName("id")
    private String mId;

    @SerializedName("is_staff")
    private boolean mIsStaff;

    @SerializedName("is_creator")
    private boolean mIsVideoCreator;

    @Nullable
    public UserBadge getBadge() {
        return mBadge;
    }

    public AccountType getAccountType() {
        if (this.mAccount == null) {
            //We should assume the account object could be null; also, a User object could be created with
            // just a uri, then updated when fetched from the server, so account would be null until then.
            // Scenario: deeplinking. [KZ] 9/29/15
            return AccountType.BASIC;
        }
        switch (this.mAccount) {
            case ACCOUNT_BUSINESS:
                return AccountType.BUSINESS;
            case ACCOUNT_PLUS:
                return AccountType.PLUS;
            case ACCOUNT_PRO:
                return AccountType.PRO;
            case ACCOUNT_STAFF:
                return AccountType.STAFF;
            case ACCOUNT_LIVE_BUSINESS:
                return AccountType.LIVE_BUSINESS;
            case ACCOUNT_LIVE_PRO:
                return AccountType.LIVE_PRO;
            case ACCOUNT_PRO_UNLIMITED:
                return AccountType.PRO_UNLIMITED;
            case ACCOUNT_BASIC:
            default:
                return AccountType.BASIC;
        }
    }

    public UserBadgeType getUserBadgeType() {
        return mBadge == null ? UserBadgeType.NONE : mBadge.getBadgeType();
    }

    /**
     * -----------------------------------------------------------------------------------------------------
     * Interaction Accessors/Helpers
     * -----------------------------------------------------------------------------------------------------
     */
    // <editor-fold desc="Accessors/Helpers">
    public void setUri(String uri) {
        mUri = uri;
    }

    public PictureCollection getPictures() {
        return mPictures;
    }

    @Override
    public boolean canFollow() {
        return getFollowInteraction() != null;
    }

    @Override
    public boolean isFollowing() {
        Interaction follow = getFollowInteraction();
        return follow != null && follow.mAdded;
    }

    @Nullable
    private ConnectionCollection getMetadataConnections() {
        if (mMetadata != null) {
            return mMetadata.getConnections();
        }
        return null;
    }

    @Nullable
    private InteractionCollection getMetadataInteractions() {
        if (mMetadata != null) {
            return mMetadata.getInteractions();
        }
        return null;
    }

    @Nullable
    @Override
    public Interaction getFollowInteraction() {
        InteractionCollection interactions = getMetadataInteractions();
        return interactions != null ? interactions.getFollow() : null;
    }

    @Nullable
    public Connection getFollowingConnection() {
        ConnectionCollection connections = getMetadataConnections();
        return connections != null ? connections.getFollowing() : null;
    }

    @Nullable
    public Connection getFeedConnection() {
        ConnectionCollection connections = getMetadataConnections();
        return connections != null ? connections.getFeed() : null;
    }

    @Nullable
    public Connection getFollowersConnection() {
        ConnectionCollection connections = getMetadataConnections();
        return connections != null ? connections.getFollowers() : null;
    }

    public int getFollowerCount() {
        Connection followers = getFollowersConnection();
        return followers != null ? followers.getTotal() : 0;
    }

    public int getFollowingCount() {
        Connection following = getFollowingConnection();
        return following != null ? following.getTotal() : 0;
    }

    @Nullable
    public Connection getLikesConnection() {
        ConnectionCollection connections = getMetadataConnections();
        return connections != null ? connections.getLikes() : null;
    }

    public int getLikesCount() {
        Connection likes = getLikesConnection();
        return likes != null ? likes.getTotal() : 0;
    }

    @Nullable
    public Connection getFollowedChannelsConnection() {
        ConnectionCollection connections = getMetadataConnections();
        return connections != null ? connections.getChannels() : null;
    }

    public int getChannelsCount() {
        Connection channels = getFollowedChannelsConnection();
        return channels != null ? channels.getTotal() : 0;
    }

    @Nullable
    public Connection getModeratedChannelsConnection() {
        ConnectionCollection connections = getMetadataConnections();
        return connections != null ? connections.getModeratedChannels() : null;
    }

    public int getModeratedChannelsConnectionCount() {
        Connection moderatedChannels = getModeratedChannelsConnection();
        return moderatedChannels != null ? moderatedChannels.getTotal() : 0;
    }

    @Nullable
    public Connection getAppearancesConnection() {
        ConnectionCollection connections = getMetadataConnections();
        return connections != null ? connections.getAppearances() : null;
    }

    public int getAppearancesCount() {
        Connection appearances = getAppearancesConnection();
        return appearances != null ? appearances.getTotal() : 0;
    }

    @Nullable
    public Connection getWatchLaterConnection() {
        ConnectionCollection connections = getMetadataConnections();
        return connections != null ? connections.getWatchlater() : null;
    }

    @Nullable
    public Connection getWatchedVideosConnection() {
        ConnectionCollection connections = getMetadataConnections();
        return connections != null ? connections.getWatchedVideos() : null;
    }

    @Nullable
    public NotificationConnection getNotificationConnection() {
        ConnectionCollection collections = getMetadataConnections();
        return collections != null ? collections.getNotifications() : null;
    }
    // </editor-fold>

    @NotNull
    public ArrayList<Picture> getPicturesList() {
        if (mPictures == null || mPictures.getPictures() == null) {
            return new ArrayList<>();
        }
        return mPictures.getPictures();
    }

    @Nullable
    public Connection getVideosConnection() {
        ConnectionCollection connections = getMetadataConnections();
        return connections != null ? connections.mVideos : null;
    }

    public int getVideoCount() {
        Connection videos = getVideosConnection();
        return videos != null ? videos.mTotal : 0;
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
        if (mPreferences != null && mPreferences.getVideos() != null &&
            mPreferences.getVideos().getPrivacy() != null) {
            privacyValue = mPreferences.getVideos().getPrivacy().getView();
        }
        return privacyValue;
    }

    public boolean canUploadPicture() {
        return mMetadata != null &&
               mMetadata.mConnections != null &&
               mMetadata.mConnections.mPictures != null &&
               mMetadata.mConnections.mPictures.mOptions != null &&
               mMetadata.mConnections.mPictures.mOptions.contains(Vimeo.OPTIONS_POST);
    }

    public UploadQuota getUploadQuota() {
        return mUploadQuota;
    }

    // Returns -1 if there is no space object on this user
    public long getFreeUploadSpace() {
        if (mUploadQuota != null) {
            return mUploadQuota.getFreeUploadSpace();
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
        return mUri;
    }

    public String getName() {
        return mName;
    }

    public String getLink() {
        return mLink;
    }

    public String getLocation() {
        return mLocation;
    }

    public String getBio() {
        return mBio;
    }

    public Date getCreatedTime() {
        return mCreatedTime;
    }

    public String getAccount() {
        return mAccount;
    }

    public ArrayList<Email> getVerifiedEmails() {
        return mEmails;
    }

    public ArrayList<Website> getWebsites() {
        return mWebsites;
    }

    public Metadata getMetadata() {
        return mMetadata;
    }

    @Nullable
    public Preferences getPreferences() {
        return mPreferences;
    }

    @Nullable
    public LiveQuota getLiveQuota() {
        return mLiveQuota;
    }

    /**
     * @return a unique identifier for the user within Vimeo
     */
    @Nullable
    public String getId() {
        return mId;
    }

    /**
     * @return whether the user is an active staff member of Vimeo
     */
    public boolean getIsStaff() {
        return mIsStaff;
    }

    /**
     * @return whether the user is the creator of the containing {@link Video} object. This value is only
     * set for a user object that is contained within a {@link Video} object.
     */
    public boolean getIsVideoCreator() {
        return mIsVideoCreator;
    }

    // </editor-fold>

    // -----------------------------------------------------------------------------------------------------
    // Setters
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Setters">

    public void setPictures(PictureCollection pictures) {
        mPictures = pictures;
    }

    public void setName(String name) {
        mName = name;
    }

    public void setLocation(String location) {
        mLocation = location;
    }

    public void setBio(String bio) {
        mBio = bio;
    }

    public void setLiveQuota(@Nullable LiveQuota liveQuota) {
        mLiveQuota = liveQuota;
    }

    void setId(@Nullable String id) {
        mId = id;
    }

    void setVideoCreator(boolean videoCreator) {
        mIsVideoCreator = videoCreator;
    }

    void setStaff(boolean staff) {
        mIsStaff = staff;
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

        return ((this.mUri != null && that.mUri != null) && this.mUri.equals(that.mUri));
    }

    @Override
    public int hashCode() {
        return this.mUri != null ? this.mUri.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "User{" +
               "mUri='" + mUri + '\'' +
               ", mName='" + mName + '\'' +
               ", mLink='" + mLink + '\'' +
               ", mLocation='" + mLocation + '\'' +
               ", mBio='" + mBio + '\'' +
               ", mCreatedTime=" + mCreatedTime +
               ", mAccount='" + mAccount + '\'' +
               ", mPictures=" + mPictures +
               ", mEmails=" + mEmails +
               ", mWebsites=" + mWebsites +
               ", mMetadata=" + mMetadata +
               ", mUploadQuota=" + mUploadQuota +
               ", mPreferences=" + mPreferences +
               ", mBadge=" + mBadge +
               ", mLiveQuota=" + mLiveQuota +
               '}';
    }
}
