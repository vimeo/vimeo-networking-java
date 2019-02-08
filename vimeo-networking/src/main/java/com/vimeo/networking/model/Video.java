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
import com.vimeo.networking.model.Privacy.ViewValue;
import com.vimeo.networking.model.error.ErrorCode;
import com.vimeo.networking.model.live.Live;
import com.vimeo.networking.model.live.Live.LiveStatus;
import com.vimeo.networking.model.playback.Play;
import com.vimeo.networking.model.playback.PlayProgress;
import com.vimeo.networking.upload.Upload;
import com.vimeo.stag.UseStag;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * A model class representing a Video.
 * Created by alfredhanssen on 4/12/15.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
@UseStag
public class Video implements Serializable, Entity {

    private static final long serialVersionUID = -2289103918709562107L;

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

    public enum TvodVideoType {
        NONE,
        TRAILER,
        RENTAL,
        SUBSCRIPTION,
        PURCHASE,
        UNKNOWN
    }

    private static final String STATUS_NONE = "N/A";
    private static final String STATUS_AVAILABLE = "available";
    private static final String STATUS_UNAVAILABLE = "unavailable";
    private static final String STATUS_UPLOADING = "uploading";
    private static final String STATUS_TRANSCODE_STARTING = "transcode_starting";
    private static final String STATUS_TRANSCODING = "transcoding";
    private static final String STATUS_UPLOADING_ERROR = "uploading_error";
    private static final String STATUS_TRANSCODING_ERROR = "transcoding_error";
    private static final String STATUS_QUOTA_EXCEEDED = "quota_exceeded";
    private static final String STATUS_TOTAL_CAP_EXCEEDED = "total_cap_exceeded";

    @UseStag
    public enum Status {
        NONE(STATUS_NONE),
        @SerializedName(STATUS_AVAILABLE)
        AVAILABLE(STATUS_AVAILABLE),
        @SerializedName(STATUS_UNAVAILABLE)
        UNAVAILABLE(STATUS_UNAVAILABLE),
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
        QUOTA_EXCEEDED(STATUS_QUOTA_EXCEEDED),
        @SerializedName(STATUS_TOTAL_CAP_EXCEEDED)
        TOTAL_CAP_EXCEEDED(STATUS_TOTAL_CAP_EXCEEDED);

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

    @SerializedName("uri")
    public String mUri;

    @SerializedName("name")
    public String mName;

    @SerializedName("description")
    public String mDescription;

    @SerializedName("link")
    public String mLink;

    @SerializedName("duration")
    public int mDuration;

    @SerializedName("width")
    public int mWidth;

    @SerializedName("height")
    public int mHeight;

    /**
     * @deprecated The Embed field is in the process of moving on to the {@link #mPlay} field
     * as a much more robust object. The API will put this behind a future
     * version release, and when this library is updated to that version,
     * this field will no longer be accessible, and consumers will use the newer
     * representation.
     */
    @Deprecated
    @SerializedName("embed")
    public Embed mEmbed;

    @SerializedName("language")
    public String mLanguage;

    @SerializedName("created_time")
    public Date mCreatedTime;

    @SerializedName("modified_time")
    public Date mModifiedTime;

    @SerializedName("release_time")
    public Date mReleaseTime;

    @SerializedName("content_rating")
    public ArrayList<String> mContentRating;

    @SerializedName("license")
    public String mLicense;

    @SerializedName("privacy")
    public Privacy mPrivacy;

    @SerializedName("pictures")
    public PictureCollection mPictures;

    @SerializedName("tags")
    public ArrayList<Tag> mTags;

    @SerializedName("stats")
    public StatsCollection mStats;

    @SerializedName("metadata")
    public Metadata mMetadata;

    @SerializedName("user")
    public User mUser;

    @SerializedName("status")
    public Status mStatus;

    @SerializedName("categories")
    public ArrayList<Category> mCategories;

    @Nullable
    @SerializedName("password")
    public String mPassword;

    @Nullable
    @SerializedName("review_page")
    private ReviewPage mReviewPage;

    @Nullable
    @SerializedName("file_transfer")
    private FileTransferPage mFileTransferPage;

    @Nullable
    @SerializedName("play")
    public Play mPlay;

    @Nullable
    @SerializedName(value = "badge", alternate = "m_video_badge")
    public VideoBadge mVideoBadge;

    @Nullable
    @SerializedName("spatial")
    public Spatial mSpatial;

    @Nullable
    @SerializedName("live")
    public Live mLive;

    /**
     * The resource_key field is the unique identifier for a Video object. It may be used for object
     * comparison.
     */
    @SerializedName("resource_key")
    public String mResourceKey;

    @Nullable
    @SerializedName("upload")
    private Upload mUpload;

    // -----------------------------------------------------------------------------------------------------
    // Getters
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Getters">
    public String getUri() {
        return mUri;
    }

    public String getName() {
        return mName;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getLink() {
        return mLink;
    }

    public int getDuration() {
        return mDuration;
    }

    public int getWidth() {
        return mWidth;
    }

    public int getHeight() {
        return mHeight;
    }

    @Deprecated
    public Embed getEmbed() {
        return mEmbed;
    }

    public String getLanguage() {
        return mLanguage;
    }

    public Date getCreatedTime() {
        return mCreatedTime;
    }

    public Date getModifiedTime() {
        return mModifiedTime;
    }

    public Date getReleaseTime() {
        return mReleaseTime;
    }

    public ArrayList<String> getContentRating() {
        return mContentRating;
    }

    public String getLicense() {
        return mLicense;
    }

    public Privacy getPrivacy() {
        return mPrivacy;
    }

    public PictureCollection getPictures() {
        return mPictures;
    }

    public ArrayList<Tag> getTags() {
        return mTags;
    }

    public StatsCollection getStats() {
        return mStats;
    }

    public Metadata getMetadata() {
        return mMetadata;
    }

    public User getUser() {
        return mUser;
    }

    public ArrayList<Category> getCategories() {
        return mCategories;
    }

    @Nullable
    public Spatial getSpatial() {
        return mSpatial;
    }

    @Nullable
    public Live getLive() {
        return mLive;
    }

    @Nullable
    public Upload getUpload() {
        return mUpload;
    }

    @Nullable
    public FileTransferPage getFileTransferPage() {
        return mFileTransferPage;
    }

    // </editor-fold>

    // -----------------------------------------------------------------------------------------------------
    // Setters
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Setters">

    public void setFileTransferPage(@Nullable FileTransferPage fileTransferPage) {
        mFileTransferPage = fileTransferPage;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public void setName(String name) {
        mName = name;
    }

    public void setPlay(@Nullable Play play) {
        mPlay = play;
    }

    public void setUri(String uri) {
        mUri = uri;
    }

    public void setUser(User user) {
        mUser = user;
    }

    public void setLive(@Nullable Live live) {
        mLive = live;
    }

    void setUpload(@Nullable Upload upload) {
        mUpload = upload;
    }

    // </editor-fold>

    // -----------------------------------------------------------------------------------------------------
    // 360
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="360">

    /**
     * Returns true if the video is a 360 video.
     * Criteria for a video being 360 is that it
     * contains a populated{@link Spatial} field.
     *
     * @return true if the video is 360, false otherwise.
     */
    public boolean is360Video() {
        return mSpatial != null;
    }

    // </editor-fold>


    // -----------------------------------------------------------------------------------------------------
    // Resource Key
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Resource Key">
    public String getResourceKey() {
        return mResourceKey;
    }

    public void setResourceKey(String resourceKey) {
        this.mResourceKey = resourceKey;
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
     * <p>
     * For a more simplified representation of the video status, use {@link Video#getStatus()}.
     */
    public Status getRawStatus() {
        return mStatus == null ? Status.NONE : mStatus;
    }

    /**
     * This getter is always guaranteed to return a {@link Status},
     * {@link Status#NONE if null}. If the Status is equal to {@link Status#TRANSCODE_STARTING}
     * then we'll just return the Status {@link Status#TRANSCODING}
     * since they're functionally equivalent from a client-side perspective.
     * <p>
     * For an all-inclusive getter of the video status, use {@link Video#getRawStatus()}.
     */
    public Status getStatus() {
        if (mStatus == Status.TRANSCODE_STARTING) {
            return Status.TRANSCODING;
        }
        return getRawStatus();
    }

    public void setStatus(Status status) {
        this.mStatus = status;
    }
    // </editor-fold>


    // -----------------------------------------------------------------------------------------------------
    // Watch Later
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Watch Later">
    @Nullable
    public Interaction getWatchLaterInteraction() {
        if (mMetadata != null && mMetadata.mInteractions != null && mMetadata.mInteractions.mWatchLater != null) {
            return mMetadata.mInteractions.mWatchLater;
        }
        return null;
    }

    public boolean canWatchLater() {
        return getWatchLaterInteraction() != null;
    }

    public boolean isWatchLater() {
        return getWatchLaterInteraction() != null && getWatchLaterInteraction().mAdded;
    }

    @Nullable
    public Connection getWatchLaterConnection() {
        if (mMetadata != null && mMetadata.mConnections != null && mMetadata.mConnections.mWatchlater != null) {
            return mMetadata.mConnections.mWatchlater;
        }
        return null;
    }

    @Nullable
    public Connection getUsersWithAccessConnection() {
        if (mMetadata != null && mMetadata.mConnections != null) {
            return mMetadata.mConnections.getUsersWithAccess();
        }
        return null;
    }

    // </editor-fold>

    // <editor-fold desc="Channel">
    @Nullable
    public Interaction getChannelMembershipInteraction() {
        final InteractionCollection interactionCollection = mMetadata != null ? mMetadata.getInteractions() : null;

        return interactionCollection != null ? interactionCollection.getChannelMembership() : null;
    }
    // </editor-fold>

    // -----------------------------------------------------------------------------------------------------
    // Report
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Report">

    /**
     * @return an {@link Interaction} containing the necessary data for reporting a video to the API
     * for a terms of service violation. This method will return null in the event that reporting is
     * unavailable for this {@code Video}
     */
    @Nullable
    public Interaction getReportInteraction() {
        if (mMetadata != null && mMetadata.mInteractions != null && mMetadata.mInteractions.getReport() != null) {
            return mMetadata.mInteractions.getReport();
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
        // API hasn't restricted the interaction, but we don't want to allow viewers to like/unlike stock clips
        // so we explicitly exclude it on the client level by checking isStock()
        // TODO: remove when https://vimean.atlassian.net/browse/SWR-1276 is fixed
        if (mMetadata != null &&
            mMetadata.mInteractions != null &&
            mMetadata.mInteractions.mLike != null &&
            !isStock()) {
            return mMetadata.mInteractions.mLike;
        }
        return null;
    }

    public boolean canLike() {
        return getLikeInteraction() != null;
    }

    public boolean isLiked() {
        return getLikeInteraction() != null && getLikeInteraction().mAdded;
    }

    @Nullable
    public Connection getLikesConnection() {
        // API hasn't restricted the interaction, but we don't want to allow viewers to like/unlike stock clips
        // so we explicitly exclude it on the client level by checking isStock()
        // TODO: remove when https://vimean.atlassian.net/browse/SWR-1276 is fixed
        if ((mMetadata != null) &&
            (mMetadata.mConnections != null) &&
            (mMetadata.mConnections.mLikes != null) &&
            !isStock()) {
            return mMetadata.mConnections.mLikes;
        }
        return null;
    }

    public int likeCount() {
        if (getLikesConnection() != null) {
            return getLikesConnection().mTotal;
        }
        return 0;
    }

    @Nullable
    public Connection getRelatedConnection() {
        if ((mMetadata != null) && (mMetadata.mConnections != null)) {
            return mMetadata.mConnections.mRelated;
        }
        return null;
    }

    @Nullable
    public Connection getTextTracksConnection() {
        if (mMetadata != null && mMetadata.mConnections != null && mMetadata.mConnections.mTextTracks != null) {
            return mMetadata.mConnections.mTextTracks;
        }
        return null;
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
        return mStats != null ? mStats.mPlays : null;
    }
    // </editor-fold>

    // -----------------------------------------------------------------------------------------------------
    // Comments
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Comments">
    @Nullable
    public Connection getCommentsConnection() {
        if ((mMetadata != null) && (mMetadata.mConnections != null) && (mMetadata.mConnections.mComments != null)) {
            return mMetadata.mConnections.mComments;
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
        if (mMetadata != null && mMetadata.mConnections != null &&
            mMetadata.mConnections.mRecommendations != null) {
            recommendationsUri = mMetadata.mConnections.mRecommendations.mUri;
        }
        if (recommendationsUri == null) {
            recommendationsUri = mUri + Vimeo.ENDPOINT_RECOMMENDATIONS;
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
     * <li>The privacy is set to password</li>
     * <li>The user making the request owns the video</li>
     * <li>The application making the request is granted access to view this field</li>
     * </ul>
     *
     * @return the password if applicable
     */
    @Nullable
    public String getPassword() {
        return mPassword;
    }
    // </editor-fold>

    // -----------------------------------------------------------------------------------------------------
    // Review Link
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Review Link">
    @Nullable
    public String getReviewLink() {
        return mReviewPage != null ? mReviewPage.getLink() : null;
    }

    @Nullable
    public ReviewPage getReviewPage() {
        return mReviewPage;
    }

    public void setReviewPage(@Nullable ReviewPage reviewPage) {
        mReviewPage = reviewPage;
    }
    // </editor-fold>

    // -----------------------------------------------------------------------------------------------------
    // Play object, which holds the playback and embed controls
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Play object, which holds the playback and embed controls">
    @Nullable
    public Play getPlay() {
        return mPlay;
    }

    @Nullable
    public Play.Status getPlayStatus() {
        if (mPlay != null) {
            return mPlay.getStatus();
        }
        return null;
    }

    @Nullable
    public PlayProgress getPlayProgress() {
        return mPlay == null ? null : mPlay.getProgress();
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
    // Live
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Live">

    /**
     * @return true if this video is a live video. This will be true even if the video is no
     * longer streaming currently, but in the archived state.
     */
    public boolean isLiveVideo() {
        return mLive != null;
    }

    /**
     * @return true if this video is a live video and the live stream has ended.
     */
    public boolean isEndedLiveVideo() {
        final LiveStatus liveStatus = mLive != null ? mLive.getLiveStatus() : null;

        return liveStatus != null && (liveStatus == LiveStatus.DONE ||
                                      liveStatus == LiveStatus.STREAMING_ERROR ||
                                      liveStatus == LiveStatus.ARCHIVING ||
                                      liveStatus == LiveStatus.ARCHIVE_ERROR);
    }

    /**
     * @return true if this video is a live video that is currently streaming.
     */
    public boolean isStreamingLiveVideo() {
        final LiveStatus liveStatus = mLive != null ? mLive.getLiveStatus() : null;

        return liveStatus != null && liveStatus == LiveStatus.STREAMING;
    }

    /**
     * @return true if this video is a live video that is currently in a pre-stream state.
     */
    public boolean isPreStreamLiveVideo() {
        final LiveStatus liveStatus = mLive != null ? mLive.getLiveStatus() : null;

        return liveStatus != null && (liveStatus == LiveStatus.UNAVAILABLE ||
                                      liveStatus == LiveStatus.READY ||
                                      liveStatus == LiveStatus.PENDING);
    }
    // </editor-fold>

    // -----------------------------------------------------------------------------------------------------
    // VOD
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="VOD">

    /**
     * A TVOD video can have multiple purchase types active at once, this is a convenience method that
     * picks one of them based on the following priority:
     * 1) trailer
     * 2) purchased
     * 3) both rental and subscription? choose the later expiration, expirations equal? choose subscription
     * 4) subscription
     * 5) rental
     *
     * @return the TvodVideoType of the video or {@code TvodVideoType.NONE} if it is not a TVOD video or
     * {@code TvodVideoType.UNKNOWN} if it is a TVOD video that is not marked as rented, subscribed or bought
     */
    @NotNull
    public TvodVideoType getTvodVideoType() {
        if (isTvod()) {
            if (isTrailer()) {
                return TvodVideoType.TRAILER;
            }
            if (isTvodPurchase()) {
                return TvodVideoType.PURCHASE;
            }
            // rentals or subscriptions that have been purchased will always have an expiration date
            Date rentalExpires = getTvodRentalExpiration();
            Date subscriptionExpires = getTvodSubscriptionExpiration();
            if (rentalExpires != null && subscriptionExpires != null) {
                if (rentalExpires.after(subscriptionExpires)) {
                    return TvodVideoType.RENTAL;
                }
            }
            if (isTvodSubscription()) {
                return TvodVideoType.SUBSCRIPTION;
            }
            if (isTvodRental()) {
                return TvodVideoType.RENTAL;
            }
            // it is a TVOD, but it was not purchased.
            // it is either the user's own video or promo code access or possibly an extra on a series
            // regardless, we don't have a way to determine it at this point 5/4/16 [HR]
            return TvodVideoType.UNKNOWN;
        }
        return TvodVideoType.NONE;
    }

    /**
     * Determines if an {@link Interaction}
     * has been purchased by the user or not.
     *
     * @param interaction the interaction to check.
     * @return true if the user has purchased the interaction, false otherwise.
     */
    private static boolean isInteractionPurchased(@Nullable Interaction interaction) {
        return (interaction != null && interaction.mStream == Stream.PURCHASED);
    }

    /**
     * Returns the date the TVOD video will expire. In the event that a video is both rented and subscribed,
     * this will return the later expiration date. If they are equal, subscription date will be returned.
     *
     * @return the expiration date or null if there is no expiration
     */
    @Nullable
    public Date getTvodExpiration() {
        if (isTvod()) {
            Date rentalExpires = getTvodRentalExpiration();
            Date subscriptionExpires = getTvodSubscriptionExpiration();
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

    @Nullable
    public Date getTvodRentalExpiration() {
        if (isTvodRental()) {
            // isTvodRental will validate and prevent possible npes
            assert mMetadata.mInteractions.mRent != null;
            return mMetadata.mInteractions.mRent.mExpiration;
        }
        return null;
    }

    @Nullable
    public Date getTvodSubscriptionExpiration() {
        if (isTvodSubscription()) {
            // isTvodSubscription will validate and prevent possible npes
            assert mMetadata.mInteractions.mSubscribe != null;
            return mMetadata.mInteractions.mSubscribe.mExpiration;
        }
        return null;
    }

    /**
     * Determines if the video is a TVOD item
     * that might purchased by the user.
     * If the video is a trailer, it is excluded.
     *
     * @return true if the video is a TVOD item
     * that might be purchased, false otherwise.
     */
    private boolean isPossibleTvodPurchase() {
        return (isTvod() && !isTrailer() && mMetadata != null && mMetadata.mInteractions != null);
    }

    /**
     * Videos that are TVODs are part of Seasons. Included on a Season
     * Connection is the name of that season.
     *
     * @return the Season name, or null if this Video is not part of a Season.
     */
    @Nullable
    public String getTvodSeasonName() {
        if (mMetadata != null && mMetadata.mConnections != null &&
            mMetadata.mConnections.mSeason != null) {
            return mMetadata.mConnections.mSeason.getName();
        }
        return null;
    }

    /**
     * Determines if the video is a TVOD
     * rental item that the user has purchased.
     *
     * @return true if the video is a TVOD item and
     * the user has rented it, false otherwise.
     */
    public boolean isTvodRental() {
        return isPossibleTvodPurchase() && isInteractionPurchased(mMetadata.mInteractions.mRent);
    }

    /**
     * Determines if the video is a TVOD
     * subscription item that the user has
     * purchased.
     *
     * @return true if the video is a TVOD item
     * and the user has subscribed to it, false otherwise.
     */
    public boolean isTvodSubscription() {
        return isPossibleTvodPurchase() && isInteractionPurchased(mMetadata.mInteractions.mSubscribe);
    }

    /**
     * Determines if the video is a TVOD
     * buyable item that the user has
     * purchased.
     *
     * @return true if the video is a TVOD item
     * and the user has bought it, false otherwise.
     */
    public boolean isTvodPurchase() {
        return isPossibleTvodPurchase() && isInteractionPurchased(mMetadata.mInteractions.mBuy);
    }

    /**
     * Determines if the video is
     * a TVOD item.
     *
     * @return true if the video is a TVOD item,
     * false otherwise.
     */
    public boolean isTvod() {
        return mMetadata != null && mMetadata.mConnections != null && mMetadata.mConnections.mTvod != null;
    }

    public boolean isStock() {
        return mPrivacy != null && mPrivacy.getView() == ViewValue.STOCK;
    }

    /**
     * Helper to determine if this video is theoretically playable. Note that there may be device limitations
     * that cause a video to fail to play back in practice.
     *
     * @return true if this video can be played, false otherwise.
     */
    public boolean isPlayable() {
        return getPlayStatus() == Play.Status.PLAYABLE;
    }

    /**
     * Determines if the video is a trailer for a TVOD video.
     *
     * @return true if the video has a trailer connection
     * and is a TVOD video, false otherwise.
     */
    public boolean isTrailer() {
        return isTvod() && mMetadata.mConnections.mTrailer == null;
    }

    @Nullable
    public String getTrailerUri() {
        if (mMetadata != null && mMetadata.mConnections != null &&
            mMetadata.mConnections.mTrailer != null) {

            return mMetadata.mConnections.mTrailer.mUri;
        }
        return null;
    }
    // </editor-fold>

    // -----------------------------------------------------------------------------------------------------
    // Badge
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Badge">

    /**
     * @return the badge associated with this video, null if there is no badge
     */
    @Nullable
    public VideoBadge getVideoBadge() {
        return mVideoBadge;
    }
    // </editor-fold>

    // -----------------------------------------------------------------------------------------------------
    // Playback failure Endpoint
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Playback failure Endpoint">

    /**
     * In the event of playback failure for a Video, this uri can be hit to get a reason for the failure.
     * At the time of this writing, the failures that will be reported are only related to DRM. The protocol is a bit
     * odd in that it will return a failure if there has been a corresponding DRM failure with the video.
     * The error code that will come back is one of the DRM-related error codes listed in {@link ErrorCode}
     * <p>
     * If there is no related DRM failure for this video, the API will return a Void success.
     *
     * @return a uri string to hit for failure info, or null if none is available
     */
    @Nullable
    public String getPlaybackFailureUri() {
        String playbackFailureUri = null;
        if (mMetadata != null && mMetadata.mConnections != null &&
            mMetadata.mConnections.mPlaybackFailureReason != null) {
            playbackFailureUri = mMetadata.mConnections.mPlaybackFailureReason.mUri;
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

        return (this.mResourceKey != null && that.mResourceKey != null) &&
               this.mResourceKey.equals(that.mResourceKey);
    }

    @Override
    public int hashCode() {
        return this.mResourceKey != null ? this.mResourceKey.hashCode() : 0;
    }

    @Nullable
    @Override
    public String getIdentifier() {
        return mResourceKey;
    }
    // </editor-fold>

}
