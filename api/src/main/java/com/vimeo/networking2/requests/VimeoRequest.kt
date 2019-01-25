package com.vimeo.networking2.requests

/**
 * Common actions such as canceling all API requests.
 */
interface VimeoRequest {

    /**
     * Cancels the API request for you.
     */
    fun cancel()

}
