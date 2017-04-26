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
package com.vimeo.networking.utils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * An interceptor that modifies the base URL for
 * various requests. The base URL can be modified
 * on a per request basis.
 */
public final class BaseUrlInterceptor implements Interceptor {

    public BaseUrlInterceptor() {}

    @NotNull
    private final Map<String, String> mBaseUrlMap = new HashMap<>();

    /**
     * Sets the base URL used for requests
     * on a specific path.
     *
     * @param path    the path to redirect to a different host.
     * @param baseUrl the different host to use.
     */
    public void setBaseUrlForRequest(@NotNull String path, @Nullable String baseUrl) {
        if (!path.startsWith("/")) {
            path = '/' + path;
        }

        mBaseUrlMap.put(path, baseUrl);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        String baseUrl = mBaseUrlMap.get(request.url().encodedPath());
        if (baseUrl != null) {
            HttpUrl httpUrl = HttpUrl.parse(baseUrl);
            HttpUrl newUrl = request.url().newBuilder()
                    .host(httpUrl.host())
                    .scheme(httpUrl.scheme())
                    .username(httpUrl.username())
                    .password(httpUrl.password())
                    .port(httpUrl.port())
                    .build();
            request = request.newBuilder()
                    .url(newUrl)
                    .build();
        }
        return chain.proceed(request);
    }
}
