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
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

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

    private byte mState = 0;

    @Nullable
    private HttpUrl mNewBaseUrl = null;

    @NotNull
    private final Set<String> mIncludeOrExclude = new HashSet<>();

    @NotNull
    private static Collection<String> sanitizePaths(@NotNull String[] paths) {
        Collection<String> sanitizedPaths = new ArrayList<>();

        for (String path : paths) {
            if (!path.startsWith("/")) {
                path = '/' + path;
            }
            sanitizedPaths.add(path);
        }

        return sanitizedPaths;
    }

    /**
     * Sets the base URL for requests based on an
     * inclusivity principle. Only future requests that
     * are included in the supplied list of paths will
     * have their base URL replaced. Call {@link #resetBaseUrl()}
     * to reset to the original base URL for all paths.
     * Any call to this method will overwrite the previously
     * used base URL, but will not erase inclusions.
     *
     * @param baseUrl    the new base URL.
     * @param inclusions the list of paths to be included,
     *                   if you don't pass anything no
     *                   base URLs will be overridden.
     */
    public void includePathsForBaseUrl(@NotNull HttpUrl baseUrl, @NotNull String... inclusions) {
        Preconditions.checkIsTrue(mState == 0 || mState == 1,
                                  "resetBaseUrl() must be called before switching from exclude to include");
        mState = 1;
        mNewBaseUrl = baseUrl;
        mIncludeOrExclude.addAll(sanitizePaths(inclusions));
    }

    /**
     * Sets the base URL for all requests based on an
     * exclusivity principle. All future requests will
     * use the new base URL unless they are to a path
     * supplied as an exclusion. Call {@link #resetBaseUrl()}
     * to reset to the original base URL for all paths.
     * Any call to this method will overwrite the previously
     * used base URL, but will not erase exclusions.
     *
     * @param baseUrl    the new base URL.
     * @param exclusions the list of paths to be excluded,
     *                   don't pass anything if you wish to
     *                   override all URLs.
     */
    public void excludePathsForBaseUrl(@NotNull HttpUrl baseUrl, @NotNull String... exclusions) {
        Preconditions.checkIsTrue(mState == 0 || mState == 2,
                                  "resetBaseUrl() must be called before switching from include to exclude");
        mState = 2;
        mNewBaseUrl = baseUrl;
        mIncludeOrExclude.addAll(sanitizePaths(exclusions));
    }

    /**
     * Resets the base URL. Required to be called
     * after {@link #includePathsForBaseUrl(HttpUrl, String...)}
     * of {@link #excludePathsForBaseUrl(HttpUrl, String...)} if
     * you wish to switch between include/exclude mode or if you
     * wish to stop overriding the base URL.
     */
    public void resetBaseUrl() {
        mState = 0;
        mNewBaseUrl = null;
        mIncludeOrExclude.clear();
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        HttpUrl baseUrl;

        switch (mState) {
            case 1:
                baseUrl = mIncludeOrExclude.contains(request.url().encodedPath()) ? mNewBaseUrl : null;
                break;
            case 2:
                baseUrl = mIncludeOrExclude.contains(request.url().encodedPath()) ? null : mNewBaseUrl;
                break;
            default:
                baseUrl = null;
                break;
        }

        if (baseUrl != null) {
            HttpUrl newUrl = request.url().newBuilder()
                    .host(baseUrl.host())
                    .scheme(baseUrl.scheme())
                    .username(baseUrl.username())
                    .password(baseUrl.password())
                    .port(baseUrl.port())
                    .build();
            request = request.newBuilder()
                    .url(newUrl)
                    .build();
        }
        return chain.proceed(request);
    }
}
