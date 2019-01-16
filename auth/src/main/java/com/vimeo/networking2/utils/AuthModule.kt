package com.vimeo.networking2.utils

import com.vimeo.networking2.config.RetrofitServicesCache
import com.vimeo.networking2.requests.AuthService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

/**
 * Provides all authenticated dependencies.
 */
object AuthModule {

    /**
     * Authentication coroutine scope.
     *
     * @return Coroutine scope for doing authentication.
     */
    val authCoroutineScope = CoroutineScope(Job() + Dispatchers.IO)

    /**
     * Get the Retrofit service for authentication endpoints.
     */
    fun authService(retrofitServicesCache: RetrofitServicesCache): AuthService =
        retrofitServicesCache.getService(AuthService::class.java)

}
