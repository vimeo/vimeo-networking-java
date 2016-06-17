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
 * Quota for uploads
 * <p/>
 * Created by kylevenn on 8/19/15.
 */
public class Quota implements Serializable {

    private static final long serialVersionUID = -9173641301792409558L;

    public boolean hd;
    public boolean sd;

    public static class QuotaTypeAdapter extends TypeAdapter<Quota> {

        @Override
        public void write(JsonWriter out, Quota value) throws IOException {
            out.beginObject();
            if (value == null) {
                ClientLogger.d("Quota null in write()");
                out.endObject();
                return;
            }
            out.name("hd").value(value.hd);
            out.name("sd").value(value.sd);

            out.endObject();
        }

        @Override
        public Quota read(JsonReader in) throws IOException {
            final Quota quota = new Quota();
            in.beginObject();
            while (in.hasNext()) {
                String nextName = in.nextName();
                JsonToken jsonToken = in.peek();
                if (jsonToken == JsonToken.NULL) {
                    in.skipValue();
                    continue;
                }
                switch (nextName) {
                    case "hd":
                        quota.hd = in.nextBoolean();
                        break;
                    case "sd":
                        quota.sd = in.nextBoolean();
                        break;
                    default:
                        // skip any values that we do not account for, without this, the app will crash if the
                        // api provides more values than we have! [KZ] 6/15/16
                        in.skipValue();
                        break;
                }
            }
            in.endObject();
            return quota;
        }
    }
}
