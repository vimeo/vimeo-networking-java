package com.vimeo.networking2

/**
 *
 */
class RetrofitServicesCache(private val retrofitManager: RetrofitManager) {

    /**
     * Cache of Retrofit API Services.  Objects created by calling getService().
     */
    private val cache = mutableMapOf<Class<*>, Any?>()

    /**
     * Create and cache instance of the given Retrofit Service interface.
     */
    fun <T> getService(serviceClass: Class<T>): Any? =
        cache.putIfAbsent(serviceClass, retrofitManager.retrofit.create(serviceClass))

    /**
     * Empty service endpoint cache.
     */
    fun clear() {
        cache.clear()
    }


}
