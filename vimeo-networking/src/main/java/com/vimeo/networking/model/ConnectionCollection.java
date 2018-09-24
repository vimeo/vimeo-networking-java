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
import com.vimeo.networking.model.notifications.NotificationConnection;
import com.vimeo.stag.UseStag;
import com.vimeo.stag.UseStag.FieldOption;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

/**
 * Stores a collection of Connection objects.
 * Created by hanssena on 4/23/15.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
@UseStag(FieldOption.SERIALIZED_NAME)
public class ConnectionCollection implements Serializable {

    private static final long serialVersionUID = -4523270955994232839L;

    @Nullable
    @SerializedName("videos")
    protected Connection mVideos;

    @Nullable
    @SerializedName("categories")
    protected Connection mCategories;

    @Nullable
    @SerializedName("comments")
    protected Connection mComments;

    @Nullable
    @SerializedName("credits")
    protected Connection mCredits;

    @Nullable
    @SerializedName("likes")
    protected Connection mLikes;

    @Nullable
    @SerializedName("pictures")
    protected Connection mPictures;

    @Nullable
    @SerializedName("texttracks")
    protected Connection mTextTracks;

    @Nullable
    @SerializedName("albums")
    protected Connection mAlbums;

    @Nullable
    @SerializedName("channels")
    protected Connection mChannels;

    @Nullable
    @SerializedName("moderated_channels")
    protected Connection mModeratedChannels;

    @Nullable
    @SerializedName("feed")
    protected Connection mFeed;

    @Nullable
    @SerializedName("followers")
    protected Connection mFollowers;

    @Nullable
    @SerializedName("following")
    protected Connection mFollowing;

    @Nullable
    @SerializedName("groups")
    protected Connection mGroups;

    @Nullable
    @SerializedName("portfolios")
    protected Connection mPortfolios;

    @Nullable
    @SerializedName("shared")
    protected Connection mShared;

    @Nullable
    @SerializedName("recommendations")
    protected Connection mRecommendations;

    @Nullable
    @SerializedName("appearances")
    protected Connection mAppearances;

    @Nullable
    @SerializedName("related")
    protected Connection mRelated;

    @Nullable
    @SerializedName("replies")
    protected Connection mReplies;

    @Nullable
    @SerializedName("users")
    protected Connection mUsers;

    @Nullable
    @SerializedName("watchlater")
    protected Connection mWatchlater;

    @Nullable
    @SerializedName("ondemand")
    protected Connection mTvod;

    @Nullable
    @SerializedName("season")
    protected Connection mSeason;

    @Nullable
    @SerializedName("seasons")
    protected Connection mSeasons;

    @Nullable
    @SerializedName("trailer")
    protected Connection mTrailer;

    @Nullable
    @SerializedName("playback")
    protected Connection mPlaybackFailureReason;

    @Nullable
    @SerializedName("recommended_channels")
    protected Connection mRecommendedChannels;

    @Nullable
    @SerializedName("recommended_users")
    protected Connection mRecommendedUsers;

    @Nullable
    @SerializedName("upload_attempt")
    private Connection mUploadAttempt;

    @Nullable
    @SerializedName("watched_videos")
    protected Connection mWatchedVideos;

    @Nullable
    @SerializedName("users_with_access")
    protected Connection mUsersWithAccess;

    @Nullable
    @SerializedName("notifications")
    protected NotificationConnection mNotifications;

    @Nullable
    @SerializedName("contents")
    protected Connection mContents;

    @Nullable
    @SerializedName("folders")
    private Connection mFolders;

    @Nullable
    @SerializedName("live_stats")
    private Interaction mLiveStats;

    /**
     * @return the {@link Interaction} for getting the {@link com.vimeo.networking.model.live.LiveStats}
     * for a live {@link Video}
     */
    @Nullable
    public Interaction getLiveStats() {
        return mLiveStats;
    }

    @Nullable
    public Connection getVideos() {
        return mVideos;
    }

    @Nullable
    public Connection getCategories() {
        return mCategories;
    }

    @Nullable
    public Connection getComments() {
        return mComments;
    }

    @Nullable
    public Connection getCredits() {
        return mCredits;
    }

    @Nullable
    public Connection getLikes() {
        return mLikes;
    }

    @Nullable
    public Connection getPictures() {
        return mPictures;
    }

    @Nullable
    public Connection getTextTracks() {
        return mTextTracks;
    }

    @Nullable
    public Connection getAlbums() {
        return mAlbums;
    }

    @Nullable
    public Connection getChannels() {
        return mChannels;
    }

    @Nullable
    public Connection getModeratedChannels() {
        return mModeratedChannels;
    }

    @Nullable
    public Connection getFeed() {
        return mFeed;
    }

    @Nullable
    public Connection getFollowers() {
        return mFollowers;
    }

    @Nullable
    public Connection getFollowing() {
        return mFollowing;
    }

    @Nullable
    public Connection getGroups() {
        return mGroups;
    }

    @Nullable
    public Connection getPortfolios() {
        return mPortfolios;
    }

    @Nullable
    public Connection getShared() {
        return mShared;
    }

    @Nullable
    public Connection getRecommendations() {
        return mRecommendations;
    }

    @Nullable
    public Connection getAppearances() {
        return mAppearances;
    }

    @Nullable
    public Connection getRelated() {
        return mRelated;
    }

    @Nullable
    public Connection getReplies() {
        return mReplies;
    }

    @Nullable
    public Connection getUsers() {
        return mUsers;
    }

    @Nullable
    public Connection getWatchlater() {
        return mWatchlater;
    }

    @Nullable
    public Connection getTvod() {
        return mTvod;
    }

    @Nullable
    public Connection getSeason() {
        return mSeason;
    }

    @Nullable
    public Connection getSeasons() {
        return mSeasons;
    }

    @Nullable
    public Connection getTrailer() {
        return mTrailer;
    }

    @Nullable
    public Connection getPlaybackFailureReason() {
        return mPlaybackFailureReason;
    }

    @Nullable
    public Connection getRecommendedChannels() {
        return mRecommendedChannels;
    }

    @Nullable
    public Connection getRecommendedUsers() {
        return mRecommendedUsers;
    }

    @Nullable
    public Connection getWatchedVideos() {
        return mWatchedVideos;
    }

    @Nullable
    public NotificationConnection getNotifications() {
        return mNotifications;
    }

    @Nullable
    public Connection getContents() {
        return mContents;
    }

    @Nullable
    public Connection getFolders() {
        return mFolders;
    }

    @Nullable
    public Connection getUploadAttempt() {
        return mUploadAttempt;
    }

    @Nullable
    public Connection getUsersWithAccess() {
        return mUsersWithAccess;
    }

    public void setUsersWithAccess(@Nullable Connection usersWithAccess) {
        mUsersWithAccess = usersWithAccess;
    }

    public void setFolders(@Nullable Connection folders) {
        mFolders = folders;
    }

    void setLiveStats(@Nullable Interaction liveStats) {
        mLiveStats = liveStats;
    }

    void setUploadAttempt(@Nullable Connection uploadAttempt) {
        mUploadAttempt = uploadAttempt;
    }
}
