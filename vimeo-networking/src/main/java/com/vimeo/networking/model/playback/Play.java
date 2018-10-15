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
import com.vimeo.networking.model.ProgressiveVideoFile;
import com.vimeo.networking.model.DashVideoFile;
import com.vimeo.networking.model.HlsVideoFile;
import com.vimeo.networking.model.playback.embed.Embed;
import com.vimeo.stag.UseStag;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Playback
 * <p>
 * Created by zetterstromk on 4/25/16.
 */
@SuppressWarnings("unused")
@UseStag
public class Play implements Serializable {

    private static final long serialVersionUID = -7429617944240759711L;

    @UseStag
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
        RESTRICTED("restricted"),
        // will require a re-request for the video, supply the password in the request
        @SerializedName("password")
        PASSWORD("password");

        @NotNull
        private final String string;

        Status(@NotNull String string) {
            this.string = string;
        }

        @Override
        public String toString() {
            return this.string;
        }
    }

    @Nullable
    @SerializedName(value = "embed", alternate = "m_embed")
    protected Embed mEmbed;

    @Nullable
    @SerializedName(value = "hls", alternate = "m_hls")
    protected HlsVideoFile mHls;

    @Nullable
    @SerializedName(value = "dash", alternate = "m_dash")
    protected DashVideoFile mDash;

    @Nullable
    @SerializedName(value = "progressive", alternate = "m_progressive")
    protected ArrayList<ProgressiveVideoFile> mProgressive;

    @Nullable
    @SerializedName(value = "progress", alternate = "m_progress")
    protected PlayProgress mProgress;

    @Nullable
    @SerializedName(value = "status", alternate = "m_status")
    protected Status mStatus;

    @Nullable
    @SerializedName(value = "drm", alternate = "m_drm")
    protected Drm mDrm;

    @Nullable
    public Embed getEmbed() {
        return mEmbed;
    }

    public void setEmbed(@Nullable Embed embed) {
        this.mEmbed = embed;
    }

    @Nullable
    public HlsVideoFile getHlsVideoFile() {
        return mHls;
    }

    @Nullable
    public DashVideoFile getDashVideoFile() {
        return mDash;
    }

    @Nullable
    public ArrayList<ProgressiveVideoFile> getProgressiveVideoFiles() {
        return mProgressive;
    }

    @Nullable
    public PlayProgress getProgress() {
        return mProgress;
    }

    public void setProgress(@Nullable PlayProgress progress) {
        this.mProgress = progress;
    }

    @Nullable
    public Drm getDrm() {
        return mDrm;
    }

    @Nullable
    public Status getStatus() {
        return mStatus;
    }

    public void setStatus(@Nullable Status status) {
        mStatus = status;
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
        return count;
    }
    // </editor-fold>
}
