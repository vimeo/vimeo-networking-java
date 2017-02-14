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

package com.vimeo.networking.model.playback.embed;

import com.google.gson.annotations.SerializedName;
import com.vimeo.stag.UseStag;

import java.io.Serializable;

/**
 * Buttons that can be contained in the {@link Embed} object. These are here to allow you to edit a video's
 * embed settings.
 * <p/>
 * Created by zetterstromk on 4/25/16.
 */
@SuppressWarnings("unused")
@UseStag
public class EmbedButtons implements Serializable {

    private static final long serialVersionUID = 6724361702326756097L;

    protected boolean like;

    @SerializedName("watchlater")
    protected boolean watchLater;

    protected boolean share;

    protected boolean embed;

    protected boolean hd;

    protected boolean fullscreen;

    protected boolean scaling;

    public boolean isLike() {
        return like;
    }

    /**
     * @param like Show or hide the like button
     */
    public void setLike(boolean like) {
        this.like = like;
    }

    public boolean isWatchLater() {
        return watchLater;
    }

    /**
     * @param watchlater Show or hide the watch later button
     */
    public void setWatchLater(boolean watchlater) {
        this.watchLater = watchlater;
    }

    public boolean isShare() {
        return share;
    }

    /**
     * @param share Show or hide the share button
     */
    public void setShare(boolean share) {
        this.share = share;
    }

    public boolean isEmbed() {
        return embed;
    }

    /**
     * @param embed Show or hide the embed button
     */
    public void setEmbed(boolean embed) {
        this.embed = embed;
    }

    public boolean isHd() {
        return hd;
    }

    /**
     * @param hd Show or hide the hd button
     */
    public void setHd(boolean hd) {
        this.hd = hd;
    }

    public boolean isFullscreen() {
        return fullscreen;
    }

    /**
     * @param fullscreen Show or hide the fullscreen button
     */
    public void setFullscreen(boolean fullscreen) {
        this.fullscreen = fullscreen;
    }

    public boolean isScaling() {
        return scaling;
    }

    /**
     * @param scaling Show or hide the scaling button (shown only in fullscreen mode)
     */
    public void setScaling(boolean scaling) {
        this.scaling = scaling;
    }
}
