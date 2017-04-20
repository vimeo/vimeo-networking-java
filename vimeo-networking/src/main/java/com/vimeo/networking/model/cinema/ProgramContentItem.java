/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vimeo
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

package com.vimeo.networking.model.cinema;

import com.google.gson.annotations.SerializedName;
import com.vimeo.networking.model.Category;
import com.vimeo.networking.model.Channel;
import com.vimeo.networking.model.Connection;
import com.vimeo.networking.model.ConnectionCollection;
import com.vimeo.networking.model.Metadata;
import com.vimeo.networking.model.Video;
import com.vimeo.networking.model.VideoList;
import com.vimeo.stag.UseStag;
import com.vimeo.stag.UseStag.FieldOption;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the data returned from the Cinema endpoint. It contains a short list of videos as well
 * as the data object that represents its root source (e.g. category/channel).
 * <p>
 * Created by rigbergh on 3/1/17.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
@UseStag(FieldOption.SERIALIZED_NAME)
public class ProgramContentItem implements Serializable {

    private static final long serialVersionUID = -3929762661095254821L;
    private static final String S_CATEGORY = "category";
    private static final String S_CHANNEL = "channel";

    @UseStag
    public enum Type {
        @SerializedName(S_CATEGORY)
        CATEGORY(S_CATEGORY),
        @SerializedName(S_CHANNEL)
        CHANNEL(S_CHANNEL);

        private final String mType;

        Type(@NotNull String type) {
            mType = type;
        }

        @Override
        public String toString() {
            return mType;
        }
    }

    @Nullable
    @SerializedName("uri")
    protected String mUri;

    @Nullable
    @SerializedName("name")
    protected String mName;

    @Nullable
    @SerializedName("type")
    protected Type mType;

    @Nullable
    @SerializedName("content")
    protected ArrayList<Video> mVideoList;

    @Nullable
    @SerializedName("metadata")
    protected Metadata mMetadata;

    /**
     * Non-null when {@link #mType} is {@link Type#CHANNEL}
     */
    @Nullable
    @SerializedName("channel")
    protected Channel mChannel;

    /**
     * Non-null when {@link #mType} is {@link Type#CATEGORY}
     */
    @Nullable
    @SerializedName("category")
    protected Category mCategory;

    /**
     * @return The uri to the original data source (as a String)
     */
    @Nullable
    public String getUri() {
        return mUri;
    }

    /**
     * @return The display name of the content item.
     */
    @Nullable
    public String getName() {
        return mName;
    }

    /**
     * @return The {@link Type} of the content item. Use this type to determine the internal data that will
     * be available for the data source. Currently, this can be either a {@link Channel} when the type is
     * {@link Type#CHANNEL} or a {@link Category} when the type is {@link Type#CATEGORY}.
     */
    @Nullable
    public Type getType() {
        return mType;
    }

    /**
     * @return A short (currently 5 count) {@link VideoList} containing the first page of video content for the
     * content item.
     */
    @Nullable
    public List<Video> getVideoList() {
        return mVideoList;
    }

    /**
     * @return A {@link Metadata} item containing the uri for the next page of videos in
     * metadata.connections.contents.uri
     */
    @Nullable
    public Metadata getMetadata() {
        return mMetadata;
    }

    /**
     * @return A {@link Channel} when {@link #getType()} returns {@link Type#CHANNEL}, otherwise null
     */
    @Nullable
    public Channel getChannel() {
        return mChannel;
    }

    /**
     * @return A {@link Category} when {@link #getType()} returns {@link Type#CATEGORY}, otherwise null
     */
    @Nullable
    public Category getCategory() {
        return mCategory;
    }

    /**
     * @return a uri (as a String) to the next page of data after the videos returned from {@link #getVideoList}
     */
    @Nullable
    public String getNextPageUri() {

        ConnectionCollection connections = mMetadata != null ? mMetadata.getConnections() : null;
        Connection contents = connections != null ? connections.getContents() : null;

        return contents != null ? contents.getUri() : null;
    }

}
