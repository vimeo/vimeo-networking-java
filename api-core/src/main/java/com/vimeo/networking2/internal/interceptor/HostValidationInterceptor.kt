package com.vimeo.networking2.internal.interceptor

import com.vimeo.networking2.config.VimeoApiConfiguration
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response
import okio.IOException

/**
 * An interceptor that ensures that only requests to the host specified in the [VimeoApiConfiguration] are made. This
 * prevents unexpected requests from being made to other hosts.
 *
 * @param vimeoApiConfiguration The configuration used to determine the expected host.
 */
class HostValidationInterceptor(private val vimeoApiConfiguration: VimeoApiConfiguration) : Interceptor {
    private val httpUrl = HttpUrl.parse(vimeoApiConfiguration.baseUrl)

    override fun intercept(chain: Interceptor.Chain): Response =
        if (chain.request().url().host() != httpUrl?.host()) {
            throw IOException(
                "Host must match specified base URL, was ${chain.request().url().host()}, expected ${httpUrl?.host()}"
            )
        } else {
            chain.proceed(chain.request())
        }
}
