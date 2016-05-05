/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 Vimeo
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

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.Date;

/**
 * This class represents the model for a Video On Demand (VOD) container
 * Created by rigbergh on 4/25/16.
 */
@SuppressWarnings("unused")
public class VodItem implements Serializable {

    private static final String S_FILM = "film";
    private static final String S_SERIES = "series";

    public enum VodType {
        @SerializedName(S_FILM)
        FILM(S_FILM),
        @SerializedName(S_SERIES)
        SERIES(S_SERIES);

        private final String mType;

        VodType(@NotNull String type) {
            mType = type;
        }

        @Override
        public String toString() {
            return mType;
        }
    }

    public static class Publish implements Serializable {

        private static final long serialVersionUID = -994389241935894370L;

        @Nullable
        @SerializedName("time")
        private Date mTime;

        @Nullable
        public Date getTime() {
            return mTime;
        }

    }

    private static final long serialVersionUID = 8360150766347816073L;

    @Nullable
    @SerializedName("name")
    private String mName;

    @Nullable
    @SerializedName("description")
    private String mDescription;

    @Nullable
    @SerializedName("type")
    private VodType mType;

    @Nullable
    @SerializedName("link")
    private String mLink;

    @Nullable
    @SerializedName("publish")
    private Publish mPublish;

    @Nullable
    @SerializedName("pictures")
    private PictureCollection mPictures;

    @Nullable
    @SerializedName("metadata")
    private Metadata mMetadata;

    @Nullable
    @SerializedName("user")
    private User mUser;

    @Nullable
    @SerializedName("film")
    private Video mFilm;

    @Nullable
    @SerializedName("trailer")
    private Video mTrailer;

    @Nullable
    public String getName() {
        return mName;
    }

    @Nullable
    public String getDescription() {
        return mDescription;
    }

    @Nullable
    public Publish getPublish() {
        return mPublish;
    }

    @Nullable
    public PictureCollection getPictures() {
        return mPictures;
    }

    @Nullable
    public Metadata getMetadata() {
        return mMetadata;
    }

    @Nullable
    public InteractionCollection getInteractions() {
        return mMetadata != null ? mMetadata.interactions : null;
    }

    @Nullable
    public ConnectionCollection getConnections() {
        return mMetadata != null ? mMetadata.connections : null;
    }

    @Nullable
    public User getUser() {
        return mUser;
    }

    @Nullable
    public Video getFilm() {
        return mFilm;
    }

    @Nullable
    public Video getTrailer() {
        return mTrailer;
    }

    @Nullable
    public String getLink() {
        return mLink;
    }

    @Nullable
    public VodType getType() {
        return mType;
    }

    public void setName(@Nullable String name) {
        mName = name;
    }

    public void setDescription(@Nullable String description) {
        mDescription = description;
    }

    public void setType(@Nullable VodType type) {
        mType = type;
    }

    public void setPublish(@Nullable Publish publish) {
        mPublish = publish;
    }

    public void setPictures(@Nullable PictureCollection pictures) {
        mPictures = pictures;
    }

    public void setMetadata(@Nullable Metadata metadata) {
        mMetadata = metadata;
    }

    public void setUser(@Nullable User user) {
        mUser = user;
    }

    public void setFilm(@Nullable Video film) {
        mFilm = film;
    }

    public void setTrailer(@Nullable Video trailer) {
        mTrailer = trailer;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof VodItem)) {
            return false;
        }
        VodItem that = (VodItem) obj;

        return (mLink != null && that.getLink() != null) && mLink.equals(that.getLink());
    }

    @Override
    public int hashCode() {
        return mLink != null ? mLink.hashCode() : 0;
    }
}
