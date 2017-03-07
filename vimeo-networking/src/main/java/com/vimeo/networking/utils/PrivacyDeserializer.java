package com.vimeo.networking.utils;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.vimeo.networking.model.Privacy;
import com.vimeo.networking.model.Privacy.PrivacyValue;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * (De)serializer for the Privacy class, since
 * we need custom handling for two fields in it
 * (download and add).
 * <p>
 * Created by restainoa on 2/1/17.
 */
@Deprecated
final class PrivacyDeserializer {

    private PrivacyDeserializer() {
    }

    /**
     * Handles deserialization for the broken Privacy class
     */
    @Deprecated
    static final class TypeAdapterFactory implements com.google.gson.TypeAdapterFactory {

        TypeAdapterFactory() {
        }

        @Override
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
            if (type.getRawType().equals(Privacy.class)) {
                return (TypeAdapter<T>) new PrivacyTypeAdapter(gson);
            }
            return null;
        }
    }

    /**
     * Handles deserialization for the broken Privacy class.
     * add and download fields are currently sent to us as
     * integers even though we need booleans. This class helps
     * us deserialize them correctly.
     */
    @Deprecated
    private static final class PrivacyTypeAdapter extends TypeAdapter<com.vimeo.networking.model.Privacy> {

        private final TypeAdapter<PrivacyValue> mPrivacyValueTypeAdapter;

        @SuppressWarnings("unchecked")
        PrivacyTypeAdapter(@NotNull Gson gsonInternal) {
            this.mPrivacyValueTypeAdapter = gsonInternal.getAdapter(PrivacyValue.class);
        }

        @Override
        public void write(JsonWriter writer, Privacy object) throws IOException {
            writer.beginObject();
            if (object == null) {
                writer.endObject();
                return;
            }

            if (object.mView != null) {
                writer.name("view");
                mPrivacyValueTypeAdapter.write(writer, object.mView);
            }

            if (object.mEmbed != null) {
                writer.name("embed");
                com.google.gson.internal.bind.TypeAdapters.STRING.write(writer, object.mEmbed);
            }

            writer.name("download");
            writer.value(object.mDownload);

            writer.name("add");
            writer.value(object.mAdd);

            if (object.mComments != null) {
                writer.name("comments");
                mPrivacyValueTypeAdapter.write(writer, object.mComments);
            }

            writer.endObject();
        }

        @Override
        public Privacy read(JsonReader reader) throws IOException {
            if (reader.peek() == com.google.gson.stream.JsonToken.NULL) {
                reader.nextNull();
                return null;
            }
            if (reader.peek() != com.google.gson.stream.JsonToken.BEGIN_OBJECT) {
                reader.skipValue();
                return null;
            }
            reader.beginObject();

            com.vimeo.networking.model.Privacy object = new com.vimeo.networking.model.Privacy();
            while (reader.hasNext()) {
                String name = reader.nextName();
                com.google.gson.stream.JsonToken jsonToken = reader.peek();
                if (jsonToken == com.google.gson.stream.JsonToken.NULL) {
                    reader.skipValue();
                    continue;
                }
                switch (name) {
                    case "view":
                        object.mView = mPrivacyValueTypeAdapter.read(reader);
                        break;
                    case "embed":
                        object.mEmbed = com.google.gson.internal.bind.TypeAdapters.STRING.read(reader);
                        break;
                    case "download":
                        if (jsonToken == JsonToken.NUMBER) {
                            object.mDownload = reader.nextInt() == 1;
                        } else {
                            object.mDownload = reader.nextBoolean();
                        }
                        break;
                    case "add":
                        if (jsonToken == JsonToken.NUMBER) {
                            object.mAdd = reader.nextInt() == 1;
                        } else {
                            object.mAdd = reader.nextBoolean();
                        }
                        break;
                    case "comments":
                        object.mComments = mPrivacyValueTypeAdapter.read(reader);
                        break;
                    default:
                        reader.skipValue();
                        break;
                }
            }

            reader.endObject();
            return object;
        }
    }

}
