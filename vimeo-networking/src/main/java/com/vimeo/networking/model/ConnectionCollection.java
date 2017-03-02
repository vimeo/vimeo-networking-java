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
@SuppressWarnings("unused")
@UseStag(FieldOption.SERIALIZED_NAME)
public class ConnectionCollection implements Serializable {

    private static final long serialVersionUID = -4523270955994232839L;
    @Nullable
    @SerializedName("videos")
    public Connection videos;
    @Nullable
    @SerializedName("categories")
    public Connection categories;
    @Nullable
    @SerializedName("comments")
    public Connection comments;
    @Nullable
    @SerializedName("credits")
    public Connection credits;
    @Nullable
    @SerializedName("likes")
    public Connection likes;
    @Nullable
    @SerializedName("pictures")
    public Connection pictures;
    @Nullable
    @SerializedName("texttracks")
    public Connection texttracks;
    @Nullable
    @SerializedName("activities")
    public Connection activities;
    @Nullable
    @SerializedName("albums")
    public Connection albums;
    @Nullable
    @SerializedName("channels")
    public Connection channels;
    @Nullable
    @SerializedName("moderated_channels")
    public Connection moderatedChannels;
    @Nullable
    @SerializedName("feed")
    public Connection feed;
    @Nullable
    @SerializedName("followers")
    public Connection followers;
    @Nullable
    @SerializedName("following")
    public Connection following;
    @Nullable
    @SerializedName("groups")
    public Connection groups;
    @Nullable
    @SerializedName("portfolios")
    public Connection portfolios;
    @Nullable
    @SerializedName("shared")
    public Connection shared;
    @Nullable
    @SerializedName("recommendations")
    public Connection recommendations;
    @Nullable
    @SerializedName("appearances")
    public Connection appearances;
    @Nullable
    @SerializedName("related")
    public Connection related;
    @Nullable
    @SerializedName("replies")
    public Connection replies;
    @Nullable
    @SerializedName("users")
    public Connection users;
    @Nullable
    @SerializedName("watchlater")
    public Connection watchlater;
    @Nullable
    @SerializedName("ondemand")
    public Connection ondemand;
    @Nullable
    @SerializedName("season")
    public Connection season;
    @Nullable
    @SerializedName("seasons")
    public Connection seasons;
    @Nullable
    @SerializedName("trailer")
    public Connection trailer;
    @Nullable
    @SerializedName("playback")
    public Connection playbackFailureReason;
    @Nullable
    @SerializedName("recommended_channels")
    public Connection recommendedChannels;
    @Nullable
    @SerializedName("recommended_users")
    public Connection recommendedUsers;
    @Nullable
    @SerializedName("watched_videos")
    public Connection watchedVideos;
    @Nullable
    @SerializedName("notifications")
    public NotificationConnection notifications;
    @Nullable
    @SerializedName("contents")
    public Connection contents;
}
