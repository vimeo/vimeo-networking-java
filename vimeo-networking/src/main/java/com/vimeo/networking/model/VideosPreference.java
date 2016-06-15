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

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.vimeo.networking.logging.ClientLogger;

import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.Serializable;

/**
 * Preferences for videos
 * <p/>
 * Created by zetterstromk on 1/28/16.
 */
public class VideosPreference implements Serializable {

    private static final long serialVersionUID = 1956447486226253433L;
    @Nullable
    @SerializedName("privacy")
    protected String privacy;

    @Nullable
    @SerializedName("password")
    protected String password;

    @Nullable
    public String getPassword() {
        return password;
    }

    @Nullable
    public String getPrivacy() {
        return privacy;
    }

    public static class VideosPreferenceTypeAdapter extends TypeAdapter<VideosPreference> {

        @Override
        public void write(JsonWriter out, VideosPreference value) throws IOException {
            out.beginObject();
            if (value == null) {
                ClientLogger.d("VideosPreference null in write()");
                out.endObject();
                return;
            }
            if (value.privacy != null) {
                out.name("privacy").value(value.privacy);
            }
            if (value.password != null) {
                out.name("password").value(value.password);
            }

            out.endObject();
        }

        @Override
        public VideosPreference read(JsonReader in) throws IOException {
            final VideosPreference videosPreference = new VideosPreference();
            in.beginObject();
            while (in.hasNext()) {
                String nextName = in.nextName();
                JsonToken jsonToken = in.peek();
                if (jsonToken == JsonToken.NULL) {
                    in.skipValue();
                    continue;
                }
                switch (nextName) {
                    case "privacy":
                        videosPreference.privacy = in.nextString();
                        break;
                    case "password":
                        videosPreference.password = in.nextString();
                        break;
                    default:
                        // skip any values that we do not account for, without this, the app will crash if the
                        // api provides more values than we have! [KZ] 6/15/16
                        in.skipValue();
                        break;
                }
            }
            in.endObject();
            return videosPreference;
        }
    }
}
