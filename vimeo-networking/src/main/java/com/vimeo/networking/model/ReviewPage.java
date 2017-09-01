/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vimeo
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
 * A model representing the review page data.
 * <p>
 * Created by zetterstromk on 8/22/17.
 */
@SuppressWarnings("unused")
@UseStag
public final class ReviewPage implements Serializable {

    private static final long serialVersionUID = -8674129392575328878L;

    @SerializedName("active")
    private boolean mActive;

    @Nullable
    @SerializedName("link")
    private String mLink;

    public boolean getActive() {
        return mActive;
    }

    public void setActive(boolean active) {
        mActive = active;
    }

    @Nullable
    public String getLink() {
        return mLink;
    }

    public void setLink(@Nullable String link) {
        mLink = link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }

        final ReviewPage that = (ReviewPage) o;

        if (mActive != that.mActive) { return false; }
        return mLink != null ? mLink.equals(that.mLink) : that.mLink == null;

    }

    @Override
    public int hashCode() {
        int result = (mActive ? 1 : 0);
        result = 31 * result + (mLink != null ? mLink.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ReviewPage{" +
               "mActive=" + mActive +
               ", mLink='" + mLink + '\'' +
               '}';
    }
}
