/*
 * Copyright (c) 2015 Vimeo (https://vimeo.com)
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

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.vimeo.networking.logging.ClientLogger;
import com.vimeo.networking.model.playback.VideoLog;
import com.vimeo.networking.model.playback.VideoLog.VideoLogTypeAdapter;
import com.vimeo.networking.utils.EnumTypeAdapter;
import com.vimeo.networking.utils.ISO8601;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

/**
 * Representation of a Video stream/playback file
 * <p/>
 * Created by alfredhanssen on 4/25/15.
 */
@SuppressWarnings("deprecation")
public class VideoFile implements Serializable {

    public enum MimeType {
        NONE("None"),
        @SerializedName("video/mp4")
        MP4("video/mp4"),
        @SerializedName("video/webm")
        WEBM("video/webm"), // Flash
        @SerializedName("vp6/x-video")
        VP6("vp6/x-video"); // Flash

        private final String string;

        MimeType(String string) {
            this.string = string;
        }

        @Override
        // Overridden for analytics.
        public String toString() {
            return this.string;
        }
    }

    @Deprecated
    public enum VideoQuality {
        NONE("N/A"),
        @SerializedName("hls")
        HLS("hls"),
        @SerializedName("hd")
        HD("hd"),
        @SerializedName("sd")
        SD("sd"),
        @SerializedName("mobile")
        MOBILE("mobile");

        private final String string;

        VideoQuality(String string) {
            this.string = string;
        }

        @Override
        // Overridden for analytics.
        public String toString() {
            return this.string;
        }
    }

    private static final long serialVersionUID = -5256416394912086020L;

    // -----------------------------------------------------------------------------------------------------
    // Fields common between all file types - HLS, Dash, Progressive
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Fields common between all file types">
    @Deprecated
    @Nullable
    public Date expires;
    @Nullable
    private Date linkExpirationTime;
    /** link will be made private in a future release - use {@link #getLink()} instead */
    @Deprecated
    public String link;
    @Nullable
    private VideoLog log;

    @Nullable
    public Date getLinkExpirationTime() {
        return linkExpirationTime;
    }

    /** @return true if this VideoFile doesn't have an expired field or if the expires date is before the current date */
    public boolean isExpired() {
        // If expires is null, we should probably refresh the video object regardless [KV] 3/31/16
        // TODO: Simplify this when expires is deprecated 4/25/16 [KZ]
        return (expires == null && linkExpirationTime == null) ||
               (expires != null && expires.before(new Date())) ||
               (linkExpirationTime != null && linkExpirationTime.before(new Date()));
    }

    public String getLink() {
        return link;
    }

    @Nullable
    public VideoLog getLog() {
        return log;
    }
    // </editor-fold>

    // -----------------------------------------------------------------------------------------------------
    // Progressive files only - these fields are not relevant to HLS/Dash
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Progressive files only">
    /** quality will be removed in the future when {@link Video#files} is removed */
    @Deprecated
    @Nullable
    private VideoQuality quality;
    @Nullable
    private MimeType type;
    private double fps;
    /** width will be made private in a future release - use {@link #getWidth()} instead */
    @Deprecated
    public int width;
    /** height will be made private in a future release - use {@link #getHeight()} instead */
    @Deprecated
    public int height;
    /** size will be made private in a future release - use {@link #getSize()} instead */
    @Deprecated
    public long size; // size of the file, in bytes
    /** The md5 provides us with a way to uniquely identify video files at {@link #getLink()} */
    /** md5 will be made private in a future release - use {@link #getMd5()} instead */
    @Deprecated
    @Nullable
    @SerializedName("md5")
    public String md5;
    @Nullable
    private Date createdTime; // time indicating when this transcode was completed

    /**
     * quality is no longer included in VideoFiles under {@link Video#getPlay()} - it will be removed
     * in a future release once {@link Video#files} is removed.
     *
     * @return the VideoQuality
     */
    @Deprecated
    public VideoQuality getQuality() {
        return quality == null ? VideoQuality.NONE : quality;
    }

    public MimeType getType() {
        return type == null ? MimeType.NONE : type;
    }

    public boolean isVP6() {
        return getType() == MimeType.VP6;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public long getSize() {
        return size;
    }

    public double getFps() {
        return fps;
    }

    @Nullable
    public String getMd5() {
        return md5;
    }

    @Nullable
    public Date getCreatedTime() {
        return createdTime;
    }
    // </editor-fold>

    // -----------------------------------------------------------------------------------------------------
    // Equals/Hashcode
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Equals/Hashcode">
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !(o instanceof VideoFile)) {
            return false;
        }

        VideoFile that = (VideoFile) o;

        return (this.getLink() != null && that.getLink() != null) && this.getLink().equals(that.getLink());
    }

    @Override
    public int hashCode() {
        return this.getLink() != null ? this.getLink().hashCode() : 0;
    }
    // </editor-fold>

    public static class VideoFileTypeAdapter extends TypeAdapter<VideoFile> {

        private final static EnumTypeAdapter<MimeType> sMimeTypeEnumTypeAdapter =
                new EnumTypeAdapter<>(MimeType.class);
        private final static EnumTypeAdapter<VideoQuality> sVideoQualityEnumTypeAdapter =
                new EnumTypeAdapter<>(VideoQuality.class);

        @NotNull
        private final VideoLogTypeAdapter mVideoLogTypeAdapter;

        public VideoFileTypeAdapter(@NotNull VideoLogTypeAdapter videoLogTypeAdapter) {
            mVideoLogTypeAdapter = videoLogTypeAdapter;
        }

        @Override
        public void write(JsonWriter out, VideoFile value) throws IOException {
            out.beginObject();
            if (value == null) {
                ClientLogger.d("Comment null in write()");
                out.endObject();
                return;
            }
            if (value.expires != null) {
                out.name("expires").value(ISO8601.fromDate(value.expires));
            }
            if (value.linkExpirationTime != null) {
                out.name("link_expiration_time").value(ISO8601.fromDate(value.linkExpirationTime));
            }
            if (value.link != null) {
                out.name("link").value(value.link);
            }
            if (value.log != null) {
                out.name("log");
                mVideoLogTypeAdapter.write(out, value.log);
            }
            if (value.quality != null) {
                out.name("quality");
                sVideoQualityEnumTypeAdapter.write(out, value.quality);
            }
            if (value.type != null) {
                out.name("type");
                sMimeTypeEnumTypeAdapter.write(out, value.type);
            }
            out.name("fps").value(value.fps);
            out.name("width").value(value.width);
            out.name("height").value(value.height);
            out.name("size").value(value.size);
            if (value.md5 != null) {
                out.name("md5").value(value.md5);
            }
            if (value.createdTime != null) {
                out.name("created_time").value(ISO8601.fromDate(value.createdTime));
            }

            out.endObject();
        }

        @Override
        public VideoFile read(JsonReader in) throws IOException {
            final VideoFile videoFile = new VideoFile();
            in.beginObject();
            while (in.hasNext()) {
                String nextName = in.nextName();
                JsonToken jsonToken = in.peek();
                if (jsonToken == JsonToken.NULL) {
                    in.skipValue();
                    continue;
                }
                switch (nextName) {
                    case "expires":
                        try {
                            videoFile.expires = ISO8601.toDate(in.nextString());
                        } catch (ParseException e) {
                            ClientLogger.e("Error parsing VideoFile date", e);
                        }
                        break;
                    case "link_expiration_time":
                        try {
                            videoFile.linkExpirationTime = ISO8601.toDate(in.nextString());
                        } catch (ParseException e) {
                            ClientLogger.e("Error parsing VideoFile date", e);
                        }
                        break;
                    case "link":
                        videoFile.link = in.nextString();
                        break;
                    case "log":
                        videoFile.log = mVideoLogTypeAdapter.read(in);
                        break;
                    case "quality":
                        videoFile.quality = sVideoQualityEnumTypeAdapter.read(in);
                        break;
                    case "type":
                        videoFile.type = sMimeTypeEnumTypeAdapter.read(in);
                        break;
                    case "fps":
                        videoFile.fps = in.nextDouble();
                        break;
                    case "width":
                        videoFile.width = in.nextInt();
                        break;
                    case "height":
                        videoFile.height = in.nextInt();
                        break;
                    case "size":
                        videoFile.size = in.nextLong();
                        break;
                    case "md5":
                        videoFile.md5 = in.nextString();
                        break;
                    case "created_time":
                        try {
                            videoFile.createdTime = ISO8601.toDate(in.nextString());
                        } catch (ParseException e) {
                            ClientLogger.e("Error parsing VideoFile date", e);
                        }
                        break;
                    default:
                        // skip any values that we do not account for, without this, the app will crash if the
                        // api provides more values than we have! [KZ] 6/15/16
                        in.skipValue();
                        break;
                }
            }
            in.endObject();
            return videoFile;
        }
    }

    public static class Factory implements TypeAdapterFactory {

        @SuppressWarnings("unchecked")
        @Override
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            if (VideoFile.class.isAssignableFrom(typeToken.getRawType())) {
                TypeAdapter videoLogTypeAdapter = gson.getAdapter(VideoLog.class);
                if (videoLogTypeAdapter instanceof VideoLogTypeAdapter) {
                    return (TypeAdapter<T>) new VideoFileTypeAdapter(
                            (VideoLogTypeAdapter) videoLogTypeAdapter);
                }
            }

            // by returning null, Gson will never check this factory if it can handle this TypeToken again [KZ] 6/15/16
            return null;
        }
    }
}
