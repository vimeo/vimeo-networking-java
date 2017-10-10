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
 * Created by zetterstromk on 9/11/17.
 */
@SuppressWarnings("unused")
@UseStag
public class LiveQuota implements Serializable {

    private static final long serialVersionUID = -2144444972795249825L;

    @Nullable
    @SerializedName("streams")
    private LiveStreamsQuota mStreams;

    @Nullable
    @SerializedName("time")
    private LiveTime mTime;

    @Nullable
    public LiveStreamsQuota getStreams() {
        return mStreams;
    }

    public void setStreams(@Nullable LiveStreamsQuota streams) {
        mStreams = streams;
    }

    @Nullable
    public LiveTime getTime() {
        return mTime;
    }

    public void setTime(@Nullable LiveTime time) {
        mTime = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }

        LiveQuota liveQuota = (LiveQuota) o;

        if (mStreams != null ? !mStreams.equals(liveQuota.mStreams) : liveQuota.mStreams != null) { return false; }
        return mTime != null ? mTime.equals(liveQuota.mTime) : liveQuota.mTime == null;

    }

    @Override
    public int hashCode() {
        int result = mStreams != null ? mStreams.hashCode() : 0;
        result = 31 * result + (mTime != null ? mTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "LiveQuota{" +
               "mStreams=" + mStreams +
               ", mTime=" + mTime +
               '}';
    }
}
