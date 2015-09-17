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

import java.io.Serializable;

/**
 * Created by alfredhanssen on 4/25/15.
 */
public class VideoLog implements Serializable {

    private static final long serialVersionUID = -4646869969374079276L;
    // URL for the "load" event
    public String load;

    // URL for the "play" event
    public String play;

    // URL for the "like_press" event
    @SerializedName("like_press")
    public String like;

    // URL for the "watchlater_press" event
    @SerializedName("watchlater_press")
    public String watchLater;

    public boolean isLoadEmpty() {
        return load == null || load.trim().isEmpty();
    }

    public boolean isPlayEmpty() {
        return play == null || play.trim().isEmpty();
    }

    public boolean isLikeEmpty() {
        return like == null || like.trim().isEmpty();
    }

    public boolean isWatchLaterEmpty() {
        return watchLater == null || watchLater.trim().isEmpty();
    }
}