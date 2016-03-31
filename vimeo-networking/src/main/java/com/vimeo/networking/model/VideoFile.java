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

import java.io.Serializable;
import java.util.Date;

/**
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

        private String string;

        MimeType(String string) {
            this.string = string;
        }

        @Override
        // Overridden for analytics.
        public String toString() {
            return this.string;
        }
    }

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

        private String string;

        VideoQuality(String string) {
            this.string = string;
        }

        @Override
        // Overridden for analytics.
        public String toString() {
            return this.string;
        }
    }

    private static final long serialVersionUID = -5256416394912086020L;

    public Date expires;
    public int width;
    public int height;
    public long size;
    public String link;
    private VideoQuality quality;
    private MimeType type;
    public VideoLog log;
    /** The md5 provides us with a way to uniquely identify video files at {@link #link} */
    @SerializedName("md5")
    public String md5;

    public VideoQuality getQuality() {
        return quality == null ? VideoQuality.NONE : quality;
    }

    public MimeType getType() {
        return type == null ? MimeType.NONE : type;
    }

    public boolean isVP6() {
        return getType() == MimeType.VP6;
    }

    /** @return true if this VideoFile doesn't have an expired field or if the expires date is before the current date */
    public boolean isExpired() {
        // If expires is null, we should probably refresh the video object regardless [KV] 3/31/16
        return expires == null || expires.before(new Date());
    }
}
