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
import com.vimeo.stag.GsonAdapterKey;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.Date;

/**
 * Representation of a Video stream/playback file
 * <p/>
 * Created by alfredhanssen on 4/25/15.
 */
public class VideoFile implements Serializable {

    public enum MimeType {
        NONE("None"),
        @SerializedName("video/mp4")
        MP4("video/mp4"),
        @SerializedName("video/webm")
        WEBM("video/webm"), // Flash
        @SerializedName("vp6/x-video")
        VP6("vp6/x-video"); // Flash

        private final String mTypeName;

        MimeType(String typeName) {
            mTypeName = typeName;
        }

        @Override
        // Overridden for analytics.
        public String toString() {
            return mTypeName;
        }
    }

    @Deprecated
    public enum VideoQuality {
        NONE("N/A"),
        @SerializedName("hls")
        HLS("hls"),
        @SerializedName("hd")
        HD("hd"),
        @SerializedName("sd")
        SD("sd"),
        @SerializedName("mobile")
        MOBILE("mobile");

        private final String mTypeName;

        VideoQuality(String typeName) {
            mTypeName = typeName;
        }

        @Override
        // Overridden for analytics.
        public String toString() {
            return mTypeName;
        }
    }

    private static final long serialVersionUID = -5256416394912086020L;

    // -----------------------------------------------------------------------------------------------------
    // Fields common between all file types - HLS, Dash, Progressive
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Fields common between all file types">
    @Nullable
    @GsonAdapterKey("link_expiration_time")
    Date mLinkExpirationTime;

    @GsonAdapterKey("link")
    String mLink;

    @Nullable
    @GsonAdapterKey("log")
    String mLog;

    @Nullable
    @GsonAdapterKey("token")
    String mToken;

    @Nullable
    @GsonAdapterKey("license_link")
    String mLicenseLink;

    @Nullable
    public Date getLinkExpirationTime() {
        return mLinkExpirationTime;
    }

    /** @return true if this VideoFile doesn't have an expired field or if the expires date is before the current date */
    public boolean isExpired() {
        // If expires is null, we should probably refresh the video object regardless [KV] 3/31/16
        // TODO: Simplify this when expires is deprecated 4/25/16 [KZ]
        return (mExpires == null && mLinkExpirationTime == null) ||
               (mExpires != null && mExpires.before(new Date())) ||
               (mLinkExpirationTime != null && mLinkExpirationTime.before(new Date()));
    }

    public String getLink() {
        return mLink;
    }

    @Nullable
    public String getLog() {
        return mLog;
    }

    @Nullable
    public String getToken() {
        return mToken;
    }

    @Nullable
    public String getLicenseLink() {
        return mLicenseLink;
    }

    // </editor-fold>

    // -----------------------------------------------------------------------------------------------------
    // Progressive files only - these fields are not relevant to HLS/Dash
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Progressive files only">
    /** quality will be removed in the future when {@link Video#files} is removed */
    @Deprecated
    @Nullable
    @GsonAdapterKey("quality")
    VideoQuality mQuality;

    /** expires will be removed in the future when {@link Video#files} is removed */
    @Deprecated
    @Nullable
    @GsonAdapterKey("expires")
    Date mExpires;

    @Nullable
    @GsonAdapterKey("type")
    MimeType mMimeType;

    @GsonAdapterKey("fps")
    double mFps;

    @GsonAdapterKey("width")
    int mWidth;

    @GsonAdapterKey("height")
    int mHeight;

    @GsonAdapterKey("size")
    long mSize; // size of the file, in bytes

    /** The md5 provides us with a way to uniquely identify video files at {@link #getLink()} */
    @Nullable
    @GsonAdapterKey("md5")
    String mMd5;

    @Nullable
    @GsonAdapterKey("created_time")
    Date mCreatedTime; // time indicating when this transcode was completed

    /**
     * quality is no longer included in VideoFiles under {@link Video#getPlay()} - it will be removed
     * in a future release once {@link Video#files} is removed.
     *
     * @return the VideoQuality
     */
    @Deprecated
    public VideoQuality getQuality() {
        return mQuality == null ? VideoQuality.NONE : mQuality;
    }

    public MimeType getType() {
        return mMimeType == null ? MimeType.NONE : mMimeType;
    }

    public boolean isVP6() {
        return getType() == MimeType.VP6;
    }

    public int getWidth() {
        return mWidth;
    }

    public int getHeight() {
        return mHeight;
    }

    public long getSize() {
        return mSize;
    }

    public double getFps() {
        return mFps;
    }

    @Nullable
    public String getMd5() {
        return mMd5;
    }

    @Nullable
    public Date getCreatedTime() {
        return mCreatedTime;
    }
    // </editor-fold>

    // -----------------------------------------------------------------------------------------------------
    // Equals/Hashcode
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Equals/Hashcode">
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !(o instanceof VideoFile)) {
            return false;
        }

        VideoFile that = (VideoFile) o;

        return (mLink != null && that.getLink() != null) && mLink.equals(that.getLink());
    }

    @Override
    public int hashCode() {
        return mLink != null ? mLink.hashCode() : 0;
    }
    // </editor-fold>
}
