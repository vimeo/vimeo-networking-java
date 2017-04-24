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

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A model representing a "pictures" response, which contains an array
 * of {@link Picture}s
 * Created by hanssena on 4/23/15.
 */
@SuppressWarnings("unused")
@UseStag
public class PictureCollection implements Serializable {

    private static final long serialVersionUID = -4495146309328278574L;

    private static final String PICTURE_TYPE_CAUTION = "caution";
    private static final String PICTURE_TYPE_CUSTOM = "custom";
    private static final String PICTURE_TYPE_DEFAULT = "default";

    /**
     * The type of picture
     * <ol>
     * <li>{@link #CAUTION} Caution is for Video pictures only. It means the video does not match
     * the authenticated (current logged in) users content rating, so a different thumbnail is
     * returned instead</li>
     * <li>{@link #CUSTOM} A user uploaded/generated image</li>
     * <li>{@link #DEFAULT} Default means the default image provided by Vimeo when the creator has
     * not uploaded a custom image for the associated content</li>
     * </ol>
     */
    public enum PictureType {
        CAUTION,
        CUSTOM,
        DEFAULT,
        UNKNOWN
    }

    @Nullable
    @SerializedName(value = "uri", alternate = "m_uri")
    protected String mUri;

    @SerializedName(value = "active", alternate = "m_is_active")
    protected boolean mIsActive;

    @Nullable
    @SerializedName(value = "type", alternate = "m_type")
    protected String mType;

    @Nullable
    @SerializedName(value = "sizes", alternate = "m_pictures")
    protected ArrayList<Picture> mPictures;

    @Nullable
    public Picture pictureForWidth(int width) {
        if (mPictures != null && !mPictures.isEmpty()) {
            Picture selectedPicture = mPictures.get(mPictures.size() - 1);
            for (Picture picture : mPictures) {
                if ((picture.mWidth >= width) && ((picture.mWidth - width) < (selectedPicture.mWidth - width))) {
                    selectedPicture = picture;
                }
            }
            return selectedPicture;
        }

        return null;
    }

    /**
     * Return the uri representing this object
     */
    @Nullable
    public String getUri() {
        return mUri;
    }

    /**
     * Returns true if this picture collection is active. Numerous picture
     * collections can be uploaded for an object (User, Category, Channel, Video, etc)
     * but only one may be active at a time. This will be true whenever this
     * object is returned as part of a another object since it is actively
     * representing that object. It can be false if requesting a
     * PictureCollection on its own - it will be false if it does not actively
     * represent the pictures of another object.
     */
    public boolean isActive() {
        return mIsActive;
    }

    /**
     * Returns the array of {@link Picture}s in this collection
     */
    @Nullable
    public ArrayList<Picture> getPictures() {
        return mPictures;
    }

    /**
     * Returns the type of picture represented here, one of {@link
     * PictureType}.
     *
     * @see PictureType PictureType for more info
     */
    @NotNull
    public PictureType getPictureType() {
        if (mType == null) {
            return PictureType.UNKNOWN;
        }

        switch (mType) {
            case PICTURE_TYPE_CAUTION:
                return PictureType.CAUTION;
            case PICTURE_TYPE_CUSTOM:
                return PictureType.CUSTOM;
            case PICTURE_TYPE_DEFAULT:
                return PictureType.DEFAULT;
            default:
                return PictureType.UNKNOWN;
        }
    }
}
