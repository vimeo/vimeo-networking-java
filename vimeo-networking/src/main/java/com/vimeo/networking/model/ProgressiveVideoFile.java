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
 * Created by zetterstromk on 8/3/17.
 */
@SuppressWarnings("unused")
@UseStag(FieldOption.SERIALIZED_NAME)
public class ProgressiveVideoFile extends VideoFile {

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

    /** The md5 provides us with a way to uniquely identify video files at {@link #getLink()} */
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

    @NotNull
    @Override
    public VideoQuality getQuality() {
        // TODO:  8/3/17 [KZ]
        return VideoQuality.HD;
    }

    // -----------------------------------------------------------------------------------------------------
    // Equals/Hashcode
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Equals/Hashcode">
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !(o instanceof ProgressiveVideoFile)) {
            return false;
        }

        ProgressiveVideoFile that = (ProgressiveVideoFile) o;

        return (mLink != null && that.getLink() != null) && mLink.equals(that.getLink());
    }

    @Override
    public int hashCode() {
        return mLink != null ? mLink.hashCode() : 0;
    }
    // </editor-fold>
}
