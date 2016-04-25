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
 * Logo data contained in {@link Embed} objects. These are here to allow you to edit a video's embed settings.
 * <p/>
 * Created by zetterstromk on 4/25/16.
 */
public class EmbedLogos implements Serializable {

    private static final long serialVersionUID = -6946192139468749658L;
    private boolean vimeo;
    @Nullable
    private EmbedCustomLogos custom;

    public boolean isVimeo() {
        return vimeo;
    }

    /**
     * @param vimeo Show or hide the vimeo logo
     */
    public void setVimeo(boolean vimeo) {
        this.vimeo = vimeo;
    }

    @Nullable
    public EmbedCustomLogos getCustom() {
        return custom;
    }

    /**
     * @param custom {@link EmbedCustomLogos}
     */
    public void setCustom(@Nullable EmbedCustomLogos custom) {
        this.custom = custom;
    }
}
