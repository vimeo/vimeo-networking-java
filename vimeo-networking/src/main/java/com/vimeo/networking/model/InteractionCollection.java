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
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.vimeo.networking.logging.ClientLogger;
import com.vimeo.networking.model.Interaction.InteractionTypeAdapter;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.Serializable;

/**
 * A collection of Interaction objects.
 * Created by zetterstromk on 6/5/15.
 */
public class InteractionCollection implements Serializable {

    private static final long serialVersionUID = 489519386122782640L;
    @Nullable
    @SerializedName("watchlater")
    public Interaction watchlater;
    @Nullable
    @SerializedName("like")
    public Interaction like;
    @Nullable
    @SerializedName("follow")
    public Interaction follow;
    @Nullable
    @SerializedName("buy")
    public Interaction buy;
    @Nullable
    @SerializedName("rent")
    public Interaction rent;
    @Nullable
    @SerializedName("subscribe")
    public Interaction subscribe;

    public static class InteractionCollectionTypeAdapter extends TypeAdapter<InteractionCollection> {

        @NotNull
        private final InteractionTypeAdapter mInteractionTypeAdapter;

        public InteractionCollectionTypeAdapter(@NotNull InteractionTypeAdapter interactionTypeAdapter) {
            mInteractionTypeAdapter = interactionTypeAdapter;
        }

        @Override
        public void write(JsonWriter out, InteractionCollection value) throws IOException {
            out.beginObject();
            if (value == null) {
                ClientLogger.d("InteractionCollection null in write()");
                out.endObject();
                return;
            }
            writeNestedInteraction(out, "watchlater", value.watchlater);
            writeNestedInteraction(out, "like", value.like);
            writeNestedInteraction(out, "follow", value.follow);
            writeNestedInteraction(out, "buy", value.buy);
            writeNestedInteraction(out, "rent", value.rent);
            writeNestedInteraction(out, "subscribe", value.subscribe);

            out.endObject();
        }

        @Override
        public InteractionCollection read(JsonReader in) throws IOException {
            final InteractionCollection interactionCollection = new InteractionCollection();
            in.beginObject();
            while (in.hasNext()) {
                String nextName = in.nextName();
                JsonToken jsonToken = in.peek();
                if (jsonToken == JsonToken.NULL) {
                    in.skipValue();
                    continue;
                }
                switch (nextName) {
                    case "watchlater":
                        interactionCollection.watchlater = mInteractionTypeAdapter.read(in);
                        break;
                    case "like":
                        interactionCollection.like = mInteractionTypeAdapter.read(in);
                        break;
                    case "follow":
                        interactionCollection.follow = mInteractionTypeAdapter.read(in);
                        break;
                    case "buy":
                        interactionCollection.buy = mInteractionTypeAdapter.read(in);
                        break;
                    case "rent":
                        interactionCollection.rent = mInteractionTypeAdapter.read(in);
                        break;
                    case "subscribe":
                        interactionCollection.subscribe = mInteractionTypeAdapter.read(in);
                        break;
                    default:
                        // skip any values that we do not account for, without this, the app will crash if the
                        // api provides more values than we have! [KZ] 6/15/16
                        in.skipValue();
                        break;
                }
            }
            in.endObject();
            return interactionCollection;
        }

        private void writeNestedInteraction(JsonWriter out, String name, @Nullable Interaction interaction)
                throws IOException {
            if (interaction != null) {
                out.name(name);
                mInteractionTypeAdapter.write(out, interaction);
            }
        }
    }

    public static class Factory implements TypeAdapterFactory {

        @SuppressWarnings("unchecked")
        @Override
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
            if (InteractionCollection.class.isAssignableFrom(type.getRawType())) {
                TypeAdapter typeAdapter = gson.getAdapter(Interaction.class);
                if (typeAdapter instanceof InteractionTypeAdapter) {
                    InteractionTypeAdapter interactionTypeAdapter = (InteractionTypeAdapter) typeAdapter;
                    return (TypeAdapter<T>) new InteractionCollectionTypeAdapter(interactionTypeAdapter);
                }
            }

            // by returning null, Gson will never check this factory if it can handle this TypeToken again [KZ] 6/15/16
            return null;
        }
    }
}
