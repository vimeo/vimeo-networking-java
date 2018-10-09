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
import com.vimeo.stag.UseStag;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zetterstromk on 7/31/15.
 */
@SuppressWarnings("unused")
@UseStag
public class Comment implements Serializable, Entity {

    private static final long serialVersionUID = -7716027694845877155L;

    @UseStag
    public enum CommentType {
        @SerializedName("video")
        VIDEO
        //TODO get the other comment types and put them here [KZ]
    }

    @SerializedName("uri")
    protected String mUri;

    @SerializedName("type")
    protected CommentType mType;

    @SerializedName("created_on")
    protected Date mCreatedOn;

    @SerializedName("text")
    protected String mText;

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

    public CommentType getType() {
        return mType;
    }

    public Date getCreatedOn() {
        return mCreatedOn;
    }

    public String getText() {
        return mText;
    }

    public User getUser() {
        return mUser;
    }

    public Metadata getMetadata() {
        return mMetadata;
    }

    public void setUser(User user) {
        mUser = user;
    }

    public int replyCount() {
        if (mMetadata != null && mMetadata.mConnections != null && mMetadata.mConnections.mReplies != null) {
            return mMetadata.mConnections.mReplies.mTotal;
        }
        return 0;
    }

    public boolean canReply() {
        return mMetadata != null &&
               mMetadata.mConnections != null &&
               mMetadata.mConnections.mReplies != null &&
               mMetadata.mConnections.mReplies.mOptions != null &&
               mMetadata.mConnections.mReplies.mOptions.contains(Vimeo.OPTIONS_POST);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Comment that = (Comment) o;

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
