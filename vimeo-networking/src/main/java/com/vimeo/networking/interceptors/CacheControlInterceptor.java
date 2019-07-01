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

package com.vimeo.networking.interceptors;

import com.vimeo.networking.Configuration;
import com.vimeo.networking.Vimeo;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Rewrite the server's cache-control header because our server sets all {@code Cache-Control} headers
 * to {@code no-store}. To get data from the cache, we set a max age to
 * {@link Configuration#getCacheMaxAge()}. This prevents OkHttp from throwing a 504 exception.
 * API no longer sends a max age in the header. OkHttp determines the cache to be invalid. This is a work
 * around for this issue.
 */
public class CacheControlInterceptor implements Interceptor {

    private final int mMaxAge;

    public CacheControlInterceptor(final int maxAge) {
        this.mMaxAge = maxAge;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        final String cacheControlValue = Vimeo.HEADER_CACHE_PUBLIC + ", max-age=" + mMaxAge;
        return chain.proceed(chain.request())
                .newBuilder()
                .header(Vimeo.HEADER_CACHE_CONTROL, cacheControlValue)
                .build();
    }
}
