package com.vimeo.networking;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Converter.Factory;
import retrofit2.Retrofit;

/**
 * This is a converter factory that allows you to specify different converters to use for each request.
 * This was taken from https://stackoverflow.com/questions/40824122/android-retrofit-2-multiple-converters-gson-simplexml-error.
 * It provides two annotations - Gson and Moshi that should be used on a Retrofit request service to specify which
 * serialization framework to use.
 * <p>
 * Ex:
 *
 * @<code>
 *
 *  @GET
 *  @Moshi Call<com.vimeo.networking2.VideoList> getVideoList(
 *  @Header("Authorization") String authHeader,
 *  @Url String uri,
 *  @QueryMap Map<String, String> options,
 *  @Header("Cache-Control") String cacheHeaderValue);
 *
 * </code>
 *
 * If a annotation is not specified, Gson will be used.
 */
public final class AnnotatedConverterFactory extends Converter.Factory {

    @Retention(RetentionPolicy.RUNTIME)
    @interface Gson {}

    @Retention(RetentionPolicy.RUNTIME)
    @interface Moshi {}

    private final Map<Class<?>, Converter.Factory> mFactoryMap;

    private AnnotatedConverterFactory(final Map<Class<?>, Converter.Factory> factoryMap) {
        mFactoryMap = factoryMap;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(final Type type,
                                                            final Annotation[] annotations,
                                                            final Retrofit retrofit) {
        for (final Annotation annotation : annotations) {
            final Converter.Factory factory = mFactoryMap.get(annotation.annotationType());
            if (factory != null) {
                System.out.println(factory);
                return factory.responseBodyConverter(type, annotations, retrofit);
            }
        }

        //try to default to gson in case no annotation on current method was found
        final Converter.Factory gsonFactory = mFactoryMap.get(Gson.class);
        System.out.println(gsonFactory);
        if (gsonFactory != null) {
            return gsonFactory.responseBodyConverter(type, annotations, retrofit);
        }
        return null;
    }

    static class Builder {

        Map<Class<?>, Factory> mFactoryMap = new LinkedHashMap<>();

        Builder add(final Class<? extends Annotation> factoryType, final Converter.Factory factory) {
            if (factoryType == null) {
                throw new NullPointerException("factoryType is null");
            }
            if (factory == null) {
                throw new NullPointerException("factory is null");
            }
            mFactoryMap.put(factoryType, factory);
            return this;
        }

        public AnnotatedConverterFactory build() {
            return new AnnotatedConverterFactory(mFactoryMap);
        }

    }
}
