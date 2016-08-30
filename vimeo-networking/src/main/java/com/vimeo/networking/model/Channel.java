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
import java.util.Date;

/**
 * Created by zetterstromk on 6/11/15.
 */
public class Channel implements Serializable, Followable {

    private static final long serialVersionUID = 3190410523525111858L;

    @GsonAdapterKey("uri")
    public String uri;
    @GsonAdapterKey("name")
    public String name;
    @GsonAdapterKey("description")
    public String description;
    @GsonAdapterKey("link")
    public String link;
    @GsonAdapterKey("created_time")
    public Date createdTime;
    @GsonAdapterKey("modified_time")
    public Date modifiedTime;
    @GsonAdapterKey("user")
    public User user;
    @GsonAdapterKey("pictures")
    public PictureCollection pictures;
    @GsonAdapterKey("header")
    public PictureCollection header;
    @GsonAdapterKey("privacy")
    public Privacy privacy;
    @GsonAdapterKey("metadata")
    public Metadata metadata;

    @Nullable
    public Connection getUsersConnection() {
        if (metadata != null && metadata.connections != null && metadata.connections.users != null) {
            return metadata.connections.users;
        }
        return null;
    }

    @Nullable
    @Override
    public Interaction getFollowInteraction() {
        if (metadata != null && metadata.interactions != null && metadata.interactions.follow != null) {
            return metadata.interactions.follow;
        }
        return null;
    }

    @Override
    public boolean canFollow() {
        return getFollowInteraction() != null;
    }

    @Override
    public boolean isFollowing() {
        if (getFollowInteraction() != null) {
            return metadata.interactions.follow.added;
        }
        return false;
    }

    public int getFollowerCount() {
        if (getUsersConnection() != null) {
            return getUsersConnection().total;
        }
        return 0;
    }

    @Nullable
    public Connection getVideosConnection() {
        if (metadata != null && metadata.connections != null && metadata.connections.videos != null) {
            return metadata.connections.videos;
        }
        return null;
    }

    public int getVideoCount() {
        if (getVideosConnection() != null) {
            return getVideosConnection().total;
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Channel that = (Channel) o;

        return ((this.uri != null && that.uri != null) ? this.uri.equals(that.uri) : false);
    }

    @Override
    public int hashCode() {
        return this.uri != null ? this.uri.hashCode() : 0;
    }
}
