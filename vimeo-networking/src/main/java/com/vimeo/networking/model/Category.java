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
import com.vimeo.networking.model.PictureCollection.PictureCollectionTypeAdapter;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Model representing a category
 * Created by zetterstromk on 8/20/15.
 */
public class Category implements Serializable {

    private static final long serialVersionUID = 441419347585215353L;
    public String uri;
    public String name;
    public String link;
    public boolean topLevel;
    public PictureCollection pictures;
    public ArrayList<Category> subcategories;
    public Category parent;
    public Metadata metadata;

    // -----------------------------------------------------------------------------------------------------
    // Helper methods
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Helper methods">
    @Nullable
    public Connection getVideosConnection() {
        if (metadata != null && metadata.connections != null && metadata.connections.videos != null) {
            return metadata.connections.videos;
        }
        return null;
    }

    public int getVideoCount() {
        if (getVideosConnection() != null) {
            return getVideosConnection().total;
        }
        return 0;
    }

    @Nullable
    public Interaction getFollowInteraction() {
        if (metadata != null && metadata.interactions != null && metadata.interactions.follow != null) {
            return metadata.interactions.follow;
        }
        return null;
    }

    public boolean canFollow() {
        return getFollowInteraction() != null;
    }

    // getFollowInteraction checks for nulls so we can safely add this suppression
    @SuppressWarnings("ConstantConditions")
    public boolean isFollowing() {
        return getFollowInteraction() != null && metadata.interactions.follow.added;
    }

    @Nullable
    public Connection getUserConnection() {
        if (metadata != null && metadata.connections != null && metadata.connections.users != null) {
            return metadata.connections.users;
        }
        return null;
    }

    public int getFollowerCount() {
        if (getUserConnection() != null) {
            return getUserConnection().total;
        }
        return 0;
    }
    // </editor-fold>

    // -----------------------------------------------------------------------------------------------------
    // Comparison
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Comparison">
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Category that = (Category) o;

        return ((this.uri != null && that.uri != null) && this.uri.equals(that.uri));
    }

    @Override
    public int hashCode() {
        return this.uri != null ? this.uri.hashCode() : 0;
    }
    // </editor-fold>

    public static class CategoryTypeAdapter extends TypeAdapter<Category> {


        @NotNull
        private final PictureCollectionTypeAdapter mPictureCollectionTypeAdapter;
        @NotNull
        private final MetadataTypeAdapter mMetadataTypeAdapter;

        public CategoryTypeAdapter(@NotNull PictureCollectionTypeAdapter pictureCollectionTypeAdapter,
                                   @NotNull MetadataTypeAdapter metadataTypeAdapter) {
            mPictureCollectionTypeAdapter = pictureCollectionTypeAdapter;
            mMetadataTypeAdapter = metadataTypeAdapter;
        }

        @Override
        public void write(JsonWriter out, Category value) throws IOException {
            out.beginObject();
            if (value == null) {
                ClientLogger.d("Category null in write()");
                out.endObject();
                return;
            }
            if (value.uri != null) {
                out.name("uri").value(value.uri);
            }
            if (value.name != null) {
                out.name("name").value(value.name);
            }
            if (value.link != null) {
                out.name("link").value(value.link);
            }
            out.name("top_level").value(value.topLevel);
            if (value.pictures != null) {
                out.name("pictures");
                mPictureCollectionTypeAdapter.write(out, value.pictures);
            }
            if (value.subcategories != null) {
                out.name("sizes").beginArray();
                for (final Category subcategory : value.subcategories) {
                    write(out, subcategory);
                }
                out.endArray();
            }
            if (value.parent != null) {
                out.name("parent");
                write(out, value.parent);
            }
            if (value.metadata != null) {
                out.name("metadata");
                mMetadataTypeAdapter.write(out, value.metadata);
            }

            out.endObject();
        }

        @Override
        public Category read(JsonReader in) throws IOException {
            final Category category = new Category();
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
                        category.uri = in.nextString();
                        break;
                    case "name":
                        category.name = in.nextString();
                        break;
                    case "link":
                        category.link = in.nextString();
                        break;
                    case "top_level":
                        category.topLevel = in.nextBoolean();
                        break;
                    case "pictures":
                        category.pictures = mPictureCollectionTypeAdapter.read(in);
                        break;
                    case "subcategories":
                        in.beginArray();
                        category.subcategories = new ArrayList<>();
                        while (in.hasNext()) {
                            category.subcategories.add(read(in));
                        }
                        in.endArray();
                        break;
                    case "parent":
                        category.parent = read(in);
                        break;
                    case "metadata":
                        category.metadata = mMetadataTypeAdapter.read(in);
                        break;
                    default:
                        // skip any values that we do not account for, without this, the app will crash if the
                        // api provides more values than we have! [KZ] 6/15/16
                        in.skipValue();
                        break;
                }
            }
            in.endObject();
            return category;
        }
    }

    public static class Factory implements TypeAdapterFactory {

        @SuppressWarnings("unchecked")
        @Override
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            if (Category.class.isAssignableFrom(typeToken.getRawType())) {
                TypeAdapter pictureCollectionTypeAdapter = gson.getAdapter(PictureCollection.class);
                TypeAdapter metadataTypeAdapter = gson.getAdapter(Metadata.class);
                if (pictureCollectionTypeAdapter instanceof PictureCollectionTypeAdapter &&
                    metadataTypeAdapter instanceof MetadataTypeAdapter) {
                    return (TypeAdapter<T>) new CategoryTypeAdapter(
                            (PictureCollectionTypeAdapter) pictureCollectionTypeAdapter,
                            (MetadataTypeAdapter) metadataTypeAdapter);
                }
            }

            // by returning null, Gson will never check this factory if it can handle this TypeToken again [KZ] 6/15/16
            return null;
        }
    }
}
