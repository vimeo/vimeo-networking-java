package com.vimeo.networking.interceptors;

import com.vimeo.networking.Vimeo;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Add custom {@code Accept-Language} header to all requests.
 * Created by harishveeramani on 6/13/18.
 */

public class LanguageHeaderInterceptor implements Interceptor {
    String validLocales;

    public LanguageHeaderInterceptor(String validLocales) {
        this.validLocales = validLocales;
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        return chain.proceed(
                chain.request().newBuilder().header(
                        Vimeo.HEADER_ACCEPT_LANGUAGE,
                        getAcceptLanguageHeader()
                ).build()
        );
    }

    private String getAcceptLanguageHeader() {
        return validLocales;
    }
}
