package com.vimeo.networking.adapters;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Set;

/**
 * A {@link JsonAdapter.Factory} that creates a {@link ReflectiveFallbackEnumAdapter} for any {@link Type} that is
 * an enum.
 */
public class ReflectiveFallbackEnumAdapterFactory implements JsonAdapter.Factory {

    @Override
    public JsonAdapter<?> create(Type type, Set<? extends Annotation> annotations, Moshi moshi) {
        final Class<?> rawType = Types.getRawType(type);

        if (!rawType.isEnum()) {
            return null;
        }

        final Class<Enum> enumType = (Class<Enum>) rawType;

        return new ReflectiveFallbackEnumAdapter<>(enumType).lenient();
    }
}
