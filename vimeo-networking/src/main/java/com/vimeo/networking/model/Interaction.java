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
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.vimeo.networking.logging.ClientLogger;
import com.vimeo.networking.utils.EnumTypeAdapter;
import com.vimeo.networking.utils.ISO8601;

import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

/**
 * This model object represents an Interaction.
 * Created by zetterstromk on 6/5/15.
 */
public class Interaction implements Serializable {

    private static final long serialVersionUID = 2033767841952340400L;
    private static final String STREAM_PURCHASED = "purchased";
    private static final String STREAM_RESTRICTED = "restricted";
    private static final String STREAM_AVAILABLE = "available";
    private static final String STREAM_UNAVAILABLE = "unavailable";

    public enum Stream {
        @SerializedName(STREAM_PURCHASED)
        PURCHASED(STREAM_PURCHASED), // you have purchased it (without using a promo code)
        @SerializedName(STREAM_RESTRICTED)
        RESTRICTED(STREAM_RESTRICTED), // you don't have it, can't purchase it in this country
        @SerializedName(STREAM_AVAILABLE)
        AVAILABLE(STREAM_AVAILABLE), // you don't have it, can purchase it
        @SerializedName(STREAM_UNAVAILABLE)
        UNAVAILABLE(STREAM_UNAVAILABLE); // you don't have it, can't purchase it

        private final String mName;

        Stream(String name) {
            mName = name;
        }

        @Override
        public String toString() {
            return mName;
        }
    }

    @SerializedName("added")
    public boolean added;
    @Nullable
    @SerializedName("added_time")
    public Date addedTime;
    @Nullable
    @SerializedName("uri")
    public String uri;
    @Nullable
    @SerializedName("stream")
    public Stream stream;
    @Nullable
    @SerializedName("expires_time")
    public Date expiration;

    public static class InteractionTypeAdapter extends TypeAdapter<Interaction> {

        private final static EnumTypeAdapter<Stream> sStreamEnumTypeAdapter =
                new EnumTypeAdapter<>(Stream.class);

        @Override
        public void write(JsonWriter out, Interaction value) throws IOException {
            out.beginObject();
            if (value == null) {
                ClientLogger.d("Interaction null in write()");
                out.endObject();
                return;
            }
            out.name("added").value(value.added);
            if (value.addedTime != null) {
                out.name("added_time").value(ISO8601.fromDate(value.addedTime));
            }
            if (value.uri != null) {
                out.name("uri").value(value.uri);
            }
            if (value.stream != null) {
                out.name("stream").value(value.stream.toString());
            }
            if (value.expiration != null) {
                out.name("expires_time").value(ISO8601.fromDate(value.expiration));
            }

            out.endObject();
        }

        @Override
        public Interaction read(JsonReader in) throws IOException {
            final Interaction interaction = new Interaction();
            in.beginObject();
            while (in.hasNext()) {
                String nextName = in.nextName();
                JsonToken jsonToken = in.peek();
                if (jsonToken == JsonToken.NULL) {
                    in.skipValue();
                    continue;
                }
                switch (nextName) {
                    case "uri":
                        interaction.uri = in.nextString();
                        break;
                    case "added":
                        interaction.added = in.nextBoolean();
                        break;
                    case "added_time":
                        try {
                            interaction.addedTime = ISO8601.toDate(in.nextString());
                        } catch (ParseException e) {
                            ClientLogger.e("Error parsing Interaction", e);
                        }
                        break;
                    case "stream":
                        interaction.stream = sStreamEnumTypeAdapter.read(in);
                        break;
                    case "expires_time":
                        try {
                            interaction.expiration = ISO8601.toDate(in.nextString());
                        } catch (ParseException e) {
                            ClientLogger.e("Error parsing Interaction", e);
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
            return interaction;
        }
    }
}
