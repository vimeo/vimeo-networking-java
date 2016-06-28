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

import com.vimeo.networking.model.Channel;
import com.vimeo.networking.model.Group;
import com.vimeo.networking.model.User;
import com.vimeo.networking.model.Video;
import com.vimeo.networking.model.VodItem;
import com.vimeo.stag.GsonAdapterKey;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

/**
 * An individual search response item
 * <p/>
 * Created by zetterstromk on 6/27/16.
 */
public class SearchResult implements Serializable {

    private static final long serialVersionUID = -1607389617833091383L;

    @GsonAdapterKey("is_staffpick")
    public boolean mIsStaffPick;
    @GsonAdapterKey("is_featured")
    public boolean mIsFeatured;
    @GsonAdapterKey("type")
    public SearchType mSearchType;

    /**
     * Non-null when {@link #mSearchType} is {@link SearchType#VIDEO}
     */
    @Nullable
    @GsonAdapterKey("clip")
    public Video mVideo;

    /**
     * Non-null when {@link #mSearchType} is {@link SearchType#USER}
     */
    @Nullable
    @GsonAdapterKey("people")
    public User mUser;

    /**
     * Non-null when {@link #mSearchType} is {@link SearchType#CHANNEL}
     */
    @Nullable
    @GsonAdapterKey("channel")
    public Channel mChannel;

    /**
     * Non-null when {@link #mSearchType} is {@link SearchType#GROUP}
     */
    @Nullable
    @GsonAdapterKey("group")
    public Group mGroup;

    /**
     * Non-null when {@link #mSearchType} is {@link SearchType#VOD}
     */
    @Nullable
    @GsonAdapterKey("ondemand")
    public VodItem mVod;

}
