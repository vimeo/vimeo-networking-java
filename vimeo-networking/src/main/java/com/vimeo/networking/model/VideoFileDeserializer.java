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

package com.vimeo.networking.model;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.vimeo.networking.model.VideoFile.MimeType;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Date;

/**
 * A class to help with parsing a {@link VideoFile}. Depending on where the file is
 * located, the <code>log</code> field may be an object or a {@link String}.
 * Created by zetterstromk on 2/16/17.
 */
@Deprecated
public final class VideoFileDeserializer {

    private VideoFileDeserializer() {
    }

    /**
     * Handles deserialization for the broken VideoFile class
     */
    @Deprecated
    public static final class TypeAdapterFactory implements com.google.gson.TypeAdapterFactory {

        public TypeAdapterFactory() {
        }

        @Override
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
            if (type.getRawType().equals(VideoFile.class)) {
                return (TypeAdapter<T>) new VideoFileTypeAdapter(gson);
            }
            return null;
        }
    }

    /**
     * Handles deserialization for the broken VideoFile class.
     * The log field was changed from an object to a String, but depending
     * on API version, the String may not be sent. Since the API will
     * only ever send Vimeo apps the log field, and we only use the newer
     * String, we can ignore the log objects.
     */
    @Deprecated
    private static final class VideoFileTypeAdapter extends TypeAdapter<com.vimeo.networking.model.VideoFile> {

        private final TypeAdapter<MimeType> mMimeTypeTypeAdapter;
        private final TypeAdapter<Date> mDateTypeAdpter;

        @SuppressWarnings("unchecked")
        VideoFileTypeAdapter(@NotNull Gson gsonInternal) {
            this.mMimeTypeTypeAdapter = gsonInternal.getAdapter(MimeType.class);
            this.mDateTypeAdpter = gsonInternal.getAdapter(Date.class);
        }

        @Override
        public void write(JsonWriter writer, VideoFile object) throws IOException {
            writer.beginObject();
            if (object == null) {
                writer.endObject();
                return;
            }

            if (object.mLinkExpirationTime != null) {
                writer.name("link_expiration_time");
                mDateTypeAdpter.write(writer, object.mLinkExpirationTime);
            }

            if (object.mLink != null) {
                writer.name("link");
                com.google.gson.internal.bind.TypeAdapters.STRING.write(writer, object.mLink);
            }

            if (object.mLog != null) {
                writer.name("log");
                com.google.gson.internal.bind.TypeAdapters.STRING.write(writer, object.mLog);
            }

            if (object.mToken != null) {
                writer.name("token");
                com.google.gson.internal.bind.TypeAdapters.STRING.write(writer, object.mToken);
            }

            if (object.mLicenseLink != null) {
                writer.name("license_link");
                com.google.gson.internal.bind.TypeAdapters.STRING.write(writer, object.mLicenseLink);
            }

            if (object.mMimeType != null) {
                writer.name("type");
                mMimeTypeTypeAdapter.write(writer, object.mMimeType);
            }

            writer.name("fps");
            writer.value(object.mFps);

            writer.name("width");
            writer.value(object.mWidth);

            writer.name("height");
            writer.value(object.mHeight);

            writer.name("size");
            writer.value(object.mSize);

            if (object.mMd5 != null) {
                writer.name("md5");
                com.google.gson.internal.bind.TypeAdapters.STRING.write(writer, object.mMd5);
            }

            if (object.mCreatedTime != null) {
                writer.name("created_time");
                mDateTypeAdpter.write(writer, object.mCreatedTime);
            }

            writer.endObject();
        }

        @Override
        public VideoFile read(JsonReader reader) throws IOException {
            if (reader.peek() == com.google.gson.stream.JsonToken.NULL) {
                reader.nextNull();
                return null;
            }
            if (reader.peek() != com.google.gson.stream.JsonToken.BEGIN_OBJECT) {
                reader.skipValue();
                return null;
            }
            reader.beginObject();

            com.vimeo.networking.model.VideoFile object = new com.vimeo.networking.model.VideoFile();
            while (reader.hasNext()) {
                String name = reader.nextName();
                com.google.gson.stream.JsonToken jsonToken = reader.peek();
                if (jsonToken == com.google.gson.stream.JsonToken.NULL) {
                    reader.skipValue();
                    continue;
                }
                switch (name) {
                    case "link_expiration_time":
                        object.mLinkExpirationTime = mDateTypeAdpter.read(reader);
                        break;
                    case "link":
                        object.mLink = com.google.gson.internal.bind.TypeAdapters.STRING.read(reader);
                        break;
                    case "log":
                        if (jsonToken == JsonToken.STRING) {
                            object.mLog = com.google.gson.internal.bind.TypeAdapters.STRING.read(reader);
                        } else {
                            reader.skipValue();
                        }
                        break;
                    case "token":
                        object.mToken = com.google.gson.internal.bind.TypeAdapters.STRING.read(reader);
                        break;
                    case "license_link":
                        object.mLicenseLink = com.google.gson.internal.bind.TypeAdapters.STRING.read(reader);
                        break;
                    case "type":
                        object.mMimeType = mMimeTypeTypeAdapter.read(reader);
                        break;
                    case "fps":
                        object.mFps = reader.nextDouble();
                        break;
                    case "width":
                        object.mWidth = reader.nextInt();
                        break;
                    case "height":
                        object.mHeight = reader.nextInt();
                        break;
                    case "size":
                        object.mSize = reader.nextLong();
                        break;
                    case "md5":
                        object.mMd5 = com.google.gson.internal.bind.TypeAdapters.STRING.read(reader);
                        break;
                    case "created_time":
                        object.mCreatedTime = mDateTypeAdpter.read(reader);
                        break;
                    default:
                        reader.skipValue();
                        break;
                }
            }

            reader.endObject();
            return object;
        }
    }
}
