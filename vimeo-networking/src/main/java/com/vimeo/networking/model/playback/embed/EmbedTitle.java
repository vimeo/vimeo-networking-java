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
import com.vimeo.stag.UseStag.FieldOption;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

/**
 * Title representation of an {@link Embed} object. These are here to allow you to edit a video's embed settings.
 * <p/>
 * Created by zetterstromk on 4/25/16.
 */
@UseStag(FieldOption.SERIALIZED_NAME)
public class EmbedTitle implements Serializable {

    private static final long serialVersionUID = -2543724237726304625L;
    @Nullable
    @SerializedName("name")
    String mName;
    @Nullable
    @SerializedName("owner")
    String mOwner;
    @Nullable
    @SerializedName("portrait")
    String mPortrait;

    @Nullable
    public String getName() {
        return mName;
    }

    /**
     * @param name Show, hide, or let the user decide if the video title shows on the video
     */
    public void setName(@Nullable String name) {
        this.mName = name;
    }

    @Nullable
    public String getOwner() {
        return mOwner;
    }

    /**
     * @param owner Show, hide, or let the user decide if the owners information shows on the video
     */
    public void setOwner(@Nullable String owner) {
        this.mOwner = owner;
    }

    @Nullable
    public String getPortrait() {
        return mPortrait;
    }

    /**
     * @param portrait Show, hide, or let the user decide if the owners portrait shows on the video
     */
    public void setPortrait(@Nullable String portrait) {
        this.mPortrait = portrait;
    }
}
