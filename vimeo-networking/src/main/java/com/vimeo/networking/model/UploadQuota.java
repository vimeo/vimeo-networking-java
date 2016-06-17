/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Vimeo
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
import com.vimeo.networking.Vimeo;
import com.vimeo.networking.logging.ClientLogger;
import com.vimeo.networking.model.Quota.QuotaTypeAdapter;
import com.vimeo.networking.model.Space.SpaceTypeAdapter;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.Serializable;

/**
 * Model representing upload quota left for users
 * Created by kylevenn on 8/19/15.
 */
public class UploadQuota implements Serializable {

    private static final long serialVersionUID = 4050488085481972886L;

    public Space space;
    public Quota quota;

    // Returns -1 if there is no space object on this user
    public long getFreeUploadSpace() {
        if (space != null) {
            return space.free;
        } else {
            return Vimeo.NOT_FOUND;
        }
    }

    public static class UploadQuotaTypeAdapter extends TypeAdapter<UploadQuota> {

        @NotNull
        private final SpaceTypeAdapter mSpaceTypeAdapter;
        @NotNull
        private final QuotaTypeAdapter mQuotaTypeAdapter;

        public UploadQuotaTypeAdapter(@NotNull SpaceTypeAdapter spaceTypeAdapter,
                                      @NotNull QuotaTypeAdapter quotaTypeAdapter) {
            mSpaceTypeAdapter = spaceTypeAdapter;
            mQuotaTypeAdapter = quotaTypeAdapter;
        }

        @Override
        public void write(JsonWriter out, UploadQuota value) throws IOException {
            out.beginObject();
            if (value == null) {
                ClientLogger.d("UploadQuota null in write()");
                out.endObject();
                return;
            }
            if (value.space != null) {
                out.name("space");
                mSpaceTypeAdapter.write(out, value.space);
            }
            if (value.quota != null) {
                out.name("quota");
                mQuotaTypeAdapter.write(out, value.quota);
            }

            out.endObject();
        }

        @Override
        public UploadQuota read(JsonReader in) throws IOException {
            final UploadQuota uploadQuota = new UploadQuota();
            in.beginObject();
            while (in.hasNext()) {
                String nextName = in.nextName();
                JsonToken jsonToken = in.peek();
                if (jsonToken == JsonToken.NULL) {
                    in.skipValue();
                    continue;
                }
                switch (nextName) {
                    case "space":
                        uploadQuota.space = mSpaceTypeAdapter.read(in);
                        break;
                    case "quota":
                        uploadQuota.quota = mQuotaTypeAdapter.read(in);
                        break;
                    default:
                        // skip any values that we do not account for, without this, the app will crash if the
                        // api provides more values than we have! [KZ] 6/15/16
                        in.skipValue();
                        break;
                }
            }
            in.endObject();
            return uploadQuota;
        }
    }

    public static class Factory implements TypeAdapterFactory {

        @SuppressWarnings("unchecked")
        @Override
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
            if (UploadQuota.class.isAssignableFrom(type.getRawType())) {
                TypeAdapter spaceTypeAdapter = gson.getAdapter(Space.class);
                TypeAdapter quotaTypeAdapter = gson.getAdapter(Quota.class);
                if (spaceTypeAdapter instanceof SpaceTypeAdapter &&
                    quotaTypeAdapter instanceof QuotaTypeAdapter) {
                    return (TypeAdapter<T>) new UploadQuotaTypeAdapter((SpaceTypeAdapter) spaceTypeAdapter,
                                                                       (QuotaTypeAdapter) quotaTypeAdapter);
                }
            }

            // by returning null, Gson will never check this factory if it can handle this TypeToken again [KZ] 6/15/16
            return null;
        }
    }
}
