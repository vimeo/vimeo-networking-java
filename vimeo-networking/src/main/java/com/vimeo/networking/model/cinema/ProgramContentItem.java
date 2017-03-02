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
import com.vimeo.networking.model.Metadata;
import com.vimeo.networking.model.Video;
import com.vimeo.networking.model.VideoList;
import com.vimeo.stag.UseStag;
import com.vimeo.stag.UseStag.FieldOption;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class represents the data returned from the Cinema endpoint. It contains a short list of videos as well
 * as the data object that represents its root source (e.g. category/channel)
 * Created by rigbergh on 3/1/17.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
@UseStag(FieldOption.SERIALIZED_NAME)
public class ProgramContentItem implements Serializable {

    private static final long serialVersionUID = -3929762661095254821L;
    private static final String S_CATEGORY = "category";
    private static final String S_CHANNEL = "channel";
    private static final String KEY_PAGE = "page";
    private static final String KEY_PER_PAGE = "per_page";
    private static final String PAGE_ONE = "1";
    private static final String QUERY_DELIM = "?";
    private static final String QUERY_PARAM_DELIM = "&";
    private static final String QUERY_KEY_VAL_DELIM = "=";

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
        if (mMetadata != null && mMetadata.connections != null && mMetadata.connections.contents != null) {
            return mMetadata.connections.contents.getUri();
        }
        return null;
    }

    /**
     * Returns a uri (as a String) to the requested page of video content of the requested sized number of items.
     *
     * @param pageSize   A number (> 0) representing the size of the first page to retrieve. If the number is <= 0,
     *                   a default value (currently 5) will be used.
     * @param pageNumber A number (> 0) representing the page to retrieve. If the number is <= 0, the default
     *                   value (currently 2) will be used.
     * @return a uri (as a String) to the first page or null if a page is not available
     */
    @Nullable
    public String getPageUri(int pageSize, int pageNumber) {
        String page = getNextPageUri();
        if (page != null) {
            URI pageUri;
            try {
                pageUri = URI.create(page);
            } catch (IllegalArgumentException iae) {
                return null;
            }
            if (pageSize > 0 && pageNumber > 0) {
                String path = pageUri.getPath();
                String queryString = pageUri.getQuery();
                if (queryString != null && !queryString.isEmpty()) {
                    String[] query = queryString.split(QUERY_PARAM_DELIM);
                    for (int i = 0; i < query.length; i++) {
                        String[] keyValSplit = query[i].split(QUERY_KEY_VAL_DELIM);
                        if (keyValSplit.length == 2) {
                            if (pageNumber > 0 && KEY_PAGE.equalsIgnoreCase(keyValSplit[0])) {
                                keyValSplit[1] = PAGE_ONE;
                            } else if (pageSize > 0 && KEY_PER_PAGE.equalsIgnoreCase(keyValSplit[0])) {
                                keyValSplit[1] = Integer.toString(pageSize);
                            }
                        }
                        query[i] = join(keyValSplit, QUERY_KEY_VAL_DELIM);
                    }
                    page = path + QUERY_DELIM + join(query, QUERY_PARAM_DELIM);
                }
            }
        }
        return page;
    }

    /**
     * Joins an array of Strings and inserts a delimiter between each sequential String when they are joined.
     * Performs the opposite of String.split. This is necessary to avoid bringing in another library in this context.
     *
     * @param fields    an array of String fields to join
     * @param delimiter the String delimiter to place between each String when they are joined
     * @return a joined String
     */
    @NotNull
    private static String join(@NotNull String[] fields, @NotNull String delimiter) {
        return Arrays.toString(fields).replace(", ", delimiter).replaceAll("[\\[\\]]", "");
    }

}
