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
import com.vimeo.stag.UseStag;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

/**
 * Stores a collection of Connection objects.
 * Created by hanssena on 4/23/15.
 */
@SuppressWarnings("unused")
@UseStag
public class ConnectionCollection implements Serializable {

    private static final long serialVersionUID = -4523270955994232839L;

    @Nullable
    public Connection videos;

    @Nullable
    public Connection categories;

    @Nullable
    public Connection comments;

    @Nullable
    public Connection credits;

    @Nullable
    public Connection likes;

    @Nullable
    public Connection pictures;

    @Nullable
    public Connection texttracks;

    @Nullable
    public Connection activities;

    @Nullable
    public Connection albums;

    @Nullable
    public Connection channels;

    @Nullable
    @SerializedName("moderated_channels")
    public Connection moderatedChannels;

    @Nullable
    public Connection feed;

    @Nullable
    public Connection followers;

    @Nullable
    public Connection following;

    @Nullable
    public Connection groups;

    @Nullable
    public Connection portfolios;

    @Nullable
    public Connection shared;

    @Nullable
    public Connection recommendations;

    @Nullable
    public Connection appearances;

    @Nullable
    public Connection related;

    @Nullable
    public Connection replies;

    @Nullable
    public Connection users;

    @Nullable
    public Connection watchlater;

    @Nullable
    public Connection ondemand;

    @Nullable
    public Connection season;

    @Nullable
    public Connection seasons;

    @Nullable
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
    public Connection notifications;
}
