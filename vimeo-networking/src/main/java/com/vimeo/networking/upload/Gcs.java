/*
 * Copyright (c) 2018 Vimeo (https://vimeo.com)
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

package com.vimeo.networking.upload;

import com.google.gson.annotations.SerializedName;
import com.vimeo.networking.model.Metadata;
import com.vimeo.stag.UseStag;
import com.vimeo.stag.UseStag.FieldOption;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

/**
 * The data representation of the Gcs object that is used for uploading files.
 */
@SuppressWarnings({"unused"})
@UseStag(FieldOption.SERIALIZED_NAME)
public class Gcs implements Serializable{

    private static final long serialVersionUID = -4272338075095674506L;

    @Nullable
    @SerializedName("start_byte")
    private Long mStartingByte;

    @Nullable
    @SerializedName("end_byte")
    private Long mEndingByte;

    @Nullable
    @SerializedName("upload_link")
    private String mUploadLink;

    @Nullable
    @SerializedName("metadata")
    private Metadata mMetadata;


    @Nullable
    public Metadata getMetadata() {
        return mMetadata;
    }

    public void setMetadata(@Nullable final Metadata metadata) {
        mMetadata = metadata;
    }

    /**
     * @return The starting byte used for uploading if available
     */
    @Nullable
    public Long getStartingByte() {
        return mStartingByte;
    }

    /**
     * Set the starting byte used for uploading
     */
    public void setStartingByte(@Nullable Long startingByte) {
        mStartingByte = startingByte;
    }
    /**
     * @return The ending byte used for uploading if available
     */
    @Nullable
    public Long getEndingByte() {
        return mEndingByte;
    }

    /**
     * Set the ending byte used for uploading
     */
    public void setEndingByte(@Nullable Long endingByte) {
        mEndingByte = endingByte;
    }
    /**
     * @return The upload link used for uploading if available
     */
    @Nullable
    public String getUploadLink() {
        return mUploadLink;
    }

    /**
     * Set the upload link used for uploading
     */
    public void setUploadLink(@Nullable String uploadLink) {
        mUploadLink = uploadLink;
    }

    @Override
    public String toString() {
        return "Gcs{" +
               "mStartingByte=" + mStartingByte +
               ", mEndingByte=" + mEndingByte +
               ", mUploadLink='" + mUploadLink + '\'' +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || !getClass().equals(o.getClass())) { return false; }

        final Gcs gcs = (Gcs) o;

        return (mStartingByte != null ? mStartingByte.equals(gcs.mStartingByte) : gcs.mStartingByte == null) &&
               (mEndingByte != null ? mEndingByte.equals(gcs.mEndingByte) : gcs.mEndingByte == null) &&
               (mUploadLink != null ? mUploadLink.equals(gcs.mUploadLink) : gcs.mUploadLink == null);
    }

    @Override
    public int hashCode() {
        int result = mStartingByte != null ? mStartingByte.hashCode() : 0;
        result = 31 * result + (mEndingByte != null ? mEndingByte.hashCode() : 0);
        result = 31 * result + (mUploadLink != null ? mUploadLink.hashCode() : 0);
        return result;
    }
}
