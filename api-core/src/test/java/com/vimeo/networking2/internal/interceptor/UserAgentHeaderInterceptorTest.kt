package com.vimeo.networking2.internal.interceptor

import okhttp3.Call
import okhttp3.Connection
import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.concurrent.TimeUnit

class UserAgentHeaderInterceptorTest {

    private fun createChain(): Interceptor.Chain = object : Interceptor.Chain {
        override fun request(): Request = Request.Builder().url("https://vimeo.com").build()

        override fun proceed(request: Request): Response = Response.Builder()
            .request(request)
            .protocol(Protocol.HTTP_2)
            .code(200)
            .message("hello")
            .build()

        override fun connection(): Connection = error("Unsupported")

        override fun call(): Call = error("Unsupported")

        override fun connectTimeoutMillis(): Int = error("Unsupported")

        override fun withConnectTimeout(timeout: Int, unit: TimeUnit): Interceptor.Chain = error("Unsupported")

        override fun readTimeoutMillis(): Int = error("Unsupported")

        override fun withReadTimeout(timeout: Int, unit: TimeUnit): Interceptor.Chain = error("Unsupported")

        override fun writeTimeoutMillis(): Int = error("Unsupported")

        override fun withWriteTimeout(timeout: Int, unit: TimeUnit): Interceptor.Chain = error("Unsupported")
    }

    @Test
    fun `normal characters are supported`() {
        assertEquals(
            UserAgentHeaderInterceptor(userAgent = "test").intercept(createChain()).request().header("User-Agent"),
            "test"
        )
    }

    @Test
    fun `special characters are supported`() {
        assertEquals(
            UserAgentHeaderInterceptor(userAgent = "test²").intercept(createChain()).request().header("User-Agent"),
            "test²"
        )
    }
}
