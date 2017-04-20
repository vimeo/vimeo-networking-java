/*
 * Copyright (c) 2017 Vimeo (https://vimeo.com)
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

package com.vimeo.networking.model.notifications;

import com.google.gson.annotations.SerializedName;
import com.vimeo.networking.model.Comment;
import com.vimeo.networking.model.Credit;
import com.vimeo.networking.model.User;
import com.vimeo.networking.model.Video;
import com.vimeo.stag.UseStag;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.Date;

/**
 * A model representing activity that a user may be notified about.
 * <p>
 * Created by zetterstromk on 1/11/17.
 */
@SuppressWarnings("unused")
@UseStag
public class Notification implements Serializable {

    private static final long serialVersionUID = -68262442832775695L;

    @Nullable
    @SerializedName("uri")
    protected String mUri;

    @Nullable
    @SerializedName("created_time")
    protected Date mCreatedDate;

    @NotNull
    @SerializedName("type")
    protected String mType;

    @Nullable
    @SerializedName("user")
    protected User mUser;

    @Nullable
    @SerializedName("comment")
    protected Comment mComment;

    @Nullable
    @SerializedName("clip")
    protected Video mVideo;

    @Nullable
    @SerializedName("credit")
    protected Credit mCredit;

    @SerializedName("new")
    protected boolean mIsNew;

    @SerializedName("seen")
    protected boolean mIsSeen;

    @Nullable
    public String getUri() {
        return mUri;
    }

    @Nullable
    public Date getCreatedDate() {
        return mCreatedDate;
    }

    @NotNull
    public NotificationType getNotificationType() {
        return NotificationType.notificationTypeFromString(mType);
    }

    @Nullable
    public User getUser() {
        return mUser;
    }

    public void setUser(@Nullable User user) {
        mUser = user;
    }

    @Nullable
    public Comment getComment() {
        return mComment;
    }

    public void setComment(@Nullable Comment comment) {
        mComment = comment;
    }

    @Nullable
    public Video getVideo() {
        return mVideo;
    }

    public void setVideo(@Nullable Video video) {
        mVideo = video;
    }

    @Nullable
    public Credit getCredit() {
        return mCredit;
    }

    public void setCredit(@Nullable Credit credit) {
        mCredit = credit;
    }

    public boolean isNew() {
        return mIsNew;
    }

    public boolean isSeen() {
        return mIsSeen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Notification that = (Notification) o;

        if (mUri != null ? !mUri.equals(that.mUri) : that.mUri != null) {
            return false;
        }
        if (mCreatedDate != null ? !mCreatedDate.equals(that.mCreatedDate) : that.mCreatedDate != null) {
            return false;
        }
        if (!mType.equals(that.mType)) {
            return false;
        }
        if (mUser != null ? !mUser.equals(that.mUser) : that.mUser != null) {
            return false;
        }
        if (mComment != null ? !mComment.equals(that.mComment) : that.mComment != null) {
            return false;
        }
        if (mVideo != null ? !mVideo.equals(that.mVideo) : that.mVideo != null) {
            return false;
        }
        return mCredit != null ? mCredit.equals(that.mCredit) : that.mCredit == null;

    }

    @Override
    public int hashCode() {
        int result = mUri != null ? mUri.hashCode() : 0;
        result = 31 * result + (mCreatedDate != null ? mCreatedDate.hashCode() : 0);
        result = 31 * result + mType.hashCode();
        result = 31 * result + (mUser != null ? mUser.hashCode() : 0);
        result = 31 * result + (mComment != null ? mComment.hashCode() : 0);
        result = 31 * result + (mVideo != null ? mVideo.hashCode() : 0);
        result = 31 * result + (mCredit != null ? mCredit.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Notification{" +
               "uri='" + mUri + '\'' +
               ", createdDate=" + mCreatedDate +
               ", type='" + mType + '\'' +
               ", user=" + mUser +
               ", comment=" + mComment +
               ", video=" + mVideo +
               ", credit=" + mCredit +
               '}';
    }
}
