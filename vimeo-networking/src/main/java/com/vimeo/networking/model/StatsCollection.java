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

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.vimeo.networking.logging.ClientLogger;

import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.Serializable;

/**
 * Stats related to videos
 * Created by hanssena on 4/23/15.
 */
public class StatsCollection implements Serializable {

    private static final long serialVersionUID = -348202198117360187L;
    @Nullable
    public Integer plays; // If this is null, that means the uploader has disabled play count [KZ] 9/9/15

    public static class StatsCollectionTypeAdapter extends TypeAdapter<StatsCollection> {

        @Override
        public void write(JsonWriter out, StatsCollection value) throws IOException {
            out.beginObject();
            if (value == null) {
                ClientLogger.d("StatsCollection null in write()");
                out.endObject();
                return;
            }
            if (value.plays != null) {
                out.name("plays").value(value.plays);
            }

            out.endObject();
        }

        @Override
        public StatsCollection read(JsonReader in) throws IOException {
            final StatsCollection statsCollection = new StatsCollection();
            in.beginObject();
            while (in.hasNext()) {
                String nextName = in.nextName();
                JsonToken jsonToken = in.peek();
                if (jsonToken == JsonToken.NULL) {
                    in.skipValue();
                    continue;
                }
                switch (nextName) {
                    case "plays":
                        statsCollection.plays = in.nextInt();
                        break;
                    default:
                        // skip any values that we do not account for, without this, the app will crash if the
                        // api provides more values than we have! [KZ] 6/15/16
                        in.skipValue();
                        break;
                }
            }
            in.endObject();
            return statsCollection;
        }
    }
}
