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
import com.vimeo.networking.Vimeo;
import com.vimeo.networking.logging.ClientLogger;
import com.vimeo.networking.model.Metadata.MetadataTypeAdapter;
import com.vimeo.networking.model.User.UserTypeAdapter;
import com.vimeo.networking.utils.EnumTypeAdapter;
import com.vimeo.networking.utils.ISO8601;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

/**
 * Model representing comments
 * <p/>
 * Created by zetterstromk on 7/31/15.
 */
public class Comment implements Serializable {

    private static final long serialVersionUID = -7716027694845877155L;

    public enum CommentType {
        @SerializedName("video")
        VIDEO
        //TODO get the other comment types and put them here [KZ]
    }

    public String uri;
    public CommentType type;
    public Date createdOn;
    public String text;
    public User user;
    public Metadata metadata;

    public int replyCount() {
        if ((metadata != null) && (metadata.connections != null) && (metadata.connections.replies != null)) {
            return metadata.connections.replies.total;
        }
        return 0;
    }

    public boolean canReply() {
        return (metadata != null) && (metadata.connections != null) &&
               (metadata.connections.replies != null) && (metadata.connections.replies.options != null) &&
               metadata.connections.replies.options.contains(Vimeo.OPTIONS_POST);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Comment that = (Comment) o;

        return ((this.uri != null && that.uri != null) && this.uri.equals(that.uri));
    }

    @Override
    public int hashCode() {
        return this.uri != null ? this.uri.hashCode() : 0;
    }

    public static class CommentTypeAdapter extends TypeAdapter<Comment> {

        private final static EnumTypeAdapter<CommentType> sCommentTypeEnumTypeAdapter =
                new EnumTypeAdapter<>(CommentType.class);

        @NotNull
        private final UserTypeAdapter mUserTypeAdapter;
        @NotNull
        private final MetadataTypeAdapter mMetadataTypeAdapter;

        public CommentTypeAdapter(@NotNull UserTypeAdapter userTypeAdapter,
                                  @NotNull MetadataTypeAdapter metadataTypeAdapter) {
            mUserTypeAdapter = userTypeAdapter;
            mMetadataTypeAdapter = metadataTypeAdapter;
        }

        @Override
        public void write(JsonWriter out, Comment value) throws IOException {
            out.beginObject();
            if (value == null) {
                ClientLogger.d("Comment null in write()");
                out.endObject();
                return;
            }
            if (value.uri != null) {
                out.name("uri").value(value.uri);
            }
            if (value.type != null) {
                out.name("type");
                sCommentTypeEnumTypeAdapter.write(out, value.type);
            }
            if (value.createdOn != null) {
                out.name("created_on").value(ISO8601.fromDate(value.createdOn));
            }
            if (value.text != null) {
                out.name("text").value(value.text);
            }
            if (value.user != null) {
                out.name("user");
                mUserTypeAdapter.write(out, value.user);
            }
            if (value.metadata != null) {
                out.name("metadata");
                mMetadataTypeAdapter.write(out, value.metadata);
            }

            out.endObject();
        }

        @Override
        public Comment read(JsonReader in) throws IOException {
            final Comment comment = new Comment();
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
                        comment.uri = in.nextString();
                        break;
                    case "text":
                        comment.text = in.nextString();
                        break;
                    case "type":
                        comment.type = sCommentTypeEnumTypeAdapter.read(in);
                        break;
                    case "created_on":
                        try {
                            comment.createdOn = ISO8601.toDate(in.nextString());
                        } catch (ParseException e) {
                            ClientLogger.e("Error parsing Comment date", e);
                        }
                        break;
                    case "user":
                        comment.user = mUserTypeAdapter.read(in);
                        break;
                    case "metadata":
                        comment.metadata = mMetadataTypeAdapter.read(in);
                        break;
                    default:
                        // skip any values that we do not account for, without this, the app will crash if the
                        // api provides more values than we have! [KZ] 6/15/16
                        in.skipValue();
                        break;
                }
            }
            in.endObject();
            return comment;
        }
    }

    public static class Factory implements TypeAdapterFactory {

        @SuppressWarnings("unchecked")
        @Override
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            if (Comment.class.isAssignableFrom(typeToken.getRawType())) {
                TypeAdapter userTypeAdapter = gson.getAdapter(User.class);
                TypeAdapter metadataTypeAdapter = gson.getAdapter(Metadata.class);
                if (userTypeAdapter instanceof UserTypeAdapter &&
                    metadataTypeAdapter instanceof MetadataTypeAdapter) {
                    return (TypeAdapter<T>) new CommentTypeAdapter((UserTypeAdapter) userTypeAdapter,
                                                                   (MetadataTypeAdapter) metadataTypeAdapter);
                }
            }

            // by returning null, Gson will never check this factory if it can handle this TypeToken again [KZ] 6/15/16
            return null;
        }
    }
}
