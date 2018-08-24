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
 * A model class representing an Album.
 * Created by Maarten de Goede on 8/24/18.
 */
@SuppressWarnings("WeakerAccess")
@UseStag
public class Album implements Serializable, Entity {

    private static final long serialVersionUID = -2289103918709562107L;

    @SerializedName("uri")
    public String mUri;

    @SerializedName("name")
    public String mName;

    @SerializedName("description")
    public String mDescription;

    @SerializedName("link")
    public String mLink;

    @SerializedName("duration")
    public int mDuration;

    @SerializedName("created_time")
    public Date mCreatedTime;

    @SerializedName("modified_time")
    public Date mModifiedTime;

    @SerializedName("user")
    public User mUser;

    @SerializedName("privacy")
    public Privacy mPrivacy;

    @SerializedName("pictures")
    public PictureCollection mPictures;

    @SerializedName("sort")
    public String mSort;

    @SerializedName("layout")
    public String mLayout;

    @SerializedName("theme")
    public String mTheme;

    @Nullable
    @SerializedName("brand_color")
    public String mBrandColor;

    @Nullable
    @SerializedName("custom_logo")
    public String mCustomLogo;

    @SerializedName("review_mode")
    public Boolean mReviewMode;

    @SerializedName("hide_nav")
    public Boolean mHideNav;

    @SerializedName("metadata")
    public Metadata mMetadata;

    @Nullable
    @SerializedName("resource_key")
    private String mResourceKey;

    // -----------------------------------------------------------------------------------------------------
    // Getters
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Getters">
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

    public int getDuration() {
        return mDuration;
    }

    public Date getCreatedTime() {
        return mCreatedTime;
    }

    public Date getModifiedTime() {
        return mModifiedTime;
    }

    public Privacy getPrivacy() {
        return mPrivacy;
    }

    public PictureCollection getPictures() {
        return mPictures;
    }

    public String getSort() {
        return mSort;
    }

    public String getLayout() {
        return mLayout;
    }

    public String getTheme() {
        return mTheme;
    }

    public String getBrandColor() {
        return mBrandColor;
    }

    public String getCustomLogo() {
        return mCustomLogo;
    }

    public Boolean getReviewMode() {
        return mReviewMode;
    }

    public Boolean getHideNav() {
        return mHideNav;
    }

    public Metadata getMetadata() {
        return mMetadata;
    }

    public User getUser() {
        return mUser;
    }

    @Nullable
    @Override
    public String getIdentifier() {
        return mResourceKey;
    }

    // </editor-fold>

    // -----------------------------------------------------------------------------------------------------
    // Setters
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Setters">

    public void setDescription(String description) {
        mDescription = description;
    }

    public void setName(String name) {
        mName = name;
    }

    public void setUri(String uri) {
        mUri = uri;
    }

    public void setUser(User user) {
        mUser = user;
    }

    // </editor-fold>
}
