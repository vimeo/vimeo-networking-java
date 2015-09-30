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

import java.util.ArrayList;
import java.util.Date;

import javax.annotation.Nullable;

/**
 * Created by alfredhanssen on 4/12/15.
 */
public class Video extends BaseVideo {

    public enum ContentRating // TODO: use this enum [AH] 4/24/2015
    {
        SAFE,
        UNRATED,
        NUDITY,
        LANGUAGE,
        DRUGS,
        VIOLENCE
    }

    public enum LicenseValue // TODO: use this, https://developer.vimeo.com/api/playground/creativecommons [AH] 4/24/2015
    {
        ATTRIBUTION,
        ATTRIBUTION_SHARE_ALIKE,
        ATTRIBUTION_NO_DERIVATIVES,
        ATTRIBUTION_NON_COMMERCIAL,
        ATTRIBUTION_NON_COMMERCIAL_SHARE_ALIKE,
        ATTRIBUTION_NON_COMMERCIAL_NO_DERIVATIVES,
        PUBLIC_DOMAIN_DEDICATION
    }

    public enum Status {
        NONE("N/A"),
        @SerializedName("available")
        AVAILABLE("available"),
        @SerializedName("uploading")
        UPLOADING("uploading"),
        @SerializedName("transcoding")
        TRANSCODING("transcoding"),
        @SerializedName("uploading_error")
        UPLOADING_ERROR("uploading_error"),
        @SerializedName("transcoding_error")
        TRANSCODING_ERROR("transcoding_error");

        private String string;

        Status(String string) {
            this.string = string;
        }

        @Override
        // Overridden for analytics.
        public String toString() {
            return this.string;
        }
    }

    public String uri;
    public String name;
    public String description;
    public String link;
    public int duration;
    public ArrayList<VideoFile> files;
    public int width;
    public int height;
    public Embed embed;
    public String language;
    public Date createdTime;
    public Date modifiedTime;
    public ArrayList<String> contentRating;
    public String license;
    public com.vimeo.networking.model.Privacy privacy;
    public PictureCollection pictures;
    public ArrayList<Tag> tags;
    public StatsCollection stats;
    public Metadata metadata;
    public com.vimeo.networking.model.User user;
    private Status status;
    public VideoLog log;

    public Status getStatus() {
        return status == null ? Status.NONE : status;
    }

    public boolean canLike() {
        if (metadata != null && metadata.interactions != null && metadata.interactions.like != null) {
            return true;
        }
        return false;
    }

    public boolean isLiked() {
        if (metadata != null && metadata.interactions != null && metadata.interactions.like != null) {
            return metadata.interactions.like.added;
        }
        return false;
    }

    public boolean canWatchLater() {
        if (metadata != null && metadata.interactions != null && metadata.interactions.watchlater != null) {
            return true;
        }
        return false;
    }

    public boolean isWatchLater() {
        if (metadata != null && metadata.interactions != null && metadata.interactions.watchlater != null) {
            return metadata.interactions.watchlater.added;
        }
        return false;
    }

    public int likeCount() {
        if ((metadata != null) && (metadata.connections != null) && (metadata.connections.likes != null)) {
            return metadata.connections.likes.total;
        }

        return 0;
    }

    @Nullable
    public Integer playCount() {
        if (stats != null) {
            return stats.plays;
        } else {
            return null;
        }

    }

    @Nullable
    @Override
    public Video getVideo() {
        return this;
    }

    @Override
    public String getRelatedUri() {
        if (metadata == null || metadata.connections == null || metadata.connections.related == null) {
            return null;
        }
        return metadata.connections.related.uri;
    }
}
