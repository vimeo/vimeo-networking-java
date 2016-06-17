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
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.vimeo.networking.logging.ClientLogger;
import com.vimeo.networking.model.ConnectionCollection.ConnectionCollectionTypeAdapter;
import com.vimeo.networking.model.InteractionCollection.InteractionCollectionTypeAdapter;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.Serializable;

/**
 * Model to hold on to connections and interactions objects
 * Created by hanssena on 4/23/15.
 */
public class Metadata implements Serializable {

    private static final long serialVersionUID = 6626539965452151962L;
    public ConnectionCollection connections;
    public InteractionCollection interactions;

    public static class MetadataTypeAdapter extends TypeAdapter<Metadata> {

        @NotNull
        private final ConnectionCollectionTypeAdapter mConnectionCollectionTypeAdapter;
        @NotNull
        private final InteractionCollectionTypeAdapter mInteractionCollectionTypeAdapter;

        public MetadataTypeAdapter(@NotNull ConnectionCollectionTypeAdapter connectionCollectionTypeAdapter,
                                   @NotNull
                                   InteractionCollectionTypeAdapter interactionCollectionTypeAdapter) {
            mConnectionCollectionTypeAdapter = connectionCollectionTypeAdapter;
            mInteractionCollectionTypeAdapter = interactionCollectionTypeAdapter;
        }

        @Override
        public void write(JsonWriter out, Metadata value) throws IOException {
            out.beginObject();
            if (value == null) {
                ClientLogger.d("Metadata null in write()");
                out.endObject();
                return;
            }
            if (value.connections != null) {
                out.name("connections");
                mConnectionCollectionTypeAdapter.write(out, value.connections);
            }
            if (value.interactions != null) {
                out.name("interactions");
                mInteractionCollectionTypeAdapter.write(out, value.interactions);
            }

            out.endObject();
        }

        @Override
        public Metadata read(JsonReader in) throws IOException {
            final Metadata metadata = new Metadata();
            in.beginObject();
            while (in.hasNext()) {
                String nextName = in.nextName();
                JsonToken jsonToken = in.peek();
                if (jsonToken == JsonToken.NULL) {
                    in.skipValue();
                    continue;
                }
                switch (nextName) {
                    case "connections":
                        metadata.connections = mConnectionCollectionTypeAdapter.read(in);
                        break;
                    case "interactions":
                        metadata.interactions = mInteractionCollectionTypeAdapter.read(in);
                        break;
                    default:
                        // skip any values that we do not account for, without this, the app will crash if the
                        // api provides more values than we have! [KZ] 6/15/16
                        in.skipValue();
                        break;
                }
            }
            in.endObject();
            return metadata;
        }
    }

    public static class Factory implements TypeAdapterFactory {

        @SuppressWarnings("unchecked")
        @Override
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
            if (Metadata.class.isAssignableFrom(type.getRawType())) {
                TypeAdapter collectionTypeAdapter = gson.getAdapter(ConnectionCollection.class);
                TypeAdapter interactionTypeAdapter = gson.getAdapter(InteractionCollection.class);
                if (collectionTypeAdapter instanceof ConnectionCollectionTypeAdapter &&
                    interactionTypeAdapter instanceof InteractionCollectionTypeAdapter) {
                    return (TypeAdapter<T>) new MetadataTypeAdapter(
                            (ConnectionCollectionTypeAdapter) collectionTypeAdapter,
                            (InteractionCollectionTypeAdapter) interactionTypeAdapter);
                }
            }

            // by returning null, Gson will never check this factory if it can handle this TypeToken again [KZ] 6/15/16
            return null;
        }
    }
}
