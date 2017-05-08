package com.vimeo.android.networking.example.kotlin.vimeonetworking

import android.util.Log

import com.vimeo.networking.logging.LogProvider

/**
 * Logger delegate for handling logs coming from the Networking library
 *
 *
 * Created by kylevenn on 1/27/16.
 */
class NetworkingLogger : LogProvider {

    override fun e(error: String) {
        Log.e(TEST_APP, error)
    }

    override fun e(error: String, exception: Exception) {
        Log.e(TEST_APP, error, exception)
    }

    override fun d(debug: String) {
        Log.d(TEST_APP, debug)
    }

    override fun v(verbose: String) {
        Log.v(TEST_APP, verbose)
    }

    companion object {

        private val TEST_APP = "~NET TEST APP~"
    }
}
