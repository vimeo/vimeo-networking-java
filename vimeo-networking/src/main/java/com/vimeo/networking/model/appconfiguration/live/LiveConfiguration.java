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

/**
 * An object that contains information about the configuration of features related to live video playback.
 * Created by rigbergh on 10/2/17.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
@UseStag(FieldOption.SERIALIZED_NAME)
public class LiveConfiguration {

    @Nullable
    @SerializedName("heartbeat")
    private LiveHeartbeatConfiguration mLiveHeartbeatConfiguration;

    @Nullable
    @SerializedName("chat")
    private LiveChatConfiguration mLiveChatConfiguration;

    /**
     * @return the {@link LiveHeartbeatConfiguration} (if available) for configuring the live video heartbeat
     */
    @Nullable
    public LiveHeartbeatConfiguration getLiveHeartbeatConfiguration() {
        return mLiveHeartbeatConfiguration;
    }

    /**
     * @return the {@link LiveChatConfiguration} for setting up live chat capabilities during a live video
     * broadcast (if available)
     */
    @Nullable
    public LiveChatConfiguration getLiveChatConfiguration() {
        return mLiveChatConfiguration;
    }

    void setLiveHeartbeatConfiguration(@Nullable LiveHeartbeatConfiguration liveConfiguration) {
        mLiveHeartbeatConfiguration = liveConfiguration;
    }

    void setLiveChatConfiguration(@Nullable LiveChatConfiguration liveChatConfiguration) {
        mLiveChatConfiguration = liveChatConfiguration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }

        final LiveConfiguration that = (LiveConfiguration) o;

        //noinspection SimplifiableIfStatement
        if (mLiveHeartbeatConfiguration != null ?
                !mLiveHeartbeatConfiguration.equals(that.mLiveHeartbeatConfiguration) :
                that.mLiveHeartbeatConfiguration != null) {
            return false;
        }
        return mLiveChatConfiguration != null ?
                mLiveChatConfiguration.equals(that.mLiveChatConfiguration) : that.mLiveChatConfiguration == null;

    }

    @Override
    public int hashCode() {
        int result = mLiveHeartbeatConfiguration != null ? mLiveHeartbeatConfiguration.hashCode() : 0;
        result = 31 * result + (mLiveChatConfiguration != null ? mLiveChatConfiguration.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "LiveConfiguration{" +
               "mLiveHeartbeatConfiguration=" + mLiveHeartbeatConfiguration +
               ", mLiveChatConfiguration=" + mLiveChatConfiguration +
               '}';
    }
}
