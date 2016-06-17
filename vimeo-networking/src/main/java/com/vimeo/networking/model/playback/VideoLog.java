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

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.vimeo.networking.logging.ClientLogger;

import java.io.IOException;
import java.io.Serializable;

/**
 * Logging endpoints. This class is NOT consumable by the general public.
 * <p/>
 * Created by alfredhanssen on 4/25/15.
 */
public class VideoLog implements Serializable {

    private static final long serialVersionUID = -4646869969374079276L;
    // URL for the "load" event
    @SerializedName("load_link")
    private String mLoadLink;

    // URL for the "play" event
    @SerializedName("play_link")
    private String mPlayLink;

    // URL for the "like_press_link" event
    @SerializedName("like_press_link")
    private String mLikePressLink;

    // URL for the "watchlater_press_link" event
    @SerializedName("watchlater_press_link")
    private String mWatchLaterPressLink;

    public String getLoadLoggingUrl() {
        return mLoadLink;
    }

    public boolean isLoadEmpty() {
        return mLoadLink == null || mLoadLink.trim().isEmpty();
    }

    public boolean isPlayEmpty() {
        return mPlayLink == null || mPlayLink.trim().isEmpty();
    }

    public String getPlayLoggingUrl() {
        return mPlayLink;
    }

    public boolean isLikeEmpty() {
        return mLikePressLink == null || mLikePressLink.trim().isEmpty();
    }

    public String getLikeLoggingUrl() {
        return mLikePressLink;
    }

    public boolean isWatchLaterEmpty() {
        return mWatchLaterPressLink == null || mWatchLaterPressLink.trim().isEmpty();
    }

    public String getWatchLaterLoggingUrl() {
        return mWatchLaterPressLink;
    }

    public static class VideoLogTypeAdapter extends TypeAdapter<VideoLog> {

        @Override
        public void write(JsonWriter out, VideoLog value) throws IOException {
            out.beginObject();
            if (value == null) {
                ClientLogger.d("VideoLog null in write()");
                out.endObject();
                return;
            }
            if (value.mLoadLink != null) {
                out.name("load_link").value(value.mLoadLink);
            }
            if (value.mPlayLink != null) {
                out.name("play_link").value(value.mPlayLink);
            }
            if (value.mLikePressLink != null) {
                out.name("like_press_link").value(value.mLikePressLink);
            }
            if (value.mWatchLaterPressLink != null) {
                out.name("watchlater_press_link").value(value.mWatchLaterPressLink);
            }

            out.endObject();
        }

        @Override
        public VideoLog read(JsonReader in) throws IOException {
            final VideoLog videoLog = new VideoLog();
            in.beginObject();
            while (in.hasNext()) {
                String nextName = in.nextName();
                JsonToken jsonToken = in.peek();
                if (jsonToken == JsonToken.NULL) {
                    in.skipValue();
                    continue;
                }
                switch (nextName) {
                    case "load_link":
                        videoLog.mLoadLink = in.nextString();
                        break;
                    case "play_link":
                        videoLog.mPlayLink = in.nextString();
                        break;
                    case "like_press_link":
                        videoLog.mLikePressLink = in.nextString();
                        break;
                    case "watchlater_press_link":
                        videoLog.mWatchLaterPressLink = in.nextString();
                        break;
                    default:
                        // skip any values that we do not account for, without this, the app will crash if the
                        // api provides more values than we have! [KZ] 6/15/16
                        in.skipValue();
                        break;
                }
            }
            in.endObject();
            return videoLog;
        }
    }
}