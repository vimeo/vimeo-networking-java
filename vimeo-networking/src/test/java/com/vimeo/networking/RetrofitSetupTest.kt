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

package com.vimeo.networking

import com.vimeo.networking.Configuration.Builder
import com.vimeo.networking.logging.ClientLogger
import okhttp3.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

/**
 * Unit tests for [RetrofitSetup].
 * Created by brentwatson on 8/8/17.
 */
class RetrofitSetupTest {

    @Before
    fun setUp() {
        ClientLogger.setLogLevel(Vimeo.LogLevel.ERROR)
    }

    @Test
    fun `user agent is successfully created with valid characters`() {
        val retrofitSetup = RetrofitSetup(
            Configuration.Builder("test")
                .setBaseUrl("http://unittesting")
                .setUserAgentString("test/test")
                .build(),
            null
        )

        assertEquals("test/test VimeoNetworking/${BuildConfig.VERSION} (Java)", retrofitSetup.createUserAgent())
    }

    @Test
    fun `user agent is created without provided characters if invalid`() {
        val retrofitSetup = RetrofitSetup(
            Configuration.Builder("test")
                .setBaseUrl("http://unittesting")
                .setUserAgentString("test/™")
                .build(),
            null
        )

        assertEquals("VimeoNetworking/${BuildConfig.VERSION} (Java)", retrofitSetup.createUserAgent())
    }

    /**
     * Test that the headers we added using `Interceptor`s are what we expect.
     */
    @Test
    fun testInjectedHeaders() {
        // These values will get set by the interceptor below:
        var acceptHeader: String? = null
        var userAgentHeader: String? = null

        val configuration = Builder("")
            .setBaseUrl("http://unittesting")
            .setClientIdAndSecret("unit", "testing")
            .setScope("unittesting")
            .setUserAgentString("unit-testing-user-agent") // <-- This value will be asserted
            .enableCertPinning(false)
            .addInterceptor { chain ->
                // Intercept request and return some static data
                val mockResponse = ResponseBody.create(MediaType.parse("application/json"), "{'unit': 'testing'}")

                // Store HTTP header values that we will assert.
                val request = chain.request().apply {
                    acceptHeader = header(Vimeo.HEADER_ACCEPT)
                    userAgentHeader = header(Vimeo.HEADER_USER_AGENT)
                }

                // Return static response (avoiding actual network request in unit testing)
                Response.Builder().request(request)
                    .protocol(Protocol.HTTP_1_1)
                    .code(200)
                    .body(mockResponse)
                    .message("")
                    .build()
            }
            .build()

        with(RetrofitSetup(configuration, null).createOkHttpClient()) {
            val request = Request.Builder().url("http://unit.test").build()
            newCall(request).execute()
        }

        assertTrue(
            "ACCEPT header does not start as expected: [$acceptHeader]",
            acceptHeader?.startsWith("application/vnd.vimeo.*+json;") == true
        )
        assertTrue(
            "USER-AGENT header does not contain 'unit-testing-user-agent': [$userAgentHeader]",
            userAgentHeader?.contains("unit-testing-user-agent") == true
        )
        assertTrue(
            "USER-AGENT header does not contain 'VimeoNetworking': [$userAgentHeader]",
            userAgentHeader?.contains("VimeoNetworking") == true
        )
    }
}
