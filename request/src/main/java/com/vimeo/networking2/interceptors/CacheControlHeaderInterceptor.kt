/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2020 Vimeo
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
package com.vimeo.networking2.interceptors

import com.vimeo.networking2.Configuration
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Rewrite the server's cache-control header because our server sets all `Cache-Control` headers to `no-store`. To get
 * data from the cache, we set a max age to [Configuration.cacheMaxAgeSeconds]. Normally the API indicates that the
 * contents should not be cached. Since most clients want common requests to be available via cache for performance
 * reasons, we overwrite this behavior and set our own [maxAgeSeconds] expiration.
 *
 * @param maxAgeSeconds The max age of the cache before expiration in seconds.
 */
class CacheControlHeaderInterceptor(private val maxAgeSeconds: Int) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response =
            chain.proceed(chain.request())
                    .newBuilder()
                    .header(HEADER_CACHE_CONTROL, "$HEADER_CACHE_PUBLIC, max-age=$maxAgeSeconds")
                    .build()

    companion object {
        private const val HEADER_CACHE_CONTROL = "Cache-Control"
        private const val HEADER_CACHE_PUBLIC = "public"
    }
}
