/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vimeo
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

import java.util.Date;

/**
 * Representation of a progressive Video stream/playback file.
 * <p>
 * Created by zetterstromk on 8/3/17.
 */
@SuppressWarnings("unused")
@UseStag(FieldOption.SERIALIZED_NAME)
public class ProgressiveVideoFile extends VideoFile {

    private static final long serialVersionUID = 2834083637971280026L;

    @UseStag
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

    @Nullable
    @SerializedName("type")
    protected MimeType mMimeType;

    @SerializedName("fps")
    protected double mFps;

    @SerializedName("width")
    protected int mWidth;

    @SerializedName("height")
    protected int mHeight;

    @SerializedName("size")
    protected long mSize; // size of the file, in bytes

    /**
     * The md5 provides us with a way to uniquely identify video files at {@link #getLink()}
     */
    @Nullable
    @SerializedName("md5")
    protected String mMd5;

    @Nullable
    @SerializedName("created_time")
    protected Date mCreatedTime; // time indicating when this transcode was completed

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

    private static final int HEIGHT_720P = 720;
    private static final int WIDTH_720P = 1280;

    @NotNull
    @Override
    public VideoQuality getQuality() {
        if (mHeight > HEIGHT_720P || mWidth > WIDTH_720P) {
            return VideoQuality.HD;
        } else {
            return VideoQuality.SD;
        }
    }
}
