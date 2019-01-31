package com.vimeo.networking2

/**
 * Common actions such as canceling all API requests.
 */
interface VimeoRequest {

    /**
     * Cancels the API request for you.
     */
    fun cancel()

}
