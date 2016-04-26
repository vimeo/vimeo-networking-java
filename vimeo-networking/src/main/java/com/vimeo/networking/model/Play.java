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

package com.vimeo.networking.model;

import com.google.gson.annotations.SerializedName;
import com.vimeo.networking.model.embed.Embed;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Playback
 * <p/>
 * Created by zetterstromk on 4/25/16.
 */
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

        private String string;

        Status(String string) {
            this.string = string;
        }

        @Override
        public String toString() {
            return this.string;
        }
    }

    @Nullable
    private Embed embed;
    @Nullable
    private VideoFile hls;
    @Nullable
    private VideoFile dash;
    @Nullable
    private ArrayList<VideoFile> progressive;
    @Nullable
    private PlayProgress progress;
    @Nullable
    private Status status;

    @Nullable
    public Embed getEmbed() {
        return embed;
    }

    public void setEmbed(@Nullable Embed embed) {
        this.embed = embed;
    }

    @Nullable
    public VideoFile getHlsVideoFile() {
        return hls;
    }

    @Nullable
    public VideoFile getDashVideoFile() {
        return dash;
    }

    @Nullable
    public ArrayList<VideoFile> getProgressiveVideoFiles() {
        return progressive;
    }

    @Nullable
    public PlayProgress getProgress() {
        return progress;
    }

    public void setProgress(@Nullable PlayProgress progress) {
        this.progress = progress;
    }

    @Nullable
    public Status getStatus() {
        return status;
    }

    // -----------------------------------------------------------------------------------------------------
    // Helpers
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Helpers">
    public int getFileCount() {
        int count = 0;
        if (getHlsVideoFile() != null) {
            count++;
        }
        if (getDashVideoFile() != null) {
            count++;
        }
        if (getProgressiveVideoFiles() != null) {
            count += getProgressiveVideoFiles().size();
        }
        return count;
    }
    // </editor-fold>
}
