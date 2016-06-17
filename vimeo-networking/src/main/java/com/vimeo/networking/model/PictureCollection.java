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
import com.vimeo.networking.model.Picture.PictureTypeAdapter;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Model representing a picture collection
 * Created by hanssena on 4/23/15.
 */
public class PictureCollection implements Serializable {

    private static final long serialVersionUID = -4495146309328278574L;
    public String uri;
    public boolean active;
    @SerializedName("default")
    public boolean isDefault;
    public ArrayList<Picture> sizes;

    public Picture pictureForWidth(int width) {
        if (sizes != null && !sizes.isEmpty()) {
            Picture selectedPicture = sizes.get(sizes.size() - 1);
            for (Picture picture : sizes) {
                if ((picture.width >= width) && ((picture.width - width) < (selectedPicture.width - width))) {
                    selectedPicture = picture;
                }
            }
            return selectedPicture;
        }

        return null;
    }

    public static class PictureCollectionTypeAdapter extends TypeAdapter<PictureCollection> {

        @NotNull
        private final PictureTypeAdapter mPictureTypeAdapter;

        public PictureCollectionTypeAdapter(@NotNull PictureTypeAdapter pictureTypeAdapter) {
            mPictureTypeAdapter = pictureTypeAdapter;
        }

        @Override
        public void write(JsonWriter out, PictureCollection value) throws IOException {
            out.beginObject();
            if (value == null) {
                ClientLogger.d("PictureCollection null in write()");
                out.endObject();
                return;
            }
            if (value.uri != null) {
                out.name("uri").value(value.uri);
            }
            out.name("active").value(value.active);
            out.name("default").value(value.isDefault);
            out.name("sizes").beginArray();
            if (value.sizes != null) {
                for (final Picture picture : value.sizes) {
                    mPictureTypeAdapter.write(out, picture);
                }
            }
            out.endArray();
            out.endObject();
        }

        @Override
        public PictureCollection read(JsonReader in) throws IOException {
            final PictureCollection pictureCollection = new PictureCollection();

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
                        pictureCollection.uri = in.nextString();
                        break;
                    case "active":
                        pictureCollection.active = in.nextBoolean();
                        break;
                    case "default":
                        pictureCollection.isDefault = in.nextBoolean();
                        break;
                    case "sizes":
                        in.beginArray();
                        pictureCollection.sizes = new ArrayList<>();
                        while (in.hasNext()) {
                            pictureCollection.sizes.add(mPictureTypeAdapter.read(in));
                        }
                        in.endArray();
                        break;
                    default:
                        // skip any values that we do not account for, without this, the app will crash if the
                        // api provides more values than we have! [KZ] 6/15/16
                        in.skipValue();
                        break;
                }
            }
            in.endObject();

            return pictureCollection;
        }
    }

    public static class Factory implements TypeAdapterFactory {

        @SuppressWarnings("unchecked")
        @Override
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
            if (PictureCollection.class.isAssignableFrom(type.getRawType())) {
                TypeAdapter typeAdapter = gson.getAdapter(Picture.class);
                if (typeAdapter instanceof PictureTypeAdapter) {
                    PictureTypeAdapter pictureTypeAdapter = (PictureTypeAdapter) typeAdapter;
                    return (TypeAdapter<T>) new PictureCollectionTypeAdapter(pictureTypeAdapter);
                }
            }

            // by returning null, Gson will never check this factory if it can handle this TypeToken again [KZ] 6/15/16
            return null;
        }
    }
}
