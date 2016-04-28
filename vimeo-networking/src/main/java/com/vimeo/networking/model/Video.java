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
import com.vimeo.networking.Vimeo;
import com.vimeo.networking.model.playback.Play;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by alfredhanssen on 4/12/15.
 */
public class Video implements Serializable {

    private static final long serialVersionUID = -1282907783845240057L;

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

    private static final String STATUS_NONE = "N/A";
    private static final String STATUS_AVAILABLE = "available";
    private static final String STATUS_UPLOADING = "uploading";
    private static final String STATUS_TRANSCODE_STARTING = "transcode_starting";
    private static final String STATUS_TRANSCODING = "transcoding";
    private static final String STATUS_UPLOADING_ERROR = "uploading_error";
    private static final String STATUS_TRANSCODING_ERROR = "transcoding_error";
    private static final String STATUS_QUOTA_EXCEEDED = "quota_exceeded";

    public enum Status {
        NONE(STATUS_NONE),
        @SerializedName(STATUS_AVAILABLE)
        AVAILABLE(STATUS_AVAILABLE),
        @SerializedName(STATUS_UPLOADING)
        UPLOADING(STATUS_UPLOADING),
        @SerializedName(STATUS_TRANSCODE_STARTING)
        TRANSCODE_STARTING(STATUS_TRANSCODE_STARTING),
        @SerializedName(STATUS_TRANSCODING)
        TRANSCODING(STATUS_TRANSCODING),
        // Errors
        @SerializedName(STATUS_UPLOADING_ERROR)
        UPLOADING_ERROR(STATUS_UPLOADING_ERROR),
        @SerializedName(STATUS_TRANSCODING_ERROR)
        TRANSCODING_ERROR(STATUS_TRANSCODING_ERROR),
        @SerializedName(STATUS_QUOTA_EXCEEDED)
        QUOTA_EXCEEDED(STATUS_QUOTA_EXCEEDED);

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

    // Note: if you rename any fields, GSON serialization of persisted videos may break. [KV] 3/31/16
    public String uri;
    public String name;
    public String description;
    public String link;
    public int duration;
    @Deprecated
    public ArrayList<VideoFile> files;
    public int width;
    public int height;
    @Deprecated
    public com.vimeo.networking.model.Embed embed;
    public String language;
    public Date createdTime;
    public Date modifiedTime;
    public ArrayList<String> contentRating;
    public String license;
    public Privacy privacy;
    public PictureCollection pictures;
    public ArrayList<Tag> tags;
    public StatsCollection stats;
    public Metadata metadata;
    public User user;
    private Status status;
    public List<Category> categories;
    @Nullable
    private String password;
    @Nullable
    private String reviewLink;
    @Nullable
    private Play play;
    @Nullable
    private ArrayList<VideoFile> download;

    /** The resource_key field is the unique identifier for a Video object. It may be used for object comparison. */
    private String resourceKey;

    // -----------------------------------------------------------------------------------------------------
    // Resource Key
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Resource Key">
    public String getResourceKey() {
        return resourceKey;
    }

    public void setResourceKey(String resourceKey) {
        this.resourceKey = resourceKey;
    }
    // </editor-fold>

    // -----------------------------------------------------------------------------------------------------
    // Status
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Status">

    /**
     * This will return the value as it's given to us from the API (or {@link Status#NONE if null}). Unlike
     * {@link Video#getStatus()}, this will return all known statuses for a video (including {@link Status#TRANSCODE_STARTING}.
     * <p/>
     * For a more simplified representation of the video status, use {@link Video#getStatus()}.
     */
    public Status getRawStatus() {
        return status == null ? Status.NONE : status;
    }

    /**
     * This getter is always guaranteed to return a {@link Status}, {@link Status#NONE if null}. If the Status
     * is equal to {@link Status#TRANSCODE_STARTING} then we'll just return the Status {@link Status#TRANSCODING}
     * since they're functionally equivalent from a client-side perspective.
     * <p/>
     * For an all-inclusive getter of the video status, use {@link Video#getRawStatus()}.
     */
    public Status getStatus() {
        if (status == Status.TRANSCODE_STARTING) {
            return Status.TRANSCODING;
        }
        return getRawStatus();
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    // </editor-fold>


    // -----------------------------------------------------------------------------------------------------
    // Watch Later
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Watch Later">
    @Nullable
    public Interaction getWatchLaterInteraction() {
        if (metadata != null && metadata.interactions != null && metadata.interactions.watchlater != null) {
            return metadata.interactions.watchlater;
        }
        return null;
    }

    public boolean canWatchLater() {
        return getWatchLaterInteraction() != null;
    }

    public boolean isWatchLater() {
        return getWatchLaterInteraction() != null && getWatchLaterInteraction().added;
    }

    @Nullable
    public Connection getWatchLaterConnection() {
        if (metadata != null && metadata.connections != null && metadata.connections.watchlater != null) {
            return metadata.connections.watchlater;
        }
        return null;
    }
    // </editor-fold>

    // -----------------------------------------------------------------------------------------------------
    // Likes
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Like">
    @Nullable
    public Interaction getLikeInteraction() {
        if (metadata != null && metadata.interactions != null && metadata.interactions.like != null) {
            return metadata.interactions.like;
        }
        return null;
    }

    public boolean canLike() {
        return getLikeInteraction() != null;
    }

    public boolean isLiked() {
        return getLikeInteraction() != null && getLikeInteraction().added;
    }

    @Nullable
    public Connection getLikesConnection() {
        if ((metadata != null) && (metadata.connections != null) && (metadata.connections.likes != null)) {
            return metadata.connections.likes;
        }
        return null;
    }

    @Nullable
    public Connection getRelatedConnection() {
        if ((metadata != null) && (metadata.connections != null)) {
            return metadata.connections.related;
        }
        return null;
    }

    public int likeCount() {
        if (getLikesConnection() != null) {
            return getLikesConnection().total;
        }
        return 0;
    }
    // </editor-fold>

    // -----------------------------------------------------------------------------------------------------
    // Stats
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Stats">

    /**
     * @return the number of plays this Videohas . It will return <code>null</code> if the owner of the video
     * has specified that they want to hide play count.
     */
    @Nullable
    public Integer playCount() {
        if (stats != null) {
            return stats.plays;
        } else {
            return null;
        }
    }
    // </editor-fold>

    // -----------------------------------------------------------------------------------------------------
    // Files
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Files">
    @Nullable
    public VideoFile getVideoFileForMd5(String md5) {
        // Only Progressive video files have an md5
        if (play == null || play.getProgressiveVideoFiles() == null) {
            return null;
        }
        for (VideoFile file : play.getProgressiveVideoFiles()) {
            if (file != null && file.getMd5() != null && file.getMd5().equals(md5)) {
                return file;
            }
        }
        return null;
    }
    // </editor-fold>

    // -----------------------------------------------------------------------------------------------------
    // Recommendation
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Recommendation">
    public String recommendationsUri() {
        String recommendationsUri = null;
        if (metadata != null && metadata.connections != null &&
            metadata.connections.recommendations != null) {
            recommendationsUri = metadata.connections.recommendations.uri;
        }
        if (recommendationsUri == null) {
            recommendationsUri = uri + Vimeo.ENDPOINT_RECOMMENDATIONS;
        }
        return recommendationsUri;
    }
    // </editor-fold>

    // -----------------------------------------------------------------------------------------------------
    // Password
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Password">

    /**
     * The password for the video, only sent when the following conditions are true:
     * <ul>
     * <ol>The privacy is set to password</ol>
     * <ol>The user making the request owns the video</ol>
     * <ol>The application making the request is granted access to view this field</ol>
     * </ul>
     *
     * @return the password if applicable
     */
    @Nullable
    public String getPassword() {
        return password;
    }
    // </editor-fold>

    // -----------------------------------------------------------------------------------------------------
    // Review Link
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Review Link">
    @Nullable
    public String getReviewLink() {
        return reviewLink;
    }
    // </editor-fold>

    // -----------------------------------------------------------------------------------------------------
    // Play object, which holds the playback and embed controls
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Play object, which holds the playback and embed controls">
    @Nullable
    public Play getPlay() {
        return play;
    }
    // </editor-fold>

    // -----------------------------------------------------------------------------------------------------
    // Download - an array of VideoFile objects that may be downloaded
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Download">
    @Nullable
    public ArrayList<VideoFile> getDownload() {
        return download;
    }
    // </editor-fold>

    // -----------------------------------------------------------------------------------------------------
    // VOD
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="VOD">
    // TODO: Most of this is all for testing. Redo when API is ready 4/28/16 [KZ]
    private boolean mIsTrailer;

    // TODO: Remove setter 4/28/16 [KZ]
    public void setTrailer(boolean isTrailer) {
        mIsTrailer = isTrailer;
    }

    public boolean isTrailer() {
        return mIsTrailer; // FIXME: 4/28/16
    }

    private boolean mIsVod;

    // TODO: remove setter 4/28/16 [KZ]
    public void setVod(boolean isVod) {
        mIsVod = isVod;
    }

    public boolean isVod() {
        return mIsVod; // FIXME: 4/28/16
    }
    // </editor-fold>

    // -----------------------------------------------------------------------------------------------------
    // Equals/Hashcode
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Equals/Hashcode">
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !(o instanceof Video)) {
            return false;
        }

        Video that = (Video) o;

        return (this.resourceKey != null && that.resourceKey != null) &&
               this.resourceKey.equals(that.resourceKey);
    }

    @Override
    public int hashCode() {
        return this.resourceKey != null ? this.resourceKey.hashCode() : 0;
    }
    // </editor-fold>
}
