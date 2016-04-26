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

package com.vimeo.networking.model.embed;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

/**
 * Embed model with access to the html iframe
 * <p/>
 * Only the html data structure will be accessible unless the requesting user owns the video.
 * <p/>
 * All other fields besides {@link #html} are here for allowing you to edit a video's embed settings
 * <p/>
 * Created by zetterstromk on 4/25/16.
 */
public class Embed implements Serializable {

    private static final long serialVersionUID = -4708548576616330795L;

    @Nullable
    private String uri;
    @Nullable
    private EmbedHtml html;
    @Nullable
    private EmbedButtons buttons;
    @Nullable
    private EmbedTitle title;
    private boolean playbar;
    private boolean volume;
    @Nullable
    private String color;

    @Nullable
    public String getUri() {
        return uri;
    }

    @Nullable
    public EmbedHtml getHtml() {
        return html;
    }

    @Nullable
    public EmbedButtons getButtons() {
        return buttons;
    }

    /**
     * @param buttons Set {@link EmbedButtons}
     */
    public void setButtons(@Nullable EmbedButtons buttons) {
        this.buttons = buttons;
    }

    @Nullable
    public EmbedTitle getTitle() {
        return title;
    }

    /**
     * @param title Set {@link EmbedTitle}
     */
    public void setTitle(@Nullable EmbedTitle title) {
        this.title = title;
    }

    public boolean isPlaybar() {
        return playbar;
    }

    /**
     * @param playbar Show or hide the playbar
     */
    public void setPlaybar(boolean playbar) {
        this.playbar = playbar;
    }

    public boolean isVolume() {
        return volume;
    }

    /**
     * @param volume Show or hide the volume selector
     */
    public void setVolume(boolean volume) {
        this.volume = volume;
    }

    @Nullable
    public String getColor() {
        return color;
    }

    /**
     * @param color A primary color used by the embed player
     */
    public void setColor(@Nullable String color) {
        this.color = color;
    }
}
