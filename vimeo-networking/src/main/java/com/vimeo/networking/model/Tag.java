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
import com.vimeo.networking.model.Metadata.MetadataTypeAdapter;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.Serializable;

/**
 * Model for tags
 * Created by hanssena on 4/23/15.
 */
public class Tag implements Serializable {

    private static final long serialVersionUID = 3388947522077930006L;
    public String uri;
    public String name;
    public String tag;
    public String canonical;
    public Metadata metadata;

    public static class TagTypeAdapter extends TypeAdapter<Tag> {

        @NotNull
        private final MetadataTypeAdapter mMetadataTypeAdapter;

        public TagTypeAdapter(@NotNull MetadataTypeAdapter metadataTypeAdapter) {
            mMetadataTypeAdapter = metadataTypeAdapter;
        }

        @Override
        public void write(JsonWriter out, Tag value) throws IOException {
            out.beginObject();
            if (value == null) {
                ClientLogger.d("Tag null in write()");
                out.endObject();
                return;
            }
            if (value.uri != null) {
                out.name("uri").value(value.uri);
            }
            if (value.name != null) {
                out.name("name").value(value.name);
            }
            if (value.tag != null) {
                out.name("tag").value(value.tag);
            }
            if (value.canonical != null) {
                out.name("canonical").value(value.canonical);
            }
            if (value.metadata != null) {
                out.name("metadata");
                mMetadataTypeAdapter.write(out, value.metadata);
            }

            out.endObject();
        }

        @Override
        public Tag read(JsonReader in) throws IOException {
            final Tag theTag = new Tag();
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
                        theTag.uri = in.nextString();
                        break;
                    case "name":
                        theTag.name = in.nextString();
                        break;
                    case "tag":
                        theTag.tag = in.nextString();
                        break;
                    case "canonical":
                        theTag.canonical = in.nextString();
                        break;
                    case "metadata":
                        theTag.metadata = mMetadataTypeAdapter.read(in);
                        break;
                    default:
                        // skip any values that we do not account for, without this, the app will crash if the
                        // api provides more values than we have! [KZ] 6/15/16
                        in.skipValue();
                        break;
                }
            }
            in.endObject();
            return theTag;
        }
    }

    public static class Factory implements TypeAdapterFactory {

        @SuppressWarnings("unchecked")
        @Override
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            if (Tag.class.isAssignableFrom(typeToken.getRawType())) {
                TypeAdapter metadataTypeAdapter = gson.getAdapter(Metadata.class);
                if (metadataTypeAdapter instanceof MetadataTypeAdapter) {
                    return (TypeAdapter<T>) new TagTypeAdapter((MetadataTypeAdapter) metadataTypeAdapter);
                }
            }

            // by returning null, Gson will never check this factory if it can handle this TypeToken again [KZ] 6/15/16
            return null;
        }
    }
}
