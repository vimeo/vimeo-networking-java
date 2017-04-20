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

package com.vimeo.networking.model;

import com.google.gson.annotations.SerializedName;
import com.vimeo.stag.UseStag;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

/**
 * A model representing a credit.
 * Created by zetterstromk on 1/11/17.
 */
@SuppressWarnings("unused")
@UseStag
public class Credit implements Serializable {

    private static final long serialVersionUID = 6037404487282167384L;

    @Nullable
    @SerializedName("uri")
    protected String mUri;

    @Nullable
    @SerializedName("role")
    protected String mRole;

    @Nullable
    @SerializedName("name")
    protected String mName;

    @Nullable
    @SerializedName("video")
    protected Video mVideo;

    @Nullable
    @SerializedName("user")
    protected User mUser;

    @Nullable
    public String getUri() {
        return mUri;
    }

    @Nullable
    public String getRole() {
        return mRole;
    }

    @Nullable
    public String getName() {
        return mName;
    }

    @Nullable
    public Video getVideo() {
        return mVideo;
    }

    public void setVideo(@Nullable Video video) {
        this.mVideo = video;
    }

    @Nullable
    public User getUser() {
        return mUser;
    }

    public void setUser(@Nullable User user) {
        this.mUser = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Credit credit = (Credit) o;

        if (mUri != null ? !mUri.equals(credit.mUri) : credit.mUri != null) {
            return false;
        }
        if (mRole != null ? !mRole.equals(credit.mRole) : credit.mRole != null) {
            return false;
        }
        if (mName != null ? !mName.equals(credit.mName) : credit.mName != null) {
            return false;
        }
        if (mVideo != null ? !mVideo.equals(credit.mVideo) : credit.mVideo != null) {
            return false;
        }
        return mUser != null ? mUser.equals(credit.mUser) : credit.mUser == null;

    }

    @Override
    public int hashCode() {
        int result = mUri != null ? mUri.hashCode() : 0;
        result = 31 * result + (mRole != null ? mRole.hashCode() : 0);
        result = 31 * result + (mName != null ? mName.hashCode() : 0);
        result = 31 * result + (mVideo != null ? mVideo.hashCode() : 0);
        result = 31 * result + (mUser != null ? mUser.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Credit{" +
               "uri='" + mUri + '\'' +
               ", role='" + mRole + '\'' +
               ", name='" + mName + '\'' +
               ", video=" + mVideo +
               ", user=" + mUser +
               '}';
    }
}
