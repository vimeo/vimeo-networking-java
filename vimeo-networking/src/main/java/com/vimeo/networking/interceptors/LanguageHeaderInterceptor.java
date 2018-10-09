package com.vimeo.networking.interceptors;

import com.vimeo.networking.Vimeo;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Add custom {@code Accept-Language} header to all requests.
 * Created by harishveeramani on 6/13/18.
 */
public class LanguageHeaderInterceptor implements Interceptor {
    private static final String HEADER_SEPARATOR = ",";

    @NotNull
    private String mValidLocales;

    public LanguageHeaderInterceptor(@NotNull List<Locale> locales) {
        mValidLocales = parseLocales(locales);
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        return chain.proceed(
                chain.request().newBuilder().header(
                        Vimeo.HEADER_ACCEPT_LANGUAGE,
                        mValidLocales
                ).build()
        );
    }

    @NotNull
    private String parseLocales(@NotNull List<Locale> locales) {
        final StringBuilder codeBuilder = new StringBuilder();

        for (int i = 0; i < locales.size(); i++) {
            if (i > 0) {
                codeBuilder.append(HEADER_SEPARATOR);
            }
            codeBuilder.append(locales.get(i).getLanguage());
        }

        return codeBuilder.toString();
    }
}
