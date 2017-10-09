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
import com.vimeo.stag.UseStag.FieldOption;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.Date;

/**
 * Base representation of a Video stream/playback file
 * <p>
 * Created by alfredhanssen on 4/25/15.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
@UseStag(FieldOption.SERIALIZED_NAME)
public abstract class VideoFile implements Serializable {

    public enum VideoQuality {
        NONE("N/A"),
        DASH("dash"),
        HLS("hls"),
        HD("hd"),
        SD("sd");

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

    @SuppressWarnings("WeakerAccess")
    public static class LiveFormat implements Serializable {

        private static final long serialVersionUID = -4662813629024544219L;

        @Nullable
        @SerializedName("heartbeat")
        private String mHeartbeat;

        /**
         * @return the uri for sending up heartbeat logs when this is a live video
         */
        @Nullable
        public String getHeartbeat() {
            return mHeartbeat;
        }

        void setHeartbeat(@Nullable String heartbeat) {
            mHeartbeat = heartbeat;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) { return true; }
            if (o == null || getClass() != o.getClass()) { return false; }

            final LiveFormat liveFormat = (LiveFormat) o;

            return mHeartbeat != null ? mHeartbeat.equals(liveFormat.mHeartbeat) : liveFormat.mHeartbeat == null;

        }

        @Override
        public int hashCode() {
            return mHeartbeat != null ? mHeartbeat.hashCode() : 0;
        }

        @Override
        public String toString() {
            return "LiveFormat{" +
                   "mHeartbeat='" + mHeartbeat + '\'' +
                   '}';
        }
    }

    private static final long serialVersionUID = -5256416394912086020L;

    @NotNull
    public abstract VideoQuality getQuality();

    // -----------------------------------------------------------------------------------------------------
    // Fields common between all file types - HLS, Dash, Progressive
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Fields common between all file types">
    @Nullable
    @SerializedName("link_expiration_time")
    protected Date mLinkExpirationTime;

    @SerializedName("link")
    protected String mLink;

    @Nullable
    @SerializedName("log")
    protected String mLog;

    @Nullable
    @SerializedName("live")
    private LiveFormat mLive;

    /**
     * @return The {@link LiveFormat} object associated with this video format
     */
    @Nullable
    LiveFormat getLive() {
        return mLive;
    }

    void setLive(@Nullable LiveFormat live) {
        mLive = live;
    }

    /**
     * @see LiveFormat#getHeartbeat()
     */
    @Nullable
    public String getLiveHeartbeatUri() {
        return mLive != null ? mLive.getHeartbeat() : null;
    }

    @Nullable
    public Date getLinkExpirationTime() {
        return mLinkExpirationTime;
    }

    /**
     * @return true if this VideoFile doesn't have an expired field or if the expires date is before the current date
     */
    public boolean isExpired() {
        // If expires is null, we should probably refresh the video object regardless [KV] 3/31/16
        return mLinkExpirationTime == null || mLinkExpirationTime.before(new Date());
    }

    public String getLink() {
        return mLink;
    }

    @Nullable
    public String getLog() {
        return mLog;
    }

    // </editor-fold>

    // -----------------------------------------------------------------------------------------------------
    // Equals/Hashcode
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Equals/Hashcode">
    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }

        final VideoFile videoFile = (VideoFile) o;

        return mLink != null ? mLink.equals(videoFile.mLink) : videoFile.mLink == null;

    }

    @Override
    public int hashCode() {
        return mLink != null ? mLink.hashCode() : 0;
    }
    // </editor-fold>
}
