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
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import okhttp3.Connection;
import okhttp3.HttpUrl;
import okhttp3.Interceptor.Chain;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Tests for {@link BaseUrlInterceptor}.
 * <p>
 * Created by restainoa on 4/27/17.
 */
public class BaseUrlInterceptorTest {

    private BaseUrlInterceptor mBaseUrlInterceptor;

    @Before
    public void setUp() throws Exception {
        mBaseUrlInterceptor = new BaseUrlInterceptor();
    }

    @NotNull
    private static Chain createVerificationChain(@NotNull final HttpUrl originalUrl, @NotNull final HttpUrl proceedUrl) {
        return new Chain() {
            @Override
            public Request request() {
                return new Request.Builder().url(originalUrl).build();
            }

            @Override
            public Response proceed(Request request) throws IOException {
                Assert.assertEquals(proceedUrl.host(), request.url().host());
                return null;
            }

            @Override
            public Connection connection() {
                return null;
            }
        };
    }

    @NotNull
    private static HttpUrl createTestUrlForPath(@Nullable String path) {
        StringBuilder url = new StringBuilder("http://test.vimeo.com");
        if (path != null) {
            url.append(path);
        }
        return HttpUrl.parse(url.toString());
    }

    @Test
    public void test_includePathsForBaseUrl_PreSanitizedPaths_SuccessfullyIntercepts() throws Exception {
        HttpUrl httpUrl = HttpUrl.parse("http://localhost");
        mBaseUrlInterceptor.includePathsForBaseUrl(httpUrl, "/me", "/you");

        mBaseUrlInterceptor.intercept(createVerificationChain(createTestUrlForPath("/me"), httpUrl));
        mBaseUrlInterceptor.intercept(createVerificationChain(createTestUrlForPath("/you"), httpUrl));
        mBaseUrlInterceptor.intercept(createVerificationChain(createTestUrlForPath("/them"), createTestUrlForPath(null)));
    }

    @Test
    public void test_includePathsForBaseUrl_UnsanitizedPaths_SuccessfullyIntercepts() throws Exception {
        HttpUrl httpUrl = HttpUrl.parse("http://localhost");
        mBaseUrlInterceptor.includePathsForBaseUrl(httpUrl, "me", "you");

        mBaseUrlInterceptor.intercept(createVerificationChain(createTestUrlForPath("/me"), httpUrl));
        mBaseUrlInterceptor.intercept(createVerificationChain(createTestUrlForPath("/you"), httpUrl));
        mBaseUrlInterceptor.intercept(createVerificationChain(createTestUrlForPath("/them"), createTestUrlForPath(null)));
    }

    @Test
    public void test_includePathsForBaseUrl_EmptyPaths_UnsuccessfullyIntercepts() throws Exception {
        HttpUrl httpUrl = HttpUrl.parse("http://localhost");
        mBaseUrlInterceptor.includePathsForBaseUrl(httpUrl);

        mBaseUrlInterceptor.intercept(createVerificationChain(createTestUrlForPath("/me"), createTestUrlForPath(null)));
        mBaseUrlInterceptor.intercept(createVerificationChain(createTestUrlForPath("/you"), createTestUrlForPath(null)));
        mBaseUrlInterceptor.intercept(createVerificationChain(createTestUrlForPath("/them"), createTestUrlForPath(null)));
    }

    @Test
    public void test_excludePathsForBaseUrl_PreSanitizedPaths_SuccessfullyIntercepts() throws Exception {
        HttpUrl httpUrl = HttpUrl.parse("http://localhost");
        mBaseUrlInterceptor.excludePathsForBaseUrl(httpUrl, "/me", "/you");

        mBaseUrlInterceptor.intercept(createVerificationChain(createTestUrlForPath("/me"), createTestUrlForPath(null)));
        mBaseUrlInterceptor.intercept(createVerificationChain(createTestUrlForPath("/you"), createTestUrlForPath(null)));
        mBaseUrlInterceptor.intercept(createVerificationChain(createTestUrlForPath("/them"), httpUrl));
    }

    @Test
    public void test_excludePathsForBaseUrl_UnsanitizedPaths_SuccessfullyIntercepts() throws Exception {
        HttpUrl httpUrl = HttpUrl.parse("http://localhost");
        mBaseUrlInterceptor.excludePathsForBaseUrl(httpUrl, "me", "you");

        mBaseUrlInterceptor.intercept(createVerificationChain(createTestUrlForPath("/me"), createTestUrlForPath(null)));
        mBaseUrlInterceptor.intercept(createVerificationChain(createTestUrlForPath("/you"), createTestUrlForPath(null)));
        mBaseUrlInterceptor.intercept(createVerificationChain(createTestUrlForPath("/them"), httpUrl));
    }

    @Test
    public void test_excludePathsForBaseUrl_EmptyPaths_UnsuccessfullyIntercepts() throws Exception {
        HttpUrl httpUrl = HttpUrl.parse("http://localhost");
        mBaseUrlInterceptor.excludePathsForBaseUrl(httpUrl);

        mBaseUrlInterceptor.intercept(createVerificationChain(createTestUrlForPath("/me"), httpUrl));
        mBaseUrlInterceptor.intercept(createVerificationChain(createTestUrlForPath("/you"), httpUrl));
        mBaseUrlInterceptor.intercept(createVerificationChain(createTestUrlForPath("/them"), httpUrl));
    }

    @Test(expected = RuntimeException.class)
    public void test_excludeThenIncludePathsForBaseUrl_NoReset_ThrowsException() throws Exception {
        HttpUrl httpUrl = HttpUrl.parse("http://localhost");
        mBaseUrlInterceptor.excludePathsForBaseUrl(httpUrl);
        mBaseUrlInterceptor.includePathsForBaseUrl(httpUrl);
    }

    @Test(expected = RuntimeException.class)
    public void test_includeThenExcludePathsForBaseUrl_NoReset_ThrowsException() throws Exception {
        HttpUrl httpUrl = HttpUrl.parse("http://localhost");
        mBaseUrlInterceptor.excludePathsForBaseUrl(httpUrl);
        mBaseUrlInterceptor.includePathsForBaseUrl(httpUrl);
    }

    @Test
    public void test_excludeThenIncludePathsForBaseUrl_Reset_Includes() throws Exception {
        HttpUrl httpUrl = HttpUrl.parse("http://localhost");
        mBaseUrlInterceptor.excludePathsForBaseUrl(httpUrl, "/me", "/you");
        mBaseUrlInterceptor.resetBaseUrl();
        mBaseUrlInterceptor.includePathsForBaseUrl(httpUrl, "/me", "/you");

        mBaseUrlInterceptor.intercept(createVerificationChain(createTestUrlForPath("/me"), httpUrl));
        mBaseUrlInterceptor.intercept(createVerificationChain(createTestUrlForPath("/you"), httpUrl));
        mBaseUrlInterceptor.intercept(createVerificationChain(createTestUrlForPath("/them"), createTestUrlForPath(null)));
    }

    @Test
    public void test_includeThenExcludePathsForBaseUrl_Reset_Exclues() throws Exception {
        HttpUrl httpUrl = HttpUrl.parse("http://localhost");
        mBaseUrlInterceptor.includePathsForBaseUrl(httpUrl, "/me", "/you");
        mBaseUrlInterceptor.resetBaseUrl();
        mBaseUrlInterceptor.excludePathsForBaseUrl(httpUrl, "/me", "/you");

        mBaseUrlInterceptor.intercept(createVerificationChain(createTestUrlForPath("/me"), createTestUrlForPath(null)));
        mBaseUrlInterceptor.intercept(createVerificationChain(createTestUrlForPath("/you"), createTestUrlForPath(null)));
        mBaseUrlInterceptor.intercept(createVerificationChain(createTestUrlForPath("/them"), httpUrl));
    }

    @Test
    public void test_NoIncludeOrExclude_NoIntercept() throws Exception {
        mBaseUrlInterceptor.intercept(createVerificationChain(createTestUrlForPath("/me"), createTestUrlForPath(null)));
        mBaseUrlInterceptor.intercept(createVerificationChain(createTestUrlForPath("/you"), createTestUrlForPath(null)));
        mBaseUrlInterceptor.intercept(createVerificationChain(createTestUrlForPath("/them"), createTestUrlForPath(null)));
    }

    @Test
    public void test_resetBaseUrl_AfterInclude_NoIntercept() throws Exception {
        HttpUrl httpUrl = HttpUrl.parse("http://localhost");
        mBaseUrlInterceptor.includePathsForBaseUrl(httpUrl, "/me", "/you");
        mBaseUrlInterceptor.resetBaseUrl();

        mBaseUrlInterceptor.intercept(createVerificationChain(createTestUrlForPath("/me"), createTestUrlForPath(null)));
        mBaseUrlInterceptor.intercept(createVerificationChain(createTestUrlForPath("/you"), createTestUrlForPath(null)));
        mBaseUrlInterceptor.intercept(createVerificationChain(createTestUrlForPath("/them"), createTestUrlForPath(null)));
    }
}