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

package com.vimeo.networking.model.vod;

import com.google.gson.annotations.SerializedName;
import com.vimeo.networking.model.Connection;
import com.vimeo.networking.model.ConnectionCollection;
import com.vimeo.networking.model.InteractionCollection;
import com.vimeo.networking.model.Metadata;
import com.vimeo.networking.model.PictureCollection;
import com.vimeo.networking.model.User;
import com.vimeo.networking.model.Video;
import com.vimeo.stag.UseStag;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.Date;

/**
 * This class represents the model for a Video On Demand (VOD) container
 * Created by rigbergh on 4/25/16.
 */
@SuppressWarnings("unused")
@UseStag
public class VodItem implements Serializable {

    private static final String S_FILM = "film";
    private static final String S_SERIES = "series";

    public enum VodType {
        @SerializedName(S_FILM)
        FILM(S_FILM),
        @SerializedName(S_SERIES)
        SERIES(S_SERIES);

        @NotNull
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
        protected Date mTime;

        @Nullable
        public Date getTime() {
            return mTime;
        }

    }

    private static final long serialVersionUID = 8360150766347816073L;

    @Nullable
    @SerializedName("name")
    protected String name;

    @Nullable
    @SerializedName("description")
    protected String description;

    @Nullable
    @SerializedName("type")
    protected VodType type;

    @Nullable
    @SerializedName("link")
    protected String link;

    @Nullable
    @SerializedName("publish")
    protected Publish publish;

    @Nullable
    @SerializedName("pictures")
    protected PictureCollection pictures;

    @Nullable
    @SerializedName("metadata")
    protected Metadata metadata;

    @Nullable
    @SerializedName("user")
    protected User user;

    @Nullable
    @SerializedName("film")
    protected Video film;

    @Nullable
    @SerializedName("trailer")
    protected Video trailer;

    @Nullable
    public String getName() {
        return name;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    @Nullable
    public Publish getPublish() {
        return publish;
    }

    @Nullable
    public PictureCollection getPictures() {
        return pictures;
    }

    @Nullable
    public Metadata getMetadata() {
        return metadata;
    }

    @Nullable
    public InteractionCollection getInteractions() {
        return metadata != null ? metadata.getInteractions() : null;
    }

    @Nullable
    public ConnectionCollection getConnections() {
        return metadata != null ? metadata.getConnections() : null;
    }

    @Nullable
    public User getUser() {
        return user;
    }

    @Nullable
    public Video getFilm() {
        return film;
    }

    @Nullable
    public Video getTrailer() {
        return trailer;
    }

    @Nullable
    public String getLink() {
        return link;
    }

    @Nullable
    public VodType getType() {
        return type;
    }

    public int getViewableVideoCount() {
        Connection videos = getVideosConnection();
        return videos != null ? videos.getViewableTotal() : 0;
    }

    @Nullable
    public String getVideosUri() {
        Connection videos = getVideosConnection();
        return videos != null ? videos.getUri() : null;
    }

    @Nullable
    public Connection getVideosConnection() {
        ConnectionCollection connections = getConnections();
        return connections != null ? connections.getVideos() : null;
    }

    @Nullable
    public Connection getSeasonsConnection() {
        ConnectionCollection connections = getConnections();
        return connections != null ? connections.getSeasons() : null;
    }

    @Nullable
    public String getSeasonsUri() {
        Connection seasons = getSeasonsConnection();
        return seasons != null ? seasons.getUri() : null;
    }

    public void setName(@Nullable String name) {
        this.name = name;
    }

    public void setDescription(@Nullable String description) {
        this.description = description;
    }

    public void setType(@Nullable VodType type) {
        this.type = type;
    }

    public void setPublish(@Nullable Publish publish) {
        this.publish = publish;
    }

    public void setPictures(@Nullable PictureCollection pictures) {
        this.pictures = pictures;
    }

    public void setMetadata(@Nullable Metadata metadata) {
        this.metadata = metadata;
    }

    public void setUser(@Nullable User user) {
        this.user = user;
    }

    public void setFilm(@Nullable Video film) {
        this.film = film;
    }

    public void setTrailer(@Nullable Video trailer) {
        this.trailer = trailer;
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

        return (link != null && that.getLink() != null) && link.equals(that.getLink());
    }

    @Override
    public int hashCode() {
        return link != null ? link.hashCode() : 0;
    }
}
