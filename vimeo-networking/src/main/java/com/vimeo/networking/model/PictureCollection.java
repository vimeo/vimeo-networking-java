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

import com.vimeo.stag.GsonAdapterKey;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A model representing a "pictures" response, which contains an array
 * of {@link Picture}s
 * Created by hanssena on 4/23/15.
 */
public class PictureCollection implements Serializable {

    private static final long serialVersionUID = -4495146309328278574L;

    private static final String PICTURE_TYPE_CAUTION = "caution";
    private static final String PICTURE_TYPE_CUSTOM = "custom";
    private static final String PICTURE_TYPE_DEFAULT = "default";

    public enum PictureType {
        CAUTION,
        CUSTOM,
        DEFAULT,
        UNKNOWN
    }

    @Nullable
    @GsonAdapterKey("uri")
    String mUri;

    @GsonAdapterKey("active")
    boolean mIsActive;

    @Nullable
    @GsonAdapterKey("type")
    String mType;

    @Nullable
    @GsonAdapterKey("sizes")
    ArrayList<Picture> mPictures;

    @Nullable
    public Picture pictureForWidth(int width) {
        if (mPictures != null && !mPictures.isEmpty()) {
            Picture selectedPicture = mPictures.get(mPictures.size() - 1);
            for (Picture picture : mPictures) {
                if ((picture.width >= width) && ((picture.width - width) < (selectedPicture.width - width))) {
                    selectedPicture = picture;
                }
            }
            return selectedPicture;
        }

        return null;
    }

    @Nullable
    public String getUri() {
        return mUri;
    }

    public boolean isActive() {
        return mIsActive;
    }

    @Nullable
    public ArrayList<Picture> getPictures() {
        return mPictures;
    }

    @NotNull
    public PictureType getPictureType() {
        if (PICTURE_TYPE_CAUTION.equals(mType)) {
            return PictureType.CAUTION;
        }

        if (PICTURE_TYPE_CUSTOM.equals(mType)) {
            return PictureType.CUSTOM;
        }

        if (PICTURE_TYPE_DEFAULT.equals(mType)) {
            return PictureType.DEFAULT;
        }

        return PictureType.UNKNOWN;
    }
}
