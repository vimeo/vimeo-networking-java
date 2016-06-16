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

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.vimeo.networking.logging.ClientLogger;
import com.vimeo.networking.model.VideoFile;
import com.vimeo.networking.model.VideoFile.VideoFileTypeAdapter;
import com.vimeo.networking.model.playback.embed.Embed;
import com.vimeo.networking.utils.EnumTypeAdapter;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
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
    @SerializedName("embed")
    private Embed mEmbed;
    @Nullable
    @SerializedName("hls")
    private VideoFile mHls;
    @Nullable
    @SerializedName("dash")
    private VideoFile mDash;
    @Nullable
    @SerializedName("progressive")
    private ArrayList<VideoFile> mProgressive;
    @Nullable
    @SerializedName("progress")
    private PlayProgress mProgress;
    @Nullable
    @SerializedName("status")
    private Status mStatus;

    @Nullable
    public Embed getEmbed() {
        return mEmbed;
    }

    public void setEmbed(@Nullable Embed embed) {
        this.mEmbed = embed;
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
        this.mProgress = progress;
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


    @Nullable
    public String getLoadLoggingUrl() {
        if (getHlsVideoFile() != null && getHlsVideoFile().getLog() != null &&
            !getHlsVideoFile().getLog().isLoadEmpty()) {
            return getHlsVideoFile().getLog().getLoadLoggingUrl();
        } else if (getDashVideoFile() != null &&
                   getDashVideoFile().getLog() != null && !getDashVideoFile().getLog().isLoadEmpty()) {
            return getDashVideoFile().getLog().getLoadLoggingUrl();
        } else if (getProgressiveVideoFiles() != null) {
            for (VideoFile videoFile : getProgressiveVideoFiles()) {
                if (videoFile.getLog() != null && !videoFile.getLog().isLoadEmpty()) {
                    return videoFile.getLog().getLoadLoggingUrl();
                }
            }
        }
        return null;
    }

    @Nullable
    public String getLikeLoggingUrl() {
        if (getHlsVideoFile() != null && getHlsVideoFile().getLog() != null &&
            !getHlsVideoFile().getLog().isLikeEmpty()) {
            return getHlsVideoFile().getLog().getLikeLoggingUrl();
        } else if (getDashVideoFile() != null &&
                   getDashVideoFile().getLog() != null && !getDashVideoFile().getLog().isLikeEmpty()) {
            return getDashVideoFile().getLog().getLikeLoggingUrl();
        } else if (getProgressiveVideoFiles() != null) {
            for (VideoFile videoFile : getProgressiveVideoFiles()) {
                if (videoFile.getLog() != null && !videoFile.getLog().isLikeEmpty()) {
                    return videoFile.getLog().getLikeLoggingUrl();
                }
            }
        }
        return null;
    }

    @Nullable
    public String getWatchLaterLoggingUrl() {
        if (getHlsVideoFile() != null && getHlsVideoFile().getLog() != null &&
            !getHlsVideoFile().getLog().isWatchLaterEmpty()) {
            return getHlsVideoFile().getLog().getWatchLaterLoggingUrl();
        } else if (getDashVideoFile() != null &&
                   getDashVideoFile().getLog() != null && !getDashVideoFile().getLog().isWatchLaterEmpty()) {
            return getDashVideoFile().getLog().getWatchLaterLoggingUrl();
        } else if (getProgressiveVideoFiles() != null) {
            for (VideoFile videoFile : getProgressiveVideoFiles()) {
                if (videoFile.getLog() != null && !videoFile.getLog().isWatchLaterEmpty()) {
                    return videoFile.getLog().getWatchLaterLoggingUrl();
                }
            }
        }
        return null;
    }

    @Nullable
    private VideoLog getVideoLogForFile(@Nullable VideoFile videoFile) {
        return videoFile != null ? videoFile.getLog() : null;
    }
    // </editor-fold>

    public static class PlayTypeAdapter extends TypeAdapter<Play> {

        private final static EnumTypeAdapter<Status> sStatusEnumTypeAdapter =
                new EnumTypeAdapter<>(Status.class);

        @NotNull
        private final VideoFileTypeAdapter mVideoFileTypeAdapter;

        public PlayTypeAdapter(@NotNull VideoFileTypeAdapter videoFileTypeAdapter) {
            mVideoFileTypeAdapter = videoFileTypeAdapter;
        }

        @Override
        public void write(JsonWriter out, Play value) throws IOException {
            out.beginObject();
            if (value == null) {
                ClientLogger.d("Comment null in write()");
                out.endObject();
                return;
            }
            if (value.mHls != null) {
                out.name("hls");
                mVideoFileTypeAdapter.write(out, value.mHls);
            }
            if (value.mDash != null) {
                out.name("dash");
                mVideoFileTypeAdapter.write(out, value.mDash);
            }
            if (value.mProgressive != null) {
                out.name("progressive").beginArray();
                for (final VideoFile videoFile : value.mProgressive) {
                    mVideoFileTypeAdapter.write(out, videoFile);
                }
                out.endArray();
            }
            if (value.mStatus != null) {
                out.name("status");
                sStatusEnumTypeAdapter.write(out, value.mStatus);
            }
            // TODO: The rest of the class members. Holding off since API does not support yet 6/16/16 [KZ]

            out.endObject();
        }

        @Override
        public Play read(JsonReader in) throws IOException {
            final Play play = new Play();
            in.beginObject();
            while (in.hasNext()) {
                String nextName = in.nextName();
                JsonToken jsonToken = in.peek();
                if (jsonToken == JsonToken.NULL) {
                    in.skipValue();
                    continue;
                }
                switch (nextName) {
                    case "hls":
                        play.mHls = mVideoFileTypeAdapter.read(in);
                        break;
                    case "dash":
                        play.mDash = mVideoFileTypeAdapter.read(in);
                        break;
                    case "progressive":
                        in.beginArray();
                        play.mProgressive = new ArrayList<>();
                        while (in.hasNext()) {
                            play.mProgressive.add(mVideoFileTypeAdapter.read(in));
                        }
                        in.endArray();
                        break;
                    case "status":
                        play.mStatus = sStatusEnumTypeAdapter.read(in);
                        break;
                    // TODO: The rest of the class members. Holding off since API does not support yet 6/16/16 [KZ]
                    default:
                        // skip any values that we do not account for, without this, the app will crash if the
                        // api provides more values than we have! [KZ] 6/15/16
                        in.skipValue();
                        break;
                }
            }
            in.endObject();
            return play;
        }
    }

    public static class Factory implements TypeAdapterFactory {

        @SuppressWarnings("unchecked")
        @Override
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            if (Play.class.isAssignableFrom(typeToken.getRawType())) {
                TypeAdapter videoFileTypeAdapter = gson.getAdapter(VideoFile.class);
                if (videoFileTypeAdapter instanceof VideoFileTypeAdapter) {
                    return (TypeAdapter<T>) new PlayTypeAdapter((VideoFileTypeAdapter) videoFileTypeAdapter);
                }
            }

            // by returning null, Gson will never check this factory if it can handle this TypeToken again [KZ] 6/15/16
            return null;
        }
    }
}
