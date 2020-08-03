/*
 * Copyright (c) 2020 Vimeo (https://vimeo.com)
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
package com.vimeo.networking2.config

import com.vimeo.networking2.ApiConstants
import com.vimeo.networking2.ScopeType
import com.vimeo.networking2.Scopes
import com.vimeo.networking2.account.AccountStore
import com.vimeo.networking2.account.InMemoryAccountStore
import com.vimeo.networking2.logging.LogDelegate
import com.vimeo.networking2.logging.DefaultLogDelegate
import okhttp3.Cache
import okhttp3.Interceptor
import java.io.File
import java.util.Locale

/**
 * The configuration for the client.
 *
 * @param baseUrl The base API URL to which all requests will be made.
 * @param clientId The Vimeo API ID of the client application.
 * @param clientSecret The Vimeo API secret of the client application.
 * @param scope The [Scopes] required by the client application.
 * @param codeGrantRedirectUri The code grant redirect used for OAuth.
 * @param locales A list of locales which the API should use to localize responses. Locales are prioritized based on
 * index.
 * @param accountStore The store used to remember authenticated accounts.
 * @param networkInterceptors The interceptors that should be attached to network requests. See
 * [https://square.github.io/okhttp/interceptors/].
 * @param applicationInterceptors The interceptors that should be attached to all requests. See
 * [https://square.github.io/okhttp/interceptors/].
 * @param userAgent The supplemental user agent string which will be added to the internal user agent, identifying the
 * client.
 * @param requestTimeoutSeconds The maximum number of seconds a request will wait to complete before timing out.
 * @param isCertPinningEnabled True if the library should pin certificates, false otherwise. Note: should be turned off
 * when debugging.
 * @param logDelegate The instance to which logging will be delegated.
 * @param logLevel The level of logging which will be performed by the library.
 * @param cacheDirectory The optional directory in which requests will be cached.
 * @param cacheMaxSizeBytes The maximum size of the cache, if one was provided.
 * @param cacheMaxAgeSeconds The maximum age of items in the cache, if one was provided.
 */
data class Configuration(
    val baseUrl: String,

    val clientId: String,
    val clientSecret: String,
    val scope: Scopes,
    val codeGrantRedirectUri: String,

    val locales: List<Locale>,

    val accountStore: AccountStore,

    val networkInterceptors: List<Interceptor>,
    val applicationInterceptors: List<Interceptor>,

    val userAgent: String,

    val requestTimeoutSeconds: Long,

    val isCertPinningEnabled: Boolean,

    val logDelegate: LogDelegate?,
    val logLevel: LogDelegate.Level,

    val cacheDirectory: File?,
    val cacheMaxSizeBytes: Long,
    val cacheMaxAgeSeconds: Int
) {

    /**
     * The OkHttp cache that should be used, if any.
     */
    internal val cache: Cache? = cacheDirectory?.let { Cache(it, cacheMaxSizeBytes) }

    /**
     * The builder for the [Configuration].
     *
     * @param clientId The Vimeo API client ID, required to construct an instance.
     * @param clientSecret The Vimeo API client secret, required to construct an instance.
     * @param scopes The permission scopes requested by the client, required to construct an instance.
     */
    class Builder(private val clientId: String, private val clientSecret: String, private val scopes: List<ScopeType>) {
        private var baseUrl: String = ApiConstants.BASE_URL

        private var codeGrantRedirectUrl: String = "vimeo$clientId://auth"

        private var locales: List<Locale> = listOf(Locale.US)

        private var accountStore: AccountStore = InMemoryAccountStore()

        private var networkInterceptors: List<Interceptor> = emptyList()
        private var applicationInterceptors: List<Interceptor> = emptyList()

        private var userAgent: String = DEFAULT_USER_AGENT

        private var requestTimeoutSeconds: Long = DEFAULT_TIMEOUT

        private var isCertPinningEnabled: Boolean = true

        private var logDelegate: LogDelegate? = DefaultLogDelegate()
        private var logLevel: LogDelegate.Level = LogDelegate.Level.NONE

        private var cacheDirectory: File? = null
        private var cacheMaxSizeBytes: Long = DEFAULT_CACHE_SIZE
        private var cacheMaxAgeSeconds: Int = DEFAULT_CACHE_MAX_AGE

        /**
         * Specify a base URL. Defaults to [ApiConstants.BASE_URL].
         *
         * @see Configuration.baseUrl
         */
        fun withBaseUrl(baseUrl: String) = apply { this.baseUrl = baseUrl }

        /**
         * Specify a code grant redirect. Defaults to `vimeo{clientId}://auth`.
         *
         * @see Configuration.codeGrantRedirectUri
         */
        fun withCodeGrantRedirectUrl(codeGrantRedirectUrl: String) = apply {
            this.codeGrantRedirectUrl = codeGrantRedirectUrl
        }

        /**
         * Specify a set of locales. Defaults to a list containing [Locale.US].
         *
         * @see Configuration.locales
         */
        fun withLocales(locales: List<Locale>) = apply { this.locales = locales }

        /**
         * Specify a store. Defaults to [InMemoryAccountStore].
         *
         * @see Configuration.locales
         */
        fun withAccountStore(accountStore: AccountStore) = apply { this.accountStore = accountStore }

        /**
         * Specify a set of network request interceptors. Defaults to none.
         *
         * @see Configuration.networkInterceptors
         */
        fun withNetworkInterceptors(networkInterceptors: List<Interceptor>) = apply {
            this.networkInterceptors = networkInterceptors
        }

        /**
         * Specify a set of application interceptors. Defaults to none.
         *
         * @see Configuration.applicationInterceptors
         */
        fun withApplicationInterceptors(
            applicationInterceptors: List<Interceptor>
        ) = apply { this.applicationInterceptors = applicationInterceptors }

        /**
         * Specify a supplemental user agent. Defaults to [DEFAULT_USER_AGENT].
         *
         * @see Configuration.userAgent
         */
        fun withUserAgent(userAgent: String) = apply { this.userAgent = userAgent }

        /**
         * Specify a request time out. Defaults to [DEFAULT_TIMEOUT].
         *
         * @see Configuration.requestTimeoutSeconds
         */
        fun withRequestTimeout(requestTimeoutSeconds: Long) = apply {
            this.requestTimeoutSeconds = requestTimeoutSeconds
        }

        /**
         * Enable or disable certificate pinning. Defaults to true.
         *
         * @see Configuration.isCertPinningEnabled
         */
        fun withCertPinning(certPinningEnabled: Boolean) = apply { this.isCertPinningEnabled = certPinningEnabled }

        /**
         * Specify a log delegate, or none at all. Defaults to [DefaultLogDelegate] which logs to the system output.
         *
         * @see Configuration.logDelegate
         */
        fun withLogDelegate(logDelegate: LogDelegate?) = apply { this.logDelegate = logDelegate }

        /**
         * Specify a log level. Defaults to [LogDelegate.Level.NONE], which never logs.
         *
         * @see Configuration.logLevel
         */
        fun withLogLevel(logLevel: LogDelegate.Level) = apply { this.logLevel = logLevel }

        /**
         * Specify a cache directory. Defaults to no cache.
         *
         * @see Configuration.cacheDirectory
         */
        fun withCacheDirectory(cacheDirectory: File) = apply { this.cacheDirectory = cacheDirectory }

        /**
         * Specify a maximum cache size. Defaults to [DEFAULT_CACHE_SIZE].
         *
         * @see Configuration.cacheMaxSizeBytes
         */
        fun withCacheMaxSizeBytes(cacheMaxSizeBytes: Long) = apply { this.cacheMaxSizeBytes = cacheMaxSizeBytes }

        /**
         * Specify a maximum cache age. Defaults to [DEFAULT_CACHE_MAX_AGE].
         *
         * @see Configuration.cacheMaxAgeSeconds
         */
        fun withCacheMaxAgeSeconds(cacheMaxAgeSeconds: Int) = apply { this.cacheMaxAgeSeconds = cacheMaxAgeSeconds }

        /**
         * Build the configuration instance using the chosen modifications and unspecified default values.
         */
        fun build(): Configuration = Configuration(
            baseUrl = baseUrl,
            clientId = clientId,
            clientSecret = clientSecret,
            scope = Scopes(scopes),
            codeGrantRedirectUri = codeGrantRedirectUrl,
            locales = locales,
            accountStore = accountStore,
            networkInterceptors = networkInterceptors,
            applicationInterceptors = applicationInterceptors,
            userAgent = userAgent,
            requestTimeoutSeconds = requestTimeoutSeconds,
            isCertPinningEnabled = isCertPinningEnabled,
            logDelegate = logDelegate,
            logLevel = logLevel,
            cacheDirectory = cacheDirectory,
            cacheMaxSizeBytes = cacheMaxSizeBytes,
            cacheMaxAgeSeconds = cacheMaxAgeSeconds
        )
    }

    companion object {
        /**
         * The default max age of the cache, 2 hours.
         */
        const val DEFAULT_CACHE_MAX_AGE = 60 * 60 * 2

        /**
         * The default size of the cache, 10 Mib.
         */
        const val DEFAULT_CACHE_SIZE = 10L * 1024 * 1024

        /**
         * Default response timeout, 60 seconds.
         */
        const val DEFAULT_TIMEOUT = 60L

        /**
         * An unspecified user agent.
         */
        const val DEFAULT_USER_AGENT = "UNSPECIFIED"

    }

}
