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

package com.vimeo.networking.model.live;

import com.google.gson.annotations.SerializedName;
import com.vimeo.stag.UseStag;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

/**
 * A model representing the streams details of the live quota for a user
 * <p>
 * Created by zetterstromk on 9/11/17.
 */
@SuppressWarnings("unused")
@UseStag
public class LiveStreamsQuota implements Serializable {

    private static final long serialVersionUID = 9008379903145719978L;

    /**
     * The maximum amount of streams that the user can create.
     */
    @Nullable
    @SerializedName("maximum")
    private Integer mMaximum;

    /**
     * The amount of remaining live streams that the user can create this month.
     */
    @Nullable
    @SerializedName("remaining")
    private Integer mRemaining;

    @Nullable
    public Integer getMaximum() {
        return mMaximum;
    }

    public void setMaximum(@Nullable Integer maximum) {
        mMaximum = maximum;
    }

    @Nullable
    public Integer getRemaining() {
        return mRemaining;
    }

    public void setRemaining(@Nullable Integer remaining) {
        mRemaining = remaining;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }

        LiveStreamsQuota that = (LiveStreamsQuota) o;

        if (mMaximum != null ? !mMaximum.equals(that.mMaximum) : that.mMaximum != null) { return false; }
        return mRemaining != null ? mRemaining.equals(that.mRemaining) : that.mRemaining == null;

    }

    @Override
    public int hashCode() {
        int result = mMaximum != null ? mMaximum.hashCode() : 0;
        result = 31 * result + (mRemaining != null ? mRemaining.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "LiveStreamsQuota{" +
               "mMaximum=" + mMaximum +
               ", mRemaining=" + mRemaining +
               '}';
    }
}
