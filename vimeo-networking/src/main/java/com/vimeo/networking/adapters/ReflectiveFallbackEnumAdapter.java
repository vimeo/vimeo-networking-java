package com.vimeo.networking.adapters;

import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.adapters.EnumJsonAdapter;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * A reflective {@link JsonAdapter} that extracts the default value from an {@link Enum} and then creates an
 * {@link EnumJsonAdapter} with an unknown fallback to which it delegates. This adapter is also distinguished from the
 * {@link EnumJsonAdapter} in that it parses {@code null} values as the default value. This will result in asymmetric
 * deserialization, as deserializing a null value and then serializing it will not result in the same JSON string.
 * However, the usage of a fallback in other cases is itself asymmetric so this is acceptable. An enum that is parsed
 * with this adapter must fill some important criteria.
 * <ul>
 * <li>The enum must have a default value available.</li>
 * <li>The default value must not have a {@link Json} annotation on it.</li>
 * </ul>
 */
class ReflectiveFallbackEnumAdapter<T extends Enum<T>> extends JsonAdapter<T> {

    @NotNull
    private final JsonAdapter<T> delegate;

    @NotNull
    private final T fallbackValue;

    ReflectiveFallbackEnumAdapter(@NotNull Class<T> enumType) {
        final T[] enumValues = enumType.getEnumConstants();
        T defaultValue = null;
        try {
            for (final T enumValue : enumValues) {
                final String constantName = enumValue.name();
                if (enumType.getField(constantName).getAnnotation(Json.class) == null) {
                    defaultValue = enumValue;
                    break;
                }
            }
        } catch (final NoSuchFieldException e) {
            throw new AssertionError("Missing field in " + enumType.getName(), e);
        }
        if (defaultValue == null) {
            throw new AssertionError("Missing UNKNOWN field in " + enumType.getName());
        }
        fallbackValue = defaultValue;
        delegate = EnumJsonAdapter.create(enumType).withUnknownFallback(defaultValue);
    }

    @Override
    public T fromJson(JsonReader reader) throws IOException {
        if (reader.peek() == JsonReader.Token.NULL) {
            return fallbackValue;
        }
        return delegate.fromJson(reader);
    }

    @Override
    public void toJson(JsonWriter writer, T value) throws IOException {
        delegate.toJson(writer, value);
    }

}
