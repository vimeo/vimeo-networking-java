/*
 * Copyright (c) 2016 Vimeo (https://vimeo.com)
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
import com.vimeo.stag.UseStag;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

/**
 * A model representing default preferences that a user has for their videos
 * Created by zetterstromk on 1/28/16.
 */
@SuppressWarnings("unused")
@UseStag
public class VideosPreference implements Serializable {

    private static final long serialVersionUID = 1956447486226253433L;

    @Nullable
    @SerializedName(value = "privacy", alternate = "m_privacy")
    protected Privacy mPrivacy;

    @Nullable
    @SerializedName(value = "password", alternate = "m_password")
    protected String mPassword;

    /**
     * Returns the default password a user has set up for their videos
     */
    @Nullable
    public String getPassword() {
        return mPassword;
    }

    /**
     * Return the default {@link Privacy} the user has set up for their videos
     */
    @Nullable
    public Privacy getPrivacy() {
        return mPrivacy;
    }
}
