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
 * Created by zetterstromk on 6/24/15.
 */
@SuppressWarnings("unused")
@UseStag
public class FeedItem implements Serializable {

    private static final long serialVersionUID = -8744477085158366576L;

    public enum AttributionType {
        UPLOAD,
        LIKE,
        CATEGORY,
        CHANNEL,
        GROUP,
        TAG,
        CREDIT,
        SHARE,
        NONE
    }

    @SerializedName("uri")
    protected String mUri;

    @SerializedName("clip")
    protected Video mClip;

    @SerializedName("type")
    protected String mType;

    @SerializedName("time")
    protected Date mTime;

    @SerializedName("user")
    protected User mUser;   // from like type

    @SerializedName("channel")
    protected Channel mChannel; // from channel type

    @SerializedName("category")
    protected Category mCategory;

    @SerializedName("tag")
    protected Tag mTag;

    @SerializedName("group")
    protected Group mGroup;

    @SerializedName("metadata")
    protected Metadata mMetadata;

    public void setClip(@Nullable Video video) {
        mClip = video;
    }

    public void setUser(User user) {
        mUser = user;
    }

    public String getUri() {
        return mUri;
    }

    public Video getClip() {
        return mClip;
    }

    public Date getTime() {
        return mTime;
    }

    public User getUser() {
        return mUser;
    }

    public Channel getChannel() {
        return mChannel;
    }

    public Category getCategory() {
        return mCategory;
    }

    public Tag getTag() {
        return mTag;
    }

    public Group getGroup() {
        return mGroup;
    }

    public Metadata getMetadata() {
        return mMetadata;
    }

    public AttributionType getType() {
        if (mType != null) {
            if (mType.equalsIgnoreCase("category")) {
                return AttributionType.CATEGORY;
            } else if (mType.equalsIgnoreCase("channel")) {
                return AttributionType.CHANNEL;
            } else if (mType.equalsIgnoreCase("like")) {
                return AttributionType.LIKE;
            } else if (mType.equalsIgnoreCase("upload")) {
                return AttributionType.UPLOAD;
            } else if (mType.equalsIgnoreCase("tag")) {
                return AttributionType.TAG;
            } else if (mType.equalsIgnoreCase("group")) {
                return AttributionType.GROUP;
            } else if (mType.equalsIgnoreCase("appearance")) {
                return AttributionType.CREDIT;
            } else if (mType.equalsIgnoreCase("share")) {
                return AttributionType.SHARE;
            }
        }

        return AttributionType.NONE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FeedItem that = (FeedItem) o;

        return ((this.mClip != null && that.mClip != null) &&
                (this.mClip.getResourceKey() != null && that.mClip.getResourceKey() != null) &&
                this.mClip.getResourceKey().equals(that.mClip.getResourceKey()));
    }

    @Override
    public int hashCode() {
        return this.mClip.getResourceKey() != null ? this.mClip.getResourceKey().hashCode() : 0;
    }

}

