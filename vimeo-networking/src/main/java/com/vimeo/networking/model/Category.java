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
import java.util.ArrayList;

/**
 * Created by zetterstromk on 8/20/15.
 */
@SuppressWarnings("unused")
@UseStag
public class Category implements Serializable, Followable, Entity {

    private static final long serialVersionUID = 441419347585215353L;

    @Nullable
    @SerializedName("uri")
    protected String mUri;

    @Nullable
    @SerializedName("name")
    protected String mName;

    @Nullable
    @SerializedName("link")
    protected String mLink;

    @SerializedName("top_level")
    protected boolean mTopLevel;

    @Nullable
    @SerializedName("pictures")
    protected PictureCollection mPictures;

    @Nullable
    @SerializedName("icon")
    protected PictureCollection mIcon;

    @Nullable
    @SerializedName("subcategories")
    protected ArrayList<Category> mSubcategories;

    @Nullable
    @SerializedName("parent")
    protected Category mParent;

    @Nullable
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

    @Nullable
    public String getUri() {
        return mUri;
    }

    @Nullable
    public String getName() {
        return mName;
    }

    @Nullable
    public String getLink() {
        return mLink;
    }

    public boolean isTopLevel() {
        return mTopLevel;
    }

    @Nullable
    public PictureCollection getPictures() {
        return mPictures;
    }

    @Nullable
    public ArrayList<Category> getSubcategories() {
        return mSubcategories;
    }

    @Nullable
    public Category getParent() {
        return mParent;
    }

    @Nullable
    public Metadata getMetadata() {
        return mMetadata;
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

    @Nullable
    public Connection getUserConnection() {
        if (mMetadata != null && mMetadata.mConnections != null && mMetadata.mConnections.mUsers != null) {
            return mMetadata.mConnections.mUsers;
        }
        return null;
    }

    public int getFollowerCount() {
        if (getUserConnection() != null) {
            return getUserConnection().mTotal;
        }
        return 0;
    }

    /**
     * Returns the icon associated with this category, in the form of a {@link PictureCollection}
     */
    @Nullable
    public PictureCollection getIcon() {
        return mIcon;
    }

    public void setUri(@Nullable String uri) {
        mUri = uri;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Category that = (Category) o;

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
