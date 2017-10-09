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
 * A model representing the time details of the live quota for a user
 * <p>
 * Created by zetterstromk on 9/11/17.
 */
@SuppressWarnings("unused")
@UseStag
public class LiveTime implements Serializable {


    private static final long serialVersionUID = -1616885787957434435L;

    /**
     * The amount of time per event that the user is allowed to live stream.
     */
    @Nullable
    @SerializedName("event_maximum")
    private Integer mEventMaximum;

    /**
     * The amount of time this month, in seconds, that the user can live stream.
     */
    @Nullable
    @SerializedName("monthly_maximum")
    private Long mMonthlyMaximum;

    /**
     * The amount of time remaining this month, in seconds, that the user can live stream.
     */
    @Nullable
    @SerializedName("monthly_remaining")
    private Long mMonthlyRemaining;

    @Nullable
    public Integer getEventMaximum() {
        return mEventMaximum;
    }

    public void setEventMaximum(@Nullable Integer eventMaximum) {
        mEventMaximum = eventMaximum;
    }

    @Nullable
    public Long getMonthlyMaximum() {
        return mMonthlyMaximum;
    }

    public void setMonthlyMaximum(@Nullable Long monthlyMaximum) {
        mMonthlyMaximum = monthlyMaximum;
    }

    @Nullable
    public Long getMonthlyRemaining() {
        return mMonthlyRemaining;
    }

    public void setMonthlyRemaining(@Nullable Long monthlyRemaining) {
        mMonthlyRemaining = monthlyRemaining;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }

        LiveTime liveTime = (LiveTime) o;

        if (mEventMaximum != null ? !mEventMaximum.equals(liveTime.mEventMaximum) : liveTime.mEventMaximum != null) {
            return false;
        }
        if (mMonthlyMaximum != null ? !mMonthlyMaximum.equals(liveTime.mMonthlyMaximum) : liveTime.mMonthlyMaximum != null) {
            return false;
        }
        return mMonthlyRemaining != null ? mMonthlyRemaining.equals(liveTime.mMonthlyRemaining) : liveTime.mMonthlyRemaining == null;

    }

    @Override
    public int hashCode() {
        int result = mEventMaximum != null ? mEventMaximum.hashCode() : 0;
        result = 31 * result + (mMonthlyMaximum != null ? mMonthlyMaximum.hashCode() : 0);
        result = 31 * result + (mMonthlyRemaining != null ? mMonthlyRemaining.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "LiveTime{" +
               "mEventMaximum=" + mEventMaximum +
               ", mMonthlyMaximum=" + mMonthlyMaximum +
               ", mMonthlyRemaining=" + mMonthlyRemaining +
               '}';
    }
}
