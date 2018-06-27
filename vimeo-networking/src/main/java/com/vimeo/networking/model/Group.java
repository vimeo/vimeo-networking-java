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
 * Created by zetterstromk on 6/26/15.
 */
@SuppressWarnings("unused")
@UseStag
public class Group implements Serializable, Entity {

    private static final long serialVersionUID = -3604741570351063891L;

    @SerializedName("uri")
    protected String mUri;

    @SerializedName("created_time")
    protected Date mCreatedTime;

    @SerializedName("group_description")
    protected String mGroupDescription;

    @SerializedName("link")
    protected String mLink;

    @SerializedName("name")
    protected String mName;

    @SerializedName("picture_collection")
    protected PictureCollection mPictureCollection;

    @SerializedName("privacy")
    protected Privacy mPrivacy;

    @SerializedName("user")
    protected User mUser;

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

    public String getUri() {
        return mUri;
    }

    public Date getCreatedTime() {
        return mCreatedTime;
    }

    public String getGroupDescription() {
        return mGroupDescription;
    }

    public String getLink() {
        return mLink;
    }

    public String getName() {
        return mName;
    }

    public PictureCollection getPictureCollection() {
        return mPictureCollection;
    }

    public Privacy getPrivacy() {
        return mPrivacy;
    }

    public User getUser() {
        return mUser;
    }

    public Metadata getMetadata() {
        return mMetadata;
    }

    @Nullable
    @Override
    public String getIdentifier() {
        return mResourceKey;
    }
}
