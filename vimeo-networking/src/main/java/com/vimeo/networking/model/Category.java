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
import java.util.ArrayList;

/**
 * Created by zetterstromk on 8/20/15.
 */
@SuppressWarnings("unused")
@UseStag
public class Category implements Serializable, Followable {

    private static final long serialVersionUID = 441419347585215353L;

    @Nullable
    public String uri;

    @Nullable
    public String name;

    @Nullable
    public String link;

    @SerializedName("top_level")
    public boolean topLevel;

    @Nullable
    public PictureCollection pictures;

    @Nullable
    protected PictureCollection icon;

    @Nullable
    public ArrayList<Category> subcategories;

    @Nullable
    public Category parent;

    @Nullable
    public Metadata metadata;

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
        Interaction interaction = getFollowInteraction();
        return interaction != null && interaction.added;
    }

    @Nullable
    public Connection getUserConnection() {
        if (metadata != null && metadata.connections != null && metadata.connections.users != null) {
            return metadata.connections.users;
        }
        return null;
    }

    public int getFollowerCount() {
        if (getUserConnection() != null) {
            return getUserConnection().total;
        }
        return 0;
    }

    /**
     * Returns the icon associated with this category, in the form of a {@link PictureCollection}
     */
    @Nullable
    public PictureCollection getIcon() {
        return icon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Category that = (Category) o;

        return ((this.uri != null && that.uri != null) && this.uri.equals(that.uri));
    }

    @Override
    public int hashCode() {
        return this.uri != null ? this.uri.hashCode() : 0;
    }
}
