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

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.vimeo.networking.logging.ClientLogger;

import java.io.IOException;
import java.io.Serializable;

/**
 * Space for uploads
 * Created by kylevenn on 8/19/15.
 */
public class Space implements Serializable {

    private static final long serialVersionUID = -1985382617862372889L;

    public long free;
    public long max;
    public long used;

    public static class SpaceTypeAdapter extends TypeAdapter<Space> {

        @Override
        public void write(JsonWriter out, Space value) throws IOException {
            out.beginObject();
            if (value == null) {
                ClientLogger.d("Space null in write()");
                out.endObject();
                return;
            }
            out.name("free").value(value.free);
            out.name("max").value(value.max);
            out.name("used").value(value.used);

            out.endObject();
        }

        @Override
        public Space read(JsonReader in) throws IOException {
            final Space space = new Space();
            in.beginObject();
            while (in.hasNext()) {
                String nextName = in.nextName();
                JsonToken jsonToken = in.peek();
                if (jsonToken == JsonToken.NULL) {
                    in.skipValue();
                    continue;
                }
                switch (nextName) {
                    case "free":
                        space.free = in.nextLong();
                        break;
                    case "max":
                        space.max = in.nextLong();
                        break;
                    case "used":
                        space.used = in.nextLong();
                        break;
                    default:
                        // skip any values that we do not account for, without this, the app will crash if the
                        // api provides more values than we have! [KZ] 6/15/16
                        in.skipValue();
                        break;
                }
            }
            in.endObject();
            return space;
        }
    }
}
