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

import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * This model object represents a Connection.
 * Created by hanssena on 4/23/15.
 */
public class Connection implements Serializable {

    private static final long serialVersionUID = -840088720891343176L;
    @Nullable
    @SerializedName("uri")
    public String uri;
    @Nullable
    @SerializedName("options")
    public ArrayList<String> options;
    @SerializedName("total")
    public int total;
    @SerializedName("main_total")
    public int mainTotal;
    @SerializedName("extra_total")
    public int extraTotal;
    @SerializedName("viewable_total")
    public int viewableTotal;

    public static class ConnectionTypeAdapter extends TypeAdapter<Connection> {

        @Override
        public void write(JsonWriter out, Connection value) throws IOException {
            out.beginObject();
            if (value == null) {
                ClientLogger.d("Connection null in write()");
                out.endObject();
                return;
            }
            out.name("uri").value(value.uri);
            if (value.options != null) {
                out.name("options").beginArray();
                for (final String option : value.options) {
                    out.value(option);
                }
                out.endArray();
            }
            out.name("total").value(value.total);
            out.name("main_total").value(value.mainTotal);
            out.name("extra_total").value(value.extraTotal);
            out.name("viewable_total").value(value.viewableTotal);
            out.endObject();
        }

        @Override
        public Connection read(JsonReader in) throws IOException {
            final Connection connection = new Connection();
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
                        connection.uri = in.nextString();
                        break;
                    case "options":
                        in.beginArray();
                        connection.options = new ArrayList<>();
                        while (in.hasNext()) {
                            connection.options.add(in.nextString());
                        }
                        in.endArray();
                        break;
                    case "total":
                        connection.total = in.nextInt();
                        break;
                    case "main_total":
                        connection.mainTotal = in.nextInt();
                        break;
                    case "extra_total":
                        connection.extraTotal = in.nextInt();
                        break;
                    case "viewable_total":
                        connection.viewableTotal = in.nextInt();
                        break;
                    default:
                        // skip any values that we do not account for, without this, the app will crash if the
                        // api provides more values than we have! [KZ] 6/15/16
                        in.skipValue();
                        break;
                }
            }
            in.endObject();
            return connection;
        }
    }
}
