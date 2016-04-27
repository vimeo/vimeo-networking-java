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
public class VodItem implements Serializable{
    public enum VodType {
        FILM,
        SERIES,
        UNKNOWN;
        private static String S_FILM = "film";
        private static String S_SERIES = "series";

        public static VodType from(@NotNull String type){
            if(type.equalsIgnoreCase(S_FILM)){
                return FILM;
            } else if(type.equalsIgnoreCase(S_SERIES)){
                return SERIES;
            }
            return UNKNOWN;
        }

        @Override
        public String toString() {
            if(this == FILM){
                return S_FILM;
            } else if(this == SERIES){
                return S_SERIES;
            }
            return null;
        }
    }

    private static class Publish implements Serializable{

        private static final long serialVersionUID = -994389241935894370L;

        @SuppressWarnings("unused")
        @Nullable
        @SerializedName("time")
        public Date time;
    }

    private static final long serialVersionUID = 8360150766347816073L;

    @SuppressWarnings("unused")
    @Nullable
    @SerializedName("name")
    private String mName;

    @SuppressWarnings("unused")
    @Nullable
    @SerializedName("description")
    private String mDescription;

    @SuppressWarnings("unused")
    @Nullable
    @SerializedName("type")
    private String mType;

    @SuppressWarnings("unused")
    @Nullable
    @SerializedName("link")
    private String mLink;

    @SuppressWarnings("unused")
    @Nullable
    @SerializedName("publish")
    private Publish mPublish;

    @SuppressWarnings("unused")
    @Nullable
    @SerializedName("pictures")
    private PictureCollection mPictures;

    @SuppressWarnings("unused")
    @Nullable
    @SerializedName("metadata")
    private Metadata mMetadata;

    @SuppressWarnings("unused")
    @Nullable
    @SerializedName("user")
    private User mUser;

    @SuppressWarnings("unused")
    @Nullable
    @SerializedName("film")
    private Video mFilm;

    @SuppressWarnings("unused")
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
    public String getLink() {
        return mLink;
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
    public User getUser() {
        return mUser;
    }

    @Nullable
    @SuppressWarnings("unused")
    public Video getFilm() {
        return mFilm;
    }

    @Nullable
    @SuppressWarnings("unused")
    public Video getTrailer() {
        return mTrailer;
    }

    @NotNull
    public VodType getType(){
        return mType == null ? VodType.UNKNOWN : VodType.from(mType);
    }

    public void setName(@Nullable String name) {
        mName = name;
    }

    public void setDescription(@Nullable String description) {
        mDescription = description;
    }

    public void setType(@Nullable VodType type) {
        mType = type.toString();
    }

    public void setLink(@Nullable String link) {
        mLink = link;
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
}
