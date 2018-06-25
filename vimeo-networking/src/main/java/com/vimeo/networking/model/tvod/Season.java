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

package com.vimeo.networking.model.tvod;

import com.google.gson.annotations.SerializedName;
import com.vimeo.networking.model.Connection;
import com.vimeo.networking.model.ConnectionCollection;
import com.vimeo.networking.model.Entity;
import com.vimeo.networking.model.Metadata;
import com.vimeo.networking.model.User;
import com.vimeo.stag.UseStag;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

/**
 * This model represents a "season" of content. Seasons are logical groupings
 * of videos set up by creators. All TVODs will have a Season, even if it is
 * a feature film - the season would contain the film in this case.
 * <p>
 * Created by zetterstromk on 10/4/16.
 */
@SuppressWarnings("unused")
@UseStag
public class Season implements Serializable, Entity {

    private static final String SEASON_TYPE_MAIN = "main";
    private static final String SEASON_TYPE_EXTRAS = "extras";

    public enum SeasonType {
        MAIN,
        EXTRAS
    }

    private static final long serialVersionUID = 2770200708069413413L;

    @Nullable
    @SerializedName("uri")
    protected String mUri;

    @Nullable
    @SerializedName("name")
    protected String mName;

    @Nullable
    @SerializedName("type")
    protected String mType;

    @Nullable
    @SerializedName("description")
    protected String mDescription;

    @Nullable
    @SerializedName("user")
    protected User mUser;

    @SerializedName("position")
    protected int mPosition;

    @Nullable
    @SerializedName("metadata")
    protected Metadata mMetadata;

    @Nullable
    @SerializedName("resource_key")
    protected String mResourceKey;

    // -----------------------------------------------------------------------------------------------------
    // Getters
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Getters">

    @Nullable
    public String getType() {
        return mType;
    }

    @Nullable
    public String getUri() {
        return mUri;
    }

    /**
     * @return the name of this season
     */
    @Nullable
    public String getName() {
        return mName;
    }

    /**
     * Seasons can be either "main" or "extras", and the type
     * is always provided.
     *
     * @return the {@link SeasonType}
     */
    @NotNull
    public SeasonType getSeasonType() {
        if (SEASON_TYPE_EXTRAS.equals(mType)) {
            return SeasonType.EXTRAS;
        }
        return SeasonType.MAIN;
    }

    /**
     * @return the description of this season
     */
    @Nullable
    public String getDescription() {
        return mDescription;
    }

    /**
     * @return the Creator of this content
     */
    @Nullable
    public User getUser() {
        return mUser;
    }

    /**
     * Seasons are given a position that details the order in which they
     * should be shown. The lower the position, the sooner the content should
     * be made available to show to the consumer.
     *
     * @return the position of the season
     */
    public int getPosition() {
        return mPosition;
    }

    /**
     * There will be a ConnectionCollection object holding a "videos" connection
     * for this season, as well as an InteractionCollection holding
     * possible purchase data
     *
     * @return the {@link Metadata}
     */
    @Nullable
    public Metadata getMetadata() {
        return mMetadata;
    }

    @Nullable
    public String getResourceKey() {
        return mResourceKey;
    }

    // </editor-fold>

    // -----------------------------------------------------------------------------------------------------
    // Helpers
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Helpers">

    @Nullable
    public ConnectionCollection getConnections() {
        return mMetadata != null ? mMetadata.getConnections() : null;
    }

    @Nullable
    public Connection getVideosConnection() {
        ConnectionCollection connections = getConnections();
        return connections != null ? connections.getVideos() : null;
    }

    @Nullable
    public String getVideosUri() {
        Connection videos = getVideosConnection();
        return videos != null ? videos.getUri() : null;
    }
    // </editor-fold>

    // -----------------------------------------------------------------------------------------------------
    // Comparison methods
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Comparison methods">
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Season)) {
            return false;
        }
        Season that = (Season) obj;

        return (mUri != null && that.getUri() != null) && mUri.equals(that.getUri());
    }

    @Override
    public int hashCode() {
        return mUri != null ? mUri.hashCode() : 0;
    }

    @Nullable
    @Override
    public String getIdentifier() {
        return mResourceKey;
    }
    // </editor-fold>
}
