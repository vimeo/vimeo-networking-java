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
import com.vimeo.networking.model.Connection.ConnectionTypeAdapter;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.Serializable;

/**
 * Stores a collection of Connection objects.
 * Created by hanssena on 4/23/15.
 */
public class ConnectionCollection implements Serializable {

    private static final long serialVersionUID = -4523270955994232839L;
    @Nullable
    @SerializedName("videos")
    public Connection videos;
    @Nullable
    @SerializedName("comments")
    public Connection comments;
    @Nullable
    @SerializedName("credits")
    public Connection credits;
    @Nullable
    @SerializedName("likes")
    public Connection likes;
    @Nullable
    @SerializedName("pictures")
    public Connection pictures;
    @Nullable
    @SerializedName("texttracks")
    public Connection texttracks;
    @Nullable
    @SerializedName("activities")
    public Connection activities;
    @Nullable
    @SerializedName("albums")
    public Connection albums;
    @Nullable
    @SerializedName("channels")
    public Connection channels;
    @Nullable
    @SerializedName("feed")
    public Connection feed;
    @Nullable
    @SerializedName("followers")
    public Connection followers;
    @Nullable
    @SerializedName("following")
    public Connection following;
    @Nullable
    @SerializedName("groups")
    public Connection groups;
    @Nullable
    @SerializedName("portfolios")
    public Connection portfolios;
    @Nullable
    @SerializedName("shared")
    public Connection shared;
    @Nullable
    @SerializedName("recommendations")
    public Connection recommendations;
    @Nullable
    @SerializedName("related")
    public Connection related;
    @Nullable
    @SerializedName("replies")
    public Connection replies;
    @Nullable
    @SerializedName("users")
    public Connection users;
    @Nullable
    @SerializedName("watchlater")
    public Connection watchlater;
    @Nullable
    @SerializedName("ondemand")
    public Connection ondemand;
    @Nullable
    @SerializedName("trailer")
    public Connection trailer;

    public static class ConnectionCollectionTypeAdapter extends TypeAdapter<ConnectionCollection> {

        @NotNull
        private final ConnectionTypeAdapter mConnectionTypeAdapter;

        public ConnectionCollectionTypeAdapter(@NotNull ConnectionTypeAdapter connectionTypeAdapter) {
            mConnectionTypeAdapter = connectionTypeAdapter;
        }

        @Override
        public void write(JsonWriter out, ConnectionCollection value) throws IOException {
            out.beginObject();
            if (value == null) {
                ClientLogger.d("ConnectionCollection null in write()");
                out.endObject();
                return;
            }
            writeNestedConnection(out, "videos", value.videos);
            writeNestedConnection(out, "comments", value.comments);
            writeNestedConnection(out, "credits", value.credits);
            writeNestedConnection(out, "likes", value.likes);
            writeNestedConnection(out, "pictures", value.pictures);
            writeNestedConnection(out, "texttracks", value.texttracks);
            writeNestedConnection(out, "activities", value.activities);
            writeNestedConnection(out, "albums", value.albums);
            writeNestedConnection(out, "channels", value.channels);
            writeNestedConnection(out, "feed", value.feed);
            writeNestedConnection(out, "followers", value.followers);
            writeNestedConnection(out, "following", value.following);
            writeNestedConnection(out, "groups", value.groups);
            writeNestedConnection(out, "portfolios", value.portfolios);
            writeNestedConnection(out, "shared", value.shared);
            writeNestedConnection(out, "recommendations", value.recommendations);
            writeNestedConnection(out, "related", value.related);
            writeNestedConnection(out, "replies", value.replies);
            writeNestedConnection(out, "users", value.users);
            writeNestedConnection(out, "watchlater", value.watchlater);
            writeNestedConnection(out, "ondemand", value.ondemand);
            writeNestedConnection(out, "trailer", value.trailer);
            out.endObject();
        }

        @Override
        public ConnectionCollection read(JsonReader in) throws IOException {
            final ConnectionCollection connectionCollection = new ConnectionCollection();
            in.beginObject();
            while (in.hasNext()) {
                String nextName = in.nextName();
                JsonToken jsonToken = in.peek();
                if (jsonToken == JsonToken.NULL) {
                    in.skipValue();
                    continue;
                }
                switch (nextName) {
                    case "videos":
                        connectionCollection.videos = mConnectionTypeAdapter.read(in);
                        break;
                    case "comments":
                        connectionCollection.comments = mConnectionTypeAdapter.read(in);
                        break;
                    case "credits":
                        connectionCollection.credits = mConnectionTypeAdapter.read(in);
                        break;
                    case "likes":
                        connectionCollection.likes = mConnectionTypeAdapter.read(in);
                        break;
                    case "pictures":
                        connectionCollection.pictures = mConnectionTypeAdapter.read(in);
                        break;
                    case "texttracks":
                        connectionCollection.texttracks = mConnectionTypeAdapter.read(in);
                        break;
                    case "activities":
                        connectionCollection.activities = mConnectionTypeAdapter.read(in);
                        break;
                    case "albums":
                        connectionCollection.albums = mConnectionTypeAdapter.read(in);
                        break;
                    case "channels":
                        connectionCollection.channels = mConnectionTypeAdapter.read(in);
                        break;
                    case "feed":
                        connectionCollection.feed = mConnectionTypeAdapter.read(in);
                        break;
                    case "followers":
                        connectionCollection.followers = mConnectionTypeAdapter.read(in);
                        break;
                    case "following":
                        connectionCollection.following = mConnectionTypeAdapter.read(in);
                        break;
                    case "groups":
                        connectionCollection.groups = mConnectionTypeAdapter.read(in);
                        break;
                    case "portfolios":
                        connectionCollection.portfolios = mConnectionTypeAdapter.read(in);
                        break;
                    case "shared":
                        connectionCollection.shared = mConnectionTypeAdapter.read(in);
                        break;
                    case "recommendations":
                        connectionCollection.recommendations = mConnectionTypeAdapter.read(in);
                        break;
                    case "related":
                        connectionCollection.related = mConnectionTypeAdapter.read(in);
                        break;
                    case "replies":
                        connectionCollection.replies = mConnectionTypeAdapter.read(in);
                        break;
                    case "users":
                        connectionCollection.users = mConnectionTypeAdapter.read(in);
                        break;
                    case "watchlater":
                        connectionCollection.watchlater = mConnectionTypeAdapter.read(in);
                        break;
                    case "ondemand":
                        connectionCollection.ondemand = mConnectionTypeAdapter.read(in);
                        break;
                    case "trailer":
                        connectionCollection.trailer = mConnectionTypeAdapter.read(in);
                        break;
                    default:
                        // skip any values that we do not account for, without this, the app will crash if the
                        // api provides more values than we have! [KZ] 6/15/16
                        in.skipValue();
                        break;
                }
            }
            in.endObject();
            return connectionCollection;
        }

        private void writeNestedConnection(JsonWriter out, String name, @Nullable Connection connection)
                throws IOException {
            if (connection != null) {
                out.name(name);
                mConnectionTypeAdapter.write(out, connection);
            }
        }
    }

    public static class Factory implements TypeAdapterFactory {

        @SuppressWarnings("unchecked")
        @Override
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
            if (ConnectionCollection.class.isAssignableFrom(type.getRawType())) {
                TypeAdapter typeAdapter = gson.getAdapter(Connection.class);
                if (typeAdapter instanceof ConnectionTypeAdapter) {
                    ConnectionTypeAdapter connectionTypeAdapter = (ConnectionTypeAdapter) typeAdapter;
                    return (TypeAdapter<T>) new ConnectionCollectionTypeAdapter(connectionTypeAdapter);
                }
            }

            // by returning null, Gson will never check this factory if it can handle this TypeToken again [KZ] 6/15/16
            return null;
        }
    }
}
