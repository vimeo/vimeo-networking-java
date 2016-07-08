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

package com.vimeo.networking.model.playback;

import com.vimeo.stag.GsonAdapterKey;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

/**
 * Logging endpoints. This class is NOT consumable by the general public.
 * <p/>
 * Created by alfredhanssen on 4/25/15.
 */
public class VideoLog implements Serializable {

    private static final long serialVersionUID = -4646869969374079276L;
    // URL for the "load" event
    @GsonAdapterKey("load_link")
    public String mLoadLink;

    // URL for the "play" event
    @GsonAdapterKey("play_link")
    public String mPlayLink;

    // URL for the "exit" event, used for drm purposes - should be used when a drm video stops playback
    @Nullable
    @GsonAdapterKey("exit_link")
    public String mExitLink;

    // URL for the "like_press_link" event
    @GsonAdapterKey("like_press_link")
    public String mLikePressLink;

    // URL for the "watchlater_press_link" event
    @GsonAdapterKey("watchlater_press_link")
    public String mWatchLaterPressLink;

    public String getLoadLoggingUrl() {
        return mLoadLink;
    }

    public boolean isLoadEmpty() {
        return isStringEmpty(mLoadLink);
    }

    public boolean isPlayEmpty() {
        return isStringEmpty(mPlayLink);
    }

    public String getPlayLoggingUrl() {
        return mPlayLink;
    }

    public boolean isExitLinkEmpty() {
        return isStringEmpty(mExitLink);
    }

    @Nullable
    public String getExitLoggingUrl() {
        return mExitLink;
    }

    public boolean isLikeEmpty() {
        return isStringEmpty(mLikePressLink);
    }

    public String getLikeLoggingUrl() {
        return mLikePressLink;
    }

    public boolean isWatchLaterEmpty() {
        return isStringEmpty(mWatchLaterPressLink);
    }

    public String getWatchLaterLoggingUrl() {
        return mWatchLaterPressLink;
    }

    private static boolean isStringEmpty(@Nullable String string) {
        return string == null || string.trim().isEmpty();
    }
}