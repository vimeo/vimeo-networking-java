/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 Vimeo
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

package com.vimeo.networking.model.search;

import com.google.gson.annotations.SerializedName;
import com.vimeo.networking.model.Channel;
import com.vimeo.networking.model.Group;
import com.vimeo.networking.model.User;
import com.vimeo.networking.model.Video;
import com.vimeo.networking.model.tvod.TvodItem;
import com.vimeo.stag.UseStag;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

/**
 * An individual search response item
 * <p>
 * Created by zetterstromk on 6/27/16.
 */
@SuppressWarnings("unused")
@UseStag
public class SearchResult implements Serializable {

    private static final long serialVersionUID = -1607389617833091383L;

    @SerializedName("is_staffpick")
    protected boolean mIsStaffPick;

    @SerializedName("is_featured")
    protected boolean mIsFeatured;

    @SerializedName("type")
    protected SearchType mSearchType;

    /**
     * Non-null when {@link #mSearchType} is {@link SearchType#VIDEO}
     */
    @Nullable
    @SerializedName("clip")
    protected Video mVideo;

    /**
     * Non-null when {@link #mSearchType} is {@link SearchType#USER}
     */
    @Nullable
    @SerializedName("people")
    protected User mUser;

    /**
     * Non-null when {@link #mSearchType} is {@link SearchType#CHANNEL}
     */
    @Nullable
    @SerializedName("channel")
    protected Channel mChannel;

    /**
     * Non-null when {@link #mSearchType} is {@link SearchType#GROUP}
     */
    @Nullable
    @SerializedName("group")
    protected Group mGroup;

    /**
     * Non-null when {@link #mSearchType} is {@link SearchType#TVOD}
     */
    @Nullable
    @SerializedName("ondemand")
    protected TvodItem mTvod;

    public boolean isStaffPick() {
        return mIsStaffPick;
    }

    public boolean isFeatured() {
        return mIsFeatured;
    }

    public SearchType getSearchType() {
        return mSearchType;
    }

    @Nullable
    public Video getVideo() {
        return mVideo;
    }

    @Nullable
    public User getUser() {
        return mUser;
    }

    @Nullable
    public Channel getChannel() {
        return mChannel;
    }

    @Nullable
    public Group getGroup() {
        return mGroup;
    }

    @Nullable
    public TvodItem getTvod() {
        return mTvod;
    }
}
