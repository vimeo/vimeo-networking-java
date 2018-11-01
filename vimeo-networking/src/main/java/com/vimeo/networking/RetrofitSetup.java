/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vimeo
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

package com.vimeo.networking;

import com.google.gson.Gson;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter;
import com.vimeo.networking.interceptors.AcceptHeaderInterceptor;
import com.vimeo.networking.interceptors.CacheControlInterceptor;
import com.vimeo.networking.interceptors.UserAgentInterceptor;
import com.vimeo.networking.logging.ClientLogger;
import com.vimeo.networking.logging.LoggingInterceptor;
import com.vimeo.networking.utils.VimeoNetworkUtil;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * Retrofit setup code.  Used to create an instance of {@link Retrofit} to make API requests.
 * Created by brentwatson on 8/7/17.
 */
class RetrofitSetup {

    /**
     * Configuration object used to pass timeout, interceptors, etc for Retrofit setup.
     */
    @NotNull
    private final Configuration mConfiguration;

    /**
     * OkHttp {@link Cache} implementation used to cache API request/response values.
     */
    @Nullable
    private final Cache mCache;

    /**
     * {@link Gson} object used to marshal / unmarshal JSON responses.
     */
    @NotNull
    private final Gson mGson;

    /**
     * Value appended to {@code User-Agent} header to identify which version of this library is used.
     */
    @NotNull
    private final String mLibraryUserAgentComponent;

    RetrofitSetup(@NotNull Configuration configuration, @Nullable Cache cache) {
        mConfiguration = configuration;
        mCache = cache;
        mGson = VimeoNetworkUtil.getGson();
        mLibraryUserAgentComponent = "VimeoNetworking/" + BuildConfig.VERSION + " (Java)";
    }

    /**
     * @return a functional instance of {@link Retrofit} that can be used to make requests to the
     * Vimeo API endpoints, with appropriate interceptors, timeouts, and cache configured.
     */
    @NotNull
    @SuppressWarnings("WeakerAccess")
    public Retrofit createRetrofit() {
        return new Retrofit.Builder().baseUrl(mConfiguration.getBaseUrl())
                .client(createOkHttpClient())
                .addConverterFactory(new AnnotatedConverterFactory(GsonConverterFactory.create(mGson),
                                                                   MoshiConverterFactory.create(createMoshi())))
                .build();
    }

    /**
     * Create an instance of Moshi with a date adapter.
     */
    @NotNull
    private Moshi createMoshi() {
        return new Moshi.Builder().add(Date.class, new Rfc3339DateJsonAdapter().nullSafe()).build();
    }

    /**
     * OkHttp setup.
     */
    @NotNull OkHttpClient createOkHttpClient() {
        final RetrofitClientBuilder retrofitClientBuilder = new RetrofitClientBuilder();
        if (mCache != null) {
            retrofitClientBuilder.setCache(mCache);
        }
        retrofitClientBuilder.addNetworkInterceptor(new CacheControlInterceptor())
                .setReadTimeout(mConfiguration.mTimeout, TimeUnit.SECONDS)
                .setConnectionTimeout(mConfiguration.mTimeout, TimeUnit.SECONDS)
                .addInterceptor(new LoggingInterceptor())
                .addInterceptor(new UserAgentInterceptor(createUserAgent()))
                .addInterceptor(new AcceptHeaderInterceptor())
                .addNetworkInterceptors(mConfiguration.mNetworkInterceptors)
                .addInterceptors(mConfiguration.mInterceptors);

        setupCertPinning(retrofitClientBuilder);
        return retrofitClientBuilder.build();
    }

    /**
     * Try and pin certificates to prevent man-in-the-middle attacks (if pinning is enabled)
     */
    private void setupCertPinning(@NotNull RetrofitClientBuilder retrofitClientBuilder) {
        if (mConfiguration.mCertPinningEnabled) {
            try {
                retrofitClientBuilder.pinCertificates();
            } catch (final Exception e) {
                ClientLogger.e("Exception when pinning certificate: " + e.getMessage(), e);
            }
        }
    }

    /**
     * @return value used for {@code User-Agent} HTTP header value.
     */
    @NotNull String createUserAgent() {
        final String userProvidedAgent = mConfiguration.getUserAgentString();

        if (userProvidedAgent != null
            && !userProvidedAgent.isEmpty()
            && isValidUserAgent(userProvidedAgent)) {
            return userProvidedAgent + ' ' + mLibraryUserAgentComponent;
        } else {
            return mLibraryUserAgentComponent;
        }
    }

    /**
     * Determines if the provided {@code userAgent} is valid and can be sent in an HTTP request.
     *
     * @param userAgent the user agent to check.
     * @return true if the user agent is valid, false if it contains invalid characters.
     */
    private boolean isValidUserAgent(@NotNull final String userAgent) {
        try {
            new Headers.Builder().set("User-Agent", userAgent);
            return true;
        } catch (IllegalArgumentException ignored) {
            return false;
        }
    }
}
