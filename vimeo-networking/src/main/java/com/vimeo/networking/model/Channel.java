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
import com.vimeo.stag.UseStag;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zetterstromk on 6/11/15.
 */
@SuppressWarnings("unused")
@UseStag
public class Channel implements Serializable, Followable, Entity {

    private static final long serialVersionUID = 3190410523525111858L;

    @SerializedName("uri")
    protected String mUri;

    @SerializedName("name")
    protected String mName;

    @SerializedName("description")
    protected String mDescription;

    @SerializedName("link")
    protected String mLink;

    @SerializedName("created_time")
    protected Date mCreatedTime;

    @SerializedName("modified_time")
    protected Date mModifiedTime;

    @SerializedName("user")
    protected User mUser;

    @SerializedName("pictures")
    protected PictureCollection mPictures;

    @SerializedName("header")
    protected PictureCollection mHeader;

    @SerializedName("privacy")
    protected Privacy mPrivacy;

    @SerializedName("metadata")
    protected Metadata mMetadata;

    @Nullable
    @SerializedName("resource_key")
    private String mResourceKey;

    @Nullable
    public String getResourceKey() {
        return mResourceKey;
    }

    protected void setResourceKey(@Nullable String resourceKey) {
        mResourceKey = resourceKey;
    }

    public void setUri(String uri) {
        mUri = uri;
    }

    public void setUser(User user) {
        mUser = user;
    }

    public String getUri() {
        return mUri;
    }

    public String getName() {
        return mName;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getLink() {
        return mLink;
    }

    public Date getCreatedTime() {
        return mCreatedTime;
    }

    public Date getModifiedTime() {
        return mModifiedTime;
    }

    public User getUser() {
        return mUser;
    }

    public PictureCollection getPictures() {
        return mPictures;
    }

    public PictureCollection getHeader() {
        return mHeader;
    }

    public Privacy getPrivacy() {
        return mPrivacy;
    }

    public Metadata getMetadata() {
        return mMetadata;
    }

    @Nullable
    public Connection getUsersConnection() {
        if (mMetadata != null && mMetadata.mConnections != null && mMetadata.mConnections.mUsers != null) {
            return mMetadata.mConnections.mUsers;
        }
        return null;
    }

    @Nullable
    @Override
    public Interaction getFollowInteraction() {
        if (mMetadata != null && mMetadata.mInteractions != null && mMetadata.mInteractions.mFollow != null) {
            return mMetadata.mInteractions.mFollow;
        }
        return null;
    }

    @Override
    public boolean canFollow() {
        return getFollowInteraction() != null;
    }

    @Override
    public boolean isFollowing() {
        Interaction interaction = getFollowInteraction();
        return interaction != null && interaction.mAdded;
    }

    public int getFollowerCount() {
        if (getUsersConnection() != null) {
            return getUsersConnection().mTotal;
        }
        return 0;
    }

    @Nullable
    public Connection getVideosConnection() {
        if (mMetadata != null && mMetadata.mConnections != null && mMetadata.mConnections.mVideos != null) {
            return mMetadata.mConnections.mVideos;
        }
        return null;
    }

    public int getVideoCount() {
        if (getVideosConnection() != null) {
            return getVideosConnection().mTotal;
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Channel that = (Channel) o;

        return ((this.mUri != null && that.mUri != null) && this.mUri.equals(that.mUri));
    }

    @Override
    public int hashCode() {
        return this.mUri != null ? this.mUri.hashCode() : 0;
    }

    @Nullable
    @Override
    public String getIdentifier() {
        return mResourceKey;
    }
}
