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

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

/**
 * Embed model with access to the html iframe
 * <p>
 * Only the html data structure will be accessible unless the requesting user owns the video.
 * <p>
 * All other fields besides {@link #mHtml} are here for allowing you to edit a video's embed settings
 * <p>
 * Created by zetterstromk on 4/25/16.
 */
@SuppressWarnings("unused")
@UseStag
public class Embed implements Serializable {

    private static final long serialVersionUID = -4708548576616330795L;

    @Nullable
    @SerializedName("uri")
    protected String mUri;

    @Nullable
    @SerializedName("html")
    protected EmbedHtml mHtml;

    @Nullable
    @SerializedName("buttons")
    protected EmbedButtons mButtons;

    @Nullable
    @SerializedName("title")
    protected EmbedTitle mTitle;

    @SerializedName("playbar")
    protected boolean mPlayBar;

    @SerializedName("volume")
    protected boolean mVolume;

    @Nullable
    @SerializedName("color")
    protected String mColor;

    @Nullable
    public String getUri() {
        return mUri;
    }

    @Nullable
    public EmbedHtml getHtml() {
        return mHtml;
    }

    @Nullable
    public EmbedButtons getButtons() {
        return mButtons;
    }

    /**
     * @param buttons Set {@link EmbedButtons}
     */
    public void setButtons(@Nullable EmbedButtons buttons) {
        this.mButtons = buttons;
    }

    @Nullable
    public EmbedTitle getTitle() {
        return mTitle;
    }

    /**
     * @param title Set {@link EmbedTitle}
     */
    public void setTitle(@Nullable EmbedTitle title) {
        this.mTitle = title;
    }

    public boolean isPlayBar() {
        return mPlayBar;
    }

    /**
     * @param playbar Show or hide the play bar
     */
    public void setPlayBar(boolean playbar) {
        this.mPlayBar = playbar;
    }

    public boolean isVolume() {
        return mVolume;
    }

    /**
     * @param volume Show or hide the volume selector
     */
    public void setVolume(boolean volume) {
        this.mVolume = volume;
    }

    @Nullable
    public String getColor() {
        return mColor;
    }

    /**
     * @param color A primary color used by the embed player
     */
    public void setColor(@Nullable String color) {
        this.mColor = color;
    }
}
