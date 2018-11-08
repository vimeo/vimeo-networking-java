package com.vimeo.networking;

import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * This is a converter factory that allows you to specify different converters to use for each request.
 * This was taken from https://stackoverflow.com/questions/40824122/android-retrofit-2-multiple-converters-gson-simplexml-error.
 * It provides two annotations - Gson and Moshi that should be used on a Retrofit request service to specify which
 * serialization framework to use. If a annotation is not specified, Gson will be used.
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
        Converter.Factory converterFactory = gsonFactory;

        for (final Annotation annotation : annotations) {
            if (annotation instanceof Serializer) {
                final ConverterType converterType = ((Serializer) annotation).converter();
                if (converterType == ConverterType.MOSHI) {
                    converterFactory = moshiFactory;
                    break;
                }
            }
        }
        return converterFactory.responseBodyConverter(type, annotations, retrofit);
    }

}
