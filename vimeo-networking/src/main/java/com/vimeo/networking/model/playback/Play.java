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

package com.vimeo.networking.model.playback;

import com.google.gson.annotations.SerializedName;
import com.vimeo.networking.model.VideoFile;
import com.vimeo.networking.model.playback.embed.Embed;
import com.vimeo.stag.GsonAdapterKey;
import com.vimeo.stag.UseStag;
import com.vimeo.stag.UseStag.FieldOption;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Playback
 * <p/>
 * Created by zetterstromk on 4/25/16.
 */
@UseStag(FieldOption.SERIALIZED_NAME)
public class Play implements Serializable {

    private static final long serialVersionUID = -7429617944240759711L;

    public enum Status {
        // if not done transcoding
        @SerializedName("unavailable")
        UNAVAILABLE("unavailable"),
        // can be played. the video is marked as done transcoding
        @SerializedName("playable")
        PLAYABLE("playable"),
        // can be purchased. is an on demand video that the user has not purchased
        @SerializedName("purchase_required")
        PURCHASE_REQUIRED("purchase_required"),
        // your region can not play or purchase this
        @SerializedName("restricted")
        RESTRICTED("restricted");

        private final String string;

        Status(String string) {
            this.string = string;
        }

        @Override
        public String toString() {
            return this.string;
        }
    }

    @Nullable
    @GsonAdapterKey("embed")
    Embed mEmbed;

    @Nullable
    @GsonAdapterKey("hls")
    VideoFile mHls;

    @Nullable
    @GsonAdapterKey("dash")
    VideoFile mDash;

    @Nullable
    @GsonAdapterKey("progressive")
    ArrayList<VideoFile> mProgressive;

    @Nullable
    @GsonAdapterKey("progress")
    PlayProgress mProgress;

    @Nullable
    @GsonAdapterKey("status")
    Status mStatus;

    @Nullable
    @GsonAdapterKey("drm")
    Drm mDrm;

    @Nullable
    public Embed getEmbed() {
        return mEmbed;
    }

    public void setEmbed(@Nullable Embed embed) {
        mEmbed = embed;
    }

    @Nullable
    public VideoFile getHlsVideoFile() {
        return mHls;
    }

    @Nullable
    public VideoFile getDashVideoFile() {
        return mDash;
    }

    @Nullable
    public ArrayList<VideoFile> getProgressiveVideoFiles() {
        return mProgressive;
    }

    @Nullable
    public PlayProgress getProgress() {
        return mProgress;
    }

    public void setProgress(@Nullable PlayProgress progress) {
        mProgress = progress;
    }

    @Nullable
    public Drm getDrm() {
        return mDrm;
    }

    @Nullable
    public Status getStatus() {
        return mStatus;
    }

    // -----------------------------------------------------------------------------------------------------
    // Helpers
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Helpers">
    public int getFileCount() {
        int count = 0;
        if (mHls != null) {
            count++;
        }
        if (mDash != null) {
            count++;
        }
        if (mProgressive != null) {
            count += mProgressive.size();
        }
        if (mDrm != null && mDrm.getWidevine() != null) {
            count++;
        }
        if (mDrm != null && mDrm.getPlayReady() != null) {
            count++;
        }
        return count;
    }
    // </editor-fold>
}
