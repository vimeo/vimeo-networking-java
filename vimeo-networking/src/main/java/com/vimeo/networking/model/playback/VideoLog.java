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

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Logging endpoints. This class is NOT consumable by the general public.
 * <p/>
 * Created by alfredhanssen on 4/25/15.
 */
public class VideoLog implements Serializable {

    private static final long serialVersionUID = -4646869969374079276L;
    // URL for the "load" event
    private String loadLink;

    // URL for the "play" event
    private String playLink;

    // URL for the "like_press_link" event
    @SerializedName("like_press_link")
    private String likeLink;

    // URL for the "watchlater_press_link" event
    @SerializedName("watchlater_press_link")
    private String watchLaterLink;

    public String getLoadLoggingUrl() {
        return loadLink;
    }

    public boolean isLoadEmpty() {
        return loadLink == null || loadLink.trim().isEmpty();
    }

    public boolean isPlayEmpty() {
        return playLink == null || playLink.trim().isEmpty();
    }

    public String getPlayLoggingUrl() {
        return playLink;
    }

    public boolean isLikeEmpty() {
        return likeLink == null || likeLink.trim().isEmpty();
    }

    public String getLikeLoggingUrl() {
        return likeLink;
    }

    public boolean isWatchLaterEmpty() {
        return watchLaterLink == null || watchLaterLink.trim().isEmpty();
    }

    public String getWatchLaterLoggingUrl() {
        return watchLaterLink;
    }
}