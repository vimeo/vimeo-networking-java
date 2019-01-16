package com.vimeo.networking2.requests

/**
 * Common actions such cancel that all API requests should have.
 */
interface ApiRequest {

    /**
     * Cancels the API request for you.
     */
    fun cancel()

}
