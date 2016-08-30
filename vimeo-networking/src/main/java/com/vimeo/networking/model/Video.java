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
import com.vimeo.networking.model.Interaction.Stream;
import com.vimeo.networking.model.playback.Play;
import com.vimeo.networking.model.playback.PlayProgress;
import com.vimeo.stag.GsonAdapterKey;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * A model class representing a Video.
 * Created by alfredhanssen on 4/12/15.
 */
@SuppressWarnings("unused")
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

    public enum VodVideoType {
        NONE,
        TRAILER,
        RENTAL,
        SUBSCRIPTION,
        PURCHASE,
        UNKNOWN
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

        private final String mString;

        Status(String string) {
            mString = string;
        }

        @Override
        // Overridden for analytics.
        public String toString() {
            return mString;
        }
    }

    // Note: if you rename any fields, GSON serialization of persisted videos may break. [KV] 3/31/16
    @GsonAdapterKey("uri")
    public String uri;
    @GsonAdapterKey("name")
    public String name;
    @GsonAdapterKey("description")
    public String description;
    @GsonAdapterKey("link")
    public String link;
    @GsonAdapterKey("duration")
    public int duration;
    @Deprecated
    @GsonAdapterKey("files")
    public ArrayList<VideoFile> files;
    @GsonAdapterKey("width")
    public int width;
    @GsonAdapterKey("height")
    public int height;
    @Deprecated
    @GsonAdapterKey("embed")
    public com.vimeo.networking.model.Embed embed;
    @GsonAdapterKey("language")
    public String language;
    @GsonAdapterKey("created_time")
    public Date createdTime;
    @GsonAdapterKey("modified_time")
    public Date modifiedTime;
    @GsonAdapterKey("release_time")
    public Date releaseTime;
    @GsonAdapterKey("content_rating")
    public ArrayList<String> contentRating;
    @GsonAdapterKey("license")
    public String license;
    @GsonAdapterKey("privacy")
    public Privacy privacy;
    @GsonAdapterKey("pictures")
    public PictureCollection pictures;
    @GsonAdapterKey("tags")
    public ArrayList<Tag> tags;
    @GsonAdapterKey("stats")
    public StatsCollection stats;
    @GsonAdapterKey("metadata")
    public Metadata metadata;
    @GsonAdapterKey("user")
    public User user;
    @GsonAdapterKey("status")
    public Status status;
    @GsonAdapterKey("categories")
    public ArrayList<Category> categories;
    @Nullable
    @GsonAdapterKey("password")
    public String password;
    @Nullable
    @GsonAdapterKey("review_link")
    public String reviewLink;
    @Nullable
    @GsonAdapterKey("play")
    public Play play;
    @Nullable
    @GsonAdapterKey("download")
    public ArrayList<VideoFile> download;

    /**
     * The resource_key field is the unique identifier for a Video object. It may be used for object
     * comparison.
     */
    @GsonAdapterKey("resource_key")
    public String resourceKey;

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
     * {@link Video#getStatus()}, this will return all known statuses for a video (including {@link
     * Status#TRANSCODE_STARTING}.
     * <p/>
     * For a more simplified representation of the video status, use {@link Video#getStatus()}.
     */
    public Status getRawStatus() {
        return status == null ? Status.NONE : status;
    }

    /**
     * This getter is always guaranteed to return a {@link Status},
     * {@link Status#NONE if null}. If the Status is equal to {@link Status#TRANSCODE_STARTING}
     * then we'll just return the Status {@link Status#TRANSCODING}
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

    @Nullable
    public PlayProgress getPlayProgress() {
        return play == null ? null : play.getProgress();
    }

    /**
     * @return The position (in seconds) to resume from if the video
     * had previously been watched. It will return {@link Vimeo#NOT_FOUND}
     * if you do not have the proper API capabilities or 0 if:
     * <ul>
     * <li>User never watched video</li>
     * <li>Video is less than 300 seconds/5 minutes</li>
     * <li>User has watched less than 15 seconds of video</li>
     * <li>Video is less than or equal to 20 minutes long and user's
     * play progress is greater than the video's duration minus 15 seconds</li>
     * <li>Video is greater than 20 minutes long and user's
     * play progress is greater than the video's duration
     * minus 120 seconds/2 minutes</li>
     * </ul>
     * @see PlayProgress#getSeconds() for nullable value
     */
    public float getPlayProgressSeconds() {
        PlayProgress playProgress = getPlayProgress();
        if (playProgress == null) {
            return Vimeo.NOT_FOUND;
        }
        return playProgress.getSeconds() == null ? 0 : playProgress.getSeconds();
    }

    /** @see #getPlayProgressSeconds() */
    public long getPlayProgressMillis() {
        float progressSeconds = getPlayProgressSeconds();
        if (progressSeconds == Vimeo.NOT_FOUND) {
            return Vimeo.NOT_FOUND;
        }
        return TimeUnit.SECONDS.toMillis((long) progressSeconds);
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

    /**
     * A VOD video can have multiple purchase types active at once, this is a convenience method that
     * picks one of them based on the following priority:
     * 1) trailer
     * 2) purchased
     * 3) both rental and subscription? choose the later expiration, expirations equal? choose subscription
     * 4) subscription
     * 5) rental
     * @return the VodVideoType of the video or {@code VodVideoType.NONE} if it is not a VOD video or
     * {@code VodVideoType.UNKNOWN} if it is a VOD video that is not marked as rented, subscribed or bought
     */
    public VodVideoType getVodVideoType() {
        if (isVod()) {
            if (isTrailer()) {
                return VodVideoType.TRAILER;
            }
            if (isPurchase()) {
                return VodVideoType.PURCHASE;
            }
            // rentals or subscriptions that have been purchased will always have an expiration date
            Date rentalExpires = getRentalExpiration();
            Date subscriptionExpires = getSubscriptionExpiration();
            if (rentalExpires != null && subscriptionExpires != null) {
                if (rentalExpires.after(subscriptionExpires)) {
                    return VodVideoType.RENTAL;
                }
            }
            if (isSubscription()) {
                return VodVideoType.SUBSCRIPTION;
            }
            if (isRental()) {
                return VodVideoType.RENTAL;
            }
            // it is a VOD, but it was not purchased.
            // it is either the user's own video or promo code access or possibly an extra on a series
            // regardless, we don't have a way to determine it at this point 5/4/16 [HR]
            return VodVideoType.UNKNOWN;
        }
        return VodVideoType.NONE;
    }

    private static boolean isPurchased(Interaction interaction) {
        return (interaction != null && interaction.stream == Stream.PURCHASED);
    }

    /**
     * Returns the date the VOD video will expire. In the event that a video is both rented and subscribed,
     * this will return the later expiration date. If they are equal, subscription date will be returned.
     * @return the expiration date or null if there is no expiration
     */
    @Nullable
    public Date getVodExpiration() {
        if (isVod()) {
            Date rentalExpires = getRentalExpiration();
            Date subscriptionExpires = getSubscriptionExpiration();
            if (rentalExpires != null && subscriptionExpires != null) {
                if (rentalExpires.after(subscriptionExpires)) {
                    return rentalExpires;
                } else {
                    return subscriptionExpires;
                }
            } else if (rentalExpires != null) {
                return rentalExpires;
            } else if (subscriptionExpires != null) {
                return subscriptionExpires;
            }
        }
        return null;
    }

    private boolean isPossiblePurchase() {
        return (isVod() && !isTrailer() && metadata != null && metadata.interactions != null);
    }

    @Nullable
    public Date getRentalExpiration() {
        if (isRental()) {
            // isRental will validate and prevent possible npes
            assert metadata.interactions.rent != null;
            return metadata.interactions.rent.expiration;
        }
        return null;
    }

    @Nullable
    public Date getSubscriptionExpiration() {
        if (isSubscription()) {
            // isSubscription will validate and prevent possible npes
            assert metadata.interactions.subscribe != null;
            return metadata.interactions.subscribe.expiration;
        }
        return null;
    }

    public boolean isRental() {
        return (isPossiblePurchase() && isPurchased(metadata.interactions.rent));
    }

    public boolean isSubscription() {
        return (isPossiblePurchase() && isPurchased(metadata.interactions.subscribe));
    }

    public boolean isPurchase() {
        return (isPossiblePurchase() && isPurchased(metadata.interactions.buy));
    }

    public boolean isTrailer() {
        return (isVod() && metadata.connections.trailer == null);
    }

    public boolean isVod() {
        return (metadata != null && metadata.connections != null && metadata.connections.ondemand != null);
    }

    @Nullable
    public Play.Status getPlayStatus() {
        if (play != null) {
            return play.getStatus();
        }
        return null;
    }

    @Nullable
    public String getTrailerUri() {
        if (metadata != null && metadata.connections != null &&
            metadata.connections.trailer != null) {

            return metadata.connections.trailer.uri;
        }
        return null;
    }
    // </editor-fold>

    // -----------------------------------------------------------------------------------------------------
    // Playback failure Endpoint
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Playback failure Endpoint">
    @Nullable
    public String getPlaybackFailureUri() {
        String playbackFailureUri = null;
        if (metadata != null && metadata.connections != null &&
            metadata.connections.playbackFailureReason != null) {
            playbackFailureUri = metadata.connections.playbackFailureReason.uri;
        }
        return playbackFailureUri;
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
