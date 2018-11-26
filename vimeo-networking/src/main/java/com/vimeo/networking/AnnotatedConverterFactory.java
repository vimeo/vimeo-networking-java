package com.vimeo.networking;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * This is a converter factory that allows you to specify different converters to use for each request.
 * This was taken from
 * https://stackoverflow.com/questions/40824122/android-retrofit-2-multiple-converters-gson-simplexml-error. It provides
 * two annotations - Gson and Moshi that should be used on a Retrofit request service to specify which serialization
 * framework to use. If a annotation is not specified, Gson will be used.
 */
public final class AnnotatedConverterFactory extends Converter.Factory {

    /**
     * Annotate a method to specify the type of serialization to use. Gson or Moshi.
     */
    @Retention(RetentionPolicy.RUNTIME)
    @interface Serializer {

        @NotNull
        ConverterType converter();

    }

    /**
     * Different types of converter that can be specified in the serializer annotation.
     */
    enum ConverterType {
        GSON,
        MOSHI
    }

    /**
     * Gson converter.
     */
    @NotNull
    private final Converter.Factory gsonFactory;

    /**
     * Moshi converter.
     */
    @NotNull
    private final Converter.Factory moshiFactory;

    /**
     * Specify all serialization converters that can be used in the SDK. The type of serialization
     * framework to use could be chosen by specifying it in the serializer annotation.
     *
     * @param gsonFactory  Gson converter factory.
     * @param moshiFactory Moshi converter factory.
     */
    AnnotatedConverterFactory(@NotNull final Converter.Factory gsonFactory,
                              @NotNull final Converter.Factory moshiFactory) {
        this.gsonFactory = gsonFactory;
        this.moshiFactory = moshiFactory;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(@NotNull final Type type,
                                                            @NotNull final Annotation[] annotations,
                                                            @NotNull final Retrofit retrofit) {
        return chooseFactoryForSerializer(annotations).responseBodyConverter(type, annotations, retrofit);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(@NotNull Type type,
                                                          @NotNull Annotation[] parameterAnnotations,
                                                          @NotNull Annotation[] methodAnnotations,
                                                          @NotNull Retrofit retrofit) {
        return chooseFactoryForSerializer(parameterAnnotations)
                .requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit);
    }

    /**
     * Choose the {@link Converter.Factory} that will be used based on the {@link Serializer} annotation.
     *
     * @param annotations The annotations array that may contain the {@link Serializer} annotation.
     */
    @NotNull
    private Converter.Factory chooseFactoryForSerializer(@NotNull final Annotation[] annotations) {
        final Serializer serializer = findSerializerInArray(annotations);
        if (serializer != null && serializer.converter() == ConverterType.MOSHI) {
            return moshiFactory;
        } else {
            return gsonFactory;
        }
    }

    /**
     * Return the {@link Serializer} annotation instance found in the array of {@link Annotation} or null if it was not
     * found.
     *
     * @param annotations The annotations array to search for the {@link Serializer} annotation.
     */
    @Nullable
    private static Serializer findSerializerInArray(@NotNull final Annotation[] annotations) {
        for (final Annotation annotation : annotations) {
            if (annotation instanceof Serializer) {
                return (Serializer) annotation;
            }
        }
        return null;
    }

}
