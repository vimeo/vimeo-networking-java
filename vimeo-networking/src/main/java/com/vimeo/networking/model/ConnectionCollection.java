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

import com.vimeo.stag.GsonAdapterKey;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

/**
 * Stores a collection of Connection objects.
 * Created by hanssena on 4/23/15.
 */
@SuppressWarnings("unused")
public class ConnectionCollection implements Serializable {

    private static final long serialVersionUID = -4523270955994232839L;
    @Nullable
    @GsonAdapterKey("videos")
    public Connection videos;
    @Nullable
    @GsonAdapterKey("categories")
    public Connection categories;
    @Nullable
    @GsonAdapterKey("comments")
    public Connection comments;
    @Nullable
    @GsonAdapterKey("credits")
    public Connection credits;
    @Nullable
    @GsonAdapterKey("likes")
    public Connection likes;
    @Nullable
    @GsonAdapterKey("pictures")
    public Connection pictures;
    @Nullable
    @GsonAdapterKey("texttracks")
    public Connection texttracks;
    @Nullable
    @GsonAdapterKey("activities")
    public Connection activities;
    @Nullable
    @GsonAdapterKey("albums")
    public Connection albums;
    @Nullable
    @GsonAdapterKey("channels")
    public Connection channels;
    @Nullable
    @GsonAdapterKey("moderated_channels")
    public Connection moderatedChannels;
    @Nullable
    @GsonAdapterKey("feed")
    public Connection feed;
    @Nullable
    @GsonAdapterKey("followers")
    public Connection followers;
    @Nullable
    @GsonAdapterKey("following")
    public Connection following;
    @Nullable
    @GsonAdapterKey("groups")
    public Connection groups;
    @Nullable
    @GsonAdapterKey("portfolios")
    public Connection portfolios;
    @Nullable
    @GsonAdapterKey("shared")
    public Connection shared;
    @Nullable
    @GsonAdapterKey("recommendations")
    public Connection recommendations;
    @Nullable
    @GsonAdapterKey("appearances")
    public Connection appearances;
    @Nullable
    @GsonAdapterKey("related")
    public Connection related;
    @Nullable
    @GsonAdapterKey("replies")
    public Connection replies;
    @Nullable
    @GsonAdapterKey("users")
    public Connection users;
    @Nullable
    @GsonAdapterKey("watchlater")
    public Connection watchlater;
    @Nullable
    @GsonAdapterKey("ondemand")
    public Connection ondemand;
    @Nullable
    @GsonAdapterKey("trailer")
    public Connection trailer;
    @Nullable
    @GsonAdapterKey("playback")
    public Connection playbackFailureReason;
    @Nullable
    @GsonAdapterKey("recommended_channels")
    public Connection recommendedChannels;
    @Nullable
    @GsonAdapterKey("recommended_users")
    public Connection recommendedUsers;
}
