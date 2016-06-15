/*
 * Copyright (c) 2016 Vimeo (https://vimeo.com)
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
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.vimeo.networking.logging.ClientLogger;
import com.vimeo.networking.model.VideosPreference.VideosPreferenceTypeAdapter;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.Serializable;


/**
 * This model holds on to certain User preferences that may have been set by a user
 * <p/>
 * Created by zetterstromk on 1/28/16.
 */
public class Preferences implements Serializable {

    private static final long serialVersionUID = -251634859829805204L;
    @Nullable
    protected VideosPreference videos;

    @Nullable
    public VideosPreference getVideos() {
        return videos;
    }

    public static class PreferencesTypeAdapter extends TypeAdapter<Preferences> {

        @NotNull
        private final VideosPreferenceTypeAdapter mVideosPreferenceTypeAdapter;

        public PreferencesTypeAdapter(@NotNull VideosPreferenceTypeAdapter videosPreferenceTypeAdapter) {
            mVideosPreferenceTypeAdapter = videosPreferenceTypeAdapter;
        }

        @Override
        public void write(JsonWriter out, Preferences value) throws IOException {
            out.beginObject();
            if (value == null) {
                ClientLogger.d("Preferences null in write()");
                out.endObject();
                return;
            }
            if (value.videos != null) {
                out.name("videos");
                mVideosPreferenceTypeAdapter.write(out, value.videos);
            }
            out.endObject();
        }

        @Override
        public Preferences read(JsonReader in) throws IOException {
            final Preferences preferences = new Preferences();

            in.beginObject();
            while (in.hasNext()) {
                String nextName = in.nextName();
                JsonToken jsonToken = in.peek();
                if (jsonToken == JsonToken.NULL) {
                    in.skipValue();
                    continue;
                }
                switch (nextName) {
                    case "videos":
                        preferences.videos = mVideosPreferenceTypeAdapter.read(in);
                        break;
                    default:
                        // skip any values that we do not account for, without this, the app will crash if the
                        // api provides more values than we have! [KZ] 6/15/16
                        in.skipValue();
                        break;
                }
            }
            in.endObject();

            return preferences;
        }
    }

    public static class Factory implements TypeAdapterFactory {

        @SuppressWarnings("unchecked")
        @Override
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
            if (Preferences.class.isAssignableFrom(type.getRawType())) {
                TypeAdapter typeAdapter = gson.getAdapter(VideosPreference.class);
                if (typeAdapter instanceof VideosPreferenceTypeAdapter) {
                    return (TypeAdapter<T>) new PreferencesTypeAdapter(
                            (VideosPreferenceTypeAdapter) typeAdapter);
                }
            }

            // by returning null, Gson will never check this factory if it can handle this TypeToken again [KZ] 6/15/16
            return null;
        }
    }
}
