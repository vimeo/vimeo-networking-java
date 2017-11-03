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
import com.vimeo.networking.model.error.VimeoError;
import com.vimeo.stag.UseStag;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.Date;

/**
 * A model representing the live object returned by the API for live videos.
 * <p>
 * A live video that has ended will eventually be archived for playback. There will be
 * a delay between the time that the video ends, and the time that it is archived, due
 * to transcoding.
 * <p>
 * Created by zetterstromk on 8/23/17.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
@UseStag
public final class Live implements Serializable {

    private static final long serialVersionUID = 6341657287285717326L;

    public enum LiveStatus {
        ARCHIVING, // The stream has ended and is currently being archived.
        ARCHIVE_ERROR, // There was a problem archiving the stream.
        DONE, // The stream has been ended intentionally by the end user.
        PENDING, // Vimeo is working on setting up the connection.
        READY, // The RTMP url is ready to receive video content.
        STREAMING, // The stream is open and receiving content.
        STREAMING_ERROR, // The stream has been terminated by Vimeo. Vimeo may terminate a stream for many reasons.
        UNAVAILABLE, // At this state the RTMP link will be visible, but cannot yet receive the stream.
        UNKNOWN;

        @NotNull
        static LiveStatus getLiveStatusFromString(@NotNull String status) {
            switch (status.toLowerCase()) {
                case "archiving":
                    return ARCHIVING;
                case "archive_error":
                    return ARCHIVE_ERROR;
                case "done":
                    return DONE;
                case "pending":
                    return PENDING;
                case "ready":
                    return READY;
                case "streaming":
                    return STREAMING;
                case "streaming_error":
                    return STREAMING_ERROR;
                case "unavailable":
                    return UNAVAILABLE;
                default:
                    return UNKNOWN;
            }
        }
    }

    /**
     * The time this stream went live
     */
    @Nullable
    @SerializedName("active_time")
    private Date mActiveTime;

    /**
     * The time this live video was archived. Null if it has not yet happened.
     */
    @Nullable
    @SerializedName("archived_time")
    private Date mArchivedTime;

    /**
     * The time this stream ended. Null if it has not yet happened.
     */
    @Nullable
    @SerializedName("ended_time")
    private Date mEndedTime;

    /**
     * The time at which the creator intends to start streaming
     */
    @Nullable
    @SerializedName("scheduled_start_time")
    private Date mStartTime;

    /**
     * Upstream RTMP link. Send your live content to this link.
     */
    @Nullable
    @SerializedName("link")
    private String mLink;

    /**
     * Streaming key. Used in conjunction with the RTMP link.
     */
    @Nullable
    @SerializedName("key")
    private String mKey;

    /**
     * The status of the RTMP link.
     */
    @Nullable
    @SerializedName("status")
    private String mStatus;

    /**
     * If status is streaming_error, this is the reason for that error
     */
    @Nullable
    @SerializedName("streaming_error")
    private VimeoError mError;

    @Nullable
    @SerializedName("chat")
    private LiveChat mChat;

    /**
     * @return the {@link LiveChat}
     */
    @Nullable
    public LiveChat getChat() {
        return mChat;
    }

    void setChat(@Nullable LiveChat chat) {
        mChat = chat;
    }

    @Nullable
    public Date getActiveTime() {
        return mActiveTime;
    }

    public void setActiveTime(@Nullable Date activeTime) {
        mActiveTime = activeTime;
    }

    @Nullable
    public Date getArchivedTime() {
        return mArchivedTime;
    }

    public void setArchivedTime(@Nullable Date archivedTime) {
        mArchivedTime = archivedTime;
    }

    @Nullable
    public Date getEndedTime() {
        return mEndedTime;
    }

    public void setEndedTime(@Nullable Date endedTime) {
        mEndedTime = endedTime;
    }

    @Nullable
    public Date getStartTime() {
        return mStartTime;
    }

    public void setStartTime(@Nullable Date startTime) {
        mStartTime = startTime;
    }

    @Nullable
    public String getLink() {
        return mLink;
    }

    public void setLink(@Nullable String link) {
        mLink = link;
    }

    @Nullable
    public String getKey() {
        return mKey;
    }

    public void setKey(@Nullable String key) {
        mKey = key;
    }

    @Nullable
    public String getStatus() {
        return mStatus;
    }

    public void setStatus(@Nullable String status) {
        mStatus = status;
    }

    @NotNull
    public LiveStatus getLiveStatus() {
        return mStatus == null ? LiveStatus.UNKNOWN : LiveStatus.getLiveStatusFromString(mStatus);
    }

    @Nullable
    public VimeoError getError() {
        return mError;
    }

    public void setError(@Nullable VimeoError error) {
        mError = error;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }

        final Live live = (Live) o;

        if (mActiveTime != null ? !mActiveTime.equals(live.mActiveTime) : live.mActiveTime != null) { return false; }
        if (mArchivedTime != null ? !mArchivedTime.equals(live.mArchivedTime) : live.mArchivedTime != null) {
            return false;
        }
        if (mEndedTime != null ? !mEndedTime.equals(live.mEndedTime) : live.mEndedTime != null) { return false; }
        if (mStartTime != null ? !mStartTime.equals(live.mStartTime) : live.mStartTime != null) { return false; }
        if (mLink != null ? !mLink.equals(live.mLink) : live.mLink != null) { return false; }
        if (mKey != null ? !mKey.equals(live.mKey) : live.mKey != null) { return false; }
        //noinspection SimplifiableIfStatement
        if (mStatus != null ? !mStatus.equals(live.mStatus) : live.mStatus != null) { return false; }
        return mError != null ? mError.equals(live.mError) : live.mError == null;

    }

    @Override
    public int hashCode() {
        int result = mActiveTime != null ? mActiveTime.hashCode() : 0;
        result = 31 * result + (mArchivedTime != null ? mArchivedTime.hashCode() : 0);
        result = 31 * result + (mEndedTime != null ? mEndedTime.hashCode() : 0);
        result = 31 * result + (mStartTime != null ? mStartTime.hashCode() : 0);
        result = 31 * result + (mLink != null ? mLink.hashCode() : 0);
        result = 31 * result + (mKey != null ? mKey.hashCode() : 0);
        result = 31 * result + (mStatus != null ? mStatus.hashCode() : 0);
        result = 31 * result + (mError != null ? mError.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Live{" +
               "mActiveTime=" + mActiveTime +
               ", mArchivedTime=" + mArchivedTime +
               ", mEndedTime=" + mEndedTime +
               ", mStartTime=" + mStartTime +
               ", mLink='" + mLink + '\'' +
               ", mKey='" + mKey + '\'' +
               ", mStatus='" + mStatus + '\'' +
               ", mError=" + mError +
               ", mChat=" + mChat +
               '}';
    }
}
