/*
 * Copyright (c) 2017 Vimeo (https://vimeo.com)
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

package com.vimeo.networking.model.appconfiguration.live;

import com.google.gson.annotations.SerializedName;
import com.vimeo.stag.UseStag;
import com.vimeo.stag.UseStag.FieldOption;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

/**
 * This object stores information related to the expected client heartbeat that
 * is sent up to the server from the player while a live video is playing.
 * <p>
 * Created by rigbergh on 10/2/17.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
@UseStag(FieldOption.SERIALIZED_NAME)
public class LiveHeartbeatConfiguration implements Serializable {

    private static final long serialVersionUID = 1623467664638069314L;

    @Nullable
    @SerializedName("interval")
    private Integer mInterval;

    @Nullable
    @SerializedName("enabled")
    private Boolean mIsEnabled;

    /**
     * @return a {@code boolean} indicating whether the client should send heartbeat data from the player
     * to the api while live videos are playing
     */
    @Nullable
    public Boolean getIsEnabled() {
        return mIsEnabled;
    }

    /**
     * @return the recommended interval time (in seconds) for sending heartbeat data to the api from the
     * player while a live video is playing
     */
    @Nullable
    public Integer getInterval() {
        return mInterval;
    }

    void setIsEnabled(@Nullable Boolean enabled) {
        mIsEnabled = enabled;
    }

    void setInterval(@Nullable Integer interval) {
        mInterval = interval;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }

        final LiveHeartbeatConfiguration that = (LiveHeartbeatConfiguration) o;

        //noinspection SimplifiableIfStatement
        if (mInterval != null ? !mInterval.equals(that.mInterval) : that.mInterval != null) { return false; }
        return mIsEnabled != null ? mIsEnabled.equals(that.mIsEnabled) : that.mIsEnabled == null;

    }

    @Override
    public int hashCode() {
        int result = mInterval != null ? mInterval.hashCode() : 0;
        result = 31 * result + (mIsEnabled != null ? mIsEnabled.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "LiveHeartbeatConfiguration{" +
               "mInterval=" + mInterval +
               ", mIsEnabled=" + mIsEnabled +
               '}';
    }
}
