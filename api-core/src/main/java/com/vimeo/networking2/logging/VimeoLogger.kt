/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2020 Vimeo
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
package com.vimeo.networking2.logging

/**
 * Internal logger that delegates to a [LogDelegate] based on the desired [LogDelegate.Level].
 *
 * @param logDelegate The class to which the logging implementation will be delegated. Default is no implementation.
 * @param logLevel The level of logs that should be sent. Default is no logs.
 */
@Suppress("FunctionMinLength")
internal class VimeoLogger(
    private val logDelegate: LogDelegate? = null,
    private val logLevel: LogDelegate.Level = LogDelegate.Level.NONE
) : LogDelegate {

    override fun e(error: String) {
        if (logLevel.level <= LogDelegate.Level.ERROR.level) {
            logDelegate?.e(error.prependIdentifier())
        }
    }

    override fun e(error: String, exception: Exception) {
        if (logLevel.level <= LogDelegate.Level.ERROR.level) {
            logDelegate?.e(error.prependIdentifier(), exception)
        }
    }

    override fun d(message: String) {
        if (logLevel.level <= LogDelegate.Level.DEBUG.level) {
            logDelegate?.d(message.prependIdentifier())
        }
    }

    override fun v(message: String) {
        if (logLevel.level <= LogDelegate.Level.VERBOSE.level) {
            logDelegate?.v(message.prependIdentifier())
        }
    }

    /**
     * Formats the message to include the library identifier.
     */
    private fun String.prependIdentifier(): String = "$LIBRARY_IDENTIFIER: $this"

    private companion object {
        private const val LIBRARY_IDENTIFIER = "vimeo-networking"
    }
}
