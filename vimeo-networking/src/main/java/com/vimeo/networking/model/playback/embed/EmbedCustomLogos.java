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
 * Custom field in {@link EmbedLogos}. These are here to allow you to edit a video's embed settings.
 * <p>
 * Created by zetterstromk on 4/25/16.
 */
@SuppressWarnings("unused")
@UseStag
public class EmbedCustomLogos implements Serializable {

    private static final long serialVersionUID = -8919686101651093878L;

    @SerializedName("active")
    protected boolean mActive;

    @Nullable
    @SerializedName("link")
    protected String mLink;

    @SerializedName("sticky")
    protected boolean mSticky;

    public boolean isActive() {
        return mActive;
    }

    /**
     * @param active Show or hide your custom logo
     */
    public void setActive(boolean active) {
        this.mActive = active;
    }

    @Nullable
    public String getLink() {
        return mLink;
    }

    /**
     * @param link A url that your user will navigate to if they click your custom logo
     */
    public void setLink(@Nullable String link) {
        this.mLink = link;
    }

    public boolean isSticky() {
        return mSticky;
    }

    /**
     * @param sticky Always show the custom logo, or hide it after time with the rest of the UI
     */
    public void setSticky(boolean sticky) {
        this.mSticky = sticky;
    }
}
